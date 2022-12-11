package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;
    @Value("classpath:public-key")
    Resource publicKeyResource;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/h2-console/**","/swagger-ui/*", "/swagger-ui.html", "/webjars/**", "/v2/**", "/swagger-resources/**" ).permitAll()
                .antMatchers("/**").authenticated();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
    }

}