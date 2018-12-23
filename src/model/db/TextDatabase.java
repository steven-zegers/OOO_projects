package model.db;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Thibault Stroobants
 * @author Steven Zegers
 * @author Wout De Boeck
 */
public abstract class TextDatabase<T> implements Database<T> {
    private List<T> items;
    private String path;

    public TextDatabase(String path) {
        this.path = path;
        createLocalFile();
        this.items = new ArrayList<>();
        this.items = readItems(readFile());
    }

    private void createLocalFile() {
        /*
        As we also have to be able to use the application when its a jar file we will have to make a copy of the
        standard database questions and categories that reside outside of the jar (as they won't be editable
        otherwise). For this reason upon first execution of the application we will make a copy of the text files on the
        home directory in a new directory ZelfEvaluatieApp.
         */
        File localFile = new File(File.separator + "ZelfEvaluatieApp" + File.separator + path);
        try {
            boolean createDir = localFile.getParentFile().mkdirs();
            boolean success = localFile.createNewFile();
            System.out.println(createDir);
            System.out.println(success);
            if(success) {
                String line;
                try {
                    InputStream is = getClass().getResourceAsStream(this.path);
                    InputStreamReader fileReader = new InputStreamReader(is);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    FileWriter fileWriter = new FileWriter(localFile.getPath());
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    int numberOfLine = 0;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (numberOfLine != 0) {
                            bufferedWriter.newLine();
                        }
                        numberOfLine++;
                        bufferedWriter.write(line);
                    }
                    bufferedReader.close();
                    fileReader.close();
                    bufferedWriter.close();
                    fileWriter.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            this.path = localFile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public abstract T getItem(String item);

    @Override
    public List<String[]> readFile() {
        String line;
        List<String[]> linesInFile = new ArrayList<>();
        try {
            //InputStream is = getClass().getResourceAsStream(this.path);
            //InputStreamReader fileReader = new InputStreamReader(is);
            FileReader fileReader = new FileReader(this.path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineString = line.split(": ");
                linesInFile.add(lineString);
            }
            bufferedReader.close();
            fileReader.close();
            //is.close();
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
            //File tempFile = new File("src/model/db/temp.txt");
            File tempFile = new File(File.separator + "ZelfEvaluatieApp" + File.separator + "temp.txt");

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
            //FileWriter fileWriter = new FileWriter("src/model/db/" + this.path, true);
            FileWriter fileWriter = new FileWriter(this.path, true);
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
