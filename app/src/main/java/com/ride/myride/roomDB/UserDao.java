package com.example.myride.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserEntity entity);

    @Query("DELETE FROM user_info")
    void deleteAll();

    @Query("SELECT * FROM user_info ORDER BY id ASC")
    List<UserEntity> getAllUser();

    @Query("SELECT * FROM user_info WHERE id=:id")
    UserEntity getUserByID(int id);

    @Query("SELECT * FROM user_info WHERE id=:name")
    UserEntity getUserByName(int name);

    @Query("SELECT * FROM user_info WHERE id=:type")
    List<UserEntity> getUserByType(int type);
}
