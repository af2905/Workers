package ru.job4j.workers.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.job4j.workers.data.entity.Worker;

public interface ApiService {
    @GET
    Call<List<Worker>> getWorkers();
}
