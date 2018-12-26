package gr.xxm.config;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration()
@EnableJpaRepositories()
public class JdbcConfig {
	
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver.class}")
    private String driver;
    
    @Bean("dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        DruidDataSource basicDataSource = new DruidDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setInitialSize(10);
        basicDataSource.setMaxActive(50);
        basicDataSource.setFilters("stat,wall");
        basicDataSource.setDriverClassName(driver);
        log.info("使用本地数据源：" + basicDataSource.getUrl());
        return basicDataSource;
    }

    /*@Bean(name = "entityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) throws SQLException {
        return entityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) throws SQLException {
        return builder.dataSource(this.dataSource())
                //设置实体类所在位置
                .packages("gr.xxm.entity")
                .persistenceUnit("logsPersistenceUnit")
                .build();
    }
    
    @Bean(name = "transactionManager")
    @Primary
    PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) throws SQLException {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }*/
}