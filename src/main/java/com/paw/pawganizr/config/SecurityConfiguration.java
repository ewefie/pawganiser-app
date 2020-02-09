package com.paw.pawganizr.config;


import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("http://pawganiser.sdacademy.xyz/")
                .and()
                .oauth2ResourceServer()
                .jwt();

        Okta.configureResourceServer401ResponseBody(http);
    }
}