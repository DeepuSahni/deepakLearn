package main.result;

import main.advice.AdviceService;
import main.dictionary.DictionaryLoader;
import main.encode.EncodeService;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class PhoneNumberProcessorTest {
    private Set<String> defaultDictionary;
    private EncodeService encodeService;
    private AdviceService adviceService;
    private PhoneNumberProcessor processor;

    @BeforeMethod
    public void setUp() throws Exception {
        encodeService = new EncodeService();
        defaultDictionary = DictionaryLoader.getDictionary();
        adviceService = new AdviceService(defaultDictionary);
        MockitoAnnotations.initMocks(this);
        processor = Mockito.spy(new PhoneNumberProcessor());
    }

    @Test
    public void given_validNumber_when_process_thenGetSuggestions() throws Exception {
        processor.setPhoneNumber("12345");
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(processor, Mockito.atLeastOnce()).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    @Test
    public void given_inValidNumber_when_process_thenGetNoSuggestions() throws Exception {
        processor.setPhoneNumber("Abc12345");
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(processor, Mockito.times(0)).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    @Test
    public void given_emptyNumber_when_process_thenGetNoSuggestions() throws Exception {
        processor.setPhoneNumber("");
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(processor, Mockito.times(0)).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    @Test
    public void given_nullNumber_when_process_thenGetNoSuggestions() throws Exception {
        processor.setPhoneNumber(null);
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(processor, Mockito.times(0)).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }
}