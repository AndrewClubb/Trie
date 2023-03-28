package trieTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import triePD.Trie;

public class TrieTest {
	public void test(){
		
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		
		Trie trie = new Trie();
		String file = "TrieText.txt";
		int index = 0;
		
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			
			int line;
			String word = "";
			
			while((line = bufferedReader.read()) != -1)	{
				char character = (char) line;
				
				if((line >= 65 && line <= 90) || (line >= 97 && line <= 122))
					word += character;
				else if(character == ' ' || character == '.' || character == ',' || character == '"' || character == '&' || character == '/' || character == '\r' || character == '\n') {
					trie.addWord(word, index - word.length());
					word = "";
				}
				index++;
			}
		}
		catch(FileNotFoundException ex)	{
			System.out.println("File not found: " + file);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		catch(StringIndexOutOfBoundsException ex) {
			System.out.println("An error has ocurred");
		}		
		
		String searchWord = "word";
		
		System.out.println("Searching for the word: searchWord");
		System.out.print("Word was found at: " + trie.indexesForWord(searchWord));
	}
}
