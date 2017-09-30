package hashTables;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * Tests the functionality of FHhashQPwFind.java. Specifically checks for
 * implementation of find() function to return an object associated with a given
 * key input.
 */
public class MyTunes {

	/** The Constant SHOW_DETAILS. */
	public static final boolean SHOW_DETAILS = false;

	/** The table of song i ds. */
	private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;

	/** The table of genres. */
		private FHhashQPwFind<String, SongsCompGenre> tableOfGenres;

	/** The genre names. */
	private ArrayList<String> genreNames;

	/**
	 * Populates two hash tables: - a hash table for comparing songs based on
	 * their int field ID. - a hash table for comparing songs based on their
	 * String field genre.
	 * 
	 * @param allSongs
	 *            Array of SongEntry objects
	 */
	public MyTunes(SongEntry[] allSongs) {
		TableGenerator g = new TableGenerator();

		tableOfSongIDs = g.populateIDtable(allSongs);
		tableOfGenres = g.populateGenreTable(allSongs);
		genreNames = g.getGenreNames();

	}

	/**
	 * Uses MillionSongDataSubset to read all songs from data file.
	 * 
	 * @param jsonFile
	 *            A JSON file to parse
	 * @return The array of SongEntry objects
	 */
	public static SongEntry[] readSongsFromDataFile(String jsonFile) {
		MillionSongDataSubset msd = new MillionSongDataSubset(jsonFile);
		SongEntry[] allSongs = msd.getArrayOfSongs();
		System.out.printf("Total number of songs read %d \n", allSongs.length);

		return allSongs;
	}

	/**
	 * Basic file reader which reads a file with format: [value to find] [value
	 * to find] etc. And stores each value into a list.
	 * 
	 * @param filename
	 *            The input file to read.
	 * @return Read lines as a list.
	 */
	public ArrayList<String> readFindRequests(String filename) {
		ArrayList<String> requests = new ArrayList<>();
		Scanner input = null;

		try {
			File infile = new File(filename);
			input = new Scanner(infile);

			String line = "";
			while (input.hasNextLine()) {
				line = input.nextLine();

				requests.add("" + line);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (input != null)
				input.close();
		}
		return requests;
	}

	/**
	 * Tests the FHhashQPwFind and wrapper class SongCompInt.
	 * 
	 * @param filename
	 *            The input file to read.
	 */
	public void testIDtable(String filename) {
		System.out.printf("Test file for id table: %s \n", filename);

		ArrayList<String> idsToFind = readFindRequests(filename);

		for (String idStr : idsToFind) {
			int id;
			try {
				id = Integer.parseInt(idStr);

			} catch (NumberFormatException e) {
				System.out.printf("\nWarning: Input \"%s\" is not a valid number. Skipping.\n", idStr);
				continue;
			}

			System.out.printf("\nFinding song id: %d\n", id);

			try {

				SongCompInt compResult = tableOfSongIDs.find(id);
				if (compResult != null) {
					System.out.printf("Song id %d found in tableOfSongIDs. \n", id);
				} else {
					System.out.printf("Song id %d NOT found in tableOfSongIDs.\n", id);
				}
			} catch (NoSuchElementException e) {
				System.out.printf("Song id %d NOT found in tableOfSongIDs.\n", id);
			}
		}

		System.out.println("Done with testing table of ids.\n");
	}

	/**
	 * Tests the FHhashQPwFind and wrapper class SongsCompGenre.
	 * 
	 * @param filename
	 *            The input file to read.
	 */
	private void testGenreTable(String filename) {
		System.out.printf("Test file for genre table: %s \n", filename);

		System.out.println("\nNumber of store songs in each genre:");
		ArrayList<String> genreKeys = this.genreNames;

		for (String genreName : genreKeys) {

			SongsCompGenre genre = tableOfGenres.find(genreName);
			System.out.printf("%20s \t %6d \n", genre.getName(), genre.getData().size());
		}

		ArrayList<String> genresToFind = readFindRequests(filename);

		for (String genre : genresToFind) {
			System.out.printf("\nFinding genre: %s\n", genre);

			try {
				SongsCompGenre compResult = tableOfGenres.find(genre);
				if (compResult != null) {
					System.out.printf("Genre \"%s\" found in tableOfGenres. \n", genre);
				} else {
					System.out.printf("Genre \"%s\" NOT found in tableOfGenres.\n", genre);
				}
			} catch (NoSuchElementException e) {
				System.out.printf("Genre \"%s\" NOT found in tableOfGenres.\n", genre);
			}
		}

		System.out.println("Done with testing table of genres.\n");
	}

	/**
	 * Creates an object of type MyTunes class that, which reads the song
	 * information from a JSON input file and stores all entries in an array of
	 * SongEntry objects. Constructs an object of MyTunes, which populates two
	 * hash tables. Each tables uses different attribute of SongEntry class as
	 * the key to find. Tests finding keys in each hash table and reports
	 * whether requested key is found.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		final String DATAFILE = "resources/music_genre_subset.json";
		final String TESTFILE01 = "resources/findIDs.txt";
		final String TESTFILE02 = "resources/findGenres.txt";

		SongEntry[] allSongs = readSongsFromDataFile(DATAFILE);

		MyTunes theStore = new MyTunes(allSongs);

		theStore.testIDtable(TESTFILE01);
		theStore.testGenreTable(TESTFILE02);
		System.err.flush();

		System.out.println("\nDone with MyTunes.");
	}
}
