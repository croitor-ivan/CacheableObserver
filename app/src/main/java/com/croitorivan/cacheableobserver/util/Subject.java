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

    private Map<EventContext, List<Event>> events = new HashMap<>();
    private List<Observer> observers = new ArrayList<>();
    private String context;

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
        final EventContext eventKey = e.getEventKey();
        if (eventKey.isOfActiveContext(context)) {
            List<Event> eventsList = events.get(eventKey);
            if (eventsList == null) {
                eventsList = new ArrayList<>();
                events.put(eventKey, eventsList);
            }
            eventsList.add(e);
            notifyScheduledEvents(eventKey);
        }
    }

    private void fireCachedEvents(List<EventContext> observerKeys) {
        if (observerKeys != null && !observerKeys.isEmpty()) {
            for (EventContext key : observerKeys) {
                notifyScheduledEvents(key);
            }
        }
    }

    private void addNewObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    private void notifyScheduledEvents(EventContext eventContext) {
        final List<Event> eventsForObserver = this.events.get(eventContext);
        if (eventsForObserver != null && !eventsForObserver.isEmpty()) {
            final Observer observer = getObserver(eventContext);
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

    private Observer getObserver(EventContext key) {
        Observer observer = null;
        for (Observer next : observers) {
            final List<EventContext> observerKeys = next.getObserverKeys();
            if (observerKeys.contains(key)) {
                observer = next;
                break;
            }
        }
        return observer;
    }

    /**
     * Used to change the active context for event.
     * Also removes all the pending events.
     *
     * @param context the new context to use.
     */
    public void setContext(String context) {
        this.context = context;
        clearEventsForOldContext();
    }

    private void clearEventsForOldContext() {
        final Iterator<Map.Entry<EventContext, List<Event>>> iterator = events.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<EventContext, List<Event>> entry = iterator.next();
            if (!entry.getKey().isOfActiveContext(context)) {
                iterator.remove();
            }
        }
    }
}
