package tictactoe;
import java.util.Scanner;
//Implement a basic interface for 2 players to play Tic Tac Toe.
/*
X X X
X X X
X X X
State Management (Model): Manages state (in progress, won, loss)
Print board (View): Prints the board
Add board (controller): Add values to the board

Flow:
    Instantiate a game
    Create an empty board
    star the game.
Loop(!isOver):
    Print board;
    Ask a player for input
    change values on the board
    check if the game is over?
    Print board;

0 for empty
1 for X
2 for O
*/
public class TicTacToe {
    
    private int _ = 0;
    private int X = 1;
    private int O = 2;
    
    public boolean isPlayerOneTurn = true;
   
    private int[][] gameBoard = new int[3][3];
    
    public void TicTacToe(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                gameBoard[row][col] = _;
            }
        }
    }
    
    private boolean boardExhausted(){
        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
               if(gameBoard[row][col] == _)
                   return false;
            }
        }
        return true;
    }
    
    private int checkColumn(int columnIndex){
        if(gameBoard[0][columnIndex] == gameBoard[1][columnIndex] && gameBoard[2][columnIndex] == gameBoard[1][columnIndex]) 
            return gameBoard[0][columnIndex];
        else
            return _;
    }
    
    private int checkRow(int rowIndex){
        if(gameBoard[rowIndex][0] == gameBoard[rowIndex][1] && gameBoard[rowIndex][1] == gameBoard[rowIndex][2]) 
            return gameBoard[rowIndex][0];
        else
            return _;
    }
    
    private int checkPrimaryDiagonal(){
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) 
            return gameBoard[1][1];
        else
            return _;
    }
    
    private int checkSecondaryDiagonal(){
        if(gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0]) 
            return gameBoard[1][1];
        else
            return _;
    }
    
    public boolean isGameOver(){
        return boardExhausted() || gameWon() != _ ;
    }
    
    public int gameWon(){
       int result;
       for(int i = 0; i < 2; i++){
           result = checkColumn(i);
           if(result != _)  
               return result;
           
           result = checkRow(i);
           if(result != _) 
               return result;
       }
       
       result = checkPrimaryDiagonal();
       if(result != _)
           return result;
       
       result = checkSecondaryDiagonal();
       if(result != _)
           return result;
       
       return _;
    }
    
    public void play(int row, int col){
        
        int symbol;
        if(isPlayerOneTurn) 
            symbol = X;
        else
            symbol = O;
        
        gameBoard[row][col] = symbol;
        
        isPlayerOneTurn = !isPlayerOneTurn;
    }
    
    public void printBoard(){
       for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(gameBoard[row][col] == _)
                    System.out.print('_');
                else if(gameBoard[row][col] == X)
                    System.out.print('X');
                else
                    System.out.print('O');
                System.out.print(' ');
            }
            System.out.println(' ');
        }
    }
    
    public static void main(String[] args) {
        TicTacToe t = new TicTacToe();
        Scanner s = new Scanner(System.in);
        
        while(!t.isGameOver()){
            t.printBoard();
            if(t.isPlayerOneTurn)   
                System.out.println("Player X: ");
            else
                System.out.println("Player O: ");
              
            System.out.println("Enter Row");
            int row = s.nextInt();
            System.out.println("Enter Column");
            int col = s.nextInt();
            
            t.play(row, col);
            
        }
        
        int gameResult = t.gameWon();
        if(gameResult == 0)    
            System.out.println("It's a DRAW!");
        else if(gameResult == 1)
            System.out.println("Player X Won!");
        else
            System.out.println("Player O Won!");
            
    }
    
}
