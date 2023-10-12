package edu.brown.cs.student.main;

public class SArray implements CreatorFromRow<String[]>{

  @Override
  public String[] create(String[] row) throws FactoryFailureException {
    return row;
  }
}
