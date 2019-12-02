package com.example.myride.roomDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = UserEntity.class,
        parentColumns = "id",childColumns = "userid",
        onDelete = ForeignKey.CASCADE))
public class UserPrimaryDetailsEntity {
    @PrimaryKey
    public final int id;
    private int userid;
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

    @Ignore
    public UserPrimaryDetailsEntity(final int id,int userid){
        this.id = id;
        this.setUserid(userid);
    }

    public UserPrimaryDetailsEntity(final int id,int userid,String firstname,
                                    String lastname,String phonenumber,String email){
        this.id = id;
        this.setUserid(userid);
        this.setEmail(email);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPhonenumber(phonenumber);

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @NonNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NonNull String firstname) {
        this.firstname = firstname;
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
}
