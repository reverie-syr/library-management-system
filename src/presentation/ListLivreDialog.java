package presentation;

import dao.ILibrairie;
import lombok.Getter;
import lombok.Setter;
import metier.entity.Auteur;

import metier.entity.Livre;
import presentation.tableModeles.TableModeleAuteur;
import presentation.tableModeles.TableModeleLivre;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class ListLivreDialog extends JDialog {
    private static final JFrame adminParentFrame = Admin.getInstance();
    private final JPanel mainSectionPanel = new JPanel(new BorderLayout());
    private final JPanel tablePanel = new JPanel(new BorderLayout());
    private final JPanel titlePanel = new JPanel();
    // ILibrairie
    private final ILibrairie librairie = dao.Librairie.getLibrairie();
    // TableModeleAuteur
    private final TableModeleLivre tableModeleLivre = TableModeleLivre.getInstance();

    // JTable
    private final JTable table = new JTable(tableModeleLivre);
    private final JScrollPane scrollPane = new JScrollPane(table);
    private final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();

    // JLabels
    private final JLabel titleLabel = new JLabel("Liste Des Livres");
    // ImageIcons
    private ImageIcon titleIcon = new ImageIcon("src\\images\\icon\\quill-pen.png");

    private static final int DIALOG_WIDTH = 700;
    private static final int DIALOG_HEIGHT = 500;
    private static final int IMAGE_SIZE = 80;

    Livre livre;
    int id = -1;

    public ListLivreDialog(JPanel parentPanel){
        super(adminParentFrame, "Liste Des Livres", true);

        this.setLayout(new BorderLayout(10,30));
        this.setBackground(Color.WHITE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        setIconImage(tk.getImage("src\\images\\icon\\listAuteursDialogIcon.png"));

        titleIcon = Utils.resizeImageIcon(titleIcon, IMAGE_SIZE, IMAGE_SIZE);
        titleLabel.setIcon(titleIcon);
        titlePanel.add(titleLabel);
        //
        titleLabel.setFont(new Font("Arial", Font.BOLD+Font.ITALIC, 40));



        //
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        table.getTableHeader().setBackground(new Color(181, 82, 92));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setForeground(Color.WHITE);

        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tableModeleLivre.chargerTable(librairie.getAllLivre());

        mainSectionPanel.add(titlePanel, BorderLayout.NORTH);
        mainSectionPanel.add(tablePanel, BorderLayout.CENTER);
        mainSectionPanel.setBorder(BorderFactory.createEmptyBorder(
                10,
                10,
                10,
                10
        ));
        tablePanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));

        titlePanel.setBackground(Color.WHITE);
        tablePanel.setBackground(Color.WHITE);
        mainSectionPanel.setBackground(Color.WHITE);
        this.add(mainSectionPanel, BorderLayout.CENTER);

        this.setSize(new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT));
        this.setLocationRelativeTo(parentPanel);

        SwingUtilities.invokeLater(() -> this.setVisible(true));


        //
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    id = (int) target.getValueAt(row, 0);

                    livre = librairie.getLivre(id);

                    if (parentPanel instanceof AttribuerLivre){
                        final AttribuerLivre parentPanel = AttribuerLivre.getInstance();

                        parentPanel.getLivreIdSpinner().setValue(livre.getID());
                        parentPanel.getBookNameLabel().setText(livre.getTitre());
                        parentPanel.getBookAvailableBooleanLabel().setText((livre.getIsAvailable())? "Oui" : "Non");
                        parentPanel.getBookAvailableBooleanLabel().setForeground((livre.getIsAvailable())? Color.GREEN : Color.RED);
                    }

                    ListLivreDialog.this.dispose();
                }
            }
        });
    }


}

