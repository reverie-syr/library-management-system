package presentation;

import com.toedter.calendar.JDateChooser;
import dao.ILibrairie;
import metier.entity.*;
import presentation.tableModeles.TableModeleAuteur;
import presentation.tableModeles.TableModeleLivre;
import presentation.tableModeles.TableModeleRetournerLivre;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;
import java.util.Optional;

public class RetournerLivre extends JPanel {
    private final JPanel titlePanel = new JPanel();

    private final JPanel mainSectionPanel = new JPanel(new GridLayout(1,2,10,10));

    // inputPanel = infoPanel + buttonsPanel
    private final JPanel inputPanel = new JPanel(new BorderLayout(10, 40));
    private final JPanel infoPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel infoPanelA = new JPanel(new GridLayout(6,1,10,10));
    private final JPanel infoPanelB = new JPanel(new BorderLayout());
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1, 3, 10, 10));
    private final JPanel bookIDPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel bookNamePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel memberIDPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel memberNamePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel issueDatePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel returnDatePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel notePanel = new JPanel(new GridLayout(1,2,10,10));

    // table section
    private final JPanel sidePanel = new JPanel(new BorderLayout(10, 10));
    private final JPanel searchPanel = new JPanel();
    private final JPanel bookStatusPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel tablePanel = new JPanel(new GridLayout(1, 1, 10, 10));

    //
    private final JPanel emptyBookPanel = new JPanel();
    private final JPanel emptyMemberPanel = new JPanel();
    private final JLabel titleLabel = new JLabel("Retourner un Livre");


    private final JLabel bookIDLabel = new JLabel("ID Livre: ");
    private final JLabel bookNameLabel = new JLabel("Titre du Livre");
    private final JLabel memberIDLabel = new JLabel("ID Membre: ");
    private final JLabel memberNameLabel = new JLabel("Nom et Prénom du Membre");
    private final JLabel issueDateLabel = new JLabel("Date d'Attribution: ");
    private final JLabel returnDateLabel = new JLabel("Date de Retour: ");
    private final JLabel noteLabel = new JLabel("Note: ");

    private final SpinnerModel spinnerModel = new SpinnerNumberModel(1, 0, 100, 1);
    private JSpinner livreIdSpinner = new JSpinner(spinnerModel);
    private JSpinner membreIdSpinner = new JSpinner(spinnerModel);

    private JDateChooser dateAttributionChooser = new JDateChooser();
    private JDateChooser dateRetourChooser = new JDateChooser();
    private JTextArea noteTextArea = new JTextArea(6, 1);
    private JScrollPane scrollPane = new JScrollPane(noteTextArea);

    private final JButton returnButton = new JButton("Retourner");
    private final JPanel returnButtonPanel = new JPanel();
    private final JButton lostButton = new JButton("Livre Perdu");
    private final JPanel lostButtonPanel = new JPanel();
    private final JButton deleteButton = new JButton("Supprimer");
    private final JPanel deleteButtonPanel = new JPanel();

    private final JLabel selectBookStatusLabel = new JLabel("sélectionner l'état du livre");
    private final JComboBox<Etat> etatJComboBox = new JComboBox<>(
            new Etat[] {
                    Etat.TOUS,
                    Etat.PERDU,
                    Etat.RENDU
            }
    );
    ImageIcon rechercherIcon = new ImageIcon("src\\images\\icon\\search-bar.png");

    ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\poem.png");

    // ILibrairie
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    // TableModeleLivre
    private final TableModeleRetournerLivre tableModeleRetournerLivre =
            TableModeleRetournerLivre.getInstance();
    // JTable
    private final JTable table = new JTable(tableModeleRetournerLivre);
    // JScrollPane
    private final JScrollPane tableScrollPane = new JScrollPane(table);
    private final DefaultTableCellRenderer renderer = new ListeMembre.CusttomRenderer();
    //
    int id = -1;

    public RetournerLivre() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);

        // resizing the icons
        titleIcon = Utils.resizeImageIcon(titleIcon, 70, 70);
        //
        titleLabel.setIcon(titleIcon);

        titlePanel.add(titleLabel);

        scrollPane.setPreferredSize(new Dimension(200, 300));
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);

        returnButton.setBorder(BorderFactory.createLineBorder(new Color(1, 1, 0, Transparency.TRANSLUCENT)));
        returnButton.setFont(new Font("Arial", Font.BOLD, 18));
        returnButton.setContentAreaFilled(false);
        returnButton.setFocusPainted(false);
        returnButton.setFocusable(false);
        returnButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        lostButton.setBorder(BorderFactory.createLineBorder(new Color(1, 1, 0, Transparency.TRANSLUCENT)));
        lostButton.setFont(new Font("Arial", Font.BOLD, 18));
        lostButton.setContentAreaFilled(false);
        lostButton.setFocusPainted(false);
        lostButton.setFocusable(false);
        lostButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(1, 1, 0, Transparency.TRANSLUCENT)));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setFocusable(false);
        deleteButtonPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

        returnButtonPanel.add(returnButton);
        lostButtonPanel.add(lostButton);
        deleteButtonPanel.add(deleteButton);

        buttonsPanel.add(returnButtonPanel);
        buttonsPanel.add(lostButtonPanel);
        buttonsPanel.add(deleteButtonPanel);

        bookIDPanel.add(bookIDLabel);
        bookIDPanel.add(livreIdSpinner);
        //
        bookNamePanel.add(emptyBookPanel);
        bookNamePanel.add(bookNameLabel);
        //
        memberIDPanel.add(memberIDLabel);
        memberIDPanel.add(membreIdSpinner);
        //
        memberNamePanel.add(emptyMemberPanel);
        memberNamePanel.add(memberNameLabel);
        //
        issueDatePanel.add(issueDateLabel);
        issueDatePanel.add(dateAttributionChooser);
        //
        returnDatePanel.add(returnDateLabel);
        returnDatePanel.add(dateRetourChooser);
        //
        notePanel.add(noteLabel);
        notePanel.add(noteTextArea);

        infoPanelA.add(bookIDPanel);
        infoPanelA.add(bookNamePanel);
        infoPanelA.add(memberIDPanel);
        infoPanelA.add(memberNamePanel);
        infoPanelA.add(issueDatePanel);
        infoPanelA.add(returnDatePanel);

        infoPanelB.add(notePanel);

        infoPanel.add(infoPanelA, BorderLayout.CENTER);
        infoPanel.add(infoPanelB, BorderLayout.SOUTH);

        inputPanel.add(infoPanel, BorderLayout.CENTER);
        inputPanel.add(buttonsPanel, BorderLayout.SOUTH);

        tablePanel.add(tableScrollPane);
        renderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.getTableHeader().setBackground(new Color(25, 75, 118).darker());
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 16));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.WHITE);

        bookStatusPanel.add(selectBookStatusLabel);
        bookStatusPanel.add(etatJComboBox);

        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.add(bookStatusPanel);
        searchPanel.add(Box.createHorizontalStrut(200));

        sidePanel.add(searchPanel, BorderLayout.NORTH);
        sidePanel.add(tablePanel, BorderLayout.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 40));
        bookIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        memberIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        issueDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        returnDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        noteLabel.setFont(new Font("Arial", Font.BOLD, 20));
        selectBookStatusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        etatJComboBox.setFont(new Font("Arial",Font.BOLD+Font.ITALIC, 18));
        bookNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        memberNameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        livreIdSpinner.setEnabled(false);
        membreIdSpinner.setEnabled(false);
        dateAttributionChooser.setEnabled(false);

        //
        noteTextArea.setFont(new Font("Arial", Font.ITALIC, 18));
        noteTextArea.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        //

        titlePanel.setBackground(new Color(25, 75, 118));
        titleLabel.setForeground(Color.WHITE);

        infoPanel.setBackground(Color.WHITE);
        infoPanelA.setBackground(Color.WHITE);
        infoPanelB.setBackground(Color.WHITE);
        bookIDPanel.setBackground(Color.WHITE);
        bookNamePanel.setBackground(Color.WHITE);
        emptyBookPanel.setBackground(Color.WHITE);
        memberIDPanel.setBackground(Color.WHITE);
        memberNamePanel.setBackground(Color.WHITE);
        emptyMemberPanel.setBackground(Color.WHITE);
        issueDatePanel.setBackground(Color.WHITE);
        returnDatePanel.setBackground(Color.WHITE);
        notePanel.setBackground(Color.WHITE);

        inputPanel.setBackground(Color.WHITE);
        searchPanel.setBackground(Color.WHITE);
        bookStatusPanel.setBackground(Color.WHITE);
        tablePanel.setBackground(Color.WHITE);
        sidePanel.setBackground(Color.WHITE);
        mainSectionPanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);

        mainSectionPanel.add(inputPanel);
        mainSectionPanel.add(sidePanel);


        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 8, 0, new Color(25, 75, 118).darker()));
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
        sidePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                50,
                10
        ));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        infoPanelA.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        infoPanelB.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));

        //

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    id = (int) target.getValueAt(row, 0);

                    Livre livre = librairie.getLivre(id);
                    Membre membre = librairie.getLivre(id).getMembre();
                    try{
                        if (membre != null) {
                            int idMembre = membre.getId();

                            livreIdSpinner.setValue(id);
                            bookNameLabel.setText(livre.getTitre());
                            membreIdSpinner.setValue(idMembre);
                            memberNameLabel.setText(String.format("%s %s", membre.getNom(), membre.getPrenom()));
                            dateAttributionChooser.setDate(livre.getDate_reception());
                            dateRetourChooser.setDate(livre.getDate_de_retour());
                            noteTextArea.setText(livre.getNote());
                        }
                        else {
                            JOptionPane.showMessageDialog(
                                    RetournerLivre.this,
                                    "Le livre n'est pas attribué à aucun membre!"
                            );
                        }
                    }
                    catch (NullPointerException nullptr) {
                        JOptionPane.showMessageDialog(
                                RetournerLivre.this,
                                ""+nullptr.getMessage()
                        );
                    }


                }

            }
        });
        returnButton.addActionListener(e -> {

            if ( dateRetourChooser.getDate() == null) {
                JOptionPane.showMessageDialog(
                        RetournerLivre.this,
                        "Erreur de saisie"
                );
            } else {
                try {
                    Livre livre = librairie.getLivre(id);
                    if (livre == null) {
                        JOptionPane.showMessageDialog(
                                RetournerLivre.this,
                                "impossible de retourner ce livre!"
                        );
                    } else {
                        livre.setIsAvailable(true);
                        livre.setEtat(Etat.RENDU);
                        livre.setNote(noteTextArea.getText());
                        livre.setDate_de_retour(new java.sql.Date(dateRetourChooser.getDate().getTime()));

                        System.out.println(livre);

                        librairie.modifierLivre(livre);
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            RetournerLivre.this,
                            ""+e1.getMessage()
                    );
                }
                tableModeleRetournerLivre.chargerTable(librairie.getAllLivre());
            }

        });
        //
        lostButton.addActionListener(e -> {

            if ( dateRetourChooser.getDate() != null ) {
                JOptionPane.showMessageDialog(
                        RetournerLivre.this,
                        "Erreur, le livre est perdu.\n"+ "Donc, il ne sera pas disponible."
                );
            } else {
                try {
                    Livre livre = librairie.getLivre(id);
                    if (livre == null) {
                        JOptionPane.showMessageDialog(
                                RetournerLivre.this,
                                "Impossible, ce livre n'existe pas."
                        );
                    } else {
                        livre.setIsAvailable(false);
                        livre.setEtat(Etat.PERDU);
                        livre.setNote(noteTextArea.getText());
                        livre.setDate_de_retour(null);

                        System.out.println(livre);

                        librairie.modifierLivre(livre);
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            RetournerLivre.this,
                            ""+e1.getMessage()
                    );
                }
                tableModeleRetournerLivre.chargerTable(librairie.getAllLivre());
            }

        });
        // book exists, but is not available.
        deleteButton.addActionListener(e -> {

            if ( dateRetourChooser.getDate() == null ) {
                JOptionPane.showMessageDialog(
                        RetournerLivre.this,
                        "Erreur, le livre sera disponible."
                );
            } else {
                try {
                    Livre livre = librairie.getLivre(id);
                    if (livre == null) {
                        JOptionPane.showMessageDialog(
                                RetournerLivre.this,
                                "Impossible, ce livre n'existe pas."
                        );
                    } else {
                        livre.setIsAvailable(false);
                        livre.setEtat(Etat.NULL);
                        livre.setNote(noteTextArea.getText());
                        livre.setDate_de_retour(new java.sql.Date(dateRetourChooser.getDate().getTime()));

                        System.out.println(livre);

                        librairie.modifierLivre(livre);
                    }

                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            RetournerLivre.this,
                            ""+e1.getMessage()
                    );
                }
                tableModeleRetournerLivre.chargerTable(librairie.getAllLivre());
            }

        });
        etatJComboBox.addActionListener(e -> {
            List<Livre> list = librairie.getAllLivre();
            if (etatJComboBox.getSelectedIndex() ==  0) {
                tableModeleRetournerLivre.chargerTable(librairie.getAllLivre());
            }
            else{
                list = list.stream()
                        .filter(livre -> livre.getEtat().equals(etatJComboBox.getSelectedItem()))
                        .toList();
            }
        });



        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainSectionPanel, BorderLayout.CENTER);
        this.setVisible(true);


    }
}