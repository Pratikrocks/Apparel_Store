package murraco.security;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

	http
      .requiresChannel()
      .anyRequest()
      .requiresSecure();
	  
	  
    // Disable CSRF (cross site request forgery)
    http.csrf().disable();

    http.cors();
    // No session will be created or used by spring security
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Entry points
    http.authorizeRequests()//
    	.antMatchers("/services/products/**").permitAll()
        .antMatchers("/users/signin").permitAll()//
        .antMatchers("/users/signup").permitAll()//
        .antMatchers("/services/files/**").permitAll()
        .antMatchers("/services/files").permitAll()
        .antMatchers("/h2-console/**/**").permitAll()
        .antMatchers("/users/addproduct").permitAll()
        .antMatchers("/services/view/product/**").permitAll()
        
        .antMatchers("/services/mycart").permitAll()
        
        // Disallow everything else..
        .anyRequest().authenticated();

    // If a user try to access a resource without having enough permissions
    http.exceptionHandling().accessDeniedPage("/login");

//    // Apply JWT
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    // Optional, if you want to test the API from a browser
    // http.httpBasic();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // Allow swagger to be accessed without authentication
    web.ignoring().antMatchers("/v2/api-docs")//
        .antMatchers("/swagger-resources/**")//
        .antMatchers("/swagger-ui.html")//
        .antMatchers("/configuration/**")//
        .antMatchers("/webjars/**")//
        .antMatchers("/public")
        
        // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
        .and()
        .ignoring()
        .antMatchers("/h2-console/**/**");;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
  
  
//  	@Bean
//	public ServletWebServerFactory servletContainer() {
//	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//	        @Override
//	        protected void postProcessContext(Context context) {
//	            SecurityConstraint securityConstraint = new SecurityConstraint();
//	            securityConstraint.setUserConstraint("CONFIDENTIAL");
//	            SecurityCollection collection = new SecurityCollection();
//	            collection.addPattern("/*");
//	            securityConstraint.addCollection(collection);
//	            context.addConstraint(securityConstraint);
//	        }
//	    };
//	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
//	    return tomcat;
//	}
//
//	private Connector redirectConnector() {
//	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	    connector.setScheme("http");
//	    connector.setPort(8080);
//	    connector.setSecure(false);
//	    connector.setRedirectPort(8443);
//	    return connector;
//	}

}
