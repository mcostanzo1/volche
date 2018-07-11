package com.administracion.volche.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@Profile("prod")
public class DataBase {

    @Autowired
    private ApplicationContext appContext;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println(" ########### ENTRANDO A entityManagerFactory ####################");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        System.out.println("****** Datasource: "+appContext.getBean(DataSource.class));
        emfb.setDataSource(appContext.getBean(DataSource.class));
        emfb.setPersistenceUnitName("entityManagerFactory");
        emfb.setPackagesToScan("com.administracion.volche.domain");
        emfb.setJpaVendorAdapter(adapter);
        return emfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        System.out.println(" ########### ENTRANDO A transactionManager ,. ####################");
        return new JpaTransactionManager(emf);
    }
}
