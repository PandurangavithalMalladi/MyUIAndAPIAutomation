package utils;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance() {

        if (extent == null) {

            String reportPath =
                    System.getProperty("user.dir")
                    + "/extent-reports/ExtentReport.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("OrangeHRM Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "OrangeHRM");
            extent.setSystemInfo("Tester", "Automation QA");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }

    public static ExtentReports getExtentReport() {

        if (extent == null) {

            String reportFolder =
                    System.getProperty("user.dir") + "/reports/extent-reports/";

            File folder = new File(reportFolder);
            if (!folder.exists()) {
                folder.mkdirs();   // 🔥 Ensure folder is created
            }

            String reportPath = reportFolder + "ExtentReport.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("OrangeHRM Report");
            spark.config().setDocumentTitle("Automation Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
    
    public static ExtentReports getInstance() {

        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());

            String reportDir = System.getProperty("user.dir")
                    + "/reports/extent-reports/";

            File folder = new File(reportDir);
            if (!folder.exists()) {
                folder.mkdirs();  // create folder if not exists
            }

            String reportPath = reportDir
                    + "ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("OrangeHRM Automation Report");
            spark.config().setDocumentTitle("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Project", "OrangeHRM");
            extent.setSystemInfo("Tester", "Panduranga");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }

}
