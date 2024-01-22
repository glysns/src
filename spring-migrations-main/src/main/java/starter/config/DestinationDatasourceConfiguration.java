package starter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "destinationEntityManagerFactory",
        transactionManagerRef = "destinationTransactionManager",
        basePackages = {"starter.repository.destination"})
public class DestinationDatasourceConfiguration {
    @Primary
    @Bean(name="destinationProperties")
    @ConfigurationProperties("spring.datasource.dest")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties p = new DataSourceProperties();
        return p;
    }
    @Primary
    @Bean(name="destinationDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.dest")
    public DataSource datasource(@Qualifier("destinationProperties") DataSourceProperties properties){
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "destinationEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder,
             @Qualifier("destinationDatasource") DataSource dataSource) {
        return builder.dataSource(dataSource)
                .packages("starter.model.destination")
                .persistenceUnit("destination").build();
    }

    @Bean(name = "destinationTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("destinationEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
