package mc.sn.review;

public class ReviewWeek2 {

	public static void main(String[] args) {
		ReviewWeek2 rw2=new ReviewWeek2();
		try{
			rw2.test1();
		} catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e.getMessage());
		}
		rw2.test2();
		rw2.test4();
	}
	
	//ex.인덱스 없이 배열 내용 출력
	public void test4() {
		int[] numbers= {1,2,3};
		for(int temp: numbers) {
			System.out.println(temp);
		}
	}
	
	//ex.오버로딩
	public void test3(int a) {}
	public void test3(String b) {}
	public void test3(int a, String b) {}
	public void test3(String b, int a) {}
	
	//ex.증감연산자
	public void test2() {
		int x=0;
		System.out.println("x++ = "+x++);
		System.out.println("x = "+x);
	}
	
	//ex.배열 생성
	public void test1() throws ArrayIndexOutOfBoundsException {
		int [] arr=new int[0];
		System.out.println(arr[0]);
	}
}
