package com.ramedis.datavisualization;

import java.util.HashMap;
import java.util.Map;

public class HashMapModel<K,V> implements MapDataStructure<K,V> {
    private final Map<K,V> hashMap = new HashMap<>();

    @Override
    public void put(K key, V value) {
        hashMap.put(key,value);
    }

    @Override
    public V get(K key) {
        return hashMap.get(key);
    }

    @Override
    public boolean remove(K key) {
        if(!hashMap.containsKey(key)){
            return false;
        }
        hashMap.remove(key);
        return true;
    }

    @Override
    public boolean containsKey(K key) {
        return hashMap.containsKey(key);
    }

    @Override
    public void clear() {
        hashMap.clear();
    }

    @Override
    public int size() {
        return hashMap.size();
    }


    public Map<K, V> getValues() {
        return new HashMap<>(hashMap);
    }
}
