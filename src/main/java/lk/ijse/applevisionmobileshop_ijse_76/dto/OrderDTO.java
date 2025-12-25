package lk.ijse.applevisionmobileshop_ijse_76.dto;

public class OrderDTO {
    private String orderDetailsId;
    private String orderId;
    private String itemCode;
    private int quantity;
    private double unitPrice;

    public OrderDTO() {
    }

    public OrderDTO(String orderDetailsId, String orderId, String itemCode, int quantity, double unitPrice) {
        this.orderDetailsId = orderDetailsId;
        this.orderId = orderId;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(String orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

}
