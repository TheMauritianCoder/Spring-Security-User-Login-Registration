package services.ravi.user.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import services.ravi.user.crm.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and() // Adding new Rule.
                .formLogin()
                    .loginPage("/login") // The request mapping for the login form
                    .loginProcessingUrl("/authenticateUser") // The URL to process the Login.
                    .permitAll() // Every one can access the Login Form
                    .and() // Add new Rule.
                    .logout().permitAll() // Allow logout support (Default URL ==> /logout
            .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied") // When we have an access denied we will be redirected to that page.
        ;
    }
}