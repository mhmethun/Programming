
package chessgame.pkgfinal;

/*
 * Keeps information of a single chess piece
 * Name holds the name of the piece
 * xPos, yPos holds the piece position
 * status means does this piece is on the board or aleady taken
 * color holds the color information of that piece
 */
public class ChessPiece {
    private String name;
    private int xPos;
    private int yPos;
    private boolean status;
    private String color;
    
    public ChessPiece(String name, int x, int y, String color){
        this.name = name;
        this.xPos = x;
        this.yPos = y;
        this.status = true;
        this.color = color;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getPositionX(){
        return this.xPos;
    }
    
    public int getPositionY(){
        return this.yPos;
    }
    
    public void setPosition(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }
    
    public void setStatus(boolean stat){
        this.status = stat;
    }
    
    public boolean getStatus(){
        return this.status;
    }
    
    public String getColor(){
        return this.color;
    }
}
