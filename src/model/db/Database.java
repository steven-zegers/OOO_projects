package model.db;

import java.util.List;

public interface Database<T> {
    List<String[]> readFile();
    List<T> readItems(List<String[]> text);
    void addItem(T item);
    List<T> getItems();
}
