/*
 * James 'Logan' Piercefield
 * Program 2 - CSC 2710
 * 2 Stack PDA
 */
import java.util.*;

public class State 
{
  private int stateId;
  private ArrayList<Transition> transitions;

  public State(int stateId, String transitionsString)
  {
    this.stateId = stateId;

    String[] strTransitions = transitionsString.split(" ");
    this.transitions = new ArrayList<Transition>();

    for(String transition: strTransitions) 
    {
      this.transitions.add(new Transition(this, transition));
    }
  }


  public Transition[] getTransitions() 
  {
    return transitions.toArray(new Transition[0]);
  }

  public int getId()
  {
    return stateId;
  }


  public Transition getTransition(int id)
  {
    return transitions.get(id);
  }
}
