package edu.buffalo.www.cse4562;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;

import org.junit.Test;

import edu.buffalo.www.cse4562.model.Node;
import edu.buffalo.www.cse4562.model.SchemaManager;
import edu.buffalo.www.cse4562.query.QueryProcessor;
import edu.buffalo.www.cse4562.query.QueryVisitor;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.ParseException;

/**
 * Test cases for {@link QueryProcessor}.
 * 
 * @author varunjai
 *
 */
public class TestQueryProcessor {

  
  @Test
  public void testCreateQuery1() throws ParseException {
    final CCJSqlParser parser = new CCJSqlParser(
        new StringReader("CREATE TABLE R(A int, B int, C int);"));

    final QueryVisitor queryVisitor = new QueryVisitor();
    parser.Statement().accept(queryVisitor);

    assertTrue(SchemaManager.getTableSchema("R") != null);

  }

  /**
   * Test simple select query w/o where clause.
   * 
   * @throws Throwable
   */
  @Test
  public void testSimpleSelect1() throws Throwable {
    CCJSqlParser parser = new CCJSqlParser(
        new StringReader("CREATE TABLE R(A int, B int, C int);"));

    final QueryVisitor queryVisitor = new QueryVisitor();
    parser.Statement().accept(queryVisitor);

    assertTrue(SchemaManager.getTableSchema("R") != null);

    parser = new CCJSqlParser(new StringReader("SELECT A + B FROM R;"));
    parser.Statement().accept(queryVisitor);

    // get the tree
    final Node root = queryVisitor.getRoot();

    
    assertEquals(10, QueryProcessor.processTree(root).size());

  }
  
}
