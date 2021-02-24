package hashcode2021;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OutputData {

	// Data output
	private int d = 0;
	private Map<Integer, List<Delivery>> deliverys = new HashMap<Integer, List<Delivery>>();
	
	private int four = 0;
	private int three = 0;
	private int two = 0;
	
	public OutputData() {
		
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public Map<Integer, List<Delivery>> getDeliverys() {
		return deliverys;
	}

	public void setDeliverys(Map<Integer, List<Delivery>> deliverys) {
		this.deliverys = deliverys;
	}

	public int getFour() {
		return four;
	}

	public void setFour(int four) {
		this.four = four;
	}

	public int getThree() {
		return three;
	}

	public void setThree(int three) {
		this.three = three;
	}

	public int getTwo() {
		return two;
	}

	public void setTwo(int two) {
		this.two = two;
	}
	
	
			
}
