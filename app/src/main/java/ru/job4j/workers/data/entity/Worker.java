package ru.job4j.workers.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

/*@Entity(tableName = "workers", foreignKeys = @ForeignKey(entity = Specialty.class, parentColumns = "id", childColumns = "specialty_id"))*/
@Entity(tableName = "workers")
public class Worker {
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("f_name")
    @ColumnInfo(name = "f_name")
    private String firstName;

    @SerializedName("l_name")
    @ColumnInfo(name = "l_name")
    private String lastName;

    private String birthday;

    @SerializedName("avatr_url")
    private String avatarUrl;

    @TypeConverters({SpecialtyConverter.class})
    private List<Specialty> specialty;

    public Worker(String firstName, String lastName, String birthday, String avatarUrl, List<Specialty> specialty) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatarUrl = avatarUrl;
        this.specialty = specialty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Specialty> getSpecialty() {
        return specialty;
    }

    public void setSpecialty(List<Specialty> specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Worker worker = (Worker) o;
        return id.equals(worker.id)
                && Objects.equals(firstName, worker.firstName)
                && Objects.equals(lastName, worker.lastName)
                && Objects.equals(birthday, worker.birthday)
                && Objects.equals(avatarUrl, worker.avatarUrl)
                && Objects.equals(specialty, worker.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, avatarUrl, specialty);
    }
}

