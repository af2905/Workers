package ru.job4j.workers.di.component;

import dagger.Component;
import ru.job4j.workers.di.module.DaoModule;
import ru.job4j.workers.repository.database.dao.SpecialtyDao;
import ru.job4j.workers.repository.database.dao.WorkerDao;

@Component(modules = {DaoModule.class})
public interface DaoComponent {
    WorkerDao getWorkerDao();

    SpecialtyDao getSpecialtyDao();
}
