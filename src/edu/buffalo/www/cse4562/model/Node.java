package edu.buffalo.www.cse4562.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.buffalo.www.cse4562.operator.Operator;
import edu.buffalo.www.cse4562.util.Validate;

/**
 * <pre>
 * Binary Tree Node where each Node represents the {@link Operator} it holds,
 * the class of the operator and its children.
 * <p/>
 * Each node is linked to an operator. An operator is oblivious of the Tree created
 * for a query and is only responsible for processing the Collection of {@link Tuple}
 * sent it for processing.
 * 
 * Each node invokes its operator's {@link Operator#process(Collection)} method
 * and passes the argument as its child's {@link #getNext()}.
 *  
 *    Node(Projection)
 *         |
 *         |
 *      Node(Select)
 *         |
 *         |
 *      Node(Scanner)
 * 
 * 
 * A node contains children, presently two children, a left child and a right child.
 * Ideally each node will have only one child but this shall change in scenario
 * for Cartesian Product and Union.
 *  
 *  TODO:
 *  Q. Why create two child, why not create an array of children for each node?
 *     An array gives more flexibility than a fixed left and right child.
 *     It avoids checking left and then right child, rather just iterate array,
 *     makes code simpler.
 * 
 * 
 * 
 * </pre>
 */
public class Node {

  private Class<? extends Operator> operatorType;
  private Operator operator;
  /*
   * private Node left; private Node right;
   */private boolean isLeaf = true;
  private List<Node> children = new ArrayList<>();

  /**
   * 
   * @param operator
   *          !null
   * @param operatorType
   *          !null
   * @throws IllegalArgumentException
   */
  public Node(Operator operator, Class<? extends Operator> operatorType) {
    // null check
    Validate.notNull(operator);
    Validate.notNull(operatorType);

    this.operator = operator;
    this.operatorType = operatorType;

  }

  public void addChild(Node child) {
    this.isLeaf = false;
    children.add(child);
  }

  public List<Node> getChildren() {
    return children;
  }

  public void setChildren(List<Node> children) {
    this.children = children;
  }

  public Class<? extends Operator> getOperatorType() {
    return operatorType;
  }
  public void setOperatorType(Class<? extends Operator> operatorType) {
    this.operatorType = operatorType;
  }
  public Operator getOperator() {
    return operator;
  }
  public void setOperator(Operator operator) {
    this.operator = operator;
  }

  /*
   * public Node getLeft() { return left; } public void setLeft(Node left) {
   * this.isLeaf = false; this.left = left; } public Node getRight() { return
   * right; } public void setRight(Node right) { this.isLeaf = false; this.right
   * = right; }
   */
  /**
   * This method is invoked to start execution for this node.
   * 
   * @return
   * @throws Throwable
   */
  public Collection<Tuple> getNext() throws Throwable {
    // if leaf node
    if (this.isLeaf) {
      return this.operator.process(null);
    }
    return this.operator.process(this.children.get(0).getNext());
  }
}