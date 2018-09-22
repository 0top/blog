package top.zerotop.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestJob {

    @Scheduled(cron = "0 22 17 * * ?")
    public void testScheduledJob() {
        System.out.println("job is init ===");
    }

}
