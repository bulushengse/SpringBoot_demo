package com.example.demo.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.github.pagehelper.PageHelper;

@Aspect   //aop切面类，用于开启全局事务
@Configuration
public class MybatisConfig {

	@Autowired
    private DataSource dataSource;
	

	/**
	 * 创建sqlSessionFactory的两种方式
	 * 1.手动创建sqlSessionFactory()
	 * 2.在application.properties文件中配置
	 * @return
	 */
	@Bean
	//@ConditionalOnMissingBean // 当容器里没有指定的Bean的情况下创建该对象
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		// 数据源
		sqlSessionFactory.setDataSource(dataSource);
		// mapper文件扫描
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
        	sqlSessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 别名
        sqlSessionFactory.setTypeAliasesPackage("com.example.demo.bean");
        // 分页插件
        
        
        //sqlSessionFactory.setPlugins(new Interceptor[] { (Interceptor) pageHelper() }); 
		
		return sqlSessionFactory;
	}
	
	
	 @Bean
	 public PageHelper getPageHelper(){
		 PageHelper pageHelper=new PageHelper();
		 
		 Properties properties=new Properties();
		 properties.setProperty("helperDialect","oracle");
		 properties.setProperty("reasonable","true");
		 properties.setProperty("supportMethodsArguments","true");
		 properties.setProperty("params","count=countSql");
		 
		 pageHelper.setProperties(properties);
		 return pageHelper;
	 }
	
	
	
	
	/**
	 * 开启事务管理  两种方法
	 * 1.声明注解式事务，
	 * 首先在启动类SpringBootDemoApplication类上添加注解@EnableTransactionManagement开启事务支持（似乎可有可无）
	 * 如果是多数据源，手动创建transactionManager1() ，  transactionManager2() ， ... 
	 * 然后在访问数据库的Service类上或其方法上添加@Transactional 或  @Transactional(value="transactionManager1") 注解
	 * 
	 * 2.声明配置式事务，全局事务
	 * 
	 * @return
	 */
	/* 	@Bean
    public DataSourceTransactionManager transactionManager1() {
        return new DataSourceTransactionManager(dataSource);
    }*/
	
	//--------全局事务----------------------
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
        txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_REQUIRED_READONLY.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("add*", txAttr_REQUIRED);
        source.addTransactionalMethod("save*", txAttr_REQUIRED);
        source.addTransactionalMethod("delete*", txAttr_REQUIRED);
        source.addTransactionalMethod("update*", txAttr_REQUIRED);
        source.addTransactionalMethod("exec*", txAttr_REQUIRED);
        source.addTransactionalMethod("set*", txAttr_REQUIRED);
        source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
        source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.example.demo.service..*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
   //--------全局事务----------------------
    
}