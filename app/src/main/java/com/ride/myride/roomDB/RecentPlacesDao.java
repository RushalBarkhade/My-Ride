package com.example.myride.roomDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


@Dao
public interface RecentPlacesDao {

    @Query("SELECT name FROM RecentPlacesEntity ORDER BY date ASC LIMIT 10")
    String[] getRecentPlaces();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecentPlacesEntity entity);

}
