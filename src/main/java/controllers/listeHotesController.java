package controllers;

import entities.Hote;
import entities.Voyage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.AvisService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class listeHotesController implements Initializable {

    @FXML
    private HBox cardLayout;


    private List<Hote> hoteList;

    AvisService as = new AvisService();


    public void refresh() {


        hoteList = new ArrayList<>(allHotes());

        // Create a GridPane for arranging cards
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Adjust spacing between cards as needed
        gridPane.setVgap(10);

        // Counter to track the number of cards added in the current row
        int colIndex = 0;
        int rowIndex = 0;

        for (Hote hote : hoteList) {
            try {
                // Load the card layout with the product
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/hoteCard.fxml"));
                AnchorPane box = fxmlLoader.load();

                HoteCardController cardController = fxmlLoader.getController();

                cardController.setData(hote);

                // Add the card to the GridPane, considering max 3 elements per row
                gridPane.add(box, colIndex, rowIndex);
                colIndex++;

                // Move to the next row if the maximum number of elements per row is reached
                if (colIndex >= 2) {
                    colIndex = 0;
                    rowIndex++;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                // Handle exception
            }
        }

        // Clear the existing cards and add the GridPane for rendering
        cardLayout.getChildren().clear();
        cardLayout.getChildren().add(gridPane);
    }

    private List<Hote> allHotes() {
        List<Hote> HoteList = new ArrayList<>();
        try {
            HoteList = as.readHote();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle exception
        }
        return HoteList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }
}
