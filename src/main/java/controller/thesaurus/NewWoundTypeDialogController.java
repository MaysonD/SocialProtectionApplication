package controller.thesaurus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import database.DatabaseWorker;
import model.entity.WoundTypeEntity;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewWoundTypeDialogController implements Initializable {

    @FXML
    private TableView<WoundTypeEntity> existsWoundTypesTable;
    @FXML
    private TableColumn<WoundTypeEntity, String> woundTypesColumn;
    @FXML
    private TextField newWoundTypeTextField;

    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        woundTypesColumn.setCellValueFactory(new PropertyValueFactory<>("woundType"));
        ObservableList<WoundTypeEntity> woundEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectWoundTypesQuery());
        existsWoundTypesTable.setItems(woundEntities);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleCloseButton() {
        dialogStage.close();
    }

    @FXML
    private void handleDeleteButton() {
        int selectedIndex = existsWoundTypesTable.getSelectionModel().getSelectedIndex();
        WoundTypeEntity selectedItem = existsWoundTypesTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Предупреждающее окно");
        alert.setContentText("Вы действительно хотите удалить данные?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            existsWoundTypesTable.getItems().remove(selectedIndex);
            DatabaseWorker.deleteWoundType(selectedItem);
            existsWoundTypesTable.refresh();
        }

    }

    @FXML
    private void handleSaveButton() {
        DatabaseWorker.saveWoundType(new WoundTypeEntity(newWoundTypeTextField.getText()));
        dialogStage.close();
    }
}

