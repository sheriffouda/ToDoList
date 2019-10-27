package ToDoList;

import FileHandler.FileManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Tasks {

    private ArrayList<Task> tasks;
    private int totalTasks;
    Scanner scanner;
    ArrayList<Task> retrievedTasks= FileManager.readFromFile("/Users/sheriffouda/Desktop/ToDoList/src/main/java/ToDoList/taskfile.csv");

    /**
     * The constructor for creating a todo list object.
     */

    public Tasks() {
        // Loading all tasks that have been created previously from the file where they were stored.
        tasks = retrievedTasks;
        scanner = new Scanner(System.in);
    }

    /**
     * A method for adding new tasks created by a user to a list
     */
    public void addTask() {
        Task task = createTask();
        task.getDetails();
        tasks.add(task);
    }

    /**
     *  A method for getting the task details.
     * @return An arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        // Creating a new task object.
        Task taskItem = new Task();
        taskItem.styling();
        for (Task task : tasks) {
            task.getDetails();
        }
        return tasks;
    }

    /**
     * A method for reading and parsing the date entered by a user to a date formate.
     * @param date
     * @return date in date format
     */
    public LocalDate dateReader(String date) {
        LocalDate localDate;
        try {
            // parse the string to localDate format with validating the input and then the old date if its entered
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(date, formatter);
            LocalDate today = LocalDate.now();
            //Checking the due date has not passed.
            if (localDate.isEqual(today)||localDate.isAfter(today)) {
                return localDate;
            }
            else {
                System.out.println("The date you entered has already passed");
                createTask();
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date (yyyy-MM-dd) \nYour task has been rejected!! \nplease make sure to enter a valid due date ");
            return null;
        }
        return localDate;
    }

    /**
     * A method for reading the user input
     *
     * @param message to inform user what inout should be entered.
     * @return user input
     */
    public String getUserInput(String message) {
        System.out.println(message);
        // storing user's input
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * A method for creating a task using user's input
     *
     * @return new task with three parameters
     */
    public Task createTask() {
        // Task title to be entered by user
        String taskTitle = getUserInput("Enter your task title >>");
        // Rejecting empty entries.
        if (taskTitle.isEmpty()) {
            System.out.println("Your entry is not valid");
            createTask();
        }
        // Project name entered by user
        String taskProject = getUserInput("Enter project name >>");
        // Rejecting empty entries.
        if (taskProject.isEmpty()) {
            System.out.println("Your entry is not valid");
            createTask();
        }
        // Due dare entered by user.
        String dueDate = getUserInput("Enter the due date of your task (YYYY-MM-DD) >>");
        LocalDate date = dateReader(dueDate);
        // Wrong date will be rejected and user will return back to main menu.
        if (date == null) {
            Menu.makeYourChoice();
        }
        // New task has been created.
        return new Task(taskTitle, taskProject, dateReader(dueDate));
    }

    /**
     * A method for getting number of tasks which has been created by the user
     *
     * @return total number of tasks created by a user.
     */
    public int getNumberOfTasks() {

        totalTasks = tasks.size();
        return totalTasks;
    }

    /**
     * A method for counting number of tasks based on its current status.
     * @param currentStatus
     * @return index which is the current number of tasks for each status specified in the parameter.
     */
    public int tasksCurrentStatusCounter(String currentStatus) {
        int index = 0;
        for (Task task : tasks) {
            if (task.getStatus().equals(currentStatus)) {
                index++;
            }
        }
        return index;
    }

    /**
     * A method to Show all tasks in the list.
      */
    public void showAllTasks() {
        tasks.stream()
                .forEach(newTask -> System.out.println(newTask.getDetails()));
    }

    /**
     * A method for searching tasks by name
     *
     * @param taskName to be searched in the current list
     * @return the task or null if task is not found.
     */
    public Task searchTasksByName(String taskName) {
//        tasks.stream().filter(newTask -> newTask.getTitle()==taskName);
//        Task newTask = new Task("", "", null);
        int index = 0;
        for (index = 0; index < tasks.size(); index++) {
            Task newTask = tasks.get(index);
            if (newTask.getTitle().equals(taskName))
                return newTask;
        }
        System.out.println("Task is not found, make sure you have typed the right task");

        return null;
    }
    public static void sortByName(ArrayList<Task> tasksTobeSorted) {

        {
            Collections.sort(tasksTobeSorted, new Comparator<Task>() {
                @Override
                public int compare(Task o1, Task o2) {
                    return 0;
                }
            });

        }
    }
    /**
     * A method for editing the tasks in the task list
     *
     * @param taskToEdit
     */

    public void EditTask(String taskToEdit) {
        String choice;
        // Searching the task to be edited by name.
        Task oldTask = searchTasksByName(taskToEdit);
        for (Task task : tasks) {
            //Checking the title entered exists in the list.
            if (task.getTitle().equals(taskToEdit)) {
                do {
                    //Showing the menu of options which a user can choose between
                    String[] mainMenu = {"\n" + "(1) Edit title", " (2) Edit project ",
                            "(3) Edit due date", "(4) Edit status", " (5) Remove task from list", " (6) Back to main menu", " (0) Quit and Save"};

                    for (int i = 0; i < mainMenu.length; i++) {
                        System.out.print(mainMenu[i]);
                    }
                    choice = getUserInput("\n Enter your choice >> ");
                    switch (choice) {

                        case "1":
                            //Editing title
                            String newTitle = getUserInput("Enter your new title >>");
                            if (newTitle.isEmpty()) {
                                System.out.println("Your entry is not valid");
                            }
                            oldTask.setTitle(newTitle);
                            break;

                        case "2":
                            // Editing project
                            String newProject = getUserInput("Enter your new project name >>");
                            if (newProject.isEmpty()) {
                                System.out.println("Your entry is not valid");
                            }
                            oldTask.setProject(newProject);
                            break;
                        case "3":
                            // Editing new dueDate
                            String newDueDate = getUserInput("Enter your new due date >>");
                            oldTask.setDuedate(dateReader(newDueDate));
                            break;
                        case "4":
                            //Editing task status
                            String newStatus = getUserInput("Set task status, you can choose between (Done - In progress - Not done) >>");
                            if (newStatus.equals("Done") || newStatus.equals("In progress") || newStatus.equals("Not done")) {
                                oldTask.setStatus(newStatus);
                            } else {
                                System.out.println("Make sure to choose one of the status stated above");
                                EditTask(taskToEdit);
                            }
                            break;
                        case "5":
                            // remove a task from list with a confirmation entry from the user
                            String confirmation = getUserInput("Are you sure you want to remove this task? \n Y/N >>");
                            // user denies removing the task
                            if (confirmation.equals("N")) {
                                EditTask(taskToEdit);
                                //user confirms removing task
                            } else if (confirmation.equals("Y")) {
                                tasks.remove(oldTask);
                                System.out.println("Task has been removed");
                            }
                            break;
                        case "6":
                            //return to main menu.
                            Menu.makeYourChoice();
                            break;
                        case "0":
                            //exiting the program with an entry confirmation from the user
                            Menu.exitingConditions();
                            break;
                        default:
                            System.out.println("Please choose one of listed options");
                            break;
                    }
                }
                while (choice != null);
            }
        }
    }
}