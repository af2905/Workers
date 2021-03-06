package ru.job4j.workers.repository.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.job4j.workers.repository.database.dao.SpecialtyDao;
import ru.job4j.workers.repository.database.dao.WorkerDao;
import ru.job4j.workers.repository.database.entity.Specialty;
import ru.job4j.workers.repository.database.entity.Worker;

@Database(entities = {Worker.class, Specialty.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WorkerDao workerDao();

    public abstract SpecialtyDao specialtyDao();
}
