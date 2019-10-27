import ToDoList.Task;
import ToDoList.Tasks;
import org.junit.*;


import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestTask {
    Tasks testTasks=new Tasks();
    String title = "Homework";
    String project = "School";
    String status= "Done";
    String dueDate = "2020-10-10";
    LocalDate testDueDate;
    Task task;

    public TestTask(){
        testDueDate = testTasks.dateReader(this.dueDate);
        task = new Task(title, project, testDueDate);
    }


    @Test
    public void getTitleTest() {

        assertEquals(title,task.getTitle());
        assertEquals("Homework",task.getTitle());
    }
    @Test
    public void setTitle(String title) {

        assertEquals("Cleaning", task.getTitle());
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate getDuedate() {

        return testDueDate;
    }

    public void setDuedate(LocalDate dueDate) {
        this.testDueDate = testDueDate;
    }

    // getting the status of the task.
    public String getStatus() {
        return status;
    }

    // Setting the status of the task.
    public void setStatus(String status) {
        this.status = status;
}
}