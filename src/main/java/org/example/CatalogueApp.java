package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CatalogueApp extends JFrame {

    private JTextField searchField;
    private JPanel cardContainer;
    private final ArrayList<ItemCard> cards = new ArrayList<>();

    public CatalogueApp() {
        setTitle("Product Catalogue");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- Search Bar at the Top ---
        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.setToolTipText("Search by name or keyword...");
        topPanel.add(new JLabel(" 🔍 Search: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // --- Container for Item Cards ---
        cardContainer = new JPanel(new GridLayout(0, 3, 15, 15)); // 3 columns, dynamic rows
        JScrollPane scrollPane = new JScrollPane(cardContainer);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // smooth scrolling
        add(scrollPane, BorderLayout.CENTER);

        // --- Add Sample Items ---
        addItem("Smartphone", "A sleek 5G smartphone with a powerful camera.", "images/phone.jpg");
        addItem("Laptop", "Lightweight laptop ideal for work and play.", "images/laptop.jpg");
        addItem("Smartwatch", "Track fitness and get notifications on the go.", "images/watch.jpg");
        addItem("Camera", "Capture beautiful moments in stunning clarity.", "images/camera.jpg");
        addItem("Headphones", "Noise-cancelling wireless headphones.", "images/headphones.jpg");
        addItem("Tablet", "A portable tablet perfect for reading and browsing.", "images/tablet.jpg");
        addItem("Bluetooth Speaker", "Compact speaker with rich sound.", "images/speaker.jpg");
        addItem("Mechanical Keyboard", "RGB backlit keyboard with tactile feedback.", "images/keyboard.jpg");

        // --- Search Functionality ---
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = searchField.getText().toLowerCase();
                for (ItemCard card : cards) {
                    boolean match = card.getTitle().toLowerCase().contains(text);
                    card.setVisible(match);
                }
                cardContainer.revalidate();
                cardContainer.repaint();
            }
        });

        setLocationRelativeTo(null); // center window on screen
        setVisible(true);
    }

    // --- Method to Add Each Item to the Catalogue ---
    private void addItem(String title, String description, String imagePath) {
        ItemCard card = new ItemCard(title, description, imagePath);
        cards.add(card);
        cardContainer.add(card);
    }

    // --- Inner Class Representing Each Item Card ---
    class ItemCard extends JPanel {
        private final String title;
        private final Color normalBorder = Color.LIGHT_GRAY;
        private final Color hoverBorder = new Color(100, 149, 237); // Cornflower Blue

        public ItemCard(String title, String description, String imagePath) {
            this.title = title;
            setLayout(new BorderLayout(5, 5));
            setBorder(BorderFactory.createLineBorder(normalBorder, 2));
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(250, 280));

            // --- Image ---
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImg = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            // --- Title ---
            JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

            // --- Description ---
            JTextArea descArea = new JTextArea(description);
            descArea.setWrapStyleWord(true);
            descArea.setLineWrap(true);
            descArea.setEditable(false);
            descArea.setOpaque(false);
            descArea.setFont(new Font("SansSerif", Font.PLAIN, 13));
            descArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            // --- Buttons Panel ---
            JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
            JButton viewBtn = new JButton("View More");
            JButton favBtn = new JButton("★ Favorite");
            btnPanel.add(viewBtn);
            btnPanel.add(favBtn);

            // --- Add Components ---
            add(imageLabel, BorderLayout.NORTH);
            add(titleLabel, BorderLayout.CENTER);
            add(descArea, BorderLayout.SOUTH);
            add(btnPanel, BorderLayout.PAGE_END);

            // --- Hover Effect ---
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBorder(BorderFactory.createLineBorder(hoverBorder, 3));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBorder(BorderFactory.createLineBorder(normalBorder, 2));
                }
            });

            // --- View More Button Action ---
            viewBtn.addActionListener(e ->
                    JOptionPane.showMessageDialog(
                            CatalogueApp.this,
                            title + "\n\n" + description,
                            "Item Details",
                            JOptionPane.INFORMATION_MESSAGE
                    )
            );

            // --- Favorite Button Action ---
            favBtn.addActionListener(e ->
                    JOptionPane.showMessageDialog(
                            CatalogueApp.this,
                            title + " added to favorites!",
                            "Favorite Added",
                            JOptionPane.PLAIN_MESSAGE
                    )
            );
        }

        public String getTitle() {
            return title;
        }
    }

    // --- Main Method ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CatalogueApp::new);
    }
}