package ir.proprog.firstapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication(scanBasePackages = "ir.proprog")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class FirstAppApplication {

    private static final Logger logger = LoggerFactory.getLogger(FirstAppApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(FirstAppApplication.class, args);
        logAppStartup(app);
    }

    private static void logAppStartup(ConfigurableApplicationContext app) {
        logger.info("start first app....");
        ConfigurableEnvironment environment = app.getEnvironment();
        String port = environment.getProperty("server.port");
        String path = environment.getProperty("server.servlet.context-path");
        logger.info("first app startup in " + path + " " + port);
    }
}
