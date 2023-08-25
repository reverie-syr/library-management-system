package presentation;

import com.toedter.calendar.JDateChooser;
import dao.ILibrairie;
import dao.Librairie;
import lombok.Getter;
import metier.entity.Membre;
import presentation.tableModeles.TableModeleMembre;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class AjouterMembre extends JPanel {


    //JPanels
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    // inputPanel = forumPanel + photoSectionPanel
    private final JPanel inputPanel = new JPanel(new GridLayout(1,2,20,20));
    private final JPanel inputPanelBorder = new JPanel(new BorderLayout(10,10));
    private final JPanel forumPanel = new JPanel(new GridLayout(10,2,10,10));

    // PhotoSectionPanel = photo + browseButton + ajouterButton + annulerButton
    private final JPanel photoSectionPanel = new JPanel(new BorderLayout(30,50));
    private final JPanel photoPanel = new JPanel(new BorderLayout());
    private final JPanel photoPanelStyle = new JPanel(new BorderLayout());


    private final JPanel buttonsPanel = new JPanel(new GridLayout(1,2,30,20));
    private final JPanel ajouterPanel = new JPanel();
    private final JPanel annulerPanel = new JPanel();
    private final JPanel photoSelectionPanel = new JPanel(new BorderLayout(30,10));
    private final JPanel photoButtonPanel = new JPanel(new GridLayout(1,2,30,10));
    private final JPanel ajouterPhotoPanel = new JPanel();
    private final JPanel annulerPhotoPanel = new JPanel();



    // JLabels
    private final JLabel titleLabel = new JLabel("Nouveau Membre");
    private final JLabel idLabel = new JLabel("ID: ");
    private final JLabel nomLabel = new JLabel("Nom: ");
    private final JLabel prenomLabel = new JLabel("Prénom: ");
    private final JLabel cinLabel = new JLabel("CIN: ");
    private final JLabel numTelLabel = new JLabel("Téléphone: ");
    private final JLabel dateDeNaissanceLabel = new JLabel("Date de naissance: ");
    private final JLabel emailLabel = new JLabel("Adresse Email: ");
    private final JLabel genreLabel = new JLabel("Genre: ");
    private final JLabel dateAdhesionLabel = new JLabel("Date d'adhésion: ");
    private final JLabel adresseLabel = new JLabel("Adresse: ");
    private final JLabel imageLabel = new JLabel("choisir une photo... ");

    // JTextFields
    private final JTextField idTextField = new JTextField();
    private final JTextField nomTextField = new JTextField();
    private final JTextField prenomTextField = new JTextField();
    private final JTextField cinTextField = new JTextField();
    private final JTextField numTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JTextField adresseTextField = new JTextField();

    //JButtons
    private final JButton ajouterButton = new JButton("Ajouter Membre");
    private final JButton annulerButton = new JButton("Annuler");
    private final JButton ajouterPhoto = new JButton("Sélectionner");
    private final JButton annulerPhoto = new JButton("Annuler");

    // ImageIcons
    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\add-contact.png");
    // Date Chooser
    JDateChooser bDayChooser =new JDateChooser();
    JDateChooser dateAdhesionChooser = new JDateChooser();

    // JComboBox
    private final JComboBox<String> comboBox = new JComboBox<>(new String[] {
            "Feminin",
            "Masculin"
    });
    File targetFile;
    BufferedImage targetImg;
    ImageIcon icon;
    JLabel imglabel;
    File file;
    // creation du Librairie Object
    ILibrairie librairie = Librairie.getLibrairie();
    // creation du TableModeleMembre Object
    TableModeleMembre me = TableModeleMembre.getInstance();

    private static final int baseSize = 400;
    private static final String basePath =
            "C:\\Images";


    public AjouterMembre(){

        //super(new BorderLayout(10,30));
        this.setLayout(new BorderLayout(10,30));
        this.setBackground(Color.WHITE);

        // resizing the icons:
        titleIcon = Utils.resizeImageIcon(titleIcon, 80, 80);


        // fonts
        // labels
        titleLabel.setIcon(titleIcon);
        titleLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 40));
        idLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nomLabel.setFont(new Font("Arial", Font.BOLD, 20));
        prenomLabel.setFont(new Font("Arial", Font.BOLD, 20));
        cinLabel.setFont(new Font("Arial", Font.BOLD, 20));
        adresseLabel.setFont(new Font("Arial", Font.BOLD, 20));
        numTelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateDeNaissanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dateAdhesionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        genreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        imageLabel.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 18));
        imageLabel.setForeground(Color.blue);

        // inputs
        idTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        nomTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        prenomTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        cinTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        adresseTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        numTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailTextField.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));

        forumPanel.add(idLabel);
        forumPanel.add(idTextField);
        forumPanel.add(nomLabel);
        forumPanel.add(nomTextField);
        forumPanel.add(prenomLabel);
        forumPanel.add(prenomTextField);
        forumPanel.add(cinLabel);
        forumPanel.add(cinTextField);
        forumPanel.add(numTelLabel);
        forumPanel.add(numTextField);

        bDayChooser.setDateFormatString("dd-MM-yyyy");//format visible date of the date chooser as you need
        String bDay=((JTextField) bDayChooser.getDateEditor().getUiComponent()).
                getText();
        forumPanel.add(dateDeNaissanceLabel);
        forumPanel.add(bDayChooser);

        forumPanel.add(emailLabel);
        forumPanel.add(emailTextField);
        forumPanel.add(genreLabel);
        forumPanel.add(comboBox);



        dateAdhesionChooser.setDateFormatString("dd-MM-yyyy");//format visible date of the date chooser as you need
        String adhesionDay =((JTextField) dateAdhesionChooser.getDateEditor().getUiComponent()).
                getText();
        forumPanel.add(dateAdhesionLabel);
        forumPanel.add(dateAdhesionChooser);
        forumPanel.add(adresseLabel);
        forumPanel.add(adresseTextField);


        // buttons
        // setting up buttons
        ajouterButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        ajouterButton.setFont(new Font("Arial", Font.BOLD, 18));
        ajouterButton.setContentAreaFilled(false);
        ajouterButton.setFocusPainted(false);
        ajouterButton.setFocusable(false);
        ajouterPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        annulerButton.setBorder(BorderFactory.createLineBorder(new Color(1,1, 0, Transparency.TRANSLUCENT)));
        annulerButton.setFont(new Font("Arial", Font.BOLD, 18));
        annulerButton.setContentAreaFilled(false);
        annulerButton.setFocusPainted(false);
        annulerButton.setFocusable(false);
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

        ButtonGroup bg = new ButtonGroup();
        bg.add(ajouterButton);
        bg.add(annulerButton);
        ajouterPanel.add(ajouterButton);
        annulerPanel.add(annulerButton);
        buttonsPanel.add(ajouterPanel);
        buttonsPanel.add(annulerPanel);

        ButtonGroup bgPhoto = new ButtonGroup();
        bgPhoto.add(ajouterPhoto);
        bgPhoto.add(annulerPhoto);
        ajouterPhotoPanel.add(ajouterPhoto);
        annulerPhotoPanel.add(annulerPhoto);
        photoButtonPanel.add(ajouterPhotoPanel);
        photoButtonPanel.add(annulerPhotoPanel);
        photoSelectionPanel.add(imageLabel, BorderLayout.NORTH);
        photoSelectionPanel.add(photoButtonPanel, BorderLayout.CENTER);


        photoSectionPanel.add(photoSelectionPanel, BorderLayout.NORTH);
        photoSectionPanel.add(photoPanelStyle, BorderLayout.CENTER);
        photoSectionPanel.add(buttonsPanel, BorderLayout.SOUTH);

        inputPanel.add(forumPanel);
        inputPanel.add(photoSectionPanel);

        photoSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                50,
                10
        ));
        inputPanelBorder.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        inputPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
        forumPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                100,
                0,
                100
        ));
        photoPanelStyle.setBorder(BorderFactory.createEmptyBorder(
                0,
                50,
                0,
                50
        ));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        photoButtonPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                100,
                0,
                100
        ));

        ajouterPanel.setBackground(new Color(13,113,117));
        annulerPanel.setBackground(new Color(205,66,31));
        ajouterButton.setForeground(Color.WHITE);
        annulerButton.setForeground(Color.WHITE);

        ajouterPhotoPanel.setBackground(Color.BLUE);
        annulerPhotoPanel.setBackground(Color.RED);
        ajouterPhoto.setForeground(Color.WHITE);
        annulerPhoto.setForeground(Color.WHITE);

        inputPanel.setBackground(Color.WHITE);
        inputPanelBorder.setBackground(Color.WHITE);
        titlePanel.setBackground(Color.WHITE);
        forumPanel.setBackground(Color.WHITE);
        photoPanel.setBackground(Color.WHITE);
        photoPanelStyle.setBackground(Color.WHITE);
        photoSectionPanel.setBackground(Color.WHITE);
        titleLabel.setBackground(Color.white);
        buttonsPanel.setBackground(Color.WHITE);
        comboBox.setBackground(Color.WHITE);
        photoSelectionPanel.setBackground(Color.WHITE);
        photoButtonPanel.setBackground(Color.WHITE);
        photoPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1, true));
        photoPanelStyle.add(photoPanel);
        inputPanelBorder.add(inputPanel);

        // setting up headlinePanel
        titlePanel.add(titleLabel);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(inputPanelBorder, BorderLayout.CENTER);

        ajouterPhoto.addActionListener(this::browseButtonActionPerformed);

        annulerPhoto.addActionListener(e-> imglabel.setIcon(null));

        ajouterButton.addActionListener(e->{
            if (
                    nomTextField.getText().isBlank()
                            || prenomTextField.getText().isBlank()
                            || cinTextField.getText().isBlank()
                            || numTextField.getText().isBlank()
                            || getBDayChooser().getDate() == null
                            || adresseTextField.getText().isBlank()
                            || comboBox.getSelectedItem() == null
                            || getDateAdhesionChooser().getDate() == null
                            || adresseTextField.getText().isBlank()
            ) {
                JOptionPane.showMessageDialog(
                        AjouterMembre.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int cin = Integer.parseInt(cinTextField.getText());
                    int numTel  = Integer.parseInt(numTextField.getText());

                    if (file == null) {
                        file = new File(String.format("src\\images\\%s.jpg",
                                (Objects.equals(comboBox.getSelectedItem(), "Feminin") ? "hellokitty" : "unnamed")));
                    }

                    if (file.length() < 65_535) { // 65_535 = maximum size of  a blob
                        String expression = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[A-Za-z]{2,3}$";
                        String email = emailTextField.getText();

                        if (email.matches(expression)) {
                            Membre membre = new Membre(
                                    nomTextField.getText(),
                                    prenomTextField.getText(),
                                    cin,
                                    numTel,
                                    new java.sql.Date(getBDayChooser().getDate().getTime()),
                                    email,
                                    comboBox.getSelectedItem().toString(),
                                    new java.sql.Date(getDateAdhesionChooser().getDate().getTime()),
                                    adresseTextField.getText(),
                                    file
                            );

                            Optional<Membre> existingMembre = librairie.getAllMembre()
                                    .stream()
                                    .filter(membre::equals)
                                    .findFirst();

                            if (existingMembre.isPresent()) {
                                JOptionPane.showMessageDialog(
                                        AjouterMembre.this,
                                        "Membre déjà existant !"
                                );
                            } else {
                                librairie.ajouterMembre(membre);
                                JOptionPane.showMessageDialog(
                                        AjouterMembre.this,
                                        "Membre ajouté !"
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    AjouterMembre.this,
                                    "Email invalide !!"
                            );
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                AjouterMembre.this,
                                "La photo est trop longue !"
                        );
                    }
                }  catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            AjouterMembre.this,
                            "erreur de saisie:\n" + e1.getMessage()
                    );
                }

                me.chargerTable(librairie.getAllMembre());
            }

        });
        annulerButton.addActionListener(e -> {
            idTextField.setText("");
            nomTextField.setText("");
            prenomTextField.setText("");
            cinTextField.setText("");
            numTextField.setText("");
            bDayChooser.setDate(null);
            emailTextField.setText("");
            comboBox.setSelectedItem(null);
            dateAdhesionChooser.setDate(null);
            adresseTextField.setText("");
            imglabel.setIcon(null);
        });


        // setup JFrame:
        this.setVisible(true);
    }
    public static BufferedImage rescale(BufferedImage originalImage)
    {
        BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
        g.dispose();
        return resizedImage;
    }
    public void setTarget(File reference)
    {
        try {
            targetFile = reference;
            targetImg = rescale(ImageIO.read(reference));
        } catch (IOException ex) {
            Logger.getLogger(AjouterMembre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (icon == null && imglabel == null) {
            icon=new ImageIcon(targetImg);
            imglabel=new JLabel();

            imglabel.setHorizontalAlignment(JLabel.CENTER);
            imglabel.setVerticalAlignment(JLabel.CENTER);

            photoPanel.add(imglabel);
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




}
