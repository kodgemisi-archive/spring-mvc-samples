package com.mvc.spring.sample.config;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Created by Aykut on 30.01.2016.
 */
public class MvcWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.setConfigLocation("com.mvc.spring.sample.config");
        servletContext.addListener(new ContextLoaderListener(dispatcherContext));

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
        
        initH2TCPServer(servletContext);
    }
    
    @Bean(initMethod="start", destroyMethod="stop")
    public Server initH2TCPServer(ServletContext servletContext) {
        Server server = null;
        try {
            server = Server.createTcpServer( "-tcp", "-tcpAllowOthers", "-tcpPort", "9092" );
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            //Always return the H2Console...
            initH2Console( servletContext );
        }
        return server;
    }

    public void initH2Console(ServletContext servletContext) {
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping( "/console/*" );
    }

}
