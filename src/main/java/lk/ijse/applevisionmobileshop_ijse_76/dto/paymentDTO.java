package lk.ijse.applevisionmobileshop_ijse_76.dto;

public class paymentDTO {
    private String customerId;
    private String orderId;
    private double amount;
    public paymentDTO() {
    }
    public paymentDTO(String customerId, String orderId, double amount) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.amount = amount;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;


    }
}
