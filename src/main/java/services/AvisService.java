package services;

import entities.Avis;
import entities.Hote;
import interfaces.IService;
import javafx.fxml.Initializable;
import utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvisService implements IService<Avis> {

    Connection cnx;
    public AvisService() {
        cnx = MyDatabase.getInstance().getConnection();
    }
    @Override
    public void add(Avis avis) throws SQLException {
        String req = "INSERT INTO avis(hote_id  , user_id , commentaire, note, datepublication) VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, avis.getHote_id());
        ps.setInt(2, avis.getHote_id());
        ps.setString(3, avis.getCommentaire());
        ps.setInt(4, avis.getNote());
        ps.setDate(5, avis.getDatepublication());
        ps.executeUpdate();
    }

    @Override
    public void update(Avis avis) throws SQLException {

    }

    @Override
    public void delete(Avis avis) throws SQLException {
        String req = "DELETE FROM avis WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, avis.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Avis> read() throws SQLException {
        List<Avis> AvisList = new ArrayList<>();
        String req = "SELECT * FROM avis";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Avis avis = new Avis(
                    rs.getInt("id"),
                    rs.getInt("hote_id"),
                    rs.getInt("user_id"),
                    rs.getInt("note"),
                    rs.getDate("datepublication"),
                    rs.getString("commentaire")
            );
            AvisList.add(avis);
        }
        System.out.println(AvisList);
        return AvisList;
    }

    public List<Hote> readHote() throws SQLException {
        List<Hote> HoteList = new ArrayList<>();
        String req = "SELECT * FROM hote";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Hote Hotes = new Hote(
                    rs.getInt("id"),
                    rs.getString("nom")
            );
            HoteList.add(Hotes);
        }
        System.out.println(HoteList);
        return HoteList;
    }

    public String GetUserNameByID(int id) throws SQLException {
        String req = "SELECT nom FROM user WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString("nom");
        }
        return null;
    }


    public List<Avis> readWhereHoteID(int hote_id) throws SQLException {
        List<Avis> AvisList = new ArrayList<>();
        String req = "SELECT * FROM avis WHERE hote_id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, hote_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Avis avis = new Avis(
                    rs.getInt("id"),
                    rs.getInt("hote_id"),
                    rs.getInt("user_id"),
                    rs.getInt("note"),
                    rs.getDate("datepublication"),
                    rs.getString("commentaire")
            );
            AvisList.add(avis);
        }
        System.out.println(AvisList);
        return AvisList;
    }
}
