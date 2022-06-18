package tczr.projects.azstore.admin.model;

import tczr.projects.azstore.admin.AdminRepository;
import tczr.projects.azstore.admin.AdminTypeRepository;

public class AdminMapper {

    static Admin admin;
    public static void buildAnExisting(Admin admin){
        AdminMapper.admin=admin;
    }

    public static void withType(AdminType adminType){
        admin.setAdminType(adminType);
    }

    public Admin finalAdmin(){ return admin;}



}
