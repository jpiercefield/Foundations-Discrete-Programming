/*
 * James 'Logan' Piercefield
 * Program 2 - CSC 2710
 * 2 Stack PDA
 */

import java.util.*;

public class PDA 
{
    private ArrayList<State> states;
    private String[] alphabet;
    private int[]    finalStates;
    private int      currentState;

    /***
    Constructor for Push Down Automaton

    @param alphabet    The alphabet of this automaton
    @param states      The array of states in ordered fashion containing all transitions info
    @param acceptState The final state ids/indexes of this automaton
     **/
    public PDA(String[] alphabet, ArrayList<State> states, int[] acceptStates)
    {
        this.currentState = 0;
        this.alphabet = alphabet;
        this.states = states;
        this.finalStates = acceptStates;
    }

    public boolean testString(String str, boolean verbose) 
    {
        currentState = 0;
        MyStack stack1 = new MyStack();
        MyStack stack2 = new MyStack();
        Transition transition;

        // for each letter in test string
        for(char cLetter: str.toCharArray()) 
        {
            String letter = ""+cLetter;

            if(verbose) 
            {
                System.out.println("\nCurrent state: "+currentState);
                System.out.println("Stack#1: "+stack1);
                System.out.println("Stack#2: "+stack2);
            }

            transition = findTransition(letter, stack1, stack2);

            if(transition == null) 
            {
                System.out.println("Transition not found!");
                return false;
            } else {
                State state = getCurrentState();

                if(!transition.push1.equals("!"))
                {
                    stack1.push(transition.push1);
                }

                if(!transition.push2.equals("!"))
                {
                    stack2.push(transition.push2);
                }

                currentState = transition.newState;
            }
        }

        if(verbose)
        {
            System.out.println("\nCurrent state: "+currentState);
            System.out.println("Stack#1: "+stack1);
            System.out.println("Stack#2: "+stack2);
        }

        return (stack1.isEmpty() && stack2.isEmpty() && isAtAcceptState());
    }

    private Transition findTransition(String letter, MyStack stack1, MyStack stack2) 
    {
        int index = getIndex(letter, alphabet);

        if(index < 0)
        {
            return null;
        }

        State state = getCurrentState();
        Transition transition = state.getTransition(index); // Get transition from current state

        String pop1 = transition.pop1;
        String pop2 = transition.pop2;

        if(!pop1.equals("!")) 
        {
            if(stack1.isEmpty())
            {
                return null;
            } else {
                if(stack1.peek().equals(pop1))
                {
                    stack1.pop();
                } else {
                    return null;
                }
            }
        }

        if(!pop2.equals("!")) 
        {
            if(stack2.isEmpty())
            {
                return null;
            } else {
                if(stack2.peek().equals(pop2))
                {
                    stack2.pop();
                } else {
                    return null;
                }
            }
        }

        return transition;
    }

    private State getCurrentState()
    {
        return states.get(currentState);
    }

    private boolean isAtAcceptState()
    {
        for(int state: finalStates)
        {
            if(currentState == state)
            {
                return true;
            }
        }

        return false;
    }

    private int getIndex(String value, String[] arr)
    {
        for(int i = 0; i < arr.length; i++) 
        {
            if(arr[i].equals(value))
            {
                return i;
            }
        }

        return -1;
    }
}
