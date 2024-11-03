// Observable.java
package com.byteme.interfaces;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {
    private final List<Observer<T>> observers = new ArrayList<>();

    public void addObserver(Observer<T> observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer<T> observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(T event) {
        for (Observer<T> observer : observers) {
            observer.update(event);
        }
    }
}