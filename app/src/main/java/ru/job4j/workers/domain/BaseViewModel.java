package ru.job4j.workers.domain;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import ru.job4j.workers.App;

public class BaseViewModel extends AndroidViewModel {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    public App getContext() {
        return getApplication();
    }
}
