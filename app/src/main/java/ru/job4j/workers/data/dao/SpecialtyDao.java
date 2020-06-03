package ru.job4j.workers.data.dao;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ru.job4j.workers.data.entity.Specialty;
import ru.job4j.workers.data.entity.SpecialtyWithWorkers;

public interface SpecialtyDao {

    @Query("SELECT * FROM specialties")
    List<Specialty> getAll();

    @Transaction
    @Query("SELECT id, name FROM specialties")
    List<SpecialtyWithWorkers> getSpecialtyWithWorkers();

    @Insert
    long insert(Specialty specialty);

    @Insert
    List<Long> insert(List<Specialty> specialties);

    @Delete
    void delete(Specialty specialty);

}
