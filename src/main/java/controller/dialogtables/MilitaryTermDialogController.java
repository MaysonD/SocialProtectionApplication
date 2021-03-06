package controller.dialogtables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entity.MilitaryTermEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class MilitaryTermDialogController implements Initializable {

    @FXML
    private TextField countryField;
    @FXML
    private DatePicker endOfServiceDate;
    @FXML
    private TextField localityField;
    @FXML
    private TextField militaryUnitField;
    @FXML
    private DatePicker startOfServiceDate;

    private Stage dialogStage;
    private MilitaryTermEntity militaryTermEntity;
    private boolean saveClicked = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMilitaryTermEntity(MilitaryTermEntity militaryTermEntity) {
        this.militaryTermEntity = militaryTermEntity;
        militaryUnitField.setText(this.militaryTermEntity.getUnit());
        countryField.setText(this.militaryTermEntity.getCountry());
        localityField.setText(this.militaryTermEntity.getLocality());
        startOfServiceDate.setValue(this.militaryTermEntity.getStartOfMilitaryService());
        endOfServiceDate.setValue(this.militaryTermEntity.getEndOfMilitaryService());
    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

    @FXML
    private void saveButtonClick() {
        setNewMilitaryTermEntity();
        saveClicked = true;
        dialogStage.close();
    }

    private void setNewMilitaryTermEntity() {
        this.militaryTermEntity.setUnit(militaryUnitField.getText());
        this.militaryTermEntity.setCountry(countryField.getText());
        this.militaryTermEntity.setLocality(localityField.getText());
        this.militaryTermEntity.setStartOfMilitaryService(startOfServiceDate.getValue());
        this.militaryTermEntity.setEndOfMilitaryService(endOfServiceDate.getValue());
    }
}
