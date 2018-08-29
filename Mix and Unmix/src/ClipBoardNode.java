
/**********************************************************************
 * Node for the clip board
 *
 * @author (Michelle Vu and Trang Nguyen)
 * @version 4/12/18
 *********************************************************************/
public class ClipBoardNode {
    private Node top; //Node references the top of each clipboard
    private int clipBoardNumber;
    private ClipBoardNode next;


    public ClipBoardNode(Node top, int clipBoardNumber , 
    		ClipBoardNode next) {
        this.next = next;
        this.top = top;
        this.clipBoardNumber = clipBoardNumber;
    }

    public int getClipBoardNumber() {
        return clipBoardNumber;
    }

    public void setClipBoardNumber(int clipBoardNumber) {
        this.clipBoardNumber = clipBoardNumber;
    }

    public ClipBoardNode() {
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public ClipBoardNode getNext() {
        return next;
    }

    public void setNext(ClipBoardNode next) {
        this.next = next;
    }
}
