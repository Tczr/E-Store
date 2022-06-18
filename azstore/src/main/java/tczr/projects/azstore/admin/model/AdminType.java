package tczr.projects.azstore.admin.model;

import java.util.Objects;

public class AdminType {
  /*  CONTENT_ADMINISTER("adding, removing, updating and deleting product"),
    COSTUMER_ADMINISTER("serve, blocked and see activity of costumer "),
    ADMIN_OF_ADMINS("adding, removing, updating and deleting admins"),
    ADMIN("All permissions");*/
    private Integer type_id;
    private String type,  permission;
    public AdminType(){}
    public AdminType(String type, String permission){
        this.type=type;
        this.permission=permission;
    }

    public Integer getId() {
        return type_id;
    }

    public void setId(Integer type_id) {
        this.type_id =type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminType adminType = (AdminType) o;
        return Objects.equals(type_id, adminType.type_id) && Objects.equals(type, adminType.type) && Objects.equals(permission, adminType.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type_id, type, permission);
    }

    @Override
    public String toString() {
        return "AdminType{" +
                "id=" + type_id +
                ", type='" + type + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
