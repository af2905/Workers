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
import ru.job4j.workers.domain.ApplicationViewModel;
import ru.job4j.workers.presentation.adapter.WorkersAdapter;
import ru.job4j.workers.presentation.base.BaseFragment;
import ru.job4j.workers.presentation.item.DividerItemDecoration;
import ru.job4j.workers.presentation.item.ISpecialtyAndWorkerClickListener;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkersFragment extends BaseFragment {
    @Inject
    protected ApplicationViewModel applicationViewModel;
    private RecyclerView recycler;
    private LinearLayoutManager manager;
    protected CallbackForDetail callback;
    private ISpecialtyAndWorkerClickListener<Worker> clickListener = worker -> openItemDetail(worker.getId());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_row, container, false);
        recycler = view.findViewById(R.id.items_row);
        manager = new LinearLayoutManager(view.getContext());
        applicationViewModel.getSelectedWorkers();
        applicationViewModel.getLiveDataSelectedWorkers().observe(this, this::initRecyclerView);
        return view;
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    private void initRecyclerView(List<Worker> workers) {
        WorkersAdapter workersAdapter = new WorkersAdapter(workers);
        workersAdapter.setItemClickListener(clickListener);
        RecyclerView.ItemDecoration decoration
                = new DividerItemDecoration(8, 16);
        recycler.addItemDecoration(decoration);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(workersAdapter);
    }

    private void openItemDetail(int id) {
        callback.openDetailClick(id);
    }

    public interface CallbackForDetail {
        void openDetailClick(int id);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.callback = (CallbackForDetail) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callback = null;
    }
}
