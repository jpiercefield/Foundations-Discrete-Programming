/*
 * James 'Logan' Piercefield
 * Program 2 - CSC 2710
 * 2 Stack PDA
 */

import java.util.*;
import java.io.*;

public class Main 
{
    public static void main(String args[]) throws Exception 
    {
        System.out.println("Two Stack PDA Tester\n====================");
        
        PDA pda;
        Scanner input = new Scanner(System.in);
        String fileName, testString, line;
        boolean result;

        while(true) 
        {
            System.out.print("\nEnter the name of file to read: ");
            fileName = input.nextLine().trim();
            pda = readPDAFromFile(fileName);

            while(true) 
            {
                System.out.println("\nSuccessfully created a Two Stack PDA from the file!");
                System.out.print("\nEnter a string to test: ");
                testString = input.nextLine().trim();

                System.out.print("Choose mode: \n1 - Fast\n2 - Step By Step\nEnter mode: ");
                line = input.nextLine().trim();

                if(line.equals("1"))
                {
                    System.out.println("\n-----Fast Mode-----");
                } else if(line.equals("2")) {
                    System.out.println("\n-----Step By Step Mode-----");
                }
                
                result = pda.testString(testString, line.equals("2"));

                if(result)
                {   
                    System.out.println("\nString was Accepted!\n");
                } else {
                    System.out.println("\nString was Rejected!\n");
                }

                System.out.print("Test another string? [Y/n]: "); //anything but N/n
                line = input.nextLine().trim();

                if(line.toLowerCase().equals("n"))
                {
                    break; //all
                }
            }

            System.out.print("Load from another PDA file? [Y/n]: "); //anything but N/n
            line = input.nextLine().trim();

            if(line.toLowerCase().equals("n"))
            {
                break; //all
            }
        }

        input.close();
        System.out.println("\nGoodbye!");
    }

    private static PDA readPDAFromFile(String fileName) throws Exception
    {
        Scanner s = new Scanner(new File(fileName));

        String[] alphabet = s.nextLine().trim().split(" ");
        int   numberOfStates = Integer.parseInt(s.nextLine().trim());
        String[] finalStates = s.nextLine().trim().split(" ");
        int[] intFinalStates = new int[finalStates.length];

        for(int i = 0; i < finalStates.length; i++)
        {
            intFinalStates[i] = Integer.parseInt(finalStates[i]);
        }

        String line = null;
        int i = 0;
        ArrayList<State> states = new ArrayList<State>();

        while(s.hasNextLine()) 
        {
            line = s.nextLine();
            if(line.trim().length() > 0)
            {
                states.add(new State(i, line));
            }
            i++;
        }

        s.close();

        return new PDA(alphabet, states, intFinalStates);
    }
}
