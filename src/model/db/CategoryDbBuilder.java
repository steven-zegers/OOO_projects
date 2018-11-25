package model.db;

import model.domain.Category;
import model.domain.CategoryFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDbBuilder {
    private String path;

    public CategoryDbBuilder(String path) {
        this.setPath(path);
    }

    public List<Category> readCategories() {
        List<Category> categories = new ArrayList<>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(this.getPath());
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            CategoryFactory factory = new CategoryFactory();
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineString = line.split(": ");
                if (lineString.length == 2) {
                    Category category = factory.createCategory(lineString[0], lineString[1], null);
                    categories.add(category);
                } else {
                    boolean found = false;
                    for (Category category : categories) {
                        if (category.getTitle().equals(lineString[2])) {
                            Category newCategory = factory.createCategory(lineString[0], lineString[1], category);
                            categories.add(newCategory);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        throw new DbException("There is no such super category");
                    }
                }

            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
