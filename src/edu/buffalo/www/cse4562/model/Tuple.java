package edu.buffalo.www.cse4562.model;

import java.util.Collection;

import edu.buffalo.www.cse4562.operator.Operator;
import edu.buffalo.www.cse4562.util.CollectionUtils;
import net.sf.jsqlparser.statement.create.table.ColDataType;

/**
 * This class represents a container for a single record returned by any
 * {@link Operator}. A tuple will hold several cell values pertaining to
 * different columns and data types.
 * 
 * Ideally I prefer a tuple consisting a collection of combination of values and
 * their associated data types. I also have a requirement of maintaining the
 * order of column cells as they appear in a row. An ordered collection like a
 * list should do that Q. How to handle null values? An array list can hold
 * multiple null values, this will be responsibility of the caller. example a
 * distinct operator may use a Set and selection operator may use an array list.
 * 
 * @author varunjai
 *
 */
public class Tuple {

  private final Collection<ColumnCell> columnCells;

  /**
   * 
   * @param columnCells
   *          can be empty.
   */
  public Tuple(Collection<ColumnCell> columnCells) {
    // Validate.notEmpty(columnCells);
    this.columnCells = columnCells;
  }

  public Collection<ColumnCell> getColumnCells() {
    return columnCells;
  }

  /**
   * Check if tuple is empty or not.
   * 
   * @return
   */
  public boolean isEmpty() {
    if (CollectionUtils.isEmpty(columnCells)) {
      return true;
    }
    return false;
  }

  /**
   * Represents one cell value.
   * 
   * @author varunjai
   *
   */
  public static class ColumnCell {
    private final String cellValue;
    private final ColDataType colDataType;
    private Integer tableId;
    private Integer columnId;

    /**
     * 
     * @param cellValue
     *          can be null.
     * @param colDataType
     *          can be null.
     */
    public ColumnCell(String cellValue, ColDataType colDataType) {
      this.cellValue = cellValue;
      this.colDataType = colDataType;
    }

    public String getCellValue() {
      return cellValue;
    }

    public ColDataType getColDataType() {
      return colDataType;
    }

    public Integer getTableId() {
      return tableId;
    }

    public void setTableId(Integer tableId) {
      this.tableId = tableId;
    }

    public Integer getColumnId() {
      return columnId;
    }

    public void setColumnId(Integer columnId) {
      this.columnId = columnId;
    }

  }

}