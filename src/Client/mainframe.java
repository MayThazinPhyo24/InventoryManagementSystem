package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Server.InventoryInterface;

public class mainframe extends JFrame {

    // =====================================================
    // COLORS
    // =====================================================

    private final Color SIDEBAR_COLOR =
            new Color(45, 27, 78);

    private final Color BACKGROUND_COLOR =
            new Color(245, 240, 250);

    private final Color PANEL_COLOR =
            new Color(255, 252, 255);

    private final Color CARD_COLOR =
            new Color(45, 27, 78);

    private final Color WHITE =
            Color.WHITE;

    private final Color TEXT_COLOR =
            new Color(45, 27, 78);

    private final Color BUTTON_COLOR =
            new Color(124, 58, 237);

    private final Color SUCCESS_COLOR =
            new Color(16, 185, 129);

    private final Color DANGER_COLOR =
            new Color(225, 29, 72);

    private final Color GRAY_COLOR =
            new Color(107, 92, 126);

    // =====================================================
    // BUILT-IN UNICODE ICONS
    // =====================================================
    // These symbols do not require an external font file.

    private static final String ICON_HOME = "⌂";
    private static final String ICON_PRODUCTS = "▤";
    private static final String ICON_PLUS = "＋";
    private static final String ICON_EDIT = "✎";
    private static final String ICON_TRASH = "✖";
    private static final String ICON_SEARCH = "⌕";
    private static final String ICON_CART = "▣";
    private static final String ICON_EXIT = "⇥";

    // =====================================================
    // RMI OBJECT
    // =====================================================

    private InventoryInterface inventory;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public mainframe() {

        

        setTitle(
                "Inventory Management System"
        );

        setSize(
                1100,
                700
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );

        // Connect RMI server
        connectToServer();

        // Main UI
        add(
                createSidebar(),
                BorderLayout.WEST
        );

        add(
                createDashboard(),
                BorderLayout.CENTER
        );
    }

    

    // =====================================================
    // CONNECT TO RMI SERVER
    // =====================================================

    private void connectToServer() {

        try {

            inventory =
                    (InventoryInterface)
                    Naming.lookup(
                            "rmi://localhost/InventoryService"
                    );

        } catch (Exception e) {

            inventory = null;

            JOptionPane.showMessageDialog(
                    this,
                    "Could not connect to RMI Server.\n\n"
                    + "Please make sure the server is running.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SIDEBAR
    // =====================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel();

        sidebar.setBackground(
                SIDEBAR_COLOR
        );

        sidebar.setPreferredSize(
                new Dimension(
                        220,
                        700
                )
        );

        sidebar.setLayout(
                new BorderLayout()
        );

        // =================================================
        // LOGO
        // =================================================

        JPanel logoPanel =
                new JPanel();

        logoPanel.setBackground(
                SIDEBAR_COLOR
        );

        logoPanel.setBorder(
                new EmptyBorder(
                        30,
                        20,
                        30,
                        20
                )
        );

        logoPanel.setLayout(
                new GridLayout(
                        2,
                        1
                )
        );

        JLabel logo =
                new JLabel(
                        ICON_PRODUCTS
                        + "  INVENTORY"
                );

        logo.setForeground(
                WHITE
        );

        logo.setFont(
                new Font(
                        "Segoe UI Symbol",
                        Font.BOLD,
                        20
                )
        );

        logo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        JLabel subtitle =
                new JLabel(
                        "MANAGEMENT SYSTEM"
                );

        subtitle.setForeground(
                new Color(
                        209,
                        213,
                        219
                )
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        logoPanel.add(
                logo
        );

        logoPanel.add(
                subtitle
        );

        sidebar.add(
                logoPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // MENU PANEL
        // =================================================

        JPanel menuPanel =
                new JPanel();

        menuPanel.setBackground(
                SIDEBAR_COLOR
        );

        menuPanel.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        15
                )
        );

        menuPanel.setLayout(
                new GridLayout(
                        8,
                        1,
                        0,
                        8
                )
        );

        // =================================================
        // MENU BUTTONS
        // =================================================

        JButton dashboardButton =
                createMenuButton(
                        ICON_HOME,
                        "Dashboard"
                );

        JButton productsButton =
                createMenuButton(
                        ICON_PRODUCTS,
                        "Products"
                );

        JButton addButton =
                createMenuButton(
                        ICON_PLUS,
                        "Add Product"
                );

        JButton updateButton =
                createMenuButton(
                        ICON_EDIT,
                        "Update Product"
                );

        JButton deleteButton =
                createMenuButton(
                        ICON_TRASH,
                        "Delete Product"
                );

        JButton searchButton =
                createMenuButton(
                        ICON_SEARCH,
                        "Search Product"
                );

        JButton sellButton =
                createMenuButton(
                        ICON_CART,
                        "Sell Product"
                );

        JButton exitButton =
                createMenuButton(
                        ICON_EXIT,
                        "Exit"
                );

        // =================================================
        // ADD BUTTONS
        // =================================================

        menuPanel.add(
                dashboardButton
        );

        menuPanel.add(
                productsButton
        );

        menuPanel.add(
                addButton
        );

        menuPanel.add(
                updateButton
        );

        menuPanel.add(
                deleteButton
        );

        menuPanel.add(
                searchButton
        );

        menuPanel.add(
                sellButton
        );

        menuPanel.add(
                exitButton
        );

        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        dashboardButton.addActionListener(
                e -> showDashboard()
        );

        productsButton.addActionListener(
                e -> viewProducts()
        );

        addButton.addActionListener(
                e -> addProduct()
        );

        updateButton.addActionListener(
                e -> updateProduct()
        );

        deleteButton.addActionListener(
                e -> deleteProduct()
        );

        searchButton.addActionListener(
                e -> searchProduct()
        );

        sellButton.addActionListener(
                e -> sellProduct()
        );

        exitButton.addActionListener(
                e -> {

                    int result =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to exit?",
                                    "Exit",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (result ==
                            JOptionPane.YES_OPTION) {

                        System.exit(0);
                    }
                }
        );

        return sidebar;
    }

    // =====================================================
    // CREATE MENU BUTTON
    // =====================================================

    private JButton createMenuButton(
            String icon,
            String text) {

        // Use a real Swing Icon instead of a text glyph. This works
        // even when the operating system does not contain the symbol font.
        JButton button =
                new JButton(
                        text,
                        createMenuIcon(icon)
                );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                SIDEBAR_COLOR
        );

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        button.setIconTextGap(
                12
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private Icon createMenuIcon(
            String iconType) {

        return new MenuIcon(
                iconType,
                WHITE
        );
    }

    /**
     * Small vector icons drawn directly by Swing. No image files,
     * icon fonts, or external libraries are required.
     */
    private static class MenuIcon implements Icon {

        private final String type;
        private final Color color;

        private MenuIcon(
                String type,
                Color color) {

            this.type = type;
            this.color = color;
        }

        @Override
        public int getIconWidth() {
            return 22;
        }

        @Override
        public int getIconHeight() {
            return 22;
        }

        @Override
        public void paintIcon(
                java.awt.Component component,
                Graphics graphics,
                int x,
                int y) {

            Graphics2D g =
                    (Graphics2D) graphics.create();

            g.setColor(color);
            g.setStroke(
                    new BasicStroke(
                            2.0f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );
            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int left = x + 3;
            int top = y + 3;
            int right = x + 19;
            int bottom = y + 19;

            switch (type) {
                case "⌂":
                    int[] roofX = {x + 2, x + 11, x + 20};
                    int[] roofY = {y + 10, y + 2, y + 10};
                    g.drawPolyline(roofX, roofY, 3);
                    g.drawRect(x + 5, y + 10, 12, 9);
                    g.drawLine(x + 10, y + 19, x + 10, y + 14);
                    break;

                case "▤":
                    g.drawRect(left, top, 16, 16);
                    g.drawLine(x + 3, y + 8, x + 19, y + 8);
                    g.drawLine(x + 3, y + 13, x + 19, y + 13);
                    g.drawLine(x + 8, y + 3, x + 8, y + 19);
                    break;

                case "＋":
                    g.drawLine(x + 11, y + 4, x + 11, y + 18);
                    g.drawLine(x + 4, y + 11, x + 18, y + 11);
                    break;

                case "✎":
                    g.drawLine(x + 5, y + 17, x + 7, y + 12);
                    g.drawLine(x + 7, y + 12, x + 16, y + 3);
                    g.drawLine(x + 16, y + 3, x + 19, y + 6);
                    g.drawLine(x + 19, y + 6, x + 10, y + 15);
                    g.drawLine(x + 10, y + 15, x + 5, y + 17);
                    break;

                case "✖":
                    g.drawLine(x + 5, y + 7, x + 17, y + 19);
                    g.drawLine(x + 17, y + 7, x + 5, y + 19);
                    g.drawLine(x + 6, y + 5, x + 16, y + 5);
                    g.drawLine(x + 8, y + 3, x + 14, y + 3);
                    break;

                case "⌕":
                    g.drawOval(x + 3, y + 3, 11, 11);
                    g.drawLine(x + 12, y + 12, x + 19, y + 19);
                    break;

                case "▣":
                    g.drawLine(x + 3, y + 5, x + 6, y + 5);
                    g.drawLine(x + 6, y + 5, x + 9, y + 15);
                    g.drawLine(x + 9, y + 15, x + 18, y + 15);
                    g.drawLine(x + 8, y + 8, x + 19, y + 8);
                    g.drawLine(x + 19, y + 8, x + 17, y + 13);
                    g.fillOval(x + 9, y + 17, 3, 3);
                    g.fillOval(x + 16, y + 17, 3, 3);
                    break;

                case "⇥":
                    g.drawLine(x + 4, y + 11, x + 17, y + 11);
                    g.drawLine(x + 12, y + 6, x + 17, y + 11);
                    g.drawLine(x + 12, y + 16, x + 17, y + 11);
                    g.drawLine(x + 4, y + 5, x + 4, y + 17);
                    break;

                default:
                    g.fillOval(x + 7, y + 7, 8, 8);
            }

            g.dispose();
        }
    }

    // =====================================================
    // DASHBOARD
    // =====================================================

    private JPanel createDashboard() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(
                false
        );

        JLabel title =
                new JLabel(
                        "Dashboard"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                TEXT_COLOR
        );

        JLabel status;

        if (inventory != null) {

            status =
                    new JLabel(
                            "● Server Connected"
                    );

            status.setForeground(
                    SUCCESS_COLOR
            );

        } else {

            status =
                    new JLabel(
                            "● Server Disconnected"
                    );

            status.setForeground(
                    DANGER_COLOR
            );
        }

        status.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        headerPanel.add(
                title,
                BorderLayout.WEST
        );

        headerPanel.add(
                status,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // DASHBOARD CARDS
        // =================================================

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        cardsPanel.setOpaque(
                false
        );

        cardsPanel.setBorder(
                new EmptyBorder(
                        30,
                        0,
                        30,
                        0
                )
        );

        String totalProducts =
                "0";

        String totalStock =
                "0";

        if (inventory != null) {

            try {

                String products =
                        inventory.viewProducts();

                if (!products.equals(
                        "No products available."
                )) {

                    int productCount =
                            0;

                    int stockCount =
                            0;

                    String[] lines =
                            products.split(
                                    "\n"
                            );

                    for (String line :
                            lines) {

                        if (line.trim()
                                .isEmpty()) {

                            continue;
                        }

                        productCount++;

                        if (line.contains(
                                "Quantity:"
                        )) {

                            try {

                                String quantityText =
                                        line.substring(
                                                line.indexOf(
                                                        "Quantity:"
                                                ) + 9
                                        ).trim();

                                int quantity =
                                        Integer.parseInt(
                                                quantityText
                                        );

                                stockCount +=
                                        quantity;

                            } catch (
                                    Exception ignored) {
                            }
                        }
                    }

                    totalProducts =
                            String.valueOf(
                                    productCount
                            );

                    totalStock =
                            String.valueOf(
                                    stockCount
                            );
                }

            } catch (
                    RemoteException e) {

                totalProducts =
                        "0";

                totalStock =
                        "0";
            }
        }

        cardsPanel.add(
                createCard(
                        "Total Products",
                        totalProducts
                )
        );

        cardsPanel.add(
                createCard(
                        "Total Stock",
                        totalStock
                )
        );

        String itemsSold =
                "0";

        if (inventory != null) {

            try {

                itemsSold =
                        String.valueOf(
                                inventory
                                        .getTotalItemsSold()
                        );

            } catch (
                    RemoteException e) {

                itemsSold =
                        "0";
            }
        }

        cardsPanel.add(
                createCard(
                        "Items Sold",
                        itemsSold
                )
        );

        mainPanel.add(
                cardsPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // WELCOME PANEL
        // =================================================

        JPanel welcomePanel =
                new JPanel(
                        new BorderLayout()
                );

        welcomePanel.setBackground(
                WHITE
        );

        welcomePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        JLabel welcomeTitle =
                new JLabel(
                        "Welcome to Inventory Management System"
                );

        welcomeTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        welcomeTitle.setForeground(
                TEXT_COLOR
        );

        JLabel welcomeText =
                new JLabel(
                        "Use the menu on the left to manage your products."
                );

        welcomeText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        welcomeText.setForeground(
                GRAY_COLOR
        );

        welcomePanel.add(
                welcomeTitle,
                BorderLayout.NORTH
        );

        welcomePanel.add(
                welcomeText,
                BorderLayout.CENTER
        );

        mainPanel.add(
                welcomePanel,
                BorderLayout.SOUTH
        );

        return mainPanel;
    }

    // =====================================================
    // DASHBOARD CARD
    // =====================================================

    private JPanel createCard(
            String title,
            String value) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                CARD_COLOR
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        229,
                                        231,
                                        235
                                )
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        // WHITE CARD TEXT
        titleLabel.setForeground(
                WHITE
        );

        JLabel valueLabel =
                new JLabel(
                        value
                );

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        // WHITE CARD TEXT
        valueLabel.setForeground(
                WHITE
        );

        card.add(
                titleLabel,
                BorderLayout.NORTH
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        return card;
    }

    // =====================================================
    // SHOW DASHBOARD
    // =====================================================

    private void showDashboard() {

        getContentPane()
                .removeAll();

        add(
                createSidebar(),
                BorderLayout.WEST
        );

        add(
                createDashboard(),
                BorderLayout.CENTER
        );

        revalidate();
        repaint();
    }

    // =====================================================
    // ADD PRODUCT
    // =====================================================

    private void addProduct() {

        if (!checkConnection()) {
            return;
        }

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        JTextField idField =
                new JTextField();

        JTextField nameField =
                new JTextField();

        JTextField quantityField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Product ID:"
                )
        );

        panel.add(
                idField
        );

        panel.add(
                new JLabel(
                        "Product Name:"
                )
        );

        panel.add(
                nameField
        );

        panel.add(
                new JLabel(
                        "Quantity:"
                )
        );

        panel.add(
                quantityField
        );

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Add Product",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String id =
                idField.getText()
                        .trim();

        String name =
                nameField.getText()
                        .trim();

        String quantityText =
                quantityField.getText()
                        .trim();

        if (id.isEmpty()
                || name.isEmpty()
                || quantityText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            if (quantity < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity cannot be negative.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            inventory.addProduct(
                    id,
                    name,
                    quantity
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Product added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (
                RemoteException e) {

            showServerError(e);
        }
    }

    // =====================================================
    // UPDATE PRODUCT
    // =====================================================

    private void updateProduct() {

        if (!checkConnection()) {
            return;
        }

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                10,
                                10
                        )
                );

        JTextField idField =
                new JTextField();

        JTextField nameField =
                new JTextField();

        JTextField quantityField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Product ID:"
                )
        );

        panel.add(
                idField
        );

        panel.add(
                new JLabel(
                        "New Product Name:"
                )
        );

        panel.add(
                nameField
        );

        panel.add(
                new JLabel(
                        "New Quantity:"
                )
        );

        panel.add(
                quantityField
        );

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Update Product",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String id =
                idField.getText()
                        .trim();

        String name =
                nameField.getText()
                        .trim();

        String quantityText =
                quantityField.getText()
                        .trim();

        if (id.isEmpty()
                || name.isEmpty()
                || quantityText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            if (quantity < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Quantity cannot be negative.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String product =
                    inventory.searchProduct(
                            id
                    );

            if (product.equals(
                    "Product not found."
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Product not found.",
                        "Update Product",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            inventory.updateProduct(
                    id,
                    name,
                    quantity
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Product updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (
                RemoteException e) {

            showServerError(e);
        }
    }

    // =====================================================
    // DELETE PRODUCT
    // =====================================================

    private void deleteProduct() {

        if (!checkConnection()) {
            return;
        }

        String id =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Product ID:",
                        "Delete Product",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (id == null
                || id.trim().isEmpty()) {

            return;
        }

        id =
                id.trim();

        try {

            String product =
                    inventory.searchProduct(
                            id
                    );

            if (product.equals(
                    "Product not found."
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Product not found.",
                        "Delete Product",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete:\n\n"
                            + product,
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirm !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            inventory.deleteProduct(
                    id
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Product deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                RemoteException e) {

            showServerError(e);
        }
    }

    // =====================================================
    // SEARCH PRODUCT
    // =====================================================

    private void searchProduct() {

        if (!checkConnection()) {
            return;
        }

        String id =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Product ID:",
                        "Search Product",
                        JOptionPane.QUESTION_MESSAGE
                );

        if (id == null
                || id.trim().isEmpty()) {

            return;
        }

        try {

            String result =
                    inventory.searchProduct(
                            id.trim()
                    );

            JTextArea textArea =
                    new JTextArea(
                            result
                    );

            textArea.setEditable(
                    false
            );

            textArea.setFont(
                    new Font(
                            "Monospaced",
                            Font.PLAIN,
                            14
                    )
            );

            textArea.setBorder(
                    new EmptyBorder(
                            10,
                            10,
                            10,
                            10
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    textArea,
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                RemoteException e) {

            showServerError(e);
        }
    }

    // =====================================================
    // VIEW PRODUCTS
    // =====================================================

    private void viewProducts() {

        if (!checkConnection()) {
            return;
        }

        try {

            String result =
                    inventory.viewProducts();

            JTextArea textArea =
                    new JTextArea(
                            result
                    );

            textArea.setEditable(
                    false
            );

            textArea.setFont(
                    new Font(
                            "Monospaced",
                            Font.PLAIN,
                            14
                    )
            );

            textArea.setLineWrap(
                    false
            );

            textArea.setBorder(
                    new EmptyBorder(
                            10,
                            10,
                            10,
                            10
                    )
            );

            JScrollPane scrollPane =
                    new JScrollPane(
                            textArea
                    );

            scrollPane.setPreferredSize(
                    new Dimension(
                            650,
                            400
                    )
            );

            JOptionPane.showMessageDialog(
                    this,
                    scrollPane,
                    "All Products",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (
                RemoteException e) {

            showServerError(e);
        }
    }

    // =====================================================
    // SELL PRODUCT
    // =====================================================

    private void sellProduct() {

        if (!checkConnection()) {
            return;
        }

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                10,
                                10
                        )
                );

        JTextField idField =
                new JTextField();

        JTextField quantityField =
                new JTextField();

        panel.add(
                new JLabel(
                        "Product ID:"
                )
        );

        panel.add(
                idField
        );

        panel.add(
                new JLabel(
                        "Sale Quantity:"
                )
        );

        panel.add(
                quantityField
        );

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Sell Product",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String id =
                idField.getText()
                        .trim();

        String quantityText =
                quantityField.getText()
                        .trim();

        if (id.isEmpty()
                || quantityText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            int quantity =
                    Integer.parseInt(
                            quantityText
                    );

            if (quantity <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Sale quantity must be greater than 0.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String resultMessage =
                    inventory.sellProduct(
                            id,
                            quantity
                    );

            if (resultMessage.startsWith(
                    "Product sold successfully"
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        resultMessage,
                        "Sale Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        resultMessage,
                        "Sale Failed",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Quantity must be a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

        } catch (
                RemoteException e) {

            showServerError(e);
        }
    }

    // =====================================================
    // CONNECTION CHECK
    // =====================================================

    private boolean checkConnection() {

        if (inventory == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "RMI server is not connected.\n\n"
                    + "Please start the RMI server first.",
                    "Server Connection Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =====================================================
    // SERVER ERROR
    // =====================================================

    private void showServerError(
            RemoteException e) {

        JOptionPane.showMessageDialog(
                this,
                "RMI Server Error:\n\n"
                + e.getMessage(),
                "Server Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    mainframe frame =
                            new mainframe();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}