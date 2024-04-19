package toy_project.newdy.rest_api.auth.config;

import toy_project.newdy.rest_api.auth.lib.JwtTokenProvider;
import toy_project.newdy.rest_api.auth.lib.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${spring.jwt.header}")
    private String SPRING_JWT_HEADER;
    private JwtTokenProvider jwtTokenProvider;
    private SecurityFilter securityFilter;

    @Autowired
    public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Autowired
    public void setSecurityFilter(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws
            Exception {
        http
                .csrf((csrfConfig) -> csrfConfig.disable())
                .cors( corsConfigurer -> {
                    Customizer.withDefaults();
                })
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( (authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.OPTIONS)
                                .permitAll()
                                .requestMatchers("/auth/**")
                                .permitAll()
                                .anyRequest().authenticated()
                ).addFilterBefore(securityFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Swagger 접근 관련
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().requestMatchers("/h2-console/**", "/swagger-ui/**" , "/v3/api-docs/**", "/favicon.ico");
        };
    }
}
