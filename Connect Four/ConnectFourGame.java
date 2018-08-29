package Project2;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**********************************************************************
 * Create a 2D Connect Four Game with player and computer option.
 * @author Trang Nguyen & Michelle Vu
 * @version February 2018 (Winter 2018)
 *********************************************************************/
public class ConnectFourGame {

    /** Final value for winning score */
    private static final int WINNING_SCORE = 100000;

    /** Final value for losing score*/
    private static final int LOSING_SCORE = -WINNING_SCORE;

    /** Board of the game */
    private int[][] board;

    /** Size of the board */
    private int size;

    /** Player of the game to be numbered as 0 or 1 */
    private int player;

    /** Value that represents the player USER */
    public static final int USER = 0;

    /** Value that represents the player COMPUTER */
    public static final int COMPUTER = 1;

    /** Map to save the conditions from zobrist method */
    private Map<Long, Integer> boardMap = new HashMap<>();

    /** To save the conditions of the board */
    private long[][][] zobrist;

    /******************************************************************
     * Returns board of the game.
     * @return int [][] board of the game
     *****************************************************************/
    public int[][] getBoard() {
        return board;
    }

    /******************************************************************
     * Sets board the game with size passed as parameter.
     * @param size of the board
     *****************************************************************/
    public void setBoard(int size) {
        board = new int[size][size];
    }

    /******************************************************************
     * Returns size of the board.
     * @return int size of the board
     *****************************************************************/
    public int getSize() {
        return size;
    }

    /******************************************************************
     * Sets the size of the board.
     * @param size of the board
     *****************************************************************/
    public void setSize(int size) {
        this.size = size;
    }

    /******************************************************************
     * Returns the current player.
     * @return int the current player
     *****************************************************************/
    public int getPlayer() {
        return player;
    }

    /******************************************************************
     * Sets the current player.
     * @param player of the game
     *****************************************************************/
    public void setPlayer(int player) {
        this.player = player;
    }

    /******************************************************************
     * Constructor creates the Connect Four Game with specified size
     * for the board of the game.
     *
     * @param pSize which is size of the board
     *****************************************************************/
    public ConnectFourGame(int pSize) {
        board = new int[pSize][pSize];
        size = pSize;
        player = USER;
        initZobrist(pSize);
        reset();
    }

    /******************************************************************
     * Save the conditions of the board.
     *
     * @param pSize size of the board
     *****************************************************************/
    private void initZobrist(int pSize) {
        zobrist = new long[pSize][pSize][2];
        Random gen = new Random();
        for (int row = 0; row < zobrist.length; row++) {
            for (int col = 0; col < zobrist[row].length; col++) {
                zobrist[row][col][0] = gen.nextLong();
                zobrist[row][col][1] = gen.nextLong();
            }
        }
    }

    /******************************************************************
     * Map to save all the conditions of the board from zobrist method.
     *
     * @param board of the game
     * @return long hashKey from the zobrist
     *****************************************************************/
    private long hash(int[][] board) {
        long hashKey = 0;
        for (int row = 0; row < zobrist.length; row++) {
            for (int col = 0; col < zobrist[row].length; col++) {
                if (board[row][col] != - 1) {
                    hashKey ^= zobrist[row][col][board[row][col]];
                }
            }
        }

        return hashKey;
    }

    /******************************************************************
     * Resets the game for a new game.
     *****************************************************************/
    public void reset() {
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = -1;
                setPlayer(USER);
            }
    }

    /******************************************************************
     * Determines which column a chip will fall into.
     *
     * @param col column of the board
     * @return int value in the board
     *****************************************************************/
    public int selectCol(int col) {
        for (int row = size - 1; row >= 0; row--)
            if (board[row][col] == -1) {
                board[row][col] = player;
                return row;
            }
        return -1;
    }

    /******************************************************************
     * Switches player from the current player to the other.
     *
     * @return the other player
     *****************************************************************/
    public int switchPlayer() {
        player = (player + COMPUTER) % 2;
        return player;
    }

    /******************************************************************
     * Returns boolean if the player wins.
     *
     * @return boolean if the player win.
     *****************************************************************/
    public boolean isWinner() {
        return isWinner(this.board, this.player);
    }

    /******************************************************************
     * Returns boolean whether the player has connect four or not.
     *
     * @param board of the game
     * @param player wins
     * @return boolean if the player wins
     ******************************************************************/
    public boolean isWinner(int board[][], int player) {

        // Horizontal Win
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] == player
                        && board[r][c + 1] == player
                        && board[r][c + 2] == player
                        && board[r][c + 3] == player)
                    return true;
            }
        }

        //Vertical win
        for (int r = 0; r < board.length - 3; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == player
                        && board[r + 1][c] == player
                        && board[r + 2][c] == player
                        && board[r + 3][c] == player)
                    return true;
            }
        }

        //Diagonal up win
        for (int r = 3; r < board.length; r++) {
            for (int c = 0; c < board[r].length - 3; c++) {
                if (board[r][c] == player
                        && board[r - 1][c + 1] == player
                        && board[r - 2][c + 2] == player
                        && board[r - 3][c + 3] == player)
                    return true;
            }
        }

        //Diagonal down win
        for (int r = 3; r < board.length; r++) {
            for (int c = 3; c < board[r].length; c++) {
                if (board[r][c] == player
                        && board[r - 1][c - 1] == player
                        && board[r - 2][c - 2] == player
                        && board[r - 3][c - 3] == player)
                    return true;
            }
        }
        return false;
    }

    /******************************************************************
     * Adds AI feature to Connect Four Game.
     *
     * @param depth for computer to calculate score
     * @return int column that the chips will fall to
     *****************************************************************/
    public int AIPlay(int depth) {
        //copy the board to get a new board for AI to calculate
        int[][] newBoard = deepCopy(board);
        ColScore colScore = new ColScore(-1,0 );

        // since the board size is too big, depth is low, Minimax tree
        //won't work properly
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length -3; c++) {
                if (board[r][c + 1] == USER && board[r][c + 2] == USER){
                    if (board[r][c] == -1)
                        colScore.column = c;
                    break;
                } else if (board[r][c] == USER &&board[r][c + 1] == USER
                        && board[r][c + 3] == USER) {
                    if (board[r][c+2] == -1)
                        colScore.column = c + 2;
                    break;
                }
            }
        }

        for (int col = 0; col < size; col++) {
            // Winning
            int row = selectCol(newBoard, col, COMPUTER);
            if (row != -1) {
                if (isWinner(newBoard, COMPUTER)) {
                    colScore.column = col;
                    break;
                }
                newBoard[row][col] = -1;
            }

            // Not Losing
            row = selectCol(newBoard, col, USER);
            if (row != -1) {
                if (isWinner(newBoard, USER)) {
                    colScore.column = col;
                    break;
                }
                newBoard[row][col] = -1;
            }
        }

        if (colScore.column != -1) {
            return colScore.column;
        }

        colScore = maximizePlay(this.getBoard(), depth,
                Integer.MIN_VALUE, Integer.MAX_VALUE);

        return colScore.column;
    }

    /******************************************************************
     * Represents the maximizer.
     *
     * @param board board of the game
     * @param depth to calculate the steps
     * @param alpha best value that maximizer can get
     * @param beta best value that minimizer can get
     * @return ColScore position AI will play
     *****************************************************************/
    private ColScore maximizePlay(int[][] board, int depth, int alpha,
                                  int beta) {
        int score = evaluateBoard(board);

        if (depth <= 0 || score == WINNING_SCORE
                || score == LOSING_SCORE || isBoardFull(board)) {
            return new ColScore(-1, score);
        }

        ColScore max = new ColScore(-1, -99999);


        for (int column = 0; column < board[0].length; column++) {
            int[][] newBoard = deepCopy(board);
            if (selectCol(newBoard, column, COMPUTER) != -1) {
                ColScore nextMove = minimizePlay(newBoard, depth--,
                        alpha, beta);

                if (nextMove.score > max.score) {
                    max.column = column;
                    max.score = nextMove.score;
                    alpha = nextMove.score;
                }

                if (alpha >= beta) return max;
            }
        }

        return max;
    }

    /******************************************************************
     * Represents the minimizer.
     *
     * @param board of the game
     * @param depth to calculate steps
     * @param alpha the best value maximizer can play
     * @param beta the best value minimizer can play
     * @return
     *****************************************************************/
    private ColScore minimizePlay(int[][] board, int depth, int alpha,
                                  int beta) {
        int score = evaluateBoard(board);

        if (depth <= 0 || score == WINNING_SCORE ||
                score == LOSING_SCORE || isBoardFull(board)) {
            return new ColScore(-1, score);
        }

        ColScore min = new ColScore(-1, 99999);

        for (int column = 0; column < board[0].length; column++) {
            int[][] newBoard = deepCopy(board);
            if (selectCol(newBoard, column, USER) != -1) {
                ColScore nextMove = maximizePlay(newBoard, depth--,
                        alpha, beta);

                if (nextMove.score < min.score) {
                    min.column = column;
                    min.score = nextMove.score;
                    beta = nextMove.score;
                }

                if (alpha >= beta) return min;
            }
        }

        return min;
    }

    /******************************************************************
     * Returns score after evaluating the board
     *
     * @param board of the game
     * @return int score of the board
     *****************************************************************/
    private int evaluateBoard(int board[][]) {
        if (boardMap.get(hash(board)) != null) {
            return boardMap.get(hash(board));
        }

        int verticalPoints = 0;
        int horizontalPoints = 0;
        int diagonalPoints1 = 0;
        int diagonalPoints2 = 0;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                int score = scorePosition(board, row, column, 1, 0);
                if (score ==  WINNING_SCORE) {
                    return  WINNING_SCORE;
                }
                if (score == LOSING_SCORE) {
                    return LOSING_SCORE;
                }
                verticalPoints += score;
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                int score = scorePosition(board, row, column, 0, 1);
                if (score == WINNING_SCORE) return  WINNING_SCORE;
                if (score == LOSING_SCORE) return LOSING_SCORE;
                horizontalPoints += score;
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                int score = scorePosition(board, row, column, 1, 1);
                if (score ==  WINNING_SCORE) return  WINNING_SCORE;
                if (score == LOSING_SCORE) return LOSING_SCORE;
                diagonalPoints1 += score;
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                int score = scorePosition(board, row, column, -1, 1);
                if (score ==  WINNING_SCORE) return  WINNING_SCORE;
                if (score == LOSING_SCORE) return LOSING_SCORE;
                diagonalPoints2 += score;
            }
        }

        int point = verticalPoints + horizontalPoints +
                diagonalPoints1 + diagonalPoints2;
        boardMap.put(hash(board), point);
        return point;
    }

    /******************************************************************
     * Returns the score based on the position of players
     *
     * @param board of the game
     * @param row of the board game
     * @param column of the board game
     * @param deltaY updates row
     * @param deltaX updates column
     * @return int score position of players
     *****************************************************************/
    private int scorePosition(int[][] board, int row, int column,
                              int deltaY, int deltaX) {
        int userPoint = 0;
        int computerPoint = 0;

        for (int i = 0; i < 4; i++) {
            if (row >= board.length || column >= board.length
                    || row < 0 || column < 0) {
                continue;
            }

            if (board[row][column] == USER) {
                userPoint++;
            } else if (board[row][column] == COMPUTER) {
                computerPoint++;
            }


            row += deltaY;
            column += deltaX;
        }

        if (userPoint == 4) {
            return LOSING_SCORE;
        } else if (computerPoint == 4) {
            return WINNING_SCORE;
        } else {
            return computerPoint;
        }
    }

    /******************************************************************
     * Copies the board to calculate the steps there.
     *
     * @param input the object (here is the board) to be copied
     * @return int[][] the copied board
     *****************************************************************/
    private int[][] deepCopy(int[][] input) {
        if (input == null) {
            return null;
        }
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

    /******************************************************************
     * Determines a column a chip will fall into with the player
     * assigned.
     *
     * @param board of the game
     * @param col of the board
     * @param player of the game
     * @return int position a chip will fall in to
     *****************************************************************/
    private int selectCol(int[][] board, int col, int player) {
        for (int row = size - 1; row >= 0; row--) {
            if (board[row][col] == -1) {
                board[row][col] = player;
                return row;
            }
        }
        return -1;
    }

    /******************************************************************
     * Checks if the board is full.
     *
     * @param board of the game.
     * @return boolean if the board is full.
     *****************************************************************/
    private boolean isBoardFull(int[][] board) {
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == -1) {
                return false;
            }
        }
        return true;
    }

    /******************************************************************
     * ColScore class to get position of column based on score
     *****************************************************************/
    class ColScore {
        private int column;
        private int score;

        /**************************************************************
         * Column positions and calculated score.
         *
         * @param column of the board
         * @param score of the game after calculating it.
         *************************************************************/
        ColScore(int column, int score) {
            this.column = column;
            this.score = score;
        }
    }
}