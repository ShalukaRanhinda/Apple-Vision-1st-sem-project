package lk.ijse.applevisionmobileshop_ijse_76.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import lk.ijse.applevisionmobileshop_ijse_76.Util.CrudUtil;

public class OrderModel {

    private OrderItemModel orderItemModel= new  OrderItemModel();
    public OrderItemModel getOrderItemModel() {
        return orderItemModel;
    }

    public void placeOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        boolean isSaved = CrudUtil.execute  (
                "INSERT INTO orders (date, customer_id) VALUES (?, ?)",
                orderDTO.getOrderDate(),
                orderDTO.getCustomerId()
        );

        if(isSaved) {
            ResultSet rs = CrudUtil.execute("SELECT id FROM orders ORDER BY id DESC LIMIT 1");
            if(rs.next()) {
                int latestOrderId = rs.getInt("id");
                orderItemModel.saveOrderItems(latestOrderId, orderDTO.getOrderItemDTO());
            } else {
                System.out.println("Something went a wrong");
            }
        } else {
            System.out.println("Something went a wrong");
        }
    }
}