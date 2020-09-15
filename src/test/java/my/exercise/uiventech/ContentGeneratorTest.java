package my.exercise.uiventech;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringStartsWith.startsWith;

public class ContentGeneratorTest {

    public static final int LINE_LENGTH = 100;

    ContentGenerator generator = new ContentGenerator();

    @Rule
    public TemporaryFolder tmpFolder = new TemporaryFolder();

    @Test
    public void getUniqueStringShouldReturn100CharactersAndStartWithIndex() {
        int lineIndex = 10;
        String uniqueString = generator.getUniqueString(lineIndex);

        assertThat(uniqueString.length(), equalTo(LINE_LENGTH));
        assertThat(uniqueString, startsWith(lineIndex + ""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateDataWithNoLineShouldThrowException() throws IOException {
        generator.generateData(tmpFolder.newFile(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generateDataWithMoreThan230LinesShouldThrowException() throws IOException {
        generator.generateData(tmpFolder.newFile(), 231);
    }

    @Test
    public void generatedDataShouldContainExactlyLineNumber() throws IOException {
        int lineNumber = 230;
        File outputFile = tmpFolder.newFile();

        generator.generateData(outputFile, lineNumber);

        assertFileContent(outputFile, lineNumber);
    }

    private void assertFileContent(File file, int lineNumber) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int counter = 0;
            while ((line = br.readLine()) != null) {

                assertThat(line.length(), equalTo(LINE_LENGTH));
                assertThat(line, startsWith(counter + ""));
                counter ++;
            }

            assertThat(counter, equalTo(lineNumber));
        }
    }
}