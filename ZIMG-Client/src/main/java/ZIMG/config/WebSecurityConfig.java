package ZIMG.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.jws.WebService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends
        WebSecurityConfigurerAdapter{
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) {

      try {
          auth
                  .inMemoryAuthentication()
                  .withUser("user")  // #1
                  .password("password")
                  .roles("USER")
                  .and()
                  .withUser("admin") // #2
                  .password("password")
                  .roles("ADMIN", "USER");
      }catch (Exception e){
          throw new RuntimeException(e);
      }
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
         .antMatchers("/resources/**"); // #3
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .antMatchers("/signup", "/about").permitAll() // #4
        .antMatchers("/admin/**").hasRole("ADMIN") // #6
        .anyRequest().authenticated() // 7
        .and()
    .formLogin()  // #8
        .loginPage("/login") // #9
        .permitAll(); // #5
  }
}