import javax.swing.plaf.synth.SynthButtonUI;
import java.util.Random;

public class AIPlayer extends Player{

    public AIPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }

    @Override
    public void makeMove(Board board) {
        board.setSymbol(this.symbol); // Sets boards symbol to AIplayer symbol
        boolean winCheck = board.aiWin(); // Checks if theres a possible winning play
        if (winCheck == false){ // if there isnt a winning play 
            char opSymbol = board.getOpSymbol();
            boolean blockCheck = board.aiBlock(opSymbol); // Check for a possible blocking play
            if(blockCheck == false){ // if there isn't a blocking play
                // Choose a random column and place a piece there for the AI, try again if the random column is full
                Random rand = new Random();
                int randy = rand.nextInt(7);
                while(board.isFull(randy)){
                    randy = rand.nextInt(7);
                }
                board.placePiece(randy, this.symbol);
            }
        }
        
        
        
        
    }
    
}
