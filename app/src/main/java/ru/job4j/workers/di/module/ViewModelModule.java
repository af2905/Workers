package ru.job4j.workers.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import ru.job4j.workers.di.scope.ViewModelScope;
import ru.job4j.workers.domain.WorkersViewModel;
import ru.job4j.workers.repository.AppRepository;

@Module
public class ViewModelModule {
    private Application application;

    public ViewModelModule(Application application) {
        this.application = application;
    }

    @ViewModelScope
    @Provides
    WorkersViewModel providesWorkersViewModel(AppRepository repository) {
        return new WorkersViewModel(application, repository);
    }
}