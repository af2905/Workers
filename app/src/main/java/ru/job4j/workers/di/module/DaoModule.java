package ru.job4j.workers.di.module;

import dagger.Module;
import dagger.Provides;
import ru.job4j.workers.repository.database.AppDatabase;
import ru.job4j.workers.repository.database.dao.SpecialtyDao;
import ru.job4j.workers.repository.database.dao.WorkerDao;

@Module
public class DaoModule {
    private AppDatabase appDatabase;

    public DaoModule(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Provides
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Provides
    WorkerDao providesWorkerDao(AppDatabase database) {
        return database.workerDao();
    }

    @Provides
    SpecialtyDao providesSpecialtyDao(AppDatabase database) {
        return database.specialtyDao();
    }
}