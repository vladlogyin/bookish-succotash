package com.sparta.spartafinalproject.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/home", "/movies", "/theatres", "/comments").permitAll()
                .antMatchers("/logout", "/comments/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers("/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/home",true).permitAll().and().exceptionHandling()
                .accessDeniedPage("/accessDenied")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home").clearAuthentication(true).deleteCookies();
    }
}
