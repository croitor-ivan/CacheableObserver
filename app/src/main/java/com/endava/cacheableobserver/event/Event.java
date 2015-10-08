package com.endava.cacheableobserver.event;

import android.os.Parcelable;

import com.endava.cacheableobserver.observer.Observer;
import com.endava.cacheableobserver.util.Subject;

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
