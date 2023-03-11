import java.text.NumberFormat.Style;

public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	private char[][] grid;
	private char symbol;
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 * You may add private and public methods if you wish. In fact, to achieve
	 * what the assignment is asking, you'll have to
	 * 
	 */
	
	 
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}
	// Initializes the board with _ characters, assuming no player will have that symbol
	public Board() {
		grid = new char[NUM_OF_ROW][NUM_OF_COLUMNS];
		for(int i = 0; i<grid.length;i++){
			for(int j = 0; j<grid[i].length;j++){
				grid[i][j] = '_';
			
			}
		}
	}
	// Prints the board by printing the value at each index and printing lines between each columns
	public void printBoard() {
		for(int i = 0; i<grid.length;i++){
			System.out.print("|");
			for(int j = 0; j<grid[i].length;j++){
				System.out.print(grid[i][j]);
				System.out.print("|");
				
			}
			System.out.println();

		}
	}
	// Checks if a given column is full
	public boolean isFull(int move){
		// If the top entry of the column is empty, return that it isn't full		
		if(grid[0][move] == '_'){
			return false; 
		}
		return true;
	}
	// Places a piece in the given column with the given symbol
	public void placePiece(int move, char symbol){
		// Goes up the column from the bottom
		for(int i = 5; i >= 0; i --){
			// if the current space is empty, place a piece
			if (grid[i][move] == '_' ){ 
				grid[i][move] = symbol;
				break;

		}

	}
}
	// Checks each valid position(Left 4 columns) for a horizontal win
	private boolean checkHoriz(char symbol){
		for(int i =0; i<NUM_OF_COLUMNS - 3;i++){
			for(int j =0; j<NUM_OF_ROW;j++){
				if (grid [j][i] == symbol && grid [j][i+1] == symbol && grid [j][i+2] == symbol && grid[j][i+3] == symbol){ return true;}
			}
		}
		return false;
	}
	// Checks each valid position(Bottom 4 rows) for a vertical win
	private boolean checkVert(char symbol){
		for(int i =0; i<NUM_OF_COLUMNS;i++){
			for(int j =0; j<NUM_OF_ROW-3;j++){
				if (grid [j][i] == symbol && grid [j+1][i] == symbol && grid [j+2][i] == symbol && grid[j+3][i] == symbol){ return true;}
			}
		}
		return false;
	}
	// Checks each valid position(top left diagonal of board) for a vertical win
	private boolean checkBackDiag(char symbol){
		for(int i =0; i<NUM_OF_COLUMNS - 3;i++){
			for(int j =3; j<NUM_OF_ROW;j++){
				if (grid [j][i] == symbol && grid [j-1][i+1] == symbol && grid [j-2][i+2] == symbol && grid[j-3][i+3] == symbol){ return true;}
			}
		}
		return false;

	}// Checks each valid position(bottom right diagonal of board) for a vertical win
	private boolean checkForDiag(char symbol){
		for(int i =0; i<NUM_OF_COLUMNS - 3;i++){
			for(int j =0; j<NUM_OF_ROW - 3;j++){
				if (grid [j][i] == symbol && grid [j+1][i+1] == symbol && grid [j+2][i+2] == symbol && grid[j+3][i+3] == symbol){ return true;}
			}
		}
		return false;

	}
	public boolean containsWin() {
		
		if (checkHoriz(symbol)){return true;}
		if (checkVert(symbol)){return true;}
		if (checkBackDiag(symbol)){return true;}
		if (checkForDiag(symbol)){return true;}
		return false;
	}
	public boolean containsOpWin(char opSymbol) {
		
		if (checkHoriz(opSymbol)){return true;}
		if (checkVert(opSymbol)){return true;}
		if (checkBackDiag(opSymbol)){return true;}
		if (checkForDiag(opSymbol)){return true;}
		return false;
	}

	// Checks if the ai has the winning board
	public boolean aiWin(){
		// Goes through each entry in the board
		for(int i = 0; i<grid.length;i++){
			for(int j = 0; j<grid[i].length;j++){
				if (grid[i][j] == '_') {
					// If the current entry is empty store it and reassign it to the AI symbol
					char temp = grid[i][j]; 
					grid[i][j] = symbol;
					// If the board doesn't contain a win, make the space empty again
					if(!this.containsWin()){
						grid[i][j] = temp;
					}
					else{
						// If it does contain a win, make that entry empty and use the placePiece function to place the symbol in the column
						// and return succesful
						grid[i][j] = temp;
						this.placePiece(j, symbol);
						return true;
					}
				}
			}
		}
		// If there was no win for ai, return not successful 
		return false;
		
	}

	// Gets the oppoenent symbol for AI player
	public char getOpSymbol(){
		for(int i = 0; i<grid.length;i++){
			for(int j = 0; j<grid[i].length;j++){
				if(grid[i][j] != '_' && grid[i][j] != symbol){ 
					return grid[i][j];
				}
			}
		}
		return symbol;		
	}
	public boolean aiBlock(char opSymbol){
		for(int i = 0; i<grid.length;i++){
			// Goes through each entry, if the entry is empty replace with opponent symbol
			// If it results in a win, replace it with the AI Symbol to block
			for(int j = 0; j<grid[i].length;j++){
				if (grid[i][j] == '_') {
					char temp = grid[i][j];
					grid[i][j] = opSymbol;
					if(!this.containsOpWin(opSymbol)){
						grid[i][j] = temp;
					}
					else{
						grid[i][j] = temp;
						this.placePiece(j, symbol); 
						return true;
					}
				}
			}
		}
		return false;
	}

	
	
	// Checks if each column is full and if theres no win to check for a tie
	public boolean isTie() {
		if( this.isFull(0) && this.isFull(1) && this.isFull(2) 
			&& this.isFull(3) && this.isFull(4) && this.isFull(5) 
			&& this.isFull(6) && !this.containsWin()){
				return true;
		}
		else{
		return false;
		}
	}
	// Reinitializes the board to empty 
	public void reset() {
		for(int i = 0; i<grid.length;i++){
			for(int j = 0; j<grid[i].length;j++){
				grid[i][j] = '_';
			
			}
		}
	}
	
}
