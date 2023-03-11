import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner myScan = new Scanner(System.in);

    public HumanPlayer(char symbol, Board board, String name) {
        super(symbol, board, name);
    }
    

    @Override
    public void makeMove(Board board) {
        System.out.print(name + ", please enter your input: "); // Ask for input
        String move =  myScan.nextLine(); 
        int newMove = Integer.parseInt(move)-1; // Store input and subract by one to account for zero indexing
        // If user enters invalid move, ask them to re-input
        while(board.isFull(newMove)) {
            System.out.print("Column is full please try again: ");
            move =  myScan.nextLine();
            newMove = Integer.parseInt(move)-1;
        }
        // Set the board symbol to our players symbol and place piece
        board.setSymbol(this.symbol); 
        board.placePiece(newMove, this.symbol);
        
    }


    
}
