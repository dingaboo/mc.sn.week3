package mc.sn.day10;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class TestMap {
	public static void main(String[] args) {
		TestMap tm=new TestMap();
		tm.test3();
		
	}
	public void printAll(HashMap<String,Integer> map) {
		Set<String> keys=map.keySet();
		Iterator<String> iter=keys.iterator();
		while(iter.hasNext()) {
			String key=iter.next();
			Integer value=map.get(key);
			System.out.println("("+key+","+value+")");
		}
	}
	
	public void test3() {
		HashMap<String, Integer> map=new HashMap<String, Integer>();		
		//map 입력
		map.put("apple", 500);
		map.put("banana", 1000);
		map.put("melon", 3000);
		this.printAll(map);
		//map 사이즈 출력
		int size=map.size();
		System.out.println("map size: "+size);
		
		//key=melon의 value 출력해보기
		Integer i1=map.get("melon");
		int r=i1.intValue();
		System.out.println("melon의 value: "+r);
		
		//key=banana 삭제
		map.remove("banana");
		size=map.size();
		System.out.println("삭제 후 map size: "+size);
		this.printAll(map);
	}
	
	
	/*
	public void test2() {
		//HashMap --> 인스턴스 만드세요
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("one", new Integer(1)); 
		map.put("two", new Integer(2));
		map.put("three", new Integer(3));
		//map.put("four", "4"); //에러
		
		int size = map.size();
		System.out.println(size);
		Integer i1 = map.get("one");
		int r = i1.intValue();
		System.out.println(r);
		
		map.put("two", new Integer(20));
		Integer i2 = map.get("two");
		int r2 = i2.intValue();
		System.out.println(r2);
		
		map.remove("two");
		size = map.size();
		System.out.println("size is "+size);
		
		i2 = map.get("two");
		r2 = i2.intValue();
		System.out.println("after remove r2 value is "+r2); //에러 : key=two 제거됨		
	}
	
	
	public void test1() {
		//HashMap --> 인스턴스 만드세요
		HashMap map = new HashMap();
		map.put("one", new Integer(1));
		map.put("two", new Integer(2));
		map.put("three", new Integer(3));
		map.put("four", "4");
		
		int size = map.size();
		System.out.println(size);
		
		Integer i1 = (Integer)map.get("one");
		int r = i1.intValue();
		System.out.println(r);
		
		map.put("two", new Integer(20));
		Integer i2 = (Integer)map.get("two");
		int r2 = i2.intValue();
		System.out.println(r2);
		
		map.remove("two");
		size = map.size();
		System.out.println("size is "+size);
		i2 = (Integer)map.get("two");
		r2 = i2.intValue();
		System.out.println("after remove r2 value is "+r2); //에러 : key=two 제거됨
	}
	*/
}
