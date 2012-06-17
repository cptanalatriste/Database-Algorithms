package pe.edu.pucp.database.algorithms.btree;

/**
 * Entry in a node. Internal nodes only use key and next while External Nodes
 * use key and value
 * 
 * @author cgavidia
 * 
 * @param <Key>
 *            Type of the Search Key
 * @param <Value>
 *            Type of the Value Stored
 */
public class Entry<Key extends Comparable<Key>, Value> {

	private Key key;
	private Value value;
	/**
	 * Helper field to iterate over array entries
	 */
	private Node<Key, Value> next;

	public Entry(Key key, Value value, Node<Key, Value> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public Node<Key, Value> getNext() {
		return next;
	}

	public void setNext(Node<Key, Value> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "(Key: " + key + " Value: " + value + ")";
	}

}
