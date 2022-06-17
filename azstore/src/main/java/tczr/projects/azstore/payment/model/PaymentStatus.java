package tczr.projects.azstore.payment.model;

public enum PaymentStatus {

    ACTIVE, EXPIRED, DISABLED;

    public static PaymentStatus toPaymentStatus(String status){
        if(isExpired(status)) return EXPIRED;
        else if( isDisabled(status)) return DISABLED;

        return ACTIVE;
    }

   public static boolean isActive(String status){
        return status.equalsIgnoreCase(ACTIVE.toString());
    }

    public static boolean isExpired(String status){
        return status.equalsIgnoreCase(EXPIRED.toString());
    }

    public static boolean isDisabled(String status){
        return status.equalsIgnoreCase(DISABLED.toString());
    }
}
