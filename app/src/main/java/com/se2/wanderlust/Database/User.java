package com.se2.wanderlust.Database;

/**
 * Created by
 * Roberto on 10.07.16.
 */
public class User {
    // id of the user. given by database
    private long id;
    // email of the user
    private String email;
    // password of the user
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
