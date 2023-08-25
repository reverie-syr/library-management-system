package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout.Alignment;



public class Test extends JFrame {

    private JPanel contentPane;
    File targetFile;
    BufferedImage targetImg;
    public JPanel panel,
            panel_1;
    private static final int baseSize = 128;
    private static final String basePath =
            "C:\\Documents and Settings\\Administrator\\Desktop\\Images";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test frame = new Test();
                    frame.setVisible(true);
                    frame.setResizable(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panel = new JPanel();
        panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        contentPane.add(panel, BorderLayout.WEST);

        JButton btnBrowse = new JButton("Browse");
        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                browseButtonActionPerformed(e);
            }
        });

        JLabel lblSelectTargetPicture = new JLabel("Select target picture..");

        JButton btnDetect = new JButton("Detect");
        btnDetect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton btnAddDigit = new JButton("Add Digit");
        btnAddDigit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        JButton button = new JButton("Recognize");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        panel_1 = new JPanel();
        panel_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(6)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(lblSelectTargetPicture)
                                                .addGap(6)
                                                .addComponent(btnBrowse))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(10)
                                                .addComponent(btnDetect)
                                                .addGap(18)
                                                .addComponent(btnAddDigit))))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(50)
                                .addComponent(button))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(7)
                                                .addComponent(lblSelectTargetPicture))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGap(3)
                                                .addComponent(btnBrowse)))
                                .addGap(18)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
                                .addGap(22)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(btnDetect)
                                        .addComponent(btnAddDigit))
                                .addGap(18)
                                .addComponent(button)
                                .addContainerGap())
        );

        panel.setLayout(gl_panel);
    }
    public BufferedImage rescale(BufferedImage originalImage)
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
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        panel_1.setLayout(new BorderLayout(0, 0));

        ImageIcon icon=new ImageIcon(targetImg);
        JLabel imglabel=new JLabel(icon);
        panel_1.add(imglabel);
        setVisible(true);
    }
    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fc = new JFileChooser(basePath);
        fc.setFileFilter(new ImageFileFilter());
        int res = fc.showOpenDialog(null);
        // We have an image!
        try {
            if (res == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                setTarget(file);
            } // Oops!
            else {
                JOptionPane.showMessageDialog(null,
                        "You must select one image to be the reference.", "Aborting...",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception iOException) {
        }

    }
}
