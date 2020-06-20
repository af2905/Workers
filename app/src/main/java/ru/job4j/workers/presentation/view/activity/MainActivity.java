package ru.job4j.workers.presentation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import ru.job4j.workers.R;
import ru.job4j.workers.di.component.ViewModelComponent;
import ru.job4j.workers.domain.ApplicationViewModel;
import ru.job4j.workers.presentation.base.BaseActivity;
import ru.job4j.workers.presentation.view.fragments.DetailFragment;
import ru.job4j.workers.presentation.view.fragments.SpecialtiesFragment;
import ru.job4j.workers.presentation.view.fragments.WorkersFragment;

public class MainActivity extends BaseActivity
        implements WorkersFragment.CallbackForDetail, SpecialtiesFragment.CallbackForWorkers {
    private static final String SPECIALTIES_FRAGMENT = "specialtyFragment";
    private static final String WORKERS_FRAGMENT = "workersFragment";
    private static final String DETAIL_FRAGMENT = "detailFragment";
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE
            = Executors.newSingleThreadScheduledExecutor();

    @Inject
    protected ApplicationViewModel applicationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationViewModel.getAllWorkers();
        setContentView(R.layout.activity_main);

        if (applicationViewModel.getLiveDataSpecialties().getValue() != null) {
            loadFragment(savedInstanceState);
        } else {
            Runnable runnable = () -> loadFragment(savedInstanceState);
            SCHEDULED_EXECUTOR_SERVICE.schedule(runnable, 2, TimeUnit.SECONDS);
        }
    }

    private void loadFragment(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, new SpecialtiesFragment(), SPECIALTIES_FRAGMENT)
                    .addToBackStack(null)
                    .commit();
            ProgressBar progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.INVISIBLE);
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
        applicationViewModel.setLiveDataWorkerId(liveDataWorkerId);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new DetailFragment(), DETAIL_FRAGMENT)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void openWorkersListClick(int id) {
        MutableLiveData<Integer> liveDataSpecialtyId = new MutableLiveData<>();
        liveDataSpecialtyId.setValue(id);
        applicationViewModel.setLiveDataSpecialtyId(liveDataSpecialtyId);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, new WorkersFragment(), WORKERS_FRAGMENT)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}