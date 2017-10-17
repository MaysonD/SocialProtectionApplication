package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import controller.dialogtables.*;
import database.DatabaseWorker;
import helpers.DateValidator;
import helpers.FileLoader;
import helpers.ImageConverter;
import model.entity.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class VeteranDialogController {

    @FXML
    private TextField addressField;
    @FXML
    private TableColumn<DisplacementEntity, LocalDate> arrivedDateColumn;
    @FXML
    private TableColumn<DisplacementEntity, String> arrivedPlaceColumn;
    @FXML
    private TextField burialPlaceField;
    @FXML
    private Label burialPlaceLabel;
    @FXML
    private TextField caseNumberField;
    @FXML
    private ComboBox<CategoryEntity> categoryField;
    @FXML
    private TableColumn<MilitaryTermEntity, String> countryOfMilitaryServiceColumn;
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private DatePicker dateOfDeathField;
    @FXML
    private Label dateOfDeathLabel;
    @FXML
    private TableColumn<DocumentEntity, LocalDate> dateOfIssueDocumentColumn;
    @FXML
    private TableColumn<VeteranHonorEntity, String> dateOfReceivingColumn;
    @FXML
    private TableColumn<VeteranWoundEntity, LocalDate> dateOfWoundColumn;
    @FXML
    private TableColumn<DisplacementEntity, LocalDate> decreasedDateColumn;
    @FXML
    private TableColumn<DisplacementEntity, String> decreasedPlaceColumn;
    private Stage dialogStage;
    @FXML
    private TableColumn<VeteranWoundEntity, String> disabilityColumn;
    @FXML
    private TableView<DisplacementEntity> displacementTable;
    @FXML
    private ComboBox<DistrictEntity> districtField;
    @FXML
    private TableColumn<DocumentEntity, String> documentIssuedByColumn;
    @FXML
    private TableColumn<DocumentEntity, String> documentNameColumn;
    @FXML
    private TableView<DocumentEntity> documentsTable;
    @FXML
    private TableColumn<MilitaryTermEntity, String> endOfMilitaryServiceColumn;
    @FXML
    private TableColumn<FamilyMemberEntity, String> familyMembersAddressColumn;
    @FXML
    private TableColumn<FamilyMemberEntity, String> familyMembersDateOfBirthColumn;
    @FXML
    private TableColumn<FamilyMemberEntity, String> familyMembersFullNameColumn;
    @FXML
    private TableColumn<FamilyMemberEntity, String> familyMembersPhoneNumberColumn;
    @FXML
    private TableColumn<FamilyMemberEntity, String> familyMembersRelationDegreeColumn;
    @FXML
    private TableView<FamilyMemberEntity> familyMembersTable;
    @FXML
    private TextField firstNameField;
    @FXML
    private TableView<VeteranHonorEntity> honorsTable;
    @FXML
    private ImageView imagePhotoView;
    @FXML
    private CheckBox isDeadCheckBox;
    @FXML
    private TableColumn<MilitaryTermEntity, String> localityOfMilitaryServiceColumn;
    @FXML
    private TableColumn<WorkPlaceEntity, String> localityOfWorkColumn;
    @FXML
    private TextField marchingOrganizationField;
    @FXML
    private TextField middleNameField;
    @FXML
    private ComboBox<RankEntity> militaryRankField;
    @FXML
    private TextField militarySubdivisionField;
    @FXML
    private TableView<MilitaryTermEntity> militaryTermTable;
    @FXML
    private TableColumn<MilitaryTermEntity, String> militaryUnitColumn;
    @FXML
    private TableColumn<VeteranHonorEntity, String> nameOfHonorColumn;
    @FXML
    private TableColumn<WorkPlaceEntity, String> numberOfDepartmentColumn;
    @FXML
    private TableColumn<DocumentEntity, String> numberOfDocumentColumn;
    @FXML
    private TableColumn<VeteranHonorEntity, String> orderColumn;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField positionField;
    @FXML
    private TextField regionalExecutiveCommitteeField;
    @FXML
    private TextField registrationAddressField;
    private boolean saveClicked = false;
    @FXML
    private TextField secondNameField;
    @FXML
    private TableColumn<DocumentEntity, String> seriesOfDocumentColumn;
    @FXML
    private TableColumn<MilitaryTermEntity, String> startOfMilitaryServiceColumn;
    @FXML
    private ComboBox<SubcategoryEntity> subcategoryField;
    @FXML
    private TableColumn<VeteranWoundEntity, String> typeOfWoundColumn;
    private VeteranEntity veteranEntity;
    @FXML
    private TextField villageExecutiveCommitteeField;
    @FXML
    private TableColumn<WorkPlaceEntity, String> workOrganizationColumn;
    @FXML
    private TableView<WorkPlaceEntity> workPlaceTable;
    @FXML
    private TableColumn<WorkPlaceEntity, String> workPositionColumn;
    @FXML
    private TableView<VeteranWoundEntity> woundsTable;

    private ObservableList<CategoryEntity> categoryEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectCategoriesQuery());
    private ObservableList<MilitaryTermEntity> militaryTermData = FXCollections.observableArrayList();
    private ObservableList<VeteranHonorEntity> honorsData = FXCollections.observableArrayList();
    private ObservableList<FamilyMemberEntity> familyMembersData = FXCollections.observableArrayList();
    private ObservableList<DocumentEntity> documentsData = FXCollections.observableArrayList();
    private ObservableList<DistrictEntity> districtEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectDistrictsQuery());
    private ObservableList<DisplacementEntity> displacementData = FXCollections.observableArrayList();
    private ObservableList<RankEntity> rankEntities = FXCollections.observableArrayList(DatabaseWorker.executeSelectRanksQuery());
    private ObservableList<SubcategoryEntity> subcategoryEntities = FXCollections.observableArrayList();
    private ObservableList<WorkPlaceEntity> workPlacesData = FXCollections.observableArrayList();
    private ObservableList<VeteranWoundEntity> woundsData = FXCollections.observableArrayList();

    public void handleSelectCategory() {
        subcategoryField.setDisable(false);
        if (!categoryField.getSelectionModel().isEmpty()) {
            this.subcategoryEntities = FXCollections.observableArrayList(categoryField.getValue().getSubcategories());
            subcategoryField.setItems(subcategoryEntities);
        }
    }

    public void setVisibleFields() {
        if (isDeadCheckBox.isSelected()) {
            dateOfDeathLabel.setVisible(true);
            burialPlaceLabel.setVisible(true);
            dateOfDeathField.setVisible(true);
            burialPlaceField.setVisible(true);
        } else {
            dateOfDeathLabel.setVisible(false);
            burialPlaceLabel.setVisible(false);
            dateOfDeathField.setVisible(false);
            burialPlaceField.setVisible(false);
        }
    }

    @FXML
    private void cancelButtonClick() {
        dialogStage.close();
    }

    @FXML
    private void handleAddDisplacement() {
        DisplacementEntity displacementEntity = new DisplacementEntity();
        boolean addButtonClicked = showAddDisplacementDialog(displacementEntity);
        if (addButtonClicked) {
            displacementData.add(displacementEntity);
            displacementTable.refresh();
        }
    }

    @FXML
    private void handleAddDocument() {
        DocumentEntity documentEntity = new DocumentEntity();
        boolean addButtonClicked = showAddDocumentDialog(documentEntity);
        if (addButtonClicked) {
            documentsData.add(documentEntity);
            documentsTable.refresh();
        }
    }

    @FXML
    private void handleAddFamilyMember() {
        FamilyMemberEntity familyMemberEntity = new FamilyMemberEntity();
        boolean addButtonClicked = showAddFamilyMemberDialog(familyMemberEntity);
        if (addButtonClicked) {
            familyMembersData.add(familyMemberEntity);
            familyMembersTable.refresh();
        }
    }

    @FXML
    private void handleAddHonor() {
        VeteranHonorEntity veteranHonorEntity = new VeteranHonorEntity();
        boolean addButtonClicked = showAddHonorDialog(veteranHonorEntity);
        if (addButtonClicked) {
            honorsData.add(veteranHonorEntity);
            honorsTable.refresh();
        }
    }

    @FXML
    private void handleAddMilitaryTerm() {
        MilitaryTermEntity militaryTermEntity = new MilitaryTermEntity();
        boolean addButtonClicked = showAddMilitaryTermDialog(militaryTermEntity);
        if (addButtonClicked) {
            militaryTermData.add(militaryTermEntity);
            militaryTermTable.refresh();
        }
    }

    @FXML
    private void handleAddWorkPlace() {
        WorkPlaceEntity workPlaceEntity = new WorkPlaceEntity();
        boolean addButtonClicked = showAddWorkPlaceDialog(workPlaceEntity);
        if (addButtonClicked) {
            workPlacesData.add(workPlaceEntity);
            workPlaceTable.refresh();
        }
    }

    @FXML
    private void handleAddWound() {
        VeteranWoundEntity woundEntity = new VeteranWoundEntity();
        boolean addButtonClicked = showAddWoundDialog(woundEntity);
        if (addButtonClicked) {
            woundsData.add(woundEntity);
            woundsTable.refresh();
        }
    }

    @FXML
    private void handleDeleteDisplacement() { // TODO delete from db
        DisplacementEntity displacementEntity = displacementTable.getSelectionModel().getSelectedItem();
        int selectedIndex = displacementTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            displacementData.remove(selectedIndex);
            this.veteranEntity.removeDisplacement(displacementEntity);
            displacementTable.refresh();
        }
    }

    @FXML
    private void handleDeleteDocument() {// TODO delete from db
        DocumentEntity documentEntity = documentsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = documentsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            documentsData.remove(selectedIndex);
            this.veteranEntity.removeDocument(documentEntity);
            documentsTable.refresh();
        }
    }

    @FXML
    private void handleDeleteFamilyMember() { // TODO delete from db
        FamilyMemberEntity familyMemberEntity = familyMembersTable.getSelectionModel().getSelectedItem();
        int selectedIndex = familyMembersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            familyMembersData.remove(selectedIndex);
            this.veteranEntity.removeFamilyMember(familyMemberEntity);
            familyMembersTable.refresh();
        }
    }

    @FXML
    private void handleDeleteHonor() { // TODO delete from db
        VeteranHonorEntity veteranHonorEntity = honorsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = honorsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            honorsData.remove(selectedIndex);
            this.veteranEntity.removeVeteranHonor(veteranHonorEntity);
            honorsTable.refresh();
        }
    }

    @FXML
    private void handleDeleteMilitaryTerm() { // TODO delete from db
        MilitaryTermEntity militaryTermEntity = militaryTermTable.getSelectionModel().getSelectedItem();
        int selectedIndex = militaryTermTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            militaryTermData.remove(selectedIndex);
            this.veteranEntity.removeMilitaryTerm(militaryTermEntity);
            militaryTermTable.refresh();
        }
    }

    @FXML
    private void handleDeleteWorkPlace() {// TODO delete from db
        WorkPlaceEntity workPlaceEntity = workPlaceTable.getSelectionModel().getSelectedItem();
        int selectedIndex = workPlaceTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            workPlacesData.remove(selectedIndex);
            this.veteranEntity.removeWorkPlace(workPlaceEntity);
            workPlaceTable.refresh();
        }
    }

    @FXML
    private void handleDeleteWound() {// TODO delete from db
        VeteranWoundEntity woundEntity = woundsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = woundsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            woundsData.remove(selectedIndex);
            this.veteranEntity.removeWound(woundEntity);
            woundsTable.refresh();
        }
    }

    @FXML
    private void handleEditDisplacement() {
        DisplacementEntity displacementEntity = displacementTable.getSelectionModel().getSelectedItem();
        int selectedIndex = displacementTable.getSelectionModel().getSelectedIndex();
        if (displacementEntity != null) {
            boolean addButtonClicked = showAddDisplacementDialog(displacementEntity);
            if (addButtonClicked) {
                displacementData.set(selectedIndex, displacementEntity);
                displacementTable.refresh();
            }
        }
    }

    @FXML
    private void handleEditDocument() {
        DocumentEntity documentEntity = documentsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = documentsTable.getSelectionModel().getSelectedIndex();
        if (documentEntity != null) {
            boolean addButtonClicked = showAddDocumentDialog(documentEntity);
            if (addButtonClicked) {
                documentsData.set(selectedIndex, documentEntity);
                documentsTable.refresh();
            }
        }
    }

    @FXML
    private void handleEditFamilyMember() {
        FamilyMemberEntity familyMemberEntity = familyMembersTable.getSelectionModel().getSelectedItem();
        int selectedIndex = familyMembersTable.getSelectionModel().getSelectedIndex();
        if (familyMemberEntity != null) {
            boolean addButtonClicked = showAddFamilyMemberDialog(familyMemberEntity);
            if (addButtonClicked) {
                familyMembersData.set(selectedIndex, familyMemberEntity);
                familyMembersTable.refresh();
            }
        }
    }

    @FXML
    private void handleEditHonor() {
        VeteranHonorEntity veteranHonorEntity = honorsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = honorsTable.getSelectionModel().getSelectedIndex();
        if (veteranHonorEntity != null) {
            boolean addButtonClicked = showAddHonorDialog(veteranHonorEntity);
            if (addButtonClicked) {
                honorsData.set(selectedIndex, veteranHonorEntity);
                honorsTable.refresh();
            }
        }
    }

    @FXML
    private void handleEditMilitaryTerm() {
        MilitaryTermEntity militaryTermEntity = militaryTermTable.getSelectionModel().getSelectedItem();
        int selectedIndex = militaryTermTable.getSelectionModel().getSelectedIndex();
        if (militaryTermEntity != null) {
            boolean addButtonClicked = showAddMilitaryTermDialog(militaryTermEntity);
            if (addButtonClicked) {
                militaryTermData.set(selectedIndex, militaryTermEntity);
                militaryTermTable.refresh();
            }
        }
    }

    @FXML
    private void handleEditWorkPlace() {
        WorkPlaceEntity workPlaceEntity = workPlaceTable.getSelectionModel().getSelectedItem();
        int selectedIndex = workPlaceTable.getSelectionModel().getSelectedIndex();
        if (workPlaceEntity != null) {
            boolean addButtonClicked = showAddWorkPlaceDialog(workPlaceEntity);
            if (addButtonClicked) {
                workPlacesData.set(selectedIndex, workPlaceEntity);
                workPlaceTable.refresh();
            }
        }
    }

    @FXML
    private void handleEditWound() {
        VeteranWoundEntity woundEntity = woundsTable.getSelectionModel().getSelectedItem();
        int selectedIndex = woundsTable.getSelectionModel().getSelectedIndex();
        if (woundEntity != null) {
            boolean addButtonClicked = showAddWoundDialog(woundEntity);
            if (addButtonClicked) {
                woundsData.set(selectedIndex, woundEntity);
                woundsTable.refresh();
            }
        }
    }

    @FXML
    private void initialize() {
        nameOfHonorColumn.setCellValueFactory(new PropertyValueFactory<>("honor"));
        dateOfReceivingColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReceiving"));
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("decree"));

        militaryUnitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        countryOfMilitaryServiceColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        localityOfMilitaryServiceColumn.setCellValueFactory(new PropertyValueFactory<>("locality"));
        startOfMilitaryServiceColumn.setCellValueFactory(new PropertyValueFactory<>("startOfMilitaryService"));
        endOfMilitaryServiceColumn.setCellValueFactory(new PropertyValueFactory<>("endOfMilitaryService"));

        familyMembersRelationDegreeColumn.setCellValueFactory(new PropertyValueFactory<>("relationDegree"));
        familyMembersFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        familyMembersDateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        familyMembersAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        familyMembersPhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        arrivedDateColumn.setCellValueFactory(new PropertyValueFactory<>("arrivedDate"));
        arrivedPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("arrivedPlace"));
        decreasedDateColumn.setCellValueFactory(new PropertyValueFactory<>("decreasedDate"));
        decreasedPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("decreasedPlace"));

        localityOfWorkColumn.setCellValueFactory(new PropertyValueFactory<>("locality"));
        workOrganizationColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));
        workPositionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
        numberOfDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("hrNumber"));

        documentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        seriesOfDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("series"));
        numberOfDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        documentIssuedByColumn.setCellValueFactory(new PropertyValueFactory<>("issuedBy"));
        dateOfIssueDocumentColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        typeOfWoundColumn.setCellValueFactory(new PropertyValueFactory<>("woundType"));
        dateOfWoundColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        disabilityColumn.setCellValueFactory(new PropertyValueFactory<>("woundDisability"));

        districtField.setItems(districtEntities);
        militaryRankField.setItems(rankEntities);
        categoryField.setItems(categoryEntities);
        subcategoryField.setItems(subcategoryEntities);
    }

    private boolean isInputValid() {
        String errorMessage = "";
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText("Вам необходимо заполнить следующие поля : \n\n");
        if (caseNumberField.getText() == null || caseNumberField.getText().length() == 0) {
            errorMessage += " Номер дела. \n";
        }
        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += " Имя. \n";
        }
        if (secondNameField.getText() == null || secondNameField.getText().length() == 0) {
            errorMessage += " Фамилия. \n";
        }
        if (middleNameField.getText() == null || middleNameField.getText().length() == 0) {
            errorMessage += " Отсчество. \n";
        }
        if (dateOfBirthField.getValue() == null) {
            errorMessage += " Отсчество. \n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    @FXML
    private void loadVeteranPhoto() throws NullPointerException {
        Image image = new Image(FileLoader.loadPhoto());
        imagePhotoView.setImage(image);
    }

    @FXML
    private void saveButtonClick() {
        if (isInputValid()) {
            saveClicked = true;
            setNewVeteran();
            dialogStage.close();
        }
    }

    private void setDisplacements(List<DisplacementEntity> displacements) {
        for (DisplacementEntity displacementEntity : displacements) {
            this.veteranEntity.addDisplacement(displacementEntity);
        }
    }

    private void setDocuments(List<DocumentEntity> documents) {
        for (DocumentEntity documentEntity : documents) {
            this.veteranEntity.addDocument(documentEntity);
        }
    }

    private void setFamilyMembers(List<FamilyMemberEntity> familyMembers) {
        for (FamilyMemberEntity familyMemberEntity : familyMembers) {
            this.veteranEntity.addFamilyMember(familyMemberEntity);
        }
    }

    private void setMilitaryTerms(List<MilitaryTermEntity> militaryTerms) {
        for (MilitaryTermEntity militaryTermEntity : militaryTerms) {
            this.veteranEntity.addMilitaryTerm(militaryTermEntity);
        }
    }

    private void setNewVeteran() {
        this.veteranEntity.setCaseNumber(caseNumberField.getText());
        this.veteranEntity.setFirstName(firstNameField.getText());
        this.veteranEntity.setSecondName(secondNameField.getText());
        this.veteranEntity.setMiddleName(middleNameField.getText());
        this.veteranEntity.setDateOfBirth(dateOfBirthField.getValue());
        this.veteranEntity.setDistrict(districtField.getValue());

        this.veteranEntity.setCategory(categoryField.getValue());
        this.veteranEntity.setSubcategory(subcategoryField.getValue());
        this.veteranEntity.setMilitaryRank(militaryRankField.getValue());
        this.veteranEntity.setPosition(positionField.getText());
        this.veteranEntity.setSubdivision(militarySubdivisionField.getText());

        this.veteranEntity.setMarchingOrganization(marchingOrganizationField.getText());
        this.veteranEntity.setVillageExecutiveCommittee(villageExecutiveCommitteeField.getText());
        this.veteranEntity.setRegionalExecutiveCommittee(regionalExecutiveCommitteeField.getText());

        this.veteranEntity.setAddress(addressField.getText());
        this.veteranEntity.setRegistrationAddress(registrationAddressField.getText());
        this.veteranEntity.setPhoneNumber(phoneNumberField.getText());

        this.veteranEntity.setIsDead(isDeadCheckBox.isSelected());
        this.veteranEntity.setDateOfDeath(dateOfDeathField.getValue());
        this.veteranEntity.setBurialPlace(burialPlaceField.getText());

        this.veteranEntity.setPhoto(ImageConverter.convertImageToString(imagePhotoView.getImage()));

        setVeteranHonors(honorsData);
        setMilitaryTerms(militaryTermData);
        setFamilyMembers(familyMembersData);
        setDisplacements(displacementData);
        setWorkPlaces(workPlacesData);
        setDocuments(documentsData);
        setWounds(woundsData);
    }

    private void setVeteranHonors(List<VeteranHonorEntity> veteranHonors) {
        for (VeteranHonorEntity veteranHonorEntity : veteranHonors) {
            this.veteranEntity.addVeteranHonor(veteranHonorEntity);
        }
    }

    private void setWorkPlaces(List<WorkPlaceEntity> workPlaces) {
        for (WorkPlaceEntity workPlaceEntity : workPlaces) {
            this.veteranEntity.addWorkPlace(workPlaceEntity);
        }
    }

    private void setWounds(List<VeteranWoundEntity> wounds) {
        for (VeteranWoundEntity woundEntity : wounds) {
            this.veteranEntity.addWound(woundEntity);
        }
    }

    private boolean showAddDisplacementDialog(DisplacementEntity displacementEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/DisplacementDialog.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            DisplacementsDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setDisplacementEntity(displacementEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showAddDocumentDialog(DocumentEntity documentEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/DocumentDialog.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            DocumentsDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setDocumentEntity(documentEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showAddFamilyMemberDialog(FamilyMemberEntity familyMemberEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/FamilyMemberDialog.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FamilyMembersDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setFamilyMemberEntity(familyMemberEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showAddHonorDialog(VeteranHonorEntity veteranHonorEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/HonorDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            HonorsDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setHonorEntity(veteranHonorEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showAddMilitaryTermDialog(MilitaryTermEntity militaryTermEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/MilitaryTermDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            MilitaryTermDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setMilitaryTermEntity(militaryTermEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showAddWorkPlaceDialog(WorkPlaceEntity workPlaceEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/WorkPlaceDialog.fxml"));
            AnchorPane page = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(page);
            stage.setScene(scene);

            WorkPlaceDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setWorkPlaceEntity(workPlaceEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean showAddWoundDialog(VeteranWoundEntity woundEntity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/veterantabledialogs/WoundDialog.fxml"));
            AnchorPane pane = loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(dialogStage);
            Scene scene = new Scene(pane);
            stage.setScene(scene);

            WoundsDialogController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setWoundEntity(woundEntity);
            stage.showAndWait();
            return controller.isSaveClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    void setVeteranEntity(VeteranEntity veteranEntity) {
        this.veteranEntity = veteranEntity;

        caseNumberField.setText(this.veteranEntity.getCaseNumber());
        firstNameField.setText(this.veteranEntity.getFirstName());
        secondNameField.setText(this.veteranEntity.getSecondName());
        middleNameField.setText(this.veteranEntity.getMiddleName());
        dateOfBirthField.setValue(DateValidator.checkDate(this.veteranEntity.getDateOfBirth()));
        districtField.setValue(this.veteranEntity.getDistrict());

        categoryField.setValue(this.veteranEntity.getCategory());
        if (this.veteranEntity.getCategory() != null) {
            this.subcategoryEntities = FXCollections.observableArrayList(categoryField.getValue().getSubcategories());
            subcategoryField.setItems(subcategoryEntities);
        }
        subcategoryField.setValue(this.veteranEntity.getSubcategory());

        subcategoryField.setValue(this.veteranEntity.getSubcategory());

        militaryRankField.setValue(this.veteranEntity.getMilitaryRank());
        positionField.setText(this.veteranEntity.getPosition());
        militarySubdivisionField.setText(this.veteranEntity.getSubdivision());

        marchingOrganizationField.setText(this.veteranEntity.getMarchingOrganization());
        villageExecutiveCommitteeField.setText(this.veteranEntity.getVillageExecutiveCommittee());
        regionalExecutiveCommitteeField.setText(this.veteranEntity.getRegionalExecutiveCommittee());

        addressField.setText(this.veteranEntity.getAddress());
        registrationAddressField.setText(this.veteranEntity.getRegistrationAddress());
        phoneNumberField.setText(this.veteranEntity.getPhoneNumber());

        imagePhotoView.setImage(ImageConverter.convertStringToImage(this.veteranEntity.getPhoto()));

        isDeadCheckBox.selectedProperty().setValue(this.veteranEntity.isDead());
        if (isDeadCheckBox.isSelected()) {
            dateOfDeathLabel.setVisible(true);
            burialPlaceLabel.setVisible(true);
            dateOfDeathField.setVisible(true);
            burialPlaceField.setVisible(true);
            dateOfDeathField.setValue(DateValidator.checkDate(this.veteranEntity.getDateOfDeath()));
            burialPlaceField.setText(this.veteranEntity.getBurialPlace());
        }

        honorsData.addAll(this.veteranEntity.getVeteranHonors());
        honorsTable.setItems(honorsData);

        militaryTermData.addAll(this.veteranEntity.getMilitaryTerms());
        militaryTermTable.setItems(militaryTermData);

        familyMembersData.addAll(this.veteranEntity.getFamilyMembers());
        familyMembersTable.setItems(familyMembersData);

        displacementData.addAll(this.veteranEntity.getDisplacements());
        displacementTable.setItems(displacementData);

        workPlacesData.addAll(this.veteranEntity.getWorkPlaces());
        workPlaceTable.setItems(workPlacesData);

        documentsData.addAll(this.veteranEntity.getDocuments());
        documentsTable.setItems(documentsData);

        woundsData.addAll(this.veteranEntity.getWounds());
        woundsTable.setItems(woundsData);
    }

    boolean isSaveClicked() {
        return saveClicked;
    }
}
