package services.ravi.user.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import services.ravi.user.crm.constant.RequestMappingUrls;
import services.ravi.user.crm.service.impl.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/login/success").authenticated()
            .antMatchers("/**").permitAll()
            .anyRequest().authenticated()
            .and() // Adding new Rule.
                .formLogin()
                    .loginPage(RequestMappingUrls.LOGIN) // The request mapping for the login form
                    .loginProcessingUrl("/authenticateUser") // The URL to process the Login.
                    .successHandler(myAuthenticationSuccessHandler())
                    .permitAll() // Every one can access the Login Form
                    .and() // Add new Rule.
                    .logout().permitAll() // Allow logout support (Default URL ==> /logout
            .and()
                .exceptionHandling()
                .accessDeniedPage(RequestMappingUrls.LOGIN_FAILED) // When we have an access denied we will be redirected to that page.
        ;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandlerConfig();
    }
}