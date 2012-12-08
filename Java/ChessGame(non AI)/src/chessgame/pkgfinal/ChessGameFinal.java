
package chessgame.pkgfinal;

public class ChessGameFinal {

    public static void main(String[] args) {
        try{
            PlayGame game = new PlayGame();
            
            game.startGame();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
