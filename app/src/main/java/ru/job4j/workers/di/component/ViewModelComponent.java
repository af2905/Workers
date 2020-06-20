package ru.job4j.workers.di.component;

import dagger.Component;
import ru.job4j.workers.di.module.ViewModelModule;
import ru.job4j.workers.di.scope.ViewModelScope;
import ru.job4j.workers.presentation.view.activity.MainActivity;
import ru.job4j.workers.presentation.view.fragments.DetailFragment;
import ru.job4j.workers.presentation.view.fragments.SpecialtiesFragment;
import ru.job4j.workers.presentation.view.fragments.WorkersFragment;

@ViewModelScope
@Component(modules = {ViewModelModule.class}, dependencies = {RepositoryComponent.class})
public interface ViewModelComponent {

    void inject(MainActivity activity);

    void inject(SpecialtiesFragment fragment);

    void inject(WorkersFragment fragment);

    void inject(DetailFragment fragment);
}
