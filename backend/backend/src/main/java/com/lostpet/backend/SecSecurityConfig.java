//package com.lostpet.backend;
//
////import com.lostpet.backend.service.UserService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
////import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
////import org.springframework.security.crypto.password.PasswordEncoder;
////
////@Configuration
////@EnableWebSecurity
////public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
////
////
////    @Autowired
////    private final UserService service;
////
////    public SecSecurityConfig(UserService service) {
////        this.service = service;
////    }
////
////
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeRequests()
////
////                .antMatchers("/login","/**","/index","/article/**","/index","/user/**","/category/**","/user/save")
////                .permitAll()
////
//////                .antMatchers("/article/save")
//////                .hasRole(UserRole.USER.toString())
//////                .antMatchers("/category/save","/user/save")
//////                .hasRole(UserRole.ADMIN.toString())
////                .anyRequest()
////
////                .authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
////    }
////
////
////    @Bean
////    public PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public DaoAuthenticationProvider authProvider() {
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(service);
////        authProvider.setPasswordEncoder(passwordEncoder());
////        return authProvider;
////    }
////
//////    @Bean
//////    @Override
//////    public UserDetailsService userDetailsService() {
//////        UserDetails user =
//////                User.withDefaultPasswordEncoder()
//////                        .username("user")
//////                        .password("password")
//////                        .roles("USER")
//////                        .build();
//////
//////        return new InMemoryUserDetailsManager(user);
//////    }
////}
//
//import com.google.common.collect.ImmutableList;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@EnableWebSecurity
//@Configuration
//public class SecSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.cors();
//    }
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(ImmutableList.of("*"));
//        configuration.setAllowedMethods(ImmutableList.of("HEAD",
//                "GET", "POST", "PUT", "DELETE", "PATCH"));
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}