package mc.sn.day9;

public class LottoBall {
	private Integer number;
	private boolean isSelected;
	
	//number를 셋팅하는 생성자 만들고
	public LottoBall(Integer number) {
		this.number = number;
	}
	//각 필드의  get/set을 만드시오.

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
}
