package tczr.projects.azstore.shared;

public enum Status {
    ACTIVE, DISABLE, DELETED, BLOCKED;

    public static Status toStatus(String status)
    {
        if (isDisable(status))
            return DISABLE;
        else if(isDeleted(status))
            return DELETED;
        else if( isBlocked(status))
            return BLOCKED;
        return ACTIVE;
    }

    public static boolean isDisable(String status){
        return status.equalsIgnoreCase(DISABLE.toString());
    }
    public static boolean isActive(String status){
        return status.equalsIgnoreCase(ACTIVE.toString());
    }
    public static boolean isDeleted(String status){
        return  status.equalsIgnoreCase(DELETED.toString());
    }
    public static boolean isBlocked(String status){
        return status.equalsIgnoreCase(BLOCKED.toString());
    }
}
