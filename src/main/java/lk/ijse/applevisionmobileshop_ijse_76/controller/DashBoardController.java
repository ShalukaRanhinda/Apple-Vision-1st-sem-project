package lk.ijse.applevisionmobileshop_ijse_76.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import lk.ijse.applevisionmobileshop_ijse_76.HelloApplication;
import lk.ijse.applevisionmobileshop_ijse_76.dto.Session;
import lk.ijse.applevisionmobileshop_ijse_76.dto.UserDTO;
import lk.ijse.applevisionmobileshop_ijse_76.model.UserModel;

import java.io.IOException;
import java.sql.SQLException;

public class DashBoardController {

    public AnchorPane ancDashboard;
    @FXML
    private AnchorPane mainContent;
    @FXML

    private static UserModel userModel = new UserModel();
    private final Session currentSession = new Session();

    @FXML
    private void goToCustomerView()throws IOException {
        navigateTo("/view/Customer.fxml");
    }

    public void goToSupplierView(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Supplier.fxml");
    }

    public void goToOrderView(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Order.fxml");

    }

    public void goToStockView(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/Stock.fxml");

    }
    public void goToUserView(ActionEvent actionEvent) throws IOException {
        navigateTo("/view/User.fxml");

    }

    private void navigateTo(String path) {
        try {
            mainContent.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));
            anchorPane.prefWidthProperty().bind(mainContent.widthProperty());
            anchorPane.prefHeightProperty().bind(mainContent.heightProperty());
            mainContent.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found", ButtonType.OK).show();
            e.printStackTrace();
        }
    }




    public void onActionLogout(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to logout?",
                ButtonType.YES, ButtonType.NO);

        alert.initStyle(StageStyle.UNDECORATED);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {

                UserDTO currentUser = Session.getCurrentUser();

                if (currentUser != null) {
                    try {
                        userModel.updateUserStatus(currentUser.getId(), "Inactive");
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    Session.clear();
                }

                try {
                    AnchorPane loginPane =
                            FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                    ancDashboard.getScene().setRoot(loginPane);
                } catch (IOException e) {
                    new Alert(Alert.AlertType.ERROR,
                            "Failed to load login page").show();
                    e.printStackTrace();
                }
            }
        });
    }



}
