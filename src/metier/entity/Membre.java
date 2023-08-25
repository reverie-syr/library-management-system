package metier.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.sql.Blob;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Membre {

    private int id;
    private String nom;
    private String prenom;
    private int cin;
    private int numTel;
    private java.sql.Date dateDeNaissance;
    private String email;
    private String genre;
    private java.sql.Date dateAdhesion;
    private String adresse;
    private File photo;

    public Membre(String nom, String prenom,
                  int cin, int numTel, Date dateDeNaissance,
                  String email, String genre, Date dateAdhesion, String adresse, File photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.numTel = numTel;
        this.dateDeNaissance = dateDeNaissance;
        this.email = email;
        this.genre = genre;
        this.dateAdhesion = dateAdhesion;
        this.adresse = adresse;
        this.photo = photo;
    }

    public Membre(String nom, String prenom,
                  int cin, int numTel, Date dateDeNaissance,
                  String email, String genre, Date dateAdhesion, String adresse){
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.numTel = numTel;
        this.dateDeNaissance = dateDeNaissance;
        this.email = email;
        this.genre = genre;
        this.dateAdhesion = dateAdhesion;
        this.adresse = adresse;

    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Membre)) return false;
        final Membre other = (Membre) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$nom = this.getNom();
        final Object other$nom = other.getNom();
        if (!Objects.equals(this$nom, other$nom)) return false;
        final Object this$prenom = this.getPrenom();
        final Object other$prenom = other.getPrenom();
        if (!Objects.equals(this$prenom, other$prenom)) return false;
        final Object this$cin = this.getCin();
        final Object other$cin = other.getCin();
        if (!Objects.equals(this$cin, other$cin)) return false;
        final Object this$numTel = this.getNumTel();
        final Object other$numTel = other.getNumTel();
        if (!Objects.equals(this$numTel, other$numTel)) return false;
        final Object this$dateDeNaissance = this.getDateDeNaissance();
        final Object other$dateDeNaissance = other.getDateDeNaissance();
        if (!Objects.equals(this$dateDeNaissance, other$dateDeNaissance))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (!Objects.equals(this$email, other$email)) return false;
        final Object this$genre = this.getGenre();
        final Object other$genre = other.getGenre();
        if (!Objects.equals(this$genre, other$genre)) return false;
        final Object this$dateAdhesion = this.getDateAdhesion();
        final Object other$dateAdhesion = other.getDateAdhesion();
        if (!Objects.equals(this$dateAdhesion, other$dateAdhesion))
            return false;
        final Object this$adresse = this.getAdresse();
        final Object other$adresse = other.getAdresse();
        if (!Objects.equals(this$adresse, other$adresse)) return false;
        final Object this$photo = this.getPhoto();
        final Object other$photo = other.getPhoto();
        if (!Objects.equals(this$photo, other$photo)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Membre;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $nom = this.getNom();
        result = result * PRIME + ($nom == null ? 43 : $nom.hashCode());
        final Object $prenom = this.getPrenom();
        result = result * PRIME + ($prenom == null ? 43 : $prenom.hashCode());
        final Object $cin = this.getCin();
        result = result * PRIME + ($cin == null ? 43 : $cin.hashCode());
        final Object $numTel = this.getNumTel();
        result = result * PRIME + ($numTel == null ? 43 : $numTel.hashCode());
        final Object $dateDeNaissance = this.getDateDeNaissance();
        result = result * PRIME + ($dateDeNaissance == null ? 43 : $dateDeNaissance.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $genre = this.getGenre();
        result = result * PRIME + ($genre == null ? 43 : $genre.hashCode());
        final Object $dateAdhesion = this.getDateAdhesion();
        result = result * PRIME + ($dateAdhesion == null ? 43 : $dateAdhesion.hashCode());
        final Object $adresse = this.getAdresse();
        result = result * PRIME + ($adresse == null ? 43 : $adresse.hashCode());
        final Object $photo = this.getPhoto();
        result = result * PRIME + ($photo == null ? 43 : $photo.hashCode());
        return result;
    }
}
