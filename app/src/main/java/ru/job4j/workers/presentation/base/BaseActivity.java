package ru.job4j.workers.presentation.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.job4j.workers.App;
import ru.job4j.workers.di.component.ViewModelComponent;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        createDaggerDependencies();
        super.onCreate(savedInstanceState);
    }

    private void createDaggerDependencies() {
        injectDependency(((App) getApplication()).getViewModelComponent());
    }

    protected abstract void injectDependency(ViewModelComponent component);
}
