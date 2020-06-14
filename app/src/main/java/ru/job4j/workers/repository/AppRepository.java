package ru.job4j.workers.repository;

import java.util.List;
import java.util.Objects;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.job4j.workers.repository.database.dao.WorkerDao;
import ru.job4j.workers.repository.database.entity.Worker;
import ru.job4j.workers.repository.server.ServerCommunicator;

public class AppRepository {
    private ServerCommunicator serverCommunicator;
    private WorkerDao workerDao;

    public AppRepository(ServerCommunicator serverCommunicator, WorkerDao workerDao) {
        this.serverCommunicator = serverCommunicator;
        this.workerDao = workerDao;
    }

    public Single<List<Worker>> getAllWorkers() {
        return serverCommunicator.getAllWorkers()
                .flatMap(list -> {
                    if (workerDao.getAll().size() == 0) {
                        workerDao.insertList(Objects.requireNonNull(list.body()).getWorkers());
                    }
                    return Single.just(workerDao.getAll());
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}