import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private List<Task> list = new ArrayList<>();
    private Storage storage;
    private static String doneErrorMessage = "OOPS!!! Please choose a valid task index to mark as done.\n";
    private static String deleteErrorMessage = "OOPS!!! Please choose a valid task index to delete.\n";

    TaskList(Storage storage) throws FileNotFoundException, DukeException {
        this.storage = storage;
        Scanner scanner = storage.load();
        Parser parser = new Parser();
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            Task task = parser.parseFileData(taskString);
            list.add(task);
        }
    }

    protected void addTask(Task task) throws IOException {
        list.add(task);
        System.out.println("Added: " + task + "\n");
        storage.saveTasks(toString());
    }

    protected void markTaskDone(String command) throws DukeException {
        try {
            int listIndex = Integer.parseInt(command.substring(5));
            Task task = list.get(listIndex - 1);
            task.markDone();
            System.out.printf("Hurray! %s is now done.\n", task.getTask());
            System.out.println(task + "\n");
            storage.saveTasks(toString());
        } catch (Exception error) {
            throw new DukeException(doneErrorMessage);
        }
    }

    protected void deleteTask(String command) throws DukeException {
        try {
            int listIndex = Integer.parseInt(command.substring(7));
            Task task = list.get(listIndex - 1);
            list.remove(listIndex - 1);
            System.out.printf("Okay %s has been deleted.\n", task.getTask());
            System.out.println(task);
            System.out.println("You now have " + list.size() + " tasks.\n");
            storage.saveTasks(toString());
        } catch (Exception error) {
            throw new DukeException(deleteErrorMessage);
        }
    }

    @Override
    public String toString() {
        String message = "";
        int count = 1;
        for (Task task : list) {
            message += String.format("%d.%s\n"
                    , count++, task);
        }
        return message;
    }
}
