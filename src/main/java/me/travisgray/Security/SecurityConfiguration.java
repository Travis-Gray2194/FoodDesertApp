package me.travisgray.Security;

import me.travisgray.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Created by ${TravisGray} on 11/13/2017.
 */

//We can determine access to different routes in this Class what roles user have within the application. Everything is role speific
//    Can create routes on the fly
//    What paths routes go to depending on security configuation Example: Successful Logout
//    In memory authenticiation want to keep on server currently running more difficult to get onto machine and keep autentician details. Very good for testing but you want encrypted databases for details



//Once you have basic security setup , adding a login form is a simple process.

//    Step 1: Create Login Form
//    Step 2: This Login page will recieive user input and pass it on  to Spring Security
//    Step 3: It will need fields that Spring can use for authentication ("username and password")
//    Step3c: It will also have to POST form details to the login route.


//    SecurityConfiguration Class
//    The Modififaction you make to this pages are

//    .formLogin().loginpage("/login").permitAll() This means that you are expecting a login form, which will display when you vist route: login everyone will be able to see this page
//    if they are not Authenticated! This is the page people will see if they havent logged in yet, before they are directed to the page that they can see after
//    logging in.




//@Configuration and@EnableWebSecurity This indicates to the compiler that the file is a configuration file and
//        Spring Security is enabled for the application.
//
//        the file class you create (SecurityConfiguration) extends the WebSecurityConflgurerAdapter, which has all of the
//        methods needed to include security in your application.

//    .authorizeRequest() This tells your apllication which request should be authorized. In this example, You are telling the application that any request that is autenticated should be permitted.
//    Right now, this mens that if a user enters a correct user/password combinatination, he/she will be directed to the defualt route. .and(
//    Adds additional authntication rules. Use this to combine rules.


//    .formLogin() This indicates that the application shpuld show a login form.Springs Boot's defualt login form will be shown, and this will include messages for inccorrect attempts.

//

//    Tells complier at runtime to determine how the application is setup
@Configuration
//Prevent access of allow access based on security
//Runs before complier these are paths that people are premitted to vist and not
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//WebSecruityConfigurerAdapter: http portocol to close off routes
    @Autowired
    private SSUserDetailsService userDetailsService;
//    These people have access from our database

    @Autowired
    private UserRepository userRepository;

//    Overriding Spring security and passing in Service to look for userrepository database
//    Get results of current user and what thier rights are

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }

    //Better readability to organize public routes
    private static final String[] PUBLIC_MATCHERS = {
            "/",
            "/h2-console/**",
            "/register",
            "/detail/**",
            "/update/**",
            "/delete/**",
            "/add",
            "/list",
            "/search",
            "/css/**",
            "/templates/**",
            "/js/**",
            "/static/**",
            "/addtopledge/**",
            "/addproducttoshoppingcart/**"
    };

//    HttpSecurity: tells us which routes people are allowed to acesses includes methods to restict or alllow access
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizerequest user must be authorized to access these requests
                .authorizeRequests()
//                .antmatchers: if you have a route you want to block off
//                .permitall: dont need access pages everyone one can acees this route example:register
                .antMatchers(PUBLIC_MATCHERS).permitAll()




//                .access("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
                .antMatchers("/admin").access("hasAuthority('ADMIN')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(
                        new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();
    }
//.httpBasic() This means that the user can avoid a login prompt by putting his/her login details in the request.
//    This can be used for testing, but should be removed before the application goes live.

//            configure() This overrides the default configure method, configures users who can access the application. By
//    default, Spring Boot will provide a new random password assigned to the user "user" when it starts up, if you
//do not include this method.
//
//    Once you include this method, you will be able to log in with the users configured here. At this point, the
//    configuration is for a single in-memory user. Multiple users can be configured here, as you wi\\ see when you
//    remove the comments in the additional code. This is also the method in which you can configure how users are granted access to the appliaction if their details are stored in a database.



    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    throws Exception{
        auth.inMemoryAuthentication().
        withUser("user").password("password").authorities("USER").
        and().
        withUser("dave").password("begreat").authorities("ADMIN");

//        Database Authentication must come after in memory authentication
        auth
                .userDetailsService(userDetailsServiceBean());

    }




}
