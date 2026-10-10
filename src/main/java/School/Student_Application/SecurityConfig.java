package School.Student_Application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // Grants universal open browser parsing pipeline access to lookups inside public static assets
                .requestMatchers("/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login")          // <-- Pointing system explicitly to our layout router mapped path
                .defaultSuccessUrl("/", true) // Forward to the index dashboard on authentication verification success
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutSuccessUrl("/login?logout") // Forward to login screen showcasing logged out banner confirmation
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        @SuppressWarnings("deprecation")
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password123")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}
