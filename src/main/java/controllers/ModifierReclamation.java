package controllers;

import entities.Reclamation;
import entities.Voyage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import services.ReclamationService;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ModifierReclamation implements Initializable {

    @FXML
    private TextField descTF;

    @FXML
    private ComboBox<String> prioCB;

    @FXML
    private TextField titreTF;

    @FXML
    private ComboBox<Voyage> voyageCB;

    Reclamation selectedReclamation;

    private ObservableList<Voyage> VoyageList; // Define an ObservableList to hold voyages

    private File selectedFile; // Field to store the selected file
    ReclamationService rs = new ReclamationService();

    @FXML
    void Modifier(ActionEvent event) throws SQLException, IOException {
        String desc = descTF.getText();
        String titre = titreTF.getText();
        String prio = prioCB.getValue();
        String picture = "";
        picture = selectedFile != null ? selectedFile.toURI().toString() : "";
        Voyage selectedVoyage = (Voyage) voyageCB.getValue();

        if (selectedVoyage == null) {
            // Show an error message if no category is selected
            System.out.println("No voyage selected.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a voyage");
            alert.showAndWait();
            return; // Exit the method if no category is selected
        }
        if (titre.isEmpty() || desc.isEmpty() || titre.isEmpty() || prio.isEmpty()  || Objects.equals(picture, "") ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        } else if (!isValidString(titre) || !isValidString(desc)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid information.");
            alert.showAndWait();
        } else {
            java.util.Date todayUtil = new java.util.Date();
            Date today = new Date(todayUtil.getTime());

            Reclamation rec = new Reclamation(selectedReclamation.getId(),1,selectedVoyage.getId(),titre,prioCB.getValue(),picture,"en cours",desc,today);
            rs.update(rec);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Crée");
            alert.setHeaderText(null);
            alert.setContentText("Reclamation Crée !");
            alert.getButtonTypes().clear(); // Remove existing button types
            alert.getButtonTypes().add(ButtonType.OK); // Add only OK button type
            alert.showAndWait();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/myReclamations.fxml"));
            Parent root = loader.load();
            Scene scene = titreTF.getScene();
            if (scene != null) {
                Stage currentStage = (Stage) scene.getWindow();
                currentStage.close();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        }

    }

    @FXML
    void chooseFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show open file dialog
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String imagePath = selectedFile.toURI().toString();
            System.out.println("Selected file: " + imagePath);
        }
    }

    void initData(Reclamation rec){
        this.selectedReclamation = rec;
        descTF.setText(rec.getDescription());
        titreTF.setText(rec.getTitre());


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            VoyageList = FXCollections.observableArrayList(rs.readVoy()); // Fetch voyages and populate list
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        voyageCB.setItems(VoyageList);
        voyageCB.setValue(null);
        voyageCB.setCellFactory(cell -> new ModifierReclamation.CategoryCellFactory());
        voyageCB.setConverter(new ModifierReclamation.CategoryStringConverter());

        List<String> priorities = Arrays.asList("faible", "eleve", "moyenne");
        prioCB.setItems(FXCollections.observableArrayList(priorities));
    }

    private static class CategoryCellFactory extends javafx.scene.control.ListCell<Voyage> {

        @Override
        protected void updateItem(Voyage voy, boolean empty) {
            super.updateItem(voy, empty);
            if (voy != null) {
                setText(voy.getTitle()); // Set the cell text to voyage title
            } else {
                setText(null);
            }
        }
    }
    private static class CategoryStringConverter extends StringConverter<Voyage> {

        @Override
        public String toString(Voyage voy) {
            return voy != null ? voy.getTitle() : null; // Convert voyage to its title
        }

        @Override
        public Voyage fromString(String string) {
            // Not needed for this example
            return null;
        }
    }


    private boolean isValidInt(String value) {
        // Check if the value is a valid integer
        return value.matches("-?\\d+");
    }

    private boolean isValidString(String name) {
        // Check if the name contains only letters and has length between 2 and 50
        return name.matches("^[a-zA-Z\\s]{2,50}$");
    }



}
