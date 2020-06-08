package ru.job4j.workers.data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import ru.job4j.workers.data.entity.Worker;

public interface ApiService {
    @GET("test-tasks/task-json/master/task.json")
    Single<List<Worker>> getWorkers();
}
