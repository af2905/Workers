package ru.job4j.workers.presentation.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import ru.job4j.workers.R;
import ru.job4j.workers.di.component.ViewModelComponent;
import ru.job4j.workers.domain.WorkersViewModel;
import ru.job4j.workers.presentation.adapter.WorkerAdapter;
import ru.job4j.workers.presentation.base.BaseFragment;
import ru.job4j.workers.presentation.item.IWorkerItemClickListener;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkersFragment extends BaseFragment {
    @Inject
    protected WorkersViewModel workersViewModel;
    private RecyclerView recycler;
    private LinearLayoutManager manager;
    protected Callback callback;
    private IWorkerItemClickListener<Worker> clickListener = worker -> openItemDetail(worker.getId());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_row, container, false);
        recycler = view.findViewById(R.id.items_row);
        manager = new LinearLayoutManager(view.getContext());
        workersViewModel.getAllWorkers();
        workersViewModel.getLiveDataItems().observe(this, this::initRecyclerView);
        return view;
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    private void initRecyclerView(List<Worker> workers) {
        WorkerAdapter workerAdapter = new WorkerAdapter(workers);
        workerAdapter.setItemClickListener(clickListener);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(workerAdapter);
    }

    private void openItemDetail(int id) {
        callback.openDetailClick(id);
    }

    public interface Callback {
        void openDetailClick(int id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.callback = (Callback) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callback = null;
    }
}
