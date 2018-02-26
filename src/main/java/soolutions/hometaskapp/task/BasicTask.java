package soolutions.hometaskapp.task;

import java.time.Duration;

public class BasicTask implements Task {
    private final String name;
    private final String description;
    private final int unitsOfWork;
    private final Importance importance;
    private final Duration frequency;

    public BasicTask(String name, String description, int unitsOfWork, Importance importance, Duration frequency) {
        this.name = name;
        this.description = description;
        this.unitsOfWork = unitsOfWork;
        this.importance = importance;
        this.frequency = frequency;
    }
}
