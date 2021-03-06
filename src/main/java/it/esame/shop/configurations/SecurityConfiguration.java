package it.esame.shop.configurations;


import it.esame.shop.support.authentication.JwtAuthenticationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //.antMatchers("/check/simple").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                //.antMatchers(HttpMethod.OPTIONS,"/prodotti/**").permitAll()
                .antMatchers(HttpMethod.GET,"/acquisto/**").permitAll()
                .antMatchers(HttpMethod.POST,"/utenti/**").permitAll()
                .antMatchers(HttpMethod.GET,"/utenti/**").permitAll()
                .antMatchers(HttpMethod.POST,"/r").permitAll()
                .antMatchers(HttpMethod.GET,"/prodotti/**").permitAll()
                .antMatchers(HttpMethod.POST,"/prodotti/**").permitAll()
                .anyRequest().authenticated().and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(new JwtAuthenticationConverter());
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }



}






