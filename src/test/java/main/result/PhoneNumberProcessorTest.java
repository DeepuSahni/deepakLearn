package main.result;

import main.advice.AdviceService;
import main.dictionary.DictionaryLoader;
import main.encode.EncodeService;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class PhoneNumberProcessorTest {
    private EncodeService encodeService;
    private AdviceService adviceService;
    private PhoneNumberProcessor processor;

    @BeforeMethod
    public void setUp() throws Exception {
        encodeService = Mockito.spy(new EncodeService());
        adviceService = new AdviceService(DictionaryLoader.getDictionary());
        MockitoAnnotations.initMocks(this);
        processor = Mockito.spy(new PhoneNumberProcessor());
    }

    public void given_validNumber_when_process_thenGetSuggestions() throws Exception {
        processor.setPhoneNumber("2255");
        processor.processPhoneNumber(encodeService, adviceService);
        // Encode is called with same number.
        Mockito.verify(encodeService, Mockito.atLeastOnce()).encode("2255");
        Mockito.verify(processor, Mockito.atLeastOnce()).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    public void given_validNumberWithPunctuation_when_process_thenGetSuggestions() throws Exception {
        processor.setPhoneNumber("(22)-55-");
        processor.processPhoneNumber(encodeService, adviceService);
        // Encode is called with number without punctuation.
        Mockito.verify(encodeService, Mockito.atLeastOnce()).encode("2255");
        Mockito.verify(processor, Mockito.atLeastOnce()).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    public void given_inValidNumber_when_process_thenGetNoSuggestions() throws Exception {
        processor.setPhoneNumber("Abc12345");
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(encodeService, Mockito.times(0)).encode(ArgumentMatchers.anyString());
        Mockito.verify(processor, Mockito.times(0)).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    public void given_emptyNumber_when_process_thenGetNoSuggestions() throws Exception {
        processor.setPhoneNumber("");
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(encodeService, Mockito.times(0)).encode(ArgumentMatchers.anyString());
        Mockito.verify(processor, Mockito.times(0)).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

    public void given_nullNumber_when_process_thenGetNoSuggestions() throws Exception {
        processor.setPhoneNumber(null);
        processor.processPhoneNumber(encodeService, adviceService);
        Mockito.verify(encodeService, Mockito.times(0)).encode(ArgumentMatchers.anyString());
        Mockito.verify(processor, Mockito.times(0)).getSuggestions(ArgumentMatchers.anyString(), ArgumentMatchers.any(AdviceService.class));
    }

}