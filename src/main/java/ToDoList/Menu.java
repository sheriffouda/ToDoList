package ToDoList;

import FileHandler.FileManager;

import java.util.Scanner;

/**
 * A Menu which the user can choose an option for Creating, editing, showing or removing a task in a todolist.
 *
 * @version 2019.10.10
 * @Author: Sherif Fouda
 */

public class Menu {

    //Option Selector
    private static String choice;
    // Task list to contain different tasks.
    private static Tasks taskList;
    //Obtaining inputs from users.
    private static Scanner scanner;
    private String fileName;

    // The constructor.

    public Menu() {
        this.taskList = new Tasks();
        this.scanner = new Scanner(System.in);
    }

    /**
     * A method for printing a welcome message when starting the program, as well as giving information of the user's current tasks and their current status
     */

    public static void welcoming() {
        String[] mainMenu = {"", "          ####################################", "             Welcome to the shake it world", "          ####################################", "", "", "You have " + taskList.tasksCurrentStatusCounter("Not done") + " task(s) to do, " + taskList.tasksCurrentStatusCounter("Done") + " task(s) done and "+ taskList.tasksCurrentStatusCounter("In progress") + " task(s) in progress", ""};

        for (int i = 0; i < mainMenu.length; i++) {
            System.out.println(mainMenu[i]);
        }
    }

    /**
     * A method for controlling that exiting conditions are satisfied
     */

    public static void exitingConditions() {
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        //Returning to main menu if answer is no.
        if (answer.equals("N")) {
            makeYourChoice();
            //Exiting the program if answer is yes.
        } else if (answer.equals("Y")) {
            System.out.println("See you in another task. Bye bye\n");
            //Saving tasks to file before exiting the program.
            String fileName = "/Users/sheriffouda/Desktop/ToDoList/src/main/java/ToDoList/taskfile.csv";
            FileManager.writeToFile(fileName, taskList.getTasks());
            //Exiting the program.
            System.exit(0);
        }
    }

    /* A method for Showing the menu to the user */

    public static void makeYourChoice() {

        do {
            String[] mainMenu = {"\nChoose one of the following options:  \n\n(1) Create a new task", "(2) Show all tasks ",
                    "(3) Edit or remove a task", "(4) Save tasks", "(0) Quit", ""};
            // printing out main menu.
            for (int i = 0; i < mainMenu.length; i++) {
                System.out.println(mainMenu[i]);
            }
            System.out.print("\nEnter your choice >>\n");
            String choice = scanner.nextLine();
            // Choosing an option from a menu.
            switch (choice) {
                //Exiting case
                case "0":
                    String answer;
                    System.out.println("Ohh, are you sure you want to quit the program? " + "Y/N");
                    exitingConditions();

                    do {
                        System.out.print("would you like to quit the program? " + " Y/N" + " >> ");
                        exitingConditions();
                        scanner.nextLine(); // clears the buffer
                        answer = scanner.nextLine();
                    }
                    while (answer != "Y" || answer != "N");
                    System.out.println("Ohh, not valid, try one more time");

                    break;

                case "1":
                    //Creating a new task and adding it to the task list.
                    taskList.addTask();
                    System.out.println("\nA new task has been created");
                    break;

                case "2":
                    // Printing all saved tasks in the task list.
                    if (taskList.getNumberOfTasks() == 0) {
                        System.out.println("\nYour task list is empty");
                    } else {
                        System.out.println("You have " + taskList.getNumberOfTasks() + " task(s)\n");
//                        Tasks.sortByName(taskList.showAllTasks());
                        taskList.getTasks();
                    }
                    break;

                case "3":
                    //Editing and removing tasks
                    taskList.getTasks();
                    System.out.println("\n Enter task to edit >>\n");
                    answer = scanner.nextLine();
                    taskList.EditTask(answer);
                    break;
                case "4":
                    // Saving tasks to a .CSV file.
                    String fileName = "/Users/sheriffouda/Desktop/IP/taskfile.csv";
                    FileManager.writeToFile(fileName, taskList.getTasks());
                    break;
                default:
                    System.out.println("\nPlease choose one of listed options");
                    break;
            }

        }
        while (choice == null);
    }
}


