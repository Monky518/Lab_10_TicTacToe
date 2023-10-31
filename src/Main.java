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
                row = SafeInput.getRangedInt(scan, "Enter which row you want", 1, 3);
                column = SafeInput.getRangedInt(scan, "Enter which column you want", 1, 3);

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
                checkGameStatus();

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

    public static void checkGameStatus()
    {
        boolean[] completedWinMethod = new boolean[8];


    }
}
