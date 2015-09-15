package abe.anime_generator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TitleWordReader {

	private Random random = new Random();
	private BufferedReader reader;
	private ArrayList<String> words;
	
	/**
	 * @param path the path to the file containing the words
	 * @throws IOException
	 */
	public TitleWordReader(String path) throws IOException {
		reader = new BufferedReader(new FileReader(path));
		words = new ArrayList<>();
		String line = reader.readLine();
		while (line != null) {
			words.add(line);
			line = reader.readLine();
		}
		reader.close();
	}
	
	/**
	 * @return a randomly generated title made with words from the
	 * provided file
	 */
	public String getRandomTitle() {
		int titleSize = random.nextInt(3) + 2;
		StringBuilder builder = new StringBuilder();
		String word;
		do {
			word = getRandomWord();
		} while (isSuffix(word));
		builder.append(word);
		for (int i = 1; i < titleSize; i++) {
			word = getRandomWord();
			if (!isSuffix(word)) {
				builder.append(" ");
			}
			builder.append(word);
			
		}
		return builder.toString();
	}
	
	/**
	 * @return a random word from the file
	 */
	private String getRandomWord() {
		return words.get(random.nextInt(words.size()));
	}
	
	/**
	 * @param word 	the word to be checked
	 * @return whether the word is a suffix or not
	 */
	private boolean isSuffix(String word){
		return word.matches("-.*");
	}
}

