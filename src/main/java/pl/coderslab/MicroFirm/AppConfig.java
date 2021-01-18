package pl.coderslab.MicroFirm;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.coderslab.MicroFirm.filters.AdminFilter;

import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan("pl.coderslab")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.coderslab.MicroFirm.repository")
public class AppConfig {

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter(){
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/firmData/*");
        return registrationBean;
    }

    public Set<Converter> getConverters() {
        Set<Converter> converters = new HashSet<>();
        //converters.add(new AuthorConverter());
        return converters;
    }

    @Bean(name = "conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
        factory.setConverters(getConverters());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}

