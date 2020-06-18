package ru.job4j.workers.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import ru.job4j.workers.R;
import ru.job4j.workers.presentation.base.BaseAdapter;
import ru.job4j.workers.presentation.item.ISpecialtyAndWorkerClickListener;
import ru.job4j.workers.presentation.item.SpecialtyViewHolder;
import ru.job4j.workers.repository.database.pojo.Specialty;

public class SpecialtiesAdapter extends BaseAdapter<SpecialtyViewHolder, Specialty, ISpecialtyAndWorkerClickListener> {
    private List<Specialty> specialties;

    public SpecialtiesAdapter(List<Specialty> list) {
        super(list);
        this.specialties = list;
    }


    @NonNull
    @Override
    public SpecialtyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpecialtyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialtyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.bind(specialties.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }
}
