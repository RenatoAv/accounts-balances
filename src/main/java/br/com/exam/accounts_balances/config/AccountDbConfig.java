package br.com.exam.accounts_balances.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "br.com.exam.accounts_balances.account.service.repository",
        entityManagerFactoryRef = "accountEmf",
        transactionManagerRef = "accountTx"
)
public class AccountDbConfig {

    @Bean
    @ConfigurationProperties("app.datasource.account")
    public DataSourceProperties accountDsProps() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource accountDs(@Qualifier("accountDsProps") DataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean accountEmf(
            EntityManagerFactoryBuilder builder,
            @Qualifier("accountDs") DataSource ds
    ) {
        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "create");

        return builder
                .dataSource(ds)
                .packages("br.com.exam.accounts_balances.account.service.repository")
                .properties(props)
                .persistenceUnit("accountPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager accountTx(
            @Qualifier("accountEmf") EntityManagerFactory emf
    ) {
        return new JpaTransactionManager(emf);
    }
}
