package ru.job4j.workers;

import android.app.Application;

import androidx.room.Room;

import com.squareup.picasso.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.job4j.workers.data.ApiService;
import ru.job4j.workers.data.AppDatabase;
import ru.job4j.workers.data.DataRepository;
import ru.job4j.workers.domain.DataInteractor;

public class App extends Application {
    private static App instance;
    private DataInteractor dataInteractor;
    private AppDatabase database;
    private ApiService apiService;
    private DataRepository dataRepository = new DataRepository();
    private static final String BASE_URL = "https://raw.githubusercontent.com/af2905/jsons/master/";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initRetrofit();
        initDatabase();
        initInteractor();
    }

    private void initDatabase() {
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    private void initInteractor() {
        dataInteractor = new DataInteractor(apiService, dataRepository);
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

    public DataInteractor getDataInteractor() {
        return dataInteractor;
    }
}




