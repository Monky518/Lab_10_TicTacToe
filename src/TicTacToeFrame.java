import javax.swing.*;

public class TicTacToeFrame
{
    JTextArea promptText = new JTextArea("");

    public TicTacToeFrame(int width, int height)
    {
        JFrame mainFrame = new JFrame();

        mainFrame.setSize(width, height);
        mainFrame.setTitle("Tic Tac Toe");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public void showBoard(String playerPiece)
    {
        // update the buttons and prompts player (X or O) to choose
        promptText.setText("Your turn player " + playerPiece);
    }

    public void clearBoard()
    {
        // double check to restart
        // if yes, restart
    }
}
