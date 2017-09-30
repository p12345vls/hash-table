package hashTables;

/**
 * The Class FHhashQPwFind extends FHhashQP and provides modifications to
 * methods find(),myHash(),findPosition() and remove().
 * 
 * @param <KeyType>
 *            the generic type
 * @param <E>
 *            the element type
 * @author pavlosp1967
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends
		FHhashQP<E> {

	/**
	 * Find a key in the table
	 * 
	 * @param key
	 *            the key
	 * @return the key
	 */
	protected E find(KeyType key) {
		int currentPos = findPosKey(key);
		return contains((E) key) ? mArray[currentPos].data : null;
	}

	/**
	 * My hash key.
	 * 
	 * @param key
	 *            the key
	 * @return the key
	 */
	protected int myHashKey(KeyType key) {

		return myHash((E) key);
	}

	/**
	 * Find position key.
	 * 
	 * @param key
	 *            the key
	 * @return the key
	 */
	protected int findPosKey(KeyType key) {

		return findPos((E) key);

	}

	/**
	 * Removes Id values
	 * 
	 * @param id
	 *            the id to remove
	 * @return true if removed
	 */
	protected boolean remove(Integer id) {
		return remove((E) id);
	}

	/**
	 * Removes Genre values
	 * 
	 * @param genre
	 *            the genre to remove
	 * @return true if removed
	 */
	protected boolean remove(String genre) {
		return remove((E) genre);
	}

}
