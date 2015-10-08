package com.croitorivan.cacheableobserver.event;

import android.os.Parcelable;

import com.croitorivan.cacheableobserver.observer.Observer;


/**
 * A class can implement this interface to interact between {@link Subject}
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
    String getEventKey();

}
