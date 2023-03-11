public class runner {
    public static void main(String[] args){
    
    Board board = new Board();
    ConnectFour game = new ConnectFour(board);
    game.setPlayer1(new AIPlayer('O', board, "Hashim"));
    game.setPlayer2(new HumanPlayer('X', board, "Elene"));
    game.playGame();
    
    
}



    
    }

    

