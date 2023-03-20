/*
 * ***************************************************************
 * Truteq Internet Payment Gateway (IPGW) version 1.0.0
 * ***************************************************************
 * Copyright (c) 2021 Truteq Australia 2019
 * ***************************************************************
 * IPGW Front end Adapter: SABRE - Microservice for IPGW - SABRE 
 * Support: Grant O'Reilly gbo@truteq.com
 * V01.00.00  20-Nov-2020 
 * ***************************************************************
 */
package com.truteq.ccpgw.frontend.adapter.sabre.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

/**
 *
 * @author Grant Blaise O'Reilly <gbo@truteq.com>
 */

@EnableWs
@Configuration
public class Config {
    
    @Value(value = "${ipgw.frontend.apdapter.url.sabre}")
    private String sabreAdapterUrl;    
    
    @Value(value = "${ipgw.frontend.apdapter.sabre.xsd.schema}")
    private String xsdSchemaFile; 
    
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) 
    {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/sabre/*");
    }
 
    /**
     * http://localhost:9091/sabre/SupplierPaymentServicesDetailsWsdl.wsdl
     * @param SupplierPaymentServicesSchema
     * @return 
     */
    @Bean(name = "SupplierPaymentServicesDetailsWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema SupplierPaymentServicesSchema) 
    {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("SupplierPaymentServicesPort");
        wsdl11Definition.setLocationUri("/sabre");
        wsdl11Definition.setTargetNamespace(sabreAdapterUrl);
        wsdl11Definition.setSchema(SupplierPaymentServicesSchema);
        return wsdl11Definition;
    }
 
    @Bean
    public XsdSchema SupplierPaymentServicesSchema() 
    {
        return new SimpleXsdSchema(new ClassPathResource(xsdSchemaFile));
    } 
    

}
