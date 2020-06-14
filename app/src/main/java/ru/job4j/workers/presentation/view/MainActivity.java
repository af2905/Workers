package ru.job4j.workers.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import ru.job4j.workers.App;
import ru.job4j.workers.R;
import ru.job4j.workers.di.component.ViewModelComponent;
import ru.job4j.workers.domain.WorkersViewModel;
import ru.job4j.workers.presentation.adapter.WorkerAdapter;
import ru.job4j.workers.repository.database.entity.Worker;

public class MainActivity extends AppCompatActivity {

    @Inject
    protected WorkersViewModel workersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workers_recyclerview);
        createDaggerDependencies();
        workersViewModel.getAllWorkers();
        workersViewModel.getLiveDataItems().observe(this, this::initRecyclerView);
    }

    private void createDaggerDependencies() {
        injectDependency(((App) getApplication()).getViewModelComponent());
    }

    void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    private void initRecyclerView(List<Worker> workers) {
        RecyclerView recyclerView = findViewById(R.id.workers_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        WorkerAdapter workerAdapter = new WorkerAdapter(this, workers);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(workerAdapter);
    }
}