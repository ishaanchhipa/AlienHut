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
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ISHAAN
 */
public class BookingController implements Initializable {
    private Parent root;
    private Scene scene;
    private Stage stage;
    
    String user,title1,movieid;
    int a11=0,a22=0,a33=0,a44=0,b11=0,b22=0,b33=0,b44=0;
    ObservableList seat=FXCollections.observableArrayList();
    
    @FXML
    private Label title;
    @FXML
    private Label name;
    @FXML
    private DatePicker date;

    @FXML
    private Button b1;
    @FXML
    private Button b2;
    @FXML
    private Button b3;
    @FXML
    private Button b4;
    @FXML
    private Button a1;
    @FXML
    private Button a2;
    @FXML
    private Button a3;
    @FXML
    private Button a4;
    @FXML
    private ComboBox<?> time;

    private void showAlert(String s) {
        if (s.equals("booking_error"))
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Improper Selection");
            alert.setContentText("Try again!");
            alert.showAndWait();
        }
        if (s.equals("booked"))
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Booked");
            alert.setHeaderText("Congratulation");
            alert.setContentText("Your Ticket Booked Successfully!");
            alert.showAndWait();
        }
    }
    
    public void getuser(String u, String t,String id){
        this.user=u;
        this.title1=t;
        this.movieid=id;
        title.setText(title1);
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from user where username="+"'"+user+"'"+";");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String s1=rs.getString(2);
                name.setText(s1);
            }    
        }catch(Exception e){System.out.println(e);}
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList list=FXCollections.observableArrayList();
            list.add("12:00pm");
            list.add("3:00pm");
            list.add("6:00pm");
            list.add("9:00pm");
        time.setItems(list);
        time.getSelectionModel().select(0);
        
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.of(2022, 8, 25);
        date.setDayCellFactory(d ->
                   new DateCell() {
                       @Override public void updateItem(LocalDate item, boolean empty) {
                           super.updateItem(item, empty);
                           setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                       }});
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view_films.fxml"));
        Parent root=loader.load();
        View_filmsController data=loader.getController();
        data.getuser(user);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void b1(ActionEvent event) {
        if(b11%2==0){
            seat.add("b1");
            b1.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; "); 
        }
        else{
            seat.remove("b1");
            b1.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;"); 
        }
        b11++;
    }

    @FXML
    private void b2(ActionEvent event) {
        
        if(b22%2==0){
            seat.add("b2");
            b2.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; ");

        }
        else{
            seat.remove("b2");
            b2.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        b22++;
    }

    @FXML
    private void b3(ActionEvent event) {
        if(b33%2==0){
            seat.add("b3");
            b3.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; "); 

        }
        else{
            seat.remove("b3");
            b3.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        b33++;
    }

    @FXML
    private void b4(ActionEvent event) {
        if(b44%2==0){
            seat.add("b4");
            b4.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; "); 
 
        }
        else{
            seat.remove("b4");
            b4.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        b44++;
    }

    @FXML
    private void a1(ActionEvent event) {
        if(a11%2==0){
            seat.add("a1");
            a1.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; ");

        }
        else{
            seat.remove("a1");
            a1.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        a11++;
    }

    @FXML
    private void a2(ActionEvent event) {
        if(a22%2==0){
            seat.add("a2");
            a2.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; ");

        }
        else{
            seat.remove("a2");
            a2.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        a22++;
    }

    @FXML
    private void a3(ActionEvent event) {
        if(a33%2==0){
            seat.add("a3");
            a3.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; ");

        }
        else{
            seat.remove("a3");
            a3.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        a33++;
    }

    @FXML
    private void a4(ActionEvent event) {
        if(a44%2==0){
            seat.add("a4");
            a4.setStyle("-fx-background-color:   #cf142b; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15; ");

        }
        else{
            seat.remove("a4");
            a4.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");

        }
        a44++;
    }

    @FXML
    private void book_now(ActionEvent event) {
        
        LocalDate date1=date.getValue();
        String time1=(String) time.getValue();
        String seat1=String.join(",",seat);
        if(date1 == null||time1 == null||seat1.equals(""))
        {
            showAlert("booking_error");
        }
        else{
            Connection con;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
                Statement s=con.createStatement();
                String s1= "insert into booking(movieid,user,title,date,time,seats) values('"+ movieid + "','"+ user + "','"+ title1 + "','"+ date1 + "','"+ time1 + "','"+ seat1 + "');";
                System.out.println("Booking Done Successfully And Stored Into Database...");
                s.executeUpdate(s1);
                
                showAlert("booked");
                
                FXMLLoader loader=new FXMLLoader(getClass().getResource("customer_view.fxml"));
                Parent root=loader.load();
                Customer_viewController data=loader.getController();
                System.out.println(s1);
                data.getuser(user);
                stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();
            }catch(Exception e){System.out.println(e);}
        }
    }


    @FXML
    private void seat_availability(ActionEvent event) {
        try{
            this.seat.removeAll();
        }catch(Exception e){System.out.print(e);}
        a1.setDisable(false);
        a2.setDisable(false);
        a3.setDisable(false);
        a4.setDisable(false);
        b1.setDisable(false);
        b2.setDisable(false);
        b3.setDisable(false);
        b4.setDisable(false);
        
        b1.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;"); 
        b2.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        b3.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        b4.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        a1.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        a2.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        a3.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        a4.setStyle("-fx-background-color:  #379683; -fx-text-fill:   #EDF5E1; -fx-font-weight:   bold; -fx-font-size:   15;");
        
        LocalDate date1=date.getValue();
        String time1=(String) time.getValue();
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/alienhut","root","");
            PreparedStatement statement = con.prepareStatement("select * from booking where movieid="+"'"+movieid+"' AND date="+"'"+date1+"'"+" AND time="+"'"+time1+"';");
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String s1=rs.getString(6);
                String[] dseats=s1.split(",");
                for(int i=0;i<dseats.length;i++){
                    if (dseats[i].equals("a1")){   
                        a1.setDisable(true);
                        a1.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("a2"))
                    {
                        a2.setDisable(true);
                        a2.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("a3")){
                        a3.setDisable(true);
                        a3.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("a4")){
                        a4.setDisable(true);
                        a4.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("b1")){
                        b1.setDisable(true);
                        b1.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("b2")){
                        b2.setDisable(true);
                        b2.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("b3")){
                        b3.setDisable(true);
                        b3.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }
                    if (dseats[i].equals("b4")){
                        b4.setDisable(true);
                        b4.setStyle("-fx-background-color:   #d8d8d8; -fx-text-fill:   #05386B; -fx-font-weight:   bold; -fx-font-size:   15; ");
                    }    
                }
            }    
        }catch(Exception e){System.out.println(e);}
    }

    
}
