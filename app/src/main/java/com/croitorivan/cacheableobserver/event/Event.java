package com.croitorivan.cacheableobserver.event;

import android.os.Parcelable;

import com.croitorivan.cacheableobserver.observer.Observer;
import com.croitorivan.cacheableobserver.util.EventContext;


/**
 * A class can implement this interface to interact between {@link com.croitorivan.cacheableobserver.util.Subject}
 * and {@link Observer}
 */
public interface Event<T> extends Parcelable {
    /**
     * Returns data object
     * @return instance of T
     */
    T getData();

    /**
     * Returns {@link String} that represents event key which used for identify {@link Event}
     * @return {@link String}
     */
    EventContext getEventKey();

}
