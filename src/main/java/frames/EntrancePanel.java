package frames;

/*
 * Created by Jonah on 2/7/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static frames.MainFrame.showPanel;

public class EntrancePanel extends JPanel
{
    private final JTextField usernameTF = new JTextField("Username");
    private final JPasswordField passwordPF = new JPasswordField("Password");
    private final JButton loginBtn = new JButton();
    private final JLabel loginStatusLbl = new JLabel("Please Enter Username And Password");

    public EntrancePanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Login Label
        loginStatusLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        //UsernameTF
        usernameTF.setMaximumSize(new Dimension(100, 40));
        usernameTF.setAlignmentX(Component.CENTER_ALIGNMENT);

        //PasswordPF
        passwordPF.setMaximumSize(new Dimension(100, 40));
        passwordPF.setAlignmentX(Component.CENTER_ALIGNMENT);

        //LoginBtns
        loginBtn.setPreferredSize(new Dimension(100, 40));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setIcon(new ImageIcon(getClass().getResource("/login.png")));
        loginBtn.addActionListener(e ->
        {
            char[] pass = {'1', '2', '3', '4'};
            boolean goodCredentials = usernameTF.getText().equalsIgnoreCase("Jonah") && Arrays.equals(passwordPF.getPassword(), pass);
            if(goodCredentials)
            {
                showPanel("Main");
            }
            else
            {
                loginStatusLbl.setText("Invalid Username Or Password");
            }
        });

        addComponents();
    }

    private void addComponents()
    {
        add(Box.createVerticalGlue());
        add(loginStatusLbl);
        add(Box.createVerticalStrut(15));
        add(usernameTF);
        add(Box.createVerticalStrut(5));
        add(passwordPF);
        add(Box.createVerticalStrut(15));
        add(loginBtn);
        add(Box.createVerticalGlue());
    }

}