import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private final String task;
    private final LocalDate deadline;

    protected Deadline(String task, LocalDate deadline) {
            this.task = task;
            this.deadline = deadline;
    }

    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                getTask(),
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

}
