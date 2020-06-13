package ru.job4j.workers.di.module;

import dagger.Module;
import dagger.Provides;
import ru.job4j.workers.di.scope.RepositoryScope;
import ru.job4j.workers.repository.AppRepository;
import ru.job4j.workers.repository.database.dao.WorkerDao;
import ru.job4j.workers.repository.server.ServerCommunicator;

@Module
public class RepositoryModule {
    @RepositoryScope
    @Provides
    public AppRepository providesAppRepository(ServerCommunicator communicator, WorkerDao workerDao) {
        return new AppRepository(communicator, workerDao);
    }
}