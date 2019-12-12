package blog.com.blog.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 使用Spring Security进行安全控制
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage("/admin/login.html")// 设置登录页面
                //登录成功后默认跳转到
                .defaultSuccessUrl("/admin/index.html")
                .permitAll()
                .and()
                .authorizeRequests()// 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers(//无需登录可以直接打开的页面
                        "/uploads/**",
                        "/admin/ueditor/config",
                        "/admin/ueditor/**",
                        "/admin/fonts/**","/admin/js/**","/admin/common/js/**",
                        "/admin/css/**","/admin/images/**","/error.html","/error","/search.html",
                        "/css/**","/fonts/**", "/html","/images/**", "/js/**",
                        "/index.html", "/basic.html", "/learn.html","/write.html",
                        "/article.html","/writing.html",
                        "/readers.html","/links.html","/tags.html"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll();
        //解决非thymeleaf的form表单提交被拦截问题
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        // 解决中文乱码问题
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("utf-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
    }
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        UserDetails user = userBuilder
                .username("user")
                .password("123456")
                .roles("USER")
                .build();
        UserDetails admin = userBuilder
                .username("admin")
                .password("123456")
                .roles("USER","ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}