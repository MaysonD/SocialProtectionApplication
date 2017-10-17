package controller.dialogtables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entity.DocumentEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentsDialogController implements Initializable {

    @FXML
    private DatePicker dateOfIssueField;
    @FXML
    private TextField issuedByField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField numberField;
    @FXML
    private TextField seriesField;

    private Stage dialogStage;
    private DocumentEntity documentEntity;
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

    public void setDocumentEntity(DocumentEntity documentEntity) {
        this.documentEntity = documentEntity;
        nameField.setText(this.documentEntity.getName());
        seriesField.setText(this.documentEntity.getSeries());
        numberField.setText(this.documentEntity.getNumber());
        issuedByField.setText(this.documentEntity.getIssuedBy());
        dateOfIssueField.setValue(this.documentEntity.getDate());
    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

    @FXML
    private void saveButtonClick() {
        setNewDocumentEntity();
        saveClicked = true;
        dialogStage.close();
    }

    private void setNewDocumentEntity() {
        this.documentEntity.setName(nameField.getText());
        this.documentEntity.setSeries(seriesField.getText());
        this.documentEntity.setNumber(numberField.getText());
        this.documentEntity.setIssuedBy(issuedByField.getText());
        this.documentEntity.setDate(dateOfIssueField.getValue());
    }
}
