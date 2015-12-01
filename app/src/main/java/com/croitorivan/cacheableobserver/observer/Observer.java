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
     * @return {@link List} of {@link String} that represents events identifiers of observer
     */
    List<EventContext> getObserverKeys();


}
