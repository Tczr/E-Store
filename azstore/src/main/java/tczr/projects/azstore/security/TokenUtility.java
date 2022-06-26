package tczr.projects.azstore.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import tczr.projects.azstore.admin.AdminService;
import tczr.projects.azstore.costumer.CostumerRepository;
import tczr.projects.azstore.shared.Account;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class TokenUtility {
    private final String[] CLAIMS = {"sub", "created"};
    @Value("${auth.timeExpiration}")
    private final long TIME_EXPIRATION = 172800L; //two days im second
    @Value("${auth.tokenKey}")
    private String TOKEN_KEY;
    private String encodedKey ;

    public TokenUtility() {}

    @PostConstruct
    private void setEncodedKey(){
//        System.out.println("encoded been initialize::");
        encodedKey = TextCodec.BASE64.encode(TOKEN_KEY);
    }
    /* public final String generateKey(){
            //to generate random secret key
            // problem how can we retrieve this random key for every request
            char[] pass= new char[70];
            final String passPattern = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@&_$#";
            Random random = new Random();
            final int size = passPattern.length()-1;
            for(int i=0; i<70; i++)
            {
                int rIndex = random.nextInt(size);
                pass[i] = passPattern.charAt(rIndex);
            }

            return String.valueOf(pass);
        }*/
    public String generateToken(UserDetails user){

        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS[0],  user.getUsername());
        claims.put(CLAIMS[1],new Date());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, encodedKey)
                .compact();
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + TIME_EXPIRATION * 1000);
    }

   /* public String getPermission(String token) {
        return fetch(token).getSubject();
    }*/

    public Claims fetch(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(encodedKey)
                    .parseClaimsJws(token)
                    .getBody();

        }catch (Exception ex){
            ex.printStackTrace();
            claims=null;
        }

        return claims;
    }

    public boolean isTokenValid(String token, UserDetails account)
    {
        String username= fetch(token).getSubject();
        if(username.equals(account.getUsername()))
          if(!isTokenExpired(token))
              return  true;

        return false;

        // short version :-> return username.equals( account.getUsername) && !isTokenNotExpired(token)
    }

    public boolean isTokenExpired(String token) {
        Date expirationTokenDate = fetch(token).getExpiration();
       Boolean result = expirationTokenDate.before(new Date());
       return result;
    }


    /*private int[] setPasswordPattern(){
        final int numbers[]= new int[10], capitalCharacter[] = new int[26], smallCharacter[] = new int[26],
                specialCharacter[]={35,36,38,64,95};
        for(int c = 0; c>25; c++)
        {
            int capitalAlpha = 65;
            int smallAlpha = 97;
            int numberCode = 48;
            if((numberCode+c)>=48 && (numberCode+c)<=57)
            {
                numbers[c]=numberCode+c;
            }
            capitalCharacter[c]=capitalAlpha+c;
            smallCharacter[c]=smallAlpha+c;
        }
        return numbers;
    }*/

}
