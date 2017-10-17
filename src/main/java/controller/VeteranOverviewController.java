package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import database.DatabaseWorker;
import database.QueryBuilder;
import model.entity.CategoryEntity;
import model.entity.DistrictEntity;
import model.entity.SubcategoryEntity;
import model.entity.VeteranEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class VeteranOverviewController {

    @FXML
    private TableColumn<VeteranEntity, String> addressColumn;
    @FXML
    private TableColumn<VeteranEntity, String> caseNumberColumn;
    @FXML
    private TableColumn<VeteranEntity, String> categoryColumn;
    @FXML
    private ComboBox<CategoryEntity> categoryFilterField;
    @FXML
    private TextField countryOfMilitaryServiceFilterField;
    @FXML
    private TableColumn<VeteranEntity, LocalDate> dateOfBirthColumn;
    @FXML
    private TableColumn<VeteranEntity, String> districtColumn;
    @FXML
    private ChoiceBox<DistrictEntity> districtFilterField;
    @FXML
    private TextField secondNameFilterField;
    @FXML
    private TableColumn<VeteranEntity, String> firstNameColumn;
    @FXML
    private TableColumn<VeteranEntity, String> lastNameColumn;
    @FXML
    private TextField localityOfMilitaryServiceFilterField;
    @FXML
    private TableView<VeteranEntity> mainTable;
    @FXML
    private TextField marchingOrganizationFilterField;
    @FXML
    private TableColumn<VeteranEntity, String> middleNameColumn;
    @FXML
    private TableColumn<VeteranEntity, String> militaryRankColumn;
    @FXML
    private TextField militaryUnitFilterField;
    @FXML
    private TextField regionalExecutiveCommitteeFilterField;
    private RootLayoutController rootLayoutController;
    @FXML
    private ComboBox<SubcategoryEntity> subcategoryFilterField;
    @FXML
    private Pagination tableViewPagination;
    @FXML
    private TextField villageExecutiveCommitteeFilterField;
    @FXML
    private TextField woundFilterField;

    private ObservableList<CategoryEntity> categoryEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectCategoriesQuery());
    private ObservableList<DistrictEntity> districtEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectDistrictsQuery());
    private ObservableList<VeteranEntity> veteransData;
    private Map<String, String> veteransMap = new LinkedHashMap<>();

    public LinkedList<VeteranEntity> getSelectedVeterans() {
        return new LinkedList<>(mainTable.getSelectionModel().getSelectedItems());
    }

    public void handleSelectCategory() {
        subcategoryFilterField.setDisable(false);
        if (!categoryFilterField.getSelectionModel().isEmpty()) {
            subcategoryFilterField.setItems(FXCollections.observableArrayList(categoryFilterField.getValue().getSubcategories()));
        }
    }

    private void clearAllFilters() {
        districtFilterField.getSelectionModel().clearSelection();
        categoryFilterField.getSelectionModel().clearSelection();
        subcategoryFilterField.getSelectionModel().clearSelection();
        militaryUnitFilterField.clear();
        countryOfMilitaryServiceFilterField.clear();
        localityOfMilitaryServiceFilterField.clear();
        woundFilterField.clear();
        marchingOrganizationFilterField.clear();
        villageExecutiveCommitteeFilterField.clear();
        regionalExecutiveCommitteeFilterField.clear();
    }

    @FXML
    private void clearFiltersHandle() {
        this.veteransMap = new LinkedHashMap<>();
        clearAllFilters();
        setPaginationProperties();
    }

    private Node createPage(int pageIndex) {
        BorderPane borderPane = new BorderPane();
        mainTable.setItems(getVeteransData(pageIndex));
        return borderPane;
    }

    private Map<String, String> getFiltersMap() {
        Map<String, String> filtersMap = new LinkedHashMap<>();
        if (!districtFilterField.getSelectionModel().isEmpty()) {
            filtersMap.put("DistrictName", districtFilterField.getValue().getName());
        }
        if (!categoryFilterField.getSelectionModel().isEmpty()) {
            filtersMap.put("Category", categoryFilterField.getValue().getName());
        }
        if (!subcategoryFilterField.getSelectionModel().isEmpty()) {
            filtersMap.put("Subcategory", subcategoryFilterField.getValue().getName());
        }
        if (!militaryUnitFilterField.getText().equals("")) {
            filtersMap.put("MilitaryUnit", militaryUnitFilterField.getText());
        }
        if (!countryOfMilitaryServiceFilterField.getText().equals("")) {
            filtersMap.put("MilitaryCountry", countryOfMilitaryServiceFilterField.getText());
        }
        if (!localityOfMilitaryServiceFilterField.getText().equals("")) {
            filtersMap.put("MilitaryLocality", localityOfMilitaryServiceFilterField.getText());
        }
        if (!woundFilterField.getText().equals("")) {
            filtersMap.put("WoundType", woundFilterField.getText());
        }
        if (!marchingOrganizationFilterField.getText().equals("")) {
            filtersMap.put("MarchingOrganization", marchingOrganizationFilterField.getText());
        }
        if (!villageExecutiveCommitteeFilterField.getText().equals("")) {
            filtersMap.put("VillageExecutiveCommittee", villageExecutiveCommitteeFilterField.getText());
        }
        if (!regionalExecutiveCommitteeFilterField.getText().equals("")) {
            filtersMap.put("RegionalExecutiveCommittee", regionalExecutiveCommitteeFilterField.getText());
        }
        if (!secondNameFilterField.getText().equals("")) {
            filtersMap.put("SecondName", secondNameFilterField.getText());
        }
        return filtersMap;
    }

    private List<VeteranEntity> getVeterans(int pageNumber) {
        QueryBuilder qb = new QueryBuilder();
        return DatabaseWorker.executeSelectVeteransQuery(qb.prepareSelectQuery(this.veteransMap, pageNumber));
    }

    private ObservableList<VeteranEntity> getVeteransData(int pageIndex) {
        this.veteransData = FXCollections.observableArrayList(getVeterans(pageIndex));
        return veteransData;
    }

    @FXML
    private void handleAddVeteran() {
        VeteranEntity tempPerson = new VeteranEntity();
        boolean addButtonClicked = showVeteranDialog(tempPerson);
        if (addButtonClicked) {
            DatabaseWorker.saveVeteran(tempPerson);
            veteransData.add(tempPerson);
            mainTable.refresh();
        }
    }

    @FXML
    private void handleDeleteVeteran() {
        int selectedIndex = mainTable.getSelectionModel().getSelectedIndex();
        VeteranEntity selectedVeteran = mainTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Предупреждающее окно");
        alert.setContentText("Вы действительно хотите удалить данные?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            mainTable.getItems().remove(selectedIndex);
            DatabaseWorker.deleteVeteran(selectedVeteran);
            mainTable.refresh();
        }
    }

    @FXML
    private void handleEditVeteran() {
        VeteranEntity selectedVeteran = mainTable.getSelectionModel().getSelectedItem();
        if (selectedVeteran != null) {
            boolean saveButtonClicked = showVeteranDialog(selectedVeteran);
            if (saveButtonClicked) {
                DatabaseWorker.saveVeteran(selectedVeteran);
                mainTable.refresh();
            }
        }
        setPaginationProperties();
    }

    @FXML
    private void initialize() {
        caseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("caseNumber"));
        militaryRankColumn.setCellValueFactory(new PropertyValueFactory<>("militaryRank"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        districtColumn.setCellValueFactory(new PropertyValueFactory<>("district"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        mainTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        districtFilterField.setItems(districtEntities);
        categoryFilterField.setItems(categoryEntities);
    }

    private boolean showVeteranDialog(VeteranEntity veteranEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/VeteranDialog.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(rootLayoutController.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            VeteranDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVeteranEntity(veteranEntity);
            dialogStage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void useFiltersHandle() {
        this.veteransMap = getFiltersMap();
        setPaginationProperties();
    }

    void setPrimaryStage(RootLayoutController rootLayoutController) {
        this.rootLayoutController = rootLayoutController;
    }

    void setPaginationProperties() {
        int pagesCount = DatabaseWorker.getPagesCount();
        tableViewPagination.setMaxPageIndicatorCount(pagesCount);
        tableViewPagination.setPageCount(pagesCount);
        tableViewPagination.setPageFactory(this::createPage);
    }

    VeteranEntity getSelectedVeteran() {
        return mainTable.getSelectionModel().getSelectedItem();
    }
}
