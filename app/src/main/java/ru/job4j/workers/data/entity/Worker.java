package ru.job4j.workers.data.entity;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "workers", foreignKeys = @ForeignKey(entity = Specialty.class, parentColumns = "id", childColumns = "specialty_id"))
public class Worker {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @SerializedName("f_name")
    @ColumnInfo(name = "first_name")
    private String firstName;

    @SerializedName("l_name")
    @ColumnInfo(name = "last_name")
    private String lastName;

    private String birthday;

    @SerializedName("avatr_url")
    @Ignore
    private Bitmap avatar;

    @ColumnInfo(name = "specialty_id")
    private long specialtyId;

    public Worker(long id, String firstName, String lastName, String birthday, Bitmap avatar, long specialtyId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatar = avatar;
        this.specialtyId = specialtyId;
    }

    public Worker(String firstName, String lastName, String birthday, Bitmap avatar, long specialtyId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.avatar = avatar;
        this.specialtyId = specialtyId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(long specialtyId) {
        this.specialtyId = specialtyId;
    }
}

