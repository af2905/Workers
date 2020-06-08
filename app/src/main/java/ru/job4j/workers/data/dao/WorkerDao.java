package ru.job4j.workers.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.job4j.workers.data.entity.Worker;
@Dao
public interface WorkerDao {

    @Query("SELECT * FROM workers")
    List<Worker> getAll();

    @Query("SELECT * FROM workers WHERE id = :id")
    Worker getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Worker worker);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insert(List<Worker> workers);

    @Update
    void update(Worker worker);

    @Delete
    void delete(Worker worker);
}
