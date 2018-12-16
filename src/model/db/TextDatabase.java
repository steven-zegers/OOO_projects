package model.db;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class TextDatabase<T> implements Database<T> {
    private List<T> items;
    private String path;

    public TextDatabase(String path) {
        this.path = path;
        this.items = new ArrayList<>();
        this.items = readItems(readFile());
    }

    @Override
    public abstract T getItem(String item);

    @Override
    public List<String[]> readFile() {
        String line;
        List<String[]> linesInFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(this.path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineString = line.split(": ");
                linesInFile.add(lineString);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesInFile;
    }

    @Override
    public void deleteItem(String title) {
        try {
            T question = this.getItem(title);
            String stringToDelete = question.toString();
            File inputFile = new File(path);
            File tempFile = new File("src/model/db/temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int currentLineNumber = 0;
            while ((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(stringToDelete)) {
                    continue;
                } else {
                    if (currentLineNumber != 0) {
                        writer.newLine();
                    }
                    currentLineNumber++;
                    writer.write(currentLine);
                }
            }
            writer.close();
            reader.close();
            boolean delete = inputFile.delete();
            boolean succesful = tempFile.renameTo(inputFile);
            items.remove(this.getItem(title));
        } catch(DbException e) {
            throw new DbException(e.getMessage());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<T> getItems() {
        return items;
    }

    @Override
    public void addItem(T item) {
        if (titleAlreadyExists(item)) throw new DbException("There is already an item that has this title.");
        items.add(item);
        try {
            String string = item.toString();
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(string);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract boolean titleAlreadyExists(T item);

    @Override
    public abstract List<T> readItems(List<String[]> text);

    @Override
    public abstract List<String> getTitles();
}
