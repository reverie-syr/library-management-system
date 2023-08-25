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
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class ModifierMembre extends JPanel {
    //JPanels
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // hold the titleLabel
    // headlinePanel = titlePanel(BorderLayout.WEST) + idResearchPanel(BorderLayout.CENTER)
    private final JPanel headlinePanel = new JPanel(new BorderLayout(30,10));
    // inputPanel = forumPanel + photoSectionPanel
    private final JPanel inputPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel inputPanelBorder = new JPanel(new BorderLayout(10,10));
    private final JPanel forumPanel = new JPanel(new GridLayout(9,2,10,10));

    // PhotoSectionPanel = photo + browseButton + modifierButton
    private final JPanel photoSectionPanel = new JPanel(new BorderLayout(30,50));
    private final JPanel photoPanel = new JPanel(new BorderLayout());
    private final JPanel photoPanelStyle = new JPanel(new BorderLayout());


    private final JPanel buttonsPanel = new JPanel(new GridLayout(1,2,30,20));
    private final JPanel modifierPanel = new JPanel();
    private final JPanel photoSelectionPanel = new JPanel(new BorderLayout(30,10));
    private final JPanel photoButtonPanel = new JPanel(new GridLayout(1,2,30,10));
    private final JPanel ajouterPhotoPanel = new JPanel();
    private final JPanel annulerPhotoPanel = new JPanel();

    // idResearchPanel = [ idPanel + resarchButtonPanel(boutton)]
    private final JPanel idResearchPanel = new JPanel(new BorderLayout(0,10));
    // idPanel = [idlabel + idTextField]
    private final JPanel idPanel = new JPanel(new BorderLayout(10,10));
    private final JPanel rechercherPanel = new JPanel(new BorderLayout());
    private final JPanel researchButtonPanel = new JPanel(new GridLayout(1,1));


    // JLabels
    private final JLabel titleLabel = new JLabel("Modifier Membre");
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
    private final JButton modifierButton = new JButton("Modifier Membre");
    private final JButton ajouterPhoto = new JButton("Sélectionner");
    private final JButton annulerPhoto = new JButton("Annuler");
    private final JButton rechercher = new JButton("Rechercher");
    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\contacs.png");

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
    File file, photo;
    // creation du Librairie Object
    ILibrairie librairie = Librairie.getLibrairie();
    // creation du TableModeleMembre Object
    TableModeleMembre me = TableModeleMembre.getInstance();

    private static final int baseSize = 400;
    private static final String basePath =
            "C:\\Images";


    // Image icons


    public ModifierMembre(){

        //super(new BorderLayout(10,30));
        this.setLayout(new BorderLayout(10,30));
        this.setBackground(Color.WHITE);

        // resizing the icons:
        titleIcon = Utils.resizeImageIcon(titleIcon, 70, 70);


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
        String adhesionDay =((JTextField) bDayChooser.getDateEditor().getUiComponent()).
                getText();
        forumPanel.add(dateAdhesionLabel);
        forumPanel.add(dateAdhesionChooser);
        forumPanel.add(adresseLabel);
        forumPanel.add(adresseTextField);


        // buttons
        // setting up buttons
        modifierButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        modifierButton.setFont(new Font("Arial", Font.BOLD, 18));
        modifierButton.setContentAreaFilled(false);
        modifierButton.setFocusPainted(false);
        modifierButton.setFocusable(false);
        modifierPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

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

        rechercher.setBorder(BorderFactory.createLineBorder(new Color(1,1, 0, Transparency.TRANSLUCENT)));
        rechercher.setFont(new Font("Arial", Font.BOLD, 18));
        rechercher.setContentAreaFilled(false);
        rechercher.setFocusPainted(false);
        rechercher.setFocusable(false);
        rechercherPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));


        modifierPanel.add(modifierButton);
        buttonsPanel.add(modifierPanel);

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

        inputPanel.add(forumPanel, BorderLayout.CENTER);
        inputPanel.add(photoSectionPanel, BorderLayout.EAST);

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
                20,
                120,
                0,
                120
        ));
        photoPanelStyle.setBorder(BorderFactory.createEmptyBorder(
                0,
                50,
                0,
                50
        ));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(
                22,
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
        idPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                40,
                10,
                40
        ));
        researchButtonPanel.setBorder(BorderFactory.createEmptyBorder(
                5,
                10,
                5,
                140
        ));
        idResearchPanel.setBorder(BorderFactory.createEmptyBorder(
                25,
                50,
                20,
                100
        ));


        modifierPanel.setBackground(new Color(13,113,117));
        modifierButton.setForeground(Color.WHITE);

        ajouterPhotoPanel.setBackground(Color.BLUE);
        annulerPhotoPanel.setBackground(Color.RED);
        ajouterPhoto.setForeground(Color.WHITE);
        annulerPhoto.setForeground(Color.WHITE);

        rechercherPanel.setBackground(new Color(0, 153, 255));
        rechercher.setAlignmentX(CENTER_ALIGNMENT);
        rechercher.setAlignmentY(CENTER_ALIGNMENT);
        rechercher.setForeground(Color.WHITE);

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

        //
        idResearchPanel.setBackground(Color.WHITE);
        headlinePanel.setBackground(Color.WHITE);
        idPanel.setBackground(Color.WHITE);
        researchButtonPanel.setBackground(Color.white);

        // setting up headlinePanel

        idPanel.add(idLabel, BorderLayout.WEST);
        idPanel.add(idTextField, BorderLayout.CENTER);

        rechercherPanel.add(rechercher);
        researchButtonPanel.add(rechercherPanel);
        idResearchPanel.add(idPanel, BorderLayout.CENTER);
        idResearchPanel.add(researchButtonPanel, BorderLayout.EAST);

        titlePanel.add(titleLabel);
        headlinePanel.add(titlePanel, BorderLayout.WEST);
        headlinePanel.add(idResearchPanel, BorderLayout.CENTER);
        this.add(headlinePanel, BorderLayout.NORTH);
        this.add(inputPanelBorder, BorderLayout.CENTER);


        // setup JFrame:
        this.setVisible(true);


        ajouterPhoto.addActionListener(e -> photo = browseButtonActionPerformed(e));
        annulerPhoto.addActionListener(e-> imglabel.setIcon(null));

        modifierButton.addActionListener(e -> {
            if (
                    idTextField.getText().isBlank()
                    || nomTextField.getText().isBlank()
                            || prenomTextField.getText().isBlank()
                            || cinTextField.getText().isBlank()
                            || numTextField.getText().isBlank()
                            || getBDayChooser().getDate() == null
                            || emailTextField.getText().isBlank()
                            || comboBox.getSelectedItem() == null
                            || getDateAdhesionChooser().getDate() == null
                            || adresseTextField.getText().isBlank()
                            || imglabel.getIcon() == null
            ) {
                JOptionPane.showMessageDialog(
                        ModifierMembre.this,
                        "erreur de saisie"
                );
            } else {
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    int cin = Integer.parseInt(cinTextField.getText());
                    int numTel  = Integer.parseInt(numTextField.getText());
                    Membre membre = librairie.getMembre(id);

                    if (membre == null) {
                        JOptionPane.showMessageDialog(
                                ModifierMembre.this,
                                "impossible de faire le mis à jour ce membre!"
                        );
                    } else {
                        String expression = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9+_.-]+\\.[A-Za-z]{2,3}$";
                        String email = emailTextField.getText();

                        if (email.matches(expression)) {
                            membre.setNom(nomTextField.getText());
                            membre.setPrenom(prenomTextField.getText());
                            membre.setCin(cin);
                            membre.setNumTel(numTel);
                            membre.setDateDeNaissance(new java.sql.Date(getBDayChooser().getDate().getTime()));
                            membre.setEmail(email);
                            membre.setGenre(comboBox.getSelectedItem().toString());
                            membre.setDateAdhesion(new java.sql.Date(getDateAdhesionChooser().getDate().getTime()));

                            System.out.println(membre);

                            if (photo== null) {
                                photo = new File(String.format("src\\images\\%s.jpg",
                                        (Objects.equals(comboBox.getSelectedItem(), "Feminin") ? "hellokitty" : "unnamed")));
                            }
                            membre.setPhoto(photo);

                            if (photo.length() < 65_535) { // 65_535 = maximum size of  a blob
                                JOptionPane.showMessageDialog(
                                        ModifierMembre.this,
                                        "membre à été modifié!"
                                );

                                librairie.modifierMembre(membre);
                            } else {
                                JOptionPane.showMessageDialog(
                                        ModifierMembre.this,
                                        "La photo est trop longue!"
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    ModifierMembre.this,
                                    "Email invalide!!"
                            );
                        }
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(
                            ModifierMembre.this,
                            "erreur de saisie de l'id, cin ou num de téléphone."
                    );
                }

                me.chargerTable(librairie.getAllMembre());
            }
        });

        rechercher.addActionListener(e -> {

            if (!idTextField.getText().isBlank()){
                try {
                    int id = Integer.parseInt(idTextField.getText());
                    Membre membre = librairie.getMembre(id);

                    if (membre != null){
                        System.out.println(membre);
                        nomTextField.setText(membre.getNom());
                        prenomTextField.setText(membre.getPrenom());
                        cinTextField.setText(String.valueOf(membre.getCin()));
                        numTextField.setText(String.valueOf(membre.getNumTel()));
                        bDayChooser.setDate(membre.getDateDeNaissance());
                        emailTextField.setText(membre.getEmail());
                        comboBox.setSelectedItem(membre.getGenre());
                        dateAdhesionChooser.setDate(membre.getDateAdhesion());
                        adresseTextField.setText(membre.getAdresse());
                        setTarget(membre.getPhoto());
                    }
                }
                catch (NumberFormatException e2){
                    e2.printStackTrace();
                }
            }
            else {
                JOptionPane.showMessageDialog(
                        ModifierMembre.this,
                        "Membre non trouvé !");
            }

        });

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
            Logger.getLogger(ModifierMembre.class.getName()).log(Level.SEVERE, null, ex);
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
    private File browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new ImageFileFilter());
        int res = fc.showOpenDialog(this);
        // We have an image!
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                setTarget(file);

                return file;
            } // Oops!
            else {
                JOptionPane.showMessageDialog(null,
                        "Vous devez choisir une image comme référence.", "Abandon...",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException) {
            iOException.printStackTrace();
        }
        return null;

    }



}
