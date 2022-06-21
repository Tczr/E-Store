package tczr.projects.azstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tczr.projects.azstore.admin.AdminRepository;
import tczr.projects.azstore.admin.model.Admin;
import tczr.projects.azstore.costumer.CostumerRepository;
import tczr.projects.azstore.product.ProductRepository;
import tczr.projects.azstore.product.model.Product;
import tczr.projects.azstore.shared.security.Encryption;
import tczr.projects.azstore.costumer.model.Costumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Config {

    CommandLineRunner command (AdminRepository pdRepo){
        return args -> {
        };
    }

}
