package controller.dialogtables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entity.WorkPlaceEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class WorkPlaceDialogController implements Initializable {

    @FXML
    private TextField departmentPhoneNumberField;
    @FXML
    private TextField localityField;
    @FXML
    private TextField organizationField;
    @FXML
    private TextField positionField;

    private Stage dialogStage;
    private boolean saveClicked = false;
    private WorkPlaceEntity workPlaceEntity;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setWorkPlaceEntity(WorkPlaceEntity workPlaceEntity) {
        this.workPlaceEntity = workPlaceEntity;
        localityField.setText(this.workPlaceEntity.getLocality());
        positionField.setText(this.workPlaceEntity.getPosition());
        departmentPhoneNumberField.setText(workPlaceEntity.getOrganization());
    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

    @FXML
    private void saveButtonClick() {
        setNewFamilyMemberEntity();
        saveClicked = true;
        dialogStage.close();
    }

    private void setNewFamilyMemberEntity() {
        this.workPlaceEntity.setLocality(localityField.getText());
        this.workPlaceEntity.setOrganization(organizationField.getText());
        this.workPlaceEntity.setPosition(positionField.getText());
        this.workPlaceEntity.setHrNumber(departmentPhoneNumberField.getText());
    }
}
