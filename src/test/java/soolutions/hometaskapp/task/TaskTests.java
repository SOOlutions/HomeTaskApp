package soolutions.hometaskapp.task;

import org.junit.jupiter.api.Test;

import java.time.Duration;

public class TaskTests {
    @Test
    public void createDailyTask() {
        Task dailyTask = new BasicTask("Create README", "Sample Description", 3, Importance.Normal, Duration.ofDays(1));
    }

    @Test
    public void createWeeklyTask() {
        Task dailyTask = new BasicTask("Create README", "Sample Description", 3, Importance.Normal, Duration.ofDays(7));
    }
}
