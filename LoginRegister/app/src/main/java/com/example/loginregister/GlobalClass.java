package com.example.loginregister;

import android.app.Application;


public class GlobalClass extends Application {

    private String username;
    private String fullname;
    private String email;
    private String locker_id;

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getLocker_id(){
        return this.locker_id;
    }

    public void setLocker_id(String locker_id){
        this.locker_id = locker_id;
    }

    public String getFullname(){
        return this.fullname;
    }

    public void setFullname(String fullname){
        this.fullname = fullname;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
