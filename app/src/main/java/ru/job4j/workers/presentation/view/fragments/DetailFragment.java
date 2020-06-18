package ru.job4j.workers.presentation.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import ru.job4j.workers.R;
import ru.job4j.workers.di.component.ViewModelComponent;
import ru.job4j.workers.domain.ApplicationViewModel;
import ru.job4j.workers.presentation.base.BaseFragment;
import ru.job4j.workers.repository.database.entity.Worker;
import ru.job4j.workers.repository.database.pojo.Specialty;

public class DetailFragment extends BaseFragment {
    @Inject
    protected ApplicationViewModel applicationViewModel;
    private ImageView avatar;
    private TextView firstName;
    private TextView lastName;
    private TextView birthday;
    private TextView specialty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        findViews(view);
        showDetailInfo();
        return view;
    }

    private void findViews(View view) {
        avatar = view.findViewById(R.id.avatar);
        firstName = view.findViewById(R.id.first_name);
        lastName = view.findViewById(R.id.last_name);
        birthday = view.findViewById(R.id.birthday);
        specialty = view.findViewById(R.id.specialty);
    }

    private void showDetailInfo() {
        Worker worker = applicationViewModel.getLiveDataDetail();
        List<Specialty> specialties = worker.getSpecialty();
        firstName.setText(worker.getFirstName());
        lastName.setText(worker.getLastName());
        birthday.setText(worker.getBirthday());
        for (Specialty s : specialties) {
            specialty.setText(String.format("%s\n", s.getName()));
        }
        String url = worker.getAvatarUrl();
        int placeholder = R.drawable.ic_baseline_photo_24;
        int error = R.drawable.ic_baseline_error_outline_24;
        applicationViewModel.loadImgFromNet(url, placeholder, error, avatar);
    }

    @Override
    public void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }
}
