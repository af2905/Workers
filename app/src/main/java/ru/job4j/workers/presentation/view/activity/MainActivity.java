package ru.job4j.workers.presentation.view.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import ru.job4j.workers.R;
import ru.job4j.workers.di.component.ViewModelComponent;
import ru.job4j.workers.domain.WorkersViewModel;
import ru.job4j.workers.presentation.base.BaseActivity;
import ru.job4j.workers.presentation.view.fragments.DetailFragment;
import ru.job4j.workers.presentation.view.fragments.WorkersFragment;

public class MainActivity extends BaseActivity implements WorkersFragment.Callback {
    private static final String SPECIALTY_FRAGMENT = "specialtyFragment";
    private static final String WORKERS_FRAGMENT = "workersFragment";
    private static final String DETAIL_FRAGMENT = "detailFragment";

    @Inject
    protected WorkersViewModel workersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.content) == null) {
            fm.beginTransaction()
                    .replace(R.id.content, new WorkersFragment(), WORKERS_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    protected void injectDependency(ViewModelComponent component) {
        component.inject(this);
    }

    @Override
    public void openDetailClick(int id) {
        MutableLiveData<Integer> liveDataWorkerId = new MutableLiveData<>();
        liveDataWorkerId.setValue(id);
        workersViewModel.setLiveDataWorkerId(liveDataWorkerId);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new DetailFragment(), DETAIL_FRAGMENT)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}