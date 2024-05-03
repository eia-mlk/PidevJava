package controllers;

import entities.Hote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HoteCardController {


    Hote hote;
    @FXML
    private Label nomHote;
    @FXML
    void LeaveReview(ActionEvent event) {
        try {
            Stage stage = (Stage) nomHote.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/leaveReview.fxml"));
            Parent root = loader.load();

            LeaveReviewController controller = loader.getController();
            controller.initData(hote);


            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoToAvis(ActionEvent event) {
        try {
            Stage stage = (Stage) nomHote.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/avisListeFront.fxml"));
            Parent root = loader.load();

            AvisListeFrontController controller = loader.getController();
           controller.setHoteId(hote.getId());


            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setData(Hote hote) {
        this.hote = hote;
        nomHote.setText(hote.getNom());

    }
}
