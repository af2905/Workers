package ru.job4j.workers.repository.database.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import ru.job4j.workers.repository.database.pojo.Specialty;

public class SpecialtyConverter {
    @TypeConverter
    public static String listToJson(List<Specialty> specialties) {
        if (specialties == null) {
            return null;
        }
        Type type = new TypeToken<List<Specialty>>() {
        }.getType();
        return new Gson().toJson(specialties, type);
    }

    @TypeConverter
    public static List<Specialty> jsonToList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Specialty>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
