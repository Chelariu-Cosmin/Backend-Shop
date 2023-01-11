package backend.springboot.online.shop.config;

import backend.springboot.online.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder ();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider ();
        auth.setUserDetailsService (userService);
        auth.setPasswordEncoder (passwordEncoder ());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider (authenticationProvider ());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests ()
            .antMatchers (HttpMethod.GET,"/","/success", "/cancel", "/js/**", "/css/**", "/img/**").permitAll ()
            .antMatchers ("/registration**", "/index**","/home","/register_success").permitAll ()
                .antMatchers (HttpMethod.GET, "/api/v1/article/**", "/api/v1/users/**", "/api/v1/orders/*").permitAll ()
                    .antMatchers (HttpMethod.POST, "/api/v1/article/**", "/api/v1/users/**", "/api/v1/orders/*").permitAll ()
                         .antMatchers (HttpMethod.DELETE, "/api/v1/article/**", "/api/v1/users/**", "/api/v1/orders/*").permitAll ()
            .anyRequest ()
            .authenticated ()
            .and ()
            .formLogin ()
            .loginPage ("/login")
            .permitAll ()
            .and ()
            .logout ()
            .invalidateHttpSession (true)
            .clearAuthentication (true)
            .logoutRequestMatcher (new AntPathRequestMatcher ("/logout"))
            .logoutSuccessUrl ("/login?logout")
            .permitAll ();
     //   http.cors().disable().csrf().disable();
    }
}
