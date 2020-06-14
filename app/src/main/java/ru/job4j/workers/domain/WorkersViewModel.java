package ru.job4j.workers.domain;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;

import java.util.List;

import ru.job4j.workers.presentation.widget.SingleLiveEvent;
import ru.job4j.workers.repository.AppRepository;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkersViewModel extends BaseViewModel {
    private AppRepository repository;
    private SingleLiveEvent<List<Worker>> liveDataItems = new SingleLiveEvent<>();

    public WorkersViewModel(@NonNull Application application, AppRepository repository) {
        super(application);
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllWorkers() {
        repository.getAllWorkers().subscribe(list -> liveDataItems.setValue(list));
    }

    public SingleLiveEvent<List<Worker>> getLiveDataItems() {
        return liveDataItems;
    }
}