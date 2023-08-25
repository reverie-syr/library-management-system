package presentation;

import dao.ILibrairie;
import metier.entity.Genre;
import presentation.tableModeles.TableModeleGenre;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;

public class GererGenres extends JPanel {

    private final JPanel titlePanel = new JPanel();
    private final JPanel mainSectionPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel infoPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel inputPanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1,4,10,10));
    // table section
    private final JPanel sidePanel = new JPanel(new BorderLayout(10,10));
    private final JPanel researchPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel tablePanel = new JPanel(new GridLayout(1,1,10,10));

    private final JPanel idPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel titrePanel = new JPanel(new GridLayout(1,2,10,10));

    // JLabels
    private final JLabel titleLabel = new JLabel("Gérer Genres");
    private final JLabel idLabel = new JLabel("ID: ");
    private final JLabel titreLabel = new JLabel("Titre: ");

    //JTextFields
    private final JTextField idTextField = new JTextField();
    private final JTextField titreTextField = new JTextField();
    private final JTextField rechercherTextField = new JTextField();

    // ILibrairie
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    // TableModeleGenre
    private final TableModeleGenre tableModeleGenre = new TableModeleGenre();

    // JTable
    private final JTable table = new JTable(tableModeleGenre);
    // JScrollPane
    private final JScrollPane scrollPane = new JScrollPane(table);

    private final DefaultTableCellRenderer renderer = new ListeMembre.CusttomRenderer();

    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\gererGenres.png");
    private ImageIcon rechercherIcon = new ImageIcon("src\\images\\icon\\searchModifierLivre.png");
    //
    private final JButton rechercherButton = new JButton();
    private final JButton ajouter = new JButton("Ajouter");
    private final JButton modifier = new JButton("Modifier");
    private final JButton supprimer = new JButton("Supprimer");
    private final JButton annuler = new JButton("Annuler");

    private final JPanel ajouterPanel = new JPanel();
    private final JPanel modifierPanel = new JPanel();
    private final JPanel supprimerPanel = new JPanel();
    private final JPanel annulerPanel = new JPanel();

    int id = -1;


    public GererGenres(){
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);

        // resizing the icons
        titleIcon = Utils.resizeImageIcon(titleIcon, 70, 70);
        rechercherIcon = Utils.resizeImageIcon(rechercherIcon, 50, 50);
        //
        titleLabel.setIcon(titleIcon);

        titlePanel.add(titleLabel);

        idPanel.add(idLabel);
        idPanel.add(idTextField);
        titrePanel.add(titreLabel);
        titrePanel.add(titreTextField);

        inputPanel.add(idPanel);
        inputPanel.add(titrePanel);

        rechercherButton.setIcon(rechercherIcon);

        rechercherButton.setContentAreaFilled(false);
        rechercherButton.setFocusPainted(false);
        rechercherButton.setFocusable(false);
        rechercherButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        //
        researchPanel.add(rechercherButton, BorderLayout.WEST);
        researchPanel.add(rechercherTextField);
        //
        tablePanel.add(scrollPane);
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setFont(new Font("Arial", Font.PLAIN, 18));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.getTableHeader().setBackground(new Color(235, 69, 95));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 20));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.WHITE);

        sidePanel.add(researchPanel, BorderLayout.NORTH);
        sidePanel.add(tablePanel, BorderLayout.CENTER);

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

        buttonsPanel.add(ajouterPanel);
        buttonsPanel.add(modifierPanel);
        buttonsPanel.add(supprimerPanel);
        buttonsPanel.add(annulerPanel);

        titleLabel.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 40));
        idLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titreLabel.setFont(new Font("Arial", Font.BOLD, 24));

        idTextField.setFont(new Font("Arial", Font.ITALIC, 22));
        titreTextField.setFont(new Font("Arial", Font.ITALIC, 22));

        rechercherTextField.setFont(new Font("Arial", Font.BOLD, 22));
        rechercherTextField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.black));

        infoPanel.add(inputPanel, BorderLayout.CENTER);
        infoPanel.add(buttonsPanel, BorderLayout.SOUTH);

        inputPanel.setBorder(BorderFactory.createEmptyBorder(
                200,
                30,
                200,
                20
        ));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                30,
                70,
                30
        ));
        idPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                30,
                10,
                30
        ));
        titrePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                30,
                10,
                30
        ));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(
                20,
                10,
                20,
                10
        ));
        mainSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));

        titlePanel.setBorder((BorderFactory.createMatteBorder(0,0,5,0, new Color(235,69, 95))));
        titlePanel.setBackground(new Color(186, 215, 233));

        idPanel.setBackground(Color.WHITE);
        titrePanel.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        infoPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        sidePanel.setBackground(Color.WHITE);
        mainSectionPanel.setBackground(Color.WHITE);
        tablePanel.setBackground(Color.WHITE);
        researchPanel.setBackground(Color.WHITE);


        mainSectionPanel.add(infoPanel, BorderLayout.WEST);
        mainSectionPanel.add(sidePanel, BorderLayout.CENTER);



        //
        supprimer.addActionListener(e -> {
            if (id != -1) {
                librairie.supprimerGenre(id);

                JOptionPane.showMessageDialog(
                        GererGenres.this,
                        "Genre Supprimé!"
                );

                tableModeleGenre.chargerTable(librairie.getAllGenres());
            } else {
                JOptionPane.showMessageDialog(
                        GererGenres.this,
                        "aucun genre est selectioné"
                );
            }
        });

        //
        rechercherButton.addActionListener(e -> {
            if (!rechercherTextField.getText().isBlank()){
                try {
                    List<Genre> list = librairie.getGenresPMC(rechercherTextField.getText());

                    tableModeleGenre.chargerTable(list);
                }
                catch (NumberFormatException e2){
                    e2.printStackTrace();
                }
            }

        });
        ajouter.addActionListener(e -> {
            if (!idTextField.getText().isBlank()
                    || !titreTextField.getText().isBlank()){
                try {
                    Genre genre = new Genre(
                            titreTextField.getText()
                    );

                    Optional<Genre> existingGenre = librairie.getAllGenres()
                            .stream()
                            .filter(genre::equals)
                            .findFirst();

                    if (existingGenre.isPresent()) {
                        JOptionPane.showMessageDialog(
                                GererGenres.this,
                                "Genre déjà existe !"
                        );
                    } else {
                        librairie.ajouterGenre(genre);
                        JOptionPane.showMessageDialog(
                                GererGenres.this,
                                "Genre ajouté !"
                        );
                    }
                }
                catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererGenres.this,
                            "Erreur de saisie de l'ID !"
                    );
                }

                tableModeleGenre.chargerTable(librairie.getAllGenres());
            }
        });
        modifier.addActionListener(e -> {
            if (
                    idTextField.getText().isBlank()
                            || titreTextField.getText().isBlank()){
                JOptionPane.showMessageDialog(
                        GererGenres.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Genre genre = librairie.getGenre(id);
                    Optional<Genre> optionalGenre = Optional.ofNullable(genre);

                    if (optionalGenre.isPresent()) {
                        genre.setTitre(titreTextField.getText());

                        JOptionPane.showMessageDialog(
                                GererGenres.this,
                                "Genre a été modifié!"
                        );

                        librairie.modifierGenre(genre);
                    }
                }

                catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            GererGenres.this,
                            "erreur de saisie de l'id!"
                    );
                }
                tableModeleGenre.chargerTable(librairie.getAllGenres());
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    id = (int) target.getValueAt(row, 0);

                    Genre genre = librairie.getGenre(id);

                    idTextField.setText(String.valueOf(id));
                    titreTextField.setText(genre.getTitre());

                }
            }
        });


        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainSectionPanel);
        this.setVisible(true);




    }


}
