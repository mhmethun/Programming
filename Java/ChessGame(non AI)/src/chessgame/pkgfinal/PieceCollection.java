
package chessgame.pkgfinal;

import java.util.ArrayList;

/*
 * This class makes 16 piece of black and 16 piece of white
 */
public class PieceCollection {
    
    public static ArrayList<ChessPiece> wlist;
    public static ArrayList<ChessPiece> blist;
    
    public PieceCollection() {
        wlist = new ArrayList<ChessPiece>(16);
        blist = new ArrayList<ChessPiece>(16);
    }
    
    public void Initialize(){
         try{
            
            wlist.add(new ChessPiece("Rooks", 0, 0, "white"));
            wlist.add(new ChessPiece("Knights", 0 , 1, "white"));
            wlist.add(new ChessPiece("Bishops", 0 , 2, "white"));
            wlist.add(new ChessPiece("King", 0, 3, "white"));
            wlist.add(new ChessPiece("Queen", 0, 4, "white"));
            wlist.add(new ChessPiece("Bishops", 0, 5, "white"));
            wlist.add(new ChessPiece("Knights", 0, 6, "white"));
            wlist.add(new ChessPiece("Rooks", 0, 7, "white"));
            // Place all powns
            for(int i=1; i < 2; i++){
                for(int j=0; j<8; j++){
                    wlist.add(new ChessPiece("Powns", i, j, "white"));
                }
            }
            
            blist.add(new ChessPiece("Rooks", 7, 0, "black"));
            blist.add(new ChessPiece("Knights", 7 , 1, "black"));
            blist.add(new ChessPiece("Bishops", 7 , 2, "black"));
            blist.add(new ChessPiece("Queen", 7, 3, "black"));
            blist.add(new ChessPiece("King", 7, 4, "black"));
            blist.add(new ChessPiece("Bishops", 7, 5, "black"));
            blist.add(new ChessPiece("Knights", 7, 6, "black"));
            blist.add(new ChessPiece("Rooks", 7, 7, "black"));
            // Place all powns
            for(int i=6; i < 7; i++){
                for(int j=0; j<8; j++){
                    blist.add(new ChessPiece("Powns", i, j, "black"));
                }
            }
        }
        catch(Exception ex){
            System.out.println("Error: " + ex);
        }
         
    }
    
    /*
     * This method print the board with its piece.
     * It only take consideration those pieces that are on the board and not taken
     */
    public void printbord() {
        ChessPiece piece = null;
        System.out.println("Bord on the Game is: \n\n");
        for (int i = 7; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                piece = this.getChessPiece(i, j);
                
                if(piece == null){
                    System.out.format("%-10s", "EMPTY");
                }
                else if(!piece.getStatus()){
                    System.out.format("%-10s", "EMPTY");
                }
                else
                    System.out.format("%-10s", piece.getColor().charAt(0) + piece.getName());
            }
             System.out.println();
        }
    }
    
    // Search on both white and black array list of pieces to get the appropriate positioned piece
    public ChessPiece getChessPiece(int x, int y){
        ChessPiece piece = null;
        
        for(int i=0; i<blist.size(); i++){
            ChessPiece tempPiece = blist.get(i);
            
            if(tempPiece.getPositionX() == x && tempPiece.getPositionY() == y && tempPiece.getStatus()){
                piece = tempPiece;
                
                return piece;
            }
        }
        
        for(int i=0; i<wlist.size(); i++){
            ChessPiece tempPiece = wlist.get(i);
            
            if(tempPiece.getPositionX() == x && tempPiece.getPositionY() == y && tempPiece.getStatus()){
                piece = tempPiece;
                
                return piece;
            }
        }
        
        return piece;
    }
    
    // Get all taken piece from supplied arraylist
    public void getTotalTakenPiece(ArrayList<ChessPiece> piece){
        
        for(int i=0; i<piece.size(); i++){
            ChessPiece tempPiece = piece.get(i);
            
            if(!tempPiece.getStatus()){
                System.out.format("%-10s", tempPiece.getColor().charAt(0) + tempPiece.getName());
            }
        }
        System.out.println();
    }
    
    // Get all live piece from supplied arraylist
    public void getTotalPieceInTheBoard(ArrayList<ChessPiece> piece){
        
        for(int i=0; i<piece.size(); i++){
            ChessPiece tempPiece = piece.get(i);
            
            if(tempPiece.getStatus()){
                System.out.format("%-10s", tempPiece.getColor().charAt(0) + tempPiece.getName());
            }
        }
        System.out.println();
    }
}
