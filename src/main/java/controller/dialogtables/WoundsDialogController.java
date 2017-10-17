package controller.dialogtables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import database.DatabaseWorker;
import model.entity.VeteranWoundEntity;
import model.entity.WoundDisabilityEntity;
import model.entity.WoundTypeEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class WoundsDialogController implements Initializable {

    @FXML
    private DatePicker dateField;
    @FXML
    private ComboBox<WoundDisabilityEntity> disabilityField;
    @FXML
    private ComboBox<WoundTypeEntity> typeField;

    private ObservableList<WoundDisabilityEntity> disabilityEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectWoundDisabilitiesQuery());
    private ObservableList<WoundTypeEntity> typeEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectWoundTypesQuery());

    private Stage dialogStage;
    private boolean saveClicked = false;
    private VeteranWoundEntity woundEntity;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeField.setItems(typeEntities);
        disabilityField.setItems(disabilityEntities);
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setWoundEntity(VeteranWoundEntity veteranWoundEntity) {
        this.woundEntity = veteranWoundEntity;
        typeField.setValue(this.woundEntity.getWoundType());
        disabilityField.setValue(this.woundEntity.getWoundDisability());
        dateField.setValue(this.woundEntity.getDate());
    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

    @FXML
    private void saveButtonClick() {
        setNewWoundEntity();
        saveClicked = true;
        dialogStage.close();
    }

    private void setNewWoundEntity() {
        this.woundEntity.setWoundType(typeField.getValue());
        this.woundEntity.setDate(dateField.getValue());
        this.woundEntity.setWoundDisability(disabilityField.getValue());
    }
}
