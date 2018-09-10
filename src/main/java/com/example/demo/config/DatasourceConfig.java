package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;


/**
 * Druid的DataResource配置类 ，
 * 获取application配置文件中的变量的两种方法1和2。
 * 创建数据源DataSource的两种方法A和B。 所在的类必须在spring的ioc容器中。
 */
@Configuration		
public class DatasourceConfig{
    
	//1.注解@Autowired 自动装配Environment类，env.getProperty(“key”)获取value
    @Autowired
    private Environment env;// 配置文件
	
    @Autowired
    DataSource dataSource;//A. 在application.properties文件中配置，直接默认配置文件中的数据源
    
    //2.在变量上注解@Value("${key}")获取value
    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String connectionUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.initialSize}")
    private Integer initialSize;
    @Value("${spring.datasource.minIdle}")
    private Integer minIdle;
    @Value("${spring.datasource.maxActive}")
    private Integer maxActive;
    @Value("${spring.datasource.maxWait}")
    private Integer maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private Boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private Boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private Boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private Boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private Integer maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${druid.loginUsername}")
    private String loginUsername;
    @Value("${druid.loginPassword}")
    private String loginPassword;
    
       
    /**
     * B. 手动创建dataSource(),
     * @return
     * @throws Exception
     */
    /* @Bean
	public DataSource dataSource() throws Exception {
		DruidDataSource dds = new DruidDataSource();
		//dds.setDriverClassName(env.getProperty("driverClassName"));//1
        dds.setDriverClassName(driverClassName);//2
        dds.setUrl(connectionUrl);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setInitialSize(initialSize);
        dds.setMinIdle(minIdle);
        dds.setMaxActive(maxActive);
        dds.setMaxWait(maxWait);
        dds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dds.setValidationQuery(validationQuery);
        dds.setTestWhileIdle(testWhileIdle);
        dds.setTestOnBorrow(testOnBorrow);
        dds.setTestOnReturn(testOnReturn);
        dds.setPoolPreparedStatements(poolPreparedStatements);
        dds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            dds.setFilters(filters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dds;
    }*/
	
	/**
	 * 设置登录查看信息的账号密码.
	 * @return
	 */
	@Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("loginUsername",loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword",loginPassword);
        return servletRegistrationBean;
    }
	
	/**
	 * Druid的StatFilter
	 * @return
	 */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
    

}
