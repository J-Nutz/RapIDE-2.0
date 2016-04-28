package frames;

/*
 * Created by Jonah on 2/7/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import static frames.MainFrame.showPanel;

public class LoginPanel extends JPanel
{
    private final JLabel loginStatusLbl = new JLabel("Please Enter Username And Password");
    private final JTextField usernameTF = new JTextField("Username");
    private final JPasswordField passwordPF = new JPasswordField("Password");
    private final JButton loginBtn = new JButton();
    private final JButton createActBtn = new JButton("Create Account");

    public LoginPanel()
    {
        initComponents();
        addComponents();
    }

    private void initComponents()
    {
        //This
        setBackground(Color.gray);

        //Login Label
        loginStatusLbl.setFont(new Font(null, Font.PLAIN, 16));
        loginStatusLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        //UsernameTF
        usernameTF.setMaximumSize(new Dimension(100, 40));
        usernameTF.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameTF.setBorder(null);

        usernameTF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                usernameTF.setText("");
            }
        });

        //PasswordPF
        passwordPF.setMaximumSize(new Dimension(100, 40));
        passwordPF.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPF.setBorder(null);

        passwordPF.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                passwordPF.setText("");
            }
        });

        //LoginBtns
        loginBtn.setMaximumSize(new Dimension(110, 40));
        loginBtn.setBackground(Color.black);
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setIcon(new ImageIcon(getClass().getResource("/loginBtn.png")));
        loginBtn.addActionListener(e ->
        {
            char[] pass = {'1', '2', '3', '4'};
            boolean goodCredentials = usernameTF.getText().equalsIgnoreCase("Jonah") && Arrays.equals(passwordPF.getPassword(), pass);
            if(goodCredentials)
            {
                loginStatusLbl.setForeground(Color.black);
                loginStatusLbl.setText("Please Enter Username And Password");

                usernameTF.setText("Username");
                passwordPF.setText("password");

                showPanel("Main");
            }
            else
            {
                loginStatusLbl.setForeground(Color.red);
                loginStatusLbl.setText("Invalid Username Or Password");
            }
        });

        //Create Account Button
        createActBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        createActBtn.addActionListener(e -> showPanel("SignUp"));
    }

    private void addComponents()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(Box.createVerticalGlue());
        add(loginStatusLbl);
        add(Box.createVerticalStrut(15));
        add(usernameTF);
        add(Box.createVerticalStrut(5));
        add(passwordPF);
        add(Box.createVerticalStrut(15));
        add(loginBtn);
        add(Box.createVerticalStrut(5));
        add(createActBtn);
        add(Box.createVerticalGlue());
    }
}