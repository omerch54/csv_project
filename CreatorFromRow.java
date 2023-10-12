package edu.brown.cs.student.main;

import java.util.List;

public interface CreatorFromRow<T> {
  T create(String[] row) throws FactoryFailureException;
}
