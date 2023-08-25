package presentation;

import dao.ILibrairie;
import dao.Librairie;
import metier.entity.Membre;
import presentation.tableModeles.TableModeleMembre;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListeMembre extends JPanel {

    // JPanels
    private final JPanel titlePanel = new JPanel();

    // headlinePanel = searchLabel + inputSearchPanel
    private final JPanel headlinePanel = new JPanel(new BorderLayout(10,10));
    private final JPanel inputSearchPanel = new JPanel(new BorderLayout(20,10));
    private final JPanel searchButtonPanel = new JPanel();
    private final JPanel tablePanel = new JPanel(new BorderLayout(10,20));

    private final JPanel cardPanel = new JPanel(new BorderLayout(10,20));
    private final JPanel infoPanel = new JPanel(new GridLayout(9,1,10,10));

    // Delete section
    private final JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
    private final JPanel deleteButtonPanel = new JPanel();

    private final ILibrairie librairie = Librairie.getLibrairie();
    // TableModeleUtilisateur
    private final TableModeleMembre me = TableModeleMembre.getInstance();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    // JTextfields
    private final JTextField searchTextField = new JTextField();
    // Labels
    private final JLabel titleLabel = new JLabel("Liste des Membres");
    private final JLabel searchLabel = new JLabel("Valeur à chercher:");
    private final JLabel picLabel = new JLabel();
    private final JLabel nomLabel = new JLabel("Nom");
    private final JLabel prenomLabel = new JLabel("Prénom");
    private final JLabel cinLabel = new JLabel("Num CIN");
    private final JLabel numLabel = new JLabel("Num tel");
    private final JLabel birthdayLabel = new JLabel("Date de naissance");
    private final JLabel emailLabel = new JLabel("Email");
    private final JLabel genreLabel = new JLabel("Genre");
    private final JLabel dateAdhesionLabel = new JLabel("Date adhésion");
    private final JLabel adresseLabel = new JLabel("Adresse");

    private final JLabel deleteLabel = new JLabel("Cliquer pour supprimer: ");

    // Buttons
    private final JButton searchButton = new JButton("Rechercher");
    private final JButton deleteButton = new JButton("Supprimer");

    // Image icons
    ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\user.png");
    ImageIcon defaultIcon = new ImageIcon("src\\images\\hellokitty.jpg");

    private final DefaultTableCellRenderer renderer = new CusttomRenderer();

    //
    int id = -1;

    public ListeMembre() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);

        picLabel.setIcon(Utils.resizeImageIcon(defaultIcon, 200, 200));

        me.chargerTable(librairie.getAllMembre());

        titleIcon = Utils.resizeImageIcon(titleIcon, 70, 70);
        titleLabel.setIcon(titleIcon);

        titlePanel.add(titleLabel);

        searchButtonPanel.add(searchButton);
        inputSearchPanel.add(searchTextField, BorderLayout.CENTER);
        inputSearchPanel.add(searchButtonPanel, BorderLayout.EAST);

        headlinePanel.add(searchLabel, BorderLayout.WEST);
        headlinePanel.add(inputSearchPanel, BorderLayout.CENTER);

        infoPanel.add(nomLabel);
        infoPanel.add(prenomLabel);
        infoPanel.add(cinLabel);
        infoPanel.add(numLabel);
        infoPanel.add(birthdayLabel);
        infoPanel.add(emailLabel);
        infoPanel.add(genreLabel);
        infoPanel.add(dateAdhesionLabel);
        infoPanel.add(adresseLabel);

        cardPanel.add(picLabel, BorderLayout.NORTH);
        cardPanel.add(infoPanel, BorderLayout.CENTER);

        deleteButtonPanel.add(deleteButton);
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteButtonPanel);

        tablePanel.add(headlinePanel, BorderLayout.NORTH);
        tablePanel.add(jsp, BorderLayout.CENTER);
        tablePanel.add(deletePanel, BorderLayout.SOUTH);

        this.add(tablePanel, BorderLayout.CENTER);
        this.add(cardPanel, BorderLayout.EAST);
        this.add(titlePanel, BorderLayout.NORTH);

        this.setVisible(true);


        searchButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        searchButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setFocusable(false);
        searchButtonPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        searchButton.setForeground(Color.WHITE);
        searchButtonPanel.setBackground(new Color(153,204,204));

        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setFocusable(false);
        deleteButtonPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        deleteButton.setForeground(Color.WHITE);
        deleteButtonPanel.setBackground(new Color(177, 179, 225));


        // emptyBorders
        infoPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                60,
                10
        ));
        headlinePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                30,
                10,
                30
        ));
        searchLabel.setBorder(BorderFactory.createEmptyBorder(
                0,
                20,
                0,
                0
        ));
        deletePanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                0,
                20,
                0
        ));


        // fonts
        titleLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 40));
        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nomLabel.setFont(new Font("Arial", Font.BOLD, 16));
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cinLabel.setFont(new Font("Arial", Font.BOLD, 16));
        adresseLabel.setFont(new Font("Arial", Font.BOLD, 16));
        numLabel.setFont(new Font("Arial", Font.BOLD, 16));
        birthdayLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dateAdhesionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        genreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        deleteLabel.setFont(new Font("Arial", Font.BOLD, 16));


        // background color
        searchButton.setAlignmentX(CENTER_ALIGNMENT);
        searchButton.setAlignmentY(CENTER_ALIGNMENT);
        searchButton.setForeground(Color.BLACK);

        titlePanel.setBackground(Color.WHITE);
        headlinePanel.setBackground(Color.WHITE);
        inputSearchPanel.setBackground(Color.WHITE);
        searchLabel.setBackground(Color.WHITE);
        tablePanel.setBackground(Color.WHITE);
        cardPanel.setBackground(Color.WHITE);
        picLabel.setBackground(Color.WHITE);
        infoPanel.setBackground(Color.WHITE);
        deletePanel.setBackground(Color.WHITE);

        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.getTableHeader().setBackground(new Color(32,106, 162));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setForeground(Color.WHITE);


        //
        deleteButton.addActionListener(e -> {
            if (id != -1) {
                librairie.supprimerMembre(id);

                JOptionPane.showMessageDialog(
                        ListeMembre.this,
                        "membre supprimé!"
                );

                me.chargerTable(librairie.getAllMembre());
            } else {
                JOptionPane.showMessageDialog(
                        ListeMembre.this,
                        "aucun membre est selectioné"
                );
            }
        });

        //
        searchButton.addActionListener(e -> {
            if (!searchTextField.getText().isBlank()){
                try {
                     List<Membre> list = librairie.getMembrePMC(searchTextField.getText());

                     me.chargerTable(list);
                }
                catch (NumberFormatException e2){
                    e2.printStackTrace();
                }
            }

        });

        //
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    id = (int) target.getValueAt(row, 0);

                    Membre membre = librairie.getMembre(id);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    nomLabel.setText(membre.getNom());
                    prenomLabel.setText(membre.getPrenom());
                    cinLabel.setText(String.valueOf(membre.getCin()));
                    numLabel.setText(String.valueOf(membre.getNumTel()));
                    birthdayLabel.setText(simpleDateFormat.format(membre.getDateDeNaissance()));
                    emailLabel.setText(membre.getEmail());
                    genreLabel.setText(membre.getGenre());
                    dateAdhesionLabel.setText(simpleDateFormat.format(membre.getDateAdhesion()));
                    adresseLabel.setText(membre.getAdresse());

                    try {
                        ImageIcon photo = new ImageIcon(AjouterMembre.rescale(ImageIO.read(membre.getPhoto())));

                        picLabel.setIcon(Utils.resizeImageIcon(photo, 200, 200));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    public static class CusttomRenderer extends DefaultTableCellRenderer {
        Font font = new Font("Arial", Font.PLAIN, 14);

        @Override
        protected void setValue(Object value) {
            setFont(font);
            super.setValue(value);
        }
    }
}
