/**
 * Node class stores the details about a node
 * 
 * @author Richard Cook
 * @version 1.0 (2014-11-26)
 * @param <T> Type of Node
 */
public class Node<T> {
	T state;
	
	// So we can backtrack
	Node<T> parent;
	
	// Action which got us from parent to this node
	String action;
	
	int pathCost;
	int depth;
	
	public Node(T state, Node<T> parent, String action, int pathCost, int depth) {
		this.state = state;
		this.parent = parent;
		this.action = action;
		this.pathCost = pathCost;
		this.depth = depth;
	}
}