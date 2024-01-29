
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
			str = str.substring(1);
		}
		return str ;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int x = levenshtein(word1, tail(word2));
		int y = levenshtein(tail(word1), tail(word2));
		int z = levenshtein(tail(word1), word2);
		int a = word1.length();
		int b = word2.length();
		if( a == 0) {
			return b;
		}
		else {
			if( b == 0){
				return a;
			}
			else{
				if( word1.charAt(1) == word2.charAt(1)){
					return levenshtein(tail(word1), tail(word2));
				}
				else {

					return (1 + Math.min(x, Math.min(y, z)));
				}
			}

		}
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
			if(levenshtein(currentWord, word) > levenshtein(word, dictionary[i]) && levenshtein(word, dictionary[i]) < threshold ) {
				currentWord = dictionary[i];
			}
		}
		if(currentWord.isEmpty()){
			return word;
		}
		return currentWord;
	}

}
