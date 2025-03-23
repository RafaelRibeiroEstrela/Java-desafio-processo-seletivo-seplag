package com.example.appdesafioprocessoseletivoseplag.configs;

import com.example.providers.exceptions.LayerDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

@Configuration
public class DomainAutoConfiguration implements BeanDefinitionRegistryPostProcessor
{

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // Cria um scanner sem considerar as anotações do Spring
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);

        // Supondo que você utilize uma interface ou marcador (por exemplo, DomainService)
        scanner.addIncludeFilter(new AssignableTypeFilter(LayerDefinition.class));

        String basePackage = "com.example.services.impl";
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> clazz = Class.forName(bd.getBeanClassName());
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
                registry.registerBeanDefinition(clazz.getSimpleName(), builder.getBeanDefinition());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Erro ao registrar o bean: " + bd.getBeanClassName(), e);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // Não é necessário implementar nada aqui para esse caso
    }
}
