package ru.job4j.workers.domain;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import ru.job4j.workers.data.ApiService;
import ru.job4j.workers.data.DataRepository;
import ru.job4j.workers.data.entity.Worker;

public class DataInteractor {
    private ApiService apiService;
    private DataRepository dataRepository;

    public DataInteractor(ApiService apiService, DataRepository dataRepository) {
        this.apiService = apiService;
        this.dataRepository = dataRepository;
    }

    public void getData(GetWorkersCallback callback) {
        this.apiService.getWorkers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Worker>>() {
                    @Override
                    public void onSuccess(List<Worker> workers) {
                        dataRepository.addToCache(workers);
                        callback.onSuccess(dataRepository.getCachedWorkersList());
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e.getMessage());
                    }
                });
    }


    public interface GetWorkersCallback {
        void onSuccess(List<Worker> workers);

        void onError(String error);
    }
}
