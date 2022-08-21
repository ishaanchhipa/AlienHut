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
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class LoginController implements Initializable {
    private Parent root;
    private Scene scene;
    String user,s1;
    String pass,s2;
    Stage stage;
    @FXML
    private TextField username_tf;
    @FXML
    private PasswordField password_tf;
    @FXML
    private Button login_button;
    @FXML
    private Button signup_button;
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
    private void login(ActionEvent event) throws IOException {
        Connection con;
        String u=username_tf.getText();
        this.user=u;
        String p=password_tf.getText();
        this.pass=p;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from user where username="+"'"+u+"'"+";");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                this.s1=rs.getString(1);
                this.s2=rs.getString(4);
            }    
        }catch(Exception e){System.out.println(e);}
        
        if(user.equals(s1) && pass.equals(s2))
            {
                
                FXMLLoader loader=new FXMLLoader(getClass().getResource("customer_view.fxml"));
                Parent root=loader.load();
                Customer_viewController data=loader.getController();
                System.out.println("User "+s1+" Logged in...");
                data.getuser(s1);
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        else if(user.equals("admin123") && pass.equals("#Admin123")){
                FXMLLoader loader=new FXMLLoader(getClass().getResource("admin_view.fxml"));
                Parent root=loader.load();
                System.out.println("Admin Logged in...");
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();   
        }
        else
        {
            username_tf.setText("");
            password_tf.setText("");
            error_lb.setTextFill(Color.color(1, 0, 0));
            error_lb.setText("Invalid Username And Password!!!");
            u="";
            p="";
        }
    }

    @FXML
    private void signup(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
