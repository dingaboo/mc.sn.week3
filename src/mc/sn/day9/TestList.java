package mc.sn.day9;
import java.util.ArrayList;

public class TestList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestList tl = new TestList();
		tl.test1();
	}
	// 클래스 A, B 가 상속관계를 가지고 있고 A가 수퍼클래스이면 
	// 다음과 같은 다형성 코드가 가능하다.
	// A a = new B() //promotion
	// B b = (B)a // casting
	public void test1() {
		// ArrayList 를 학습합니다. CRUD 크기
		// ArrayList 인스턴스를 만드세요
		ArrayList list = new ArrayList();
		list.add(new Integer(1)); //삽입 (해당 리스트의 마지막에 삽입)
		list.add(new Integer(2));
		list.add(new Integer(3));
		int size = list.size(); //크기
		System.out.println(size);
		this.printList(list);
		list.set(1,"20"); //변경
		this.printList(list);
		list.remove(1); // 삭제
		this.printList(list);
		
	}
	
	//제너릭 개념 도입 --> 이유는 여러분이 적어보세요
	public void test2() {
		// ArrayList 를 학습합니다. CRUD 크기
		// ArrayList 인스턴스를 만드세요
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(1)); //삽입 (해당 리스트의 마지막에 삽입)
		list.add(new Integer(2));
		list.add(new Integer(3));
		int size = list.size(); //크기
		System.out.println(size);
		this.printList(list);
		list.set(1,new Integer(20)); //변경
		this.printList(list);
		list.remove(1); // 삭제
		this.printList(list);
		
	}
	
	public void test3() {
		// ArrayList 를 학습합니다. CRUD 크기
		// ArrayList 인스턴스를 만드세요
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1); // 1하고 new Integer(1)  boxing(자동으로 해당 객체 생성)
		list.add(2);
		list.add(3);
		int size = list.size(); 
		System.out.println(size);
		this.printList(list);
		list.set(1,20); 
		this.printList(list);
		list.remove(1); 
		this.printList(list);
		
	}
	//int -->Integer char -->Character
	// wrapping 클래스 확인하세요 (책)
	public void printList(ArrayList<Integer> list) {
		for(int i=0;i<list.size();i++) {
			int temp = list.get(i); //조회 1 //unboxing(자동으로 해당하는 기본 타입으로 변경)
			System.out.println(temp);
		}
	}

}