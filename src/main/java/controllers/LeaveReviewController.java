package controllers;

import entities.Hote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LeaveReviewController implements Initializable {

    @FXML
    private ComboBox<Integer> NoteCB;

    @FXML
    private TextField commentaireLB;

    @FXML
    private Label hoteLB;

    Hote selectedHote;

    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void Rate(ActionEvent event) {

    }


    void initData(Hote h){
        this.selectedHote = h;
        hoteLB.setText(h.getNom());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 1; i <= 5; i++) {
            NoteCB.getItems().add(i);
        }
    }
}
