package ru.job4j.workers.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.BuildConfig;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.job4j.workers.di.scope.ApiScope;
import ru.job4j.workers.repository.server.ApiService;
import ru.job4j.workers.repository.server.ServerCommunicator;

@Module
public class ApiModule {
    private static final String BASE_URL = "https://raw.githubusercontent.com/af2905/jsons/master/";

    @Provides
    @ApiScope
    public ServerCommunicator providesServerCommunicator(ApiService apiService) {
        return new ServerCommunicator(apiService);
    }

    @Provides
    @ApiScope
    public ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @ApiScope
    public Retrofit providesRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(BASE_URL).build();
    }

    @Provides
    @ApiScope
    public Retrofit.Builder providesRetrofitBuilder() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(5, 30, TimeUnit.SECONDS))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Gson gson =
                new GsonBuilder()
                        .setLenient()
                        .create();


        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }
}