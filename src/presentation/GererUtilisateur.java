package presentation;

import dao.ILibrairie;
import lombok.Getter;
import lombok.Setter;
import metier.entity.Utilisateur;
import presentation.tableModeles.TableModeleUtilisateur;

import javax.swing.*;
import java.awt.*;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;
import java.util.Optional;

@Getter
@Setter
public class GererUtilisateur extends JPanel{

    //JPanels
    // Upper Section
    private final JPanel tableJPanel = new JPanel(new GridLayout(1, 1));

    // Title Section
    private final JPanel manageUsersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
    // panel = [retour : west + manageUsersPanel : center]
    private final JPanel panel = new JPanel(new BorderLayout(10, 10));

    // Down Section
    private final JPanel mainSection = new JPanel(new BorderLayout(10,10));

    // forumPanel = [ idPanel + typePanel + usernamePanel + passwordPanel + confirmPasswordPanel + buttonsPanel]
    private final JPanel forumPanel = new JPanel(new GridLayout(6,1,10,10));

    private final JPanel idPanel = new JPanel(new GridLayout(2,1,5,5));
    private final JPanel typePanel = new JPanel(new GridLayout(2,1,5,5));
    private final JPanel usernamePanel = new JPanel(new GridLayout(2,1,5,5));
    private final JPanel passwordPanel = new JPanel(new GridLayout(2,1,5,5));
    private final JPanel confirmPasswordPanel = new JPanel(new GridLayout(2,1,5,5));
    private final JPanel buttonsPanel = new JPanel(new GridLayout(2,1,10,10));

    // table section
    private final JPanel sidePanel = new JPanel(new BorderLayout(10,10));
    private final JPanel researchPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel tablePanel = new JPanel(new GridLayout(1,1,10,10));

    // title
    private final JLabel titleLabel = new JLabel("Gérer Les Utilisateurs");


    //id section
    private final JLabel idLabel = new JLabel("ID: ");
    private final JTextField idTextField = new JTextField();

    // username Section
    private final JLabel usernameLabel = new JLabel("Nom D'utilisateur: ");
    private final JTextField usernameTextField = new JTextField();

    // password section
    private final JLabel passwordLabel = new JLabel("Mot De Passe: ");
    private final JLabel confirmPasswordLabel = new JLabel("Confirmer le mot de passe: ");
    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField confirmPasswordField = new JPasswordField();

    // Type de l'utilisateur
    private final JLabel userTypeLabel = new JLabel("Type de l'utilisateur: ");
    private final JComboBox<String> comboBox = new JComboBox<>(new String[] {
            "Admin",
            "Libraire"
    });

    // research section
    private final JTextField rechercherTextField = new JTextField();

    // Image icons
    private ImageIcon backIcon = new ImageIcon("src\\images\\icon\\backIcon.png");
    private ImageIcon searchIcon = new ImageIcon("src\\images\\icon\\searchIcon.png");

    // JButtons
    private final JButton retour = new JButton();
    private final JButton rechercher = new JButton();
    private final JButton ajouter = new JButton("créer un compte");
    private final JButton edit = new JButton("modifier un compte");
    private final JButton supprimer = new JButton("supprimer un compte");
    private final JButton annuler = new JButton("annuler");

    // ILibrairie
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    // TableModeleUtilisateur
    private final TableModeleUtilisateur me = new TableModeleUtilisateur();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    //
    String name = usernameTextField.getText();
    String password = String.valueOf(passwordField.getPassword());
    String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

    // icons
    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\manageUsersIcon.png");


    public GererUtilisateur(){

        super(new GridLayout(1,1,10,10));

        // resizing the icons:
        backIcon = Utils.resizeImageIcon(backIcon, 70, 70);
        searchIcon = Utils.resizeImageIcon(searchIcon, 40, 40);

        // adding the ImageIcons to the JButtons:
        retour.setIcon(backIcon);
        rechercher.setIcon(searchIcon);

        // setting focusable for the JButtons:
        retour.setFocusable(false);
        rechercher.setFocusable(false);
        annuler.setFocusable(false);
        ajouter.setFocusable(false);
        supprimer.setFocusable(false);
        edit.setFocusable(false);

        // making the borders and the background transparent for the retour + rechercher JButtons:
        // retour JButton:
        retour.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        retour.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // rechercher JButton:
        rechercher.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        rechercher.setBackground(new Color(0,0, 0, Transparency.TRANSLUCENT));

        // setting the fonts + colors:
        //fonts:
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        rechercherTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        annuler.setFont(new Font("Arial", Font.PLAIN, 20));
        ajouter.setFont(new Font("Arial", Font.PLAIN, 20));
        supprimer.setFont(new Font("Arial", Font.PLAIN, 20));
        edit.setFont(new Font("Arial", Font.PLAIN, 20));
        idLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        idTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        usernameTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 15));
        userTypeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));


        // background
        rechercher.setContentAreaFilled(false);
        rechercher.setFocusPainted(false);
        retour.setContentAreaFilled(false);
        retour.setFocusPainted(false);

        // resizing the title icon
        titleIcon = Utils.resizeImageIcon(titleIcon, 50,50);

        ajouter.addActionListener(e -> {
            if (!validateInputFields()) {
                try {
                    Utilisateur utilisateur = new Utilisateur(
                            usernameTextField.getText(),
                            String.valueOf(passwordField.getPassword()),
                            comboBox.getSelectedItem().equals("Admin") ? 1 : 0
                    );

                    Optional<Utilisateur> existingUser = librairie.getAllUtilisateur()
                            .stream()
                            .filter(utilisateur::equals)
                            .findFirst();

                    if (existingUser.isPresent()) {
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "Utilisateur déjà existant !"
                        );
                    } else {
                        librairie.ajouterUtilisateur(utilisateur);
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "Utilisateur ajouté !"
                        );
                    }
                }
                catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "Erreur de saisie de l'ID !"
                    );
                }

                me.chargerTable(librairie.getAllUtilisateur());
            }
        });

        edit.addActionListener(e -> {
            if (name.isBlank() || password.isBlank() || !password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(
                        GererUtilisateur.this,
                        "erreur de saisie"
                );
            }
            else {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Utilisateur utilisateur = librairie.getUtilisateur(id);

                    if (utilisateur == null) {
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "impossible de faire le mis à jour ce compte!"
                        );
                    } else {
                        utilisateur.setUsername(usernameTextField.getText());
                        utilisateur.setPassword(String.valueOf(passwordField.getPassword()));
                        utilisateur.setAdmin((Objects.equals(comboBox.getSelectedItem(), "Admin"))? 1 : 0);

                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "utilisateur à été modifier!"
                        );

                        librairie.modifierUtilisateur(utilisateur);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "erreur de saisie de l'id!"
                    );
                }

                me.chargerTable(librairie.getAllUtilisateur());
            }
        });
        supprimer.addActionListener(e -> {
            if (idTextField.getText().isBlank()) {
                JOptionPane.showMessageDialog(
                        GererUtilisateur.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());

                    if (librairie.getUtilisateur(id) == null) {
                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "impossible de supprimer ce compte!"
                        );
                    } else {
                        librairie.supprimerUtilisateur(id);

                        JOptionPane.showMessageDialog(
                                GererUtilisateur.this,
                                "utilisateur à été supprimer!"
                        );
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererUtilisateur.this,
                            "erreur de saisie de l'id!"
                    );
                }

                me.chargerTable(librairie.getAllUtilisateur());
            }
        });
        annuler.addActionListener(e -> {
            idTextField.setText("");
            usernameTextField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        // setting up the interface
        // title
        titleLabel.setIcon(titleIcon);
        manageUsersPanel.add(titleLabel);

        idPanel.add(idLabel);
        idPanel.add(idTextField);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        typePanel.add(userTypeLabel);
        typePanel.add(comboBox);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);

        buttonsPanel.add(ajouter);
        buttonsPanel.add(edit);
        buttonsPanel.add(supprimer);
        buttonsPanel.add(annuler);

        forumPanel.add(idPanel);
        forumPanel.add(usernamePanel);
        forumPanel.add(typePanel);
        forumPanel.add(passwordPanel);
        forumPanel.add(confirmPasswordPanel);
        forumPanel.add(buttonsPanel);

        //
        forumPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        20,
                        20
                )
        );
        sidePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        20,
                        20
                )
        );
        retour.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );
        rechercherTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));

        // adding the JScrollPane to the tableJPanel JPanel:
        tableJPanel.add(jsp);
        tablePanel.add(tableJPanel);
        researchPanel.add(rechercher, BorderLayout.WEST);
        researchPanel.add(rechercherTextField);

        sidePanel.add(researchPanel, BorderLayout.NORTH);
        sidePanel.add(tablePanel, BorderLayout.CENTER);

        panel.add(retour, BorderLayout.WEST);
        panel.add(manageUsersPanel);

        mainSection.add(panel, BorderLayout.NORTH);
        mainSection.add(forumPanel, BorderLayout.WEST);
        mainSection.add(sidePanel, BorderLayout.CENTER);

        // setting up background color
        mainSection.setBackground(Color.WHITE);
        forumPanel.setBackground(Color.WHITE);
        sidePanel.setBackground(Color.WHITE);
        idPanel.setBackground(Color.WHITE);
        usernamePanel.setBackground(Color.WHITE);
        typePanel.setBackground(Color.WHITE);
        passwordPanel.setBackground(Color.WHITE);
        confirmPasswordPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        forumPanel.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        manageUsersPanel.setBackground(Color.WHITE);
        retour.setBackground(Color.WHITE);
        researchPanel.setBackground(Color.WHITE);
        comboBox.setBackground(Color.WHITE);
        rechercher.setBackground(Color.WHITE);


        //
        this.add(mainSection);
        this.setVisible(true);


    }

    private boolean validateInputFields() {

        if (name.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(
                    GererUtilisateur.this,
                    "Veuillez remplir tous les champs obligatoires."
            );
            return false;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(
                    GererUtilisateur.this,
                    "Le mot de passe et la confirmation de mot de passe doivent être identiques."
            );
            return false;
        }

        return true;
    }

}