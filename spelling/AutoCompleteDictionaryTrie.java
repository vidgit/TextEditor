package spelling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		//System.out.println("Adding "+word);
		word=word.toLowerCase();
		TrieNode curr=root;
		int i=0;
		if(!isWord(word)){
		while(curr!=null&&i<word.length())
		{
		char c=word.charAt(i++);
		if(curr.getChild(c)==null)
			{curr=curr.insert(c);}
		else
			curr=curr.getChild(c);
		}
		
		curr.setEndsWord(true);
		//System.out.println(prev.getText()+" : "+prev.endsWord());
		//System.out.println(curr.getText()+" : "+curr.endsWord());
		size++;
		return true;
		}
		
			//System.out.println("It already exists in the list");
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String word) 
	{
	    // TODO: Implement this method
		word=word.toLowerCase();
		TrieNode curr=root;
		int i=0;
		if(word=="")
			return false;
		while(i<word.length()&&curr.getChild(word.charAt(i))!=null)
		{	
			if(curr.getText().equals(word))
				return true;
			//System.out.println(word.charAt(i)+" "+curr.endsWord()+" "+curr.getText());
			curr=curr.getChild(word.charAt(i++));
		}
		return (i>=word.length()?curr.endsWord():false);
	/**	else{
			System.out.println(prev.getText()+"::"+word+"=="+word.equals(prev.getText()));
		
	}*/
	}
	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 TrieNode current=root;
    	 int i=0;
    	 prefix=prefix.toLowerCase();
    	 if(!prefix.equals("")){
    	 char c=prefix.charAt(0);
    	 while((i+1)<prefix.length()&&current.getChild(c)!=null){
    		// System.out.println("1 "+c+":"+current.getChild(c).getText()+"::"+current.getText());
    		// System.out.println(current.getChild(c).getText());
    		 current=current.getChild(c);
    		 
    		 c=prefix.charAt(++i);
    		// System.out.println(c+":"+current.getChild(c));
    		
    		// System.out.println(i+"::"+prefix.length());
    		// System.out.println("2 "+c+":"+current.getChild(c).getText()+"::"+current.getText());
    		// System.out.println(current.getChild(c).getText());
    		// System.out.println(current.getChild(c)!=null);
    	 	}
    	// System.out.println("------i="+i);
    	 if(i<prefix.length()-1||current.getChild(prefix.charAt(i))==null){
    		// System.out.println(prefix+": Stem not found");
    		 return new ArrayList<String>();
    	 }
    	 }
    	
    	 
    		// System.out.println(prefix+":"+current.getText()+":"+": Stem is found!");
    		 Queue<TrieNode> nodeList=new LinkedList<TrieNode>();
    		 if(!prefix.equals(""))
    		 nodeList.add(current.getChild(prefix.charAt(i)));
    		 else
    			 nodeList.add(root);
    		 List<String> list=new ArrayList<String>();
    		 if(isWord(prefix)){
    			 list.add(prefix);
    			 numCompletions--;
    		 }
    		 while(numCompletions>0&&!(nodeList.isEmpty())){
    			 TrieNode curr=nodeList.remove();
    			 if(curr.endsWord()&&!list.contains(curr.getText())){
    				 list.add(curr.getText());
    				 numCompletions--;
    				// System.out.println(numCompletions+":"+curr.getText());
    			 }
    			 Set<Character> set=curr.getValidNextCharacters();
    			 Iterator<Character> iterator=set.iterator();
    			 while(iterator.hasNext()){
    				 nodeList.add(curr.getChild(iterator.next()));
    			 }
    		 }
    		 return list;
    	 
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}