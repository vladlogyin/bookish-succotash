package com.sparta.spartafinalproject.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
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
                .antMatchers("/home", "/movies/all", "/theaters/all", "/comments/all", "/schedules/all", "/mistake").permitAll()
                .antMatchers("/logout", "/comments/**", "/createcomment", "/createcomment/post",
                        "/displaycomments/{id}", "/displaytheaters/{id}","/displayschedules/{id}", "/displaymovies/{id}").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
                .and().logout()
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");

//        http.authorizeRequests()
//                .antMatchers("/home", "/movies", "/theatres", "/comments").permitAll()
//                .antMatchers("/logout", "/comments/**").hasAnyAuthority("USER","ADMIN")
//                .antMatchers("/**").hasAuthority("ADMIN")
//                .anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/home",true).permitAll().and().exceptionHandling()
//                .accessDeniedPage("/accessDenied")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home").clearAuthentication(true).deleteCookies();
    }
}
