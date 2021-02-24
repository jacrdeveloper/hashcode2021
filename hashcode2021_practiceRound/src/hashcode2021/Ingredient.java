package hashcode2021;

import java.util.HashMap;
import java.util.Map;

public class Ingredient {

	private int index;
	
	private String name;
	
	private Map<Integer, Pizza> pizzas = new HashMap<Integer, Pizza>();

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Integer, Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(Map<Integer, Pizza> pizzas) {
		this.pizzas = pizzas;
	}

}
