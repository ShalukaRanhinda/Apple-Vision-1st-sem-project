package lk.ijse.applevisionmobileshop_ijse_76.dto.TM;

public class OrderTm {
    private int itemId;
    private String itemName;
    private double unitPrice;
    private int orderQTY;
    private double itemTotal;

    public OrderTm() {
    }

    public OrderTm(int itemId, String itemName, double unitPrice, int orderQTY, double itemTotal) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.orderQTY = orderQTY;
        this.itemTotal = itemTotal;
    }

    public OrderTm( String itemName, double unitPrice, int orderQTY, double itemTotal) {

        this.itemName = itemName;
        this.unitPrice = unitPrice;
        this.orderQTY = orderQTY;
        this.itemTotal = itemTotal;
    }






    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getOrderQTY() {
        return orderQTY;
    }

    public void setOrderQTY(int orderQTY) {
        this.orderQTY = orderQTY;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    @Override
    public String toString() {
        return "OrderTM{" + "itemId=" + itemId + ", itemName=" + itemName + ", unitPrice=" + unitPrice + ", orderQTY=" + orderQTY + ", itemTotal=" + itemTotal + '}';
    }
}