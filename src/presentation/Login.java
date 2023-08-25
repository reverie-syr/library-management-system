package presentation;

import dao.LoginDAO;
import metier.entity.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Login extends JFrame {
    // components:
    // JPanels
    private final JPanel loginPanel = new JPanel(); // loginPanel = [iconPanel + forumPanel]
    private final JPanel iconPanel = new JPanel();
    // forumPanel = [ titlePanel + inputPanel + buttonsPanel]
    private final JPanel forumPanel = new JPanel(new BorderLayout(5,5));
    private final JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5,5));
    private final JPanel buttonsPanel = new JPanel(new GridLayout(1,2,40,40));
    // inputPanel = [usernameInput + passwordInput + showPasswordCheckbox]
    private final JPanel inputPanel = new JPanel(new GridLayout(7,1,0,0));
    private final JPanel loginButtonPanel = new JPanel();
    private final JPanel cancelButtonPanel = new JPanel();

    // JLabels:
    private final JLabel loginLabel = new JLabel("Login", JLabel.CENTER);
    private final JLabel usernameLabel = new JLabel("Nom d'Utilisateur:", JLabel.LEFT);
    private final JLabel roleLabel = new JLabel("Rôle:", JLabel.LEFT);
    private final JLabel passwordLabel = new JLabel("Mot De Passe:", JLabel.LEFT);
    // JTextFields:
    private final JTextField usernameTextField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    // JComboBoxs:
    JComboBox<String> comboBox = new JComboBox<>(new String[] {
            "Admin",
            "Libraire"
    });
    // JButtons:
    private final JButton loginButton = new JButton("Login");
    private final JButton cancelButton = new JButton("Annuler");
    private final JCheckBox showPassword = new JCheckBox("Afficher le mot de passe");

    LoginDAO loginSystem = new LoginDAO();
    // image icons
    private ImageIcon authenticationIcon = new ImageIcon("src\\images\\icon\\authentication.png");
    private final JLabel authenticationLabel = new JLabel(authenticationIcon);

    private Utilisateur utilisateur;
    private static int id;

    public Login() {
        // titre:
        super("Login");

        // toolkit --> getting screen size (xSize, ySize) and tasBarSize:
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = (int) tk.getScreenSize().getWidth();
        int ySize = (int) tk.getScreenSize().getHeight();
        Insets screenMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = screenMax.bottom;

        setIconImage(tk.getImage("src\\images\\icon\\profile.png"));


        // setting  the fonts
        loginLabel.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 80));
        usernameLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        usernameTextField.setFont(new Font("Arial", Font.BOLD, 30));
        //
        passwordLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        passwordField.setFont(new Font("Arial", Font.BOLD, 30));
        //
        roleLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        comboBox.setFont(new Font("Arial", Font.BOLD, 30));

        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        cancelButton.setFont(new Font("Arial", Font.BOLD, 24));
        comboBox.setFont(new Font("Arial", Font.BOLD, 30));
        showPassword.setFont(new Font("Arial", Font.BOLD, 28));


        // making loginPanel have a BoxLayout.
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.X_AXIS));

        // adding components to the inputPanel
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameTextField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(showPassword);
        inputPanel.add(roleLabel);
        inputPanel.add(comboBox);

        ButtonGroup bg = new ButtonGroup();
        bg.add(loginButton);
        bg.add(cancelButton);

        // adding buttons to buttonsPanel
        loginButtonPanel.add(loginButton);
        cancelButtonPanel.add(cancelButton);
        buttonsPanel.add(loginButtonPanel);
        buttonsPanel.add(cancelButtonPanel);

        // adding the login label to the title panel
        titlePanel.add(loginLabel);

        // adding components to forumPanel
        forumPanel.add(titlePanel, BorderLayout.NORTH);
        forumPanel.add(inputPanel, BorderLayout.CENTER);
        forumPanel.add(buttonsPanel, BorderLayout.SOUTH);

        // setting up empty borders
        loginPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(
                40,
                20,
                60,
                20
        ));
        iconPanel.setBorder(BorderFactory.createEmptyBorder(
                40,
                30,
                50,
                30
        ));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                300, 80,
                300
        ));

        // setting up buttons
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0, Transparency.TRANSLUCENT)));
        loginButton.setContentAreaFilled(false);
        loginButton.setFocusPainted(false);
        loginButton.setFocusable(false);
        loginButtonPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(107,127,190)));

        cancelButton.setBorder(BorderFactory.createLineBorder(new Color(1,1, 0, Transparency.TRANSLUCENT)));
        cancelButton.setContentAreaFilled(false);
        cancelButton.setFocusPainted(false);
        cancelButton.setFocusable(false);
        cancelButtonPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, new Color(107,127,190)));


        // adding the image panel to the iconPanel
        authenticationIcon=Utils.resizeImageIcon(authenticationIcon, 10, 5);
        iconPanel.add(authenticationLabel);

        // setting up colors:
        loginPanel.setBackground(new Color(133,95,168));
        iconPanel.setBackground(new Color(107,127,190).darker());
        forumPanel.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        titlePanel.setBackground(Color.WHITE);
        buttonsPanel.setBackground(Color.WHITE);
        showPassword.setBackground(Color.WHITE);
        comboBox.setBackground(Color.WHITE);
        loginButtonPanel.setBackground(Color.WHITE);
        cancelButtonPanel.setBackground(Color.WHITE);

        loginPanel.add(iconPanel);
        loginPanel.add(forumPanel);


        // adding the ActionListener to the JButton
        loginButton.addActionListener(e -> {
            if (usernameLabel.getText().isBlank() || String.valueOf(passwordField.getPassword()).isBlank()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erreur de saisie, faut remplir les champs!"
                );
            } else {
                utilisateur = new Utilisateur(
                        usernameTextField.getText(),
                        String.valueOf(passwordField.getPassword()),
                        (Objects.equals(comboBox.getSelectedItem(), "Admin"))? 1 : 0
                );

                utilisateur = loginSystem.validate(utilisateur);

                if (utilisateur != null) {
                    JOptionPane.showMessageDialog(this, "Identification réussie.", "Login",
                            JOptionPane.INFORMATION_MESSAGE);

                    EventQueue.invokeLater(() -> {
                        Login.this.dispose();
                        id = utilisateur.getId();
                        if (utilisateur.getAdmin() == 1) {
                            Admin admin = (Admin) Admin.getInstance();
                            admin.setUserId(id);
                        } else {
                            Libraire libraire = (Libraire) Libraire.getInstance();
                            libraire.setUserId(id);
                        }
                    });
                }
                else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Utilisateur invalide."
                        );
                    }
                }
        });
        cancelButton.addActionListener(e -> {
            usernameTextField.setText("");
            passwordField.setText("");
            showPassword.setSelected(false);
            comboBox.setSelectedIndex(0);
        });
        showPassword.addActionListener(e -> {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        });


        // adding components to the JFrame:
        this.add(loginPanel);

        // setup JFrame:
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(xSize, ySize - taskBarSize);
        this.setVisible(true);
    }
    public static int getId(){
        return id;
    }
    public void setId(int idUser){
        id= idUser;
    }


}
