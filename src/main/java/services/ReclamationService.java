package services;

import entities.Avis;
import entities.Reclamation;
import entities.Voyage;
import interfaces.IService;
import javafx.fxml.Initializable;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReclamationService implements IService<Reclamation> {

    Connection cnx;

    public ReclamationService() {
        cnx = MyDatabase.getInstance().getConnection();
    }


    @Override
    public void add(Reclamation reclamation) throws SQLException {
        String req = "INSERT INTO reclamation(user_id  , voyage_id , titre, priorite, createddate, image, status, description) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, reclamation.getUser_id());
        ps.setInt(2, reclamation.getVoyage_id());
        ps.setString(3, reclamation.getTitre());
        ps.setString(4, reclamation.getPriorite());
        ps.setDate(5, reclamation.getCreateddate());
        ps.setString(6, reclamation.getImage());
        ps.setString(7, reclamation.getStatus());
        ps.setString(8, reclamation.getDescription());
        ps.executeUpdate();
    }

    @Override
    public void update(Reclamation reclamation) throws SQLException {
        String req = "UPDATE reclamation SET user_id = ?, voyage_id = ?, titre = ?, priorite = ?, createddate = ?, image = ?, status = ?, description = ? WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, reclamation.getUser_id());
        ps.setInt(2, reclamation.getVoyage_id());
        ps.setString(3, reclamation.getTitre());
        ps.setString(4, reclamation.getPriorite());
        ps.setDate(5, reclamation.getCreateddate());
        ps.setString(6, reclamation.getImage());
        ps.setString(7, reclamation.getStatus());
        ps.setString(8, reclamation.getDescription());
        ps.setInt(9, reclamation.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(Reclamation reclamation) throws SQLException {
        String req = "DELETE FROM reclamation WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, reclamation.getId());
        ps.executeUpdate();

    }

    @Override
    public List<Reclamation> read() throws SQLException {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM reclamation";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Reclamation rec = new Reclamation(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("voyage_id"),
                    rs.getString("titre"),
                    rs.getString("priorite"),
                    rs.getString("image"),
                    rs.getString("status"),
                    rs.getString("description"),
                    rs.getDate("createddate")
            );
            reclamations.add(rec);
        }
        System.out.println(reclamations);
        return reclamations;
    }


    public void resolve(Reclamation rec) throws SQLException {

        String req = "UPDATE reclamation SET status = ? WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        if (Objects.equals(rec.getStatus(), "en cours")) {
            ps.setString(1, "résolu");
            System.out.println("ress");
        } else {
            ps.setString(1, "en cours");
            System.out.println("msh res");
        }
        ps.setInt(2, rec.getId());
        System.out.println(rec.getId());
        ps.executeUpdate();
    }


    public List<Voyage> readVoy() throws SQLException {
        List<Voyage> Voyages = new ArrayList<>();
        String req = "SELECT * FROM voyage";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Voyage voyage = new Voyage(
                    rs.getInt("id"),
                    rs.getInt("propriete_id"),
                    rs.getInt("nbr_personne"),
                    rs.getString("image"),
                    rs.getString("description"),
                    rs.getString("title"),
                    rs.getDouble("prixtotal"),
                    rs.getDate("datedebut"),
                    rs.getDate("datefin")
            );
            Voyages.add(voyage);
        }
        System.out.println(Voyages);
        return Voyages;
    }
}

