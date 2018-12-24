package model.db;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
import java.util.List;

public interface Database<T> {
    List<String[]> readFile();
    List<String> getTitles();
    List<T> readItems(List<String[]> text);
    void addItem(T item);
    List<T> getItems();
    void createDatabase();
    T getItem(String item);
    void deleteItem(String title);
}
