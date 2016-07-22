package com.se2.wanderlust.Database;

import java.io.Serializable;

/**
 * Created by
 * Roberto on 10.07.16.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1;

    // id of the user. given by database
    private long id;
    private String name;
    private String lastname;
    // email of the user
    private String email;
    // password of the user
    private String password;
    // can we use the photos public
    public boolean isPublicPhotos = true;
    // the hpa
    public double hpa = 0;
    // the tracking rate in ms
    public double tracking_rate = 0;

    /**
     * Creats a user object
     */
    public User() {
    }


    /**
     *  GETTER and SETTER
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublicPhotos() {
        return isPublicPhotos;
    }

    public void setPublicPhotos(boolean publicPhotos) {
        isPublicPhotos = publicPhotos;
    }

    public double getHpa() {
        return hpa;
    }

    public void setHpa(double hpa) {
        this.hpa = hpa;
    }

    public double getTracking_rate() {
        return tracking_rate;
    }

    public void setTracking_rate(double tracking_rate) {
        this.tracking_rate = tracking_rate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isPublicPhotos=" + isPublicPhotos +
                ", hpa=" + hpa +
                ", tracking_rate=" + tracking_rate +
                '}';
    }
}
