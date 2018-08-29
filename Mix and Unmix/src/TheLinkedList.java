
/**********************************************************************
 * Linked list class that has all of the linked list function.
 *
 * @author Michelle Vu and Trang Nguyen
 * @version 4/12/18
 *********************************************************************/
public class TheLinkedList<E> {
	  /** Top node */
    private Node<E> top;

    /** Clip board */
    ClipBoard clipBoard = new ClipBoard();

    /** Default constructor */
    TheLinkedList() {
        top = null;
    }

    /******************************************************************
     * Gets the top node.
     *
     * @return the top node.
     *****************************************************************/
    
    private Node<E> getTop() {
        return top;
    }
    /******************************************************************
     * Sets the top node of the linked list.
     *
     * @param top the top node.
     *****************************************************************/
    
    public void setTop(Node<E> top) {
        this.top = top;
    }
    /******************************************************************
     * Copies the linked list and add it to the clip board.
     *
     * @param start of copied node
     * @param end of copied node
     * @param clipNum - clip board number that has the copied linked
     *                list
     *****************************************************************/
    
    void copy(int start, int end, int clipNum){
        if(start > end
                || clipNum < 0
                || start < 0
                || start > getLen()
                || end > getLen())
            throw new IndexOutOfBoundsException();

        Node<E> nodeTop = new Node<>();
        nodeTop.setData(get(start).getData());
        Node<E> currentNode = nodeTop;
        for (int i = start + 1; i <= end ; i++) {
            Node<E> next = new Node<>();
            next.setData(get(i).getData());
            currentNode.setNext(next);
            currentNode = next;
        }

        ClipBoardNode clipBoardNode = new ClipBoardNode
        		(nodeTop, clipNum, null);

        clipBoard.addAtTop(clipBoardNode);
    }
    
    /******************************************************************
     * Cuts the linked list and add it to the clip board.
     *
     * @param start of the cut node
     * @param end of the cut node
     * @param clipNum - clip board number that has the cut linked
     *                list
     * @return the String message for the commands.
     *****************************************************************/
    
    String cut(int start, int end, int clipNum){
        String message = "";

        if(start > end
                || clipNum < 0
                || start < 0
                || start > getLen()
                || end > getLen())
            throw new IndexOutOfBoundsException();

        Node<E> nodeTop = new Node<>();
        nodeTop.setData(get(start).getData());
        Node<E> currentNode = nodeTop;
        for (int i = start+1; i <= end ; i++) {
            Node<E> next = new Node<>();
            next.setData(get(i).getData());
            currentNode.setNext(next);
            currentNode = next;


        }
        for(int x = start;x<=end;x++){
        	
        	if(get(x).getData().equals(' ')) {
        		message = "r " +"_" + " " + (x) +"\n" + message;
        	}else
            message = "r " +get(x).getData() + " " + (x) +"\n" + message;
        }
        for(int j = end; j >=start; j--)
        delAt(j);
        ClipBoardNode clipBoardNode = new ClipBoardNode
        		(nodeTop, clipNum, null);
        clipBoard.addAtTop(clipBoardNode);

        return message;

    }
    
    /******************************************************************
     * Pastes the linked list that saved in the clipboard according to
     * the clip board number.
     *
     * @param position to paste the linked list
     * @param clipNum clip board number
     * @return the message for the commands
     *****************************************************************/
    String paste(int position, int clipNum) {
        String message = "";
        int num = position;
        if(clipNum < 0 || position < 0 || position > getLen()) {
            throw new IndexOutOfBoundsException();
        }
        ClipBoardNode clipBoardNode = 
        		clipBoard.getByClipBoardNum(clipNum);

        if (clipBoardNode == null) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> current = clipBoardNode.getTop();

        while (current != null) {
            message = "b " + num + "\n" + message;
            addAtPosition(current.getData(), position++);
            current = current.getNext();
        }
        return message;
    }
    
    /******************************************************************
     * Swaps the letter from the parameter first to the second.
     *
     * @param first index one of the swap
     * @param second index two of the swap
     *****************************************************************/
    
    void swapLetters(int first, int second) {
        if(first == second
                || first > getLen()
                || first < 0
                || second < 0
                || second > getLen())
            throw new IndexOutOfBoundsException();
        else {
            E temp1 = get(first).getData();
            E temp2 = get(second).getData();
            get(first).setData(temp2);
            get(second).setData(temp1);
        }
    }
    
    /******************************************************************
     * Adds the node to the top of the list.
     *
     * @param data the data that will go into the node on the top
     ******************************************************************/
    
    void addAtTop(E data) {
        // the list does not exist
        if (top == null)
            top = new Node<>(data, null);
            // the list exist
        else {
            top = new Node<>(data, top);
        }
    }

    /******************************************************************
     * Adds a node at a specific position
     * @param data the node that you want to add
     * @param pos where you want that node to go
     *****************************************************************/
    void addAtPosition(E data, int pos) {
    	
        if( pos < 0) {
            throw new IndexOutOfBoundsException();
        }
        else if(top == null) {
            top = new Node<>(data, null);
        }
        else if(pos == getLen())
            append(data);
        else if(pos == 0) {
            addAtTop(data);
        }
        else {
            Node<E> temp = top;
            for(int i = 0; i < pos -1; i++) {
            	if(temp.getNext()!=null) {
            		  temp = temp.getNext();
            	}
            }
            //set param data to location
            temp.setNext(new Node<>(data, temp.getNext()));
        }
    }
    
    /******************************************************************
     * To append the data.
     *
     * @param data the needs to appends
     *****************************************************************/
    
    private void append(E data) {
        // Case 0:  The list does not exist.
        if (top == null)
            addAtTop(data);

            // Case 1: at Least one item in list
        else {
            Node temp = top;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node<>(data, temp.getNext()));
        }

    }
    
    /******************************************************************
     * Gets the node at a specific position.
     *
     * @param pos in the linked list
     * @return the node
     *****************************************************************/
    
    Node<E> get(int pos){

        Node<E> temp = getTop();
        for(int i = 0; i < pos; i++){
            temp = temp.getNext();
        }
        return temp;
    }
    
    /******************************************************************
     * Gets the length of the node.
     *
     * @return the length of the node
     *****************************************************************/
    
    int getLen() {
        int count = 0;
        Node<E> temp = getTop();
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    /****************************************************************
     * Removes the top element of the list
     *
     * @return returns the element that was removed.
     *
     * @throws RuntimeException if top == null, that is,
     * 			 there is no list.
     ****************************************************************/
    
    private void removeTop() {
        if (top == null) {
            throw new RuntimeException();
        } else if (top.getData() != null) {
            top = top.getNext();
        } else {
            Node<E> temp = top;
            while ((temp.getNext() != null)) {
                if (temp.getNext().getData() != null) {

                    temp.setNext(temp.getNext().getNext());
                }

                temp = temp.getNext();
            }
        }
    }
    
    /******************************************************************
     * Deletes all spaces
     * @return String that will decode the deletion
     *****************************************************************/
    
    String deleteSpace() {
		String message= "";
		int times = 0;
		Node<E> temp = top;
		for(int i = 0; i < getLen();i++) {
			if(temp.getData().equals(' ')) {
				message = "r " + "_" +" "+ (i+times)+"\n" + message;
				times++;
				delAt(i);
				if(i<getLen()-1)
					temp = temp.getNext().getNext();
				else if(i==getLen()-1)
					temp = temp.getNext();
			} else
				temp = temp.getNext();
		}
		return message;

	}

    /****************************************************************
     *
     * This Method removes a node at the specific index position.  The
     * first node is index 0.
     *
     * @param pos the position in the linked list that is to be
     * 			removed.  The first position is zero.
     *
     * @throws IllegalArgumentException if index < 0 or
     * 			index >= size of the list
     *
     ****************************************************************/
    
    void delAt(int pos){
        if(pos < 0 || pos > getLen()) {
            // throw new IllegalArgumentException();
        } else if(pos == 0) {
            removeTop();
        } else {
            Node<E> temp = top;

            //set node to space before location
            for(int i = 0; i < pos-1; i++){
                temp = temp.getNext();
            }

            //skips specified node in linked list
            temp.setNext(temp.getNext().getNext());
            for(int i = pos + 1; i <= getLen(); i++) {
                temp = temp.getNext();
            }
        }
    }
    
    /******************************************************************
     * Deletes the vowels in the linked list.
     * @return message for the commands.
     *****************************************************************/
    
    String deleteVowels() {
        String message= "";
        int times = 0;
        for(int j = 0; j <= getLen();j++) {
            Node<E> temp = top;
            for(int i = 0; i < getLen();i++) {
                if(temp.getData().equals('i')
                        || temp.getData().equals('e')
                        || temp.getData().equals('u')
                        || temp.getData().equals('o')
                        || temp.getData().equals('a')
                        || temp.getData().equals('I')
                        || temp.getData().equals('E')
                        || temp.getData().equals('U')
                        || temp.getData().equals('O')
                        || temp.getData().equals('A')) {

                    message = "r " + temp.getData() +" "+ 
                    (i+times)+"\n" + message;
                    times++;
                    System.out.println(message);
                    delAt(i);
                    if(i<getLen()-1)
                        temp = temp.getNext().getNext();
                    else if(i==getLen()-1)
                        temp = temp.getNext();
                } else
                    temp = temp.getNext();
            }


        }

        return message;
    }

    /******************************************************************
     * Removes range of the linked list.
     *
     * @param start of node the being deleted
     * @param end of node the being deleted
     *****************************************************************/
    
    String removeRange(int start, int end) {
        String Message = "";
        Node<E> temp = top;
        if (top == null) {
            throw new RuntimeException();
        } else if(start > end || start < 0 || end > getLen()) {
            throw new IndexOutOfBoundsException();
        } else {
            if (start == 0) {
                for(int i = start; i <= end; i++) {
                    if(top.getData().equals(' ')) {
                        Message = "r " + "_" + " "+ (i) +
                        		"\n" + Message;
                        top = top.getNext();
                    } else {
                        Message = "r " + top.getData() + " " +
                    (i) + "\n" + Message;
                        top = top.getNext();
                    }
                }

            } else {
                for(int i = 0; i < start - 1; i++) {
                    temp = temp.getNext();
                }
                Node <E> endT = temp;
                for(int i = start - 1; i <= end ; i++) {
                    endT = endT.getNext();
                    if(i< end)
                        if(endT.getData().equals(' ')) {
                            Message ="r " + "_" + " "+ (i+1) 
                            		+ "\n" + Message;
                        }else
                            Message ="r " + endT.getData() + " "
                        + (i+1) + "\n" + Message;
                }
                temp.setNext(endT);
            }
        }
        return Message;
    }
    
    /******************************************************************
     * To string method for linked list with spaces
     *
     * @return linked list with space
     *****************************************************************/
    
    public String toString(){
        StringBuilder x = new StringBuilder();
        for(int i = 0; i < getLen(); i++){
            x.append(get(i).getData()).append("  ");
        }
        return x.toString();
    }
    
    /******************************************************************
     * To string method for linked list without spaces
     *
     * @return linked list without spaces
     *****************************************************************/
    
    String toStringNoSpace(){
        StringBuilder x = new StringBuilder();
        for(int i = 0; i < getLen(); i++){
            x.append(get(i).getData());
        }
        return x.toString();
    }
}
