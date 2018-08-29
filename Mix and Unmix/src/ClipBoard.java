
/**********************************************************************
 * Class for the clip board that has a linked list
 *
 * @author (Michelle Vu and Trang Nguyen)
 * @version 4/12/18
 *********************************************************************/
public class ClipBoard {

    /** Clip boards variable that has clip board node*/
    private static TheLinkedList<ClipBoardNode> clipBoards
            = new TheLinkedList<>();

    /** Top of clip boards */
    private ClipBoardNode top;


    /******************************************************************
     * Returns the clip boards.
     *
     * @return static clip boards.
     *****************************************************************/
    public static TheLinkedList<ClipBoardNode> get() {
        return clipBoards;
    }

    /******************************************************************
     * Adds element  to top of the clip board node
     *
     * @param clipBoardNode the node of clip board
     *****************************************************************/
    void addAtTop(ClipBoardNode clipBoardNode) {
        if (top == null) {
            top = clipBoardNode;
        } else {
            clipBoardNode.setNext(top);
            top = clipBoardNode;
        }
    }

    /******************************************************************
     * Gets the clip board number
     *
     * @param clipBoardNum clip board number
     * @return node of clip board
     *****************************************************************/
    ClipBoardNode getByClipBoardNum(int clipBoardNum) {
        ClipBoardNode current = top;
        while (current != null) {
            if (current.getClipBoardNumber() == clipBoardNum) {
                return current;
            }

            current = current.getNext();
        }

        return null;
    }
}
