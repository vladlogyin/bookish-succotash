package com.sparta.spartafinalproject.configs;


import org.springframework.context.annotation.Configuration;
<<<<<<< Updated upstream
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

=======
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
>>>>>>> Stashed changes
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
<<<<<<< Updated upstream
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
=======
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Admin1")
                .password("{noop}1234")
                .authorities("ADMIN");
        auth.inMemoryAuthentication()
                .withUser("Kira")
                .password("{noop}kira")
                .authorities("USER");
        auth.inMemoryAuthentication()
                .withUser("Jeff")
                .password("{noop}jeff")
                .authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // ONLY ADMINS HAVE ACCESS

                // ONLY USERS HAVE ACCESS

                // EVERYONE HAS ACCESS

                //other stuff
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("")
                .defaultSuccessUrl("", true)
                .permitAll()
                .and().logout()
                .logoutSuccessUrl("")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
>>>>>>> Stashed changes
    }
}
