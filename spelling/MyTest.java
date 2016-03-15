package spelling;

import static java.lang.System.out;

import java.util.List;
public class MyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AutoCompleteDictionaryTrie trie=new AutoCompleteDictionaryTrie();
		out.println(trie.size());
		trie.addWord("Hello");
		out.println(trie.size());
		trie.addWord("HElLo");
		out.println(trie.size());
		trie.addWord("help");
		out.println(trie.size());
		trie.addWord("he");
		out.println(trie.size());
		trie.addWord("hem");
		out.println(trie.size());
		trie.addWord("hot");
		out.println(trie.size());
		trie.addWord("hey");
		out.println(trie.size());
		trie.addWord("a");
		out.println(trie.size());
		trie.addWord("subsequent");
		out.println(trie.size());
		
		//trie.printTree();
		//System.out.println("HElLo".toLowerCase().equals("Hello".toLowerCase()));
		out.println(trie.isWord("hem"));
		out.println(trie.isWord("hey"));
		out.println(trie.isWord(""));
		out.println(trie.size());
		System.out.println("\\-----------------------/");
		List<String> list=trie.predictCompletions("he", 2);
		for(String s:list){
			System.out.println(s);
		}
		System.out.println("\\-----------------------/");
		list=trie.predictCompletions("hel", 10);
		for(String s:list){
			System.out.println(s);
		}
		System.out.println("\\-----------------------/");
		list=trie.predictCompletions("", 2);
		for(String s:list){
			System.out.println(s);
		}
		System.out.println("\\-----------------------/");
		list=trie.predictCompletions("k", 2);
		for(String s:list){
			System.out.println(s);
		}
	}

}
