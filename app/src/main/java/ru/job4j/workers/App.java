package ru.job4j.workers;

import android.app.Application;

import androidx.room.Room;

import ru.job4j.workers.di.component.ApiComponent;
import ru.job4j.workers.di.component.DaggerApiComponent;
import ru.job4j.workers.di.component.DaggerDaoComponent;
import ru.job4j.workers.di.component.DaggerRepositoryComponent;
import ru.job4j.workers.di.component.DaggerViewModelComponent;
import ru.job4j.workers.di.component.DaoComponent;
import ru.job4j.workers.di.component.RepositoryComponent;
import ru.job4j.workers.di.component.ViewModelComponent;
import ru.job4j.workers.di.module.ApiModule;
import ru.job4j.workers.di.module.DaoModule;
import ru.job4j.workers.di.module.RepositoryModule;
import ru.job4j.workers.di.module.ViewModelModule;
import ru.job4j.workers.repository.database.AppDatabase;

public class App extends Application {
    private ViewModelComponent viewModelComponent;
    private AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();
        initRoom();
        initDagger();
    }

    private void initRoom() {
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public ViewModelComponent getViewModelComponent() {
        return viewModelComponent;
    }

    private void initDagger() {
        ApiComponent apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();

        DaoComponent daoComponent = DaggerDaoComponent.builder()
                .daoModule(new DaoModule(database))
                .build();

        RepositoryComponent repositoryComponent = DaggerRepositoryComponent.builder()
                .apiComponent(apiComponent)
                .daoComponent(daoComponent)
                .repositoryModule(new RepositoryModule())
                .build();

        viewModelComponent = DaggerViewModelComponent.builder()
                .repositoryComponent(repositoryComponent)
                .viewModelModule(new ViewModelModule(this))
                .build();
    }
}