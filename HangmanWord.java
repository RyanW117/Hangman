package softwareEngineering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class used in the HangmanApp that uses a CSV file to randomly pick words
 * for the player to guess.  The CSV can be easily expanded or contracted.
 * I got the random words from:  http://www.randomlists.com/
 * 
 * Assignment: A01 - Hangman
 * Class: CSIS 2450 Software Engineering
 * Time: MW 11:00am - 12:20am
 * Date: September 1, 2014
 * @author Ryan Wilson
 */
public class HangmanWord 
{
	private int numberOfLetters;
	private String word;
	
	private String csvFileLocation = "src/softwareEngineering/csvTest.csv";
	
	/**
	 * Constructor that initializes a word and the number of letters in the word
	 * depending on whether the CSV was found or not.
	 */
	public HangmanWord()
	{
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFileLocation)))
		{
			//To pick random words from the CSV, I skip a random number of characters then
			//read in the word.  The value 11000 is present because there happens to be about 
			//that many characters in the CSV file (I checked by using Microsoft Word).
			reader.skip( (int) (Math.random() * 11000));
			reader.readLine();
			word = reader.readLine();
			numberOfLetters = word.length();
		} 
		catch (IOException e)
		{
			word = backupWords();
			numberOfLetters = word.length();
		}
	}
	
	/**
	 * A list of words that are used if the CSV is not found.
	 * 
	 * @return random word
	 */
	private String backupWords()
	{
		List<String> backupList = new ArrayList<>();
		backupList.add("grotesque");backupList.add("cultured");backupList.add("questionable");
		backupList.add("fallacious");backupList.add("hilarious");backupList.add("disgusted");
		backupList.add("sophisticated");backupList.add("impossible");backupList.add("monkey");
		Collections.shuffle(backupList);
		
		return backupList.get( (int) (Math.random() * (backupList.size())));
	}

	/**
	 * Gets the number of letters in the word.
	 * 
	 * @return number of letters in the word
	 */
	public int getNumberOfLetters() 
	{
		return numberOfLetters;
	}

	/**
	 * Gets a randomly selected word.
	 * 
	 * @return the randomly selected word
	 */
	public String getWord() 
	{
		return word;
	}
	
	/**
	 * toString() method that returns the word.
	 */
	@Override
	public String toString() 
	{
		return word;
	}
	
}