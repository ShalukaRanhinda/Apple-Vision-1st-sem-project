package lk.ijse.applevisionmobileshop_ijse_76.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import lk.ijse.applevisionmobileshop_ijse_76.dto.UserDTO;
import lk.ijse.applevisionmobileshop_ijse_76.model.UserModel;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private TableColumn<UserDTO, String> idColumn;

    @FXML
    private TableColumn<UserDTO, String> UsernameColumn;

    @FXML
    private TableColumn<UserDTO, String> emailColumn;

    @FXML
    private TableColumn<UserDTO, String> passwordColumn;

    @FXML
    private TableColumn<UserDTO, String> jobroleColumn;

    @FXML
    private TableColumn<UserDTO, String> statusColumn;

    @FXML
    private TableView<UserDTO> tableUser;

    @FXML
    private Label lblId;

    @FXML
    private TextField textUserame;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textPassword;

    @FXML
    private TextField textJobrole;

    @FXML
    private TextField textStats;

    private static UserModel userModel = new UserModel();
    @FXML
    void onActionSave(ActionEvent event) {
        if (!validateInputs()) return;

        UserDTO dto = createUserDtoFromInputs();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Are you sure you want to add this user?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isSaved = new UserModel().saveUser(dto);
                if (isSaved) {
                    showSuccessMessage("User added successfully!");
                    loadUserTable();
                    clearFields();
                    generateUserId();
                } else {
                    showErrorMessage("Failed to add user!");
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                showErrorMessage("Duplicate email or username!");
            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage("Error occurred while saving user!");
            }
        }
    }

    // ================= UPDATE =================
    @FXML
    void onActionUpdate(ActionEvent event) {
        if (!validateInputs()) return;

        UserDTO dto = createUserDtoFromInputs();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Are you sure you want to update this user?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isUpdated = new UserModel().updateUser(dto);
                if (isUpdated) {
                    showSuccessMessage("User updated successfully!");
                    loadUserTable();
                    clearFields();
                    generateUserId();
                } else {
                    showErrorMessage("Failed to update user!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage("Error occurred while updating!");
            }
        }
    }

    // ================= DELETE =================
    @FXML
    void onActionDelete(ActionEvent event) {
        String id = lblId.getText();
        if (id.isEmpty()) {
            showErrorMessage("Please select a user to delete!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Are you sure you want to delete this user?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isDeleted = new UserModel().deleteUser(id);
                if (isDeleted) {
                    showSuccessMessage("User deleted successfully!");
                    loadUserTable();
                    clearFields();
                    generateUserId();
                } else {
                    showErrorMessage("Failed to delete user!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage("Error occurred while deleting!");
            }
        }
    }

    @FXML
    void onActionReset(ActionEvent event) {
        clearFields();
        generateUserId();
    }

    @FXML
    void setData(MouseEvent event) {
        UserDTO dto = tableUser.getSelectionModel().getSelectedItem();
        if (dto != null) {
            lblId.setText(dto.getId());
            textUserame.setText(dto.getUsername());
            textEmail.setText(dto.getEmail());
            textPassword.setText(dto.getPassword());
            textJobrole.setText(dto.getJob_role());
            textStats.setText(dto.getStatus());
        }
    }

    private void loadUserTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        jobroleColumn.setCellValueFactory(new PropertyValueFactory<>("job_role"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            ArrayList<UserDTO> users = new UserModel().getAllUsers();
            ObservableList<UserDTO> list = FXCollections.observableArrayList(users);
            tableUser.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateUserId() {
        try {
            String lastId =userModel.getLastUserId();
            int next = 1;
            if (lastId != null && lastId.startsWith("U")) {
                next = Integer.parseInt(lastId.substring(1)) + 1;
            }
            lblId.setText(String.format("U%03d", next));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UserDTO createUserDtoFromInputs() {
        return new UserDTO(
                lblId.getText(),
                textUserame.getText(),
                textEmail.getText(),
                textPassword.getText(),
                textJobrole.getText(),
                textStats.getText()
        );
    }

    private boolean validateInputs() {
        if (textUserame.getText().isEmpty() ||
                textEmail.getText().isEmpty() ||
                textPassword.getText().isEmpty() ||
                textJobrole.getText().isEmpty() ||
                textStats.getText().isEmpty()) {
            showErrorMessage("All fields must be filled!");
            return false;
        }
        return true;
    }

    private void clearFields() {
        textUserame.clear();
        textEmail.clear();
        textPassword.clear();
        textJobrole.clear();
        textStats.clear();
    }

    private void showSuccessMessage(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    private void showErrorMessage(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateUserId();
        loadUserTable();
    }
}
