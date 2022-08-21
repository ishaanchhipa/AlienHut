/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alienHut;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class Customer_viewController implements Initializable {
    String username="Welcome...";
    private Parent root;
    private Scene scene;
    private Stage stage;
    @FXML
    private Label user;
    @FXML
    private ImageView profile_pic;
    
    public void getuser(String u){
        this.username=u;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void logout_button(ActionEvent event) throws IOException {
        Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void view_films(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view_films.fxml"));
        Parent root=loader.load();
        View_filmsController data=loader.getController();
        data.getuser(username);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void view_bookings(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view_bookings.fxml"));
        Parent root=loader.load();
        View_bookingsController data=loader.getController();
        data.getuser(username);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void view_profile(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view_profile.fxml"));
        Parent root=loader.load();
        View_profileController data=loader.getController();
        data.getuser(username);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
