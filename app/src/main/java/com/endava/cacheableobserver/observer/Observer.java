package com.endava.cacheableobserver.observer;

import java.util.List;

import com.endava.cacheableobserver.event.Event;

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
    List<String> getObserverKeys();


}
