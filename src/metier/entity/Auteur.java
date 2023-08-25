package metier.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Auteur {

    private int id;
    private String nom;
    private String prenom;
    private String expertise;
    private String informations;


    public Auteur(String nom, String prenom, String expertise, String informations){
        this.nom=nom;
        this.prenom=prenom;
        this.expertise=expertise;
        this.informations=informations;
    }

    public String toString() {
        return this.prenom.substring(0,1).toUpperCase()+ this.prenom.substring(1)
                + " " + this.nom.substring(0,1).toUpperCase()+ this.nom.substring(1);
    }
}
