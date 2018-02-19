package edu.buffalo.www.cse4562.query;

import java.util.ArrayList;
import java.util.Collection;

import edu.buffalo.www.cse4562.model.Node;
import edu.buffalo.www.cse4562.model.Tuple;

/**
 * Process each type of query.
 *
 * @author varunjai
 *
 */
public class QueryProcessor {

  /**
   * This method is responsible for processing {@link CreateTable} query by
   * storing the {@link TableSchema} in the {@link SchemaManager}.
   *
   * @param createStatement
   *          !null.
   */
  /*
   * @Deprecated public static void processCreateQuery(CreateTable
   * createStatement) { // null check if (createStatement == null ||
   * createStatement.getTable() == null) { throw new
   * IllegalArgumentException("Invalid Create Query"); }
   * 
   * final String tableName = createStatement.getTable().getName(); // add to
   * Schema Manager SchemaManager.addTableSchema(tableName, new
   * TableSchema(tableName, createStatement.getColumnDefinitions()));
   * 
   * }
   * 
   *//**
     * Processes a {@link Select} query.
     *
     * @param statement
     *          !null
     * @return
     * @throws Throwable
     *//*
       * @Deprecated public static Collection<Tuple> processSelectQuery(Select
       * statement) throws Throwable { final Node root = new TreeGenerator( new
       * TreeGenerator.Config(ApplicationConstants.DATA_DIR_PATH))
       * .evaluateSelect(statement); Collection<Tuple> tuples = root.getNext();
       * final Collection<Tuple> output = new ArrayList<>();
       * 
       * // move iteratively while (!tuples.isEmpty()) { output.addAll(tuples);
       * tuples = root.getNext(); } // while
       * 
       * return output; }
       */
  /**
   * Process the Tree.
   * 
   * @param root
   *          !null.
   * @return
   * @throws Throwable
   */
  public static Collection<Tuple> processTree(Node root) throws Throwable {
    Collection<Tuple> tuples = root.getNext();
    final Collection<Tuple> output = new ArrayList<>();

    // move iteratively
    while (!tuples.isEmpty()) {
      output.addAll(tuples);
      tuples = root.getNext();
    } // while

    return output;
  }

}
