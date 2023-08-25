package dao;

import metier.entity.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface ILibrairie {

    void ajouterLivre(Livre livre);
    void modifierLivre(Livre livre);
    void supprimerLivre(int id);
    Livre getLivre(int id);
    Livre getLivreByISBN(int isbn);
    List<Livre> getAllLivre();
    List<Livre> getLivrePMC(String mc);
    List<Livre> getLivrePDate(Date date1, Date date2);

    byte[] getImageData(int id);

    void ajouterAuteur(Auteur a);
    void modifierAuteur(Auteur a);
    void supprimerAuteur(int id);
    Auteur getAuteur(int id);
    Auteur getAuteurPMC(String mc);
    List<Auteur> getAllAuteurs();
    List<Auteur> getAuteursPMC(String mc);

    void ajouterGenre (Genre genre);
    void modifierGenre (Genre genre);
    void supprimerGenre (int id);
    Genre getGenre(int id);
    List<Genre> getAllGenres();
    List<Genre> getGenresPMC (String mc);

    void ajouterUtilisateur(Utilisateur u);
    void modifierUtilisateur(Utilisateur u);
    void supprimerUtilisateur(int id);
    Utilisateur getUtilisateur(int id);
    List<Utilisateur> getAllUtilisateur();
    List<Utilisateur> getUtilisateurPMC(String mc);

    void ajouterMembre(Membre m);
    void modifierMembre(Membre m);
    void supprimerMembre(int id);
    Membre getMembre(int id);
    List<Membre> getAllMembre();
    List<Membre> getMembrePMC(String mc);

}
