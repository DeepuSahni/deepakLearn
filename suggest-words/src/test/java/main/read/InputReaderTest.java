package main.read;

import co.unruly.matchers.StreamMatchers;
import main.util.Error;
import org.mockito.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


@Test
public class InputReaderTest {
    @Mock
    private InputStream inputStream;

    private InputReader inputReader;

    private static final String VALID_FILE = "testPhoneNumbers.txt";

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inputReader = Mockito.spy(new InputReader(inputStream));
    }

    public void given_zeroFileArgs_when_process_then_readFromStandardInput() {
        inputReader.readInput(Stream.empty());
        Mockito.verify(inputReader, Mockito.times(1)).readUserInput();
        Mockito.verify(inputReader, Mockito.times(0)).readFileInput(ArgumentMatchers.anyString(), ArgumentMatchers.any(List.class));
    }

    public void given_fileArgs_when_process_then_readFromFileInput() {
        inputReader.readInput(Stream.of(VALID_FILE));
        Mockito.verify(inputReader, Mockito.times(0)).readUserInput();
        Mockito.verify(inputReader, Mockito.times(1)).readFileInput(ArgumentMatchers.anyString(), ArgumentMatchers.any(List.class));
    }

    public void given_validFileNameArg_when_process_then_returnsCorrectCountInFile() {
        assertEquals(inputReader.readInput(Stream.of(VALID_FILE)).count(), 3);
    }

    public void given_validFileNameArg_when_process_then_returnsSameNumbersInFile() {
        Stream<String> allNumberStream = inputReader.readInput(Stream.of(VALID_FILE));
        assertThat(allNumberStream, StreamMatchers.contains("12345","123-345","(123)"));
    }

    public void given_nonExistentFile_when_process_then_returnsEmptyStream() {
        Stream<String> allNumberStream = inputReader.readInput(Stream.of("NO_SUCH_FILE"));
        Mockito.verify(inputReader, Mockito.times(0)).readUserInput();
        Mockito.verify(inputReader, Mockito.times(1)).readFileInput(ArgumentMatchers.anyString(), ArgumentMatchers.any(List.class));
        assertEquals(inputReader.getError(), Optional.of(Error.FILE_NOT_FOUND));
        assertEquals(allNumberStream.count(), 0);
    }
}