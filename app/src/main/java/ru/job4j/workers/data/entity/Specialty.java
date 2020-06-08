package ru.job4j.workers.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "specialties")
public class Specialty {
    @PrimaryKey
    @SerializedName("specialty_id")
    private Integer specialtyId;

    private String name;

    public Specialty(Integer specialtyId, String name) {
        this.specialtyId = specialtyId;
        this.name = name;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Specialty specialty = (Specialty) o;
        return specialtyId.equals(specialty.specialtyId)
                && Objects.equals(name, specialty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialtyId, name);
    }
}
