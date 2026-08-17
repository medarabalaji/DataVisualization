package com.ramedis.datavisualization;

import java.util.LinkedList;
import java.util.List;

public class LinkedListModel<T> implements DataStructure<T> {
    private final LinkedList<T> data = new LinkedList<>();
    @Override
    public boolean add(T value) {
        data.add(value);
        return false;
    }

    @Override
    public boolean remove(T value) {
        return data.remove(value);
    }

    @Override
    public boolean contains(T value) {
        return data.contains(value);
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public int size() {
        return data.size();
    }


    public List<T> getValues() {
        return new LinkedList<>(data);
    }

    public T get(int index){
        return data.get(index);
    }


    public boolean isEmpty() {
        return false;
    }
}
