package ru.job4j.workers.repository.database.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.job4j.workers.repository.database.entity.Worker;

public class WorkersList {
    @SerializedName("response")
    private List<Worker> workers;

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
