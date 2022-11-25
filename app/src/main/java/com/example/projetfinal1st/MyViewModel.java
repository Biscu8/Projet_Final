package com.example.projetfinal1st;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;

public class MyViewModel extends AndroidViewModel {

    UserRepository userRepository;

    /**
     * Links repo with app
     * @param application Application of main app
     */
    public MyViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public String getUsername(String username) {
        username = userRepository.getUsername();
    }
}
