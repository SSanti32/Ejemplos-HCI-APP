package ar.edu.itba.example.viewmodel;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private int counter;

    public int getCounter() {
        return counter;
    }

    public int incrementCounter() {
            return ++counter;
        }
}
