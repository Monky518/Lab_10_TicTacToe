import java.util.Scanner;

public class oldMain
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
                    if (rowWin(row) || colWin(col) || diaWin())
                    {
                        System.out.println("O wins!");
                        done = true;
                    }
                    else if (possibleContinue())
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
                    else if (possibleContinue())
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
        int[] emptyRow = new int[9];
        int[] emptyCol = new int[9];
        int emptyCounter = 0;
        boolean possible = false;

        // finds empty space to search with later
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == " ")
                {
                    emptyRow[emptyCounter] = i;
                    emptyCol[emptyCounter] = j;

                    emptyCounter++;
                }
            }
        }

        // searching time :)
        for (int i = 0; i <= emptyCounter; i++)
        {
            if (board[emptyRow[i]][0] == board[emptyRow[i]][1] || board[emptyRow[i]][0] == board[emptyRow[i]][2])
            {
                possible = true;
                System.out.println("DEBUG: found valid move in row " + emptyRow[i]);
            }
            else if (board[0][emptyCol[i]] == board[1][emptyCol[i]] || board[0][emptyCol[i]] == board[2][emptyCol[i]])
            {
                possible = true;
                System.out.println("DEBUG: found valid move in col " + emptyCol[i]);
            }
            else if (topLeftDiagonal(emptyRow[i], emptyCol[i]))
            {
                if (board[0][0].matches(board[1][1]) || board[0][0].matches(board[2][2]) || board[1][1].matches(board[2][2]))
                {
                    possible = true;
                    System.out.println("DEBUG: found valid move from topL to botR");
                }
            }
            else if (topRightDiagonal(emptyRow[i], emptyCol[i]))
            {
                // (row == 0 && col == 2) || (row == 1 && col == 1) || (row == 2 && col == 0)
                if (board[0][2].matches(board[1][1]) || board[0][2].matches(board[2][0]) || board[1][1].matches(board[2][0]))
                {
                    possible = true;
                    System.out.println("DEBUG: found valid move from topL to botR");
                }
            }
        }

        return possible;
    }

    public static boolean topLeftDiagonal(int row, int col)
    {
        if ((row == 0 && col == 0) || (row == 1 && col == 1) || (row == 2 && col == 2))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean topRightDiagonal(int row, int col)
    {
        if ((row == 0 && col == 2) || (row == 1 && col == 1) || (row == 2 && col == 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}