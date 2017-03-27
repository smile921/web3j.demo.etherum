package zjex.com;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by frere921 on 2017/3/24.
 */

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean charEncodingFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(characterEncodingFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("encoding", "UTF-8");
        registration.addInitParameter("forceEncoding", "true");
        registration.setName("characterEncodingFilter");
        return registration;
    }

    @Bean(name = "characterEncodingFilter")
    public CharacterEncodingFilter characterEncodingFilter() {
        return new CharacterEncodingFilter();
    }

}
