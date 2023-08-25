package metier.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.sql.Date;

@Getter
@Setter
@EqualsAndHashCode
public class Livre {

    private int ID;
    private int ISBN;
    private String titre;
    private Auteur auteur;
    private Genre genre;
    private int quantite;
    private String editeur;
    private double prix;
    private java.sql.Date date_reception;
    private java.sql.Date date_de_retour;
    private String description;
    private String note;
    private File photo;
    private boolean isAvailable;
    private Membre membre;
    private Etat etat;

    public Livre(int ISBN,String titre, Auteur auteur, Genre genre, int quantite, String editeur, double prix, Date date_reception,
                 String description, File photo) {
        this.ISBN= ISBN;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.quantite = quantite;
        this.editeur = editeur;
        this.prix = prix;
        this.date_reception= date_reception;
        this.description = description;
        this.photo = photo;
        this.isAvailable = true;
    }

    public Livre(int ISBN,String titre, Auteur auteur, Genre genre, int quantite, String editeur, double prix, Date date_reception,
                 String description) {
        this.ISBN= ISBN;
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.quantite = quantite;
        this.editeur = editeur;
        this.prix = prix;
        this.date_reception= date_reception;
        this.description = description;
        this.isAvailable = true;
    }
    public Livre(String titre, Auteur auteur, Genre genre, int quantite, String editeur, double prix, Date date_reception,
                 String description) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.quantite = quantite;
        this.editeur = editeur;
        this.prix = prix;
        this.date_reception= date_reception;
        this.description = description;
        this.photo = photo;
        this.isAvailable = true;
    }
    public Livre(String titre, Auteur auteur, Genre genre, int quantite, String editeur, double prix, Date date_reception,
                 String description, File photo) {
        this.titre = titre;
        this.auteur = auteur;
        this.genre = genre;
        this.quantite = quantite;
        this.editeur = editeur;
        this.prix = prix;
        this.date_reception= date_reception;
        this.description = description;
        this.photo = photo;
        this.isAvailable = true;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Etat getEtat() {
        return etat;
    }

    @Override
    public String toString() {
        return String.format("Titre: %s, Auteur: %s, Editeur: %s, Photo: %s", titre, auteur, editeur, photo.getAbsolutePath());
    }
}
