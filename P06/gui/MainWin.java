package gui;

import store.Computer;
import store.Customer;
import store.Store;
import store.Option;

import javax.swing.JFrame; // for main window
import javax.swing.JOptionPane; // for standard dialogs
import javax.swing.JTextField;
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

import java.io.File; // opens a file
import java.io.IOException; // reports an error reading from a file
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
        setSize(400, 200);

        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem quit = new JMenuItem("Quit");

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

        quit.addActionListener(event -> onQuitClick());

        Store store = new Store("Store");
        // For Insert
        customer.addActionListener(event -> onInsertCustomerClick(store));
        option.addActionListener(event -> onInsertOptionClick(store));
        computer.addActionListener(event -> onInsertComputerClick(store));
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
        // JToolBar toolbar = new JToolBar("Nim Controls");

        // // Add a New Game stock icon
        // JButton anewB = new JButton(UIManager.getIcon("FileView.fileIcon"));
        // anewB.setActionCommand("New Game");
        // anewB.setToolTipText("Create a new game, discarding any in progress");
        // anewB.setBorder(null);
        // toolbar.add(anewB);
        // anewB.addActionListener(event -> onNewGameClick());

        // // A "horizontal strut" is just a space of the specified pixel width
        // toolbar.add(Box.createHorizontalStrut(25));

        // // Create the 3 buttons using the icons provided
        // ImageIcon ii = new ImageIcon("button1.png");
        // button1 = new JButton(new ImageIcon("button1.png"));
        // button1.setActionCommand("Select one stick");
        // button1.setToolTipText("Select one stick");
        // toolbar.add(button1);
        // button1.addActionListener(event -> onButtonClick(1));

        // button2 = new JButton(new ImageIcon("button2.png"));
        // button2.setActionCommand("Select two sticks");
        // button2.setToolTipText("Select two sticks");
        // toolbar.add(button2);
        // button2.addActionListener(event -> onButtonClick(2));

        // button3 = new JButton(new ImageIcon("button3.png"));
        // button3.setActionCommand("Select three sticks");
        // button3.setToolTipText("Select three sticks");
        // toolbar.add(button3);
        // button3.addActionListener(event -> onButtonClick(3));

        // toolbar.add(Box.createHorizontalStrut(25));

        // // Create a toggle button to enable or disable the computer player
        // computerPlayer = new JToggleButton(new ImageIcon("freepik_robot.png"));
        // computerPlayer.setActionCommand("Enable computer player");
        // computerPlayer.setToolTipText("Enable computer to be Player 2");
        // computerPlayer.setBorder(null);
        // toolbar.add(computerPlayer);
        // computerPlayer.addActionListener(event -> onComputerPlayerClick());

        // // "Horizontal glue" expands as much as possible, pushing the "X" to the
        // right
        // toolbar.add(Box.createHorizontalGlue());

        // // Create a custom Quit button (not available in Swing stock icons)
        // JButton quitB = new JButton("X");
        // quitB.setActionCommand("Quit");
        // quitB.setToolTipText("Exit game");
        // quitB.setBorder(null);
        // toolbar.add(quitB);
        // quitB.addActionListener(event -> onQuitClick());
        // toolbar.addSeparator();

        // getContentPane().add(toolbar, BorderLayout.PAGE_START);

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

    protected void onInsertComputerClick(Store store) {
        JFrame frame = new JFrame("New Computer");
        JTextField computerName = new JTextField(40);
        JTextField computerModel = new JTextField(40);

        // OBJECT///
        Object[] optionChoices = store.getOptions(); // List all options
        String[] options = Arrays.stream(optionChoices).map(Object::toString).toArray(String[]::new); // get the text

        JComboBox<String> comboBox = new JComboBox<>(options);

        Object[] labels = { "Computer Name", computerName, "Computer Model", computerModel, "Options", comboBox };

        int dialogBox = JOptionPane.showConfirmDialog(frame, labels, "New Computer", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (dialogBox == JOptionPane.OK_OPTION) {
            try {
                Computer newComputer = new Computer(computerName.getText(), computerModel.getText());

                while (true) {
                    int optionDialog = JOptionPane.showConfirmDialog(frame, labels, "Select Options",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (optionDialog == JOptionPane.OK_OPTION) {
                        Option selectedObject = (Option) comboBox.getSelectedItem();
                        newComputer.addOption(selectedObject);
                    } else {
                        break;
                    }
                }

                store.add(newComputer);
                JOptionPane.showMessageDialog(null, "Computer Added!", "Confirmation", JOptionPane.PLAIN_MESSAGE);

            } catch (IllegalArgumentException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Invalid Input. Please Try Again", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Handle other exceptions
            }
        } else {
            return;
        }
    }

    protected void onViewClick(Record record, Store store) {

        // Define header
        String header = "";
        Object[] objects;

        // CUSTOMER, OPTION, COMPUTER, ORDER

        // Switch? based on parameter
        switch (record) {
            case CUSTOMER: {
                header = "<html><h2>Customers</h2></html>";
                objects = store.getCustomers();
                break;
            }
            case OPTION: {
                header = "<html><h2>Options</h2></html>";
                objects = store.getOptions();
                break;
            }
            case COMPUTER: {
                header = "<html><h2>Computers</h2></html>";
                objects = store.getComputers();
                break;
            }
            case ORDER: {
                header = "<html><h2>Orders</h2></html>";
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
        JOptionPane.showMessageDialog(null, viewClickLabel);

    }

    protected void onAboutClick() { // Display About dialog
        JLabel logo = null;
        try {
            BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
            logo = new JLabel(new ImageIcon(myPicture));
        } catch (IOException e) {
        }

        JLabel title = new JLabel("<html>" + "<p><font size=+4>Nim</font></p>" + "<p>Version 1.4J</p>" + "</html>",
                SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>" + "<br/><p>Copyright 2017-2023 by George F. Rice</p>"
                + "<p>Licensed under Gnu GPL 3.0</p><br/>" + "<p>Logo by M0tty, licensed under CC BY-SA 3.0</p>"
                + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Pyramidal_matches.svg</font></p>"
                + "<p>Robot by FreePik.com, licensed for personal</p><p>and commercial purposes with attribution</p>"
                + "<p><font size=-2>https://www.freepik.com/free-vector/grey-robot-silhouettes_714902.htm</font></p>"
                + "</html>");

        JOptionPane.showMessageDialog(this, new Object[] { logo, title, artists }, "The Game of Nim",
                JOptionPane.PLAIN_MESSAGE);
    }

    protected void onQuitClick() {
        System.exit(0);
    } // Exit the game

    private JLabel sticks; // Display of sticks on game board
    private JLabel msg; // Status message display
    private JButton button1; // Button to select 1 stick
    private JButton button2; // Button to select 2 sticks
    private JButton button3; // Button to select 3 sticks
    private JToggleButton computerPlayer; // Button to enable robot

}
