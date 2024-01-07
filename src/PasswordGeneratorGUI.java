import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


// GUI rendering Section

public class PasswordGeneratorGUI extends JFrame {

    private PasswordGenerator passwordGenerator;
    private PasswordGeneratorController controller;

    public PasswordGeneratorGUI()
    {
        // frame render and add a title
        super("Password Generator");

        // set the title of the GUI
        setSize(540, 570);

        // prevent GUI from being able to resized
        setResizable(false);

        // we will set the layout to be null to have control over the position and size of our components in app
        setLayout(null);

        // terminate the program when the GUI is closed 
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // center the GUI to the screen
        setLocationRelativeTo(null);

        //init password generator
        passwordGenerator = new PasswordGenerator();

        //init controller
        controller = new PasswordGeneratorController();

        // render GUI components
        addGuiComponents();
        

    }

    private void addGuiComponents(){
        // create title text
        JLabel titleLabel = new JLabel("Password Generator");

        // increase the font size and make it bold
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));

        // center the text to the screen
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // set x,y cordinates and width/height values
        titleLabel.setBounds(0, 30, 540, 39);

        // add to GUI
        add(titleLabel);

        // create result text area
        JTextArea passwordOutput = new JTextArea();

        // prevent editing the text area
        passwordOutput.setEditable(false);
        passwordOutput.setFont(new Font("Dialog", Font.BOLD, 32));

        // add scrollability in case output becomes too big
        JScrollPane passwordOutputPane = new JScrollPane(passwordOutput);
        passwordOutputPane.setBounds(25, 97, 479, 70);

        // create a black border around the text area
        passwordOutputPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(passwordOutputPane);

        // create password length label
        JLabel passwordLengthLabel = new JLabel("Password Length: ");
        passwordLengthLabel.setFont(new Font("Dialog", Font.PLAIN, 32));
        passwordLengthLabel.setBounds(25, 215, 272, 39);
        add(passwordLengthLabel);

        // create password length input
        JTextArea passwordLengthInputArea = new JTextArea();
        passwordLengthInputArea.setFont(new Font("Dialog", Font.PLAIN, 32));
        passwordLengthInputArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        passwordLengthInputArea.setBounds(285, 220, 220, 35);
        add(passwordLengthInputArea);

        // create toggle buttons 

        // uppercase letter toggle
        JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
        uppercaseToggle.setFont(new Font("Dialog", Font.PLAIN, 24));
        uppercaseToggle.setBounds(25, 285, 225, 56);
        uppercaseToggle.setBackground(Color.gray);
        add(uppercaseToggle);

        // lowercase letter toggle
        JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
        lowercaseToggle.setFont(new Font("Dialog", Font.PLAIN, 24));
        lowercaseToggle.setBounds(276, 285, 225, 56);
        lowercaseToggle.setBackground(Color.gray);
        add(lowercaseToggle);

        // numbers letter toggle
        JToggleButton numberToggle = new JToggleButton("Numbers");
        numberToggle.setFont(new Font("Dialog", Font.PLAIN, 24));
        numberToggle.setBounds(25, 360, 225, 56);
        numberToggle.setBackground(Color.gray);
        add(numberToggle);

        // symbols letter toggle
        JToggleButton symbolToggle = new JToggleButton("Symbols");
        symbolToggle.setFont(new Font("Dialog", Font.PLAIN, 24));
        symbolToggle.setBounds(276, 360, 225, 56);
        symbolToggle.setBackground(Color.gray);
        add(symbolToggle);

        // copy button
        JButton copyButton = new JButton("Copy");
        copyButton.setFont(new Font("Dialog", Font.PLAIN, 18));
        copyButton.setBounds(380, 170, 125, 26);
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                // getting generated password for copy
                String password = passwordOutput.getText();

                // Get Clipboard object
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                //Copy password to clipboard with StringSelection
                StringSelection selection = new StringSelection(password);
                clipboard.setContents(selection, null);

                // feedback that password is copied
                copyButton.setText("Copied");
            }
        });
        add(copyButton); 

        // Password Scrore indicator
        JProgressBar passwordStrengthProgressBar = new JProgressBar(0,100);
        passwordStrengthProgressBar.setValue(0);
        passwordStrengthProgressBar.setStringPainted(true);
        passwordStrengthProgressBar.setBounds(25, 170, 250, 26);
        add(passwordStrengthProgressBar);

        // create generate button
        JButton generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Dialog", Font.PLAIN, 28));
        generateButton.setBounds(151, 435, 225, 56);
        generateButton.setBackground(Color.GREEN);
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                //validation: generate a password only when length > 0 and one of the toggled buttons is pressed
                if(passwordLengthInputArea.getText().length() <= 0 ) return;
                boolean anyToggleSelected = lowercaseToggle.isSelected() || uppercaseToggle.isSelected() || numberToggle.isSelected() || symbolToggle.isSelected();

                //generate password 
                //converts text to an integer value
                int passwordLength = Integer.parseInt(passwordLengthInputArea.getText());
                if(anyToggleSelected)
                {
                    String generatedPassword = passwordGenerator.generatePassword(passwordLength,
                    uppercaseToggle.isSelected(), 
                    lowercaseToggle.isSelected(), 
                    numberToggle.isSelected(), 
                    symbolToggle.isSelected());

                    passwordOutput.setText(generatedPassword);

                    // password strength controller updated
                    int passwordStrength = controller.calculatePasswordStrength(generatedPassword);
                    passwordStrengthProgressBar.setValue(passwordStrength);
                }
            }
        });
        add(generateButton);

    }
}
