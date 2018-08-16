package com.example.demo.config;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.alibaba.druid.pool.DruidDataSource;

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
		//Interceptor[] interceptor = {new PaginationInterceptor()};
		//sqlSessionFactoryBean.setPlugins(interceptor); //分页插件
		
		return sqlSessionFactory;
	}
	
	

	/**
	 * 开启事务管理  三种方法
	 * 1.注解式事务，手动创建transactionManager()
	 * 2.注解式事务，在启动类SpringBootDemoApplication上添加注解@EnableTransactionManagement
	 * 3.声明式事务，
	 * 
	 * @return
	 */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
	
    /*@Bean("txSource")
    public TransactionAttributeSource transactionAttributeSource(){
      NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
       只读事务，不做更新操作
      RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
      readOnlyTx.setReadOnly(true);
      readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED );
      当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务
      //RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
      //requiredTx.setRollbackRules(
      //  Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
      //requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
      RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED,
          Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
      requiredTx.setTimeout(5);
      Map<String, TransactionAttribute> txMap = new HashMap<>();
      txMap.put("add*", requiredTx);
      txMap.put("save*", requiredTx);
      txMap.put("insert*", requiredTx);
      txMap.put("update*", requiredTx);
      txMap.put("delete*", requiredTx);
      txMap.put("get*", readOnlyTx);
      txMap.put("query*", readOnlyTx);
      source.setNameMap( txMap );
      return source;
    }
    *//**切面拦截规则 参数会自动从容器中注入*//*
    @Bean
    public AspectJExpressionPointcutAdvisor pointcutAdvisor(TransactionInterceptor txInterceptor){
      AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
      pointcutAdvisor.setAdvice(txInterceptor);
      pointcutAdvisor.setExpression("execution (* com.alibaba.fm9..service.*.*(..))");
      return pointcutAdvisor;
    }
    事务拦截器
    @Bean("txInterceptor")
    public TransactionInterceptor getTransactionInterceptor(PlatformTransactionManager tx){
      return new TransactionInterceptor(tx , transactionAttributeSource()) ;
    }*/
	
}