import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.LoginDialogController;
import controller.RootLayoutController;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    public MainApp() {

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Application");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);
        primaryStage.setMaximized(true);
        if (isAuthorized()) {
            initRootLayout();
        }
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/RootLayout.fxml"));
            BorderPane rootLayout = loader.load();
            rootLayout.setMinSize(800, 650);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setPane(rootLayout);
            controller.setStage(primaryStage);
            controller.showPersonOverview();
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isAuthorized() {
        try {
            FXMLLoader loginLoader = new FXMLLoader();
            loginLoader.setLocation(getClass().getClassLoader().getResource("view/LoginDialog.fxml"));
            AnchorPane pane = (AnchorPane) loginLoader.load();
            Stage loginStage = new Stage();
            Scene scene = new Scene(pane);
            loginStage.setScene(scene);

            LoginDialogController controller = loginLoader.getController();
            controller.setLoginStage(loginStage);
            loginStage.showAndWait();
            return controller.isAuthorized();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
