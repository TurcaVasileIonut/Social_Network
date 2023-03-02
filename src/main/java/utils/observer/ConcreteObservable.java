package utils.observer;

import utils.events.Event;

import java.util.HashSet;
import java.util.Set;

public class ConcreteObservable<E extends Event, ID> implements Observable<E> {

    protected Set<Observer<E>> observers = new HashSet<>();

    @Override
    public void addObserver(Observer<E> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<E> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(E event) {
        observers.forEach(observer -> observer.update(event));
    }
}
