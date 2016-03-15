package textgen;

public class PrintList {
	static MyLinkedList<Integer> ll;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ll = new MyLinkedList<Integer>();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(4);
		System.out.println(ll.size());
		pprint();
		ll.add(2,99);
		pprint();
		ll.add(1, 100);
		pprint();
		ll.set(1, -68);
		pprint();
		ll.remove(1);
		pprint();
		System.out.println(ll.size());
		ll.add(4, 92);
		pprint();
	}
public static void pprint(){
	System.out.println();
	for(int i=0;i<ll.size();i++){
		System.out.println(ll.get(i));
	}
	System.out.println();
}
}
