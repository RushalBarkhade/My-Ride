package com.example.myride.roomDB;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private LiveData<List<UserEntity>> mAllUser;

    UserRepository(Application application){
        RoomDB db = RoomDB.getInstance(application);
        userDao = db.userDao();
        mAllUser = (LiveData<List<UserEntity>>) userDao.getAllUser();
    }

    LiveData<List<UserEntity>> getmAllUser(){
        return mAllUser;
    }

    void insert(final UserEntity entity){
        RoomDB.database.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insert(entity);
            }
        });
    }
}
