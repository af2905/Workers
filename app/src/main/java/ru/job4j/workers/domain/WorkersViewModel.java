package ru.job4j.workers.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.job4j.workers.presentation.widget.SingleLiveEvent;
import ru.job4j.workers.repository.AppRepository;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkersViewModel extends BaseViewModel {
    private AppRepository repository;
    private SingleLiveEvent<List<Worker>> liveDataItems = new SingleLiveEvent<>();
    private MutableLiveData<Integer> liveDataWorkerId = new MutableLiveData<>();

    public WorkersViewModel(@NonNull Application application, AppRepository repository) {
        super(application);
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllWorkers() {
        repository.getAllWorkers().subscribe(list -> liveDataItems.setValue(list));
    }

    public void loadImgFromNet(String url, int placeholder, int error, ImageView imageView) {
        repository.loadImg(url, placeholder, error, imageView);
    }

    public SingleLiveEvent<List<Worker>> getLiveDataItems() {
        return liveDataItems;
    }

    public void setLiveDataWorkerId(MutableLiveData<Integer> liveDataWorkerId) {
        this.liveDataWorkerId = liveDataWorkerId;
    }

    private MutableLiveData<Integer> getLiveDataWorkerId() {
        return liveDataWorkerId;
    }

    public Worker getLiveDataDetail() {
        return repository.getWorker(getLiveDataWorkerId().getValue());
    }
}
