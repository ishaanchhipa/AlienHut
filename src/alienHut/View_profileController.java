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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class View_profileController implements Initializable {
    String user;
     private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Button edit_button;
    @FXML
    private ImageView profile_pic;
    @FXML
    private Label username;
    @FXML
    private Label firstname;
    @FXML
    private Label lastname;
    
    public void getuser(String u){
        this.user=u;
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from user where username="+"'"+user+"'"+";");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String s1=rs.getString(1);
                String s2=rs.getString(2);
                String s3=rs.getString(3);
                username.setText(s1);
                firstname.setText(s2);
                lastname.setText(s3);
            }    
        }catch(Exception e){System.out.println(e);}
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void back_button(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("customer_view.fxml"));
        Parent root=loader.load();
        Customer_viewController data=loader.getController();
        data.getuser(user);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void edit(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("edit_profile.fxml"));
        Parent root=loader.load();
        Edit_profileController data=loader.getController();
        data.getuser(user);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
