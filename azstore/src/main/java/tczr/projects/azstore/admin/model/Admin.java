package tczr.projects.azstore.admin.model;

import tczr.projects.azstore.shared.Account;

public class Admin extends Account {

    AdminType adminType;


    public Admin(){
        super();
    }
    public Admin(String email, String userName, String password) {
        super(email, userName, password);
    }


    public AdminType getAdminType() {
        return adminType;
    }

    public void setAdminType(AdminType adminType) {
        this.adminType = adminType;
    }

    @Override
    public String toString() {
        return  super.toString() +"Admin{" +
                ", adminType=" + adminType +
                '}';
    }
}
