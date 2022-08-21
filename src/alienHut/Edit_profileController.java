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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static alienHut.SignupController.isValidPassword;
import static alienHut.SignupController.isValidUsername;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class Edit_profileController implements Initializable {
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
    
    private void showAlert(String s) {
        if (s.equals("username"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Username");
            alert.setContentText("Try another!");
            alert.showAndWait();
        }
        if (s.equals("password"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Password");
            alert.setContentText("Try another!");
            alert.showAndWait();
        }
        if (s.equals("success"))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Updated");
            alert.setHeaderText("Profile Updated Successfully");
            alert.setContentText("Enjoy!");
            alert.showAndWait();
        }
    }
    
    String user;
    private Parent root;
    private Scene scene;
    private Stage stage;
    String olduser,oldpass,oldfname,oldlname;
    String newuser,newpass,newfname,newlname;
    
    @FXML
    private TextField username_tf;
    @FXML
    private TextField password_tf;
    @FXML
    private TextField firstname_tf;
    @FXML
    private TextField lastname_tf;
    @FXML
    private ImageView profile_pic;

    public void getuser(String u){
        this.user=u;
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from user where username="+"'"+user+"'"+";");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
            this.olduser=rs.getString(1);
            this.oldfname=rs.getString(2);
            this.oldlname=rs.getString(3);
            this.oldpass=rs.getString(4);
            
            this.newuser=rs.getString(1);
            this.newfname=rs.getString(2);
            this.newlname=rs.getString(3);
            this.newpass=rs.getString(4);
            }    
        }catch(Exception e){System.out.println(e);}
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void cancel(ActionEvent event) throws IOException {
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view_profile.fxml"));
        Parent root=loader.load();
        View_profileController data=loader.getController();
        data.getuser(newuser);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void save(ActionEvent event) {
        if (username_tf.getText().equals("")){
            this.newuser=olduser;
        }
        else{
            this.newuser=username_tf.getText();
            if (isValidUsername(newuser)==false)
            {
                username_tf.setText("");
                firstname_tf.setText("");
                lastname_tf.setText("");
                password_tf.setText("");
                showAlert("username");
            }
            
        }
        
        if (password_tf.getText().equals("")){
            this.newpass=oldpass;
        }
        else{
            this.newpass=password_tf.getText();
            if (isValidPassword(newpass)==false)
            {
                username_tf.setText("");
                firstname_tf.setText("");
                lastname_tf.setText("");
                password_tf.setText("");
                showAlert("password");
            }
        }
        
        if (firstname_tf.getText().equals("")){
            this.newfname=oldfname;
        }
        else{
            this.newfname=firstname_tf.getText();
        }
        
        if (lastname_tf.getText().equals("")){
            this.newlname=oldlname;
        }
        else{
            this.newlname=lastname_tf.getText();
        }
        
        Connection con;
        if (isValidPassword(newpass)==true && isValidUsername(newuser)==true){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");    
                Statement s=con.createStatement();
                String s1= "UPDATE user set username = '" + newuser + "'"+",firstname = '"+newfname+"'"+",lastname = '"+newlname+"'"+",password = '"+newpass+"'"+" where username = '" +olduser+"';" ;
                System.out.println("User Successfully Updated And Stored Into Database...");
                s.executeUpdate(s1);
                String s2= "UPDATE booking set user = '" + newuser +"'"+" where user = '" +olduser+"';" ;
                s.executeUpdate(s1);
                s.executeUpdate(s2);
                
                showAlert("success");
                
                FXMLLoader loader=new FXMLLoader(getClass().getResource("view_profile.fxml"));
                Parent root=loader.load();
                View_profileController data=loader.getController();
                data.getuser(newuser);
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch(Exception e){System.out.println(e);}
        }
    }
}
