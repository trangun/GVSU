package chess;

import java.util.Random;

import javax.swing.JOptionPane;

import static chess.Player.BLACK;
import static chess.Player.WHITE;

/**********************************************************************
 * This class implements the IChessModel Interface to creates the 
 * ChessModel class which is the logic for the chess game. 
 *
 * @author Michelle Vu
 * @author Trang Nguyen
 * @author Habiba Salem
 *
 * @version 03/30/2018
 *********************************************************************/
public class ChessModel implements IChessModel{
    /** Instance Variable that creates a board of IChessPiece*/
    private IChessPiece[][] board;

    /**Instance Variable that holds the player */
    private Player player;

    /**Instance Variable that says if AI mode is on */
    private boolean aiMode;

    /**Instance Variable that says if the AI can promote randomly */
    private boolean aiPromote;

    /**Instance Variable that determines if it is castling */
    private boolean castling;

    /**Checks if move won't get you in check*/
    boolean vMove;

    /**Instance Variable that checks if left black rook has moved */
    private boolean castlingRBL;

    /**Instance Variable that checks if right black rook has moved */
    private boolean castlingRBR;

    /**Instance Variable that checks if left white rook has moved */
    private boolean castlingRWL;

    /**Instance Variable that checks if right white rook has moved */
    private boolean castlingRWR;

    /**Instance Variable that checks if black king has moved*/
    private int castlingKB;

    /**Instance Variable that checks if black king has moved*/
    private int castlingKW;

    /******************************************************************
     * Gets the boolean for if castling is possible
     *
     * @return boolean castling true if its castling otherwise false
     *****************************************************************/

    boolean isCastling() {
        return castling;
    }

    /******************************************************************
     * Sets the boolean for if castling is possible
     *
     * @param castling true if its castling otherwise false
     *****************************************************************/
    public void setCastling(boolean castling) {
        this.castling = castling;
    }

    /******************************************************************
     * Castling that will check if you can move the king and the rook
     * to castle them.
     *
     * @param move to find out if you are moving the king and if it is
     *             a valid castle move
     *****************************************************************/
    void castling(Move move) {
        castling = true;
        if(!inCheck(currentPlayer()))
            if (currentPlayer() == BLACK) {
                if (board[move.fromRow][move.fromColumn] != null)
                    if (board[move.fromRow][move.fromColumn].type().
                            equals("King"))
                        //checks if the king moved prior
                        if (move.fromRow == 7 && move.fromColumn == 4)
                            if (castlingKB == 0)
                                if (move.toColumn == 2 && board[7][0].type().
                                        equals("Rook")) {
                                    if (castlingRBL)
                                        //checks if you can move over one
                                        if (board[7][1] == null &&
                                                board[7][2] == null &&
                                                board[7][3] == null) {
                                            Move m9 = new Move(7,
                                                    4, 7, 3);
                                            if (isValidMove(m9)) {
                                                board[7][3] = board[7][4];
                                                board[7][4] = null;
                                                if (!inCheck(BLACK)) {
                                                    //checks if you can
                                                    // move over
                                                    // one more time
                                                    Move m10 = new Move(7,
                                                            3, 7, 2);
                                                    if (isValidMove(m10)) {
                                                        board[7][2] = board[7][3];
                                                        board[7][3] = null;
                                                        if (!inCheck(BLACK)) {
                                                            board[7][3] = board[7][0];
                                                            board[7][0] = null;
                                                            board[7][4] = null;
                                                            setPlayer(
                                                                    currentPlayer()
                                                                            .next());
                                                            castling = true;
                                                            return;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    //checks other side for the black pieces for castle
                                } else if (move.toColumn == 6 && board[7][7].type()
                                        .equals("Rook"))
                                    if (castlingRBR)
                                        if (board[7][5] == null &&
                                                board[7][6] == null) {
                                            Move m11=new Move(7,
                                                    4,7,5);
                                            if (isValidMove(m11)) {
                                                board[7][5] =
                                                        board[7][4];
                                                board[7][4] = null;
                                                if (!inCheck(BLACK)) {
                                                    Move m12=new
                                                            Move(7,
                                                            5,7,6);
                                                    if (isValidMove(m12)) {
                                                        board[7][6] =
                                                                board[7][5];
                                                        board[7][5] =
                                                                null;
                                                        board[7][4] =
                                                                null;
                                                        if (!inCheck(BLACK)) {
                                                            board[7][5] =
                                                                    board[7][7];
                                                            board[7][7] = null;
                                                            board[7][4] = null;
                                                            setPlayer(currentPlayer()
                                                                    .next());
                                                            castling = true;
                                                            return;
                                                        }
                                                    }
                                                }

                                            }
                                        }
            } else
                //checks white side
                if (currentPlayer() == WHITE)
                    if (board[move.fromRow][move.fromColumn] != null)
                        if (board[move.fromRow][move.fromColumn]
                                .type().equals("King"))
                            if (move.fromRow == 0 && move.fromColumn == 4)
                                if (castlingKW == 0)
                                    if (move.toColumn == 2
                                            && board[0][0]
                                            .type().equals("Rook")) {
                                        if (castlingRWL)
                                            if (board[0][1] == null &&
                                                    board[0][2] == null
                                                    && board[0][3] == null) {
                                                Move m9 = new Move(0,
                                                        4, 0, 3);
                                                if (isValidMove(m9)) {
                                                    board[0][3] = board[0][4];
                                                    board[0][4] = null;
                                                    if (!inCheck(WHITE)) {
                                                        Move m10 = new
                                                                Move(0,
                                                                3,0,2);
                                                        if (isValidMove(m10)) {
                                                            board[0][2] = board[0][3];
                                                            board[0][4] = null;
                                                            if (!inCheck(WHITE)) {
                                                                board[0][3] =
                                                                        board[0][0];
                                                                board[0][0] = null;
                                                                board[0][4] = null;
                                                                setPlayer(currentPlayer()
                                                                        .next());
                                                                castling = true;
                                                                return;
                                                            }
                                                        }
                                                    }

                                                }
                                            }
                                    } else if (move.toColumn == 6 &&
                                            board[0][7].
                                                    type()
                                                    .equals("Rook"))
                                        if (castlingRWR)
                                            if (board[0][5] == null &&
                                                    board[0][6] == null) {
                                                Move m11=new Move(0,4,
                                                        0,5);
                                                if (isValidMove(m11)) {
                                                    board[0][5] = board[0][4];
                                                    board[0][4] = null;
                                                    if (!inCheck(WHITE)) {
                                                        Move m12 = new Move(0,
                                                                5,0,6);
                                                        if (isValidMove(m12)) {
                                                            board[0][6] =
                                                                    board[0][5];
                                                            board[0][5] =
                                                                    null;
                                                            if (!inCheck(WHITE)) {
                                                                board[0][5] =
                                                                        board[0][7];
                                                                board[0][7] = null;
                                                                board[0][4] = null;
                                                                setPlayer(currentPlayer()
                                                                        .next());
                                                                castling = true;
                                                                return;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
        castling = false;

    }

    /******************************************************************
     * A method that checks to see if the AI can promote their pawn.
     *
     * @return aiPromotes, if the AI can promote it's pawn.
     *****************************************************************/
    public boolean isAiPromote() {
        return aiPromote;
    }

    /******************************************************************
     * A method that sets the AI promotion of the pawn and sets it equal
     * to the boolean in aiPromote.
     *
     * @param aiPromote, if the AI can promote or not.
     *****************************************************************/
    void setAiPromote(boolean aiPromote) {
        this.aiPromote = aiPromote;
    }

    /******************************************************************
     * A method that checks to see if the current game is in the AI
     * mode.
     *
     * @return aiMode, if the game mode is in AI.
     *****************************************************************/
    public boolean isAiMode() {
        return aiMode;
    }

    /******************************************************************
     * A method that sets the game in AI mode which then is set to a
     * local variable.
     *
     * @param aiMode, if the game mode is AI.
     *****************************************************************/
    void setAiMode(boolean aiMode) {
        this.aiMode = aiMode;
    }

    /******************************************************************
     * A method of that sets the player of type Player to a local
     * variable.
     *
     * @param player, assigned to the local variable.
     *****************************************************************/
    public void setPlayer(Player player) {
        this.player = player;
    }

    /******************************************************************
     * A constructor that resets the game.
     *****************************************************************/
    public ChessModel() {
        reset();
    }

    /******************************************************************
     * A method that checks if the game is over.
     *
     * @return false if the game is not over, true otherwise.
     *****************************************************************/
    private boolean gameOver() {
        for(int row = 0;row <=7;row++) {
            for(int col = 0;col<=7;col++) {
                if(player == BLACK) {
                    if(board[row][col]!=null&&board[row][col]
                            .type().equals("King")&&
                            board[row][col].player()==BLACK) {
                        return false;
                    }
                }
                if(player == WHITE) {
                    if(board[row][col]!=null&&board[row][col]
                            .type().equals("King")&&
                            board[row][col].player()==WHITE) {

                        return false;
                    }
                }
            }
        }
        return true;
    }

    /******************************************************************
     * Checks if the game is complete. It is complete if it is a game
     * over or there are no ways for one player to win.
     *
     * @return boolean true if it is complete.
     *****************************************************************/
    @Override
    public boolean isComplete() {
        if (gameOver()) {
            return true;
        }
        if (inCheck(currentPlayer())) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (board[row][col] != null) {
                        if (board[row][col].player() == currentPlayer()) {
                            for (int r = 0; r < 8; r++) {
                                for (int c = 0; c < 8; c++) {
                                    Move m2 = new Move(row, col, r, c);
                                    IChessPiece piece;
                                    IChessPiece tPiece;
                                    tPiece = board[r][c];
                                    piece = board[row][col];
                                    if (board[row][col].
                                            isValidMove(m2, board)) {
                                        board[row][col] = null;
                                        board[r][c] = piece;
                                        if (!inCheck(currentPlayer())) {
                                            board[row][col] = piece;
                                            board[r][c] = tPiece;
                                            return false;
                                        } else {
                                            board[row][col] = piece;
                                            board[r][c] = tPiece;
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }
            return true;
        }

        //checks if it is only the kings left, if so it is complete
        boolean moreThanKingW = false;
        boolean moreThanKingB = false;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    if (board[row][col].player() == WHITE) {
                        if (!board[row][col].type().equals("King"))
                            moreThanKingW = true;
                    }
                    if (board[row][col].player() == BLACK) {
                        if (!board[row][col].type().equals("King"))
                            moreThanKingB = true;
                    }
                }
            }
        }
        return !moreThanKingW || !moreThanKingB;
    }

    /******************************************************************
     * A method that sets the check that finds if the move won't get
     * you in check and setting it to a local variable.
     *
     * @param b, whether its dangerous to move or not.
     *****************************************************************/
    void setvMove(boolean b) {
        vMove = b;
    }

    /******************************************************************
     * A method checks to see if the move is valid for any piece,
     * making sure that a piece can't be put on itself, can't go over
     *  the board size, jump over another piece.
     *
     * @param move, an object describing the move to be made.
     * @return true if the proposed move is valid, otherwise, return
     * false.
     *****************************************************************/
    public boolean isValidMove(Move move) {
        vMove = true;
        IChessPiece P1,P2;
        if (pieceAt(move.fromRow, move.fromColumn) != null) {
            if((pieceAt(move.fromRow, move.fromColumn)
                    .isValidMove(move, board))) {
                P1 = board[move.fromRow][move.fromColumn];
                P2 = board[move.toRow][move.toColumn];
                board[move.fromRow][move.fromColumn] = null;
                board[move.toRow][move.toColumn] = P1;

                if(inCheck(currentPlayer())) {
                    vMove = false;
                    board[move.fromRow][move.fromColumn] = P1;
                    board[move.toRow][move.toColumn] = P2;
                    return false;
                } else {
                    board[move.fromRow][move.fromColumn] = P1;
                    board[move.toRow][move.toColumn] = P2;

                    if(player == WHITE) {
                        if(board[move.fromRow][move.fromColumn]
                                .player() == WHITE) {
                            return true;
                        }
                    }

                    if(player == BLACK) {
                        return board[move.fromRow][move.fromColumn]
                                .player() == BLACK;
                    }
                }
            }
        }
        return false;
    }

    /******************************************************************
     * A method checks to see if the King or the Rook has moved.
     * Then will move the parameter move if it is a valid move.
     *
     * @param move, an object describing the move to be made.
     *****************************************************************/
    public void move(Move move) {

        if(board[move.fromRow][move.fromColumn]!=null) {
            if(board[move.fromRow][move.fromColumn]
                    .type().equals("King")){
                if(board[move.fromRow][move.fromColumn]
                        .player() ==WHITE)
                    //adds so you can't castle after you move the king
                    castlingKW++;
                else
                    castlingKB++;
            }
        }

        if(board[move.fromRow][move.fromColumn]!=null) {
            if(board[move.fromRow][move.fromColumn]
                    .type().equals("Rook")){
                if(board[move.fromRow][move.fromColumn]
                        .player() ==WHITE) {
                    if(move.fromRow == 0 &&move.fromColumn == 0) {
                        //makes it false if you move the rook so you can't
                        //castle it when it previously moved
                        castlingRWL = false;
                    }
                    if(move.fromRow==0 && move.fromColumn ==7)
                        castlingRWR = false;
                }else {
                    if(move.fromRow==7&&move.fromColumn ==0) {
                        castlingRBL = false;
                    }
                    if(move.fromRow==7&&move.fromColumn==7)
                        castlingRBR = false;
                }
            }
        }

        if (isValidMove(move)) {
            board[move.toRow][move.toColumn] =
                    board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;

        } else {
            board[move.toRow][move.toColumn]
                    = board[move.toRow][move.toColumn];
            board[move.fromRow][move.fromColumn]
                    = board[move.fromRow][move.fromColumn];

        }
    }

    /******************************************************************
     * Goes through every piece to see if they are able to checkmate
     * the king.
     *
     * @param p player who is the current player we are checking for
     *          checks.
     * @return boolean true if is possible to checkmate the king.
     *****************************************************************/
    public boolean inCheck(Player p) {
        for(int row = 0; row< 8; row++)
            for(int col = 0; col < 8;col++)
                if(board[row][col]!=null)
                    if(board[row][col].player()!= p)
                        for(int r = 0; r < 8;r++)
                            for(int c = 0; c < 8;c++) {
                                Move m1 = new Move(row, col, r, c);
                                if(board[row][col].isValidMove(m1,board)
                                        && board[r][c]!=null)
                                    if(board[r][c].player()==p)
                                        if(board[r][c].type()
                                                .equals("King")) {
                                            return true;
                                        }

                            }
        return false;
    }

    /******************************************************************
     * Promotes the pawn to either a rook, queen, bishop or a knight
     * depending on the number you input. If it is AI, it will randomly
     * choose.
     *
     * @param move that finds the pawn that is moving to the end of the
     *             board to promote it
     *****************************************************************/
    void promotion(Move move) {
        String type;
        int choice = 0;
        if(!aiPromote)
            if (board[move.fromRow][move.fromColumn] == null)
                if (board[move.toRow][move.toColumn]
                        .type().equals("Pawn")) {
                    if (move.toRow == 0 && currentPlayer() == WHITE) {
                        while (choice == 0) {
                            try {
                                type = JOptionPane.showInputDialog
                                        (null, "Pawn Promotion: Type "
                                                + "1 for Queen,"
                                                + " type 2 for Rook, type 3 "
                                                + "for Bishop, type 4 for"
                                                + " knight");
                                if (type != null) {
                                    choice = Integer.parseInt(type);
                                }
                                if (choice > 0 && choice < 5)
                                    board[move.toRow][move.toColumn] = null;
                                else {
                                    choice = 0;
                                    throw new IllegalArgumentException();
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(
                                        null, "Invalid number");
                            }
                            if (choice == 1)
                                board[move.toRow][move.toColumn] =
                                        new Queen(Player.BLACK);
                            else if (choice == 2)
                                board[move.toRow][move.toColumn] =
                                        new Rook(Player.BLACK);
                            else if (choice == 3)
                                board[move.toRow][move.toColumn] =
                                        new Bishop(Player.BLACK);
                            else if (choice == 4)
                                board[move.toRow][move.toColumn] =
                                        new Knight(Player.BLACK);
                        }
                    }
                    if (move.toRow == 7 && currentPlayer() == BLACK) {
                        while (choice == 0) {
                            try {
                                type = JOptionPane.showInputDialog(
                                        null, "Pawn Promotion: Type "
                                                + "1 for Queen,"
                                                + " type 2 for Rook, type 3"
                                                + " for Bishop, type 4 "
                                                + "for knight");
                                if (type != null)
                                    choice = Integer.parseInt(type);
                                if (choice > 0 && choice < 5)
                                    board[move.toRow][move.toColumn] = null;
                                else {
                                    choice = 0;
                                    throw new IllegalArgumentException();
                                }
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog
                                        (null, "Invalid number");
                            }
                            if (choice == 1)
                                board[move.toRow][move.toColumn] =
                                        new Queen(Player.WHITE);
                            else if (choice == 2)
                                board[move.toRow][move.toColumn] =
                                        new Rook(Player.WHITE);
                            else if (choice == 3)
                                board[move.toRow][move.toColumn] =
                                        new Bishop(Player.WHITE);
                            else if (choice == 4)
                                board[move.toRow][move.toColumn] =
                                        new Knight(Player.WHITE);
                        }
                    }
                }

        if(aiPromote)
            if (board[move.toRow][move.toColumn] != null)
                if (move.toRow == 7 && currentPlayer() == WHITE)
                    if (board[move.toRow][move.toColumn]
                            .type().equals("Pawn")) {
                        Random rand = new Random();
                        int AIChoice = rand.nextInt(4) + 1;
                        board[move.toRow][move.toColumn] = null;
                        if (AIChoice == 1)
                            board[move.toRow][move.toColumn] =
                                    new Queen(Player.WHITE);
                        else if (AIChoice == 2)
                            board[move.toRow][move.toColumn] =
                                    new Rook(Player.WHITE);
                        else if (AIChoice == 3)
                            board[move.toRow][move.toColumn] =
                                    new Bishop(Player.WHITE);
                        else if (AIChoice == 4)
                            board[move.toRow][move.toColumn] =
                                    new Knight(Player.WHITE);
                    }
    }

    /******************************************************************
     * A method of that checks who the current player is.
     *
     * @return player, who the current player is.
     *****************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /******************************************************************
     * A method that returns the number of rows on the game board.
     *
     * @return board.length, the size of the board.
     *****************************************************************/
    int numRows() {
        return board.length;
    }

    /******************************************************************
     * A method that returns the number of columns on the game board.
     *
     * @return board.length, the size of the board.
     *****************************************************************/
    int numColumns() {
        return board.length;
    }

    /******************************************************************
     * A method that returns the piece at a specified spot on the game
     * board.
     *
     * @return board[row][col], the row and col of the spot on the board.
     *****************************************************************/
    IChessPiece pieceAt(int row, int col) {
        return board[row][col];
    }

    /******************************************************************
     * A method that resets the board.
     *****************************************************************/
    void reset() {
        board = new IChessPiece[8][8];
        player = BLACK;
        vMove = true;
        aiPromote = false;
        castlingKB = 0;
        castlingKW = 0;
        castlingRBL = true;
        castlingRBR = true;
        castlingRWL = true;
        castlingRWR = true;

        board[0][0] = new Rook(WHITE);
        board[0][1] = new Knight(WHITE);
        board[0][2] = new Bishop(WHITE);
        board[0][3] = new Queen(WHITE);
        board[0][4] = new King(WHITE);
        board[0][5] = new Bishop(WHITE);
        board[0][6] = new Knight(WHITE);
        board[0][7] = new Rook(WHITE);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(WHITE);
        }

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(BLACK);
        }
        board[7][0] = new Rook(BLACK);
        board[7][1] = new Knight(BLACK);
        board[7][2] = new Bishop(BLACK);
        board[7][3] = new Queen(BLACK);
        board[7][4] = new King(BLACK);
        board[7][5] = new Bishop(BLACK);
        board[7][6] = new Knight(BLACK);
        board[7][7] = new Rook(BLACK);
    }

    /******************************************************************
     * Method for AI that attempts to put opponent into check
     * (or check mate).
     *
     * @return Move that attempts to put opponent into check.
     *****************************************************************/
    private Move tryToWin () {
        IChessPiece t1;
        IChessPiece t2;

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8;col++) {
                if(board[row][col] != null) {
                    if(board[row][col].player() == WHITE) {
                        for(int r = 0; r < 8;r++) {
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if (board[row][col].
                                        isValidMove(move, board)) {
                                    t1 = board[row][col];
                                    board[row][col] = null;
                                    t2 = board[r][c];
                                    board[r][c] = t1;
                                    if (inCheck(BLACK)) {
                                        return move;
                                    } else {
                                        board[row][col] = t1;
                                        board[r][c] = t2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /******************************************************************
     * Checks each spot in the board to see if the king is in check,
     * if so, it will move anything to get out of check
     *
     * @return Move that will get out of check
     *****************************************************************/
    private Move getOutOfCheck() {
        IChessPiece t1;
        IChessPiece t2;
        for(int row = 0; row < 8;row++) {
            for(int col = 0; col < 8;col++) {
                if(board[row][col]!=null) {
                    if(board[row][col].player() == WHITE) {
                        for(int r = 0; r < 8; r++)
                            for(int c = 0; c < 8;c++) {
                                Move m2 = new Move(row, col, r, c);
                                if(board[row][col].
                                        isValidMove(m2, board)) {
                                    t1 = board[row][col];
                                    board[row][col] = null;
                                    t2 = board[r][c];
                                    board[r][c] = t1;
                                    if(!inCheck(WHITE)) {
                                        return m2;
                                    } else {
                                        board[row][col] = t1;
                                        board[r][c] = t2;
                                    }
                                }
                            }
                    }
                }
            }
        }
        return null;
    }

    /******************************************************************
     * Takes the pieces if AI piece is in danger.
     *
     * @return Move that takes the pieces if our piece is in danger.
     *****************************************************************/
    private Move tryToEat () {
        IChessPiece t1;

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if(board[row][col] != null) {
                    if(board[row][col].player() == WHITE) {
                        for(int r = 0; r < 8; r++) {
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if(board[row][col]
                                        .isValidMove(move, board)
                                        && board[r][c] != null) {
                                    t1 = board[row][col];
                                    board[row][col] = null;
                                    board[r][c] = t1;
                                    if(inDanger(WHITE)) {
                                        return move;
                                    } else {
                                        move = getAValidMove(t1, board,
                                                row, col);
                                        return move;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /******************************************************************
     * Determines if any of our pieces are in danger.
     *
     * @param p - Player that is in danger.
     * @return boolean if player is in danger.
     *****************************************************************/
    private boolean inDanger (Player p) {
        for(int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] != null) {
                    if (board[row][col].player() != p) {
                        for (int r = 0; r < 8; r++) {
                            for (int c = 0; c < 8; c++) {
                                Move move = new Move(row, col, r, c);
                                if(board[row][col].isValidMove(move, board)
                                        && board[r][c] != null){
                                    if(board[r][c].player() == p) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /******************************************************************
     * Represents AI move.
     *
     * @return Move that AI is going to make.
     *****************************************************************/
    Move aiMove() {
        if (player == WHITE) {
            if (inCheck(WHITE)) {
                return getOutOfCheck();
            }
            else if (!inDanger(BLACK)
                    && !inDanger(WHITE)
                    && tryToWin() != null){
                return tryToWin();

            }
            else if (inDanger(BLACK)) {
                return tryToEat();
            }
            else {
                for (int fromRow = 0; fromRow < 8; fromRow++) {
                    for (int fromCol = 0; fromCol < 8; fromCol++) {
                        IChessPiece chessPiece = board[fromRow][fromCol];
                        if (chessPiece != null
                                && chessPiece.player() == WHITE) {
                            Move move = getAValidMove(chessPiece,
                                    board, fromRow, fromCol);
                            if (move != null) {
                                return move;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /******************************************************************
     * Random moves for AI when it cannot take the opponent piece or
     * attempt to put opponent into check.
     *
     * @param chessPiece - to check valid move for the pieces
     * @param board - board of the chess game
     * @param fromRow - AI initial row
     * @param fromCol - AI initial column
     * @return Move - random moves that AI is going to make
     *****************************************************************/
    private Move getAValidMove(IChessPiece chessPiece,
                               IChessPiece[][] board,
                               int fromRow, int fromCol) {
        Move move;
        Random rand = new Random();
        int randomNumber = rand.nextInt(8);

        switch (chessPiece.type()) {
            case "Pawn":
                if (fromRow < 7) {
                    if (fromRow == 1
                            && board[fromRow + 1][fromCol] == null) {
                        int iniPawnCase = rand.nextInt(2) + 1;
                        switch (iniPawnCase) {
                            case 1:
                                move = new Move(fromRow, fromCol,
                                        fromRow + 2, fromCol);
                                if (chessPiece.isValidMove(move, board)){
                                    return move;
                                }
                                break;
                            case 2:
                                move = new Move(fromRow, fromCol,
                                        fromRow + 1, fromCol);
                                if (chessPiece.isValidMove(move, board)){
                                    return move;
                                }
                                break;
                        }
                    } else {
                        move = new Move(fromRow, fromCol,
                                fromRow + 1, fromCol);
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;
                    }
                }

            case "Knight":
                int knightCase = rand.nextInt(4) + 1;
                switch (knightCase) {
                    case 1 :
                        move = new Move(fromRow, fromCol,
                                fromRow + 2, fromCol + 1);
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;

                    case 2:
                        move = new Move(fromRow, fromCol,
                                fromRow + 1, fromCol + 2);
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;

                    case 3:
                        move = new Move(fromRow, fromCol,
                                fromRow - 1, fromCol - 2);
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;

                    case 4:
                        move = new Move(fromRow, fromCol,
                                fromRow - 2, fromCol - 1);
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;
                }

            case "Bishop":
                move = new Move(fromRow, fromCol,
                        Math.abs(fromRow + randomNumber),
                        Math.abs(fromCol + randomNumber));
                if (chessPiece.isValidMove(move, board)) {
                    return move;
                }
                break;

            case "Rook":
                int rookCase = rand.nextInt(2) + 1;
                switch (rookCase) {
                    case 1:
                        move = new Move(fromRow, fromCol, fromRow,
                                Math.abs(fromCol + randomNumber));
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;

                    case 2:
                        move = new Move(fromRow, fromCol,
                                Math.abs(fromRow + randomNumber),
                                fromCol);
                        if (chessPiece.isValidMove(move, board)) {
                            return move;
                        }
                        break;
                }
        }
        return null;
    }
}

