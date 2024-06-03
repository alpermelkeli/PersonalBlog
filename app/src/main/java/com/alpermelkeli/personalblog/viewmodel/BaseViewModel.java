package com.alpermelkeli.personalblog.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public abstract class BaseViewModel<T, R> extends ViewModel {
    protected R repository;
    protected MutableLiveData<List<T>> liveData;

    public BaseViewModel(R repository) {
        this.repository = repository;
        this.liveData = new MutableLiveData<>();
    }

    public LiveData<List<T>> getLiveData() {
        return liveData;
    }

    protected abstract void loadItems();
}
