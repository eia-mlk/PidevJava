package controllers;

import entities.Avis;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.AvisService;

import java.sql.SQLException;

public class AvisCardController {

    @FXML
    private Label UserLB;

    @FXML
    private Label dateLB;

    @FXML
    private Label descLB;

    AvisService as = new AvisService();


    Avis selectedAvis;

    public void setData(Avis avis) throws SQLException {
        this.selectedAvis =avis;
       String name = as.GetUserNameByID(avis.getUser_id());
        UserLB.setText(name);
        descLB.setText(avis.getCommentaire());

    }
}
