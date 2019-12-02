package com.example.myride.roomDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserEntity {

    @NonNull
    @ColumnInfo(name = "firstname")
    private String firstname;

    @NonNull
    @ColumnInfo(name = "lastname")
    private String lastname;

    @NonNull
    @ColumnInfo(name = "phonenumber")
    private String phonenumber;

    @ColumnInfo(name = "email")
    private String email;

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @Ignore
    public UserEntity(){

    }
    @Ignore
    public UserEntity(String firstname,String lastname,String phonenumber,String email){
          this.setPhonenumber(phonenumber);
          this.setFirstname(firstname);
          this.setLastname(lastname);
          this.setEmail(email);
    }

    public UserEntity(int id,String firstname,String lastname,String phonenumber,String email){
        this.setPhonenumber(phonenumber);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
        this.setId(id);
    }

    @NonNull
    public String getLastname() {
        return lastname;
    }

    public void setLastname(@NonNull String lastname) {
        this.lastname = lastname;
    }

    @NonNull
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(@NonNull String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
    }
}
