package com.swam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 * Created by gkou on 2016/08/29.
 */
@EnableWs
@Configuration
@ComponentScan(value = "com.swam.ws.component")
public class SoapConfig extends WsConfigurerAdapter {


    //    @Bean
//    public DefaultWsdl11Definition country(XsdSchema countriesSchema) {
//        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
//        definition.setSchema(countriesSchema);
//
//        definition.setPortTypeName("Country");
//        definition.setLocationUri("http://wstest/uri");
//        definition.setTargetNamespace("http://wstest");
//
//        return definition;
//    }
    @Bean(name = "countries")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }
    
    @Bean(name = "userpara")
    public DefaultWsdl11Definition userparaWsdl11Definition(XsdSchema userparaSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserparaPort");
        wsdl11Definition.setLocationUri("/ws/userpara");
        wsdl11Definition.setTargetNamespace("http://spring.io/guides/gs-producing-web-service");
        wsdl11Definition.setSchema(userparaSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }
    @Bean
    public XsdSchema userparaSchema() {
        return new SimpleXsdSchema(new ClassPathResource("userpara.xsd"));
    }
}