import java.util.Scanner;

public class Main
{
    private static String[][] board = new String[3][3];

    public static void main(String[] args)
    {
        String currentPlay;
        Scanner scan = new Scanner(System.in);
        int moveCounter = 0;
        int row;
        int col;
        boolean validMove = false;
        boolean playAgain = false;
        boolean done = false;

        do
        {
            // reset the game
            clearBoard();
            done = false;
            currentPlay = "X";

            do
            {
                showBoard();

                // checks for valid user input
                do
                {
                    row = SafeInput.getRangedInt(scan, "Enter the row you want", 1, 3) - 1;
                    col = SafeInput.getRangedInt(scan, "Enter the column you want", 1, 3) - 1;

                    if (board[row][col].matches(" "))
                        validMove = true;
                    else
                    {
                        validMove = false;
                        System.out.println("\nThat spot is already taken, pick another one\n");
                    }

                } while(!validMove);

                // uses the valid user input
                board[row][col] = currentPlay;
                moveCounter++;

                // change current play
                if (currentPlay.matches("X"))
                    currentPlay = "O";
                else
                    currentPlay = "X";

                // game check
                if (moveCounter == 6 || moveCounter == 8)
                {
                    if (rowWin(row, true) || colWin(col, true) || diaWin(true))
                    {
                        System.out.println("O wins!");
                        done = true;
                    }
                    else if (rowWin(row, false) || colWin(col, false) || diaWin(false))
                    {
                        // game continues
                        done = false;
                    }
                    else
                    {
                        System.out.println("No one can win this game");
                        done = true;
                    }
                }
                else if (moveCounter == 5 || moveCounter == 7)
                {
                    if (rowWin(row) || colWin(col) || diaWin())
                    {
                        System.out.println("X wins!");
                        done = true;
                    }
                    else if (rowWin(row) || colWin(col) || diaWin())
                    {
                        // game continues
                        done = false;
                    }
                    else
                    {
                        System.out.println("No one can win this game");
                        done = true;
                    }
                }
                else if (moveCounter == 9)
                {
                    if (rowWin(row) || colWin(col) || diaWin())
                    {
                        System.out.println("X wins!");
                        done = true;
                    }
                    else
                    {
                        System.out.println("Board is full without a winner");
                        done = true;
                    }
                }

            } while(!done);

            playAgain = SafeInput.getYNConfirm(scan, "Do you want to play again?");

        } while(playAgain);
    }

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

    public static void showBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            System.out.printf("| %s | %s | %s |\n", board[i][0], board[i][1], board[i][2]);
        }
    }

    public static boolean colWin(int col)
    {
        if (board[0][col].matches(board[1][col]) && board[0][col].matches(board[2][col]))
            return true;
        else
            return false;
    }

    public static boolean rowWin(int row)
    {
        if (board[row][0].matches(board[row][1]) && board[row][0].matches(board[row][2]))
            return true;
        else
            return false;
    }

    public static boolean diaWin()
    {
        if (board[0][0].matches(board[1][1]) && board[0][0].matches(board[2][2]))
            return true;
        else if (board[0][2].matches(board[1][1]) && board[0][2].matches(board[2][0]))
            return true;
        else
            return false;
    }

    public static boolean possibleContinue()
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0].matches(board[i][1]) || board[i][0].matches(board[i][2]) || board[i][2].matches(board[i][1]))
            {
                return true;
                break;
            }
            else if (board[0][i].matches(board[1][i]) || board[0][i].matches(board[2][i]) || board[2][i].matches(board[1][i]))
            {
                return true;
                break;
            }
            else
            {
                if (board[0][0].matches(board[1][1]) || board[0][0].matches(board[2][2]))
            }
        }
    }
}