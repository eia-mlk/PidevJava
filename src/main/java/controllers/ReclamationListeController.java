package controllers;

import entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ReclamationService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReclamationListeController implements Initializable {

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> descCol;

    @FXML
    private TableColumn<?, ?> prioCol;

    @FXML
    private TableColumn<?, ?> statusCol;

    @FXML
    private TableView<Reclamation> tableRec;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> userCol;

    @FXML
    private TableColumn<?, ?> voyageCol;

    private List<Reclamation> reclamationsList;

    ReclamationService rs = new ReclamationService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ListeReclamations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ListeReclamations() throws SQLException {

        ReclamationService cs = new ReclamationService();
        // Initialize table columns
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        voyageCol.setCellValueFactory(new PropertyValueFactory<>("voyage_id"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        prioCol.setCellValueFactory(new PropertyValueFactory<>("priorite"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("createddate"));


        boolean deleteColumnExists = false;
        boolean ModifyColumnExists = false;

        for (TableColumn column : tableRec.getColumns()) {
            if (column.getText().equals("Action")) {
                deleteColumnExists = true;
                break;
            }
        }

        if (!deleteColumnExists) {
            TableColumn<Reclamation, Void> deleteColumn = new TableColumn<>("Action");
            deleteColumn.setCellFactory(column -> {
                return new TableCell<Reclamation, Void>() {
                    private final Button deleteButton = new Button("Delete");

                    {
                        deleteButton.setOnAction(event -> {
                            Reclamation c = getTableView().getItems().get(getIndex());
                            ReclamationService cs = new ReclamationService();
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Delete Reclamation");
                            alert.setHeaderText("Are you sure you want to delete this Reclamation?");
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

            tableRec.getColumns().add(deleteColumn);
        }

        if (!ModifyColumnExists) {
            TableColumn<Reclamation, Void> modifyColumn = new TableColumn<>("Update");
            modifyColumn.setCellFactory(column -> {
                return new TableCell<Reclamation, Void>() {
                    private final Button modifyButton = new Button("Traiter");

                    {
                        modifyButton.setOnAction(event -> {
                            Reclamation selectedReclamation = getTableView().getItems().get(getIndex());
                            try {
                                rs.resolve(selectedReclamation);
                                refreshTable();
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Validé");
                                alert.setHeaderText(null);
                                alert.setContentText("Reclamation traité !");
                                alert.getButtonTypes().clear(); // Remove existing button types
                                alert.getButtonTypes().add(ButtonType.OK); // Add only OK button type
                                alert.showAndWait();
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }

                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(modifyButton);
                        }
                    }
                };
            });

            tableRec.getColumns().add(modifyColumn);
        }

        // Load voyages from the database
        List<Reclamation> list = cs.read();
        System.out.println(list);
        ObservableList<Reclamation> observableList = FXCollections.observableArrayList(list);
        tableRec.setItems(observableList);

    }

    public void refreshTable() {
        try {
            reclamationsList = new ReclamationService().read();
            tableRec.setItems(FXCollections.observableArrayList(reclamationsList));
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationListeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
