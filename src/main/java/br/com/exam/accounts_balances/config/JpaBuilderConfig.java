package br.com.exam.accounts_balances.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;

import java.util.Collections;

@Configuration
public class JpaBuilderConfig {

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(
            JpaVendorAdapter jpaVendorAdapter,
            ObjectProvider<PersistenceUnitManager> persistenceUnitManager
    ) {
        return new EntityManagerFactoryBuilder(
                jpaVendorAdapter,
                dataSource -> Collections.emptyMap(),
                persistenceUnitManager.getIfAvailable()
        );
    }
}