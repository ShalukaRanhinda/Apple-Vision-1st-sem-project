module lk.ijse.applevisionmobileshop_ijse_76 {
    requires javafx.controls;
    requires javafx.fxml;
     requires java.sql;
     requires java.desktop;
     requires java.base;
    requires static lombok;

    opens lk.ijse.applevisionmobileshop_ijse_76.controller to javafx.fxml;
    opens lk.ijse.applevisionmobileshop_ijse_76.dto to javafx.base;
    opens lk.ijse.applevisionmobileshop_ijse_76 to javafx.fxml;

    exports lk.ijse.applevisionmobileshop_ijse_76;
    exports  lk.ijse.applevisionmobileshop_ijse_76.dto;
    exports lk.ijse.applevisionmobileshop_ijse_76.controller;

}