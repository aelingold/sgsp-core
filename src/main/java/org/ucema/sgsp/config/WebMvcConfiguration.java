package org.ucema.sgsp.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import freemarker.template.TemplateException;
import freemarker.template.utility.XmlEscape;

@Configuration
@EnableWebMvc
@ComponentScan("org.ucema.sgsp")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
 
        Properties exceptionMappings = new Properties();
 
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");
        exceptionMappings.put("org.springframework.security.access.AccessDeniedException", "error/403");
 
        exceptionResolver.setExceptionMappings(exceptionMappings);
 
        Properties statusCodes = new Properties();
 
        statusCodes.put("error/403", "403");
        statusCodes.put("error/404", "404");
        statusCodes.put("error/error", "500");
 
        exceptionResolver.setStatusCodes(statusCodes);
 
        return exceptionResolver;
    }    
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
 
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(2);
 
        return viewResolver;
    }
    
    @Bean
    public ViewResolver freemarkerViewResolver() {
      FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
      resolver.setCache(true);
      resolver.setPrefix("");
      resolver.setSuffix(".ftl");
      resolver.setOrder(1);
      resolver.setContentType("text/html;charset=UTF-8");
      return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() throws IOException, TemplateException {      
      FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
      configurer.setTemplateLoaderPaths("/WEB-INF/views/","classpath:/WEB-INF/views/");
      configurer.setDefaultEncoding("UTF-8");

      Properties settings = new Properties();
      settings.setProperty("number_format", "0.######");
      settings.setProperty("auto_import", "spring.ftl as spring");
      configurer.setFreemarkerSettings(settings);
      Map<String, Object> variables = new java.util.HashMap<String, Object>();

      variables.put("xml_escape", new XmlEscape());
      configurer.setFreemarkerVariables(variables);
      
      return configurer;      
    }    
}
