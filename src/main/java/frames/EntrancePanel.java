package frames;

/*
 * Created by Jonah on 2/7/2016.
 */

import javax.swing.*;

import java.awt.*;

import static frames.MainFrame.showPanel;

public class EntrancePanel extends JPanel
{
    private final JTextField usernameTF = new JTextField("Username");
    private final JPasswordField passwordPF = new JPasswordField("Password");
    private final JButton loginBtn = new JButton("Login");

    public EntrancePanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        usernameTF.setMaximumSize(new Dimension(100, 40));
        usernameTF.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordPF.setMaximumSize(new Dimension(100, 40));
        passwordPF.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginBtn.setPreferredSize(new Dimension(100, 40));
        loginBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginBtn.addActionListener(e ->
        {
            showPanel("Main");
        });

        addComponents();
    }

    private void addComponents()
    {
        add(Box.createVerticalGlue());
        add(usernameTF);
        add(passwordPF);
        add(loginBtn);
        add(Box.createVerticalGlue());
    }

}