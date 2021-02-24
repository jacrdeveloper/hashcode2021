package hashcode2021;

import java.util.HashMap;
import java.util.Map;

public class Pizza implements Comparable<Pizza> {

	private int index;
	
	private int numIngredients;
	
	private Map<String, Ingredient> listaIngredients = new HashMap<String, Ingredient>();
	
	private int difNext = 0;
	
	public Pizza() {
		
	}
	
	public Pizza(int index, int numIngredients) {
		super();
		this.index = index;
		this.numIngredients = numIngredients;
	}
	
	public Pizza(int index, int numIngredients, Map<String, Ingredient> listaIngredients) {
		super();
		this.index = index;
		this.numIngredients = numIngredients;
		this.listaIngredients = listaIngredients;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getNumIngredients() {
		return numIngredients;
	}

	public void setNumIngredients(int numIngredients) {
		this.numIngredients = numIngredients;
	}

	public Map<String, Ingredient> getListaIngredients() {
		return listaIngredients;
	}

	public void setListaIngredients(Map<String, Ingredient> listaIngredients) {
		this.listaIngredients = listaIngredients;
	}

	public int getDifNext() {
		return difNext;
	}

	public void setDifNext(int difNext) {
		this.difNext = difNext;
	}

	@Override
	public int compareTo(Pizza pizzaTwo) {
		int result = 0;
		
		if (pizzaTwo.getNumIngredients() > getNumIngredients()) {
			result = 1;
		} else if (pizzaTwo.getNumIngredients() < getNumIngredients()) {
			result = -1;
		}
		
		return result;
	}
	
	
}
