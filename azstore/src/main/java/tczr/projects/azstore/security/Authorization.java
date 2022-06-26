package tczr.projects.azstore.security;

public interface Authorization {
    Object getObject(String token);
    boolean isAllowed(String token);
}
