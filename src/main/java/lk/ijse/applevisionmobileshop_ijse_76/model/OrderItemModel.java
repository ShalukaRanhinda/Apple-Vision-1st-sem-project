package lk.ijse.applevisionmobileshop_ijse_76.model;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.applevisionmobileshop_ijse_76.dto.OrderItemDTO;
import lk.ijse.applevisionmobileshop_ijse_76.Util.CrudUtil;

public class OrderItemModel {
    public void saveOrderItems(int orderId, List<OrderItemDTO> orderItemList) throws SQLException, ClassNotFoundException {
        for(OrderItemDTO orderItemDTO : orderItemList) {
            boolean isSaved = CrudUtil.execute(
                    "INSERT INTO order_items (order_id, item_id, qty, price) VALUE (?, ?, ?, ?)",
                    orderId,
                    orderItemDTO.getItemId(),
                    orderItemDTO.getQty(),
                    orderItemDTO.getPrice()
            );
            if(isSaved) {

            } else {
                System.out.println("Something went a wrong");
            }
        }
    }
}