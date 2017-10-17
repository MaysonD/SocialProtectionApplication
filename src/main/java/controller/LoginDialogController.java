package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import database.DatabaseWorker;
import model.entity.UserEntity;

public class LoginDialogController {

    @FXML
    private PasswordField inputDistrictField;
    @FXML
    private PasswordField inputPasswordField;

    private Stage loginStage;
    private boolean authorized = false;

    public void initialize() {

    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setLoginAction() {
        if (isValidCredentials(inputDistrictField.getText().trim(), inputPasswordField.getText().trim())) {
            authorized = true;
            loginStage.close();
        }
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    private boolean isValidCredentials(String districtCode, String password) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean isOk = false;
        UserEntity user = DatabaseWorker.getUser(districtCode);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                isOk = true;
            }
            else {
                alert.setHeaderText("Неверный пароль !");
                alert.showAndWait();
            }
        }
        else {
            alert.setHeaderText("Неверный Логин !");
            alert.showAndWait();
        }
        return isOk;
    }
}
