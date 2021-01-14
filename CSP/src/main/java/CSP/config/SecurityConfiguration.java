package CSP.config;

import CSP.repositories.UserRepository;
import CSP.services.CustomUserDetailsService;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .httpBasic()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/resources/**","/users/registration").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/")
//                .failureUrl("/login?error")
//                .loginPage("/login")
//                .permitAll();
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence charSequence) {
                        String hashedPassword = Hashing.sha256()
                                .hashString(charSequence, StandardCharsets.UTF_8)
                                .toString();
                        return hashedPassword;
                    }

                    @Override
                    public boolean matches(CharSequence charSequence, String s) {
//                        System.out.println(Hashing.sha256()
//                                .hashString(charSequence, StandardCharsets.UTF_8)
//                                .toString());
//                        System.out.println("s = " + s);
                        return Hashing.sha256()
                                .hashString(charSequence, StandardCharsets.UTF_8)
                                .toString().equals(s);
                    }
                });
    }

}
