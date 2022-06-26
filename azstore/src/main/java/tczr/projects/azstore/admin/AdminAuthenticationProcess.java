package tczr.projects.azstore.admin;

import tczr.projects.azstore.shared.Account;
import tczr.projects.azstore.security.Authentication;

public interface AdminAuthentication extends Authentication{
    boolean isAdmin(Account account);
}
