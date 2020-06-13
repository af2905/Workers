package ru.job4j.workers.presentation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import ru.job4j.workers.R;
import ru.job4j.workers.presentation.base.BaseAdapter;
import ru.job4j.workers.presentation.item.IWorkerItemClickListener;
import ru.job4j.workers.presentation.item.WorkerViewHolder;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkerAdapter extends BaseAdapter<WorkerViewHolder, Worker, IWorkerItemClickListener> {
    private List<Worker> workers;
    private Context context;

    public WorkerAdapter(Context context, List<Worker> list) {
        super(context, list);
        this.workers = list;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkerViewHolder(LayoutInflater.from(context).inflate(R.layout.worker_template, parent, false));
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
