/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chessgame.pkgfinal;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class PlayGame {

    private Movement move;
    private WhitePlayer wPlayer;
    private BlackPlayer bPlayer;
    private PieceCollection pieceCollection;
    private static int turnCount;

    public PlayGame() {
        wPlayer = new WhitePlayer();
        bPlayer = new BlackPlayer();
        turnCount = 0;

        this.pieceCollection = new PieceCollection();
        this.pieceCollection.Initialize();
    }

    public void startGame() {

        this.move = new Movement(this.pieceCollection);
        this.pieceCollection.printbord();

        this.move.movementStart();
    }

    /*
     * This is a inner class specify all types movement that a piece can perform according to its movement rule
     */
    class Movement {

        boolean wFirst;
        boolean bFirst;
        int destX, destY;
        private PieceCollection pieceCollection;
        boolean kingCheck = false;

        public Movement(PieceCollection pc) {
            this.wFirst = true;
            this.bFirst = true;

            this.pieceCollection = pc;
        }

        public void movementStart() {
            try {

                System.out.println("\nwhite player start the game ->");
                // Always start white player
                whitePlayerMovement();
                // If total turncount is even then black player won the game. Otherwise white player
                if (turnCount % 2 == 0) {
                    System.out.println("\nBlack player won the game.");
                } else {
                    System.out.println("\nWhite player won the game.");
                }

                System.out.println("\nWhite pieces on the board:");
                this.pieceCollection.getTotalPieceInTheBoard(PieceCollection.wlist);

                System.out.println("\nTotal taken white pieces:");
                this.pieceCollection.getTotalTakenPiece(PieceCollection.wlist);

                System.out.println("\nBlack pieces on the board:");
                this.pieceCollection.getTotalPieceInTheBoard(PieceCollection.blist);

                System.out.println("\nTotal taken Black pieces:");
                this.pieceCollection.getTotalTakenPiece(PieceCollection.blist);

            } catch (Exception ex) {
                System.out.println("Error: " + ex);
            }
        }

        /*
         * Take input from console
         */
        private String inputFromConsole() {
            Scanner scanner = new Scanner(System.in);

            String data = scanner.nextLine();

            return data;
        }

        /*
         * This method initiate the movement of black player
         */
        private void blackPlayerMovement() {
            int x = 0;
            int y = 0;
            Random rand = new Random();
            // first time it takes data from console
            // Otherwise it randomly chose piece from arraylist
            if (this.wFirst) {
                System.out.print("Would you like to chose Powns or Knites.\nPress p for Powns and k for knights:");

                String data = this.inputFromConsole();


                if (data.equalsIgnoreCase("p")) {
                    System.out.println("Black player choosen Pown.");
                    x = 6;
                    y = 7;
                } else if (data.equalsIgnoreCase("k")) {
                    System.out.println("Black player choosen Knight.");
                    x = 7;
                    y = 1;
                } else {
                    System.out.println("Wrong input, please try again.");
                    this.blackPlayerMovement();
                }

                this.wFirst = false;
            } else {
                int bRand = rand.nextInt(16);

                ChessPiece tempPiece = PieceCollection.blist.get(bRand);

                if (tempPiece != null) {
                    x = tempPiece.getPositionX();
                    y = tempPiece.getPositionY();
                }
            }

            ChessPiece wPiece = this.pieceCollection.getChessPiece(x, y);
            // if king has checked
            if (kingCheck) {
                System.out.println("Black king has checked by White player.");
                // If there has not any way to escape
                if (!this.canMove(wPiece, "black")) {
                    System.out.println("Black king has checked by White player. No way to escape.\nGame Over!!");
                } else {
                    wPiece = this.pieceCollection.getChessPiece(destX, destY);
                    kingCheck = false;
                    
                    // If king has any escape posion then move here
                    if (this.canMove(wPiece, "black")) {
                        this.playPiece(wPiece, x, y);

                        turnCount++;

                        this.whitePlayerMovement();
                    }
                }
            } else if (this.canMove(wPiece, "black")) {
                this.playPiece(wPiece, x, y);

                turnCount++;

                this.whitePlayerMovement();
            } else {
                // If wrong piece chose then call this mehtod again
                this.blackPlayerMovement();
            }

        }

        private void whitePlayerMovement() {
            int x = 0;
            int y = 0;
            Random rand = new Random();

            if (this.bFirst) {
                System.out.print("\nWhite players turn.\nWould you like to chose Powns or Knites.\nPress p for Powns and k for knights:");

                String data = this.inputFromConsole();

                if (data.equalsIgnoreCase("p")) {
                    System.out.println("White player choosen Pown.");
                    x = 1;
                    y = 0;
                } else if (data.equalsIgnoreCase("k")) {
                    System.out.println("White player choosen Knight.");
                    x = 0;
                    y = 6;
                } else {
                    System.out.println("Wrong input, please try again.");
                    this.whitePlayerMovement();
                }

                this.bFirst = false;
            } else {
                int bRand = rand.nextInt(16);

                ChessPiece tempPiece = PieceCollection.wlist.get(bRand);

                if (tempPiece != null) {
                    x = tempPiece.getPositionX();
                    y = tempPiece.getPositionY();
                }

            }

            ChessPiece bPiece = this.pieceCollection.getChessPiece(x, y);

            if (kingCheck) {
                System.out.println("White king has checked by Black player.");
                if (!this.canMove(bPiece, "white")) {
                    System.out.println("White king has checked by Black player. No way to escape.\nGame Over!!");
                } else {
                    bPiece = this.pieceCollection.getChessPiece(destX, destY);
                    kingCheck = false;

                    if (this.canMove(bPiece, "white")) {
                        this.playPiece(bPiece, x, y);

                        turnCount++;

                        this.blackPlayerMovement();
                    }
                }

            } else if (this.canMove(bPiece, "white")) {
                this.playPiece(bPiece, x, y);

                turnCount++;

                this.blackPlayerMovement();
            } else {
                this.whitePlayerMovement();
            }

        }
        /*
         * This method change the coordinate postion of piece
         */

        private void playPiece(ChessPiece piece, int x, int y) {
            if (destX >= 0 && destX < 8 && destY >= 0 && destY < 8) {

                ChessPiece tempPiece = this.pieceCollection.getChessPiece(destX, destY);

                if (piece != null) {
                    // If new position is null then just update the position
                    if (tempPiece == null) {
                        piece.setPosition(destX, destY);
                        System.out.println(piece.getColor().toUpperCase() + " player has moves " + piece.getName() + " from [" + x + "," + y + "] to [" + destX + "," + destY + "]");

                        if(!kingCheck){
                            if (piece.getColor().equalsIgnoreCase("white")) {
                                System.out.println("\nNow Black players turn");
                            } else {
                                System.out.println("\nNow White players turn");
                            }
                        }
                    } // If position is not empty then make the piece taken marked and update the position
                    else {
                        if (!tempPiece.getColor().equalsIgnoreCase(piece.getColor())) {
                            if (!tempPiece.getName().equalsIgnoreCase("King")) {
                                tempPiece.setStatus(false);
                                piece.setPosition(destX, destY);

                                System.out.println(piece.getColor().toUpperCase() + " player has taken " + tempPiece.getName() + " of " + tempPiece.getColor().toUpperCase() + " player.");

                                if (piece.getColor().equalsIgnoreCase("white")) {
                                    System.out.println("\nNow Black players turn");
                                } else {
                                    System.out.println("\nNow White players turn");
                                }
                            } else {
                                this.kingCheck = true;
                            }
                        }
                    }
                }
            }
        }
        
        /*
         * This method return the available position of piece to where we can move
         * Every move based on the piece movement rule
         * Like pown can move only UP, LEFT and RIGHT from corrent position
         */
        private boolean canMove(ChessPiece piece, String color) {

            if (piece == null) {
                return false;
            }

            String name = piece.getName();
            int x = piece.getPositionX();
            int y = piece.getPositionY();
            ChessPiece tempPiece = null;
            destX = -1;
            destY = -1;

            if (name.equalsIgnoreCase("Powns")) {
                // White is at the below of the board so, it moves up that us y+1
                if (color.equalsIgnoreCase("white")) {
                    destX = x;
                    destY = y + 1;

                    if (destY < 8) {
                        tempPiece = this.pieceCollection.getChessPiece(destX, destY);

                        // If new position has no piece
                        if (tempPiece == null) {
                            return true;
                        } // Or new position has opposite party piece        
                        else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                            return true;
                        } // Or new position has dead piece
                        else if (!tempPiece.getStatus()) {
                            return true;
                        }
                    }
                } // Black is at upper position then it moves down that is y-1
                else {
                    destX = x;
                    destY = y - 1;

                    if (destY >= 0) {
                        tempPiece = this.pieceCollection.getChessPiece(destX, destY);

                        // If new position has no piece
                        if (tempPiece == null) {
                            return true;
                        } // Or new position has opposite party piece        
                        else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                            return true;
                        } // Or new position has dead piece
                        else if (!tempPiece.getStatus()) {
                            return true;
                        }
                    }
                }

                // goes right
                destX = x + 1;
                destY = y;

                if (destX < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(destX, destY);

                    if (tempPiece == null) {
                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        return true;
                    } else if (!tempPiece.getStatus()) {
                        return true;
                    }
                }

                // goes left
                destX = x - 1;
                destY = y;

                if (destX >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(destX, destY);

                    if (tempPiece == null) {
                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        return true;
                    } else if (!tempPiece.getStatus()) {
                        return true;
                    }
                }

            } else if (name.equalsIgnoreCase("Rooks")) {
                int i = x + 1;
                int j = y;
                // Move to right
                while (i < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i++;
                }

                i = x;
                j = y + 1;
                // Move to up
                while (j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    j++;
                }

                i = x - 1;
                j = y;
                // Move to left
                while (i >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i--;
                }

                i = x;
                j = y - 1;
                // Move to down
                while (j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    j--;
                }
            } else if (name.equalsIgnoreCase("Knights")) {
                int i = x + 2;
                int j = y + 1;

                if (i < 8 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x + 2;
                j = y - 1;

                if (i < 8 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x + 1;
                j = y + 2;

                if (i < 8 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 1;
                j = y + 2;

                if (i >= 0 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 2;
                j = y + 1;

                if (i >= 0 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 2;
                j = y - 1;

                if (i >= 0 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x + 1;
                j = y - 2;

                if (i < 8 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 1;
                j = y - 2;

                if (i >= 0 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }
            } else if (name.equalsIgnoreCase("Bishops")) {
                int i = x + 1;
                int j = y + 1;
                // Move to top right corner
                while (i < 8 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i++;
                    j++;
                }

                i = x + 1;
                j = y - 1;
                // Move to bottom right corner
                while (i < 8 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i++;
                    j--;
                }

                i = x - 1;
                j = y + 1;
                // Move to top left corner
                while (i >= 0 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i--;
                    j++;
                }

                i = x - 1;
                j = y - 1;
                // Move to bottom left corner
                while (i >= 0 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i--;
                    j--;
                }
            } else if (name.equalsIgnoreCase("King")) {
                int i = x;
                int j = y + 1;

                if (j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x;
                j = y - 1;

                if (j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x + 1;
                j = y;

                if (i < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 1;
                j = y;

                if (i >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x + 1;
                j = y + 1;

                if (i < 8 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x + 1;
                j = y - 1;

                if (i < 8 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 1;
                j = y + 1;

                if (i >= 0 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }

                i = x - 1;
                j = y - 1;

                if (i >= 0 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }
                }
            } else if (name.equalsIgnoreCase("Queen")) {
                int i = x + 1;
                int j = y;
                // Move to up
                while (i < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i++;
                }

                i = x;
                j = y + 1;
                // Move right
                while (j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    j++;
                }

                i = x - 1;
                j = y;
                // Move down
                while (i >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i--;
                }

                i = x;
                j = y - 1;
                // Move left
                while (j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    j--;
                }

                i = x + 1;
                j = y + 1;
                // Move to top right corner
                while (i < 8 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i++;
                    j++;
                }

                i = x + 1;
                j = y - 1;
                // Move to top left corner
                while (i < 8 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i++;
                    j--;
                }

                i = x - 1;
                j = y + 1;
                // Move to bottom left corner
                while (i >= 0 && j < 8) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i--;
                    j++;
                }

                i = x - 1;
                j = y - 1;
                // Move to bottom right corner
                while (i >= 0 && j >= 0) {
                    tempPiece = this.pieceCollection.getChessPiece(i, j);

                    if (tempPiece == null) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (tempPiece.getStatus() && !tempPiece.getColor().equalsIgnoreCase(color)) {
                        destX = i;
                        destY = j;

                        return true;
                    } else if (!tempPiece.getStatus()) {
                        destX = i;
                        destY = j;

                        return true;
                    }

                    i--;
                    j--;
                }

            }

            return false;
        }
    }
}
