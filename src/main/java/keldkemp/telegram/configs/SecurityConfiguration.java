package keldkemp.telegram.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    public static final String DEFAULT_LOGIN_URL = "/auth/login";
    public static final int DEFAULT_EXPIRED = 86400;

    @Autowired
    private UserDetailsService baseUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/", "/resources/**").permitAll()
                .antMatchers("/webhook/**").permitAll()
                .antMatchers("/api/registration/**").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
                    .loginPage(DEFAULT_LOGIN_URL + ".html")
                    .loginProcessingUrl(DEFAULT_LOGIN_URL)
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                .permitAll()
            .and()
                .rememberMe()
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(DEFAULT_EXPIRED)
            .and()
                .logout()
                    .logoutUrl("/api/logout")
                .deleteCookies("JSESSIONID", "SESSION")
                .permitAll();
    }

    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(baseUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
}
