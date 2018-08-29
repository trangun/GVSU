package Project2.threeD;

/**********************************************************************
 *3D Connect Four Game class that creates the logic for a connect four
 *that has depth. The player is 0 and the opponent is 1. If the player
 *or opponent gets four in a row, they win.
 *@author Michelle Vu and Trang Nguyen
 *@version 2/27/18
 **********************************************************************/
public class ConnectFourGame3D {

    /** Three dimensional array that creates the board.*/
    private int board[][][];

    /** Variable that holds the player, either 0 or 1 */
    private int player;

    /** Variable that holds the USER */
    private static final int USER = 0;

    /** Variable that holds the COMPUTER */
    private static final int COMPUTER = 1;

    /** Variable that holds the size of the board */
    private static final int SIZE =7;

    /** Variable that holds the depth */
    private int depth;

    /******************************************************************
     * Constructor that sets the size of the board by SIZE
     *****************************************************************/
    public ConnectFourGame3D(){
        board = new int[SIZE][SIZE][SIZE];
    }

    /******************************************************************
     * Gets the three dimensional array for the board, int terms of
     * [row][col][depth]
     * @return board the board that holds the 3 dimensional array
     *****************************************************************/
    public int[][][] getBoard() {
        return board;
    }

    /******************************************************************
     * Sets the three dimensional array for the board, with int terms
     * of [row][col][depth]
     * @param board int sets the this.board to the board in the
     *              parameter
     *****************************************************************/
    public void setBoard(int[][][] board) {
        this.board = board;
    }

    /******************************************************************
     * Gets the player that is currently playing; either 0 or 1
     * @return player Int that holds either User or Computer
     *****************************************************************/
    public int getPlayer() {
        return player;
    }

    /******************************************************************
     * Sets the player in the parameter to the variable player
     * @param player int that is either User or Computer
     *****************************************************************/
    public void setPlayer(int player) {
        this.player = player;
    }

    /******************************************************************
     * Gets the depth for the array
     * @return depth int of how deep the connect four is
     *****************************************************************/
    public int getDepth() {
        return depth;
    }

    /******************************************************************
     * Sets the depth in the parameter to the variable depth
     * @param depth int of how deep the connect four is
     *****************************************************************/
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /******************************************************************
     * Switches the player from User to Computer or Vice versa
     * @return player int either 0 (user) or 1 (Computer)
     *****************************************************************/
    public int switchPlayer () {
        player = (player + COMPUTER) % 2;
        return player;
    }

    /******************************************************************
     * Sets the piece into the parameter row and column and puts the
     * board at that depth to the player.
     *
     * @param col int that chooses which column the peg should go in
     * @param row int that chooses which row the peg should go in
     * @return row int of the row that chooses which row the peg should
     * go in
     * @return -1 if there are no columns or rows that you can set
     * pieces in or the depth is greater than 7
     *****************************************************************/
    public int setPiece(int col,int row) {
        if(depth<7) {
            if(board[row][col][depth]==-1) {
                board[row][col][depth] = player;
                return row;
            }
        }
        return -1;
    }

    /******************************************************************
     * Resets the board array to -1 and sets the player to the USER
     *****************************************************************/
    public void reset() {
        for(int r = 0; r<SIZE; r++) {
            for( int c = 0;c<SIZE;c++){
                for(int d = 0; d<SIZE;d++) {
                    board[r][c][d] =-1;
                    setPlayer(USER);
                }
            }
        }
    }

    /******************************************************************
     * Checks if the player is a winner horizontally, vertically, and
     * diagonally. Also takes in consideration the depth.
     *
     * @return true boolean if they are a winner
     * @return false boolean if they are not a winner
     *****************************************************************/
    public boolean isWinner() {
        //check horizontal win on one plane
        for(int r = 0; r<SIZE; r++) {
            for( int c = 0;c<SIZE-3;c++){
                for(int d = 1; d<SIZE;d++) {
                    if(board[r][c][d]==player&&
                            board[r][c+1][d]==player&&
                            board[r][c+2][d]==player&&
                            board[r][c+3][d]==player)
                        return true;
                }
            }
        }

        // check vertical win on one plane
        for(int r = 0; r<SIZE-3; r++) {
            for( int c = 0;c<SIZE;c++){
                for(int d = 1; d<SIZE;d++) {
                    if(board[r][c][d]==player&&
                            board[r+1][c][d]==player&&
                            board[r+2][c][d]==player&&
                            board[r+3][c][d]==player)
                        return true;
                }
            }
        }

        //check diagonal up win on one plane
        for(int r = 3; r<SIZE; r++) {
            for( int c = 0;c<SIZE-3;c++){
                for(int d = 1; d<SIZE;d++) {
                    if(board[r][c][d]==player&&
                            board[r-1][c+1][d]==player&&
                            board[r-2][c+2][d]==player&&
                            board[r-3][c+3][d]==player)
                        return true;
                }
            }
        }

        //check diagonal down win on one plane
        for(int r = 3; r<SIZE; r++) {
            for( int c = 3;c<SIZE;c++){
                for(int d = 1; d<SIZE;d++) {
                    if(board[r][c][d]==player&&
                            board[r-1][c-1][d]==player&&
                            board[r-2][c-2][d]==player&&
                            board[r-3][c-3][d]==player)
                        return true;
                }
            }
        }

        //check horizontal win left to right with depth
        for(int r = 0; r<SIZE; r++) {
            for( int c = 0;c<SIZE-3;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d]==player&&
                            board[r][c+1][d+1]==player&&
                            board[r][c+2][d+2]==player&&
                            board[r][c+3][d+3]==player)
                        return true;
                }
            }
        }

        //check horizontal win right to left with depth
        for(int r = 0; r<SIZE; r++) {
            for( int c = 0;c<SIZE-3;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d+3]==player&&
                            board[r][c+1][d+2]==player&&
                            board[r][c+2][d+1]==player&&
                            board[r][c+3][d]==player)
                        return true;
                }
            }
        }

        //check vertical win north to south with depth
        for(int r = 0; r<SIZE-3; r++) {
            for( int c = 0;c<SIZE;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d]==player&&
                            board[r+1][c][d+1]==player&&
                            board[r+2][c][d+2]==player&&
                            board[r+3][c][d+3]==player)
                        return true;
                }
            }
        }

        //check vertical win south to north with depth
        for(int r = 0; r<SIZE-3; r++) {
            for( int c = 0;c<SIZE;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d+3]==player&&
                            board[r+1][c][d+2]==player&&
                            board[r+2][c][d+1]==player&&
                            board[r+3][c][d]==player)
                        return true;
                }
            }
        }

        //check diagonal up win left to right with depth
        for(int r = 3; r<SIZE; r++) {
            for( int c = 0;c<SIZE-3;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d]==player&&
                            board[r-1][c+1][d+1]==player&&
                            board[r-2][c+2][d+2]==player&&
                            board[r-3][c+3][d+3]==player)
                        return true;
                }
            }
        }

        //check diagonal up win right to left with depth
        for(int r = 3; r<SIZE; r++) {
            for( int c = 0;c<SIZE-3;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d+3]==player&&
                            board[r-1][c+1][d+2]==player&&
                            board[r-2][c+2][d+1]==player&&
                            board[r-3][c+3][d]==player)
                        return true;
                }
            }
        }

        //check diagonal down win right to left with depth
        for(int r = 3; r<SIZE; r++) {
            for( int c = 3;c<SIZE;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d]==player&&
                            board[r-1][c-1][d+1]==player&&
                            board[r-2][c-2][d+2]==player&&
                            board[r-3][c-3][d+3]==player)
                        return true;
                }
            }
        }

        //check diagonal down win left to right with depth
        for(int r = 3; r<SIZE; r++) {
            for( int c = 3;c<SIZE;c++){
                for(int d = 1; d<SIZE-3;d++) {
                    if(board[r][c][d+3]==player&&
                            board[r-1][c-1][d+2]==player&&
                            board[r-2][c-2][d+1]==player&&
                            board[r-3][c-3][d]==player)
                        return true;
                }
            }
        }

        //checks a win with only depth
        for(int r=0; r<SIZE;r++) {
            for(int c = 0; c<SIZE;c++) {
                for (int d=1;d<SIZE;d++){
                    if(board[r][c][d]==player&&
                            board[r][c][d+1]==player&&
                            board[r][c][d+2]==player&&
                            board[r][c][d+3]==player)
                        return true;
                }
            }
        }
        return false;
    }

}
