package tczr.projects.azstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tczr.projects.azstore.admin.AdminService;
import tczr.projects.azstore.costumer.CostumerService;
import tczr.projects.azstore.costumer.model.Costumer;
import tczr.projects.azstore.security.dto.JwtResponse;
import tczr.projects.azstore.security.dto.Register;
import tczr.projects.azstore.security.dto.SignIn;
import tczr.projects.azstore.shared.Account;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class SecurityController {

    @Autowired
    private final CostumerService costumerService;
    @Autowired
    private final AdminService adminService;
    @Autowired
    private final TokenUtility tokenUtility;
    @Autowired
    private final CostumeAuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityController(CostumerService costumerService,
                              AdminService adminService,
                              TokenUtility tokenUtility,
                              CostumeAuthenticationManager authenticationManager) {
        this.costumerService = costumerService;
        this.adminService = adminService;
        this.tokenUtility = tokenUtility;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(path = "/user/login")
    public Object login(@RequestBody SignIn signIn)
    {
        String Token="";
        try{
            Authentication authentication = AccountAuthentication(signIn.getEmail(), signIn.getPass());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Token = getTokenFor(authentication);
        }catch (Exception exc){
            Token="not valid "+exc.getMessage();
            exc.printStackTrace();
        }
        return new JwtResponse(Token);
    }


    private Authentication AccountAuthentication(String email, String pass) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, pass)
        );
    }

    private String getTokenFor(Authentication authentication){

        Boolean res = authentication.getAuthorities().toString().contains("ADMIN");
        System.out.println("is it admin: "+res);
        UserDetails user ;
        if(res) user = adminService.loadUserByUsername(authentication.getName());

        else user =  costumerService.loadUserByUsername(authentication.getName());

        return tokenUtility.generateToken(user);
    }

    @PostMapping(path = "/user/register")
    public Object Register(@RequestBody Register register){
        System.out.println("::register");
        String Token="";
        try {
            Optional<Costumer> account =  costumerService.insert(register);
            UserDetails user = costumerService.loadUserByUsername(account.get().getEmail());
           if(account.isPresent()){
              Token= tokenUtility.generateToken(user);
              //set authentication token
               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account.get(), null, null);
               SecurityContextHolder.getContext().setAuthentication(authentication);
           }
        }catch (Exception ex){
            Token="error creation, maybe there are someone using the same email or:"+ex.getMessage();
            ex.printStackTrace();
        }
        return new JwtResponse(Token);
    }
}
