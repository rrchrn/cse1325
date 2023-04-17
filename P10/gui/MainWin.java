package gui;

import store.Computer;
import store.Customer;
import store.Store;
import store.Option;
import javax.swing.JFrame; // for main window
import javax.swing.JOptionPane; // for standard dialogs
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar; // row of menu selections
import javax.swing.JMenu; // menu selection that offers another menu
import javax.swing.JMenuItem; // menu selection that does something
import javax.swing.JToolBar; // row of buttons under the menu
import javax.swing.JButton; // regular button
import javax.swing.JComboBox;

import javax.swing.JToggleButton; // 2-state button
import javax.swing.BorderFactory; // manufacturers Border objects around buttons
import javax.swing.Box; // to create toolbar spacer
import javax.swing.UIManager; // to access default icons
import javax.swing.JLabel; // text or image holder
import javax.swing.JList;
import javax.swing.ImageIcon; // holds a custom icon
import javax.swing.SwingConstants; // useful values for Swing method calls

import javax.imageio.ImageIO; // loads an image from a file
import javax.management.openmbean.OpenDataException;

import java.util.Arrays;
import java.awt.BorderLayout; // layout manager for main window
import java.awt.FlowLayout; // layout manager for About dialog

import java.awt.Color; // the color of widgets, text, or borders
import java.awt.Font; // rich text in a JLabel or similar widget
import java.awt.image.BufferedImage; // holds an image loaded from a file

public class MainWin extends JFrame {
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        Store store = new Store("Store");

        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();

        // File Tab
        JMenu file = new JMenu("File");
        // Create New, Open, Save, Save As Menu Items//
        JMenuItem anew = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem quit = new JMenuItem("Quit");
        file.add(anew);
        file.add(open);
        file.add(save);
        file.add(saveAs);

        // ^^^^^^ Add action listener events
        file.add(quit);

        // HELP Tab
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        JMenu view = new JMenu("View");
        JMenuItem customers = new JMenuItem("Customers");
        JMenuItem options = new JMenuItem("Options");
        JMenuItem computers = new JMenuItem("Computers");
        JMenuItem orders = new JMenuItem("Orders");
        view.add(customers);
        view.add(options);
        view.add(computers);
        view.add(orders);

        JMenu insert = new JMenu("Insert");
        JMenuItem customer = new JMenuItem("Customer");
        JMenuItem option = new JMenuItem("Option");
        JMenuItem computer = new JMenuItem("Computer");
        JMenuItem order = new JMenuItem("Order");
        insert.add(customer);
        insert.add(option);
        insert.add(computer);
        insert.add(order);

        // For File
        anew.addActionListener(event -> onNewStoreClick());
        open.addActionListener(event -> onOpenStoreClick());
        save.addActionListener(event -> OnSaveClick());
        saveAs.addActionListener(event -> onSaveAsClick());
        quit.addActionListener(event -> onQuitClick());

        // For Insert
        customer.addActionListener(event -> onInsertCustomerClick(store));
        option.addActionListener(event -> onInsertOptionClick(store));
        computer.addActionListener(event -> onInsertComputerClick(store));
        order.addActionListener(event -> onInsertOrderClick(store));
        // For View
        options.addActionListener(event -> onViewClick(Record.OPTION, store));
        customers.addActionListener(event -> onViewClick(Record.CUSTOMER, store));
        computers.addActionListener(event -> onViewClick(Record.COMPUTER, store));
        orders.addActionListener(event -> onViewClick(Record.ORDER, store));
        // For Help
        about.addActionListener(event -> onAboutClick());

        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);

        setJMenuBar(menubar);

        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R

        // Add a toolbar to the PAGE_START region below the menu
        // REMOVE FOR NOW
        JToolBar toolbar = new JToolBar("ELSA Controls");

        // 3 more buttons - save, save as, new, open,
        toolbar.addSeparator();
        // File - New
        JButton newButton = new JButton(new ImageIcon("gui/resources/new_icon.png"));
        toolbar.add(newButton);
        newButton.addActionListener(event -> onNewStoreClick());
        // File - Open
        JButton openButton = new JButton(new ImageIcon("gui/resources/open_icon.png"));
        toolbar.add(openButton);
        openButton.addActionListener(event -> onOpenStoreClick());
        // File - Save
        JButton saveButton = new JButton(new ImageIcon("gui/resources/save_icon.png"));
        toolbar.add(saveButton);
        saveButton.addActionListener(event -> OnSaveClick());
        // File -Save As
        JButton saveasButton = new JButton(new ImageIcon("gui/resources/saveas_icon.png"));
        toolbar.add(saveasButton);
        saveasButton.addActionListener(event -> onSaveAsClick());

        toolbar.addSeparator();
        // Insert Option
        JButton button2 = new JButton(new ImageIcon("gui/resources/add_option.png"));
        toolbar.add(button2);
        button2.addActionListener(event -> onInsertOptionClick(store));

        // Insert Customer
        JButton button4 = new JButton(new ImageIcon("gui/resources/add_customer.png"));
        toolbar.add(button4);
        button4.addActionListener(event -> onInsertCustomerClick(store));

        // Insert Computer
        JButton button3 = new JButton(new ImageIcon("gui/resources/add_computer.png"));
        toolbar.add(button3);
        button3.addActionListener(event -> onInsertComputerClick(store));

        // Insert Order Button
        JButton insertOrder = new JButton(new ImageIcon("gui/resources/insert_order.png"));
        toolbar.add(insertOrder);
        insertOrder.addActionListener(event -> onInsertOrderClick(store));

        // add seperator
        toolbar.addSeparator();

        // View Customer
        JButton button1 = new JButton(new ImageIcon("gui/resources/view_customers.png"));
        toolbar.add(button1);
        button1.addActionListener(event -> onViewClick(Record.CUSTOMER, store));

        // View Option
        JButton button5 = new JButton(new ImageIcon("gui/resources/view_options.png"));
        toolbar.add(button5);
        button5.addActionListener(event -> onViewClick(Record.OPTION, store));

        // View Computer
        JButton button6 = new JButton(new ImageIcon("gui/resources/view_computers.png"));
        toolbar.add(button6);
        button6.addActionListener(event -> onViewClick(Record.COMPUTER, store));

        /// View Orders Button
        JButton viewOrder = new JButton(new ImageIcon("gui/resources/view_orders.png"));
        toolbar.add(viewOrder);
        viewOrder.addActionListener(event -> onViewClick(Record.ORDER, store));

        toolbar.add(Box.createHorizontalGlue());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        // Provide a text entry box to show the remaining sticks
        final String computerString[] = {};
        final String customerString[] = {};
        JList<String> computerList = new JList<String>(computerString);
        computerList.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(computerList, BorderLayout.CENTER);

        JList<String> customerList = new JList<String>(customerString);
        customerList.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(customerList, BorderLayout.LINE_END);

        // S T A T U S B A R D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);

        // Make everything in the JFrame visible
        setVisible(true);

        // Instance new Store

    }

    protected enum Record {
        CUSTOMER, OPTION, COMPUTER, ORDER
    }

    // File Button Listeners

    // New
    protected void onNewStoreClick() {

        String storeName = JOptionPane.showInputDialog("Enter the name of your new store:");
        if (storeName != null && !storeName.isEmpty()) {
            newStore = new Store(storeName);
        }
        msg.setFont(new JLabel().getFont());
    }

    // Open from Prof Rice solution
    protected void onOpenStoreClick() {
        final JFileChooser fc = new JFileChooser(filename); // Create a file chooser dialog
        FileFilter storeFiles = new FileNameExtensionFilter("Store files", "store");
        fc.addChoosableFileFilter(storeFiles); // Add "Nim file" filter
        fc.setFileFilter(storeFiles); // Show Nim files only by default

        int result = fc.showOpenDialog(this); // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile(); // Obtain the selected File object

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

                // Fill for open store
                if (computerUser != null && computerUser.isSelected()) {
                    // Fill for open store
                    if (computerUser.isSelected()) {
                        computerUser.doClick();
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                        "Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    // Save
    protected void OnSaveClick() {
        if (newStore != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                bw.write("ELSA" + '\n');
                bw.write("1.0" + '\n');
                newStore.save(bw);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Unable to open " + filename + '\n' + e,
                        "Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No store to save.", "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // SaveAs
    protected void onSaveAsClick() {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter storeFiles = new FileNameExtensionFilter("Store Files", "store");
        fc.addChoosableFileFilter(storeFiles);
        fc.setFileFilter(storeFiles);

        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();
            if (!filename.getAbsolutePath().endsWith(".store")) {
                filename = new File(filename.getAbsolutePath() + ".store");
                OnSaveClick();
            }
        }
    }

    // Listeners
    protected void onInsertCustomerClick(Store store) {
        JFrame frame = new JFrame("New Customer");
        JTextField customerName = new JTextField(30);
        JTextField customerEmail = new JTextField(40);

        Object[] labels = { "Customer Name", customerName, "Customer email", customerEmail };

        int dialogBox = JOptionPane.showConfirmDialog(frame, labels, "New Customer", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (dialogBox == JOptionPane.YES_OPTION) {
            try {

                Customer newCustomer = new Customer(customerName.getText(), customerEmail.getText());
                store.add(newCustomer);
                JOptionPane.showMessageDialog(null, "Customer Added!", "Confirmation", JOptionPane.PLAIN_MESSAGE);

            } catch (IllegalArgumentException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Invalid Email Address. Please Try Again", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {

            }
        } else {
            return;
        }

    }

    protected void onInsertOptionClick(Store store) {
        JFrame frame = new JFrame("New Customer");
        JTextField optionName = new JTextField(30);
        JTextField cost = new JTextField(40);

        Object[] labels = { "Option Name", optionName, "Cost (Write as 4.99 or 5.00)", cost };

        int dialogBox = JOptionPane.showConfirmDialog(frame, labels, "New Option", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        // Need to put add button so if dialogBox == add then add and then do again
        if (dialogBox == JOptionPane.YES_OPTION) {
            try {
                long costLong = (long) (Double.parseDouble(cost.getText()) * 100.00);
                Option newOption = new Option(optionName.getText(), costLong);

                store.add(newOption);

                JOptionPane.showMessageDialog(null, "Option Added!", "Confirmation", JOptionPane.PLAIN_MESSAGE);

            } catch (IllegalArgumentException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Invalid Number. Please Try Again", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {

            }
        } else { // this will be where the cancel will go
            return;
        }

    }

    // Professor Rice suggested solution code
    protected void onInsertComputerClick(Store store) {
        try {
            Computer c = new Computer(
                    JOptionPane.showInputDialog(this, "Computer name",
                            "New Computer", JOptionPane.QUESTION_MESSAGE),
                    JOptionPane.showInputDialog(this, "Computer model",
                            "New Computer", JOptionPane.QUESTION_MESSAGE));
            JComboBox<Object> cb = new JComboBox<>(store.getOptions());
            int optionsAdded = 0; // Don't add computers with no options
            while (true) {
                int button = JOptionPane.showConfirmDialog(this, cb,
                        "Another Option?", JOptionPane.YES_NO_OPTION);
                if (button != JOptionPane.YES_OPTION)
                    break;
                c.addOption((Option) cb.getSelectedItem());
                ++optionsAdded;
            }
            if (optionsAdded > 0) {
                store.add(c);
                onViewClick(Record.COMPUTER, store);
            }
        } catch (NullPointerException e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e,
                    "Computer Not Created", JOptionPane.ERROR_MESSAGE);
        }

    }

    protected void onInsertOrderClick(Store store) {
        if (customers.isEmpty()) {
            onInsertCustomerClick(store);
        }

    }

    protected void onViewClick(Record record, Store store) {

        // Define header
        String header = "";
        Object[] objects;

        // CUSTOMER, OPTION, COMPUTER, ORDER

        // Switch based on parameter
        switch (record) {
            case CUSTOMER: {
                header = "<html><h2>Customers</h2>";
                objects = store.getCustomers();
                break;
            }
            case OPTION: {
                header = "<html><h2>Options</h2>";
                objects = store.getOptions();
                break;
            }
            case COMPUTER: {
                header = "<html><h2>Computers</h2>";
                objects = store.getComputers();
                break;
            }
            case ORDER: {
                header = "<html><h2>Orders</h2>";
                objects = store.getOrders();
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid Record Type" + record);

            }

            // Numbered list of objects returned by the store
            // String Builder that iterates through everything

        }

        StringBuilder builder = new StringBuilder("<html><ol>");
        for (Object object : objects) {
            builder.append("<li>" + object.toString() + "</li>");
        }
        builder.append("</ol></html>");

        JLabel viewClickLabel = new JLabel(header + builder.toString());
        JOptionPane.showMessageDialog(this, viewClickLabel);

    }

    ////////////////// ABOUT CLICK //////////////////////////
    // From Professor Rice Suggested Solutions
    protected void onAboutClick() { // Display About dialog

        class Canvas extends JPanel {

            public Canvas() {
                String imageFile = "gui/resources/about_icon.png";
                try {
                    image = ImageIO.read(new File(imageFile));
                } catch (IOException e) {
                    throw new RuntimeException("Unable to load image from " + imageFile, e);
                }
            }

            public Dimension getPreferredSize() {
                return new Dimension(250, 200); // Suggest canvas size
            }

            @Override
            public void paintComponent(Graphics graphics) {
                super.paintComponent(graphics); // Give JPanel a turn
                Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast
                int width = getWidth();
                int height = getHeight();

                /// From StackOverflow
                g.setColor(Color.GREEN);
                g.drawLine(0, height / 2, width / 2, 0);
                g.setColor(Color.BLUE);
                g.drawLine(width / 2, 0, width, height / 2);
                g.setColor(Color.MAGENTA);
                g.drawLine(0, height, width / 2, height / 2);
                g.setColor(Color.CYAN);
                g.drawLine(width / 2, height / 2, width, height);
                g.setColor(Color.RED);
                g.drawString("\n\n!! ELSA !!", (width / 2) + 40, height / 2);
                g.drawImage(image, (width / 2) - 70, (height / 2) - 60, this);

            }
        }

        Canvas canvas = new Canvas();

        JLabel title = new JLabel("<html>"
                + "<p><font size=+4>ELSA</font></p>"
                + "</html>",
                SwingConstants.CENTER);

        JLabel subtitle = new JLabel("<html>"
                + "<p>Exceptional Laptops and Supercomputers Always</p>"
                + "</html>",
                SwingConstants.CENTER);

        JLabel version = new JLabel("<html>"
                + "<p>Version 0.2</p>"
                + "</html>",
                SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
                + "<br/><p>Copyright 2017-2023 by George F. Rice</p>"
                + "<p>Licensed under Gnu GPL 3.0</p><br/>"

                + "<br/><p>Add Customer icon based on work by Dreamstate per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/user_3114957</font></p>"

                + "<br/><p>View Customers icon based on work by Ilham Fitrotul Hayat per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/group_694642</font></p>"

                + "<br/><p>Add Option icon based on work by Freepik per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/quantum-computing_4103999</font></p>"

                + "<br/><p>View Options icon based on work by Freepik per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/edge_8002173</font></p>"

                + "<br/><p>Add Computer icon based on work by Freepik per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/laptop_689396</font></p>"

                + "<br/><p>View Computers icon based on work by Futuer per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/computer-networks_9672993</font></p>"

                + "<br/><p>About icon based on work by Leremy per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/computer_9991911</font></p>"

                // Add Icon
                + "<br/><p>New Store Icon based on work by Ilham Fitrotul Hayat per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/add_3394636</font></p>"

                // Open Icon
                + "<br/><p>Open Icon based on work by Freepik per the Flaticon license</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/folder_73581</font></p>"

                // Save Icon
                + "<br/><p>Save Icon based on work by Yogi Aprelliyanto per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/diskette_2874091</font></p>"

                // Save as icon
                + "<br/><p>Save As Icon based on work by Yogi Aprelliyanto per the Flaticon License</p>"
                + "<p><font size=-2>https://www.flaticon.com/free-icon/diskette_2874050</font></p>"

                // Insert Order icon
                + "<br/><p>Insert Order Icon based on work by vectorsmarket15 per the Flaticon License</p>"
                + "<p><font size=-2></font>https://www.flaticon.com/free-icon/order-delivery_3624080</p>"

                // View Order
                + "<br/><p>View Order Icon based on work by FreePik per the Flaticon License</p>"
                + "<p><font size=-2></font>https://www.flaticon.com/free-icon/file_1150592</p>"

                + "</html>");

        JOptionPane.showMessageDialog(this,
                new Object[] { canvas, title, subtitle, version, artists },
                "ELSA",
                JOptionPane.PLAIN_MESSAGE);
    }

    protected void onQuitClick() {
        System.exit(0);
    } // Exit the game

    private JLabel msg; // Status message display
    private File filename;
    private BufferedImage image;
    private Store newStore;
    private JToggleButton computerUser;

}