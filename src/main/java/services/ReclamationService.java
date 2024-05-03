package services;

import entities.Avis;
import entities.Reclamation;
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

    }

    @Override
    public void update(Reclamation reclamation) throws SQLException {

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
}

