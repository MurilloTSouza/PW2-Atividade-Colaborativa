package br.edu.ifpb.atividadecolaborativa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Qualquer URL aqui nao usara autenticacao
    // independente de ser GET, POST, DELETE ou PUT
    private static final String[] NO_AUTH_PATHS = {
    };

    private static final String ADMIN_ROLE = "ADMIN";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("adm")
                .password("{noop}1234")
                .roles(ADMIN_ROLE);
    }

    // Qualquer request PUT e DELETE somente para ADMIN
    // Qualquer request POST somente para autenticado
    // Qualquer request GET ou que esteja no NO_AUTH_PATHS nao precisa de autenticacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(NO_AUTH_PATHS).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.DELETE).hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PUT).hasRole(ADMIN_ROLE)
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().formLogin().permitAll()
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
