package ru.job4j.workers.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.job4j.workers.data.dao.SpecialtyDao;
import ru.job4j.workers.data.dao.WorkerDao;
import ru.job4j.workers.data.entity.Specialty;
import ru.job4j.workers.data.entity.Worker;

@Database(entities = {Worker.class, Specialty.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract WorkerDao workerDao();

    public abstract SpecialtyDao specialtyDao();
}
