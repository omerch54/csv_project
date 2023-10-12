package edu.brown.cs.student;


import edu.brown.cs.student.main.CSVParser;
import edu.brown.cs.student.main.CSVSearcher;
import edu.brown.cs.student.main.SArray;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Tests {

  //Testing the Parse function
  //Testing it relative to FileReader
  @Test
  public void testingparserfile() throws IOException{
    Reader file = new FileReader("data/stars/ten-star.csv");
    String val = "282.43485";
    List<String[]> parsedFile = new CSVParser(file).parse();
    assertEquals("[StarID, ProperName, X, Y, Z]", Arrays.deepToString(parsedFile.get(0)));
  }

  // Testing parser multiple CreatorFromRow classes to extract CSV data in different formats -
  // string[]
  @Test
  public void testingparserCreatorFromRow() throws IOException{
    Reader file = new FileReader("data/stars/ten-star.csv");
    String val = "282.43485";
    List<String[]> parsedFile = new CSVParser(file, new SArray()).parse();
    assertEquals("[StarID,ProperName,X,Y,Z]", Arrays.deepToString(parsedFile.get(0)));
  }

  //Testing parse relative to StringReader
  @Test
  public void testingparserstring() throws IOException{
    Reader file = new StringReader("X,Y,Z");
    String val = "Y";
    List<String[]> parsedFile = new CSVParser(file).parse();
    assertEquals("[X, Y, Z]", Arrays.deepToString(parsedFile.get(0)));
  }

  // Testing parser relative to StringRreader - inputting empty string
  @Test
  public void testingparseremptys() throws IOException{
    Reader file = new StringReader("");
    String val = "Y";
    List<String[]> parsedFile = new CSVParser(file).parse();
    //assertEquals("[]", parsedFile);
  }

  // Testing parser relative to FileRreader - inputting empty File
  @Test
  public void testingparseremptyf() throws IOException{
    Reader file = new FileReader("data/stars/empty.csv");
    String val = "Y";
    List<String[]> parsedFile = new CSVParser(file).parse();
    //assertEquals("[]", parsedFile);
  }

  //Testing the Searcher function
  // Without column headers and w/o column identifier
  @Test
  public void testingsearch1() throws IOException {
    String val = "E";
    List<String[]> parsed = List.of(new String[]{"A", "B", "C"}, new String[]{"D","E","F"});
    String value = new CSVSearcher(parsed, val, "no").Search();
    assertEquals("2: [D, E, F]", value);
  }

  // With column headers and w/o column identifier
  @Test
  public void testingsearch2() throws IOException {
    String val = "3";
    List<String[]> parsed = List.of(new String[]{"A", "B", "C"}, new String[]{"1","2","3"});
    String value = new CSVSearcher(parsed, val, "yes").Search();
    assertEquals("2: [1, 2, 3]", value);
  }

  // With column headers and with column identifier
  @Test
  public void testingsearch3() throws IOException {
    String val = "3";
    List<String[]> parsed = List.of(new String[]{"A", "B", "C"}, new String[]{"1","2","3"});
    String value = new CSVSearcher(parsed, val, "C", "yes").Search();
    assertEquals("2: [1, 2, 3]", value);
  }

  // With column headers and with column identifier when value not in CSV
  @Test
  public void testingsearch4() throws IOException {
    String val = "X";
    List<String[]> parsed = List.of(new String[]{"A", "B", "C"}, new String[]{"1","2","3"});
    String value = new CSVSearcher(parsed, val, "C", "yes").Search();
    assertEquals("Value isn't in the CSV", value);
  }

  //Testing the Software as a whole.

  //CSV data with and without column headers
  // with
  @Test
  public void testingSoftware1() throws IOException {
    Reader file = new FileReader("data/stars/stardata.csv");
    String val = "Andreas";
    List<String[]> parsedFile = new CSVParser(file).parse();
    String value = new CSVSearcher(parsedFile, val,"ProperName", "yes").Search();
    assertEquals("3: [1, Andreas, 282.43485, 0.00449, 5.36884]", value);
  }

  // w/o cloumn headers
  @Test
  public void testingSoftware2() throws IOException {
    Reader file = new FileReader("data/stars/r.csv");
    String val = "4";
    List<String[]> parsedFile = new CSVParser(file).parse();
    String value = new CSVSearcher(parsedFile, val,"no").Search();
    assertEquals("2: [4, 5, 6]", value);
  }

  //CSV data in different Reader types - using StringReader in this case
  @Test
  public void testingSotware3() throws IOException {
    Reader file = new StringReader("A,B,C,D");
    String val = "C";
    List<String[]> parsedFile = new CSVParser(file).parse();
    String value = new CSVSearcher(parsedFile, val, "no").Search();
    assertEquals("1: [A, B, C, D]", value);
  }

  // Searching for values that aren't present in that data - StringReader
  @Test
  public void testingSotware4() throws IOException {
    Reader file = new StringReader("A,B,C,D");
    String val = "X";
    List<String[]> parsedFile = new CSVParser(file).parse();
    String value = new CSVSearcher(parsedFile, val, "no").Search();
    assertEquals("Value isn't in the CSV", value);
  }

    // Searching for values that aren't present in that data - FileReader
    @Test
    public void testingSotware5() throws IOException {
      Reader file = new FileReader("data/stars/stardata.csv");
      String val = "Andrease";
      List<String[]> parsedFile = new CSVParser(file).parse();
      String value = new CSVSearcher(parsedFile, val,"ProperName", "yes").Search();
      assertEquals("Value isn't in the CSV", value);
  }
  


}
