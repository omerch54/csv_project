package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * This is the class which has the parse function to parse the inputted CSV, which would be an object
 * of the type reader and produces a List of String[] called parsed, which has each row of the csv
 * as a string[].
 */
public class CSVParser<T> {

  // The CSV that is inputted by the user of the type Reader.
  private Reader reader;

  //A list of String arrays which would contain the output of the parse function
  private List<String[]> parsed = new ArrayList<>();
  private List<T> Tparsed = new ArrayList<>();

  // Specifying the datatype of the rows that the developer wants the parsed CSV to have.
  private CreatorFromRow<T> toCreated;

  // One of the Constructors for the CSV parser class that takes in the csv, of the type Reader, to be parsed
  // and toCreate which is of the type CreatorFromRow Interface specifying the datatype for the rows
  // of the parsed csv.
  public CSVParser(Reader reader, CreatorFromRow<T> toCreated) {
    this.reader = reader;
    this.toCreated = toCreated;
  }

  // One of the Constructors for the CSV parser class that takes in the csv, of the type Reader,
  // to be parsed by the parse() method.
  public CSVParser(Reader reader) {
    this.reader = reader;
  }

  /**
   * This is the main method for parsing the csv. It first creates an object in of type buffered reader
   * as we wrap our generic reader in the BufferedReader statement. It then reads each line from in
   * through the readline function and splits splits each row visavis commas and adds each row to the
   * parsed as a String array
   *
   * @return parsed - which the parsed version of the csv that is inputted by the user
   * @throws IOException
   */
  public List<T> parse() throws IOException{
    try(BufferedReader in = new BufferedReader((java.io.Reader) this.reader)){
      String row;
      while((row = in.readLine()) != null) {
        String[] att = row.split(",");
        if (toCreated !=null){
          try{
            T obj = toCreated.create(row.split(" "));
            Tparsed.add(obj);
          } catch (FactoryFailureException e) {
            throw new RuntimeException(e);
          }
       } else{
          Tparsed.add((T) att);
        }
      }
      in.close();
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return Tparsed;
  }


}
