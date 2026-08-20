package com.ramedis.datavisualization;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetModel<T extends Comparable<T>> implements DataStructure<T> {
    private final Set<T> treeSet = new TreeSet<>();

    @Override
    public boolean add(T value) {
        return treeSet.add(value);
    }

    @Override
    public boolean remove(T value) {
        return treeSet.remove(value);
    }

    @Override
    public boolean contains(T value) {
        return treeSet.contains(value);
    }

    @Override
    public void clear() {
        treeSet.clear();
    }

    @Override
    public int size() {
        return treeSet.size();
    }


    public Set<T> getValues() {
        return new TreeSet<>(treeSet);
    }
}
