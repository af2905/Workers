package ru.job4j.workers.repository.server;

import android.util.Log;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import ru.job4j.workers.repository.database.pojo.WorkersList;

public class ServerCommunicator {
    private ApiService apiService;
    private static final int DEFAULT_TIMEOUT = 10;
    private static final int DEFAULT_RETRY_ATTEMPTS = 4;

    public ServerCommunicator(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<Response<WorkersList>> getAllWorkers() {
        return apiService
                .getWorkers()
                .compose(singleTransformer())
                .doOnError(throwable -> Log.d("ServerCommunicator", Objects.requireNonNull(throwable.getMessage())));
    }

    private static <T> SingleTransformer<T, T> singleTransformer() {
        return upstream -> upstream
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .timeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retry(DEFAULT_RETRY_ATTEMPTS);
    }
}