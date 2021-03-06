package tczr.projects.azstore.costumer;

import org.springframework.boot.CommandLineRunner;
import tczr.projects.azstore.admin.AdminRepository;
import tczr.projects.azstore.security.Encryption;
import tczr.projects.azstore.costumer.model.Costumer;


public class UserConfig {

    CommandLineRunner command (
            CostumerRepository userRepository, AdminRepository adminRepository){
        return args -> {

            userRepository.insert(
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
