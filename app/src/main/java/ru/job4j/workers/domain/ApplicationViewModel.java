package ru.job4j.workers.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.job4j.workers.presentation.widget.SingleLiveEvent;
import ru.job4j.workers.repository.AppRepository;
import ru.job4j.workers.repository.database.entity.Specialty;
import ru.job4j.workers.repository.database.entity.Worker;

public class ApplicationViewModel extends BaseViewModel {
    private AppRepository repository;
    private SingleLiveEvent<List<Worker>> liveDataWorkers = new SingleLiveEvent<>();
    private SingleLiveEvent<List<Specialty>> liveDataSpecialties = new SingleLiveEvent<>();
    private SingleLiveEvent<List<Worker>> liveDataSelectedWorkers = new SingleLiveEvent<>();
    private MutableLiveData<Integer> liveDataWorkerId = new MutableLiveData<>();
    private MutableLiveData<Integer> liveDataSpecialtyId = new MutableLiveData<>();

    public ApplicationViewModel(@NonNull Application application, AppRepository repository) {
        super(application);
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllWorkers() {
        repository.getAllWorkers().subscribe(list -> liveDataWorkers.setValue(list));
    }

    @SuppressLint("CheckResult")
    public void getAllSpecialties() {
        repository.getAllSpecialties().subscribe(list -> liveDataSpecialties.setValue(list));
    }

    @SuppressLint("CheckResult")
    public void getSelectedWorkers() {
        repository.getSelectedWorkers(liveDataSpecialtyId.getValue())
                .subscribe(list -> liveDataSelectedWorkers.setValue(list));
    }

    public void loadImgFromNet(String url, int placeholder, int error, ImageView imageView) {
        repository.loadImg(url, placeholder, error, imageView);
    }

    public SingleLiveEvent<List<Specialty>> getLiveDataSpecialties() {
        return liveDataSpecialties;
    }

    public SingleLiveEvent<List<Worker>> getLiveDataSelectedWorkers() {
        return liveDataSelectedWorkers;
    }

    public void setLiveDataWorkerId(MutableLiveData<Integer> liveDataWorkerId) {
        this.liveDataWorkerId = liveDataWorkerId;
    }

    public void setLiveDataSpecialtyId(MutableLiveData<Integer> liveDataSpecialtyId) {
        this.liveDataSpecialtyId = liveDataSpecialtyId;
    }

    private MutableLiveData<Integer> getLiveDataWorkerId() {
        return liveDataWorkerId;
    }

    public Worker getLiveDataDetail() {
        return repository.getWorker(getLiveDataWorkerId().getValue());
    }
}
