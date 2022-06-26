package tczr.projects.azstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final String[] PUBLIC_END_POINTS ={
      "/api/*/auth/user/**",
      "/api/*/product/getAll",
      "/api/*/product/get/*"
    };
    private final String[] ADMIN_END_POINT={
            "/api/*/auth/admin/**",
            "/api/*/admin/**",
            "/api/*/**",
    };

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Primary
    @Bean
    protected CostumeAuthenticationManager costumeAuthenticationManager() throws Exception {
        return new CostumeAuthenticationManager();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    protected CostumerAuthorizationFilter costumerAuthorizationFilter(){
        return new CostumerAuthorizationFilter();
    }

    @Autowired
    private DataSource dataSource;

   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("" +
                        "SELECT " +
                        "user_email," +
                        " user_password," +
                        "user_id" +
                        " from user where user_email=?")
                .authoritiesByUsernameQuery("SELECT " +
                        "user_accountName," +
                        " user_type " +
                        "FROM user WHERE user_email=?");
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_END_POINTS).permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(costumerAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
