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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class Admin_view_film_bookingsController implements Initializable {
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    String title1,movieid;
    
    @FXML
    private Label title;
    @FXML
    private Label count;
    @FXML
    private TableView<bookingclass> table;
    @FXML
    private TableColumn<bookingclass, Integer> sno;
    @FXML
    private TableColumn<bookingclass, String> user_name;
    @FXML
    private TableColumn<bookingclass, String> date1;
    @FXML
    private TableColumn<bookingclass, String> time1;
    @FXML
    private TableColumn<bookingclass, String> seats;
    
    ObservableList <bookingclass> items=FXCollections.observableArrayList();
    public void getmovie(String t,String id){
        this.title1=t;
        this.movieid=id;
        title.setText(title1);
        
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from booking where movieid="+"'"+movieid+"'"+";");
            ResultSet rs = statement.executeQuery();
            int i=1;
            while(rs.next()){
                String s1=rs.getString(2);
                String s2=rs.getString(4);
                String s3=rs.getString(5);
                String s4=rs.getString(6);
                items.add(new bookingclass(i,s1,s2,s3,s4));
                table.setItems(items);
                sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
                user_name.setCellValueFactory(new PropertyValueFactory<>("user_name"));
                date1.setCellValueFactory(new PropertyValueFactory<>("date"));
                time1.setCellValueFactory(new PropertyValueFactory<>("time"));
                seats.setCellValueFactory(new PropertyValueFactory<>("seats"));
                i++;
            }
            count.setText(Integer.toString(i-1));
        }catch(Exception e){System.out.println(e);}
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setPlaceholder(new Label("No Bookings"));
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("admin_view_films.fxml"));
        Parent root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
