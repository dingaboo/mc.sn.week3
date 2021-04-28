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
		//map �Է�
		map.put("apple", 500);
		map.put("banana", 1000);
		map.put("melon", 3000);
		this.printAll(map);
		//map ������ ���
		int size=map.size();
		System.out.println("map size: "+size);
		
		//key=melon�� value ����غ���
		Integer i1=map.get("melon");
		int r=i1.intValue();
		System.out.println("melon�� value: "+r);
		
		//key=banana ����
		map.remove("banana");
		size=map.size();
		System.out.println("���� �� map size: "+size);
		this.printAll(map);
	}
	
	
	/*
	public void test2() {
		//HashMap --> �ν��Ͻ� ���弼��
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("one", new Integer(1)); 
		map.put("two", new Integer(2));
		map.put("three", new Integer(3));
		//map.put("four", "4"); //����
		
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
		System.out.println("after remove r2 value is "+r2); //���� : key=two ���ŵ�		
	}
	
	
	public void test1() {
		//HashMap --> �ν��Ͻ� ���弼��
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
		System.out.println("after remove r2 value is "+r2); //���� : key=two ���ŵ�
	}
	*/
}
