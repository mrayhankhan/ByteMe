// Observer.java
package com.byteme.interfaces;

public interface Observer<T> {
    void update(T event);
}