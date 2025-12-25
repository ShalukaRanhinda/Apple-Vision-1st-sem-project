package lk.ijse.applevisionmobileshop_ijse_76.model;


import lk.ijse.applevisionmobileshop_ijse_76.Util.CrudUtil;
import lk.ijse.applevisionmobileshop_ijse_76.dto.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public boolean saveSupplier(SupplierDTO supplierDTO)throws SQLException ,ClassNotFoundException{
        String sql = "INSERT INTO Supplier (supplier_id,name,nic,email,contact,address) VALUES (?,?,?,?,?,?)";
        return CrudUtil.execute(sql,supplierDTO.getSupplierId(),supplierDTO.getName(),supplierDTO.getNic(),supplierDTO.getEmail(),supplierDTO.getContact(),supplierDTO.getAddress());
    }
    public boolean updateSupplier(SupplierDTO supplierDTO)throws SQLException ,ClassNotFoundException{
        String sql = "UPDATE Supplier SET name =?, nic =?, email =?, contact =?, address =? WHERE supplier_id =?";
        return CrudUtil.execute(sql,supplierDTO.getName(),supplierDTO.getNic(),supplierDTO.getEmail(),supplierDTO.getContact(),supplierDTO.getAddress(),supplierDTO.getSupplierId());

    }
    public boolean deleteSupplier(String supplier_id)throws SQLException ,ClassNotFoundException{
        String sql = "DELETE FROM Supplier WHERE supplier_id =?";
        return CrudUtil.execute(sql,supplier_id);
    }

    public String getLastSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT MAX(supplier_id) FROM Supplier");
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }

    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.execute("SELECT * FROM Supplier");
        ArrayList<SupplierDTO> list = new ArrayList<>();
        while (rs.next()) {
            list.add(convertToDto(rs));
        }
        return list;
    }
    private SupplierDTO convertToDto(ResultSet rs) throws SQLException {
        return new SupplierDTO(
                rs.getString("supplier_id"),
                rs.getString("name"),
                rs.getString("nic"),
                rs.getString("email"),
                rs.getString("contact"),
                rs.getString("address")
        );
    }
}
