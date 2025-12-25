package lk.ijse.applevisionmobileshop_ijse_76.model;


import lk.ijse.applevisionmobileshop_ijse_76.Util.CrudUtil;
import lk.ijse.applevisionmobileshop_ijse_76.dto.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public boolean saveCustomer(CustomerDTO customerDTO)throws SQLException ,ClassNotFoundException{
        String sql = "INSERT INTO Customer (customer_id,name,nic,email,contact,address) VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,customerDTO.getCustomerId(),customerDTO.getName(),customerDTO.getNic(),customerDTO.getEmail(),customerDTO.getContact(),customerDTO.getAddress());
    }
    public boolean updateCustomer(CustomerDTO customerDTO)throws SQLException ,ClassNotFoundException{
        String sql = "UPDATE Customer SET name =?, nic =?, email =?, contact =?, address =? WHERE customer_id =?";
        return CrudUtil.execute(sql,customerDTO.getName(),customerDTO.getNic(),customerDTO.getEmail(),customerDTO.getContact(),customerDTO.getAddress(),customerDTO.getCustomerId());

    }
    public boolean deleteCustomer(String customerId)throws SQLException ,ClassNotFoundException{
        String sql = "DELETE FROM Customer WHERE customer_id =?";
        return CrudUtil.execute(sql,customerId);
    }

    public String getLastCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT MAX(customer_id) FROM Customer");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(convertToDto(rs));
        }
        return list;
    }
    private CustomerDTO convertToDto(ResultSet rs) throws SQLException {
        return new CustomerDTO(
                rs.getString("customer_id"),
                rs.getString("name"),
                rs.getString("nic"),
                rs.getString("email"),
                rs.getString("contact"),
                rs.getString("address")
        );
    }
}
