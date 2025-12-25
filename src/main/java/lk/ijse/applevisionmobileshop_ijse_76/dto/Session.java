package lk.ijse.applevisionmobileshop_ijse_76.dto;


import lombok.Getter;
import lombok.Setter;

public class Session {
    @Getter
    @Setter
    private static UserDTO currentUser;

    public static void clear() {
        currentUser = null;
    }
}

