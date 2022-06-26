package tczr.projects.azstore.security;
import org.springframework.security.core.Authentication;
public interface AuthenticationProcess {
        Authentication login(String email, String password ) throws Exception;

}
