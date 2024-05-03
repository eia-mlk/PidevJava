package services;

import entities.Avis;
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
}
