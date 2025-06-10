package com.bkosft.uploadservice.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        //stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    @Bean
//    public FilterRegistrationBean<TokenFilter> loggingFilter() {
//        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new TokenFilter(restTemplate));
//        registrationBean.addUrlPatterns("/api/v1/admin/*");
//        return registrationBean;
//    }

}
