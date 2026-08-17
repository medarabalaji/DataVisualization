package com.ramedis.datavisualization;

public class DataStructureSelect {
    public static DataStructure<?> create(String type){
        return switch (type){
            case "ArrayList" -> new ArrayListModel<>();
            case "LinkedList" -> new LinkedListModel<>();
            case "HashSet" -> new HashSetModel<>();
            case "TreeSet" -> new TreeSetModel<>();
            default -> throw new IllegalArgumentException(
                    "Unsupported data structure: "+type
            );
        };
    }
}
