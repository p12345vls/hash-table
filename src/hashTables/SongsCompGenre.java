package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * The Class SongsCompGenre wrapper class for SongEntry objects.
 * Compares objects based on the songs String genre field.
 */
public class SongsCompGenre implements Comparable<String> {
	
	/** The name. */
	private final String name;
	
	/** The songs. */
	private final ArrayList<SongEntry> songs;

	/**
	 * Instantiates a new songs comp genre.
	 *
	 * @param name the name
	 */
	public SongsCompGenre(String name) {
		songs = new ArrayList<>();
		this.name = name;
	}

	/**
	 * Adds the song.
	 *
	 * @param song the song
	 */
	public void addSong(SongEntry song) {
		songs.add(song);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof SongsCompGenre && ((SongsCompGenre) obj)
				.getName().equals(name))
				|| (obj instanceof String && ((String) obj).equals(name));
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(String key) {
		return name.compareTo(key);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public ArrayList<SongEntry> getData() {
		return songs;
	}
}
