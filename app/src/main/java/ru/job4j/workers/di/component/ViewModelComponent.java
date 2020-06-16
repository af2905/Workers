package ru.job4j.workers.di.component;

import dagger.Component;
import ru.job4j.workers.di.module.ViewModelModule;
import ru.job4j.workers.di.scope.ViewModelScope;
import ru.job4j.workers.presentation.view.WorkersFragment;

@ViewModelScope
@Component(modules = {ViewModelModule.class}, dependencies = {RepositoryComponent.class})
public interface ViewModelComponent {
    void inject(WorkersFragment fragment);
}
