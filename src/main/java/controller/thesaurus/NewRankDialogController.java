package controller.thesaurus;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import database.DatabaseWorker;
import model.entity.RankEntity;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewRankDialogController implements Initializable {

    @FXML
    private TableView<RankEntity> existsRanksTable;
    @FXML
    private TableColumn<RankEntity, String> ranksColumn;
    @FXML
    private TextField newRankTextField;

    private Stage dialogStage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ranksColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<RankEntity> rankEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectRanksQuery());
        existsRanksTable.setItems(rankEntities);
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
        int selectedIndex = existsRanksTable.getSelectionModel().getSelectedIndex();
        RankEntity selectedItem = existsRanksTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Предупреждающее окно");
        alert.setContentText("Вы действительно хотите удалить данные?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            existsRanksTable.getItems().remove(selectedIndex);
            DatabaseWorker.deleteRank(selectedItem);
            existsRanksTable.refresh();
        }
    }

    @FXML
    private void handleSaveButton() {
        DatabaseWorker.saveNewRank(new RankEntity(newRankTextField.getText()));
        dialogStage.close();
    }
}

