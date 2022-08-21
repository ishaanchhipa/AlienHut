/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alienHut;

import java.awt.Checkbox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class View_bookingsController implements Initializable {
    String user;
    private Parent root;
    private Scene scene;
    private Stage stage;
    String val1,val2,val3,val4,val5;
    @FXML
    private TableColumn<bookingclass, Integer> sno;
    @FXML
    private TableColumn<bookingclass, String> movieid;
    @FXML
    private TableColumn<bookingclass, String> movie_title;
    @FXML
    private TableColumn<bookingclass, String> date1;
    @FXML
    private TableColumn<bookingclass, String> time1;
    @FXML
    private TableColumn<bookingclass, String> seats;
    @FXML
    private TableView<bookingclass> table;

    ObservableList <bookingclass> items=FXCollections.observableArrayList();
    public void getuser(String u){
        this.user=u;
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from booking where user="+"'"+user+"'"+";");
            ResultSet rs = statement.executeQuery();
            int i=1;
            while(rs.next()){
                String s1=rs.getString(1);
                String s2=rs.getString(3);
                String s3=rs.getString(4);
                String s4=rs.getString(5);
                String s5=rs.getString(6);
                items.add(new bookingclass(i,s1,s2,s3,s4,s5));
                table.setItems(items);
                sno.setCellValueFactory(new PropertyValueFactory<>("sno"));
                movieid.setCellValueFactory(new PropertyValueFactory<>("movieid"));
                movie_title.setCellValueFactory(new PropertyValueFactory<>("movietitle"));
                date1.setCellValueFactory(new PropertyValueFactory<>("date"));
                time1.setCellValueFactory(new PropertyValueFactory<>("time"));
                seats.setCellValueFactory(new PropertyValueFactory<>("seats"));
                i++;
            }    
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
    private void delete_booking(ActionEvent event) {
        bookingclass selectedItems = table.getSelectionModel().getSelectedItems().get(0);
        val1=selectedItems.getMovieid();
        val2=selectedItems.getMovietitle();
        val3=selectedItems.getDate();
        val4=selectedItems.getTime();
        val5=selectedItems.getSeats();
        
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");    
            Statement s=con.createStatement();
            String s1= "delete from booking where movieid = '" + val1 + "'"+"AND title = '"+val2+"'"+" AND date = '"+val3+"'"+"AND time = '"+val4+"'"+"AND seats = '" +val5+"';" ;
            System.out.println("Booking Deleted Successfully Updated And Stored Into Database...");
            s.executeUpdate(s1);

    //        showAlert("success");

            FXMLLoader loader=new FXMLLoader(getClass().getResource("view_bookings.fxml"));
            Parent root=loader.load();
            View_bookingsController data=loader.getController();
            data.getuser(user);
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch(Exception e){System.out.println(e);}
    }
    
}
