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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class View_filmsController implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    String user,id1,id2,id3,id4;
    @FXML
    private Label title1;
    @FXML
    private Label d1;
    @FXML
    private Label title2;
    @FXML
    private Label d2;
    @FXML
    private Label title3;
    @FXML
    private Label d3;
    @FXML
    private Label title4;
    @FXML
    private Label d4;
    
    public void getuser(String u){
        this.user=u;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection con;
        int i=1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from films;");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                if(i==1){
                    id1=rs.getString(1);
                    title1.setText(rs.getString(2));
                    d1.setText(rs.getString(3));      
                }
                if(i==2){
                    id2=rs.getString(1);
                    title2.setText(rs.getString(2));
                    d2.setText(rs.getString(3));      
                }
                if(i==3){
                    id3=rs.getString(1);
                    title3.setText(rs.getString(2));
                    d3.setText(rs.getString(3));      
                }
                if(i==4){
                    id4=rs.getString(1);
                    title4.setText(rs.getString(2));
                    d4.setText(rs.getString(3));      
                }
                i++;
            }    
        }catch(Exception e){System.out.println(e);}
    }    

    @FXML
    private void book1(ActionEvent event) throws IOException {
        String title=title1.getText();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root=loader.load();
        BookingController data=loader.getController();
        data.getuser(user,title,id1);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void book2(ActionEvent event) throws IOException {
        String title=title2.getText();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root=loader.load();
        BookingController data=loader.getController();
        data.getuser(user,title,id2);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void book3(ActionEvent event) throws IOException {
        String title=title3.getText();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root=loader.load();
        BookingController data=loader.getController();
        data.getuser(user,title,id3);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void book4(ActionEvent event) throws IOException {
        String title=title4.getText();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("booking.fxml"));
        Parent root=loader.load();
        BookingController data=loader.getController();
        data.getuser(user,title,id4);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    
}
