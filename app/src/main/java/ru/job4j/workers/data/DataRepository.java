package ru.job4j.workers.data;

import java.util.ArrayList;
import java.util.List;

import ru.job4j.workers.data.entity.Worker;

public class DataRepository {
    private List<Worker> cachedWorkersList = new ArrayList<>();

    public void addToCache(List<Worker> workers) {
        this.cachedWorkersList.addAll(workers);
    }

    public List<Worker> getCachedWorkersList() {
        return cachedWorkersList;
    }
}
