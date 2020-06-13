package ru.job4j.workers.repository.server;

import io.reactivex.Single;
import retrofit2.Response;
import ru.job4j.workers.repository.database.pojo.WorkersList;

public interface ApiService {
    Single<Response<WorkersList>> getWorkers();
}
