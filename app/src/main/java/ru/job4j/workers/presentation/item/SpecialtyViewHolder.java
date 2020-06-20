package ru.job4j.workers.presentation.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.job4j.workers.R;
import ru.job4j.workers.repository.database.entity.Specialty;

public class SpecialtyViewHolder extends RecyclerView.ViewHolder {
    private View itemView;
    private Specialty specialty;
    private ISpecialtyAndWorkerClickListener<Specialty> clickListener;
    private View.OnClickListener openDetail = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            clickListener.openDetailInfo(specialty);
        }
    };

    public SpecialtyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    public void bind(Specialty specialty, ISpecialtyAndWorkerClickListener<Specialty> clickListener) {
        this.specialty = specialty;
        this.clickListener = clickListener;
        setupItem();
    }

    private void setupItem() {
        ImageView smallIcon = itemView.findViewById(R.id.small_icon);
        TextView name = itemView.findViewById(R.id.item);
        smallIcon.setImageResource(R.drawable.ic_baseline_format_list_bulleted_24);
        name.setText(String.format("%sS", specialty.getName().toUpperCase()));
        itemView.setOnClickListener(openDetail);
    }
}
