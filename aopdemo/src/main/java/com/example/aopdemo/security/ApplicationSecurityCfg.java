package com.example.aopdemo.security;

import com.example.aopdemo.security.jwt.JWTAuthenticationFilter;
import com.example.aopdemo.security.jwt.JWTTokenVerifier;
import com.example.aopdemo.security.service.ApplicationUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityCfg extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JWTTokenVerifier(), JWTAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", ".index.html").permitAll()
                //.antMatchers(HttpMethod.GET, "/aop-v1/products*").permitAll()
                //.antMatchers("/aop-v1/products", "/aop-v1/products/*").hasRole(ADMIN.name())
                //.antMatchers("/aop-v1/products", "/aop-v1/products/*").hasAuthority(WRITE.name())
                .anyRequest()
                .authenticated();
    }

    /*
    //uncomment this method if not using db based user authentication

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(Arrays.asList(
                User.builder().username("admin").password(encoder.encode("password")).roles(ADMIN.name())
                        .authorities(ADMIN.getGrantedAuthorities()).build(),
                User.builder().username("manager").password(encoder.encode("password")).roles(MANAGER.name())
                        .authorities(MANAGER.getGrantedAuthorities()).build(),
                User.builder().username("sales").password(encoder.encode("password")).roles(SALES.name())
                        .authorities(SALES.getGrantedAuthorities()).build()
        ));
    }
    */

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(encoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        return daoAuthenticationProvider;
    }
}
