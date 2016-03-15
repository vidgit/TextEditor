package spelling;

import java.util.ArrayList;
import java.util.List;

public class MyTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dictionary dict = new DictionaryHashSet();
        DictionaryLoader.loadDictionary(dict, "test_cases/dict2.txt");
		NearbyWords n=new NearbyWords(dict);
		List<String> k=new ArrayList<String>();
		List<String> d1 = n.suggestions("dag",4 );
		System.out.println(d1);
	}

}