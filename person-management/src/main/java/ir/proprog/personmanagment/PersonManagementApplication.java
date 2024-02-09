package ir.proprog.personmanagment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication(scanBasePackages = "ir.proprog")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class PersonManagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(PersonManagementApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(PersonManagementApplication.class, args);
        logAppStartup(app);
    }

    private static void logAppStartup(ConfigurableApplicationContext app) {
        try {
            logger.info("Start application....");
            ConfigurableEnvironment environment = app.getEnvironment();
            Object path = InetAddress.getLocalHost().getHostName() + " (" + InetAddress.getLocalHost().getHostAddress() + ")";
            logger.info("Application startup in " + path);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
