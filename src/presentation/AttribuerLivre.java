package presentation;

import com.toedter.calendar.JDateChooser;
import dao.ILibrairie;
import lombok.Getter;
import metier.entity.Livre;
import metier.entity.Membre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
public class AttribuerLivre extends JPanel{

    private static AttribuerLivre attribuerLivre;

    /**
     * inputPanelA = all 6 Labels
     * inputPanelB = all 6 fields
     * bookIDInputPanel = new BoxLayout ( bookIDTextField + searchBookButton );
     * bookIDNamePanel = bookIDInputPanel + bookNameLabel
     *
     * memberIDInputPanel = new BoxLayout ( memberIDTextField + searchMemberButton);
     * memberIDNamePanel = memberIDInputPanel + memberNameLabel
     */
    // ILibrairie
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    private final JPanel mainSectionPanel = new JPanel(new BorderLayout(10,10));

    private final JPanel inputPanel = new JPanel(new BorderLayout(10,10));

    // inputPanel = inputPanelA ( west) + inputPanelB (center)
    private final JPanel inputPanelStyle = new JPanel(new BorderLayout());

    private final JPanel inputPanelA = new JPanel(new GridLayout(7,1,10,10));
    private final JPanel inputPanelB = new JPanel(new GridLayout(7,1,10,10));
    private final JPanel titlePanel = new JPanel();

    private final JPanel bookIDInputPanel = new JPanel();
    private final JPanel emptyBookPanel = new JPanel();

    private final JPanel memberIDInputPanel = new JPanel(new GridLayout(1,2,10,10));
    private final JPanel emptyMemberPanel = new JPanel();

    private final JLabel enterBookIDLabel = new JLabel("Entrer l'ID du livre");
    private final JLabel enterMemberIDLabel = new JLabel("Entrer l'ID du membre");

    private JLabel bookAvailableBooleanLabel = new JLabel("Oui-ou-Non");
    private final JLabel bookAvailableLabel = new JLabel("Ce livre est-il disponible ?");

    private final JPanel issueDateFlowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final JLabel issueDateLabel = new JLabel("Date d'Attribution: ");
    private final JPanel returnDateFlowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final JLabel returnDateLabel = new JLabel("Date de Retour: ");
    private final JPanel noteFlowPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final JLabel noteLabel = new JLabel("Note: ");

    // BUTTONS
    private final JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); //
    private final JPanel bookNameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JLabel bookNameLabel = new JLabel("Titre du Livre");
    private final JPanel memberNameLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private final JLabel memberNameLabel = new JLabel(("Nom et Prénom du Member"));
    private final JButton searchBookButton = new JButton("Rechercher un Livre");
    private final JButton searchMemberButton = new JButton("Rechercher un Member");
    private final JButton attribuerButton = new JButton("Attribuer");
    private final JButton annulerButton = new JButton("Annuler");

    //
    private final SpinnerModel livreIDSpinnerModel = new SpinnerNumberModel(1, 0, 100, 1);
    private final SpinnerModel membreIDSpinnerModel = new SpinnerNumberModel(1,0,100,1);
    private JSpinner livreIdSpinner = new JSpinner(livreIDSpinnerModel);
    private JSpinner membreIdSpinner = new JSpinner(membreIDSpinnerModel);

    //
    private JDateChooser dateAttributionChooser =new JDateChooser();
    private JPanel dateAttributionPanel = new JPanel(); // box layout
    private JDateChooser dateRetourChooser = new JDateChooser();
    private JPanel dateRetourPanel = new JPanel();
    private final JPanel notePanel = new JPanel();
    private JTextArea noteTextArea = new JTextArea(6, 1);
    private JScrollPane scrollPane = new JScrollPane(noteTextArea);

    private final JLabel titleLabel = new JLabel("Attribuer Livre");

    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\attribuerLivreIcon.png");

    private BoxLayout dateBoxLayoutPanel = new BoxLayout(dateAttributionPanel, BoxLayout.X_AXIS);
    private BoxLayout dateRetourBoxLayoutPanel = new BoxLayout(dateRetourPanel, BoxLayout.X_AXIS);
    private BoxLayout buttonBoxLayoutPanel = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
    private BoxLayout noteBoxLayoutPanel = new BoxLayout(notePanel, BoxLayout.X_AXIS);
    private BoxLayout bookIDInputBoxLayoutPanel = new BoxLayout(bookIDInputPanel, BoxLayout.X_AXIS);
    private BoxLayout memberIDInputBoxLayoutPanel = new BoxLayout(memberIDInputPanel, BoxLayout.X_AXIS);

    private ListLivreDialog listLivreDialog;
    private ListMembreDialog listMembreDialog;

    private AttribuerLivre(){

        this.setLayout(new BorderLayout(10,30));
        this.setBackground(Color.WHITE);

        titleIcon = Utils.resizeImageIcon(titleIcon, 80, 80);

        // labels Fonts
        enterBookIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        enterMemberIDLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bookAvailableLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bookAvailableBooleanLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bookAvailableBooleanLabel.setForeground(Color.BLUE);
        issueDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        returnDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        noteLabel.setFont(new Font("Arial", Font.BOLD, 20));

        titleLabel.setFont(new Font("Arial", Font.BOLD+ Font.ITALIC, 40));
        titleLabel.setIcon(titleIcon);
        titlePanel.add(titleLabel);

        // Buttons
        attribuerButton.setFont(new Font("Arial", Font.BOLD, 20));
        attribuerButton.setContentAreaFilled(false);
        attribuerButton.setFocusPainted(false);
        attribuerButton.setFocusable(false);

        annulerButton.setFont(new Font("Arial", Font.BOLD, 20));
        annulerButton.setContentAreaFilled(false);
        annulerButton.setFocusPainted(false);
        annulerButton.setFocusable(false);

        searchBookButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchBookButton.setContentAreaFilled(false);
        searchBookButton.setFocusPainted(false);
        searchBookButton.setFocusable(false);

        searchMemberButton.setFont(new Font("Arial", Font.BOLD, 18));
        searchMemberButton.setContentAreaFilled(false);
        searchMemberButton.setFocusPainted(false);
        searchMemberButton.setFocusable(false);

        bookNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bookNameLabelPanel.setBackground(Color.WHITE);
        bookNameLabel.setForeground(Color.BLUE);
        bookNameLabelPanel.add(bookNameLabel);

        memberNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        memberNameLabel.setBackground(Color.WHITE);
        memberNameLabel.setForeground(Color.BLUE);
        memberNameLabelPanel.add(memberNameLabel);

        //
        mainSectionPanel.setBackground(Color.WHITE);
        inputPanel.setBackground(Color.WHITE);
        inputPanelA.setBackground(Color.WHITE);
        inputPanelB.setBackground(Color.WHITE);
        titlePanel.setBackground(Color.WHITE);
        notePanel.setBackground(Color.WHITE);
        memberNameLabelPanel.setBackground(Color.WHITE);
        dateAttributionPanel.setBackground(Color.WHITE);
        dateRetourPanel.setBackground(Color.WHITE);
        emptyMemberPanel.setBackground(Color.WHITE);
        emptyBookPanel.setBackground(Color.WHITE);
        bookIDInputPanel.setBackground(Color.WHITE);
        memberIDInputPanel.setBackground(Color.WHITE);
        returnDateFlowPanel.setBackground(Color.WHITE);
        issueDateFlowPanel.setBackground(Color.WHITE);
        noteFlowPanel.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);


//        bookIDInputPanel.add(enterBookIDTextField);
        bookIDInputPanel.add(livreIdSpinner);
        bookIDInputPanel.add(Box.createHorizontalStrut(20));
        bookIDInputPanel.add(searchBookButton);
        bookIDInputPanel.add(Box.createHorizontalStrut(50));

//        memberIDInputPanel.add(enterMemberIDTextField);
        memberIDInputPanel.add(membreIdSpinner);
        memberIDInputPanel.add(Box.createHorizontalStrut(20));
        memberIDInputPanel.add(searchMemberButton);
        memberIDInputPanel.add(Box.createHorizontalStrut(50));

        inputPanelA.add(enterBookIDLabel);
        inputPanelA.add(emptyBookPanel); //
        inputPanelA.add(enterMemberIDLabel);
        inputPanelA.add(emptyMemberPanel); //
        inputPanelA.add(bookAvailableLabel);

        // issueDateFlowPanel
        issueDateFlowPanel.add(issueDateLabel);
        inputPanelA.add(issueDateFlowPanel);

        // returnDateFlowPanel
        returnDateFlowPanel.add(returnDateLabel);
        inputPanelA.add(returnDateFlowPanel);

        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setLineWrap(true);
        scrollPane.setPreferredSize(new Dimension(100,100));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //
        noteTextArea.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        bookIDInputPanel.setLayout(bookIDInputBoxLayoutPanel);
        memberIDInputPanel.setLayout(memberIDInputBoxLayoutPanel);

        inputPanelB.add(bookIDInputPanel);
        inputPanelB.add(bookNameLabelPanel);
        inputPanelB.add(memberIDInputPanel);
        inputPanelB.add(memberNameLabelPanel);

        dateAttributionPanel.setLayout(dateBoxLayoutPanel);
        dateRetourPanel.setLayout(dateRetourBoxLayoutPanel);
        buttonPanel.setLayout(buttonBoxLayoutPanel);
        notePanel.setLayout(noteBoxLayoutPanel);

        dateAttributionPanel.add(dateAttributionChooser);
        dateAttributionPanel.add(Box.createHorizontalStrut(300)); //

        dateRetourPanel.add(dateRetourChooser);
        dateRetourPanel.add(Box.createHorizontalStrut(300)); //

        inputPanelB.add(bookAvailableBooleanLabel);
        inputPanelB.add(dateAttributionPanel);
        inputPanelB.add(dateRetourPanel);

        // noteFlowPanel
        noteFlowPanel.add(noteLabel);

        notePanel.add(Box.createHorizontalStrut(-105));
        notePanel.add(noteFlowPanel);
        notePanel.add(scrollPane);
        notePanel.add(Box.createHorizontalStrut(200));

        inputPanel.add(inputPanelA, BorderLayout.WEST);
        inputPanel.add(inputPanelB, BorderLayout.CENTER);
        inputPanel.add(notePanel, BorderLayout.SOUTH);

        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(attribuerButton);
        buttonPanel.add(Box.createHorizontalStrut(100));
        buttonPanel.add(annulerButton);
        buttonPanel.add(Box.createHorizontalGlue());

        //
        searchBookButton.setRolloverEnabled(true);
        searchBookButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                searchBookButton.setBackground(new Color(181, 82, 92));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchBookButton.setBackground(new Color(0,116,179));
            }
        });
        searchBookButton.addActionListener(e -> {
            listLivreDialog = new ListLivreDialog(this);
            listLivreDialog.setVisible(true);
        });

        //
        searchMemberButton.setRolloverEnabled(true);
        searchMemberButton.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                searchMemberButton.setBackground(new Color(181, 82, 92));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchMemberButton.setBackground(new Color(0,116,179));
            }
        });
        searchMemberButton.addActionListener(e -> {
            listMembreDialog = new ListMembreDialog(this);
            listMembreDialog.setVisible(true);

        });

        //
        annulerButton.addActionListener(e -> {
            enterBookIDLabel.setText("Entrer l'ID du livre");
            enterMemberIDLabel.setText("Entrer l'ID du membre");
            noteTextArea.setText("");
            dateRetourChooser.setDate(null);
            dateAttributionChooser.setDate(null);
            bookAvailableBooleanLabel.setText("Oui-ou-Non");
            bookAvailableBooleanLabel.setForeground(Color.BLUE);
        });

        //
        attribuerButton.addActionListener(e -> {
            int idLivre = (int) livreIdSpinner.getValue();
            int idMembre = (int) membreIdSpinner.getValue();

            Livre livre = librairie.getLivre(idLivre);
            Membre membre = librairie.getMembre(idMembre);


            try {
                if (livre.getIsAvailable()) {
                    if (dateRetourChooser.getDate().getTime() > dateAttributionChooser.getDate().getTime()){
                        livre.setIsAvailable(false);
                        livre.setMembre(membre);

                        JOptionPane.showMessageDialog(
                                AttribuerLivre.this,
                                "Le Livre a été attribué au membre avec l'id = " + membre.getId()
                        );
                    }
                    else{
                        JOptionPane.showMessageDialog(
                                AttribuerLivre.this,
                                "erreur: date de retour < date de réception!!"

                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            AttribuerLivre.this,
                            "erreur: Le livre a été déjà attribué!"

                    );
                }

            }
            catch (NullPointerException nullptr) {
                JOptionPane.showMessageDialog(
                        AttribuerLivre.this,
                        ""+nullptr.getMessage()
                );
            }

        });

        //
        inputPanel.setBorder(BorderFactory.createEmptyBorder(
                40,
                200,
                80,
                200
        ));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(
                0,
                40,
                60,
                40
        ));
        inputPanelStyle.setBorder(BorderFactory.createMatteBorder(
                1,
                1,
                1,
                1,
                Color.BLACK
        ));

        mainSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                20,
                10,
                20
        ));
        this.setLayout(new BorderLayout(10,10));

        inputPanelStyle.add(inputPanel);
        mainSectionPanel.add(inputPanelStyle, BorderLayout.CENTER);
        mainSectionPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(mainSectionPanel, BorderLayout.CENTER);

    }
    public static AttribuerLivre getInstance() {
        if (attribuerLivre == null) {
            attribuerLivre = new AttribuerLivre();
        }

        return attribuerLivre;
    }





}
