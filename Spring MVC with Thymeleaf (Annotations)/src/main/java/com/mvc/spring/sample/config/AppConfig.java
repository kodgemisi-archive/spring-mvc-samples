package com.mvc.spring.sample.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.activation.DataSource;

/**
 * Created by Aykut on 30.01.2016.
 */
@EnableWebMvc
@Configuration
@ComponentScan({"com.mvc.spring.sample"})
public class AppConfig {
//    @Autowired
//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//
//        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//
//        sessionBuilder.scanPackages("com.kodgemisi.parabot.model");
//        sessionBuilder.addProperties(getHibernateProperties());
//
//        return sessionBuilder.buildSessionFactory();
//    }
}
