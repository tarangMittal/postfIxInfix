package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	private LLNode<T> topNode = null;
	private int count = 0;

	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		if(topNode==null) {
			return null;
		}
		T val = topNode.info;
		topNode = topNode.link;
		count--;
		
		return val;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		if(topNode ==null) {
			return null;
		}
		return topNode.info;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		
		// TODO
		return (topNode==null);
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		
		return count;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		LLNode<T> newNode = new LLNode<T>(elem);
		newNode.link = topNode;
		topNode = newNode;
		count++;
		
		// TODO
	}

}
