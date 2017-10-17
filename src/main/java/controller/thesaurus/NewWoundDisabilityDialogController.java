package controller.thesaurus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import database.DatabaseWorker;
import model.entity.WoundDisabilityEntity;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewWoundDisabilityDialogController implements Initializable {

    @FXML
    private TableView<WoundDisabilityEntity> existsWoundsDisabilitiesTable;
    @FXML
    private TableColumn<WoundDisabilityEntity, String> woundDisabilitiesColumn;
    @FXML
    private TextField newWoundDisabilitiesTextField;
    private ObservableList<WoundDisabilityEntity> woundDisabilityEntities;
    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        woundDisabilitiesColumn.setCellValueFactory(new PropertyValueFactory<>("disability"));
        this.woundDisabilityEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectWoundDisabilitiesQuery());
        existsWoundsDisabilitiesTable.setItems(woundDisabilityEntities);
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
        int selectedIndex = existsWoundsDisabilitiesTable.getSelectionModel().getSelectedIndex();
        WoundDisabilityEntity selectedItem = existsWoundsDisabilitiesTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Предупреждающее окно");
        alert.setContentText("Вы действительно хотите удалить данные?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            existsWoundsDisabilitiesTable.getItems().remove(selectedIndex);
            DatabaseWorker.deleteWoundDisability(selectedItem);
            existsWoundsDisabilitiesTable.refresh();
        }

    }

    @FXML
    private void handleSaveButton() {
        DatabaseWorker.saveWoundDisability(new WoundDisabilityEntity(newWoundDisabilitiesTextField.getText()));
        dialogStage.close();
    }
}

