package gui;

import store.Store;
import store.Customer;
import store.Option;
import store.Computer;
import store.Order;
import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame; // for main window
import javax.swing.JOptionPane; // for standard dialogs
// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar; // row of menu selections
import javax.swing.JMenu; // menu selection that offers another menu
import javax.swing.JMenuItem; // menu selection that does something
import javax.swing.JToolBar; // row of buttons under the menu
import javax.swing.JButton; // regular button
import javax.swing.JToggleButton; // 2-state button
import javax.swing.BorderFactory; // manufacturers Border objects around buttons
import javax.swing.Box; // to create toolbar spacer
import javax.swing.UIManager; // to access default icons
import javax.swing.JLabel; // text or image holder
import javax.swing.JTextField; // text input box
import javax.swing.ImageIcon; // holds a custom icon
import javax.swing.JComboBox; // for selecting from lists
import javax.swing.SwingConstants; // useful values for Swing method calls
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.imageio.ImageIO; // loads an image from a file

import javax.swing.JFileChooser; // File selection dialog
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File; // opens a file
import java.io.FileReader; // opens a file for reading
import java.io.BufferedReader; // adds buffering to a FileReader
import java.io.FileWriter; // opens a file for writing
import java.io.BufferedWriter; // adds buffering to a FileWriter
import java.io.IOException; // reports an error reading from a file

import java.awt.BorderLayout; // layout manager for main window
import java.awt.FlowLayout; // layout manager for About dialog

import java.awt.Color; // the color of widgets, text, or borders
import java.awt.Font; // rich text in a JLabel or similar widget
import java.awt.image.BufferedImage; // holds an image loaded from a file
import java.util.ArrayList;
import java.util.Arrays; // for setAll

public class MainWin extends JFrame {
    private final String NAME = "ELSA";
    private final String EXTENSION = "elsa";
    private final String VERSION = "0.4";
    private final String FILE_VERSION = "1.1";
    private final String MAGIC_COOKIE = "⮚Ě1şà⮘";
    private final String DEFAULT_STORE_NAME = "New " + NAME + " Store";

    public enum Record {
        CUSTOMER, OPTION, COMPUTER, ORDER
    };

    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);

        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem anew = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As");
        JMenuItem quit = new JMenuItem("Quit");

        JMenu insert = new JMenu("Insert");
        JMenuItem iCustomer = new JMenuItem("Customer");
        JMenuItem iOption = new JMenuItem("Option");
        JMenuItem iComputer = new JMenuItem("Computer");
        JMenuItem iOrder = new JMenuItem("Order");

        JMenu view = new JMenu("View");
        JMenuItem vCustomer = new JMenuItem("Customers");
        JMenuItem vOption = new JMenuItem("Options");
        JMenuItem vComputer = new JMenuItem("Computers");
        JMenuItem vOrder = new JMenuItem("Orders");
        JMenuItem vDetail = new JMenuItem("Details");

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");

        anew.addActionListener(event -> onNewClick());
        open.addActionListener(event -> onOpenClick());
        save.addActionListener(event -> onSaveClick());
        saveAs.addActionListener(event -> onSaveAsClick());
        quit.addActionListener(event -> onQuitClick());

        iCustomer.addActionListener(event -> onInsertCustomerClick());
        iOption.addActionListener(event -> onInsertOptionClick());
        iComputer.addActionListener(event -> onInsertComputerClick());
        iOrder.addActionListener(event -> onInsertOrderClick());

        vCustomer.addActionListener(event -> onViewClick(Record.CUSTOMER));
        vOption.addActionListener(event -> onViewClick(Record.OPTION));
        vComputer.addActionListener(event -> onViewClick(Record.COMPUTER));
        vOrder.addActionListener(event -> onViewClick(Record.ORDER));
        vDetail.addActionListener(event -> onViewDetailsClick());

        about.addActionListener(event -> onAboutClick());

        file.add(anew);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        insert.add(iCustomer);
        insert.add(iOption);
        insert.add(iComputer);
        insert.add(iOrder);
        view.add(vCustomer);
        view.add(vOption);
        view.add(vComputer);
        view.add(vOrder);
        view.add(vDetail);
        help.add(about);

        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);
        setJMenuBar(menubar);

        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Nim Controls");

        // Add a New ELSA store icon
        JButton anewButton = new JButton(new ImageIcon("gui/resources/new_icon.png"));
        anewButton.setActionCommand("Create a new ELSA file");
        anewButton.setToolTipText("Create a new ELSA file");
        anewButton.addActionListener(event -> onNewClick());
        toolbar.add(anewButton);

        JButton openButton = new JButton(new ImageIcon("gui/resources/open_icon.png"));
        openButton.setActionCommand("Load data from selected file");
        openButton.setToolTipText("Load data from selected file");
        openButton.addActionListener(event -> onOpenClick());
        toolbar.add(openButton);

        saveButton = new JButton(new ImageIcon("gui/resources/save_icon.png"));
        saveButton.setActionCommand("Save data to default file");
        saveButton.setToolTipText("Save data to default file");
        saveButton.addActionListener(event -> onSaveClick());
        toolbar.add(saveButton);

        saveAsButton = new JButton(new ImageIcon("gui/resources/saveas_icon.png"));
        saveAsButton.setActionCommand("Save data to selected file");
        saveAsButton.setToolTipText("Save data to selected file");
        saveAsButton.addActionListener(event -> onSaveAsClick());
        toolbar.add(saveAsButton);

        toolbar.add(Box.createHorizontalStrut(25));

        // Create the 3 buttons using the icons provided
        JButton bAddCustomer = new JButton(new ImageIcon("gui/resources/add_customer.png"));
        bAddCustomer.setActionCommand("Insert Customer");
        bAddCustomer.setToolTipText("Insert Customer");
        toolbar.add(bAddCustomer);
        bAddCustomer.addActionListener(event -> onInsertCustomerClick());

        JButton bAddOption = new JButton(new ImageIcon("gui/resources/add_option.png"));
        bAddOption.setActionCommand("Insert Option");
        bAddOption.setToolTipText("Insert Option");
        toolbar.add(bAddOption);
        bAddOption.addActionListener(event -> onInsertOptionClick());

        JButton bAddComputer = new JButton(new ImageIcon("gui/resources/add_computer.png"));
        bAddComputer.setActionCommand("Insert Computer");
        bAddComputer.setToolTipText("Insert Computer");
        toolbar.add(bAddComputer);
        bAddComputer.addActionListener(event -> onInsertComputerClick());

        JButton bAddOrder = new JButton(new ImageIcon("gui/resources/insert_order.png"));
        bAddOrder.setActionCommand("Insert Order");
        bAddOrder.setToolTipText("Insert Order");
        toolbar.add(bAddOrder);
        bAddOrder.addActionListener(event -> onInsertOrderClick());
        toolbar.add(Box.createHorizontalStrut(25));

        // Create the 3 buttons using the icons provided
        JButton bViewCustomers = new JButton(new ImageIcon("gui/resources/view_customers.png"));
        bViewCustomers.setActionCommand("View Customer");
        bViewCustomers.setToolTipText("View Customers");
        toolbar.add(bViewCustomers);
        bViewCustomers.addActionListener(event -> onViewClick(Record.CUSTOMER));

        JButton bViewOptions = new JButton(new ImageIcon("gui/resources/view_options.png"));
        bViewOptions.setActionCommand("View Options");
        bViewOptions.setToolTipText("View Options");
        toolbar.add(bViewOptions);
        bViewOptions.addActionListener(event -> onViewClick(Record.OPTION));

        JButton bViewComputers = new JButton(new ImageIcon("gui/resources/view_computers.png"));
        bViewComputers.setActionCommand("View Computers");
        bViewComputers.setToolTipText("View Computers");
        toolbar.add(bViewComputers);
        bViewComputers.addActionListener(event -> onViewClick(Record.COMPUTER));

        JButton bViewOrders = new JButton(new ImageIcon("gui/resources/view_orders.png"));
        bViewOrders.setActionCommand("View Orders");
        bViewOrders.setToolTipText("View Orders");
        toolbar.add(bViewOrders);
        bViewOrders.addActionListener(event -> onViewClick(Record.ORDER));

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        // Provide a label to show data requested by the user
        display = new JLabel();
        display.setFont(new Font("SansSerif", Font.BOLD, 14));
        display.setVerticalAlignment(JLabel.TOP);

        // scroll pane and add the display component to it
        JScrollPane scrollPane = new JScrollPane(display);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // status
        statusBar = new JLabel("Ready");
        getContentPane().add(statusBar, BorderLayout.PAGE_END);

        // add scroll pane to the center
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Make everything in the JFrame visible
        setVisible(true);

        // Start a new store
        onNewClick(DEFAULT_STORE_NAME);
    }

    private void setStatus(String text) {
        statusBar.setText(text);
    }

    // Listeners

    // File I/O Methods

    protected void onNewClick() {
        onNewClick("");
    }

    protected void onNewClick(String name) {
        setStatus("");
        if (name.isEmpty()) {
            name = JOptionPane.showInputDialog(this, "Store Name", DEFAULT_STORE_NAME);
            if (name.isEmpty())
                name = DEFAULT_STORE_NAME;
        }
        store = new Store(name);
        onViewClick(Record.CUSTOMER);
        setDirty(false);
    }

    protected void onOpenClick() {
        setStatus("");
        final JFileChooser fc = new JFileChooser(filename); // Create a file chooser dialog
        FileFilter elsaFiles = new FileNameExtensionFilter("ELSA files", EXTENSION);
        fc.addChoosableFileFilter(elsaFiles); // Add "ELSA file" filter
        fc.setFileFilter(elsaFiles); // Show ELSA files only by default
        setStatus("Opening...");
        int result = fc.showOpenDialog(this); // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile(); // Obtain the selected File object

            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String magicCookie = br.readLine();
                if (!magicCookie.equals(MAGIC_COOKIE))
                    throw new RuntimeException("Not an ELSA file");
                String fileVersion = br.readLine();
                if (!fileVersion.equals(FILE_VERSION))
                    throw new RuntimeException("Incompatible ELSA file format");

                store = new Store(br); // Open a new store
                onViewClick(Record.CUSTOMER); // Update the user interface
                setDirty(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Unable to load " + filename + '\n' + e,
                        "Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void onSaveClick() {
        setStatus("");
        setStatus("Saving...");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');
            bw.write(FILE_VERSION + '\n');
            store.save(bw);
            setDirty(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to write " + filename + '\n' + e,
                    "Failed", JOptionPane.ERROR_MESSAGE);
        }
        setDirty(false);

        setStatus("Saved!");

    }

    protected void onSaveAsClick() {
        setStatus("");
        setStatus("Saving...");
        final JFileChooser fc = new JFileChooser(filename); // Create a file chooser dialog
        FileFilter elsaFiles = new FileNameExtensionFilter("ELSA files", EXTENSION);
        fc.addChoosableFileFilter(elsaFiles); // Add "ELSA file" filter
        fc.setFileFilter(elsaFiles); // Show ELSA files only by default

        int result = fc.showSaveDialog(this); // Run dialog, return button clicked
        if (result == JFileChooser.APPROVE_OPTION) { // Also CANCEL_OPTION and ERROR_OPTION
            filename = fc.getSelectedFile(); // Obtain the selected File object
            if (!filename.getAbsolutePath().endsWith("." + EXTENSION)) // Ensure it ends with ".elsa"
                filename = new File(filename.getAbsolutePath() + "." + EXTENSION);
            onSaveClick(); // Delegate to Save method
        }

        setStatus("Saved" + filename.getName());
    }

    protected void onQuitClick() {
        setStatus("Quitting..");
        System.exit(0);
    } // Exit the program

    /////////////// UNIFIED DIALOG //////////

    protected String[] UnifiedDialog(String[] fields, String title, String iconFilename, boolean includeImageChooser) {
        // Returning null indicates Cancel or X was clicked
        String[] result = null;

        // Load the icon if available
        ImageIcon icon = null;
        try {
            if (iconFilename != null)
                icon = new ImageIcon(iconFilename);
        } catch (Exception e) {
        }

        // Widgets will include a label and a JTextField or a JButton for each field
        Object[] widgets = new Object[2 * fields.length];

        // Create the widget pairs
        for (int i = 0; i < fields.length; ++i) {
            if (fields[i].equals("Image Filename") && includeImageChooser) {
                // Add a JFileChooser button for selecting an image file
                JButton fileChooserButton = new JButton("Select Image File");
                final int finalI = i; // Define final variable to use in ActionListener
                fileChooserButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Select Image File");
                        int result = fileChooser.showOpenDialog(null);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String selectedFilePath = selectedFile.getAbsolutePath();
                            widgets[2 * finalI + 1] = selectedFilePath;
                        }
                    }
                });
                widgets[2 * i] = new JLabel("<html><br>" + fields[i] + "</html>");
                widgets[2 * i + 1] = fileChooserButton;
            } else {
                // Add a regular JTextField for all other fields
                widgets[2 * i] = new JLabel("<html><br>" + fields[i] + "</html>");
                widgets[2 * i + 1] = new JTextField();
            }
        }

        // Show the dialog
        int button = JOptionPane.showConfirmDialog(null, widgets, title,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

        // If the OK button was pressed, extract result from widgets
        if (button == JOptionPane.OK_OPTION) {
            result = new String[fields.length];
            for (int i = 0; i < fields.length; ++i) {
                if (fields[i].equals("Image Filename") && includeImageChooser) {
                    result[i] = (String) widgets[2 * i + 1];
                } else {
                    result[i] = ((JTextField) widgets[2 * i + 1]).getText();
                }
            }
        }
        return result;
    }

    protected void onInsertCustomerClick() {
        setStatus("");
        setStatus("Inserting Customer...");
        try {
            String[] result = UnifiedDialog(new String[] { "Name", "Email", "Image Filename" },
                    "New Customer", "gui/resources/add_customer.png", true);

            if (result != null) {
                store.add(new Customer(result[0], result[1], result[2]));
                setDirty(true);
                onViewClick(Record.CUSTOMER);
                setStatus("Customer Inserted!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Customer Not Created", JOptionPane.ERROR_MESSAGE);
            setStatus("Error");
        }
    }

    protected void onInsertOptionClick() {
        setStatus("");
        setStatus("Inserting Option...");
        try {
            String[] result = UnifiedDialog(new String[] { "Name", "Cost", "Image Filename" },
                    "New Option", "gui/resources/add_option.png", true);

            if (result != null) {

                store.add(new Option(result[0], (long) (100.0 * Double.parseDouble(result[1])), result[2]));
                setDirty(true);
                onViewClick(Record.OPTION);
                setStatus("Option Inserted!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Option Not Created", JOptionPane.ERROR_MESSAGE);
            setStatus("Error");
        }
    }

    protected void onInsertComputerClick() {
        // Load the icon if available
        setStatus("");
        setStatus("Inserting Computer..");
        ImageIcon icon = null;
        try {
            icon = new ImageIcon("gui/resources/add_computer.png");
        } catch (Exception e) {
        }
        try {
            String[] result = UnifiedDialog(new String[] { "Computer Name", "Computer Model" },
                    "New Computer", "gui/resources/add_computer.png", true);
            if (result == null)
                return;

            Computer c = new Computer(result[0], result[1], result[2]);

            JComboBox<Object> cb = new JComboBox<>(store.options());
            int optionsAdded = 0; // Don't add computers with no options
            while (true) {
                int button = JOptionPane.showConfirmDialog(this, cb, "Another Option?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                if (button != JOptionPane.YES_OPTION)
                    break;
                c.addOption((Option) cb.getSelectedItem());
                ++optionsAdded;
            }
            if (optionsAdded > 0) {
                store.add(c);
                onViewClick(Record.COMPUTER);
                setDirty(true);
                setStatus("Computer Added!");
            }
        } catch (NullPointerException e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Computer Not Created", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onInsertOrderClick() {
        setStatus("");
        // Load the image if available
        setStatus("Inserting Order...");
        ImageIcon icon = null;
        try {
            icon = new ImageIcon("gui/resources/add_order.png");
        } catch (Exception e) {
        }

        try {
            // Select (or create) a Customer
            Object[] customers = store.customers();
            if (customers.length == 0) {
                onInsertCustomerClick();
                customers = store.customers();
                if (customers.length == 0)
                    return;
            }
            Customer customer = (Customer) customers[0];
            if (customers.length > 1) {
                JLabel label = new JLabel("Order for which Customer?");
                JComboBox<Object> cb = new JComboBox<>(customers);
                int button = JOptionPane.showConfirmDialog(this, new Object[] { label, cb }, "New Order",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, icon);
                if (button != JOptionPane.OK_OPTION)
                    return;
                customer = (Customer) cb.getSelectedItem();
            }

            // Create the order (no computers yet)
            Order o = new Order(customer);
            JComboBox<Object> cb = new JComboBox<>(store.computers());
            int computersAdded = 0; // Don't add orders with no computers
            while (true) {
                // Show the order thus far as part of the dialog
                JLabel label = new JLabel("<html><p>" + o.toString().replaceAll("\n", "<br/>") + "</p></html>");
                int button = JOptionPane.showConfirmDialog(this, new Object[] { label, cb }, "Another Computer?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, icon);

                // Exit loop when No (or X) is clicked
                if (button != JOptionPane.YES_OPTION)
                    break;

                // Add the selected Option
                o.addComputer((Computer) cb.getSelectedItem());
                ++computersAdded;
            }

            // Create the new computer
            if (computersAdded > 0) {
                store.add(o);
                onViewClick(Record.ORDER);
                setDirty(true);
            }
        } catch (NullPointerException e) {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Order Not Created", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onViewClick(Record record) {
        setStatus("");
        setStatus("Viewing...");
        String header = null;
        Object[] list = null;

        if (record == Record.CUSTOMER) {
            header = "Our Beloved Customers";
            list = store.customers();
        }
        if (record == Record.OPTION) {
            header = "Options for our SuperComputers";
            list = store.options();
        }
        if (record == Record.COMPUTER) {
            header = "Computers for Sale - Cheap!";
            list = store.computers();
        }
        if (record == Record.ORDER) {
            header = "Orders Placed to Date";
            list = store.orders();
        }

        StringBuilder sb = new StringBuilder("<html><p><font size=+2>" + header + "</font></p><br/>\n<ol>\n");
        for (Object i : list)
            sb.append("<li>" + i.toString().replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\n", "<br/>") + "</li>\n");
        sb.append("</ol></html>");
        display.setText(sb.toString());
    }

    // VIEW > DETAIL
    private void onViewDetailsClick() {
        // Create a dialog to select the data class
        setStatus("");
        JComboBox<String> dataClassComboBox = new JComboBox<String>(new String[] { "Customer", "Option", "Computer" });
        int result = JOptionPane.showConfirmDialog(this, dataClassComboBox, "Select Data Class",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String selectedDataClass = (String) dataClassComboBox.getSelectedItem();
            // Get the records for the selected data class
            List<String> records = new ArrayList<String>();
            Object[] data = null;
            if (selectedDataClass.equals("Customer")) {
                data = store.customers();
            } else if (selectedDataClass.equals("Option")) {
                data = store.options();
            } else if (selectedDataClass.equals("Computer")) {
                data = store.computers();
            }
            for (Object obj : data) {
                records.add(obj.toString());
            }
            // Create a dialog to select the record
            JComboBox<String> recordComboBox = new JComboBox<String>(records.toArray(new String[0]));
            result = JOptionPane.showConfirmDialog(this, recordComboBox, "Select Record", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                String selectedRecord = (String) recordComboBox.getSelectedItem();
                // Find the selected record in the data list
                Object selectedData = null;
                for (Object obj : data) {
                    if (obj.toString().equals(selectedRecord)) {
                        selectedData = obj;
                        break;
                    }
                }
                // Get the image file path from the selected data object
                String imagePath = null;
                if (selectedData instanceof Customer) {
                    imagePath = ((Customer) selectedData).getImageFilename();
                } else if (selectedData instanceof Option) {
                    imagePath = ((Option) selectedData).getImageFilename();
                } else if (selectedData instanceof Computer) {
                    imagePath = ((Computer) selectedData).getImageFilename();
                }
                // Create the JLabel with the image and text
                if (imagePath != null) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    display.setIcon(icon);
                } else {
                    display.setIcon(null);
                }
                // Set the JLabel in the main window
                setStatus("Detail added");
            }
        }
    }

    protected void onAboutClick() { // Display About dialog
        setStatus("");
        Canvas logo = new Canvas("gui/resources/logo320.png");

        JLabel title = new JLabel("<html>"
                + "<p><font size=+4>" + NAME + "</font></p>"
                + "</html>",
                SwingConstants.CENTER);

        JLabel subtitle = new JLabel("<html>"
                + "<p>Exceptional Laptops and Supercomputers Always</p>"
                + "</html>",
                SwingConstants.CENTER);

        JLabel version = new JLabel("<html>"
                + "<p>Version " + VERSION + "</p>"
                + "</html>",
                SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
                + "<br/><p>Copyright 2017-2023 by George F. Rice</p>"
                + "<p>Licensed under Gnu GPL 3.0</p><br/>"

                + "<br/><p>Bonus Parts by Rachael Rocha :)</p>"

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
                new Object[] { logo, title, subtitle, version, artists },
                "ELSA",
                JOptionPane.PLAIN_MESSAGE);
    }

    private void setDirty(boolean isDirty) {
        save.setEnabled(isDirty);
        // saveAs.setEnabled(isDirty);
        saveButton.setEnabled(isDirty);
        // saveAsButton.setEnabled(isDirty);
    };

    private Store store; // The current Elsa store
    private JLabel display; // Display page of data
    private JLabel statusBar;

    private File filename;

    private JMenuItem save;
    private JMenuItem saveAs;
    private JButton saveButton;
    private JButton saveAsButton;

}
