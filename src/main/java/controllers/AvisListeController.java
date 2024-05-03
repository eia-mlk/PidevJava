package controllers;

import entities.Avis;
import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.AvisService;
import services.ReclamationService;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvisListeController implements Initializable {

    @FXML
    private TableColumn<?, ?> commCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> hoteCol;

    @FXML
    private TableColumn<?, ?> noteCol;

    @FXML
    private TableView<Avis> tabAvis;

    @FXML
    private TableColumn<?, ?> userCol;

    AvisService as = new AvisService();


    List<Avis> AvisList;


    public void ListeAvis() throws SQLException {

        AvisService as = new AvisService();
        userCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        hoteCol.setCellValueFactory(new PropertyValueFactory<>("hote_id"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("datepublication"));
        commCol.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

        boolean deleteColumnExists = false;

        for (TableColumn column : tabAvis.getColumns()) {
            if (column.getText().equals("Action")) {
                deleteColumnExists = true;
                break;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<Avis, Void> deleteColumn = new TableColumn<>("Action");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<Avis, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Avis c = getTableView().getItems().get(getIndex());
                            AvisService cs = new AvisService();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Delete Avis");
                            alert.setHeaderText("Are you sure you want to delete this Avis?");
                            alert.setContentText("This action cannot be undone.");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try {
                                    System.out.println(c);
                                    cs.delete(c);

                                    refreshTable();
                                } catch (SQLException ex) {
                                    Logger.getLogger(ReclamationListeController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {

                                alert.close();
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(deleteButton);
                        }
                    }
                };
            });

            tabAvis.getColumns().add(deleteColumn);
        }

        List<Avis> list = as.read();
        System.out.println(list);
        ObservableList<Avis> observableList = FXCollections.observableArrayList(list);
        tabAvis.setItems(observableList);


    }

    public void refreshTable() {
        try {
            AvisList = new AvisService().read();
            tabAvis.setItems(FXCollections.observableArrayList(AvisList));
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ListeAvis();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
