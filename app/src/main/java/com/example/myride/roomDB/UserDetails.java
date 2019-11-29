package com.example.myride.roomDB;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = UserEntity.class,
        parentColumns = "id",childColumns = "userid",
        onDelete = ForeignKey.CASCADE))
public class UserDetails {
    @PrimaryKey
    public final int id;
    private int userid;
    private String img;

    public UserDetails(final  int id,int userid,String img){
        this.setImg(img);
        this.id = id;
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
