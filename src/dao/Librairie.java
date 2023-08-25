package dao;

import metier.entity.*;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Librairie implements ILibrairie {
    private static ILibrairie Ilibrairie;
    Connection cx = SingletonConnection.getInstance();

    private Librairie() {}

    public static ILibrairie getLibrairie() {
        if ( Ilibrairie== null) {
            Ilibrairie = new Librairie();
        }

        return Ilibrairie;
    }

    @Override
    public void ajouterLivre(Livre livre) {

        try {
            PreparedStatement st = cx.prepareStatement("insert into livre(ISBN, Titre, idAuteur, idGenre, Quantite, Editeur, Prix, DateRCP," +
                    " Description, Photo) values(?, ?, ?,?,?,?,?,?,?,?)");

            st.setInt(1, livre.getISBN());
            st.setString(2, livre.getTitre());
            st.setInt(3, livre.getAuteur().getId());
            st.setInt(4, livre.getGenre().getId());
            st.setInt(5, livre.getQuantite());
            st.setString(6, livre.getEditeur());
            st.setDouble(7, livre.getPrix());
            st.setDate(8, livre.getDate_reception());
            st.setString(9, livre.getDescription());
            st.setBlob(10, new FileInputStream(livre.getPhoto().getAbsolutePath()));

            st.executeUpdate();
        } catch (SQLException | FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void modifierLivre(Livre livre) {

        try {
            PreparedStatement st = cx.prepareStatement("update livre set ISBN= ?, Titre= ?, idAuteur= ?, idGenre= ?, " +
                    "`Quantite`= ?, Editeur= ?, Prix= ?, `DateRCP`= ?, Description= ?, Photo= ?, Etat= ? , Note= ?, DateRetour = ?, Disponibilité = ? where id= ?");

            st.setInt(1, livre.getISBN());
            st.setString(2, livre.getTitre());
            st.setInt(3, livre.getAuteur().getId());
            st.setInt(4, livre.getGenre().getId());
            st.setInt(5, livre.getQuantite());
            st.setString(6, livre.getEditeur());
            st.setDouble(7, livre.getPrix());
            st.setDate(8, livre.getDate_reception());
            st.setString(9, livre.getDescription());
            st.setBlob(10, new FileInputStream(livre.getPhoto().getAbsolutePath()));
            st.setString(11, livre.getEtat().toString());
            st.setString(12, livre.getNote());
            st.setDate(13, livre.getDate_de_retour());
            st.setBoolean(14, livre.getIsAvailable());
            st.setInt(15, livre.getID());

            st.executeUpdate();
        } catch (FileNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }

    }
    @Override
    public void supprimerLivre(int id) {
        try {
            PreparedStatement st = cx.prepareStatement("delete from livre where id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Livre getLivre(int id) {
        Livre livre = null;
        InputStream inputStream;
        OutputStream outputStream;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from livre where id= ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inputStream = rs.getBinaryStream("photo");

                if (inputStream == null) {
                    livre = new Livre(
                            rs.getInt(2),
                            rs.getString(3),
                            getAuteur(rs.getInt(4)),
                            getGenre(rs.getInt(5)),
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getDouble(9),
                            rs.getDate(10),
                            rs.getString(12)
                    );
                    livre.setID(rs.getInt(1));
                    livre.setMembre(getMembre(rs.getInt(6)));

                    livre.setPhoto(new File("src\\images\\noimage.jpg"));

                    return livre;
                }
                File tempFile = File.createTempFile("livre", ".png");
                outputStream = new FileOutputStream(tempFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                livre = new Livre(
                        rs.getInt(2),
                        rs.getString(3),
                        getAuteur(rs.getInt(4)),
                        getGenre(rs.getInt(5)),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDouble(9),
                        rs.getDate(10),
                        rs.getString(12),
                        tempFile
                );
                livre.setID(rs.getInt(1));
                livre.setMembre(getMembre(rs.getInt(6)));

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return livre;
    }

    @Override
    public Livre getLivreByISBN(int isbn) {
        Livre livre = null;
        InputStream inputStream;
        OutputStream outputStream;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from livre where ISBN= ?");
            ps.setInt(1, isbn);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inputStream = rs.getBinaryStream("photo");

                if (inputStream == null) {
                    livre = new Livre(
                            rs.getString(3),
                            getAuteur(rs.getInt(4)),
                            getGenre(rs.getInt(5)),
                            rs.getInt(7),
                            rs.getString(8),
                            rs.getDouble(9),
                            rs.getDate(10),
                            rs.getString(12)
                    );
                    livre.setID(rs.getInt(1));
                    livre.setISBN(rs.getInt(2));
                    livre.setMembre(getMembre(rs.getInt(6)));

                    livre.setPhoto(new File("src\\images\\noimage.jpg"));

                    return livre;
                }
                File tempFile = File.createTempFile("livre", ".png");
                outputStream = new FileOutputStream(tempFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                livre = new Livre(
                        rs.getString(3),
                        getAuteur(rs.getInt(4)),
                        getGenre(rs.getInt(5)),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDouble(9),
                        rs.getDate(10),
                        rs.getString(12),
                        tempFile
                );
                livre.setID(rs.getInt(1));
                livre.setISBN(rs.getInt(2));
                livre.setMembre(getMembre(rs.getInt(6)));

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return livre;
    }

    @Override
    public List<Livre> getAllLivre() {
        List<Livre> list = new ArrayList<>();
        try {
            PreparedStatement ps = cx.prepareStatement("select * from livre");
            ResultSet rs = ps.executeQuery();
            InputStream inputStream;
            OutputStream outputStream;

            while (rs.next()) {
                File file = new File("src\\images\\livre.png");

                inputStream = rs.getBinaryStream(10);
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                Livre livre = new Livre(
                        rs.getInt(2),
                        rs.getString(3),
                        getAuteur(rs.getInt(4)),
                        getGenre(rs.getInt(5)),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDouble(9),
                        rs.getDate(10),
                        rs.getString(12),
                        file
                );
                livre.setID(rs.getInt(1));
                livre.setDate_de_retour(rs.getDate(11));
                livre.setEtat(Etat.valueOf((rs.getString(13) == null)? "NULL": rs.getString(13)));
                livre.setMembre(getMembre(rs.getInt(6)));
                livre.setNote(rs.getString(14));
                livre.setIsAvailable(rs.getBoolean(15));

                list.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Livre> getLivrePMC(String mc) {
        Livre livre = null;
        List<Livre> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("SELECT * FROM livre " +
                    "WHERE titre LIKE ? " +
                    "OR idAuteur IN (SELECT id FROM auteur WHERE nom LIKE ?)");
            ps.setString(1, "%" + mc + "%"); // search for mc in titre
            ps.setString(2, "%" + mc + "%"); // search for mc in nom

            ResultSet rs = ps.executeQuery();

            InputStream inputStream;
            OutputStream outputStream;

            while (rs.next()) {
                File file = new File("src\\images\\livre.png");

                inputStream = rs.getBinaryStream(10);
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                livre = new Livre(
                        rs.getInt(2),
                        rs.getString(3),
                        getAuteur(rs.getInt(4)),
                        getGenre(rs.getInt(5)),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getDate(9),
                        rs.getString(10),
                        file
                );
                livre.setID(rs.getInt(1));
                list.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Livre> getLivrePDate(Date date1, Date date2) {
        Livre livre = null;
        List<Livre> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from livre where DateRCP between ? and ?");
            ps.setDate(1, date1);
            ps.setDate(2, date2);

            ResultSet rs = ps.executeQuery();

            InputStream inputStream;
            OutputStream outputStream;

            while (rs.next()) {
                File file = new File("src\\images\\livre.png");

                inputStream = rs.getBinaryStream(10);
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                livre = new Livre(
                        rs.getInt(2),
                        rs.getString(3),
                        getAuteur(rs.getInt(4)),
                        getGenre(rs.getInt(5)),
                        rs.getInt(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getDate(9),
                        rs.getString(10),
                        file
                );
                livre.setID(rs.getInt(1));
                list.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public byte[] getImageData(int id){
        byte[] imageData = null;

        try {
            PreparedStatement ps = cx.prepareStatement("SELECT photo FROM livre WHERE id = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Blob blob = rs.getBlob("photo");

                if (blob != null) {
                    imageData = blob.getBytes(1, (int) blob.length());
                }
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return imageData;
    }

    @Override
    public void ajouterAuteur(Auteur a) {

        try {
            PreparedStatement st = cx.prepareStatement("insert into auteur(nom, prenom, expertise,informations) values(?, ?, ?,?)");

            st.setString(1, a.getNom());
            st.setString(2, a.getPrenom());
            st.setString(3, a.getExpertise());
            st.setString(4, a.getInformations());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void modifierAuteur(Auteur a) {

        try {
            PreparedStatement st = cx.prepareStatement("update auteur set nom= ?, prenom= ?, expertise= ?, informations= ? where id= ?");

            st.setString(1, a.getNom());
            st.setString(2, a.getPrenom());
            st.setString(3, a.getExpertise());
            st.setString(4, a.getInformations());

            st.setInt(5,a.getId());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void supprimerAuteur(int id) {

        try {
            PreparedStatement st = cx.prepareStatement("delete from auteur where id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Auteur getAuteur(int id) {
        Auteur auteur= null;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from auteur where id= ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                auteur = new Auteur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

                auteur.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auteur;
    }

    @Override
    public Auteur getAuteurPMC(String mc) {
        Auteur auteur= null;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from auteur where nom like ? or prenom like ?");
            ps.setString(1, "%" + mc + "%"); // search for mc in nom (last name)
            ps.setString(2, "%" + mc + "%"); // search for mc in prenom (first name)

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                auteur = new Auteur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );

                auteur.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return auteur;
    }

    @Override
    public List<Auteur> getAllAuteurs() {
        List<Auteur> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from auteur");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Auteur auteur = new Auteur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                auteur.setId(rs.getInt(1));

                list.add(auteur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Auteur> getAuteursPMC(String mc) {
        List<Auteur> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from auteur where auteur.nom like ?");

            ps.setString(1, "%" + mc + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Auteur auteur = new Auteur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
                auteur.setId(rs.getInt(1));

                list.add(auteur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void ajouterGenre(Genre genre) {

        try {
            PreparedStatement st = cx.prepareStatement("insert into genre(titre) values(?)");

            st.setString(1, genre.getTitre());
            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void modifierGenre(Genre genre) {

        try {
            PreparedStatement st = cx.prepareStatement("update genre set titre= ? where id= ?");

            st.setString(1, genre.getTitre());
            st.setInt(2, genre.getId());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void supprimerGenre(int id) {

        try {
            PreparedStatement st = cx.prepareStatement("delete from genre where id= ?");

            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Genre getGenre(int id) {
        Genre genre=null;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from genre where id= ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                genre = new Genre(
                        rs.getString(2)
                );
                genre.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genre;
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from genre");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getString(2)
                );
                genre.setId(rs.getInt(1));

                list.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Genre> getGenresPMC(String mc) {
        List<Genre> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from genre where genre.titre like ?");

            ps.setString(1, "%" + mc + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getString(2)
                );
                genre.setId(rs.getInt(1));

                list.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void ajouterUtilisateur(Utilisateur u) {

        try {
            PreparedStatement st = cx.prepareStatement("insert into user(username, password, isAdmin) values(?, ?, ?)");

            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            st.setInt(3, u.getAdmin());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void modifierUtilisateur(Utilisateur u) {

        try {
            PreparedStatement st = cx.prepareStatement("update user set username= ?, password= ?, isAdmin= ? where id= ?");

            st.setString(1, u.getUsername());
            st.setString(2, u.getPassword());
            st.setInt(3, u.getAdmin());
            st.setInt(4, u.getId());

            st.executeUpdate();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void supprimerUtilisateur(int id) {

        try {
            PreparedStatement st = cx.prepareStatement("delete from user where id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur getUtilisateur(int id) {
        Utilisateur utilisateur = null;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from user where id= ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                utilisateur = new Utilisateur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );

                utilisateur.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }

    @Override
    public List<Utilisateur> getAllUtilisateur() {
        List<Utilisateur> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                utilisateur.setId(rs.getInt(1));

                list.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Utilisateur> getUtilisateurPMC(String mc) {
        List<Utilisateur> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from user where username like ?");

            ps.setString(1, "%" + mc + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
                utilisateur.setId(rs.getInt(1));

                list.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void ajouterMembre(Membre m) {

        try {
            PreparedStatement st = cx.prepareStatement("insert into membre(nom, prenom, cin, Tél, `Date Naissance`,Email, Genre, `Date Adhésion`, Adresse, Photo) " +
                    "values(?, ?, ?,?,?,?,?,?,?,?)");



            st.setString(1, m.getNom());
            st.setString(2, m.getPrenom());
            st.setInt(3, m.getCin());
            st.setInt(4, m.getNumTel());
            st.setDate(5, m.getDateDeNaissance());
            st.setString(6, m.getEmail());
            st.setString(7, m.getGenre());
            st.setDate(8, m.getDateAdhesion());
            st.setString(9, m.getAdresse());
            st.setBlob(10, new FileInputStream(m.getPhoto().getAbsolutePath()));

            st.executeUpdate();
        } catch (SQLException | FileNotFoundException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void modifierMembre(Membre m) {

        try {
            PreparedStatement st = cx.prepareStatement("update membre set nom= ?, prenom= ?, cin= ?, Tél= ?, " +
                    "`Date Naissance`= ?, Email= ?, Genre= ?, `Date Adhésion`= ?, Adresse= ?, Photo= ? where id= ?");

            st.setString(1, m.getNom());
            st.setString(2, m.getPrenom());
            st.setInt(3, m.getCin());
            st.setInt(4, m.getNumTel());
            st.setDate(5, m.getDateDeNaissance());
            st.setString(6, m.getEmail());
            st.setString(7, m.getGenre());
            st.setDate(8, m.getDateAdhesion());
            st.setString(9, m.getAdresse());
            st.setBlob(10, new FileInputStream(m.getPhoto().getAbsolutePath()));
            st.setInt(11, m.getId());

            st.executeUpdate();
        } catch (FileNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void supprimerMembre(int id) {

        try {
            PreparedStatement st = cx.prepareStatement("delete from membre where id= ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Membre getMembre(int id) {
        Membre membre = null;
        InputStream inputStream;
        OutputStream outputStream;

        try {
            PreparedStatement ps = cx.prepareStatement("select * from membre where id= ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                inputStream = rs.getBinaryStream("photo");

                if (inputStream == null) {
                    membre = new Membre(
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getDate(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getDate(9),
                            rs.getString(10)
                    );
                    membre.setId(rs.getInt(1));

                    membre.setPhoto(new File(String.format("src\\images\\%s.jpg",
                            (Objects.equals(membre.getGenre().toLowerCase(), "Feminin") ? "hellokitty " : "unnamed"))));

                    return membre;
                }

                File tempFile = File.createTempFile("membre", ".png");
                outputStream = new FileOutputStream(tempFile);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                membre = new Membre(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10),
                        tempFile
                );
                membre.setId(rs.getInt(1));
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return membre;
    }

    @Override
    public List<Membre> getAllMembre() {
        List<Membre> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from membre");
            ResultSet rs = ps.executeQuery();

            InputStream inputStream;
            OutputStream outputStream;

            while (rs.next()) {
                File file = new File("src\\images\\output.png");

                inputStream = rs.getBinaryStream(10);
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                Membre membre = new Membre(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10),
                        file
                );
                membre.setId(rs.getInt(1));

                list.add(membre);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Membre> getMembrePMC(String mc) {
        List<Membre> list = new ArrayList<>();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from membre where nom like ?");
            ps.setString(1, "%" + mc + "%");

            ResultSet rs = ps.executeQuery();

            InputStream inputStream;
            OutputStream outputStream;

            while (rs.next()) {
                File file = new File("src\\images\\output.png");

                inputStream = rs.getBinaryStream(10);
                outputStream = new FileOutputStream(file);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                Membre membre = new Membre(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10),
                        file
                );
                membre.setId(rs.getInt(1));

                list.add(membre);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return list;
    }



}
