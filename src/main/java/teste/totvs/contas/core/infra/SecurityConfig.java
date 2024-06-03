package teste.totvs.contas.core.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import teste.totvs.contas.core.auth.app.AutenticarAppService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAppService jwtAppServive;
    private AutenticarAppService autenticarAppService;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(this.autenticarAppService, this.jwtAppServive);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        authz ->
                                authz
                                        .requestMatchers("/auth").permitAll()
                                        .requestMatchers("/ws-api/**").authenticated()
                                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(this.corsConfigurationSource()))
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritConfigurer -> httpSecuritConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(this.jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(this.autenticarAppService);

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

    @Autowired
    public void setJwtAppServive(JwtAppService jwtAppServive) {
        this.jwtAppServive = jwtAppServive;
    }

    @Autowired
    public void setAutenticarAppService(AutenticarAppService autenticarAppService) {
        this.autenticarAppService = autenticarAppService;
    }
}
