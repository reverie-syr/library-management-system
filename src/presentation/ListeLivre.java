package presentation;

import com.toedter.calendar.JDateChooser;
import dao.ILibrairie;
import dao.Librairie;
import lombok.Getter;
import metier.entity.Livre;
import presentation.tableModeles.TableModeleLivre;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Getter
public class ListeLivre extends JPanel {

    // JPanels
    private final JPanel titlePanel = new JPanel();
    /* topPanel = 2 panels (searchPanel1 and searchPanel2)
    * searchPanel1 (GridLayout(2, 1, 10, 10)) = searchByBookNameLabel + headlinePanel (borderlayout(10,10) : {label : west + gridlayout(1, 2) {
        textfield + button
    } : center})
    * searchPanel2 (GridLayout(2, 1, 10, 10)) = searchByBookBetweenTwoDatesTitleLabel + searchDateSectionPanel
    * searchDateSectionPanel (BorderLayout(10, 10)) =
    *   searchBookPanel : center +
    *   searchByBookBetweenTwoDatesLabel : west
    *
    * searchBookPanel(Grid(1,4)) = searchByBookBetweenTwoDateDate1 + andLabel
    *                                                   + searchByBookBetweenTwoDates2 + searchByBookBetweenTwoDatesButton
    */
    private final JPanel searchPanelA = new JPanel(new GridLayout(2, 1, 10, 10));
    private final JPanel searchPanelB = new JPanel(new GridLayout(2, 1, 10, 10));
    private final JPanel searchDateSectionPanel = new JPanel();
    private final JPanel topPanelStyle = new JPanel(new GridLayout(1,1));
    private final JPanel topPanel = new JPanel(new BorderLayout(10, 10));
    // headlinePanel = searchLabel + inputSearchPanel
    private final JPanel headlinePanel = new JPanel(new BorderLayout(10,10));
    private final JPanel inputSearchPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel tablePanel = new JPanel(new BorderLayout(10,20));

    private final JPanel cardPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel infoPanel = new JPanel(new GridLayout(6,1,5,10));

    //
    private final BoxLayout layout = new BoxLayout(searchDateSectionPanel, BoxLayout.X_AXIS);

    // Delete section
    private final JPanel deletePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
    private final JPanel deleteButtonPanel = new JPanel();

    private final ILibrairie librairie = Librairie.getLibrairie();
    // TableModeleUtilisateur
    private final TableModeleLivre me = TableModeleLivre.getInstance();
    // JTable
    private final JTable table = new JTable(me);
    // JScrollPane
    private final JScrollPane jsp = new JScrollPane(table);

    // JTextfields
    private final JTextField searchTextField = new JTextField();
    // Labels
    private final JLabel titleLabel = new JLabel("Liste des Livres");
    private final JLabel searchLabel = new JLabel("Valeur à chercher:");
    private final JLabel picLabel = new JLabel();
    private final JLabel andLabel = new JLabel("Et: ");
    private final JLabel searchDateSectionLabel = new JLabel("Date de réception:");
    private final JLabel searchPanelATitleLabel = new JLabel("Chercher par titre our par description");
    private final JLabel searchPanelBTitleLabel = new JLabel("Chercher un livre entre deux dates de réception.");
    //
    private final JLabel isbnLabel = new JLabel("ISBN: ");
    private final JLabel titreLabel = new JLabel("Titre: ");
    private final JLabel auteurLabel = new JLabel("Auteur: ");
    private final JLabel genreLabel = new JLabel("Genre: ");
    private final JLabel prixLabel = new JLabel("Prix: ");
    private final JLabel dateLabel = new JLabel("Date de réception: ");

    private final JLabel deleteLabel = new JLabel("Cliquer pour supprimer: ");

    // Buttons
    private final JButton searchButton = new JButton("Rechercher");
    private final JButton searchBookButton = new JButton("Rechercher");
    private final JButton deleteButton = new JButton("Supprimer");

    // Image icons
    ImageIcon defaultIcon = new ImageIcon("src\\images\\noimage.jpg");

    private final DefaultTableCellRenderer renderer = new CusttomRenderer();

    // JDateChoosers
    private JDateChooser date1Chooser = new JDateChooser();
    private JDateChooser date2Chooser = new JDateChooser();

    // Image icons
    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\supprimerLivreIcon.png");
    int id = -1;

    public ListeLivre() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.WHITE);

        picLabel.setIcon(Utils.resizeImageIcon(defaultIcon, 180, 240));

        me.chargerTable(librairie.getAllLivre());

        titleIcon = Utils.resizeImageIcon(titleIcon, 70, 70);
        titleLabel.setIcon(titleIcon);

        titlePanel.add(titleLabel);

        inputSearchPanel.add(searchTextField);
        inputSearchPanel.add(searchButton);

        headlinePanel.add(searchLabel, BorderLayout.WEST);
        headlinePanel.add(inputSearchPanel, BorderLayout.CENTER);

        infoPanel.add(isbnLabel);
        infoPanel.add(titreLabel);
        infoPanel.add(auteurLabel);
        infoPanel.add(genreLabel);
        infoPanel.add(prixLabel);
        infoPanel.add(dateLabel);

        cardPanel.add(picLabel, BorderLayout.NORTH);
        cardPanel.add(infoPanel, BorderLayout.CENTER);

        deleteButtonPanel.add(deleteButton);
        deletePanel.add(deleteLabel);
        deletePanel.add(deleteButtonPanel);

        andLabel.setHorizontalAlignment(JLabel.CENTER);

        //
        searchDateSectionPanel.setLayout(layout);

        // searchDateSectionPanel
        searchDateSectionPanel.add(searchDateSectionLabel);
        searchDateSectionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchDateSectionPanel.add(date1Chooser);
        searchDateSectionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchDateSectionPanel.add(andLabel);
        searchDateSectionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchDateSectionPanel.add(date2Chooser);
        searchDateSectionPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchDateSectionPanel.add(searchBookButton);

        // searchPanel2
        searchPanelB.add(searchPanelBTitleLabel);
        searchPanelB.add(searchDateSectionPanel);

        // searchPanel1
        searchPanelA.add(searchPanelATitleLabel);
        searchPanelA.add(headlinePanel);

        //
        topPanel.add(searchPanelA, BorderLayout.WEST);
        topPanel.add(searchPanelB, BorderLayout.CENTER);

        topPanelStyle.add(topPanel);
        tablePanel.add(topPanelStyle, BorderLayout.NORTH);
        tablePanel.add(cardPanel, BorderLayout.EAST);
        tablePanel.add(jsp, BorderLayout.CENTER);
        tablePanel.add(deletePanel, BorderLayout.SOUTH);

        this.add(tablePanel, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);

        this.setVisible(true);


        searchButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setFocusable(false);
        searchButton.setForeground(Color.BLACK);

        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        deleteButton.setFont(new Font("Arial", Font.BOLD, 15));
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setFocusable(false);
        deleteButtonPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        deleteButton.setForeground(Color.WHITE);
        deleteButtonPanel.setBackground(new Color(177, 179, 225));


        searchBookButton.setFont(new Font("Arial", Font.BOLD, 15));
        searchBookButton.setContentAreaFilled(false);
        searchBookButton.setFocusPainted(false);
        searchBookButton.setFocusable(false);
        searchBookButton.setForeground(Color.BLACK);

        // emptyBorders
        infoPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));

        searchPanelA.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        searchPanelB.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        deletePanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                0,
                20,
                0
        ));
        tablePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        //
        topPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        //
        topPanelStyle.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));


        // fonts
        titleLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 40));
        searchLabel.setFont(new Font("Arial", Font.BOLD, 16));

        searchPanelATitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchPanelBTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        titleLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 40));
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        auteurLabel.setFont(new Font("Arial", Font.BOLD, 20));
        genreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        prixLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));

        andLabel.setFont(new Font("Arial", Font.BOLD, 16));
        searchDateSectionLabel.setFont(new Font("Arial", Font.BOLD, 16));

        //
        deleteLabel.setFont(new Font("Arial", Font.BOLD, 18));

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

        topPanel.setBackground(Color.WHITE);
        topPanelStyle.setBackground(Color.WHITE);
        headlinePanel.setBackground(Color.WHITE);
        searchPanelA.setBackground(Color.WHITE);
        searchPanelB.setBackground(Color.WHITE);
        searchDateSectionPanel.setBackground(Color.WHITE);


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
                librairie.supprimerLivre(id);

                JOptionPane.showMessageDialog(
                        ListeLivre.this,
                        "livre supprimé!"
                );

                me.chargerTable(librairie.getAllLivre());
            } else {
                JOptionPane.showMessageDialog(
                        ListeLivre.this,
                        "aucun livre est selectioné"
                );
            }
        });

        //
        searchButton.addActionListener(e -> {
            if (!searchTextField.getText().isBlank()){
                try {
                    List<Livre> list = librairie.getLivrePMC(searchTextField.getText());

                    me.chargerTable(list);
                }
                catch (NumberFormatException e2){
                    e2.printStackTrace();
                }
            }
        });

        //
        searchBookButton.addActionListener(e -> {
            if (getDate1Chooser().getDate() != null && getDate2Chooser().getDate() != null){
                try {
                    List<Livre> list = librairie.getLivrePDate(
                            new java.sql.Date(getDate1Chooser().getDate().getTime()),
                            new java.sql.Date(getDate2Chooser().getDate().getTime())
                    );

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

                    Livre livre = librairie.getLivre(id);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    isbnLabel.setText(String.valueOf(livre.getISBN()));
                    titreLabel.setText(livre.getTitre());
                    auteurLabel.setText(livre.getAuteur().toString());
                    genreLabel.setText(livre.getGenre().toString());
                    prixLabel.setText(String.valueOf(livre.getPrix()));
                    dateLabel.setText(simpleDateFormat.format(livre.getDate_reception()));

                    try {
                        ImageIcon photo = new ImageIcon(AjouterMembre.rescale(ImageIO.read(livre.getPhoto())));

                        picLabel.setIcon(Utils.resizeImageIcon(photo, 180, 240));
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

