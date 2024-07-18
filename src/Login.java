import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JButton validarAccesoButton;
    private JTextField user;
    private JTextField pass;
    private JPanel panel1;
    public Login() throws SQLException {
        setTitle("Login");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(panel1);


        validarAccesoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Validar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void Validar() throws SQLException {
        //1 Creo el objeto para conexion
        Connection conecta=conexion();

        //2 guardo lo que se ingrese en los JtextField
        String usuario=user.getText();
        String contraseña=pass.getText();

        //3 creo la consulta y lo paso a un objeto PreparedStatement
        String sql="select * from usuario where usuario=? and password=?";
        PreparedStatement pstmt = conecta.prepareStatement(sql);
        //guardo las ? afectando al pstmt
        pstmt.setString(1,usuario);
        pstmt.setString(2,contraseña);
        //Creo un objeto ResultSet , afectando al pstmt con un execute Querry
        ResultSet rs=pstmt.executeQuery();

        //Recorro  el ResultSet
        if (rs.next()){
            JOptionPane.showMessageDialog(null,"Acceso correcto");
            Registrar r1=new Registrar();
            r1.Iniciar();
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(null,"Credenciales incorrectas");
            user.setText("");
            pass.setText("");
        }

        //Cierro todo
        conecta.close();
        pstmt.close();
        rs.close();
    }

    //1 Creo la conexion
    public Connection conexion() throws SQLException {
        String url= "jdbc:mysql://ucgorwk4bgmw0hwo:S0DUGAQzQgDvE1XjtpIW@bzudd6l1argld6vr3nhg-mysql.services.clever-cloud.com:3306/bzudd6l1argld6vr3nhg";
        String user="ucgorwk4bgmw0hwo";
        String password="S0DUGAQzQgDvE1XjtpIW";
        return DriverManager.getConnection(url,user,password);
    }
    public void Iniciar(){
        setVisible(true);
        setTitle("Actualizar registro");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
