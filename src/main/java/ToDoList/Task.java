package ToDoList;

import java.time.LocalDate;

public class Task {

    private String title;
    private String project;
    private String status;
    private LocalDate dueDate;

    /**
     * Constructor of the class Task
     *
     * @param title
     * @param project
     * @param dueDate
     */
    public Task(String title, String project, LocalDate dueDate) {

        this.title = title;
        this.project = project;
        this.status = "Not done";
        this.dueDate = dueDate;
    }

    public Task() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate getDuedate() {
        return dueDate;
    }

    public void setDuedate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // getting the status of the task.
    public String getStatus() {
        return status;
    }

    // Setting the status of the task.
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getting the data stored inside each task
     *
     * @return title, project, due date and the status of each task
     */
    public String getDetails() {
        String format = "%-40s %-30s %20s %30s\n", taskTitle = "Title", projectName = "Project", taskDueDate = "Due date", taskStatus = "Status";
        System.out.printf(format, title, project, dueDate, status);
        return title + project + dueDate + status;
    }

    public void styling() {

        String format = "%-40s %-30s %18s %30s\n", taskTitle = "Title", projectName = "Project", taskDueDate = "Due date", taskStatus = "Status";
        System.out.printf(format, taskTitle, projectName, taskDueDate, taskStatus);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------");

    }
}


