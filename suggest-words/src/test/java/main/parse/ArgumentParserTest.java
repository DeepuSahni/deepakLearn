package main.parse;

import org.testng.annotations.Test;

import java.util.stream.Stream;

import static org.testng.Assert.*;

@Test
public class ArgumentParserTest {
    private ArgumentParser argumentParser = new ArgumentParser();

    public void given_nullArgs_when_parseArgs_then_getZeroArgs() {
        Stream parsedArgument = argumentParser.parseArguments(null);
        assertEquals(parsedArgument.count(), 0);
    }

    public void given_EmptyArgs_when_parseArgs_then_getZeroArgs() {
        Stream parsedArgument = argumentParser.parseArguments(new String[]{});
        assertEquals(parsedArgument.count(), 0);
    }

    public void given_blankArgs_when_parseArgs_then_getZeroArgs() {
        Stream parsedArgument = argumentParser.parseArguments(new String[]{"  ", "   ", "  ", ""});
        assertEquals(parsedArgument.count(), 0);
    }

    public void given_OneFileNameArg_when_parseArgs_then_getOneFileName() {
        Stream parsedArgument = argumentParser.parseArguments(new String[]{"/myFiles/myDictionary.txt"});
        assertEquals(parsedArgument.count(), 1);
    }

    public void given_manyFileNameArgs_when_parseArgs_then_getManyFileNames() {
        Stream parsedArgument = argumentParser.parseArguments(new String[]{"yes", "no", "icanAlsoBeFile", "/myFiles/myDictionary.txt"});
        assertEquals(parsedArgument.count(), 4);
    }

    public void given_mixedFileNameArgs_when_parseArgs_then_getValidFileNameCount() {
        Stream parsedArgument = argumentParser.parseArguments(new String[]{"", "  ", "/", "/myFiles/myDictionary.txt"});
        assertEquals(parsedArgument.count(), 1);
    }

}
