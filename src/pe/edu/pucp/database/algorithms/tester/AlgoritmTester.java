package pe.edu.pucp.database.algorithms.tester;

import pe.edu.pucp.database.algorithms.btree.BTree;

/**
 * @author cgavidia
 * 
 */
public class AlgoritmTester {

	private static final String DELL_VALUE = "143.166.224.230";
	private static final String DELL_KEY = "www.dell.com";
	private static final String EBAY_VALUE = "66.135.192.87";
	private static final String EBAY_KEY = "www.ebay.com";
	private static final String APPLE_VALUE = "17.112.152.32";
	private static final String APPLE_KEY = "www.apple.com";
	private static final String SIMPSONS_VALUE = "209.052.165.60";
	private static final String SIMPSONS_KEY = "www.simpsons.com";
	private static String CS_PRINCETON_KEY = "www.cs.princeton.edu";
	private static final String CS_PRINCETON_VALUE = "128.112.136.11";

	private static String FAKE_KEY = "www.harvardsucks.com";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BTree<String, String> bTreeForTest = getBtreeForTest();

		System.out.println("cs.princeton.edu:  "
				+ bTreeForTest.get(CS_PRINCETON_KEY));
		System.out.println("hardvardsucks.com: " + bTreeForTest.get(FAKE_KEY));
		System.out.println("simpsons.com:      "
				+ bTreeForTest.get(SIMPSONS_KEY));
		System.out.println("apple.com:         " + bTreeForTest.get(APPLE_KEY));
		System.out.println("ebay.com:          " + bTreeForTest.get(EBAY_KEY));
		System.out.println("dell.com:          " + bTreeForTest.get(DELL_KEY));

		System.out.println("size:    " + bTreeForTest.getSize());
		System.out.println("height:  " + bTreeForTest.getHeight());
		System.out.println(bTreeForTest);
	}

	private static BTree<String, String> getBtreeForTest() {
		BTree<String, String> btree = new BTree<String, String>();

		btree.put(CS_PRINCETON_KEY, CS_PRINCETON_VALUE);
		btree.put(SIMPSONS_KEY, SIMPSONS_VALUE);
		btree.put(APPLE_KEY, APPLE_VALUE);
		btree.put(EBAY_KEY, EBAY_VALUE);
		btree.put(DELL_KEY, DELL_VALUE);

		btree.put("www.princeton.edu", "128.112.128.15");
		btree.put("www.yale.edu", "130.132.143.21");

		btree.put("www.amazon.com", "207.171.182.16");
		btree.put("www.cnn.com", "64.236.16.20");
		btree.put("www.google.com", "216.239.41.99");
		btree.put("www.nytimes.com", "199.239.136.200");
		btree.put("www.microsoft.com", "207.126.99.140");
		btree.put("www.slashdot.org", "66.35.250.151");
		btree.put("www.espn.com", "199.181.135.201");
		btree.put("www.weather.com", "63.111.66.11");
		btree.put("www.yahoo.com", "216.109.118.65");

		return btree;
	}

}
