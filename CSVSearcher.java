package edu.brown.cs.student.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * It is the class that has Search() method which searches the parsed csv for the value that is
 * inputted by the user.
 */
public class CSVSearcher {

  // The parsed csv which is a list of String arrays
  private List<String[]> parsed;
  // The value which is to searched
  private String val;
  // index, the column identifier, which if not specified by the user is initialized to "-1" to handle
  // those intances when index is not specified
  private String index = "-1";
  //Specifies whether or not the first row contains headers
  private String isHeader = "no";

  // One of the constructors for the CSVSearcher class. It takes in parsed, which is a List of String
  // array refering to our parsed CSV, val, which is a strign that we are looking for, the index,
  // which is a cloumn identifier and the String specifying isHeader.
  public CSVSearcher(List<String[]> parsed, String val, String index, String isHeader) {
    this.parsed = parsed;
    this.val = val;
    this.index = index;
    this.isHeader = isHeader;
  }

  // The second constructor fir CSVSearcher that contain all the inputs of the first constructor with
  // the exception of index, i.e. the column identifier.
  public CSVSearcher(List<String[]> parsed, String val, String isHeader) {
    this.parsed = parsed;
    this.val = val;
    this.isHeader = isHeader;
  }

  /**
   * This is the main method that finds the row which has the specified value indicated by the user.
   * It first assigns index the value of the column header identifier if it is specified by the user.
   * Then if no identifiers have been added i.e. the index = -1 then it goes through each row and
   * each attribute of those rows to whether they equal to the inputted val, if yes it outputs that
   * row. If the column identifier i.e. the identifier has been specified then we simply check the
   * attributes at that specific column, whther they equal to the val and accordingly output the
   * row. If none of the case passes it returns "value isn't found"
   * @return
   */
  public String Search() {
    int lnumber = 1;
    // if the first row contains headers and the column identifier (column name) has been specified
    // setting index to the index of the occurrence of the specified column identifier.
    if (index != "-1" && isHeader.equals("yes")) {
      int i = 0;
      String[] frow = parsed.get(0);
      for (String cname : frow) {
        if (cname.equals(index)) {
          index = String.valueOf(i);
          break;
        }
        i++;
      }
    }
    for (String[] row : parsed) {
      // index = -1 refers to the case when index value has not been entered
      if (index == "-1") {
        for (String attribute : row) {
          if (attribute.equals(val)) {
            return (lnumber + ": " + Arrays.toString(row));
          }
        }
      } else {
        // if the column identifier (index) had been specified
        if (row[Integer.parseInt(index)].equals(val)) {
          return (lnumber + ": " + Arrays.toString(row));
        }

      }
      lnumber++;
    }
    return "Value isn't in the CSV";
  }


}
