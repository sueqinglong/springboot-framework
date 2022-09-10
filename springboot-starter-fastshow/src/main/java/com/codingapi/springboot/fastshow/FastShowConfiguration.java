package com.codingapi.springboot.fastshow;

import com.codingapi.springboot.fastshow.executor.JpaExecutor;
import com.codingapi.springboot.fastshow.mapping.MvcEndpointMapping;
import com.codingapi.springboot.fastshow.registrar.MvcMappingRegistrar;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.persistence.EntityManager;

@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
public class FastShowConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public MvcEndpointMapping mvcEndpointMapping(RequestMappingHandlerMapping handlerMapping){
        return new MvcEndpointMapping(handlerMapping);
    }

    @Bean(initMethod = "registerMvcMapping")
    @ConditionalOnMissingBean
    public MvcMappingRegistrar mappingRegistrar(MvcEndpointMapping mvcEndpointMapping,JpaExecutor jpaExecutor){
        return new MvcMappingRegistrar(mvcEndpointMapping,jpaExecutor);
    }

    @Bean
    @ConditionalOnMissingBean
    public JpaExecutor jpaExecutor(EntityManager entityManager){
        return new JpaExecutor(entityManager);
    }

}
