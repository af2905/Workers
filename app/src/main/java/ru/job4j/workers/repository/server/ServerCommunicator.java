package ru.job4j.workers.repository.server;

import io.reactivex.Single;
import retrofit2.Response;
import ru.job4j.workers.repository.database.pojo.WorkersList;

public class ServerCommunicator {
    private ApiService apiService;

    public ServerCommunicator(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<Response<WorkersList>> getAllWorkers() {
        return apiService.getWorkers();
    }
}
