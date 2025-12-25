package lk.ijse.applevisionmobileshop_ijse_76.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.applevisionmobileshop_ijse_76.dto.ItemDTO;
import lk.ijse.applevisionmobileshop_ijse_76.Util.CrudUtil;

public class ItemModel {
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        boolean result = CrudUtil.execute("INSERT INTO item (id, name, qty, UnitPrice) VALUES (?,?,?, ?)", itemDTO.getId(), itemDTO.getName(), itemDTO.getQty(), itemDTO.getUnitPrice());

        return result;
    }

    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        boolean result = CrudUtil.execute("UPDATE item SET name = ?, qty = ?, UnitPrice = ? WHERE id = ?", itemDTO.getName(), itemDTO.getQty(), itemDTO.getUnitPrice() ,itemDTO.getId());

        return result;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean result = CrudUtil.execute("DELETE FROM item WHERE id = ?", id);

        return result;
    }

    public ItemDTO search(String id) throws SQLException, ClassNotFoundException {
        ResultSet  result = CrudUtil.execute("SELECT * FROM item WHERE id = ?", id);

        if (result.next()) {
            int itemId = result.getInt("id");
            String itemName = result.getString("name");
            int itemQty = result.getInt("qty");
            double unitPrice = result.getDouble("UnitPrice");
            return new ItemDTO(itemId, itemName, itemQty, unitPrice);
        }
        return null;
    }

    public List<ItemDTO> getAllIds() throws SQLException, ClassNotFoundException {
        List<ItemDTO> itemList = new ArrayList();

        ResultSet  rs = CrudUtil.execute("SELECT * FROM item ORDER BY id DESC");

        while(rs.next()) {
            int itemId = rs.getInt("id");
            String itemName = rs.getString("name");
            int itemQty = rs.getInt("qty");
            double unitPrice = rs.getDouble("UnitPrice");

            ItemDTO itemDTO = new ItemDTO(itemId, itemName, itemQty, unitPrice);

            itemList.add(itemDTO);
        }

        return itemList;
    }

    public boolean decreaseItemQty(int itemId, int qty) throws SQLException, ClassNotFoundException {

        boolean isUpdated = CrudUtil.execute("UPDATE item SET qty=qty-? WHERE id=?",
                qty,
                itemId);

        return isUpdated;
    }
}