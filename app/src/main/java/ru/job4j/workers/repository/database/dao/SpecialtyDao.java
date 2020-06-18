package ru.job4j.workers.repository.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ru.job4j.workers.repository.database.entity.Specialty;

@Dao
public interface SpecialtyDao {
    @Query("SELECT * FROM specialties")
    List<Specialty> getAll();

    @Query("SELECT * FROM specialties WHERE specialtyId = :id")
    Specialty getById(Integer id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Specialty specialty);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertList(List<Specialty> specialties);

    @Update
    void update(Specialty specialty);

    @Update
    void updateAll(List<Specialty> specialties);

    @Delete
    void delete(Specialty specialty);
}
