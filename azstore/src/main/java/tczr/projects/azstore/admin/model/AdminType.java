package tczr.projects.azstore.admin.model;

public enum AdminType {
    CONTENT_ADMINISTER("adding, removing, updating and deleting product"),
    COSTUMER_ADMINISTER("serve, blocked and see activity of costumer "),
    ADMIN_OF_ADMINS("adding, removing, updating and deleting admins"),
    ADMIN("All permissions");
    private String permission;

    AdminType(String permission){
        this.permission=permission;
    }

    public String getPermission() {
        return permission;
    }

    public static boolean isItType(String type){

           if (!type.equalsIgnoreCase(COSTUMER_ADMINISTER.toString()) &&
                type.equalsIgnoreCase(ADMIN_OF_ADMINS.toString()) &&
                type.equalsIgnoreCase(ADMIN.toString()) )
               return false;

           return true;
    }

    public static AdminType toAdminType(String type){
        if (type.equalsIgnoreCase(COSTUMER_ADMINISTER.toString())) return COSTUMER_ADMINISTER;
        else if (type.equalsIgnoreCase(ADMIN_OF_ADMINS.toString())) return ADMIN_OF_ADMINS;
        else if(type.equalsIgnoreCase(ADMIN.toString())) return ADMIN;
        else
            return CONTENT_ADMINISTER;
    };


}
