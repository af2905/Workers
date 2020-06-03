package ru.job4j.workers;

import android.app.Application;

import androidx.room.Room;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.job4j.workers.data.ApiService;
import ru.job4j.workers.data.AppDatabase;

public class App extends Application {
    private static App instance;
    private AppDatabase database;
    private ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDatabase();
        initRetrofit();
    }

    private void initDatabase() {
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/test-tasks/task-json/blob/master/task.json")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public ApiService getApiService() {
        return apiService;
    }
}




