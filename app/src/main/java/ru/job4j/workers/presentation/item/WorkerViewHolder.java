package ru.job4j.workers.presentation.item;

import android.view.View;
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
        TextView firstName, lastName, birthday, specialty;
        firstName = itemView.findViewById(R.id.first_name);
        lastName = itemView.findViewById(R.id.last_name);
        birthday = itemView.findViewById(R.id.birthday);
        specialty = itemView.findViewById(R.id.specialty);

        firstName.setText(worker.getFirstName());
        lastName.setText(worker.getLastName());
        birthday.setText(worker.getBirthday());
        specialty.setText("");

        itemView.setOnClickListener(openDetail);
    }
}
