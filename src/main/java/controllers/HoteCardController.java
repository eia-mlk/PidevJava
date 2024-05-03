package controllers;

import entities.Hote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class HoteCardController {


    Hote hote;
    @FXML
    private Label nomHote;

    @FXML
    void GoToAvis(ActionEvent event) {

    }


    public void setData(Hote hote) {
        this.hote = hote;
        nomHote.setText(hote.getNom());

    }
}
