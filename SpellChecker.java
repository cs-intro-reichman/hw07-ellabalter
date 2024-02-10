
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1) {
			str = "";
		}
		else {
			str = str.substring(1,str.length());
		}
		return str ;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int a = word1.length();
		int b = word2.length();

		if(word1.isEmpty()) {
			return b;
		}
		if( word2.isEmpty()){
			return a;
			}
		if(word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		    }
		int min = Math.min(levenshtein(word1, tail(word2)), Math.min(levenshtein(tail(word1), tail(word2)), levenshtein(tail(word1), word2)));
					return (1 + min);
		}


	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		String word;
		for(int i = 0; i < dictionary.length; i++) {
			word = in.readLine();
			dictionary[i] = word;
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String currentWord = "";
		for(int i = 0; i < dictionary.length; i++){
			// # feedback - In order to be more efficient, it is better to call to levenshtein once per word in the dictionary. This means it is better to
			// save the current min in a variable, and also call "levenshtein(word, dictionary[i])" once and assign it to a variable.
			if((levenshtein(currentWord, word) > levenshtein(word, dictionary[i])) && (levenshtein(word, dictionary[i]) <= threshold) ) {
				currentWord = dictionary[i];
			}
		}
		if(currentWord.length() == 0){
			return word;
		}
		return currentWord;
	}
}
