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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class AjouterLivre extends JPanel {

    private static AjouterLivre ajouterLivre;

    private final JPanel titlePanel = new JPanel();
    private final JPanel mainSection = new JPanel(new BorderLayout(10,10));
    private final JPanel inputPanel = new JPanel(new GridLayout(1,2,30,10));
    private final JPanel inputPanelA = new JPanel(new GridLayout(8,1,10,10));
    private final JPanel inputPanelB = new JPanel(new BorderLayout(10,10));

    private final JPanel isbnPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel titrePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel auteurPanel = new JPanel(new GridLayout(1,3,10,10));
    private final JPanel genrePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel quantitePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel editeurPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel prixPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel datePanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel descriptionPanel = new JPanel(new BorderLayout(10,10));

    //PHOTO SECTION
    // PhotoSectionPanel = photo + browseButton + ajouterButton + annulerButton
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
    private final JPanel ajouterPanel = new JPanel();
    private final JPanel annulerPanel = new JPanel();

    private final JButton ajouter = new JButton("Ajouter Livre");
    private final JButton annuler = new JButton("Annuler");
    private final JButton ajouterPhoto = new JButton("Ajouter");
    private final JButton annulerPhoto = new JButton("Annuler ");
    private final JButton selectionnerAuteur = new JButton("Sélectionner l'auteur.");

    private final JLabel titleLabel = new JLabel("Ajouter un livre");
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
    private final JTextField titreTextField = new JTextField();
    private final JTextField auteurTextField = new JTextField();

    private JComboBox<Genre> genreComboBox = new JComboBox<>(new GenreComboBoxModel());

    private SpinnerModel quantiteModel = new SpinnerNumberModel(1,0,100,1);
    private JSpinner quantiteSpinner = new JSpinner(quantiteModel);

    private JTextField editeurTextField = new JTextField();

    private JTextField prixTextField = new JTextField();
    private JDateChooser dateReceptionChooser =new JDateChooser();

    private JTextArea descriptionTextArea = new JTextArea(1,10);
    private JScrollPane scrollPane = new JScrollPane(descriptionTextArea);

    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\book-mark.png");

    File targetFile;
    BufferedImage targetImg;
    ImageIcon icon;
    JLabel imglabel;
    File file;
    // creation du Librairie Object
    ILibrairie librairie = Librairie.getLibrairie();
    // creation du TableModeleLivre Object
    TableModeleLivre tableModeleLivre = TableModeleLivre.getInstance();

    private static final int baseSize = 210;
    private static final String basePath =
            "C:\\Images";

    ListAuteursDialog listAuteursDialog;
    private AjouterLivre(){

        this.setLayout(new BorderLayout(10,30));
        this.setBackground(Color.WHITE);

        titleIcon = Utils.resizeImageIcon(titleIcon, 80, 80);


        // fonts
        // labels
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
        ajouter.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        ajouter.setFont(new Font("Arial", Font.BOLD, 18));
        ajouter.setContentAreaFilled(false);
        ajouter.setFocusPainted(false);
        ajouter.setFocusable(false);
        ajouterPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

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

        selectionnerAuteur.setBorder(BorderFactory.createLineBorder(Color.black));
        selectionnerAuteur.setFont(new Font("Arial", Font.BOLD, 16));
        selectionnerAuteur.setFocusPainted(false);
        selectionnerAuteur.setFocusable(false);
        selectionnerAuteur.setBackground(new Color(0,116,179));
        selectionnerAuteur.setForeground(Color.WHITE);
        selectionnerAuteur.setRolloverEnabled(true);
        selectionnerAuteur.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                selectionnerAuteur.setBackground(new Color(181, 82, 92));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                selectionnerAuteur.setBackground(new Color(0,116,179));
            }
        });



        titleLabel.setIcon(titleIcon);
        titlePanel.add(titleLabel);

        isbnPanel.add(isbnLabel);
        isbnPanel.add(isbnTextField);
        titrePanel.add(titreLabel);
        titrePanel.add(titreTextField);
        auteurPanel.add(auteurLabel);
        auteurPanel.add(auteurTextField);
        auteurPanel.add(selectionnerAuteur);


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


        inputPanelA.add(isbnPanel);
        inputPanelA.add(titrePanel);
        inputPanelA.add(auteurPanel);
        inputPanelA.add(genrePanel);
        inputPanelA.add(quantitePanel);
        inputPanelA.add(editeurPanel);
        inputPanelA.add(prixPanel);
        inputPanelA.add(datePanel);

        ButtonGroup bgLivre = new ButtonGroup();
        bgLivre.add(ajouter);
        bgLivre.add(annuler);
        //
        ajouterPanel.add(ajouter);
        annulerPanel.add(annuler);
        //
        buttonsPanel.add(ajouterPanel);
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

        inputPanelB.add(descriptionPanel, BorderLayout.NORTH);
        inputPanelB.add(photoSectionPanel, BorderLayout.CENTER);

        inputPanel.add(inputPanelA);
        inputPanel.add(inputPanelB);

        titlePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        inputPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        mainSection.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        inputPanelA.setBorder(BorderFactory.createEmptyBorder(
                10,
                20,
                0,
                10
        ));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                400,
                40,
                400
        ));
        photoSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                50,
                10
        ));
        photoPanelStyle.setBorder(BorderFactory.createEmptyBorder(
                0,
                70,
                0,
                70
        ));
        photoButtonPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                100,
                0,
                100
        ));

        ajouter.setForeground(Color.WHITE);
        annuler.setForeground(Color.WHITE);
        //
        ajouterPhoto.setForeground(Color.WHITE);
        annulerPhoto.setForeground(Color.WHITE);
        //
        ajouterPhotoPanel.setBackground(new Color(0,116,179));
        annulerPhotoPanel.setBackground(new Color(181, 82, 92));
        //
        ajouterPanel.setBackground(new Color(43, 52,103));
        annulerPanel.setBackground(new Color(235, 69, 95));


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
        isbnPanel.setBackground(Color.WHITE);
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


        photoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1, true));
        photoPanelStyle.add(photoPanel);

        mainSection.add(inputPanel, BorderLayout.CENTER);
        mainSection.add(buttonsPanel, BorderLayout.SOUTH);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainSection, BorderLayout.CENTER);


        ajouterPhoto.addActionListener(this::browseButtonActionPerformed);

        annulerPhoto.addActionListener(e-> imglabel.setIcon(null));

        selectionnerAuteur.addActionListener(e -> {
            listAuteursDialog = new ListAuteursDialog(this);
            listAuteursDialog.setVisible(true);

        });

        ajouter.addActionListener(e->{
            if (
                    isbnTextField.getText().isBlank()
                            || titreTextField.getText().isBlank()
                            || auteurTextField.getText().isBlank()
                            || genreComboBox.getSelectedItem() == null
                            || quantiteSpinner.getValue() == null
                            || editeurTextField.getText().isBlank()
                            || prixTextField.getText().isBlank()
                            || getDateReceptionChooser().getDate() == null
                            || descriptionTextArea.getText().isBlank()

            ) {
                JOptionPane.showMessageDialog(
                        AjouterLivre.this,
                        "Erreur de saisie!!"
                );
            }
            else {
                try {

                    int isbn = Integer.parseInt(isbnTextField.getText());
                    int quantite  = (int) (quantiteSpinner.getValue());
                    double prix = Double.parseDouble(prixTextField.getText());

                    if (file == null) {
                        file = new File("src\\images\\noimage.jpg");
                    }

                    if (file.length() < 65_535) { // 65_535 = maximum size of  a blob

                        Livre livre = new Livre(
                                isbn,
                                titreTextField.getText(),
                                listAuteursDialog.getAuteur(),
                                (Genre) genreComboBox.getSelectedItem(),
                                quantite,
                                editeurTextField.getText(),
                                prix,
                                new java.sql.Date(getDateReceptionChooser().getDate().getTime()),
                                descriptionTextArea.getText(),
                                file);
                        Optional<Livre> existingLivre = librairie.getAllLivre()
                                .stream()
                                .filter(livre::equals)
                                .findFirst();

                        if (existingLivre.isPresent()) {
                            JOptionPane.showMessageDialog(
                                    AjouterLivre.this,
                                    "Livre déjà existant !"
                            );
                        } else {
                            librairie.ajouterLivre(livre);
                            JOptionPane.showMessageDialog(
                                    AjouterLivre.this,
                                    "Livre ajouté !"
                            );
                        }
                    }
                    else {
                            JOptionPane.showMessageDialog(
                                    AjouterLivre.this,
                                    "La photo est trop longue !"
                            );
                        }
                }  catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            AjouterLivre.this,
                            "erreur de saisie:\n" + e1.getMessage()
                    );
                }

                tableModeleLivre.chargerTable(librairie.getAllLivre());
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
            Logger.getLogger(AjouterLivre.class.getName()).log(Level.SEVERE, null, ex);
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

//        setVisible(true);
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


    public static AjouterLivre getInstance() {
        if (ajouterLivre == null) {
            ajouterLivre = new AjouterLivre();
        }

        return ajouterLivre;
    }










}
