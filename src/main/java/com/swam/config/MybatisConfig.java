package com.swam.config;

/**
 * Created by gkou on 2016/08/30.
 */


import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

import static org.apache.ibatis.session.ExecutorType.BATCH;


@Configuration
@MapperScan("com.swam.mappers")
@PropertySource("classpath:db.properties")
public class MybatisConfig {

    @Bean
    public DataSourceProperties dataSourceProperties(Environment environment) {
        return new DataSourceProperties(environment);
    }

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, DataSourceProperties properties) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setConfigLocation(new ClassPathResource(properties.getSqlMapperPath()));
        return (SqlSessionFactory) sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(DataSource dataSource, DataSourceProperties properties) throws Exception {
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory(dataSource, properties), BATCH);
        return sqlSessionTemplate;
    }



    class DataSourceProperties {

        private Environment environment;

        public DataSourceProperties(Environment environment) {
            this.environment = environment;
        }

        public String getDriverClassName() {
            return environment.getRequiredProperty("jdbc.driverClass");
        }

        public String getUrl() {
            return environment.getRequiredProperty("jdbc.url");
        }

        public String getUsername() {
            return environment.getRequiredProperty("jdbc.username");
        }

        public String getPassword() {
            return environment.getRequiredProperty("jdbc.password");
        }

        public String getSqlMapperPath() { return environment.getRequiredProperty("SqlMapperPath"); }
    }
}
