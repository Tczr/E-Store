package tczr.projects.azstore.shared;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Account {
    private Integer Id;
    private String email;
    private String userName;
    private String phone;
    private String password;
    private String fullname;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime lastLogin;

    public Account(){}

    public Account(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Account(String email, String userName, String phone, String password) {
        this.email = email;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
    }

    public Account(String email, String userName, String phone, String password, String fullname) {
        this.email = email;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.fullname = fullname;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getFirstName(){
        String[] array = fullname.split(" ");

        return array[0];
    }

    public String getLastName(){
        String[] array = fullname.split(" ");

        return array[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(Id, account.Id)
                && Objects.equals(email, account.email)
                && Objects.equals(userName, account.userName)
                && Objects.equals(phone, account.phone)
                && Objects.equals(password, account.password)
                && Objects.equals(fullname, account.fullname)
                && status == account.status
                && Objects.equals(createdAt, account.createdAt)
                && Objects.equals(modifiedAt, account.modifiedAt)
                && Objects.equals(lastLogin, account.lastLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, email, userName, phone, password, fullname, status, createdAt, modifiedAt, lastLogin);
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
