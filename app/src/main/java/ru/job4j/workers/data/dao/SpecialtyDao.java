package ru.job4j.workers.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.job4j.workers.data.entity.Specialty;

@Dao
public interface SpecialtyDao {
    @Query("SELECT * FROM specialties")
    List<Specialty> getAll();

    @Insert
    long insert(Specialty specialty);

    @Insert
    List<Long> insert(List<Specialty> specialties);

    @Delete
    void delete(Specialty specialty);

}
