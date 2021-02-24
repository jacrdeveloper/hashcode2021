package hashcode2021;

import java.util.HashMap;
import java.util.Map;

public class InputData {

	// Data input
	private int m = 0;
	private int tTwo = 0;
	private int tThree = 0;
	private int tFour = 0;
	private Map<Integer, Pizza> pizzas = new HashMap<Integer, Pizza>();
	
	public InputData() {
		
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int gettTwo() {
		return tTwo;
	}

	public void settTwo(int tTwo) {
		this.tTwo = tTwo;
	}

	public int gettThree() {
		return tThree;
	}

	public void settThree(int tThree) {
		this.tThree = tThree;
	}

	public int gettFour() {
		return tFour;
	}

	public void settFour(int tFour) {
		this.tFour = tFour;
	}

	public Map<Integer, Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Integer, Pizza> pizzas) {
		this.pizzas = pizzas;
	}
	
	
	
	
			
}
