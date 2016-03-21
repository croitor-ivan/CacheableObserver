package com.croitorivan.cacheableobserver.observer;

import com.croitorivan.cacheableobserver.event.Event;
import com.croitorivan.cacheableobserver.util.EventContext;

import java.util.List;


/**
 * A class can implement the Observer interface when it wants to be informed of changes in observable objects.
 */
public interface Observer {
    /**
     * This method is called whenever the observable object produces new {@link Event}
     * @param e the new  instance of {@link Event}
     */
    void onEvent(Event e);

    /**
     * Returns observer identifier
     * @return {@link List} of {@link EventContext} that represents events identifiers of observer
     */
    List<EventContext> getObserverKeys();


    /**
     * Called when we need to define an observer as the <b>MAIN</b> observer for a specific key.
     * If {@link com.croitorivan.cacheableobserver.util.Subject} finds a <b>MAIN</b> observer,
     * the event that is registered under the {#code key} will be removed from the waiting queue.
     * If no <b>MAIN</b> observer is found, the event will be propagated to that observer but will remain
     * in the queue.
     *
     * @param key an {@link EventContext} we should check against if {@link Observer} is <b>MAIN</b>.
     *
     * @return <b>true</b> if observer is <b>MAIN</b>.
     */
    boolean isMainObserverForKey(EventContext key);
}
