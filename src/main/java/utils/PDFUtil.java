package utils;

import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFUtil {

    // Read full PDF text
    public static String readPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {

            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document)
                           .replaceAll("\\s+", " ")
                           .trim();
        } catch (Exception e) {
            throw new RuntimeException("Unable to read PDF: " + filePath, e);
        }
    }

    // Optional: page count check
    public static int getPageCount(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            return document.getNumberOfPages();
        } catch (Exception e) {
            throw new RuntimeException("Unable to get page count", e);
        }
    }
}
