import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    private static String[][] board = new String[3][3];

    enum WinStatus
    {
        WIN,
        CLOSED,
        OPEN
    }
    private static WinStatus[] winMethods = new WinStatus[8];

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String currentPlay;
        boolean validInput;
        boolean playAgain;
        boolean stillInSession;
        int row;
        int column;
        int roundCounter;

        do
        {
            clearBoard();
            Arrays.fill(winMethods, WinStatus.OPEN);
            roundCounter = 0;
            currentPlay = "X";
            playAgain = false;
            stillInSession = true;

            do
            {
                do
                {
                    showBoard();
                    row = SafeInput.getRangedInt(scan, "Enter which row you want", 1, 3) - 1;
                    column = SafeInput.getRangedInt(scan, "Enter which column you want", 1, 3) - 1;

                    if (board[row][column].equals(" "))
                        validInput = true;
                    else
                    {
                        validInput = false;
                        System.out.println("\nThat spot is already taken by " + board[row][column]);
                    }

                } while(!validInput);

                roundCounter++;
                board[row][column] = currentPlay;

                if (roundCounter >= 5)
                    stillInSession = checkGameStatus(currentPlay);

                currentPlay = changePlayer(currentPlay);

            } while(stillInSession);

            playAgain = SafeInput.getYNConfirm(scan, "\nDo you want to play again?");

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

    public static boolean checkGameStatus(String player)
    {
        int closedCounter = 0;

        rowWin(player);
        columnWin(player);
        diagonalWin(player);

        for (WinStatus test : winMethods)
        {
            if (test == WinStatus.WIN)
            {
                showBoard(); // the winner can see their winning board
                System.out.println("\nPlayer " + player + " is the winner!");
                return false;
            }
            else if (test == WinStatus.CLOSED)
                closedCounter++;
        }

        if (closedCounter == 8)
        {
            showBoard(); // they can see their tie
            System.out.println("\nThe board is all tied up!");
            return false;
        }

        return true;
    }

    public static void rowWin(String playerTest)
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0].equals(playerTest) && board[i][1].equals(playerTest) && board[i][2].equals(playerTest))
                winMethods[i] = WinStatus.WIN;
            else if (!board[i][0].matches(" |" + playerTest) || !board[i][1].matches(" |" + playerTest) || !board[i][2].matches(" |" + playerTest))
                winMethods[i] = WinStatus.CLOSED;
            else
                winMethods[i] = WinStatus.OPEN;
        }
    }

    public static void columnWin(String playerTest)
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i].equals(playerTest) && board[1][i].equals(playerTest) && board[2][i].equals(playerTest))
                winMethods[i + 3] = WinStatus.WIN;
            else if (!board[0][i].matches(" |" + playerTest) || !board[1][i].matches(" |" + playerTest) || !board[2][i].matches(" |" + playerTest))
                winMethods[i + 3] = WinStatus.CLOSED;
            else
                winMethods[i + 3] = WinStatus.OPEN;
        }
    }

    public static void diagonalWin(String playerTest)
    {
        // backslash
        if (board[0][0].equals(playerTest) && board[1][1].equals(playerTest) && board[2][2].equals(playerTest))
            winMethods[6] = WinStatus.WIN;
        else if (!board[0][0].matches(" |" + playerTest) || !board[1][1].matches(" |" + playerTest) || !board[2][2].matches(" |" + playerTest))
            winMethods[6] = WinStatus.CLOSED;
        else
            winMethods[6] = WinStatus.OPEN;

        // forward slash
        if (board[0][2].equals(playerTest) && board[1][1].equals(playerTest) && board[2][0].equals(playerTest))
            winMethods[7] = WinStatus.WIN;
        else if (!board[0][2].matches(" |" + playerTest) || !board[1][1].matches(" |" + playerTest) || !board[2][0].matches(" |" + playerTest))
            winMethods[7] = WinStatus.CLOSED;
        else
            winMethods[7] = WinStatus.OPEN;
    }

    public static String changePlayer(String player)
    {
        if (player.matches("X"))
            player = "O";
        else
            player = "X";

        return player;
    }
}
