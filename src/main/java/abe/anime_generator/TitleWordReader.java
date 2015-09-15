package abe.anime_generator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TitleWordReader {

	private Random random = new Random();
	private BufferedReader reader;
	private ArrayList<String> cards;
	
	public TitleWordReader(String path) throws IOException {
		reader = new BufferedReader(new FileReader(path));
		cards = new ArrayList<>();
		String line = reader.readLine();
		while (line != null) {
			cards.add(line);
			line = reader.readLine();
		}
		reader.close();
	}
	
	public String getRandomTitle() {
		int titleSize = random.nextInt(3) + 2;
		StringBuilder builder = new StringBuilder();
		String card;
		do {
			card = getRandomCard();
		} while (isSuffix(card));
		builder.append(card);
		for (int i = 1; i < titleSize; i++) {
			card = getRandomCard();
			if (!isSuffix(card)) {
				builder.append(" ");
			}
			builder.append(card);
			
		}
		return builder.toString();
	}
	
	private String getRandomCard() {
		return cards.get(random.nextInt(cards.size()));
	}
	
	private boolean isSuffix(String card){
		return card.matches("-.*");
	}
}

