package com.example.myride.roomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository repository;
    private LiveData<List<UserEntity>> mAllUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        mAllUser = repository.getmAllUser();
    }

    LiveData<List<UserEntity>> getmAllUser(){
        return mAllUser;
    }
    public void insert(UserEntity entity){
        repository.insert(entity);
    }
}
