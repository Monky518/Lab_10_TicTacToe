import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeRunner
{
    // Use JOptionPane to msg the user as needed for:
        // illegal moves
        // when the game is won or tied
        // or when the user quits

    // You also want to create a single listener instance for all the Buttons on the board
    // It should determine the row col position of the Button and interface with the code for the game logic

    String[][] board = new String[3][3];
    enum WinStatus
    {
        WIN,
        CLOSED,
        OPEN
    }
    Main.WinStatus[] winMethods = new Main.WinStatus[8];
    Scanner scan = new Scanner(System.in);
    String currentPlay;
    boolean validInput;
    boolean playAgain;
    boolean stillInSession;
    int row;
    int column;
    int roundCounter;

    TicTacToeFrame mainFrame = new TicTacToeFrame(400, 400);

    do
    {
        mainFrame.clearBoard();
        Arrays.fill(winMethods, Main.WinStatus.OPEN);
        roundCounter = 0;
        currentPlay = "X";
        playAgain = false;
        stillInSession = true;

        do
        {
            do
            {
                mainFrame.showBoard(currentPlay);
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
