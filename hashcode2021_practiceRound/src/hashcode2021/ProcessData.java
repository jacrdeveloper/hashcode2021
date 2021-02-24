package hashcode2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProcessData {

	// Data process
	private Map<String, Ingredient> ingredientsPizzas = new HashMap<String, Ingredient>();
	
	// Lista de ingredientes ordenados de más a menos se repiten
	private List<Ingredient> listaIngredientesOrder = new ArrayList<Ingredient>();
	
	// Lista de pizzas ordenados de más a menos ingredientes y que más o menos se repiten
	private List<Pizza> listaPizzasOrder = new ArrayList<Pizza>();
	
	public ProcessData() {
		
	}

	public Map<String, Ingredient> getIngredientsPizzas() {
		return ingredientsPizzas;
	}

	public void setIngredientsPizzas(Map<String, Ingredient> ingredientsPizzas) {
		this.ingredientsPizzas = ingredientsPizzas;
	}

	public List<Ingredient> getListaIngredientesOrder() {
		return listaIngredientesOrder;
	}

	public void setListaIngredientesOrder(List<Ingredient> listaIngredientesOrder) {
		this.listaIngredientesOrder = listaIngredientesOrder;
	}

	public List<Pizza> getListaPizzasOrder() {
		return listaPizzasOrder;
	}

	public void setListaPizzasOrder(List<Pizza> listaPizzasOrder) {
		this.listaPizzasOrder = listaPizzasOrder;
	}

			
}
