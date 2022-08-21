/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alienHut;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class SignupController implements Initializable {
        public static boolean
    isValidPassword(String password)
    {
  
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }
  
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
  
        // Return if the password
        // matched the ReGex
        return m.matches();
    }
    
    public static boolean isValidUsername(String name)
    {
  
        // Regex to check valid username.
        String regex = "^[A-Za-z]\\w{5,29}$";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // If the username is empty
        // return false
        if (name == null) {
            return false;
        }
  
        // Pattern class contains matcher() method
        // to find matching between given username
        // and regular expression.
        Matcher m = p.matcher(name);
  
        // Return if the username
        // matched the ReGex
        return m.matches();
    }
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button signup_button;
    @FXML
    private Button login_button;
    @FXML
    private TextField username_tf;
    @FXML
    private TextField firstname_tf;
    @FXML
    private TextField lastname_tf;
    @FXML
    private PasswordField password_tf;
    @FXML
    private Label error_lb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        error_lb.setTextFill(Color.color(1, 0, 0));
        error_lb.setText("");
    }    

    @FXML
    private void signupbutton(ActionEvent event) {
        Connection con = null;
        error_lb.setTextFill(Color.color(1, 0, 0));
        String u=username_tf.getText();
        String fn=firstname_tf.getText();
        String ln=lastname_tf.getText();
        String p=password_tf.getText();
        
        if (isValidPassword(p)==false)
        {
            username_tf.setText("");
            firstname_tf.setText("");
            lastname_tf.setText("");
            password_tf.setText("");
            error_lb.setText("Invalid password!!!");
        }
        if (isValidUsername(u)==false)
        {
            username_tf.setText("");
            firstname_tf.setText("");
            lastname_tf.setText("");
            password_tf.setText("");
            error_lb.setText("Invalid Username!!!");
        }
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from user where username="+"'"+u+"'"+";");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                String s=rs.getString(1);
                if(u.equals(s))
                {
                    username_tf.setText("");
                    firstname_tf.setText("");
                    lastname_tf.setText("");
                    password_tf.setText("");
                    error_lb.setText("User Already Exists!!!");
                    u="";
                }
            }
        }catch(Exception e){System.out.println(e);}
        
        if (isValidPassword(p)==true && isValidUsername(u)==true)
        {
        try{
                Statement s=con.createStatement();
                String s1= "insert into user(username,firstname,lastname,password) values('"+ u + "','"+ fn + "','"+ ln + "','"+ p + "');";
                System.out.println("User Successfully Created And Stored Into Database...");
                s.executeUpdate(s1);
                username_tf.setText("");
                firstname_tf.setText("");
                lastname_tf.setText("");
                password_tf.setText("");
                error_lb.setTextFill(Color.color(0, 1, 0));
                error_lb.setText("User Created Successfully");
                
                FXMLLoader loader=new FXMLLoader(getClass().getResource("login.fxml"));
                Parent root=loader.load();
                
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch(Exception e){System.out.println(e);}
        }
    }

    @FXML
    private void loginbutton(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
