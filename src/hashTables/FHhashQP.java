package hashTables;

/**
 * The Class FHhashQP quadratic probing hash table
 *
 * @param <E> the element type
 */
public class FHhashQP<E> {
	
	/** The Constant ACTIVE. */
	protected static final int ACTIVE = 0;
	
	/** The Constant EMPTY. */
	protected static final int EMPTY = 1;
	
	/** The Constant DELETED. */
	protected static final int DELETED = 2;

	/** The Constant INIT_TABLE_SIZE. */
	static final int INIT_TABLE_SIZE = 97;
	
	/** The Constant INIT_MAX_LAMBDA. */
	static final double INIT_MAX_LAMBDA = 0.49;

	/** The m array. */
	protected HashEntry<E>[] mArray;
	
	/** The m size. */
	protected int mSize;
	
	/** The m load size. */
	protected int mLoadSize;
	
	/** The m table size. */
	protected int mTableSize;
	
	/** The m max lambda. */
	protected double mMaxLambda;
	
	/** The collisions. */
	protected int collisions = 0;

	/**
	 * Instantiates a new f hhash qp.
	 *
	 * @param tableSize the table size
	 */
	// public methods ---------------------------------
	public FHhashQP(int tableSize) {
		mLoadSize = mSize = 0;
		if (tableSize < INIT_TABLE_SIZE)
			mTableSize = INIT_TABLE_SIZE;
		else
			mTableSize = nextPrime(tableSize);

		allocateArray(); // uses mTableSize;
		mMaxLambda = INIT_MAX_LAMBDA;
	}

	/**
	 * Instantiates a new f hhash qp.
	 */
	public FHhashQP() {
		this(INIT_TABLE_SIZE);
	}

	/**
	 * Insert.
	 *
	 * @param x the x
	 * @return true, if successful
	 */
	public boolean insert(E x) {
		int bucket = findPos(x);

		if (mArray[bucket].state == ACTIVE)
			return false;

		mArray[bucket].data = x;
		mArray[bucket].state = ACTIVE;
		mSize++;

		// check load factor
		if (++mLoadSize > mMaxLambda * mTableSize)
			rehash();

		return true;
	}

	/**
	 * Removes the.
	 *
	 * @param x the x
	 * @return true, if successful
	 */
	public boolean remove(E x) {
		int bucket = findPos(x);
		if (mArray[bucket].state != ACTIVE)
			return false;

		mArray[bucket].state = DELETED;
		mSize--; 
		return true;
	}

	/**
	 * Contains.
	 *
	 * @param x the x
	 * @return true, if successful
	 */
	public boolean contains(E x) {
		return mArray[findPos(x)].state == ACTIVE;
	}

	/**
	 * Size.
	 *
	 * @return the int
	 */
	public int size() {
		return mSize;
	}

	/**
	 * Make empty.
	 */
	void makeEmpty() {
		int k, size = mArray.length;

		for (k = 0; k < size; k++)
			mArray[k].state = EMPTY;
		mSize = mLoadSize = 0;
	}

	/**
	 * Sets the max lambda.
	 *
	 * @param lam the lam
	 * @return true, if successful
	 */
	public boolean setMaxLambda(double lam) {
		if (lam < .1 || lam > INIT_MAX_LAMBDA)
			return false;
		mMaxLambda = lam;
		return true;
	}

	// protected methods of class ----------------------

	/**
	 * Find pos.
	 *
	 * @param x the x
	 * @return the int
	 */
	protected int findPos(E x) {
		 
		int kthOddNum = 1;
		int index = myHash(x);

		while (mArray[index].state != EMPTY && !mArray[index].data.equals(x)) {
			index += kthOddNum; // k squared = (k-1) squared + kth odd #
			 ++collisions;
			kthOddNum += 2; // compute next odd #
			if (index >= mTableSize)
				index -= mTableSize;
		}
		return index;
	}

	/**
	 * Rehash.
	 */
	protected void rehash() {

		HashEntry<E>[] oldArray = mArray;
		int k, oldTableSize = mTableSize;

		mTableSize = nextPrime(2 * oldTableSize);

		allocateArray(); // uses mTableSize;

		mSize = mLoadSize = 0;
		for (k = 0; k < oldTableSize; k++)
			if (oldArray[k].state == ACTIVE)
				insert(oldArray[k].data);
	}

	/**
	 * My hash.
	 *
	 * @param x the x
	 * @return the int
	 */
	protected int myHash(E x) {
		int hashVal;

		hashVal = x.hashCode() % mTableSize;
		if (hashVal < 0)
			hashVal += mTableSize;

		return hashVal;
	}

	/**
	 * Next prime.
	 *
	 * @param n the n
	 * @return the int
	 */
	protected static int nextPrime(int n) {
		int k, candidate, loopLim;

		if (n <= 2)
			return 2;
		else if (n == 3)
			return 3;

		for (candidate = (n % 2 == 0) ? n + 1 : n; true; candidate += 2) {
			// all primes > 3 are of the form 6k +/- 1
			loopLim = (int) ((Math.sqrt((double) candidate) + 1) / 6);

			if (candidate % 3 == 0)
				continue;

			// now we can check for divisibility of 6k +/- 1 up to sqrt
			for (k = 1; k <= loopLim; k++) {
				if (candidate % (6 * k - 1) == 0)
					break;
				if (candidate % (6 * k + 1) == 0)
					break;
			}
			if (k > loopLim)
				return candidate;
		}
	}

	/**
	 * Allocate array.
	 */
	@SuppressWarnings("unchecked")
	void allocateArray() {
		int k;

		mArray = new HashEntry[mTableSize];
		for (k = 0; k < mTableSize; k++)
			mArray[k] = new HashEntry<E>();
	}

	

}
