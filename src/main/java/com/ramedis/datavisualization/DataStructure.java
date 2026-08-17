package com.ramedis.datavisualization;

public interface DataStructure<T> {
    boolean add(T value);
    boolean remove(T value);
    boolean contains(T value);
    void clear();
    int size();

}
