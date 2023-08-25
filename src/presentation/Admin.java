package presentation;

import dao.ILibrairie;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Admin extends JFrame {
    //
    private static JFrame admin;

    // SimpleDateFormat
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final Date date = new Date();

    // components:
    // Jpanels:
    private final JPanel libraryIconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,5));
    private final JPanel menuBar = new JPanel(new BorderLayout());
    private final JPanel navBar = new JPanel(new GridBagLayout());
    private GridBagConstraints gbc = new GridBagConstraints(); // GridBag constraints for navBar
    private final JPanel homePage = new JPanel(new BorderLayout());
    private final JPanel upperSection = new JPanel(new BorderLayout(0,20));
    // trackingSection = [booksPanel : 1 + membersPanel : 2 + authorsPanel : 3]
    private final JPanel trackingSection = new JPanel(new GridLayout(1, 3, 40, 30));
    // JPanel to set the size of the tracking section
    private final JPanel setSizeTrackingPanel = new JPanel(new GridLayout(1,1,0,0));

    // book Panel
    private final JPanel booksPanel = new JPanel(new BorderLayout(40,10));
    private final JPanel booksTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
    // Members Panel
    private final JPanel membersPanel = new JPanel(new BorderLayout());
    private final JPanel membersTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

    // Authors Panel
    private final JPanel authorsPanel = new JPanel(new BorderLayout());
    private final JPanel authorsTitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));

    // libraryBodyGridPanel = [trackingSection : 1 + booksAddedSection : 2]
    private final JPanel libraryBodyGridPanel = new JPanel(new GridLayout(2, 1, 10, 10));
    // Book Added Section
    // Setting the size using another JPanel
    private final JPanel booksAddedSizePanel = new JPanel(new GridLayout(1,1,10,10));
   // BooksAddedSection = [ BooksImagesPanel + booksSection]
    private final JPanel booksAddedSection = new JPanel(new BorderLayout());
    // bookImagesPanel = panel holding the 5 images
    private final JPanel bookImagesPanel = new JPanel(new GridLayout(1,5,5,5));
    // LatestBooksAddedPanel = panel holding the LatestBooksLabel
    private final JPanel latestBooksAddedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
    private final JPanel booksSection = new JPanel(new GridLayout(1,1,10,10));
    private final JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,15,20));




    //JLabels
    private final JLabel welcomeLabel = new JLabel("Welcome Back [insert user here]");
    private final JLabel booksLabel = new JLabel("Livres");
    private final JLabel membersLabel = new JLabel("Membres");
    private final JLabel authorsLabel = new JLabel("Auteurs");

    private JLabel bookNumberLabel = new JLabel("8", JLabel.CENTER);
    private JLabel memberNumberLabel= new JLabel("4", JLabel.CENTER);
    private JLabel authorNumberLabel= new JLabel("7", JLabel.CENTER);

    private final JLabel latestBooksLabel = new JLabel("Livres récemment ajoutés:");

    // Images
    private ImageIcon kafkaShore = new ImageIcon("src\\images\\kafka_on_the_shore.jpg");
    private ImageIcon murakami1Q84 = new ImageIcon("src\\images\\murakami1Q84.jpg");
    private ImageIcon albertCamus = new ImageIcon("src\\images\\albertcamus.jpg");
    private ImageIcon goodbyeEri = new ImageIcon("src\\images\\goodbye_eri.png");
    private ImageIcon chainsawMan = new ImageIcon("src\\images\\chainsawman.jpg");



    //Jlabels for images
    private JLabel kafkaLabel = new JLabel(kafkaShore);
    private JLabel murakamiLabel = new JLabel(murakami1Q84);
    private JLabel albertCamusLabel = new JLabel(albertCamus);
    private JLabel goodbyeEriLabel = new JLabel(goodbyeEri);
    private JLabel chainsawLabel = new JLabel(chainsawMan);



    // JButton for Home Page
    private final JButton libraryButtonIcon = new JButton("Librairie");
    // image Icon:
    private ImageIcon libraryIcon = new ImageIcon("src\\images\\icon\\book.png");

    // JButtons
    private JButton livresButton, ajouterLivreButton, modifierLivreButton,listLivreButton;
    private JButton gererUtilisateursButton;  // button only visible for admin
    private JButton gererGenresButton, gererAuteursButton;
    private JButton membreButton,
            ajouterMembreButton, modifierMembreButton,listMembreButton;
    private JButton circulationButton, issueBookButton,returnBookButton;

    private final JLabel time = new JLabel(dateFormat.format(date));

    private static CardLayout cardlayout = new CardLayout();
    private static  JPanel adminCardPanel = new JPanel(cardlayout);
    private final JPanel rightPanel = new JPanel(new GridLayout(1,1,10,10));
    private static final JPanel ajouterLivre = AjouterLivre.getInstance();
    private static final JPanel modifierLivre = ModifierLivre.getInstance();

    private static final JPanel attribuerLivre = AttribuerLivre.getInstance();
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    private int idUser;

    private Admin() {
        // titre:
        super("Admin");

        // changing the Background of the JFrame
        this.getContentPane().setBackground(new Color(80, 80, 80));


        // toolkit --> getting screen size (xSize, ySize) and tasBarSize:
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        Insets screenMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = screenMax.bottom;

        // library button
        libraryIcon=Utils.resizeImageIcon(libraryIcon, 75, 75);
        libraryButtonIcon.setIcon(libraryIcon);
        libraryButtonIcon.setFont(new Font("Arial",Font.BOLD + Font.ITALIC,40));
        libraryButtonIcon.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.WHITE));
        libraryButtonIcon.setForeground(Color.WHITE);


        // navBar
        // JButtons for Members
        // JButtons for Members
        membreButton = new JButton("Membres");
        gbc.gridx= 0;
        gbc.gridy=0;
        gbc.gridwidth=0;
        gbc.insets= new Insets(-390,-100,0,0);
        navBar.add(membreButton,gbc);

        ajouterMembreButton = new JButton("Ajouter Membre");
        gbc.gridx = 1; // column 1
        gbc.gridy = 1; // row 1
        gbc.insets= new Insets(-330,30,0,0);
        navBar.add(ajouterMembreButton, gbc);

        modifierMembreButton = new JButton("Modifier Membre");
        gbc.gridx = 1; // column 1
        gbc.gridy = 2; // row 2
        gbc.insets= new Insets(-260,30,0,0);
        navBar.add(modifierMembreButton, gbc);

        listMembreButton = new JButton("Liste Membres");
        gbc.gridx = 1; // column 1
        gbc.gridy = 3; // row 3
        gbc.insets= new Insets(-190,20,0,0);
        navBar.add(listMembreButton, gbc);

        livresButton = new JButton("Livres");
        gbc.gridx= 0;
        gbc.gridy=4;
        gbc.insets= new Insets(-120,30,0,150);
        navBar.add(livresButton, gbc);

        ajouterLivreButton = new JButton("Ajouter Livre");
        gbc.gridx= 1;
        gbc.gridy=5;
        gbc.insets= new Insets(-50,0,0,0);
        navBar.add(ajouterLivreButton, gbc);

        modifierLivreButton = new JButton("Modifier Livre");
        gbc.gridx= 1;
        gbc.gridy=6;
        gbc.insets= new Insets(0,0,0,0);
        navBar.add(modifierLivreButton, gbc);

        listLivreButton = new JButton("Liste Livres");
        gbc.gridx=1;
        gbc.gridy=7;
        gbc.insets= new Insets(10,0,0,0);
        navBar.add(listLivreButton, gbc);

        circulationButton = new JButton("Circulation");
        gbc.gridx= 0;
        gbc.gridy=8;
        gbc.insets= new Insets(20,-100,0,0);
        navBar.add(circulationButton, gbc);

        issueBookButton = new JButton("Attribuer Livre");
        gbc.gridx= 0;
        gbc.gridy=9;
        gbc.insets= new Insets(10,-5,0,0);
        navBar.add(issueBookButton, gbc);

        returnBookButton = new JButton("Retourner Livre");
        gbc.gridx= 0;
        gbc.gridy=10;
        gbc.insets= new Insets(10,0,0,-3);
        navBar.add(returnBookButton, gbc);

        gererGenresButton = new JButton("Gérer Genres");
        gbc.gridx=0;
        gbc.gridy=11;
        gbc.insets= new Insets(0,0,-130,0);
        navBar.add(gererGenresButton, gbc);

        gererAuteursButton = new JButton("Gérer Auteurs");
        gbc.gridx=0;
        gbc.gridy=12;
        gbc.insets= new Insets(0,0,-200,0);
        navBar.add(gererAuteursButton, gbc);

        gererUtilisateursButton = new JButton("Gérer Utilisateur");
        gbc.gridx=0;
        gbc.gridy=13;
        gbc.insets= new Insets(0,0,-270,0);
        navBar.add(gererUtilisateursButton, gbc);

        menuBar.add(libraryIconPanel,BorderLayout.NORTH);
        menuBar.add(navBar, BorderLayout.CENTER);

        // setting focusable for the JButtons
        libraryButtonIcon.setFocusable(false);
        //
        membreButton.setFocusable(false);
        ajouterMembreButton.setFocusable(false);
        modifierMembreButton.setFocusable(false);
        listMembreButton.setFocusable(false);
        //
        livresButton.setFocusable(false);
        ajouterLivreButton.setFocusable(false);
        modifierLivreButton.setFocusable(false);
        listLivreButton.setFocusable(false);
        //
        circulationButton.setFocusable(false);
        issueBookButton.setFocusable(false);
        returnBookButton.setFocusable(false);
        //
        gererUtilisateursButton.setFocusable(false);
        gererGenresButton.setFocusable(false);
        gererAuteursButton.setFocusable(false);


        // removing hover from jbuttons
        libraryButtonIcon.setContentAreaFilled(false);


        // Styling the buttons
        libraryButtonIcon.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        libraryButtonIcon.setBackground(new Color(12,101,124));
        libraryIconPanel.add(libraryButtonIcon);


        libraryIconPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        libraryIconPanel.setBackground(new Color(12,101,124));

        Font font=new Font("Arial",Font.BOLD,18);

        membreButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        membreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        membreButton.setContentAreaFilled(false);
        membreButton.setForeground(Color.WHITE);

        ajouterMembreButton.setFont(font);
        ajouterMembreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        ajouterMembreButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        ajouterMembreButton.setContentAreaFilled(false);
        ajouterMembreButton.setForeground(Color.WHITE);

        modifierMembreButton.setFont(font);
        modifierMembreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        modifierMembreButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        modifierMembreButton.setContentAreaFilled(false);
        modifierMembreButton.setForeground(Color.WHITE);

        listMembreButton.setFont(font);
        listMembreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        listMembreButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        listMembreButton.setContentAreaFilled(false);
        listMembreButton.setForeground(Color.WHITE);

        livresButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        livresButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        livresButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        livresButton.setContentAreaFilled(false);
        livresButton.setForeground(Color.WHITE);

        ajouterLivreButton.setFont(font);
        ajouterLivreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        ajouterLivreButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        ajouterLivreButton.setContentAreaFilled(false);
        ajouterLivreButton.setForeground(Color.WHITE);

        modifierLivreButton.setFont(font);
        modifierLivreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        modifierLivreButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        modifierLivreButton.setContentAreaFilled(false);
        modifierLivreButton.setForeground(Color.WHITE);

        listLivreButton.setFont(font);
        listLivreButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        listLivreButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        listLivreButton.setContentAreaFilled(false);
        listLivreButton.setForeground(Color.WHITE);

        gererAuteursButton.setFont(font);
        gererAuteursButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        gererAuteursButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        gererAuteursButton.setContentAreaFilled(false);
        gererAuteursButton.setForeground(Color.WHITE);

        gererGenresButton.setFont(font);
        gererGenresButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        gererGenresButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        gererGenresButton.setContentAreaFilled(false);
        gererGenresButton.setForeground(Color.WHITE);

        gererUtilisateursButton.setFont(font);
        gererUtilisateursButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        gererUtilisateursButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        gererUtilisateursButton.setContentAreaFilled(false);
        gererUtilisateursButton.setForeground(Color.WHITE);

        // circulation buttons
        circulationButton.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 20));
        circulationButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        circulationButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        circulationButton.setContentAreaFilled(false);
        circulationButton.setForeground(Color.WHITE);

        // issue Book Button
        issueBookButton.setFont(font);
        issueBookButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        issueBookButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        issueBookButton.setContentAreaFilled(false);
        issueBookButton.setForeground(Color.WHITE);

        // Return Book Button
        returnBookButton.setFont(font);
        returnBookButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        returnBookButton.setBackground(new Color(0,0,0,Transparency.TRANSLUCENT));
        returnBookButton.setContentAreaFilled(false);
        returnBookButton.setForeground(Color.WHITE);


        // homePage Section
        // adding the Jlabels into the bookPanel, memberPanel and authorPanel

        booksTitlePanel.add(booksLabel);
        booksPanel.add(booksTitlePanel, BorderLayout.NORTH);
        booksPanel.add(bookNumberLabel, BorderLayout.CENTER);
        //
        membersTitlePanel.add(membersLabel);
        membersPanel.add(membersTitlePanel, BorderLayout.NORTH);
        membersPanel.add(memberNumberLabel, BorderLayout.CENTER);
        //
        authorsTitlePanel.add(authorsLabel);
        authorsPanel.add(authorsTitlePanel, BorderLayout.NORTH);
        authorsPanel.add(authorNumberLabel, BorderLayout.CENTER);

        //making the bookNumberLabel, memberNumberLabel and authorNumberLabel dynamic
        bookNumberLabel.setText(String.valueOf(librairie.getAllLivre().size()));
        memberNumberLabel.setText(String.valueOf(librairie.getAllMembre().size()));
        authorNumberLabel.setText(String.valueOf(librairie.getAllAuteurs().size()));

        navBar.setBackground(new Color(21,22,23));
        navBar.setBorder(BorderFactory.createMatteBorder(3,0,0,0,Color.WHITE));

        // tracking Section
        // adding the components into the tracking Section:
        trackingSection.add(booksPanel);
        trackingSection.add(membersPanel);
        trackingSection.add(authorsPanel);
        trackingSection.setBackground(Color.WHITE);
        // Setting size for setSizeTrackingPanel using an empty border
        setSizeTrackingPanel.setBorder(BorderFactory.createEmptyBorder(
                20,
                180,
                60,
                180
        ));
        // adding the tracking section to setSizeTrackingPanel
        setSizeTrackingPanel.add(trackingSection);
        setSizeTrackingPanel.setBackground(Color.WHITE);

        // adding the components into the libraryBodyGridPanel
        libraryBodyGridPanel.add(setSizeTrackingPanel);
        libraryBodyGridPanel.add(booksAddedSizePanel);
        libraryBodyGridPanel.setBackground(Color.WHITE);

        // adding the components into the Upper Section
        upperSection.add(welcomePanel, BorderLayout.NORTH);
        upperSection.setBackground(Color.WHITE);

        // Latest Books Added Section
        latestBooksAddedPanel.add(latestBooksLabel);
        // adding the book images into the book images panel

        /*bookImagesPanel.add(kafkaLabel);
        bookImagesPanel.add(murakamiLabel);
        bookImagesPanel.add(albertCamusLabel);
        bookImagesPanel.add(goodbyeEriLabel);
        bookImagesPanel.add(chainsawLabel);*/

        //
        librairie.getAllLivre()
                .stream()
                .limit(5)
                .forEach(livre -> {
                    byte[] imageData = librairie.getImageData(livre.getID()); // Retrieve image data for the current Livre object

                    if (imageData != null) {
                        ImageIcon imageIcon = new ImageIcon(imageData);
                        imageIcon = Utils.resizeImageIcon(imageIcon, 200, 300);
                        JLabel label = new JLabel(imageIcon);
                        bookImagesPanel.add(label);
                    }
                });


        bookImagesPanel.revalidate();
        bookImagesPanel.repaint();

        // adding the images and the title label to the bookAddedSection
        booksSection.add(bookImagesPanel);
        booksAddedSection.add(latestBooksAddedPanel, BorderLayout.NORTH);
        booksAddedSection.add(booksSection, BorderLayout.CENTER);

        // adding the booksSection to the booksAddedSizePanel
        booksAddedSizePanel.add(booksAddedSection);
        booksAddedSizePanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                30,
                30,
                30
        ));
        booksAddedSizePanel.setBackground(Color.WHITE);



        // setting up fonts and style
        // upper Section
            // fonts
        // welcomeLabel
        welcomeLabel.setFont(new Font("Arial", Font.BOLD+Font.ITALIC,28));
        welcomePanel.add(welcomeLabel);
        welcomePanel.setBackground(Color.WHITE);

        //
        booksLabel.setFont(new Font("Arial", Font.BOLD, 24));
        membersLabel.setFont(new Font("Arial", Font.BOLD, 24));
        authorsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bookNumberLabel.setFont(new Font("Arial", Font.BOLD, 40));
        memberNumberLabel.setFont(new Font("Arial", Font.BOLD, 40));
        authorNumberLabel.setFont(new Font("Arial", Font.BOLD, 40));
            // foreground colors
        booksLabel.setForeground(Color.WHITE);
        membersLabel.setForeground(Color.WHITE);
        authorsLabel.setForeground(Color.WHITE);
        bookNumberLabel.setForeground(Color.WHITE);
        memberNumberLabel.setForeground(Color.WHITE);
        authorNumberLabel.setForeground(Color.WHITE);
            // background colors
        booksTitlePanel.setBackground(new Color(11, 36, 71).brighter());
        booksPanel.setBackground(new Color(11, 36, 71));
        //
        membersTitlePanel.setBackground(new Color(25, 55, 109).brighter());
        membersPanel.setBackground(new Color(25, 55, 109));
        //
        authorsTitlePanel.setBackground(new Color(87, 108, 188).brighter());
        authorsPanel.setBackground(new Color(87, 108, 188));

        // Books Section
        latestBooksLabel.setFont(new Font("Arial",Font.BOLD+ Font.ITALIC,24));
        latestBooksLabel.setForeground(Color.WHITE);
        latestBooksAddedPanel.setBackground(new Color(42, 47, 79));
        latestBooksAddedPanel.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.BLACK));

        booksSection.setBorder(BorderFactory.createMatteBorder(0,1,1,1, Color.BLACK));
        bookImagesPanel.setBackground(new Color(145, 127, 179));
        bookImagesPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));


        // adding the components to the homepage
        homePage.add(upperSection, BorderLayout.NORTH);
        homePage.add(libraryBodyGridPanel, BorderLayout.CENTER);


        // setting up CardPanel Layout


        rightPanel.add(homePage);
        adminCardPanel.setLayout(cardlayout);
        adminCardPanel.add(rightPanel, "Home Panel");

        // Action Listeners:
        libraryButtonIcon.addActionListener(e -> cardlayout.show(adminCardPanel, "Home Panel"));

        GererUtilisateur gererUtilisateur = new GererUtilisateur();
        adminCardPanel.add(gererUtilisateur, "Gérér Utilisateur");
        JButton backButton = gererUtilisateur.getRetour();

        gererUtilisateursButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Gérér Utilisateur"));

        backButton.addActionListener(e -> cardlayout.previous(adminCardPanel));
        AjouterMembre ajouterMembre = new AjouterMembre();
        adminCardPanel.add(ajouterMembre, "Ajouter Membre");
        ajouterMembreButton.addActionListener(e -> cardlayout.show(adminCardPanel,"Ajouter Membre"));

        ModifierMembre modifierMembre = new ModifierMembre();
        adminCardPanel.add(modifierMembre, "Modifier Membre");
        modifierMembreButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Modifier Membre"));

        ListeMembre listeMembre = new ListeMembre();
        adminCardPanel.add(listeMembre, "Liste Membre");
        listMembreButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Liste Membre"));


        GererAuteurs gererAuteurs = new GererAuteurs();
        adminCardPanel.add(gererAuteurs, "Gérer Auteurs");
        gererAuteursButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Gérer Auteurs"));

        GererGenres gererGenres = new GererGenres();
        adminCardPanel.add(gererGenres, "Gérer Genres");
        gererGenresButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Gérer Genres"));

        adminCardPanel.add(ajouterLivre, "Ajouter Livre");
        ajouterLivreButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Ajouter Livre"));

        adminCardPanel.add(modifierLivre, "Modifier Livre");
        modifierLivreButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Modifier Livre"));

        ListeLivre listLivre = new ListeLivre();
        adminCardPanel.add(listLivre, "Liste Livre");
        listLivreButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Liste Livre"));

        adminCardPanel.add(attribuerLivre, "Attribuer Livre");
        issueBookButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Attribuer Livre"));

        RetournerLivre retournerLivre = new RetournerLivre();
        adminCardPanel.add(retournerLivre, "Retourner Livre");
        returnBookButton.addActionListener(e -> cardlayout.show(adminCardPanel, "Retourner Livre"));


        this.add(menuBar, BorderLayout.WEST);
        this.add(adminCardPanel, BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);



    }

    public static synchronized JFrame getInstance() {
        if (admin == null) {
            admin = new Admin();
        }

        return admin;
    }

    public void setUserId(int id){
        welcomeLabel.setText("Welcome Back " + librairie.getUtilisateur(id).toString());
    }


    public static void main(String[] args) {
        Admin.getInstance();
    }
}