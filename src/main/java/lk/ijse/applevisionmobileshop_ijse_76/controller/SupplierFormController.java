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
import lk.ijse.applevisionmobileshop_ijse_76.dto.SupplierDTO;
import lk.ijse.applevisionmobileshop_ijse_76.model.SupplierModel;

import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    private TableColumn<SupplierDTO, String> addressColumn;

    @FXML
    private TableColumn<SupplierDTO, String> contactColumn;

    @FXML
    private TableColumn<SupplierDTO, String> emailColumn;

    @FXML
    private TableColumn<SupplierDTO, String> idColumn;

    @FXML
    private Label lblId;

    @FXML
    private TableColumn<SupplierDTO, String> nameColumn;

    @FXML
    private TableColumn<SupplierDTO, String> nicColumn;

    @FXML
    private TableView<SupplierDTO> tableSupplier;

    @FXML
    private TextField textAddress;

    @FXML
    private TextField textContact;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textName;

    @FXML
    private TextField textNic;

    private final SupplierModel supplierModel = new SupplierModel();

    @FXML
    void onActionDelete(ActionEvent event) {
        String id = lblId.getText();
        if (id.isEmpty()) {
            showErrorMessage("Please select a supplier to delete.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Are you sure you want to delete this supplier?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isDeleted = supplierModel.deleteSupplier(id);
                if (isDeleted) {
                    showSuccessMessage("Supplier deleted successfully!");
                    loadSupplierTable();
                    clearFields();
                    setGeneretedSupplierId();
                } else {
                    showErrorMessage("Failed to delete supplier!");
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
    }

    @FXML
    void onActionSave(ActionEvent event) {
        if (!validateInputs()) return;

        SupplierDTO supplierDTO = createSupplierDtoFromInputs();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Are you sure you want to add this Supplier?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isAdded = supplierModel.saveSupplier(supplierDTO);
                if (isAdded) {
                    showSuccessMessage("Supplier added successfully!");
                    loadSupplierTable();
                    clearFields();
                    setGeneretedSupplierId();
                } else {
                    showErrorMessage("Failed to add supplier!");
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                showErrorMessage("Integrity constraint violation: " + e.getMessage());
                e.printStackTrace();

            } catch (SQLException e) {
                showErrorMessage("SQL Error: " + e.getMessage());
                e.printStackTrace();

            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage("Error occurred while adding!");
            }
        }
    }



    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    void onActionUpdate(ActionEvent event) {
        if (!validateInputs()) return;

        SupplierDTO dto = createSupplierDtoFromInputs();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setContentText("Are you sure you want to update this suppler?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-border-color: green; -fx-border-width: 2px;");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean isUpdated = supplierModel.updateSupplier(dto);
                if (isUpdated) {
                    showSuccessMessage("Supplier updated successfully!");
                    loadSupplierTable();
                    clearFields();
                    setGeneretedSupplierId();
                } else {
                    showErrorMessage("Failed to update supplier!");
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                showErrorMessage("Integrity constraint violation: " + e.getMessage());
            } catch (SQLException e) {
                showErrorMessage("SQL Error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                showErrorMessage("Error occurred while updating!");
            }
        }
    }

    @FXML
    void setData(MouseEvent event) {
        SupplierDTO customerDto = tableSupplier.getSelectionModel().getSelectedItem();
        if (customerDto != null) {
            lblId.setText(customerDto.getSupplierId());
            textName.setText(customerDto.getName());
            textNic.setText(customerDto.getNic());
            textEmail.setText(customerDto.getEmail());
            textContact.setText(customerDto.getContact());
            textAddress.setText(customerDto.getAddress());
        }
    }
    private boolean validateInputs() {
        if (textName.getText().isEmpty() || textNic.getText().isEmpty() || textContact.getText().isEmpty() ||
                textEmail.getText().isEmpty()  || textAddress.getText().isEmpty()) {
            showErrorMessage("All fields must be filled!");
            return false;
        }

        if (!isValidEmail(textEmail.getText())) {
            showErrorMessage("Invalid email format!");
            return false;
        }
        if (!isValidContact(textContact.getText())) {
            showErrorMessage("Contact number must be 10 digits!");
            return false;
        }
        if (!isValidNIC(textNic.getText())) {
            showErrorMessage("Invalid NIC format!");
            return false;
        }
        return true;
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(message);
        alert.showAndWait();
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidContact(String contact) {
        return contact != null && contact.matches("^\\d{10}$");
    }

    private boolean isValidNIC(String nic) {
        return nic != null && (nic.matches("^\\d{9}[vVxX]$") || nic.matches("^\\d{12}$"));
    }
    private SupplierDTO createSupplierDtoFromInputs() {
        return new SupplierDTO(
                lblId.getText(),
                textName.getText(),
                textNic.getText(),
                textEmail.getText(),
                textContact.getText(),
                textAddress.getText());
    }

    private void clearFields() {
        lblId.setText("");
        textAddress.clear();
        textContact.clear();
        textEmail.clear();
        textNic.clear();
        textName.clear();
        setGeneretedSupplierId();
    }
    private void setGeneretedSupplierId() {
        try {
            String lastId = supplierModel.getLastSupplierId();
            int newIdNum = 1;
            if (lastId != null && lastId.startsWith("S")) {
                newIdNum = Integer.parseInt(lastId.substring(1)) + 1;
            }
            String formattedId = String.format("S%03d", newIdNum);
            lblId.setText(formattedId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadSupplierTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>("nic"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        try{
            ArrayList<SupplierDTO> supplierDtos = supplierModel.getAllSuppliers();
            if(supplierDtos != null && !supplierDtos.isEmpty()){
//                showSuccessMessage("Supplier loaded: " + supplierDtos.size());
                ObservableList<SupplierDTO> supplierObservableList = FXCollections.observableArrayList(supplierDtos);
                tableSupplier.setItems(supplierObservableList);
            }
//            else {
//                showErrorMessage("No supplier found!");
//            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGeneretedSupplierId();
        loadSupplierTable();
    }
}
