package entities;

import java.sql.Date;

public class Avis {
    int id,hote_id,user_id,note;
    Date datepublication;
    String commentaire;

    public Avis(int id, int hote_id, int user_id, int note, Date datepublication, String commentaire) {
        this.id = id;
        this.hote_id = hote_id;
        this.user_id = user_id;
        this.note = note;
        this.datepublication = datepublication;
        this.commentaire = commentaire;
    }

    public Avis(int hote_id, int user_id, int note, Date datepublication, String commentaire) {
        this.hote_id = hote_id;
        this.user_id = user_id;
        this.note = note;
        this.datepublication = datepublication;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHote_id() {
        return hote_id;
    }

    public void setHote_id(int hote_id) {
        this.hote_id = hote_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Date getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Date datepublication) {
        this.datepublication = datepublication;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Avis{" +
                "id=" + id +
                ", hote_id=" + hote_id +
                ", user_id=" + user_id +
                ", note=" + note +
                ", datepublication=" + datepublication +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
