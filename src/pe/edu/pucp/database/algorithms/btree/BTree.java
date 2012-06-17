package pe.edu.pucp.database.algorithms.btree;

/**
 * B-Tree implementation largely based on the implementation made in Algorithms
 * book by Robert Sedgewick. Original code can be found in <a
 * href="http://algs4.cs.princeton.edu/62btrees/BTree.java.html"> the book web
 * site<a/>.
 * 
 * @author cgavidia
 * 
 * @param <Key>
 *            Type of the Search Key
 * @param <Value>
 *            Type of the Value Stored
 */
public class BTree<Key extends Comparable<Key>, Value> {

	/**
	 * Tree parameter. Every node must have at most M - 1 key-link pairs
	 */
	public static final int M = 4;

	private Node<Key, Value> root;
	/**
	 * Height of B-Tree
	 */
	private int height;
	/**
	 * Number of key-value pairs in B-Tree
	 */
	private int size;

	public BTree() {
		root = new Node<Key, Value>(0);
	}

	public int getSize() {
		return size;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Search for given key
	 * 
	 * @param key
	 *            Key to search
	 * @return Associated value; return null if no such key
	 */
	public Value get(Key key) {
		return search(root, key, height);
	}

	private Value search(Node<Key, Value> node, Key key, int treeHeight) {
		Entry<Key, Value>[] children = node.getChildrenArray();

		// external node
		if (treeHeight == 0) {
			for (int j = 0; j < node.getNumberOfChildren(); j++) {
				if (equal(key, children[j].getKey())) {
					return (Value) children[j].getValue();
				}
			}
		}

		// internal node
		else {
			for (int j = 0; j < node.getNumberOfChildren(); j++) {
				if (node.getNumberOfChildren() == j + 1
						|| less(key, children[j + 1].getKey()))
					return search(children[j].getNext(), key, treeHeight - 1);
			}
		}
		return null;
	}

	/**
	 * Inserts a Key-Value pair
	 * 
	 * @param key
	 *            Key to insert
	 * @param value
	 *            Value to insert
	 */
	public void put(Key key, Value value) {
		// TODO (cgavidia): Rename u and t variable names
		Node<Key, Value> u = insert(root, key, value, height);
		size++;
		if (u == null) {
			return;
		}

		// need to split root
		Node<Key, Value> t = new Node<Key, Value>(2);
		t.getChildrenArray()[0] = new Entry<Key, Value>(
				root.getChildrenArray()[0].getKey(), null, root);
		t.getChildrenArray()[1] = new Entry<Key, Value>(u.getChildrenArray()[0]
				.getKey(), null, u);
		root = t;
		height++;
	}

	private Node<Key, Value> insert(Node<Key, Value> node, Key key,
			Value value, int treeHeight) {
		int j;
		Entry<Key, Value> nodeToInsert = new Entry<Key, Value>(key, value, null);

		// external node
		if (treeHeight == 0) {
			for (j = 0; j < node.getNumberOfChildren(); j++) {
				if (less(key, node.getChildrenArray()[j].getKey())) {
					break;
				}

			}
		}

		// internal node
		else {
			for (j = 0; j < node.getNumberOfChildren(); j++) {
				if ((node.getNumberOfChildren() == j + 1)
						|| less(key, node.getChildrenArray()[j + 1].getKey())) {
					Node<Key, Value> u = insert(node.getChildrenArray()[j++]
							.getNext(), key, value, treeHeight - 1);
					if (u == null) {
						return null;
					}
					nodeToInsert.setKey(u.getChildrenArray()[0].getKey());
					nodeToInsert.setNext(u);
					break;
				}
			}
		}

		for (int i = node.getNumberOfChildren(); i > j; i--) {
			node.getChildrenArray()[i] = node.getChildrenArray()[i - 1];

		}

		node.getChildrenArray()[j] = nodeToInsert;
		node.setNumberOfChildren(node.getNumberOfChildren() + 1);
		if (node.getNumberOfChildren() < M)
			return null;
		else
			return split(node);
	}

	/**
	 * Splits node in half
	 * 
	 * @param nodeToSplit
	 *            The Node to Split
	 * @return
	 */
	private Node<Key, Value> split(Node<Key, Value> nodeToSplit) {
		Node<Key, Value> newNode = new Node<Key, Value>(M / 2);
		nodeToSplit.setNumberOfChildren(M / 2);
		for (int j = 0; j < M / 2; j++) {
			newNode.getChildrenArray()[j] = nodeToSplit.getChildrenArray()[M
					/ 2 + j];
		}
		return newNode;
	}

	public String toString() {
		return toString(root, height, "") + "\n";
	}

	private String toString(Node<Key, Value> currentNode, int ht, String indent) {
		String outputString = "";
		Entry<Key, Value>[] childrenArray = currentNode.getChildrenArray();

		if (ht == 0) {
			for (int j = 0; j < currentNode.getNumberOfChildren(); j++) {
				outputString += indent + childrenArray[j].getKey() + " "
						+ childrenArray[j].getValue() + "\n";
			}
		} else {
			for (int j = 0; j < currentNode.getNumberOfChildren(); j++) {
				if (j > 0) {
					outputString += indent + "(" + childrenArray[j].getKey()
							+ ")\n";
				}
				outputString += toString(childrenArray[j].getNext(), ht - 1,
						indent + "     ");
			}
		}
		return outputString;
	}

	private boolean less(Key k1, Key k2) {
		return k1.compareTo(k2) < 0;
	}

	private boolean equal(Key k1, Key k2) {
		return k1.compareTo(k2) == 0;
	}

}
