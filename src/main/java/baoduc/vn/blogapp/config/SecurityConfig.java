package baoduc.vn.blogapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import baoduc.vn.blogapp.security.CustomUserDetailsService;
import baoduc.vn.blogapp.security.JwtAuthenticationEntryPoint;
import baoduc.vn.blogapp.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    //    @Autowired
    //    private UserDetailsService userDetailsService;
    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // Này để cấu hình xem security sẽ làm những gì
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    // Quaản lí phân quyền
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Vô hiệu hóa CSRF protection.
        // Yêu cầu tất cả các yêu cầu HTTP phải được xác thực.
        // Sử dụng Basic Authentication với cấu hình mặc định.
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authurize) -> authurize
                        .requestMatchers(HttpMethod.GET, "/api/**")
                        .permitAll()
                        .requestMatchers("/api/auth/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/**")
                        .hasRole("USER")
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
