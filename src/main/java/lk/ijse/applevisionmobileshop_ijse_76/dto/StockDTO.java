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


}
