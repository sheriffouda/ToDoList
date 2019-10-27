package FileHandler;

import ToDoList.Task;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    /**
     * The constructor
     */
    public FileManager() {
    }

    /**
     * To write tasks to a CSV file
     * @param fileName
     * @param tasks
     * @return a boolean with value of "true" in case task is added successfully or "false" in case task could be saved.
     */
    public static boolean writeToFile(String fileName, ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for (Task task : tasks) {
                String line = String.join(",", task.getTitle(), task.getProject(), task.getDuedate().toString(), task.getStatus(),"\n");
                writer.write(line);
            }
            writer.close();
            System.out.println("\nYour task(s) have been saved successfully");
            return true;
        } catch (IOException e) {
            System.out.println("Couldn't write to file");
            return false;
        }
    }

    /**
     * To read an object from a file
     *
     * */

    public static ArrayList<Task> readFromFile(String fileName) {
        try {
            BufferedReader reader
                    = new BufferedReader(new FileReader(fileName));
            ArrayList<Task> tasks = reader.lines().map(line -> {
                String[] values = line.split(",");
                LocalDate localDate = null;
                try {
                    // parse the string to localDate format with validating the input and then the old date if its entered
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    localDate = LocalDate.parse(values[2], formatter);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date (yyyy-MM-dd) \nYour task has been rejected!! \nplease make sure to enter a valid due date ");
                }
                //reading lines from the CSV file
                Task retrievedTask = new Task(values[0], values[1], localDate);
                return retrievedTask;
            }).collect(Collectors.toCollection(ArrayList<Task>::new));
            return tasks;
        } catch (IOException e) {
            System.out.println("Couldn't read from file");
            return new ArrayList<Task>();
        }
    }

    }


