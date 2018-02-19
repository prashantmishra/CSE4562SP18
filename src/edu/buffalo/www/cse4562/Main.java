package edu.buffalo.www.cse4562;

import edu.buffalo.www.cse4562.model.Node;
import edu.buffalo.www.cse4562.model.Tuple;
import edu.buffalo.www.cse4562.query.QueryProcessor;
import edu.buffalo.www.cse4562.query.QueryVisitor;
import edu.buffalo.www.cse4562.util.ApplicationConstants;
import edu.buffalo.www.cse4562.util.TuplePrinter;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.statement.Statement;

public class Main {

  public static void main(String[] args) throws Throwable {
    // prompt
    System.out.println(ApplicationConstants.BASH);
    System.out.flush();

    final CCJSqlParser parser = new CCJSqlParser(System.in);
    Statement statement = parser.Statement();

    while (statement != null) {

      // process query to generate Tree
      final QueryVisitor queryVisitor = new QueryVisitor();
      statement.accept(queryVisitor);

      // get the tree
      final Node root = queryVisitor.getRoot();

      // if a SELECT
      if (null != root) {

        for (final Tuple tuple : QueryProcessor.processTree(root)) {
          TuplePrinter.printTuple(tuple);
        }
      }// if

      // prompt
      System.out.println(ApplicationConstants.BASH);
      System.out.flush();
      
      statement = parser.Statement();

    }// while
  }

}
