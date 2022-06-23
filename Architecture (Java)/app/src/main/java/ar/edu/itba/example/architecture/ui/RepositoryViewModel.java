package ar.edu.itba.example.architecture.ui;

import androidx.lifecycle.ViewModel;

public class RepositoryViewModel<T> extends ViewModel {
    protected T repository;

    public RepositoryViewModel(T repository) {
        this.repository = repository;
    }
}
