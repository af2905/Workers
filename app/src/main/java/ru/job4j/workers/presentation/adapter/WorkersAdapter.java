package ru.job4j.workers.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import ru.job4j.workers.R;
import ru.job4j.workers.presentation.base.BaseAdapter;
import ru.job4j.workers.presentation.item.ISpecialtyAndWorkerClickListener;
import ru.job4j.workers.presentation.item.WorkerViewHolder;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkersAdapter extends BaseAdapter<WorkerViewHolder, Worker, ISpecialtyAndWorkerClickListener> {
    private List<Worker> workers;

    public WorkersAdapter(List<Worker> list) {
        super(list);
        this.workers = list;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(workers.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }
}
