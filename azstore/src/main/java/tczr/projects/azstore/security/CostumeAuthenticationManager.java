package tczr.projects.azstore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import tczr.projects.azstore.shared.Account;
import tczr.projects.azstore.shared.Repo;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class CostumeAuthenticationManager implements AuthenticationManager {

    @Autowired
    @Qualifier("costumerRepository")
    private Repo costumerRepo;

    @Autowired
    @Qualifier("adminRepository")
    private Repo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CostumeAuthenticationManager(){}

    public CostumeAuthenticationManager(Repo costumerRepo, Repo adminRepo) {
        this.costumerRepo = costumerRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<Account> userAccount = getAccount(authentication.getPrincipal()); //
        System.out.println("get it-> "+ userAccount);
        if(!userAccount.isEmpty()) {
            if(passwordEncoder.matches(authentication.getCredentials().toString(), userAccount.get().getPassword()))
               {
                   return new UsernamePasswordAuthenticationToken(
                           authentication.getPrincipal(),
                           authentication.getCredentials(),
                           userAccount.get().getAuthorities());
               }
            else {throw new BadCredentialsException("wrong password");}
        }
        else { throw new BadCredentialsException("wrong user");}
    }


    private Optional<Account> getAccount(Object email) {
        Map<String, Optional<Account>> account = getExistingAccount((String)email);
        System.out.println("get Account "+account);
        if ( account.get("COSTUMER").isPresent() ) return account.get("COSTUMER") ;

        else if (account.get("ADMIN").isPresent()) return  account.get("ADMIN");


        return Optional.empty();
    }


    private Map<String, Optional<Account> > getExistingAccount(String email){

        Map<String,Optional<Account>> account = new HashMap<>();
        AtomicReference<Optional<Account>> admin = new AtomicReference<>();
        AtomicReference<Optional<Account>> costumer = new AtomicReference<>();
        Thread costumerThread = new Thread(
                ()->{
                    costumer.set(costumerRepo.findBy(email));
                }
        );

        Thread adminThread = new Thread(
                ()-> {
                    admin.set(adminRepo.findBy(email));
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
