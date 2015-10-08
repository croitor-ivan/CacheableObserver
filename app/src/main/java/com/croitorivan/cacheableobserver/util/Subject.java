package com.croitorivan.cacheableobserver.util;


import com.croitorivan.cacheableobserver.event.Event;
import com.croitorivan.cacheableobserver.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Subject is a class that registers and unregisters {@link Observer} and takes care about
 * caches  {@link Event} and notifies {@link Observer} when {@link Event} appears
 */
public class Subject {

    private Map<String, List<Event>> events = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();

    public Subject() {
    }

    /**
     * Registers {@link Observer} and notifies all cached {@link Event} if they exists
     *
     * @param observer observer instance
     */
    public void registerObserver(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Observer must be not null");
        }
        addNewObserver(observer);
        fireCachedEvents(observer.getObserverKeys());
    }


    /**
     * Unregisters {@link Observer}
     *
     * @param observer observer instance
     */
    public void unregisterObservers(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Observer must be not null");
        }
        observers.remove(observer);
    }


    /**
     * Caches {@link Event} and notifies {@link Observer}
     *
     * @param e event instance
     */
    public void onNewEvent(Event e) {
        List<Event> eventsList = events.get(e.getEventKey());
        if (eventsList == null) {
            eventsList = new ArrayList<>();
        }
        eventsList.add(e);
        events.put(e.getEventKey(), eventsList);
        notifyScheduledEvents(e.getEventKey());
    }

    private void fireCachedEvents(List<String> observerKeys) {
        if (observerKeys != null && !observerKeys.isEmpty()) {
            for (String key : observerKeys) {
                notifyScheduledEvents(key);
            }
        }
    }

    private void addNewObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    private void notifyScheduledEvents(String observerKey) {
        final List<Event> eventsForObserver = this.events.get(observerKey);
        if (eventsForObserver != null && !eventsForObserver.isEmpty()) {
            final Observer observer = getObserver(observerKey);
            if (observer != null) {
                final Iterator<Event> iterator = eventsForObserver.iterator();
                while (iterator.hasNext()) {
                    final Event event = iterator.next();
                    observer.onEvent(event);
                    iterator.remove();
                }
            }
        }
    }

    private Observer getObserver(String key) {
        Observer observer = null;
        for (Observer next : observers) {
            final List<String> observerKeys = next.getObserverKeys();
            if (observerKeys.contains(key)) {
                observer = next;
                break;
            }
        }
        return observer;
    }
}
