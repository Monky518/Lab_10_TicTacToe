import java.util.Scanner;

public class Main
{
    private static String[][] board = new String[3][3];

    enum GameStatus
    {
        win,
        tie,
        earlyTie,
        stillInSession
    }
    private static GameStatus currentGame = GameStatus.stillInSession;
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String currentPlay;
        boolean validInput;
        int row;
        int column;
        int roundCounter;

        do
        {
            clearBoard();
            showBoard();
            roundCounter = 0;
            currentPlay = "X";

            do
            {
                row = SafeInput.getRangedInt(scan, "Enter which row you want", 1, 3) - 1;
                column = SafeInput.getRangedInt(scan, "Enter which column you want", 1, 3) - 2;

                if (board[row][column].equals(" "))
                    validInput = true;
                else
                    validInput = false;

            } while(!validInput);

            roundCounter++;
            board[row][column] = currentPlay;

            if (currentPlay.matches("X"))
                currentPlay = "O";
            else
                currentPlay = "X";

            if (roundCounter >= 5)
                checkGameStatus(row, column, currentPlay);

            if (currentGame == GameStatus.win)
            {
                System.out.println("Player " + currentPlay + " is the winner!");
            }
            else if (currentGame == GameStatus.earlyTie)
            {
                System.out.println("This game is not going to have a winner, sorry");
            }
            else if (currentGame == GameStatus.tie)
            {
                System.out.println("The board is all tied up!");
            }

        } while(currentGame == GameStatus.stillInSession);
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

    public static void checkGameStatus(int row, int column, String player)
    {
        int faiCounter = 0;

        if (row == column || (row + column) == 2)
            diagonalWin(row, column, player);

        rowWin(row, player);
        columnWin(column, player);

    }

    /**
     * Checks for win or tie from a row win by setting the status
     * @param row the row with the new move
     * @return true if a complete method and false if stillInSession
     */
    public static boolean rowWin(int row, String player)
    {
        if (board[row][0].equals(player) && board[row][1].equals(player) && board[row][2].equals(player))
        {
            currentGame = GameStatus.win;
            return true;
        }
        else if (board[row][0].matches(" " + player) && board[row][1].matches(" " + player) && board[row][2].matches(" " + player))
        {
            currentGame = GameStatus.stillInSession;
            return false;
        }
        else
        {
            currentGame = GameStatus.earlyTie;
            return true;
        }
    }

    /**
     * Checks for win or tie from a column win by setting the status
     * @param column the column with the new move
     * @return true if a complete method and false if stillInSession
     */
    public static boolean columnWin(int column, String player)
    {
        if (board[0][column].equals(player) && board[1][column].equals(player) && board[2][column].equals(player))
        {
            currentGame = GameStatus.win;
            return true;
        }
        else if (board[0][column].matches(" " + player) && board[1][column].matches(" " + player) && board[2][column].matches(" " + player))
        {
            currentGame = GameStatus.stillInSession;
            return false;
        }
        else
        {
            currentGame = GameStatus.earlyTie;
            return true;
        }
    }

    /**
     * Checks for win or tie from a diagonal win by setting the status
     * @param row the row with the new move
     * @param column the column with the new move
     * @return true if a complete method and false if stillInSession
     */
    public static boolean diagonalWin(int row, int column, String player)
    {
        if (row == column)
        {
            // backslash
            if (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player))
            {
                currentGame = GameStatus.win;
                return true;
            }
            else if (board[0][0].matches(" " + player) && board[1][1].matches(" " + player) && board[2][2].matches(" " + player))
            {
                currentGame = GameStatus.stillInSession;
                return false;
            }
            else
            {
                currentGame = GameStatus.earlyTie;
                return true;
            }
        }
        else // if row + column == 2
        {
            // forward slash
            if (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player))
            {
                currentGame = GameStatus.win;
                return true;
            }
            else if (board[0][2].matches(" " + player) && board[1][1].matches(" " + player) && board[2][0].matches(" " + player))
            {
                currentGame = GameStatus.stillInSession;
                return false;
            }
            else
            {
                currentGame = GameStatus.earlyTie;
                return true;
            }
        }
    }
}
