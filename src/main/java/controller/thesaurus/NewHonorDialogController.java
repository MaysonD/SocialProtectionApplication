package controller.thesaurus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import database.DatabaseWorker;
import model.entity.HonorEntity;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewHonorDialogController implements Initializable {

    @FXML
    private TableView<HonorEntity> existsHonorsTable;
    @FXML
    private TableColumn<HonorEntity, String> honorsColumn;
    @FXML
    private TextField newHonorTextField;

    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        honorsColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<HonorEntity> honorEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectHonorsQuery());
        existsHonorsTable.setItems(honorEntities);
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
        int selectedIndex = existsHonorsTable.getSelectionModel().getSelectedIndex();
        HonorEntity selectedItem = existsHonorsTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Предупреждающее окно");
        alert.setContentText("Вы действительно хотите удалить данные?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            existsHonorsTable.getItems().remove(selectedIndex);
            DatabaseWorker.deleteHonor(selectedItem);
            existsHonorsTable.refresh();
        }
    }

    @FXML
    private void handleSaveButton() {
        DatabaseWorker.saveNewHonor(new HonorEntity(newHonorTextField.getText()));
        dialogStage.close();
    }
}

