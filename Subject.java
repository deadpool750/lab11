package org.example;

import org.example.Observer;

public interface Subject {
    void registerObserver(Observer o);
    void  removeObserver(Observer o);

    void notifyObserver();
}