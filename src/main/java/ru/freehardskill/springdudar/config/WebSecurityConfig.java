package ru.freehardskill.springdudar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/styles/**", "/js/**","/img/**","/images/**","/", "/signup", "/login", "/error").permitAll()
                    .anyRequest().authenticated())
            .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/process_login")
                    .defaultSuccessUrl("/", true)
                    .failureUrl("/login?error=true")
                    .permitAll())
            .logout(logout -> logout
                    .logoutUrl("/logout")  // URL выхода (Spring Security обрабатывает автоматически)
                    .logoutSuccessUrl("/") // После выхода отправляем на главную
                    .invalidateHttpSession(true) // Удаляем сессию
                    .deleteCookies("JSESSIONID") // Удаляем куки авторизации
                    .permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}
