package com.ramedis.datavisualization;

import java.util.ArrayList;
import java.util.List;

public class ArrayListModel<T> implements DataStructure<T> {
    private final List<T> data = new ArrayList<>();
    @Override
    public boolean add(T value){
        data.add(value);
        return false;
    }
    @Override
    public boolean remove(T value){
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
        return new ArrayList<>(data);
    }
    public T get(int index){
        return data.get(index);
    }
}
