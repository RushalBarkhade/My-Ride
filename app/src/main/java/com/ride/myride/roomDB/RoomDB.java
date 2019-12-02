package com.example.myride.roomDB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.myride.utils.Converter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserEntity.class,RecentPlacesEntity.class},
        version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public abstract class RoomDB extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract RecentPlacesDao recentPlacesDao();
    //public abstract UserPrimaryDetailsEntity userPrimaryDetailsEntity();
    //public abstract UserDetails userDetails();

    private static volatile RoomDB INSTANCE;
    private static final int NUMBERS_OF_THREAD = 4;
    static final ExecutorService database = Executors.newFixedThreadPool(NUMBERS_OF_THREAD);

    public static RoomDB getInstance(final Context context){
        if (INSTANCE==null){
            synchronized (RoomDB.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class,"DATABASE").build();
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
