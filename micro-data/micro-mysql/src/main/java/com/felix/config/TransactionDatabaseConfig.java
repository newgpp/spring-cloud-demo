/**
 *
 */
package com.felix.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(value = "com.felix.dao.transaction", sqlSessionFactoryRef = "transactionSqlSessionFactory")
public class TransactionDatabaseConfig {

    @Bean(name = "transactionDataSource")
    @ConfigurationProperties(prefix = "spring.transaction.datasource")
    public DataSource transactionDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "transactionSqlSessionFactory")
    public SqlSessionFactory transactionSqlSessionFactory(@Qualifier("transactionDataSource") DataSource transactionDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(transactionDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.felix.model.entity");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/transaction/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "transactionSqlSessionTemplate")
    public SqlSessionTemplate transactionSqlSessionTemplate(@Qualifier("transactionSqlSessionFactory") SqlSessionFactory transactionSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(transactionSqlSessionFactory);
    }

    @Bean(name = "transactionTxManager")
    public PlatformTransactionManager transactionTxManager(@Autowired @Qualifier("transactionDataSource") DataSource transactionDataSource) {
        return new DataSourceTransactionManager(transactionDataSource);
    }

}