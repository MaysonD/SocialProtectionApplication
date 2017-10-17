package controller.dialogtables;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entity.DisplacementEntity;

import java.net.URL;
import java.util.ResourceBundle;

public class DisplacementsDialogController implements Initializable {

    @FXML
    private DatePicker arrivedDate;
    @FXML
    private TextField arrivedPlace;
    @FXML
    private DatePicker decreasedDate;
    @FXML
    private TextField decreasedPlace;

    private Stage dialogStage;
    private DisplacementEntity displacementEntity;
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

    public void setDisplacementEntity(DisplacementEntity displacementEntity) {
        this.displacementEntity = displacementEntity;
        arrivedDate.setValue(this.displacementEntity.getArrivedDate());
        arrivedPlace.setText(this.displacementEntity.getArrivedPlace());
        decreasedDate.setValue(this.displacementEntity.getDecreasedDate());
        decreasedPlace.setText(this.displacementEntity.getDecreasedPlace());
    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

    @FXML
    private void saveButtonClick() {
        setNewDisplacementEntity();
        saveClicked = true;
        dialogStage.close();
    }

    private void setNewDisplacementEntity() {
        this.displacementEntity.setArrivedDate(arrivedDate.getValue());
        this.displacementEntity.setArrivedPlace(arrivedPlace.getText());
        this.displacementEntity.setDecreasedDate(decreasedDate.getValue());
        this.displacementEntity.setDecreasedPlace(decreasedPlace.getText());
    }
}
