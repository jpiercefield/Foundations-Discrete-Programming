/*
 * James 'Logan' Piercefield
 * Program 2 - CSC 2710
 * 2 Stack PDA
 */
import java.util.*;

public class MyStack 
{
  private Stack<String> stack;

  public MyStack() 
  {
    stack = new Stack<String>();
  }

  public void pop() 
  {
    stack.pop();
  }

  public void push(String str) 
  {
    stack.push(str);
  }

  public String peek() 
  {
    return stack.peek();
  }

  public boolean isEmpty() 
  {
    return stack.empty();
  }

  public String toString() 
  {
    return Arrays.toString(stack.toArray());
  }
}
