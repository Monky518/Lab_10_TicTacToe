import java.util.Scanner;

public class Main
{
    // initiate board here
    private static String[][] board = new String[3][3];

    public static void main(String[] args)
    {
        String currentPlay = "X";
        Scanner scan = new Scanner(System.in);
        int moveCounter = 0;
        int row;
        int col;
        boolean playAgain = false;
        boolean validMove = false;

        // clear the board
        clearBoard();

        do
        {
            // show the board
            showBoard();

            do
            {
                // get coords for move (1 - 3 converted to 0 - 2)
                row = SafeInput.getRangedInt(scan, "Enter the row you want", 1, 3) - 1;
                col = SafeInput.getRangedInt(scan, "Enter the column you want", 1, 3) - 1;

                // checks for valid move
                if (board[row][col].matches(" "))
                    validMove = true;
                else
                {
                    validMove = false;
                    System.out.println("\n That spot is already taken, pick another one");
                }
            } while (!validMove);

            // set the move
            board[row][col] = currentPlay;

            // update counter
            moveCounter++;

            // updates currentPlay
            if (currentPlay.matches("X"))
                currentPlay = "O";
            else
                currentPlay = "X";

                // if else
                // when move count == 9, tie
                // when move count == 7, check for early tie (check for X and O in each win possibility)
                // when move count >= 5, check for win
            // change from X to O or O to X
                // if else
                // when winner or tie, ask for play again
        // play again if player indicated
        } while(!playAgain);
    }

    // clear board method
    public static void clearBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                board[i][j] = " ";
            }
        }
    }

    // show board method
    public static void showBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("| %s | %s | %s |\n", board[i][0], board[i][1], board[i][2]);
        }
    }

    // check for valid move method
    public static void validMove(int row, int col)
    {

    }

    // col win method

    // row win method

    // diagnal win method
}