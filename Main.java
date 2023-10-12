package edu.brown.cs.student.main;

/** The Main class of our project. This is where execution begins. */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
public final class Main {
  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  /**
   * The main method first checks if the lenght of the args is 4, meaining the user has entered the
   * optional column identifier. If the lenght is four then if assigns the each argument to its proper
   * identifier, if the length is 3 the assignmwnts are made accordingly. One interesting thing in
   * the code for Main is the way I handle the two instances regarding entering a reader relative to
   * Filereader or the Stringreader. I do this by using a try and catch statement - it first tries
   * Filereader, if the input csv is a file the code is run wothout erring but if the input csv is
   * a string csv then it would catch a FIleNotFOundException and run the code relative to StringReader,
   *  thereby handling both cases.
   * @param args entered by the user
   */
  private Main(String[] args) {
    try {
      if (args.length == 4) {
        try {
          Reader file = new FileReader(args[0]);
          String val = args[1];
          String ind = args[2];
          String isHeader = args[3];
          List<String[]> parsed = new CSVParser(file).parse();
          String value = new CSVSearcher(parsed, val, ind, isHeader).Search();
          System.out.println(value);
        } catch (FileNotFoundException e) {
          Reader file = new StringReader(args[0]);
          String val = args[1];
          String ind = args[2];
          String isHeader = args[3];
          List<String[]> parsed = new CSVParser(file).parse();
          String value = new CSVSearcher(parsed, val, ind, isHeader).Search();
          System.out.println(value);
        }
      } else if (args.length == 3) {
        try {
          Reader file = new FileReader(args[0]);
          String val = args[1];
          String isHeader = args[2];
          List<String[]> parsed = new CSVParser(file).parse();
          String value = new CSVSearcher(parsed, val, isHeader).Search();
          System.out.println(value);
        } catch (FileNotFoundException e) {
          Reader file = new StringReader(args[0]);
          String val = args[1];
          String isHeader = args[2];
          List<String[]> parsed = new CSVParser(file).parse();
          String value = new CSVSearcher(parsed, val, isHeader).Search();
          System.out.println(value);
        }

      }
    } catch(IOException e) {
      System.err.println(e.getMessage());
  }

}

  private void run() {
    // dear student: you can remove this. you can remove anything. you're in cs32. you're free!
  }
}
