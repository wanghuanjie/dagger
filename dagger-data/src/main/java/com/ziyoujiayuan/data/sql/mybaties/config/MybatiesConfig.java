package com.ziyoujiayuan.data.sql.mybaties.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * Mybaties相关配置
 * @Author wanghjbuf
 * @Date 2017年9月14日
 */
@Configuration
@MapperScan("com.ziyoujiayuan.data.sql.mybaties.mapper")
public class MybatiesConfig {

	
//	@Bean
//	@ConfigurationProperties(prefix="spring.datasource")
//	public DataSource dataSource() {
//		DataSource dataSource = new DataSource();
//		dataSource.setTestWhileIdle(true);
//		return  dataSource;
//	}
	
    @Bean
    @ConfigurationProperties(prefix="c3p0")
    public DataSource dataSource(){
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybaties/**/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactoryBean());
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
