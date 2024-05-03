package entities;

import java.sql.Date;

public class Reclamation {
    int id,user_id,voyage_id;
    String titre,priorite,image,status,description ;
    Date createddate;

    public Reclamation(int id, int user_id, int voyage_id, String titre, String priorite, String image, String status, String description, Date createddate) {
        this.id = id;
        this.user_id = user_id;
        this.voyage_id = voyage_id;
        this.titre = titre;
        this.priorite = priorite;
        this.image = image;
        this.status = status;
        this.description = description;
        this.createddate = createddate;
    }

    public Reclamation(int user_id, int voyage_id, String titre, String priorite, String image, String status, String description, Date createddate) {
        this.user_id = user_id;
        this.voyage_id = voyage_id;
        this.titre = titre;
        this.priorite = priorite;
        this.image = image;
        this.status = status;
        this.description = description;
        this.createddate = createddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVoyage_id() {
        return voyage_id;
    }

    public void setVoyage_id(int voyage_id) {
        this.voyage_id = voyage_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", voyage_id=" + voyage_id +
                ", titre='" + titre + '\'' +
                ", priorite='" + priorite + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", createddate=" + createddate +
                '}';
    }
}
