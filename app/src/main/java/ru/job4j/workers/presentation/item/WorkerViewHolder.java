package ru.job4j.workers.presentation.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.job4j.workers.R;
import ru.job4j.workers.repository.database.entity.Worker;

public class WorkerViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private Worker worker;
    private IWorkerItemClickListener<Worker> clickListener;
    private View.OnClickListener openDetail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickListener.openDetail(worker);
        }
    };

    public WorkerViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bind(Worker worker, IWorkerItemClickListener<Worker> clickListener) {
        this.worker = worker;
        this.clickListener = clickListener;
        setupItem();
    }

    private void setupItem() {
        ImageView smallIcon = itemView.findViewById(R.id.small_icon);
        TextView name = itemView.findViewById(R.id.item);
        smallIcon.setImageResource(R.drawable.ic_baseline_person_outline_24);
        name.setText(String.format("%s %s", worker.getFirstName(), worker.getLastName()));
        itemView.setOnClickListener(openDetail);
    }
}
