package lk.ijse.applevisionmobileshop_ijse_76.model;

import lk.ijse.applevisionmobileshop_ijse_76.Util.CrudUtil;
import lk.ijse.applevisionmobileshop_ijse_76.dto.UserDTO;

import java.sql.ResultSet;

public class UserModel {

    public UserDTO searchUser(String username, String password) {

        String sql = "SELECT * FROM `User` WHERE username = ? AND password = ?";

        try {
            ResultSet resultSet = CrudUtil.execute(sql, username, password);

            if (resultSet.next()) {
                return new UserDTO(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        resultSet.getString("job_role"),
                        resultSet.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean updateUserStatus(int userId, String status) {
        String sql = "UPDATE `User` SET status = ? WHERE user_id = ?";

        try {
            return CrudUtil.execute(sql, status, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
