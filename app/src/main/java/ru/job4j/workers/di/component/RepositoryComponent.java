package ru.job4j.workers.di.component;

import dagger.Component;
import ru.job4j.workers.di.module.RepositoryModule;
import ru.job4j.workers.di.scope.RepositoryScope;
import ru.job4j.workers.repository.AppRepository;

@RepositoryScope
@Component(modules = {RepositoryModule.class}, dependencies = {ApiComponent.class, DaoComponent.class})
public interface RepositoryComponent {
    AppRepository getRepository();
}
