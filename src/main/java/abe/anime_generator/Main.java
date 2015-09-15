package abe.anime_generator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tumblr.jumblr.*;
import com.tumblr.jumblr.types.TextPost;

public class Main {
    
	static TitleWordReader cardReader;
	
	public static void main( String[] args ){
		// create a new cardReader
		try {
			cardReader = new TitleWordReader("src/titlecards.txt");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// print random title if there are no args, give error if the
		// number of args is greater than zero but not four
		if (args.length == 0) {
			System.out.println(cardReader.getRandomTitle());
			return;
		}
		else if (args.length != 4) {
			System.err.println("Invalid arguments: format for args is '<consumerKey> <consumerSecret> <token> <tokenSecret>'");
			return;
		}
		
		// parse input arguments
		String consumerKey = args[0];
		String privateKey = args[1];
		String token = args[2];
		String tokenSecret = args[3];
        
		// sets up a new client
		final JumblrClient client = new JumblrClient(
          consumerKey,
          privateKey
        );
        client.setToken(
          token,
          tokenSecret
        );
        
        // posts the title to Tumblr
        try {
 			TextPost post = client.newPost("anime-generator.tumblr.com", TextPost.class);
 			String title = cardReader.getRandomTitle();
 			post.setTitle(title);
 			List<String> tags = new ArrayList<>();
 			tags.add("anime");
 			tags.add("anime generator");
 			post.setTags(tags);
 			post.save();
 		} catch (IllegalAccessException e) {
 			e.printStackTrace();
 		} catch (InstantiationException e) {
 			e.printStackTrace();
 		}
	}
	
}
