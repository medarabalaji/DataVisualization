package com.ramedis.datavisualization;

import java.util.Map;

public interface MapDataStructure<K,V>{
    void put(K key, V value);

    V get(K key);

    boolean removeKey(K key);

    boolean removeValue(V value);

    boolean containsKey(K key);

    void clear();

    int size();


}
