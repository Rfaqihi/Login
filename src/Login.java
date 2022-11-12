import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Login {
    private JTextField username;
    private JTextField password;
    private JButton saveButton;
    private JPanel Main;
    private JLabel name;
    private JLabel pwd;
    private JLabel btsave;
    private JButton updateButton;
    private JButton deleteButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    Connection con;
    PreparedStatement pst;
    public void connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javalogin", "root","");
            System.out.println("Successes");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public Login() {
        connect();
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name, pwd;
                name = username.getText();
                pwd = password.getText();

                try {
                    pst = con.prepareStatement("insert into users(name, pwd) values (?,?)");
                    pst.setString(1, name);
                    pst.setString(2, pwd);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Save Successfully");


                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, pwd;
                name = username.getText();
                pwd = password.getText();

                try {
                    pst = con.prepareStatement("update users set pwd = ? where name = ? ");
                    pst.setString(1, pwd);
                    pst.setString(2, name);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Password Updated");

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, pwd;
                name = username.getText();
                try {
                    pst = con.prepareStatement("delete from users where name = ?");
                    pst.setString(1, name);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Name Deleted");
                } catch (SQLException e1) {

                    e1.printStackTrace();
                }


            }
            });



    }




}
