package tczr.projects.azstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import tczr.projects.azstore.admin.AdminService;
import tczr.projects.azstore.costumer.CostumerService;
import tczr.projects.azstore.shared.Account;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class CostumerAuthorizationFilter extends OncePerRequestFilter {
    @Value("${auth.tokenHeader}")
    private String TOKEN_HEADER;
    @Autowired
    private CostumerService costumerService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private  TokenUtility tokenUtility ;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(TOKEN_HEADER);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if(securityContext.getAuthentication()==null && header!=null)
        {
           final String token = header.substring("Bearer ".length());
           final String email= tokenUtility.fetch(token).getSubject();
            if(email!=null)
            {
                UserDetails user = loadBy(email);
                if(tokenUtility.isTokenValid(token, user))
                {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                     new BadCredentialsException("token is not valid");
                }
            }else {
                 new IllegalArgumentException("wrong user");
            }
        }else{
             new IllegalAccessError("header is empty, session is expired");
        }

            filterChain.doFilter(request, response);

    }




    private UserDetails loadBy(Object email) {
        Map<String, UserDetails> account = getExistingAccount((String)email);
        System.out.println("get Account "+account);
        if ( account.get("COSTUMER")!=null) return account.get("COSTUMER") ;

        else if (account.get("ADMIN")!=null) return account.get("ADMIN");


        return null;
    }


    private Map<String, UserDetails > getExistingAccount(String email){

        Map<String,UserDetails> account = new HashMap<>();
        AtomicReference<UserDetails> admin = new AtomicReference<>();
        AtomicReference<UserDetails> costumer = new AtomicReference<>();
        Thread costumerThread = new Thread(
                ()->{
                    try {
                        costumer.set(costumerService.loadUserByUsername(email));
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
        );

        Thread adminThread = new Thread(
                ()-> {
                    admin.set(adminService.loadUserByUsername(email));
                }
        );


        costumerThread.run();
        adminThread.run();


        account.put("COSTUMER", costumer.get());
        account.put("ADMIN",admin.get());

        System.out.println(account.values());

        return account;
    }
}
