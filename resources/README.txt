project folder:
pavlosP1967_project07/


Brief description of submitted files:

pavlosP1967_project07/src/hashTables/MyTunes.java
  - Tests the functionality of FHhashQPwFind.java. Specifically checks for
  	implementation of find() function to return an object associated with a given
  	key input.
	
 pavlosP1967_project07/src/hashTables/FHhashQP.java 
  - FHhashQP quadratic probing hash table class 

 
 pavlosP1967_project07/src/hashTables/FHhashQPwFind.java 
	-The Class FHhashQPwFind extends FHhashQP and provides modifications to
	 methods find(),myHash(),findPosition() and remove(). 


pavlosP1967_project07/src/hashTables/HashEntry.java 
 	-Creates a new hash entry


pavlosP1967_project07/src/hashTables/SongCompInt.java 
	 - Wrapper class for a SongEntry object. Compares objects based on the songs int
	  id field.It includes main() for testing the SongCompInt class


pavlosP1967_project07/src/hashTables/SongCompInt.java 
	 - Wrapper class for SongEntry objects.
	   Compares objects based on the songs String genre field.


pavlosP1967_project07/src/hashTables/TableGenerator.java 
 	- Generates two tables one with ids and one with song genres.
 	
 	
pavlosP1967_project07/src/hashTables/TestClass.java  	
	-The Class TestClass tests quadratic probing hash table search
 	 vs. sequential find on the ArrayList of SongEntry objects. 	


pavlosP1967_project07/src/cs1c/MillionSongDataSubset.java
    - One object of class MillionSongDataSubset parses a JSON data set and stores each entry in an array.

  
   
pavlosP1967_project07/src/cs1c/SongEntry.java 
  - One object of class SongEntry stores a simplified version of the genre data set from 
    the Million Song Dataset.
 
    
pavlosP1967_project07/src/cs1c/TimeConverter.java
  - Class method that converts seconds into format:
	hours : minutes : seconds. 
 
 
pavlosP1967_project07/resources/music_genre_subset.json
   - JSON file contains all the songs
 
   
pavlosP1967_project07/resources/resources/findGenres.txt
   - File for hashing based on genres names
 
   
pavlosP1967_project07/resources/resources/findIDs.txt
   - File for hashing based on IDs
 
   
pavlosP1967_project07/resources/resources/graph1.png
   - Graph representation of Test cases
   
RUN.txt
    - console output of MyTunes.java
    - console output of TestClass.java
    - console output of SongCompInt.java

README.txt
    - description of submitted files