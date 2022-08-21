/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alienHut;

import java.awt.Checkbox;

/**
 *
 * @author ISHAAN
 */
public class bookingclass {
    private int sno;
    private String user_name;
    private String movieid;
    private String movietitle;
    private String date;
    private String time;
    private String seats;
    
    bookingclass(int sno,String movieid,String movietitle,String date,String time,String seats){
        this.sno=sno;
        this.movieid=movieid;
        this.movietitle=movietitle;
        this.date=date;
        this.time=time;
        this.seats=seats;
    }
    bookingclass(int sno,String user_name,String date,String time,String seats){
        this.sno=sno;
        this.user_name=user_name;
        this.date=date;
        this.time=time;
        this.seats=seats;
    }

    /**
     * @return the sno
     */
    public int getSno() {
        return sno;
    }

    /**
     * @param sno the sno to set
     */
    public void setSno(int sno) {
        this.sno = sno;
    }

    /**
     * @return the movieid
     */
    public String getMovieid() {
        return movieid;
    }

    /**
     * @param movieid the movieid to set
     */
    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    /**
     * @return the movietitle
     */
    public String getMovietitle() {
        return movietitle;
    }

    /**
     * @param movietitle the movietitle to set
     */
    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the seats
     */
    public String getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(String seats) {
        this.seats = seats;
    }

    /**
     * @return the user_name
     */
    public String getUser_name() {
        return user_name;
    }

    /**
     * @param user_name the user_name to set
     */
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
  
}
