package com.ramedis.datavisualization;

import java.util.HashSet;
import java.util.Set;

public class HashSetModel<T> implements DataStructure<T> {
    private final Set<T> hashSet = new HashSet<>();

    @Override
    public boolean add(T value) {
        return hashSet.add(value);
    }

    @Override
    public boolean remove(T value) {
        return hashSet.remove(value);
    }

    @Override
    public boolean contains(T value) {
        return hashSet.contains(value);
    }

    @Override
    public void clear() {
        hashSet.clear();
    }

    @Override
    public int size() {
        return hashSet.size();
    }


    public Set<T> getValues() {
        return new HashSet<>(hashSet);
    }
}
