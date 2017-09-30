package hashTables;

import java.util.ArrayList;

import cs1c.SongEntry;

/**
 * The Class TableGenerator generates two tables one with ids and one with song genres.
 * @author pavlosp1967
 */
public class TableGenerator {

	/** The genre names. */
	ArrayList<String> genreNames;
	
	/** The song int. */
	FHhashQPwFind<Integer, SongCompInt> songInt;
	
	/** The song string. */
	FHhashQPwFind<String, SongsCompGenre> songString;

	/**
	 * Instantiates a new table generator.
	 */
	public TableGenerator() {
		songInt = new FHhashQPwFind<>();
		songString = new FHhashQPwFind<>();
		genreNames = new ArrayList<>();
	}

	/**
	 * Populate table with Integers.
	 *
	 * @param allSongs the all songs
	 * @return the table
	 */
	public FHhashQPwFind<Integer, SongCompInt> populateIDtable(
			SongEntry[] allSongs) {

		for (int i = 0; i < allSongs.length; i++) {

			Integer intId = allSongs[i].getID();
			SongCompInt id = songInt.find(intId);
			if (id == null) {
				id = new SongCompInt(intId);
				songInt.insert(id);
			}

		}
		return songInt;
	}

	/**
	 * Populate table with String genre .
	 *
	 * @param allSongs the songs
	 * @return the  genre table
	 */
	public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(
			SongEntry[] allSongs) {

		for (int i = 0; i < allSongs.length; i++) {

			String genreString = allSongs[i].getGenre();
			SongsCompGenre genre = songString.find(genreString);

			if (genre == null) {
				genreNames.add(genreString);
				genre = new SongsCompGenre(genreString);
				songString.insert(genre);
			}
			
			genre.addSong(allSongs[i]);
		}

		return songString;
	}

	/**
	 * Gets the genre names.
	 *
	 * @return the genre names
	 */
	public ArrayList<String> getGenreNames() {
		return genreNames;
	}

}
