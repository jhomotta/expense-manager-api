package in.bushansirgur.expensetrackerapi.securingweb;


import in.bushansirgur.expensetrackerapi.securingweb.impl.CustomUserDetailsService;
import in.bushansirgur.expensetrackerapi.securingweb.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/register", "/healthcheck" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
                http.httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        //        .withUser("bushan").password("12345").authorities("admin")
        //        .and()
        //        .withUser("bharath").password("12345").authorities("user")
        //        .and()
        //        .passwordEncoder(NoOpPasswordEncoder.getInstance());

        //InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        //UserDetails user1 = User.withUsername("bushan").password("12345").authorities("admin").build();
        //UserDetails user2 = User.withUsername("bharath").password("12345").authorities("user").build();

        //inMemoryUserDetailsManager.createUser(user1);
        //inMemoryUserDetailsManager.createUser(user2);
        //auth.userDetailsService(inMemoryUserDetailsManager);
        auth.userDetailsService(userDetailsService);

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
