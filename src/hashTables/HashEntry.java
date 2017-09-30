package hashTables;

/**
 * The Class HashEntry.
 *
 * @param <E> the element type
 */
public class HashEntry<E>
{
   
   /** The data. */
   public E data;
   
   /** The state. */
   public int state;
   
   /**
    * Instantiates a new hash entry.
    *
    * @param x the x
    * @param st the state
    */
   public HashEntry( E x, int st )
   {
      data = x;
      state = st;
   }

   /**
    * Instantiates a new hash entry.
    */
   public HashEntry()
   {
      this(null, FHhashQP.EMPTY);
   }
}