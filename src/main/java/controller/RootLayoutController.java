package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.thesaurus.NewHonorDialogController;
import controller.thesaurus.NewRankDialogController;
import controller.thesaurus.NewWoundDisabilityDialogController;
import controller.thesaurus.NewWoundTypeDialogController;
import helpers.ExcelTableGenerator;
import helpers.FileLoader;
import helpers.WordDocumentGenerator;

import java.io.IOException;

public class RootLayoutController {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private VeteranOverviewController veteranOverviewController;

    public void setPane(BorderPane rootLayout) {
        this.rootLayout = rootLayout;
        // Add observable list data to the table
    }

    public void setStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showPersonOverview() {
        try {
            FXMLLoader veteranOverviewLoader = new FXMLLoader();
            veteranOverviewLoader.setLocation(getClass().getClassLoader().getResource("view/VeteranOverview.fxml"));
            BorderPane pane = veteranOverviewLoader.load();
            rootLayout.setCenter(pane);

            this.veteranOverviewController = veteranOverviewLoader.getController();
            veteranOverviewController.setPaginationProperties();
            veteranOverviewController.setPrimaryStage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddHonorMenuItem() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/menudialogs/NewHonorDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            NewHonorDialogController controller = loader.getController();
            controller.setDialogStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddRankMenuItem() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/menudialogs/NewRankDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            NewRankDialogController controller = loader.getController();
            controller.setDialogStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddWoundDisabilityMenuItem() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/menudialogs/NewWoundDisabilityDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            NewWoundDisabilityDialogController controller = loader.getController();
            controller.setDialogStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddWoundTypeMenuItem() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/menudialogs/NewWoundTypeDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            NewWoundTypeDialogController controller = loader.getController();
            controller.setDialogStage(stage);

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSaveToExcel() {
        try {
            ExcelTableGenerator.writeIntoExcel(veteranOverviewController.getSelectedVeterans(), FileLoader.saveExcel());
        } catch (IOException e) {

        }

    }

    @FXML
    private void handleSaveToWord() {
        WordDocumentGenerator.generateWordReport(veteranOverviewController.getSelectedVeteran(), FileLoader.saveWord());
    }

    Stage getPrimaryStage() {
        return primaryStage;
    }
}
