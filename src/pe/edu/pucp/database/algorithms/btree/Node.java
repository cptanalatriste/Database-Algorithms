package pe.edu.pucp.database.algorithms.btree;

/**
 * B-Tree Node data type
 * 
 * @author cgavidia
 * 
 */
@SuppressWarnings("unchecked")
public class Node<Key extends Comparable<Key>, Value> {

	private int numberOfChildren;
	private Entry<Key, Value>[] childrenArray = new Entry[BTree.M];

	/**
	 * Creates a node with k children
	 * 
	 * @param k
	 *            number of children
	 */
	public Node(int k) {
		numberOfChildren = k;
	}

	public Entry<Key, Value>[] getChildrenArray() {
		return childrenArray;
	}

	public void setChildrenArray(Entry<Key, Value>[] children) {
		this.childrenArray = children;
	}

	public int getNumberOfChildren() {
		return numberOfChildren;
	}

	public void setNumberOfChildren(int childrenNumber) {
		this.numberOfChildren = childrenNumber;
	}

	@Override
	public String toString() {
		String result = "{ ";
		for (int i = 0; i < numberOfChildren; i++) {
			Entry entry = childrenArray[i];
			result = result + entry.toString() + ", ";
		}
		result = result + " }";
		return result;
	}

}