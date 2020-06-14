package ru.job4j.workers.repository.server;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import ru.job4j.workers.repository.database.pojo.WorkersList;


public interface ApiService {
    @GET("task.json")
    Single<Response<WorkersList>> getWorkers();
}
