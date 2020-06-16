package ru.job4j.workers.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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

public class WorkersFragment extends Fragment {
    @Inject
    protected WorkersViewModel workersViewModel;
    private RecyclerView recycler;
    private LinearLayoutManager manager;
    private AppCompatActivity activity;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        createDaggerDependencies();
        View view = inflater.inflate(R.layout.fragment_row, container, false);
        recycler = view.findViewById(R.id.items_row);
        manager = new LinearLayoutManager(view.getContext());
        workersViewModel.getAllWorkers();
        workersViewModel.getLiveDataItems().observe(this, this::initRecyclerView);
        return view;
    }

    private void createDaggerDependencies() {
        injectDependency(((App) (activity).getApplication()).getViewModelComponent());
    }

    void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    private void initRecyclerView(List<Worker> workers) {
        WorkerAdapter workerAdapter = new WorkerAdapter(workers);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(workerAdapter);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        activity = (AppCompatActivity) context;
        super.onAttach(context);
    }
}
