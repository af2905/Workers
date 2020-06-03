package ru.job4j.workers.data.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SpecialtyWithWorkers {

    @Embedded
    private Specialty specialty;

    @Relation(parentColumn = "id", entityColumn = "specialty_id")
    private List<Worker> workers;

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
