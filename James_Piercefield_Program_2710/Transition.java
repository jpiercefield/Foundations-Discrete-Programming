/*
 * James 'Logan' Piercefield
 * Program 2 - CSC 2710
 * 2 Stack PDA
 */
import java.util.*;

public class Transition 
{
  public State state;
  public int newState;
  public String pop1;
  public String push1;
  public String pop2;
  public String push2;

  public Transition(State state, String transitionStr) 
  {
    // int newState, String pop1, String push1, String pop2, String push2
    this.state = state;

    String[] parts = transitionStr.split(",");
    this.newState = Integer.parseInt(parts[0]);
    this.pop1 = parts[1];
    this.push1 = parts[2];
    this.pop2 = parts[3];
    this.push2 = parts[4];
  }
}
