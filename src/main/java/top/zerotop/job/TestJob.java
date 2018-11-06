package top.zerotop.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TestJob {
    private static Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Scheduled(cron = "0 22 17 * * ?")
    public void testScheduledJob() {
        logger.info("job is run");
    }
}
