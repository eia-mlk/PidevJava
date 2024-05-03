package entities;

public class Hote {

    int id;
    String nom;

    public Hote(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hote{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
