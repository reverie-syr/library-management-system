package presentation;

import com.toedter.calendar.JDateChooser;
import dao.ILibrairie;
import dao.Librairie;
import lombok.Getter;
import lombok.Setter;
import metier.entity.Genre;
import metier.entity.GenreComboBoxModel;
import metier.entity.Livre;
import presentation.tableModeles.TableModeleLivre;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class ModifierLivre extends JPanel {

    private static ModifierLivre modifierLivre;
    // headlinePanel = titlePanel(WEST) + searchSectionPanel
    private final JPanel headlinePanel = new JPanel(new BorderLayout(10,10));
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JPanel searchButtonPanel = new JPanel(new BorderLayout());
    // mainSection = inputPanel + buttonPanel
    private final JPanel mainSection = new JPanel(new BorderLayout(10,10));
    // inputPanel = inputPanelA + inputPanelB
    private final JPanel inputPanel = new JPanel(new GridLayout(1,2,30,10));
    private final JPanel forumPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel inputPanelA = new JPanel(new GridLayout(8,1,10,10));
    private final JPanel inputPanelB = new JPanel(new BorderLayout(10,10));


    // searchFieldPanel = idPanel + isbnPanel
    // searchSectionPanel = searchFieldPanel + searchButtonPanel
    /** searchFieldPanel(BorderLayout) = 2 panels ( panel 1 = gridlayout(2,1,10,10) inside of it les labels)
     * + panel 2 = (gridlayout(2,1,10,10) les textfields )
     */
    private final JPanel searchFieldPanel = new JPanel(new BorderLayout());
    private final JPanel labelPanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel textFieldPanel = new JPanel(new GridLayout(2,1,10,10));
    private final JPanel searchSectionPanel = new JPanel(new BorderLayout(10,10));

    private final JPanel titrePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel auteurPanel = new JPanel(new GridLayout(1,3,10,10));
    private final JPanel genrePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel quantitePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel editeurPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel prixPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel datePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel descriptionPanel = new JPanel(new BorderLayout(10,10));

    //PHOTO SECTION
    // PhotoSectionPanel = photo + browseButton + modifierButton + annulerButton
    private final JPanel photoSectionPanel = new JPanel(new BorderLayout(30,50));
    private final JPanel photoPanel = new JPanel(new BorderLayout());
    private final JLabel couvertureLabel = new JLabel("Couvertutre du livre: ");
    private final JPanel photoPathPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JLabel photoPathLabel = new JLabel("Choisissez  une photo.. ");
    private final JPanel photoPanelStyle = new JPanel(new BorderLayout(0,0));
    private final JPanel photoSelectionPanel = new JPanel(new BorderLayout(20,10));
    private final JPanel photoButtonPanel = new JPanel(new BorderLayout());
    private final JPanel buttonPicPanel = new JPanel(new GridLayout(1,2,20,0));

    private final JPanel ajouterPhotoPanel = new JPanel();
    private final JPanel annulerPhotoPanel = new JPanel();
    //
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel modifierPanel = new JPanel();
    private final JPanel annulerPanel = new JPanel();

    private final JButton modifier = new JButton("Modifier Livre");
    private final JButton annuler = new JButton("Annuler");
    private final JButton ajouterPhoto = new JButton("Ajouter Photo");
    private final JButton annulerPhoto = new JButton("Annuler ");
    private final JButton modifierAuteur = new JButton("Modifier l'auteur.");
    private final JButton searchButton = new JButton("Rechercher par ID our ISBN");

    private final JLabel idLabel = new JLabel("ID: ");
    private final JLabel titleLabel = new JLabel("Modifier un livre");
    private final JLabel isbnLabel = new JLabel("ISBN: ");
    private final JLabel titreLabel = new JLabel("Titre: ");
    private final JLabel auteurLabel = new JLabel("Auteur: ");
    private final JLabel genreLabel = new JLabel("Genre: ");
    private final JLabel quantiteLabel = new JLabel("Quantite: ");
    private final JLabel editeurLabel = new JLabel("Editeur: ");
    private final JLabel prixLabel = new JLabel("Prix: ");
    private final JLabel dateLabel = new JLabel("Date de réception: ");
    private final JLabel descriptionLabel = new JLabel("Description du livre: ");


    private final JTextField isbnTextField = new JTextField();
    private final JTextField idTextField = new JTextField();
    private final JTextField titreTextField = new JTextField();
    private final JTextField auteurTextField = new JTextField();

    private GenreComboBoxModel comboBoxModel = new GenreComboBoxModel();
    private JComboBox<Genre> genreComboBox = new JComboBox<>(comboBoxModel);

    private SpinnerModel quantiteModel = new SpinnerNumberModel(1,0,100,1);
    private JSpinner quantiteSpinner = new JSpinner(quantiteModel);

    private JTextField editeurTextField = new JTextField();

    private JTextField prixTextField = new JTextField();
    private JDateChooser dateReceptionChooser =new JDateChooser();

    private JTextArea descriptionTextArea = new JTextArea(2,10);
    private JScrollPane scrollPane = new JScrollPane(descriptionTextArea);

    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\modifierLivreIcon.png");

    File targetFile;
    BufferedImage targetImg;
    ImageIcon icon;
    JLabel imglabel;
    File file, photo;
    // creation du Librairie Object
    ILibrairie librairie = Librairie.getLibrairie();
    // creation du TableModeleLivre Object
    TableModeleLivre tableModeleLivre = TableModeleLivre.getInstance();

    private static final int baseSize = 210;
    private static final String basePath =
            "C:\\Images";

    ListAuteursDialog listAuteursDialog;
    private ModifierLivre(){

        //super(new BorderLayout(10,30));
        this.setLayout(new BorderLayout(10,30));
        this.setBackground(Color.WHITE);

        titleIcon = Utils.resizeImageIcon(titleIcon, 80, 80);


        // fonts
        // labels
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 40));
        isbnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        auteurLabel.setFont(new Font("Arial", Font.BOLD, 20));
        genreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        quantiteLabel.setFont(new Font("Arial", Font.BOLD, 20));
        editeurLabel.setFont(new Font("Arial", Font.BOLD, 20));
        prixLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        descriptionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        couvertureLabel.setFont(new Font("Arial", Font.BOLD, 20));
        photoPathLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 16));

        idTextField.setFont(new Font("Arial", Font.ITALIC, 20));
        isbnTextField.setFont(new Font("Arial", Font.ITALIC, 20));
        titreTextField.setFont(new Font("Arial", Font.ITALIC, 20));
        auteurTextField.setFont(new Font("Arial", Font.ITALIC, 20));
        genreComboBox.setFont(new Font("Arial", Font.ITALIC, 20));
        quantiteSpinner.setFont(new Font("Arial", Font.ITALIC, 20));
        editeurTextField.setFont(new Font("Arial", Font.ITALIC, 20));
        prixTextField.setFont(new Font("Arial", Font.ITALIC, 20));
        dateReceptionChooser.setFont(new Font("Arial", Font.PLAIN, 20));
        descriptionTextArea.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 20));

        // setting up colors
        photoPathLabel.setForeground(new Color(235,69, 95));
        // buttons settings
        searchButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        searchButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);
        searchButton.setFocusable(false);
        searchButtonPanel.add(searchButton);

        modifier.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        modifier.setFont(new Font("Arial", Font.BOLD, 18));
        modifier.setContentAreaFilled(false);
        modifier.setFocusPainted(false);
        modifier.setFocusable(false);
        modifierPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        annuler.setBorder(BorderFactory.createLineBorder(new Color(1,1, 0, Transparency.TRANSLUCENT)));
        annuler.setFont(new Font("Arial", Font.BOLD, 18));
        annuler.setContentAreaFilled(false);
        annuler.setFocusPainted(false);
        annuler.setFocusable(false);
        annulerPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));


        ajouterPhoto.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        ajouterPhoto.setFont(new Font("Arial", Font.BOLD, 18));
        ajouterPhoto.setContentAreaFilled(false);
        ajouterPhoto.setFocusPainted(false);
        ajouterPhoto.setFocusable(false);
        ajouterPhotoPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        annulerPhoto.setBorder(BorderFactory.createLineBorder(new Color(1,1, 0, Transparency.TRANSLUCENT)));
        annulerPhoto.setFont(new Font("Arial", Font.BOLD, 18));
        annulerPhoto.setContentAreaFilled(false);
        annulerPhoto.setFocusPainted(false);
        annulerPhoto.setFocusable(false);
        annulerPhotoPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        modifierAuteur.setBorder(BorderFactory.createLineBorder(Color.black));
        modifierAuteur.setFont(new Font("Arial", Font.BOLD, 16));
        modifierAuteur.setFocusPainted(false);
        modifierAuteur.setFocusable(false);
        modifierAuteur.setBackground(new Color(0,116,179));
        modifierAuteur.setForeground(Color.WHITE);
        modifierAuteur.setRolloverEnabled(true);
        modifierAuteur.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                modifierAuteur.setBackground(new Color(181, 82, 92));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                modifierAuteur.setBackground(new Color(0,116,179));
            }
        });



        titleLabel.setIcon(titleIcon);
        titlePanel.add(titleLabel);
        
        titrePanel.add(titreLabel);
        titrePanel.add(titreTextField);
        auteurPanel.add(auteurLabel);
        auteurPanel.add(auteurTextField);
        auteurPanel.add(modifierAuteur);


        genrePanel.add(genreLabel);
        genrePanel.add(genreComboBox);
        //
        quantitePanel.add(quantiteLabel);
        quantitePanel.add(quantiteSpinner);
        //
        editeurPanel.add(editeurLabel);
        editeurPanel.add(editeurTextField);
        //
        prixPanel.add(prixLabel);
        prixPanel.add(prixTextField);
        //
        datePanel.add(dateLabel);
        datePanel.add(dateReceptionChooser);
        //
        scrollPane.setPreferredSize(new Dimension(75,75));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        descriptionPanel.add(descriptionLabel, BorderLayout.NORTH);
        descriptionPanel.add(scrollPane, BorderLayout.CENTER);
        //
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setLineWrap(true);
        //

        descriptionTextArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        inputPanelA.add(titrePanel);
        inputPanelA.add(auteurPanel);
        inputPanelA.add(genrePanel);
        inputPanelA.add(quantitePanel);
        inputPanelA.add(editeurPanel);
        inputPanelA.add(prixPanel);
        inputPanelA.add(datePanel);

        forumPanel.add(inputPanelA, BorderLayout.CENTER);
        forumPanel.add(descriptionPanel, BorderLayout.SOUTH);

        ButtonGroup bgLivre = new ButtonGroup();
        bgLivre.add(modifier);
        bgLivre.add(annuler);
        //
        modifierPanel.add(modifier);
        annulerPanel.add(annuler);
        //
        buttonsPanel.add(modifierPanel);
        buttonsPanel.add(annulerPanel);

        ButtonGroup bgPhoto = new ButtonGroup();
        bgPhoto.add(ajouterPhoto);
        bgPhoto.add(annulerPhoto);
        ajouterPhotoPanel.add(ajouterPhoto);
        annulerPhotoPanel.add(annulerPhoto);

        buttonPicPanel.add(ajouterPhotoPanel);
        buttonPicPanel.add(annulerPhotoPanel);

        photoButtonPanel.add(buttonPicPanel, BorderLayout.CENTER);

        photoPathPanel.add(photoPathLabel);
        photoButtonPanel.add(photoPathPanel, BorderLayout.WEST);
        photoSelectionPanel.add(couvertureLabel, BorderLayout.NORTH);
        photoSelectionPanel.add(photoButtonPanel, BorderLayout.CENTER);


        photoSectionPanel.add(photoSelectionPanel, BorderLayout.NORTH);
        photoSectionPanel.add(photoPanelStyle, BorderLayout.CENTER);
        photoSectionPanel.add(buttonsPanel, BorderLayout.SOUTH);

        inputPanelB.add(photoSectionPanel, BorderLayout.CENTER);

        inputPanel.add(forumPanel);
        inputPanel.add(inputPanelB);

        inputPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        mainSection.setBorder(BorderFactory.createEmptyBorder(
                0,
                10,
                0,
                10
        ));

        inputPanelA.setBorder(BorderFactory.createEmptyBorder(
                10,
                20,
                0,
                10
        ));
        descriptionPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                20,
                20,
                20
        ));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                400,
                30,
                400
        ));
        photoSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                0,
                10
        ));
        photoPanelStyle.setBorder(BorderFactory.createEmptyBorder(
                0,
                100,
                50,
                100
        ));
        photoButtonPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                50,
                10,
                50
        ));
        searchFieldPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        searchButtonPanel.setBorder(BorderFactory.createEmptyBorder(
                30,
                30,
                30,
                30
        ));
        searchSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                20,
                20,
                20,
                20
        ));

        modifier.setForeground(Color.WHITE);
        annuler.setForeground(Color.WHITE);
        //
        ajouterPhoto.setForeground(Color.WHITE);
        annulerPhoto.setForeground(Color.WHITE);
        //
        ajouterPhotoPanel.setBackground(new Color(0,116,179));
        annulerPhotoPanel.setBackground(new Color(181, 82, 92));
        //
        modifierPanel.setBackground(new Color(43, 52,103));
        annulerPanel.setBackground(new Color(235, 69, 95));
        //
        searchButton.setForeground(Color.WHITE);
        searchButtonPanel.setBackground(new Color(43, 52,103));


        titlePanel.setBackground(Color.WHITE);
        mainSection.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        inputPanelA.setBackground(Color.WHITE);
        inputPanelB.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        //
        photoPanel.setBackground(Color.WHITE);
        photoPanelStyle.setBackground(Color.WHITE);
        photoSectionPanel.setBackground(Color.WHITE);
        photoSelectionPanel.setBackground(Color.WHITE);
        photoButtonPanel.setBackground(Color.WHITE);
        buttonPicPanel.setBackground(Color.WHITE);
        photoPathPanel.setBackground(Color.WHITE);

        //
        titrePanel.setBackground(Color.WHITE);
        auteurPanel.setBackground(Color.WHITE);
        genrePanel.setBackground(Color.WHITE);
        quantitePanel.setBackground(Color.WHITE);
        editeurPanel.setBackground(Color.WHITE);
        prixPanel.setBackground(Color.WHITE);
        datePanel.setBackground(Color.WHITE);
        descriptionPanel.setBackground(Color.WHITE);

        dateReceptionChooser.setBackground(Color.WHITE);
        genreComboBox.setBackground(Color.WHITE);
        //
        forumPanel.setBackground(Color.WHITE);
        //
        searchSectionPanel.setBackground(Color.WHITE);
        searchFieldPanel.setBackground(Color.WHITE);
        labelPanel.setBackground(Color.WHITE);
        textFieldPanel.setBackground(Color.WHITE);
        headlinePanel.setBackground(Color.WHITE);


        photoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1, true));
        photoPanelStyle.add(photoPanel);


        labelPanel.add(idLabel);
        labelPanel.add(isbnLabel);

        textFieldPanel.add(idTextField);
        textFieldPanel.add(isbnTextField);

        searchFieldPanel.add(labelPanel, BorderLayout.WEST);
        searchFieldPanel.add(textFieldPanel, BorderLayout.CENTER);

        searchSectionPanel.add(searchFieldPanel, BorderLayout.CENTER);
        searchSectionPanel.add(searchButtonPanel, BorderLayout.EAST);

        headlinePanel.add(titlePanel, BorderLayout.WEST);
        headlinePanel.add(searchSectionPanel, BorderLayout.CENTER);

        mainSection.add(inputPanel, BorderLayout.CENTER);
        mainSection.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(headlinePanel, BorderLayout.NORTH);
        this.add(mainSection, BorderLayout.CENTER);


        ajouterPhoto.addActionListener(this::browseButtonActionPerformed);

        annulerPhoto.addActionListener(e-> imglabel.setIcon(null));

        modifierAuteur.addActionListener(e -> {
            listAuteursDialog = new ListAuteursDialog(this);
            listAuteursDialog.setVisible(true);

        });

        modifier.addActionListener(e -> {
            if (titreTextField.getText().isBlank()
                    || auteurTextField.getText().isBlank()
                    || genreComboBox.getSelectedItem() == null
                    || quantiteSpinner.getValue() == null
                    || editeurTextField.getText().isBlank()
                    || prixTextField.getText().isBlank()
                    || getDateReceptionChooser().getDate() == null
                    || descriptionTextArea.getText().isBlank()
            ) {
                JOptionPane.showMessageDialog(
                        ModifierLivre.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());

                    Livre livre = librairie.getLivre(id);
                        if (livre == null) {
                            JOptionPane.showMessageDialog(
                                    ModifierLivre.this,
                                    "impossible de faire la mise à jour de ce livre!"
                            );
                        } else {
                            livre.setTitre(titreTextField.getText());
                            livre.setAuteur(librairie.getAuteurPMC(auteurTextField.getText()));
                            livre.setGenre((Genre)genreComboBox.getSelectedItem());
                            livre.setQuantite((int)quantiteSpinner.getValue());
                            livre.setEditeur(editeurTextField.getText());
                            livre.setPrix(Double.parseDouble(prixTextField.getText()));
                            livre.setDate_reception(new java.sql.Date(getDateReceptionChooser().getDate().getTime()));
                            livre.setDescription(descriptionTextArea.getText());

                            System.out.println(livre);

                            if (photo== null) {
                                photo = new File("src\\images\\noimage.jpg");

                            }
                            livre.setPhoto(photo);

                            if (photo.length() < 65_535) { // 65_535 = maximum size of  a blob
                                JOptionPane.showMessageDialog(
                                        ModifierLivre.this,
                                        "livre a été modifié!"
                                );

                                librairie.modifierLivre(livre);
                            } else {
                                JOptionPane.showMessageDialog(
                                        ModifierLivre.this,
                                        "La photo est trop longue!"
                                );
                            }
                        }



                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            ModifierLivre.this,
                            ""+e1.getMessage()
                    );
                }

                tableModeleLivre.chargerTable(librairie.getAllLivre());
            }
        });
        searchButton.addActionListener(e -> {
            if (!idTextField.getText().isBlank() || !isbnTextField.getText().isBlank()){
                try {
                    if (!isbnTextField.getText().isBlank() && idTextField.getText().isBlank()) {
                        int id = Integer.parseInt(isbnTextField.getText());
                        Livre livre = librairie.getLivreByISBN(id);

                        if (livre != null){
                            System.out.println(livre);
                            titreTextField.setText(livre.getTitre());
                            auteurTextField.setText(livre.getAuteur().toString());
                            genreComboBox.setSelectedItem(livre.getGenre());
                            genreComboBox.repaint();
                            quantiteSpinner.setValue(livre.getQuantite());
                            editeurTextField.setText(livre.getEditeur());
                            prixTextField.setText(String.valueOf(livre.getPrix()));
                            dateReceptionChooser.setDate(livre.getDate_reception());
                            descriptionTextArea.setText(livre.getDescription());

                            setTarget(livre.getPhoto());
                        }
                        else{
                            JOptionPane.showMessageDialog(
                                    ModifierLivre.this,
                                    "Livre non trouvé!");
                        }
                    } else {
                        int id = Integer.parseInt(idTextField.getText());
                        Livre livre = librairie.getLivre(id);

                        if (livre != null){
                            System.out.println(livre);
                            titreTextField.setText(livre.getTitre());
                            auteurTextField.setText(livre.getAuteur().toString());
                            genreComboBox.setSelectedItem(livre.getGenre());
                            genreComboBox.repaint();
                            quantiteSpinner.setValue(livre.getQuantite());
                            editeurTextField.setText(livre.getEditeur());
                            prixTextField.setText(String.valueOf(livre.getPrix()));
                            dateReceptionChooser.setDate(livre.getDate_reception());
                            descriptionTextArea.setText(livre.getDescription());

                            setTarget(livre.getPhoto());
                        } else{
                            id = Integer.parseInt(isbnTextField.getText());
                            livre = librairie.getLivreByISBN(id);

                            if (livre != null){
                                System.out.println(livre);
                                titreTextField.setText(livre.getTitre());
                                auteurTextField.setText(livre.getAuteur().toString());
                                genreComboBox.setSelectedItem(livre.getGenre());
                                genreComboBox.repaint();
                                quantiteSpinner.setValue(livre.getQuantite());
                                editeurTextField.setText(livre.getEditeur());
                                prixTextField.setText(String.valueOf(livre.getPrix()));
                                dateReceptionChooser.setDate(livre.getDate_reception());
                                descriptionTextArea.setText(livre.getDescription());

                                setTarget(livre.getPhoto());
                            } else{
                                JOptionPane.showMessageDialog(
                                        ModifierLivre.this,
                                        "Livre non trouvé!");
                            }
                        }
                    }
                }
                catch (NumberFormatException e2){
                    e2.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        ModifierLivre.this,
                        "Erreur de saisie, faut remplir les champs!");
            }

        });

        annuler.addActionListener(e -> {
            isbnTextField.setText("");
            titreTextField.setText("");
            auteurTextField.setText("");
            genreComboBox.setSelectedItem(null);
            quantiteSpinner.setValue(1);
            editeurTextField.setText("");
            prixTextField.setText("");
            dateReceptionChooser.setDate(null);
            descriptionTextArea.setText("");
            imglabel.setIcon(null);

        });



    }
    public static BufferedImage rescale(BufferedImage originalImage)
    {
        BufferedImage resizedImage = new BufferedImage(baseSize, baseSize+60, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, baseSize, baseSize+60, null);
        g.dispose();
        return resizedImage;
    }
    public void setTarget(File reference)
    {
        try {
            targetFile = reference;
            targetImg = rescale(ImageIO.read(reference));
        } catch (IOException ex) {
            Logger.getLogger(ModifierLivre.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (icon == null && imglabel == null) {
            icon=new ImageIcon(targetImg);
            imglabel=new JLabel();

            imglabel.setHorizontalAlignment(JLabel.CENTER);
            imglabel.setVerticalAlignment(JLabel.CENTER);

            photoPanel.add(imglabel, BorderLayout.CENTER);
        }
        icon=new ImageIcon(targetImg);
        imglabel.setIcon(icon);
    }
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new ImageFileFilter());
        int res = fc.showOpenDialog(this);
        // We have an image!
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                setTarget(file);
            } // Oops!
            else {
                JOptionPane.showMessageDialog(null,
                        "Vous devez choisir une image comme référence.", "Abandon...",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException) {
            iOException.printStackTrace();
        }

    }


    public static ModifierLivre getInstance() {
        if (modifierLivre == null) {
            modifierLivre = new ModifierLivre();
        }

        return modifierLivre;
    }

}