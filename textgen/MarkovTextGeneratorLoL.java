package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{	
		String[] source=sourceText.split("[ ]+");
		for(int i=0;i<source.length;i++){
			boolean flag=false;
			ListIterator<ListNode> iterator=wordList.listIterator();
			while(iterator.hasNext()){
				ListNode w=iterator.next();
				if(w.getWord().equals(starter)){
					w.addNextWord(source[i]);
					flag=true;
					break;
				}
			}
			if(!flag){
				ListNode w=new ListNode(starter);
				w.addNextWord(source[i]);
				wordList.add(w);
				}
			starter=source[i];
		}
		//wordList.add(new ListNode(starter));
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		String output="";
		starter="";
		if(wordList.size()>0){
		for(int i=0;i<numWords;i++){
			boolean flag=false;
			ListIterator<ListNode> iterator=wordList.listIterator();
			while(iterator.hasNext()){
				ListNode w=iterator.next();
				if(w.getWord().equals(starter)){
					//System.out.println(w);
					String ss=w.getRandomNextWord(rnGenerator);
					output+=(ss+" ");
					starter=ss;
					flag=true;
					break;
				}
			
			}
			if(!flag){
				String ss=wordList.get(0).getRandomNextWord(rnGenerator);
				starter=ss;
				output+=(ss+" ");
			}
		}
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		String[] source=sourceText.split("[ ]+");
		for(int i=0;i<source.length;i++){
			boolean flag=false;
			ListIterator<ListNode> iterator=wordList.listIterator();
			while(iterator.hasNext()){
				ListNode w=iterator.next();
				if(w.getWord().equals(starter)){
					w.addNextWord(source[i]);
					flag=true;
					break;
				}
			}
			if(!flag){
				ListNode w=new ListNode(starter);
				w.addNextWord(source[i]);
				wordList.add(w);
				}
			starter=source[i];
		}
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		System.out.println(gen.generateText(20));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		
		int i=generator.nextInt(nextWords.size());
		String s=nextWords.get(i);
		return s;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


