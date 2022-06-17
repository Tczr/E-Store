package tczr.projects.azstore.costumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tczr.projects.azstore.admin.AdminRepository;
import tczr.projects.azstore.shared.security.Encryption;
import tczr.projects.azstore.costumer.model.Costumer;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner command (
            CostumerRepository userRepository, AdminRepository adminRepository){
        return args -> {

            userRepository.save(
                    new Costumer(
                            "user@emailato.com",
                            "Fifora",
                            Encryption.encryptPassword("newPassword@Azstore"))
            );

            //Stopped Here
            /***
             * ToDO:
             *  -implement user and admin api
             *  -implement tables and other components
             *  -implements apis of other components
             *
             *
             */
            System.out.println(userRepository.findBy("Fifora"));
            System.out.println(userRepository.findBy("user@emailato.com"));

        };
    }

}
