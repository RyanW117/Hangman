package softwareEngineering;

import java.util.Scanner;


/**
 * A Hangman game that allows players to play hangman.  The player is able
 * to guess letters or words in order to figure out the unknown word.  The
 * player only has six lives and they get subtracted off every time the
 * player guesses the wrong letter or word.
 * When the entire stick figure is shown, the player loses.
 * 
 * Programming Note: This program might be able to be improved by adding
 * another fun factor like points or extra lives.
 * 
 * Assignment: A01 - Hangman
 * Class: CSIS 2450 Software Engineering
 * Time: MW 11:00am - 12:20am
 * Date: September 1, 2014
 * @author Ryan Wilson
 */
public class HangmanApp
{
	public static void main(String[] args) 
	{
		
		HangmanWord word = new HangmanWord();
		Scanner input = new Scanner(System.in);
		String guess;

		int livesLeft = 6;
		char[] characters = new char[word.getNumberOfLetters()];
		boolean correct = false;
		
		String correctWord = "";
		
		for (int i = 0; i < word.getNumberOfLetters(); i++)
		{
			correctWord += "_ ";
			characters[i] = '_';
		}
		
		do
		{	
			printGallows(livesLeft, correctWord);
			
			//If the word contains no more blank spaces, you win.
			if (!correctWord.contains("_"))
			{
				System.out.println("You Won, you did not get hung!");
				break;
			}
			
			//If the player still has lives, keep guessing.
			if (livesLeft > 0)
				System.out.print("Your guess: ");
			else
			{
				System.out.println("You got hung!");
				System.out.println("The word was: " + word);
				break;
			}
			
			guess = input.nextLine();
			System.out.println();
			
			// If the length of the guess is greater than one (the guess is a word),
			// compare the correct word with the guess.
			if (guess.length() > 1)
			{
				if (guess.equalsIgnoreCase(word.getWord()))
				{
					correctWord = "";
					for (int i = 0; i < word.getNumberOfLetters(); i++)
						correctWord += word.getWord().substring(i, i + 1) + " ";
					
					correct = true;
				}
			}
			//If the guess is not a word, check each character in the correct word.  If the player
			//guessed correctly, put that character in the character array.
			else
			{				
				for (int i = 0; i < word.getNumberOfLetters(); i++)
				{
					if (word.getWord().substring(i, i + 1).equals(guess))
					{
						characters[i] = guess.charAt(0);
						correct = true;
					}
				}
				
				//If the player got a character right, recreate the unknown word with
				//the player's correct guess.
				if (correct)
				{
					correctWord = "";
					for (int i = 0; i < word.getNumberOfLetters(); i++)
						correctWord += characters[i] + " ";
				}
			}
			
			if (!correct)
				livesLeft--;
			correct = false;
			
		} while (livesLeft > -1);
		
		input.close();
	}
	
	/**
	 * Displays the gallows in different ways depending on how many lives the player
	 * has and how long the unknown word is.
	 * 
	 * @param livesLeft
	 * @param correctWord
	 */
	public static void printGallows(int livesLeft, String correctWord)
	{
		switch (livesLeft)
		{
			case 0:
				System.out.println(" ;--,\n |  O\n | /|\\\n | / \\    " + correctWord + "\n_|____");
				break;
			case 1:
				System.out.println(" ;--,\n |  O\n | /|\\\n | /       " + correctWord + "\n_|____");
				break;
			case 2:
				System.out.println(" ;--,\n |  O\n | /|\\\n |         " + correctWord  + "\n_|____");
				break;
			case 3:
				System.out.println(" ;--,\n |  O\n | /|  \n |         " + correctWord + "\n_|____");
				break;
			case 4:
				System.out.println(" ;--,\n |  O\n |  |  \n |         " + correctWord + "\n_|____");
				break;
			case 5:
				System.out.println(" ;--,\n |  O\n |     \n |         " + correctWord + "\n_|____");
				break;
			case 6:
				System.out.println(" ;--,\n |   \n |     \n |         " + correctWord + "\n_|____");
				break;
		}
	}
}
