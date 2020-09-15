package my.exercise.uiventech;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ContentGenerator {

    public static final int LINE_LENGTH = 100;

    String getUniqueString(int lineIndex) {
        return lineIndex + RandomStringUtils.randomAlphabetic(LINE_LENGTH - Integer.toString(lineIndex).length());
    }

    void generateData(File outputFile, int lineNumber) throws IOException {
        if (lineNumber < 1 || lineNumber > 230) {
            throw new IllegalArgumentException("Line number should be in the range of 1 and 230");
        }

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos))) {
                for (int i = 0; i < lineNumber; i++) {
                    writer.write(getUniqueString(i));
                    writer.newLine();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ContentGenerator generator = new ContentGenerator();
        generator.generateData(new File("output.txt"), 230);
    }
}
