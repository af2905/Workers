package ru.job4j.workers.di.component;

import dagger.Component;
import ru.job4j.workers.di.module.ApiModule;
import ru.job4j.workers.di.scope.ApiScope;
import ru.job4j.workers.repository.server.ServerCommunicator;

@ApiScope
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    ServerCommunicator getServerCommunicator();
}
