package presentation;

import dao.ILibrairie;
import metier.entity.Auteur;
import presentation.tableModeles.TableModeleAuteur;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

public class GererAuteurs extends JPanel {

    private final JPanel titlePanel = new JPanel();

    private final JPanel mainSectionPanel = new JPanel(new BorderLayout(10,10));

    // inputPanel = infoPanel + buttonsPanel
    private final JPanel inputPanel = new JPanel(new BorderLayout(10,40));
    private final JPanel infoPanel = new JPanel(new GridLayout(5,1,10,10));
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1,3,10,10));

    private final JPanel idPanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel nomPanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel prenomPanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel expertisePanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel aproposPanel = new JPanel(new BorderLayout(10,10));
    // table section
    private final JPanel sidePanel = new JPanel(new BorderLayout(10,10));
    private final JPanel researchPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel tablePanel = new JPanel(new GridLayout(1,1,10,10));


    private final JLabel titleLabel = new JLabel("Gérer Auteurs");
    private final JLabel idLabel = new JLabel("ID: ");
    private final JLabel nomLabel = new JLabel("Nom: ");
    private final JLabel prenomLabel = new JLabel("Prénom: ");
    private final JLabel expertiseLabel = new JLabel("Expertise: ");
    private final JLabel aproposLabel = new JLabel("À propos: ");

    private final JTextField idTextField = new JTextField();
    private final JTextField nomTextField = new JTextField();
    private final JTextField prenomTextField = new JTextField();
    private final JTextField expertiseTextField = new JTextField();
    private final JTextArea aproposTextArea = new JTextArea(3,30);

    private final JTextField rechercherTextField = new JTextField();


    private final JButton ajouter = new JButton("Ajouter");
    private final JPanel ajouterPanel = new JPanel();
    private final JButton modifier = new JButton("Modifier");
    private final JPanel modifierPanel = new JPanel();
    private final JButton supprimer = new JButton("Supprimer");
    private final JPanel supprimerPanel = new JPanel();
    private final JButton annuler = new JButton("Annuler");
    private final JPanel annulerPanel = new JPanel();
    private final JButton rechercherButton = new JButton();

    ImageIcon rechercherIcon = new ImageIcon("src\\images\\icon\\search-bar.png");

    ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\poem.png");

    // ILibrairie
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    // TableModeleAuteur
    private final TableModeleAuteur tableModeleAuteur = new TableModeleAuteur();

    // JTable
    private final JTable table = new JTable(tableModeleAuteur);
    // JScrollPane
    private final JScrollPane scrollPane = new JScrollPane(table);
    private final JScrollPane scrollPaneTextArea = new JScrollPane(aproposTextArea);

    private final DefaultTableCellRenderer renderer = new ListeMembre.CusttomRenderer();
    //
    int id = -1;


    public GererAuteurs(){
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);

        // resizing the icons
        titleIcon = Utils.resizeImageIcon(titleIcon, 70, 70);
        rechercherIcon = Utils.resizeImageIcon(rechercherIcon, 70, 70);
        //
        titleLabel.setIcon(titleIcon);

        titlePanel.add(titleLabel);

        idPanel.add(idLabel);
        idPanel.add(idTextField);
        nomPanel.add(nomLabel);
        nomPanel.add(nomTextField);
        prenomPanel.add(prenomLabel);
        prenomPanel.add(prenomTextField);
        expertisePanel.add(expertiseLabel);
        expertisePanel.add(expertiseTextField);
        aproposPanel.add(aproposLabel, BorderLayout.NORTH);
        aproposPanel.add(scrollPaneTextArea, BorderLayout.CENTER);
        scrollPaneTextArea.setPreferredSize(new Dimension(200,300));
        aproposTextArea.setLineWrap(true);
        aproposTextArea.setWrapStyleWord(true);

        infoPanel.add(idPanel);
        infoPanel.add(nomPanel);
        infoPanel.add(prenomPanel);
        infoPanel.add(expertisePanel);
        infoPanel.add(aproposPanel);

        buttonsPanel.add(ajouterPanel);
        buttonsPanel.add(modifierPanel);
        buttonsPanel.add(supprimerPanel);
        buttonsPanel.add(annulerPanel);

        inputPanel.add(infoPanel, BorderLayout.CENTER);
        inputPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tablePanel.add(scrollPane);
        renderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.getTableHeader().setBackground(new Color(25, 75, 118).darker());
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 16));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.WHITE);

        researchPanel.add(rechercherButton, BorderLayout.WEST);
        researchPanel.add(rechercherTextField);

        sidePanel.add(researchPanel, BorderLayout.NORTH);
        sidePanel.add(tablePanel, BorderLayout.CENTER);



        rechercherButton.setIcon(rechercherIcon);

        rechercherButton.setContentAreaFilled(false);
        rechercherButton.setFocusPainted(false);
        rechercherButton.setFocusable(false);
        rechercherButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));


        ajouter.setContentAreaFilled(false);
        ajouter.setFocusPainted(false);
        ajouter.setFocusable(false);
        ajouter.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        ajouter.setFont(new Font("Arial", Font.BOLD, 18));
        ajouterPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        ajouterPanel.setBackground(new Color(151, 37, 33));
        ajouter.setForeground(Color.WHITE);
        ajouterPanel.add(ajouter);

        modifier.setContentAreaFilled(false);
        modifier.setFocusPainted(false);
        modifier.setFocusable(false);
        modifier.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        modifier.setFont(new Font("Arial", Font.BOLD, 18));
        modifierPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        modifierPanel.setBackground(new Color(151, 37, 33));
        modifier.setForeground(Color.WHITE);
        modifierPanel.add(modifier);

        supprimer.setContentAreaFilled(false);
        supprimer.setFocusPainted(false);
        supprimer.setFocusable(false);
        supprimer.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        supprimer.setFont(new Font("Arial", Font.BOLD, 18));
        supprimerPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        supprimerPanel.setBackground(new Color(151, 37, 33));
        supprimer.setForeground(Color.WHITE);
        supprimerPanel.add(supprimer);

        annuler.setContentAreaFilled(false);
        annuler.setFocusPainted(false);
        annuler.setFocusable(false);
        annuler.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        annuler.setFont(new Font("Arial", Font.BOLD, 18));
        annulerPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        annulerPanel.setBackground(new Color(151, 37, 33));
        annuler.setForeground(Color.WHITE);
        annulerPanel.add(annuler);

        titleLabel.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 40));
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nomLabel.setFont(new Font("Arial", Font.BOLD, 20));
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 20));
        expertiseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        aproposLabel.setFont(new Font("Arial", Font.BOLD, 20));

        idTextField.setFont(new Font("Arial", Font.ITALIC, 18));
        nomTextField.setFont(new Font("Arial", Font.ITALIC, 18));
        prenomTextField.setFont(new Font("Arial", Font.ITALIC, 18));
        expertiseTextField.setFont(new Font("Arial", Font.ITALIC, 18));
        //
        aproposTextArea.setFont(new Font("Arial", Font.ITALIC, 18));
        aproposTextArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        //
        rechercherTextField.setFont(new Font("Arial", Font.BOLD, 18));
        rechercherTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));

        titlePanel.setBackground(new Color(25, 75, 118));
        titleLabel.setForeground(Color.WHITE);
        idPanel.setBackground(Color.WHITE);
        nomPanel.setBackground(Color.WHITE);
        prenomPanel.setBackground(Color.WHITE);
        expertisePanel.setBackground(Color.WHITE);
        aproposPanel.setBackground(Color.WHITE);
        infoPanel.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        researchPanel.setBackground(Color.WHITE);
        tablePanel.setBackground(Color.WHITE);
        sidePanel.setBackground(Color.WHITE);
        mainSectionPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);

        mainSectionPanel.add(inputPanel, BorderLayout.WEST);
        mainSectionPanel.add(sidePanel, BorderLayout.CENTER);


        titlePanel.setBorder(BorderFactory.createMatteBorder(0,0,8,0, new Color(25, 75, 118).darker()));
        mainSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                20,
                10,
                20
        ));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(
                20,
                10,
                0,
                10
        ));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                10,
                20,
                10
        ));

        //
        supprimer.addActionListener(e -> {
            if (id != -1) {
                librairie.supprimerAuteur(id);

                JOptionPane.showMessageDialog(
                        GererAuteurs.this,
                        "Auteur Supprimé!"
                );

                tableModeleAuteur.chargerTable(librairie.getAllAuteurs());
            } else {
                JOptionPane.showMessageDialog(
                        GererAuteurs.this,
                        "aucun auteur est selectioné"
                );
            }
        });

        //
        rechercherButton.addActionListener(e -> {
            if (!rechercherTextField.getText().isBlank()){
                try {
                    List<Auteur> list = librairie.getAuteursPMC(rechercherTextField.getText());

                    tableModeleAuteur.chargerTable(list);
                }
                catch (NumberFormatException e2){
                    e2.printStackTrace();
                }
            }

        });
        ajouter.addActionListener(e -> {
            if (!idTextField.getText().isBlank()
                    || !nomTextField.getText().isBlank()
                    || !prenomTextField.getText().isBlank()
                    || !expertiseTextField.getText().isBlank()
                    || !aproposTextArea.getText().isBlank()) {
                try {
                    Auteur auteur = new Auteur(
                            nomTextField.getText(),
                            prenomTextField.getText(),
                            expertiseTextField.getText(),
                            aproposTextArea.getText()
                    );

                    Optional<Auteur> existingAuteur = librairie.getAllAuteurs()
                            .stream()
                            .filter(auteur::equals)
                            .findFirst();

                    if (existingAuteur.isPresent()) {
                        JOptionPane.showMessageDialog(
                                GererAuteurs.this,
                                "auteur déjà existe !"
                        );
                    } else {
                        librairie.ajouterAuteur(auteur);
                        JOptionPane.showMessageDialog(
                                GererAuteurs.this,
                                "Auteur ajouté !"
                        );
                    }
                }
                catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererAuteurs.this,
                            "Erreur de saisie de l'ID !"
                    );
                }

                tableModeleAuteur.chargerTable(librairie.getAllAuteurs());
            }
        });
        modifier.addActionListener(e -> {
            if (
                    idTextField.getText().isBlank()
                            || nomTextField.getText().isBlank()
                            || prenomTextField.getText().isBlank()
                            || expertiseTextField.getText().isBlank()
                            || aproposTextArea.getText().isBlank()){
                JOptionPane.showMessageDialog(
                        GererAuteurs.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Auteur auteur = librairie.getAuteur(id);
                    Optional<Auteur> optionalAuteur = Optional.ofNullable(auteur);

                    if (optionalAuteur.isPresent()) {
                        auteur.setNom(nomTextField.getText());
                        auteur.setPrenom(prenomTextField.getText());
                        auteur.setExpertise(expertiseTextField.getText());
                        auteur.setInformations(aproposTextArea.getText());

                        JOptionPane.showMessageDialog(
                                GererAuteurs.this,
                                "Auteur a été modifié!"
                        );

                        librairie.modifierAuteur(auteur);
                    }
                }

                catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(
                                GererAuteurs.this,
                                "erreur de saisie de l'id!"
                        );
                }
                tableModeleAuteur.chargerTable(librairie.getAllAuteurs());
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    id = (int) target.getValueAt(row, 0);

                    Auteur auteur= librairie.getAuteur(id);

                    idTextField.setText(String.valueOf(id));
                    nomTextField.setText(auteur.getNom());
                    prenomTextField.setText(auteur.getPrenom());
                    expertiseTextField.setText(auteur.getExpertise());
                    aproposTextArea.setText(auteur.getInformations());
                }
            }
        });


        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainSectionPanel, BorderLayout.CENTER);
        this.setVisible(true);




    }






}
