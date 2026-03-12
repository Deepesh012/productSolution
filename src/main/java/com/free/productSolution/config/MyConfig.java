package com.free.productSolution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class MyConfig {

    @Bean
    public UserDetailsService getUserDetailService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //.csrf().disable() // ✅ disable CSRF for APIs like /api/enquiry
        .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                 new AntPathRequestMatcher("/api/enquiry"),
                 new AntPathRequestMatcher("/organization/upload"),
          		 new AntPathRequestMatcher("/news/add"),
          		 new AntPathRequestMatcher("/news/delete/**")
          		 )
                )
            
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/dashboard/**").authenticated()
                .requestMatchers("/user/**").authenticated()
                .requestMatchers("/carousel").authenticated()
                .requestMatchers("/media*").authenticated()
                .requestMatchers("/youtube/replace/**").authenticated()
                .requestMatchers("/organization-chart/upload").authenticated()
                .requestMatchers("/news/manage-news").authenticated()
                                .anyRequest().permitAll()
            )
            .formLogin(login -> login
                .loginPage("/signin")
                .loginProcessingUrl("/dologin")
                .defaultSuccessUrl("/user/dashboard", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            )
            .sessionManagement(session -> session
                .invalidSessionUrl("/")
                .maximumSessions(1)
                .expiredUrl("/")
                .maxSessionsPreventsLogin(false) // allow new login after expiry
            );

        return http.build();
    }


}



