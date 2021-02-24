package hashcode2021;

import java.util.ArrayList;
import java.util.List;

public class Delivery {

	private int index;
	
	private int teamNumPerson;
	
	private List<Pizza> listaPizzas = new ArrayList<Pizza>();
	
	private Integer score;
	
	public Delivery() {
		
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getTeamNumPerson() {
		return teamNumPerson;
	}

	public void setTeamNumPerson(int teamNumPerson) {
		this.teamNumPerson = teamNumPerson;
	}

	public List<Pizza> getListaPizzas() {
		return listaPizzas;
	}

	public void setListaPizzas(List<Pizza> listaPizzas) {
		this.listaPizzas = listaPizzas;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	
	
}
