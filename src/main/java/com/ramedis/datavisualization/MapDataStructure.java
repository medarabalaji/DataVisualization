package com.ramedis.datavisualization;

import java.util.Map;

public interface MapDataStructure<K,V>{
    void put(K key, V value);

    V get(K key);

    boolean remove(K key);

    boolean containsKey(K key);

    void clear();

    int size();


}
