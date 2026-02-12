package tests.PDF_Compare;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.PDFUtil;

public class PDFComparisonTest {

    @Test
    public void compareExpectedAndActualPDF() {

        // Expected PDF (static file)
        String expectedPDF =
                System.getProperty("user.dir")
                + "/src/test/resources/PDFS/expectedPDFs/expected_invoice.pdf";

        // Actual PDF (downloaded by Selenium)
        String actualPDF =
                System.getProperty("user.dir")
                + "/src/test/resources/PDFS/actualPDF/actual_invoice.pdf";

        // Safety check
        Assert.assertTrue(new File(expectedPDF).exists(),
                "Expected PDF not found");

        Assert.assertTrue(new File(actualPDF).exists(),
                "Actual PDF not found");

        // Read PDF contents
        String expectedText = PDFUtil.readPDF(expectedPDF);
        System.out.println("text inside expeted pdf is "+ expectedText);
        String actualText = PDFUtil.readPDF(actualPDF);
        System.out.println("text inside actual pdf is "+ actualText);
        // Compare
        Assert.assertEquals(actualText, expectedText,
                "PDF content mismatch!");
    }
}
