package lk.ijse.applevisionmobileshop_ijse_76.dto;

public class StockDTO {

    private String stockId;
    private String itemName;
    private int quantity;
    private double price;
    public StockDTO() {
    }
    public StockDTO(String stockId, String itemName, int quantity, double price) {
        this.stockId = stockId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
public  String getStockId() {
        return stockId;
    }
    public void setStockId(String stockId) {
        this.stockId = stockId;

    }

    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;

    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;

}
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {


    }

    }

