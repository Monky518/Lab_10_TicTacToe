import java.util.Scanner;

public class SafeInput
{
    /**
     * returns a String input by the user that must be at least one character
     * @param pipe the scanner to use for the input
     * @param prompt the message for the user telling them what to input
     * @return a String of at least one character
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        boolean done = false;
        String response = "";

        do
        {
            System.out.print(prompt + ": ");
            response = pipe.nextLine();
            if (response.length() > 0)
            {
                done = true;
            }
            else
            {
                System.out.println("\nYou must enter at least one character!\n");
            }
        } while(!done);

        return response;
    }

    /**
     * gets an int value from the user with no constraints
     * @param pipe Scanner to use for the input
     * @param prompt message to user for what to enter
     * @return any valid int
     */
    public static int getInt(Scanner pipe, String prompt)
    {
        boolean done = false;
        String trash = "";
        int value = 0;

        do
        {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt())
            {
                value = pipe.nextInt();
                pipe.nextLine(); //clear the buffer
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou entered " + trash + ", which is not a number!\n");

            }
        } while(!done);

        return value;
    }

    public static double getDouble(Scanner pipe, String prompt)
    {
        boolean done = false;
        String trash = "";
        double value = 0;

        do
        {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble())
            {
                value = pipe.nextDouble();
                pipe.nextLine(); //clear the buffer
                done = true;
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou entered " + trash + ", which is not a number!\n");
            }
        } while(!done);

        return value;
    }

    /**
     * gets an int val from the user with no constraints
     * @param pipe Scanner to user for the input
     * @param prompt msg to user for what to enter
     * @param low the bottom value for the inclusive range
     * @param high the top value for the inclusive range
     * @return valid input from high to low
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high)
    {
        boolean done = false;
        String trash = "";
        int value = 0;

        do
        {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt())
            {
                value = pipe.nextInt();
                pipe.nextLine(); //clear the buffer
                if(value >= low && value <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("\nYou entered " + value + ", which is not a part of the range!\n");
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou entered " + trash + ", which is not a number!\n");

            }
        } while(!done);

        return value;
    }

    /**
     * gets a double val with in an inclusive range
     * @param pipe Scanner to user for the input
     * @param prompt msg to user for what to enter
     * @param low the bottom bound for the inclusive range
     * @param high the top bound for the inclusive range
     * @return valid double input from high to low range
     */
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high)
    {
        boolean done = false;
        String trash = "";
        double value = 0;

        do
        {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble())
            {
                value = pipe.nextDouble();
                pipe.nextLine(); //clear the buffer
                if(value >= low && value <= high)
                {
                    done = true;
                }
                else
                {
                    System.out.println("\nYou entered " + value + ", which is not a part of the range!\n");
                }
            }
            else
            {
                trash = pipe.nextLine();
                System.out.println("\nYou entered " + trash + ", which is not a number!\n");

            }
        } while(!done);

        return value;
    }

    public static boolean getYNConfirm (Scanner pipe, String prompt)
    {
        boolean done = false;
        boolean retVal = false;
        String response = "";

        do
        {
            System.out.print(prompt + "[Y N]: ");
            response = pipe.nextLine();
            if (response.toUpperCase().matches("[YN]"))
            {
                done = true;
                if (response.equalsIgnoreCase("Y"))
                {
                    retVal = true;
                }
                else //response == N
                {
                    retVal = false;
                }
            }
            else
            {
                System.out.println("\nYou must enter yes or no! [Y N]\n");
            }
        } while(!done);

        return retVal;
    }

    /**
     * takes a regEx String and returns a valid match from the user
     * @param pipe scanner to use for input
     * @param prompt msg to user telling them what is needed for input
     * @param regEx a String that represents a Regular Expression to use for the test
     * @return a String value that matches the Regular Expression
     */
    public static String getRegExString(Scanner pipe, String prompt, String regEx)
    {
        boolean done = false;
        String response = "";

        do
        {
            System.out.print(prompt + " " + regEx + ": ");
            response = pipe.nextLine();
            if (response.matches(regEx))
            {
                done = true;
            }
            else
            {
                System.out.println("\nYou must enter a String that matches the pattern" + regEx + "!\n");
            }
        } while(!done);

        return response;
    }

    /**
     * creates a pretty header that is centered with a custom message
     * @param msg custom message for the header
     */
    public static void prettyHeader(String msg)
    {
        for (int i = 0; i < 60; i++)
        {
            System.out.print("*");
        }

        System.out.print("\n");

        int totalSpace = 54 - msg.length();
        int halfSpace = totalSpace / 2;
        for (int i = 0; i < 3; i++)
        {
            System.out.print("*");
        }
        for (int i = 0; i < halfSpace; i++)
        {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < halfSpace; i++)
        {
            System.out.print(" ");
        }
        if ((msg.length() % 2) == 1)
        {
            // odd, add a place at the end
            System.out.print(" ");
        }
        for (int i = 0; i < 3; i++)
        {
            System.out.print("*");
        }

        System.out.print("\n");

        for (int i = 0; i < 60; i++)
        {
            System.out.print("*");
        }
    }
}
