package main;

import main.advice.AdviceService;
import main.dictionary.DictionaryLoader;
import main.encode.EncodeService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

@Test
public class ApplicationTest {
    @Mock
    private Application application;

    private static final String VALID_FILE = "testPhoneNumbers.txt";
    private static final String USER_DICTIONARY = "userDictionary.txt";
    private static final String DICTIONARY = "dictionary";

    private Set<String> defaultDictionary;
    private EncodeService encodeService;
    private AdviceService adviceService;
    private String[] args;

    @BeforeMethod
    public void setUp() throws Exception {
        encodeService = new EncodeService();
        defaultDictionary = DictionaryLoader.getDictionary();
        adviceService = new AdviceService(defaultDictionary);
        MockitoAnnotations.initMocks(this);
        application = Mockito.spy(new Application());
        args = new String[] {VALID_FILE};
    }

    // This test also runs the end to end flow. The file passed in arguments is processed and suggestions are printed.
    public void given_defaultDictionaryIsSet_when_run_then_runApplication() {
        application.startApplication(args);
        Mockito.verify(application, Mockito.times(1)).runApplication(args, defaultDictionary);
    }

    public void given_badUserDictionary_when_run_then_doNothing() {
        System.setProperty(DICTIONARY, "this file never existed");
        application.startApplication(args);
        Mockito.verify(application, Mockito.times(0)).runApplication(args, DictionaryLoader.getDictionary());
        System.clearProperty(DICTIONARY);
    }

    public void given_validUserDictionary_when_run_then_runApplication() {
        System.setProperty(DICTIONARY, USER_DICTIONARY);
        application.startApplication(args);
        Mockito.verify(application, Mockito.times(1)).runApplication(args, DictionaryLoader.getDictionary());
        System.clearProperty(DICTIONARY);
    }

}
