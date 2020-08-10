package app.wordyourself.trainingjms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * alper - 10/08/2020
 */
@EnableAsync
@Configuration
@EnableScheduling
public class TaskConfig {

    TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

}
