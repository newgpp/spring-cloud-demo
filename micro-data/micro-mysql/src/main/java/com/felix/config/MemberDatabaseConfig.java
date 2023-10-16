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
@MapperScan(value = "com.felix.dao.member", sqlSessionFactoryRef = "memberSqlSessionFactory")
public class MemberDatabaseConfig {

    @Bean(name = "memberDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.member.datasource")
    public DataSource memberDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "memberSqlSessionFactory")
    @Primary
    public SqlSessionFactory memberSqlSessionFactory(@Qualifier("memberDataSource") DataSource memberDataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(memberDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.felix.model.entity");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/member/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "memberSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate memberSqlSessionTemplate(@Qualifier("memberSqlSessionFactory") SqlSessionFactory memberSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(memberSqlSessionFactory);
    }

    @Bean(name = "memberTxManager")
    @Primary
    public PlatformTransactionManager memberTxManager(@Autowired @Qualifier("memberDataSource") DataSource memberDataSource) {
        return new DataSourceTransactionManager(memberDataSource);
    }

}