
package com.examly.springapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
public class SpringappApplicationTests {

    private static String PROJ_ROOT;

    @BeforeAll
    public static void setUp() {
        try {
            PROJ_ROOT = new File(".").getCanonicalPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDirectoryStructure() {
        String[] directories = {
            "src/main/java/com/examly/springapp",
            "src/main/resources",
            "src/main/resources/static",
            "src/main/resources/templates",
            "src/test/java/com/examly/springapp"
        };

        for (String dir : directories) {
            assertTrue(Files.exists(Paths.get(PROJ_ROOT, dir)), 
                "Directory not found: " + dir);
        }
    }

    @Test
    public void testRequiredFiles() {
        String[] files = {
            "pom.xml",
            "src/main/resources/application.properties",
            "src/main/java/com/examly/springapp/SpringappApplication.java",
            ".gitignore",
            "mvnw",
            "mvnw.cmd"
        };

        for (String file : files) {
            assertTrue(Files.exists(Paths.get(PROJ_ROOT, file)), 
                "File not found: " + file);
        }
    }

    @Test
    public void testModelClasses() {
        try {
            // Test Job class
            Class<?> jobClass = Class.forName("com.examly.springapp.model.Job");
            assertTrue(jobClass.getDeclaredField("jobID") != null);
            assertTrue(jobClass.getDeclaredField("jobTitle") != null);
            assertTrue(jobClass.getDeclaredField("department") != null);
            assertTrue(jobClass.getDeclaredField("location") != null);
            assertTrue(jobClass.getDeclaredField("applications") != null);

            // Test Application class
            Class<?> applicationClass = Class.forName("com.examly.springapp.model.Application");
            assertTrue(applicationClass.getDeclaredField("applicationID") != null);
            assertTrue(applicationClass.getDeclaredField("applicationName") != null);
            assertTrue(applicationClass.getDeclaredField("status") != null);
            assertTrue(applicationClass.getDeclaredField("job") != null);

            // Test User class
            Class<?> userClass = Class.forName("com.examly.springapp.model.User");
            assertTrue(userClass.getDeclaredField("id") != null);
            assertTrue(userClass.getDeclaredField("email") != null);
            assertTrue(userClass.getDeclaredField("userRole") != null);

            // Test Payment class
            Class<?> paymentClass = Class.forName("com.examly.springapp.model.Payment");
            assertTrue(paymentClass.getDeclaredField("id") != null);
            assertTrue(paymentClass.getDeclaredField("status") != null);
            assertTrue(paymentClass.getDeclaredField("totalAmount") != null);
        } catch (Exception e) {
            fail("Required model classes or their fields are missing: " + e.getMessage());
        }
    }

    @Test
    public void testControllers() {
        try {
            // Test AuthController
            Class<?> authController = Class.forName("com.examly.springapp.controller.AuthController");
            assertTrue(authController.getMethod("login") != null);
            assertTrue(authController.getMethod("register") != null);

            // Test JobController
            Class<?> jobController = Class.forName("com.examly.springapp.controller.JobController");
            assertTrue(jobController.getMethod("getAllJobs") != null);
            assertTrue(jobController.getMethod("addJob") != null);
            assertTrue(jobController.getMethod("updateJob") != null);
            assertTrue(jobController.getMethod("deleteJob") != null);

            // Test ApplicationController
            Class<?> applicationController = Class.forName("com.examly.springapp.controller.ApplicationController");
            assertTrue(applicationController.getMethod("submitApplication") != null);
            assertTrue(applicationController.getMethod("getApplicationStatus") != null);
        } catch (Exception e) {
            fail("Required controllers or their methods are missing: " + e.getMessage());
        }
    }

    @Test
    public void testServices() {
        try {
            // Test JobService
            Class<?> jobService = Class.forName("com.examly.springapp.service.JobService");
            assertTrue(jobService.getMethod("getAllJobs") != null);
            assertTrue(jobService.getMethod("addJob") != null);

            // Test ApplicationService
            Class<?> applicationService = Class.forName("com.examly.springapp.service.ApplicationService");
            assertTrue(applicationService.getMethod("submitApplication") != null);
            assertTrue(applicationService.getMethod("updateApplicationStatus") != null);

            // Test PaymentService
            Class<?> paymentService = Class.forName("com.examly.springapp.service.PaymentService");
            assertTrue(paymentService.getMethod("processPayment") != null);
        } catch (Exception e) {
            fail("Required services or their methods are missing: " + e.getMessage());
        }
    }

    @Test
    public void testSecurityConfiguration() {
        try {
            Class<?> securityConfig = Class.forName("com.examly.springapp.config.SecurityConfig");
            assertTrue(securityConfig != null);
        } catch (Exception e) {
            fail("Security configuration is missing: " + e.getMessage());
        }
    }

    @Test
    public void testExceptionHandling() {
        try {
            Class<?> customException = Class.forName("com.examly.springapp.exception.CustomException");
            Class<?> globalExceptionHandler = Class.forName("com.examly.springapp.exception.GlobalExceptionHandler");
            assertTrue(customException != null);
            assertTrue(globalExceptionHandler != null);
        } catch (Exception e) {
            fail("Exception handling classes are missing: " + e.getMessage());
        }
    }

    @Test
    public void testRepositories() {
        try {
            Class<?> jobRepo = Class.forName("com.examly.springapp.repository.JobRepository");
            Class<?> applicationRepo = Class.forName("com.examly.springapp.repository.ApplicationRepository");
            Class<?> userRepo = Class.forName("com.examly.springapp.repository.UserRepository");
            assertTrue(jobRepo != null);
            assertTrue(applicationRepo != null);
            assertTrue(userRepo != null);
        } catch (Exception e) {
            fail("Required repositories are missing: " + e.getMessage());
        }
    }
}
