package mc.sn.day9;

import java.util.ArrayList;
import java.util.Random;

public class LottoMachine {
	private ArrayList<LottoBall> list;
	
	public LottoMachine(ArrayList<LottoBall> list) {
		this.list = list;
	}
	
	public LottoBall getBall() {
		LottoBall ball = null;
		// 공을 하나씩 뽑는 과정
		Random r = new Random();
		int index = r.nextInt(45);
		ball = list.get(index);
		return ball;
	}
	
	public LottoBall getBallV2() {
		LottoBall ball = null;
		// 공을 하나씩 뽑는 과정
		Random r = new Random();
		int index = r.nextInt(list.size());
		ball = list.remove(index);
		return ball;
	}
	
	public void startMachineV2(int num) {
		//공을 뽑는 과정(반복)
		LottoBall ball = null;
		for(int i=0;i<6;i++) {
			ball = this.getBallV2();
			System.out.println(ball.getNumber()+"번이 뽑혔습니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startMachine(int num) {
		//공을 뽑는 과정(반복)
		LottoBall ball = null;
		for(int i=0;i<6;i++) {
			ball = this.getBall();
			//공을 검사
			if(ball.isSelected()) {
				//true이면 뽑힌 공이다.
				i--;
				continue;
			} else {
				//false
				ball.setSelected(true);
			}
			
			System.out.println(ball.getNumber()+"번이 뽑혔습니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

