
package chessgame.pkgfinal;

/*
 * Parent class holds the color information
 */
public class Person {

    String color;
    
    public Person(String col) {
        this.color = col;
    }
    
    public String getPlayerColor(){
        return this.color;
    }
}
