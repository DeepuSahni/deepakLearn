package main.read;

import main.advice.AdviceService;
import main.dictionary.DictionaryLoader;
import main.encode.EncodeService;
import main.util.Error;

import org.hamcrest.MatcherAssert;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.testng.Assert.assertEquals;


@Test
public class InputReaderTest {
    @Mock
    private InputReader inputReader;

    private static final String VALID_FILE = "testPhoneNumbers.txt";

    private EncodeService encodeService;
    private AdviceService adviceService;

    List<String> expected =Arrays.asList("12345", "123-345", "(123)");

    @BeforeClass
    public void initialise() {
        encodeService = new EncodeService();
        adviceService = new AdviceService(DictionaryLoader.getDictionary());
    }

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        inputReader = Mockito.spy(new InputReader());
    }

    public void given_validFileNameArg_when_process_then_returnsCorrectCountInFile() {
        assertEquals(inputReader.readFileInput(Stream.of(VALID_FILE)).size(), 3);
    }

    public void given_validFileNameArg_when_process_then_returnsSameNumbersInFile() {
        List<String> allNumbers = inputReader.readFileInput(Stream.of(VALID_FILE));
        MatcherAssert.assertThat(allNumbers, containsInAnyOrder(expected.toArray()));
    }

    public void given_nonExistentFile_when_process_then_returnsNothing() {
        List<String> allNumbers = inputReader.readFileInput(Stream.of("NO_SUCH_FILE"));
        Mockito.verify(inputReader, Mockito.times(1)).readFromFile(ArgumentMatchers.anyString(), ArgumentMatchers.any(List.class));
        assertEquals(inputReader.getError(), Optional.of(Error.FILE_NOT_FOUND));
        assertEquals(allNumbers.size(), 0);
    }

    public void given_mixedOfGoodBadFileArgs_when_process_then_readFromFileInput() {
        List<String> allNumbers =  inputReader.readFileInput(Stream.of(VALID_FILE, "being sneaky"));
        Mockito.verify(inputReader, Mockito.times(2)).readFromFile(ArgumentMatchers.anyString(), ArgumentMatchers.any(List.class));
        MatcherAssert.assertThat(allNumbers, containsInAnyOrder(expected.toArray()));
    }
}