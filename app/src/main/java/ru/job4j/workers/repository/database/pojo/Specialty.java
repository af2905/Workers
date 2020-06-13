package ru.job4j.workers.repository.database.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Specialty {
    @SerializedName("specialty_id")
    private int specialtyId;

    private String name;

    public Specialty(Integer specialtyId, String name) {
        this.specialtyId = specialtyId;
        this.name = name;
    }

    public int getSpecialtyId() {
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
        return specialtyId == specialty.specialtyId
                && Objects.equals(name, specialty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specialtyId, name);
    }
}
