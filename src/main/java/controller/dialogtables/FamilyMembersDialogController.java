package controller.dialogtables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entity.FamilyMemberEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class FamilyMembersDialogController implements Initializable {

    @FXML
    private TextField addressField;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField relationDegreeField;

    private Stage dialogStage;
    private FamilyMemberEntity familyMemberEntity;
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

    public void setFamilyMemberEntity(FamilyMemberEntity familyMemberEntity) {
        this.familyMemberEntity = familyMemberEntity;
        relationDegreeField.setText(this.familyMemberEntity.getRelationDegree());
        fullNameField.setText(this.familyMemberEntity.getFullName());
        addressField.setText(this.familyMemberEntity.getAddress());
        dateOfBirthField.setValue(this.familyMemberEntity.getDateOfBirth());
        phoneNumberField.setText(this.familyMemberEntity.getPhoneNumber());
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
        this.familyMemberEntity.setRelationDegree(relationDegreeField.getText());
        this.familyMemberEntity.setFullName(fullNameField.getText());
        this.familyMemberEntity.setAddress(addressField.getText());
        this.familyMemberEntity.setDateOfBirth(dateOfBirthField.getValue());
        this.familyMemberEntity.setPhoneNumber(phoneNumberField.getText());
    }
}
