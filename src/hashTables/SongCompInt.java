package hashTables;

import cs1c.SongEntry;

/**
 * Wrapper class for a SongEntry object. Compares objects based on the songs int
 * id field.It includes main() for testing the SongCompInt class
 * 
 */
public class SongCompInt implements Comparable<Integer> {

	/** The id. */
	Integer id;

	/**
	 * Instantiates a new song comp int.
	 * 
	 * @param id
	 *            the id
	 */
	public SongCompInt(Integer id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Integer key) {
		return id - key;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SongCompInt)
				|| (obj instanceof Integer && id.compareTo((Integer) obj) == 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return id.hashCode();
	}

	static String DATAFILE = "resources/music_genre_subset.json";

	public static void main(String[] args) {

		SongEntry[] songs = MyTunes.readSongsFromDataFile(DATAFILE);
		TableGenerator generator = new TableGenerator();
		FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs = new FHhashQPwFind<>();

		tableOfSongIDs = generator.populateIDtable(songs);
		System.out.println("Populating id table..\nids inserted:"
				+ tableOfSongIDs.size());

		int idsfound = 0;
		for (int i = 0; i < songs.length; i++) {
			if (tableOfSongIDs.find(songs[i].getID()) != null)
				idsfound++;
		}
		System.out.println("\nFind the ids in the table..\nfound: " + idsfound);

		
		int removed=0;
		for (int i = 0; i < songs.length; i++) {
			if (tableOfSongIDs.remove((songs[i]).getID()))
				removed++;
		}
		System.out.println("\nRemove ids from the table..\nremoved: " + removed);
		System.out.println("TableOfSongIDs size:"+tableOfSongIDs.size());
	}
}
