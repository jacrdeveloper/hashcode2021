package hashcode2021;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example Google HashCode 2021
 * 
 * @author jacrdeveloper - www.jacrdeveloper.es
 *
 */
public class Solution {
	
	static boolean debug = true;

	public static void main(String[] args) {
		
		
		InputData inputData = new InputData();
		ProcessData processData = new ProcessData();
		OutputData outputData = new OutputData();
		
		String filename = "a_example";
		//String filename = "b_little_bit_of_everything";
		//String filename = "c_many_ingredients";
		//String filename = "d_many_pizzas";
		//String filename = "e_many_teams";
		// Creates a FileReader
		FileReader fileReader = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileReader = new FileReader("src/hashcode2021/" + filename + ".in");
			// Creates a BufferedReader
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			// The 
			String line = bufferReader.readLine();
			String[] splitLine = line.split(" ");
			
			// Read nextLines
			inputData.setM(Integer.valueOf(splitLine[0]));
			inputData.settTwo(Integer.valueOf(splitLine[1]));
			inputData.settThree(Integer.valueOf(splitLine[2]));
			inputData.settFour(Integer.valueOf(splitLine[3]));
			
			if (debug) {
				System.out.println("Numero de pizzas: " + inputData.getM());
				System.out.println("Numero de equipos de 2 personas: " + inputData.gettTwo());
				System.out.println("Numero de equipos de 3 personas: " + inputData.gettThree());
				System.out.println("Numero de equipos de 4 personas: " + inputData.gettFour());
				System.out.println("-------------------------------");
			}
			
			for (int i = 0; i < inputData.getM(); i++) {
				line = bufferReader.readLine();
				splitLine = line.split(" ");
				
				String[] ingredients = new String[Integer.valueOf(splitLine[0])];
				
				Pizza pizza = new Pizza(i, ingredients.length);
				
				for (int o = 0; o < ingredients.length; o++) {
					ingredients[o] = splitLine[o+1];
					addIngredientPizza(processData, ingredients[o], pizza);
				}
				
				inputData.getPizzas().put(i, pizza);
				
				if (debug) {
					System.out.println("Pizza " + i + ":");
					System.out.println("Numero de ingredientes: " + ingredients.length);
					System.out.print("Ingredientes: ");
					for (int o = 0; o < ingredients.length; o++) {
						System.out.print(ingredients[o] + " ");
					}
					System.out.println("");
					System.out.println("-------------------------------");
				}
				
			}
			
			fileReader.close();
			
			process(inputData, outputData, processData);
			
			fileOutputStream = new FileOutputStream(filename + ".out");
			// Creates a BufferedWriter
			BufferedOutputStream bufferOutput = new BufferedOutputStream(fileOutputStream);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append(outputData.getD() + "\n");
			bufferOutput.write(buffer.toString().getBytes());
			
			if (debug) {
				System.out.println("Numero de envios: " + outputData.getD());
				System.out.println("-------------------------------");
			}
			
			for (Map.Entry<Integer, List<Delivery>> entry : outputData.getDeliverys().entrySet()) {
			
				buffer = new StringBuffer();
				Integer numTeam = entry.getKey();
				List<Delivery> listaEnvios = entry.getValue();
				
				for (Delivery delivery : listaEnvios) {
					buffer = new StringBuffer();
					buffer.append(numTeam.intValue());
					if (debug) {
						System.out.println("Tipo de equipo: " + numTeam);
						System.out.print("Pizzas: ");
					}
					List<Pizza> listaPizzasOrdenadas = ordenarPizzas(delivery.getListaPizzas());
					for (Pizza pizza : listaPizzasOrdenadas) {
						buffer.append(' ');
						buffer.append(pizza.getIndex());
	
						if (debug) {
							System.out.print(" " + pizza.getIndex());
						}
					
					}
					if (debug) {
						System.out.println("");
					}
					buffer.append('\n');
					bufferOutput.write(buffer.toString().getBytes());
				}
				
				if (debug) {
					System.out.println("");
					System.out.println("-------------------------------");
				}

			}
			bufferOutput.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
				
				if (fileOutputStream != null) {
					fileOutputStream.close();
				} 
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static List<Pizza> ordenarPizzas(List<Pizza> listaPizzas) {
		
		Collections.sort(listaPizzas, new Comparator<Pizza>() {
			@Override
			public int compare(Pizza p1, Pizza p2) {
				if (p1.getIndex() == p2.getIndex()) {
					return 0;
				} else if (p1.getIndex() > p2.getIndex()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		
		return listaPizzas;
	}
	
	public static void process(InputData inputData, OutputData outputData, ProcessData processData) {
		
		
		pizzaOrderCalc(inputData, processData);
		
		// Se realiza un bucle hasta que hayamos asignado todas las pizzas en algún envío
		
		
		// Miramos como vamos a cuadrar los equipos primero
		// Como primer momento se igualan los equipos de input con output
		int teamTemp = 0;
		int teamFour = 0;
		int teamThree = 0;
		int teamTwo = 0;
		
		int faltan = processData.getListaPizzasOrder().size();
		
		if (debug) {
			System.out.println("----------------------");
			System.out.println("Faltan inicial " + faltan);
			System.out.println("----------------------");
		}
		if (inputData.gettFour() != 0) {
			teamTemp = faltan / 4;
			
			if (teamTemp >= inputData.gettFour()) {
				teamTemp = inputData.gettFour();
			}
			
			teamFour = teamTemp;
			
			if (teamFour != 0) {
				faltan =  faltan - (4 * teamFour);
			}
			
			if (debug) {
				System.out.println("----------------------");
				System.out.println("Team four pizzas" + teamFour);
				System.out.println("Faltan:" + faltan);
				System.out.println("----------------------");
			}
			
		}
		
		if (faltan != 0) {
			if (inputData.gettThree() != 0) {
				teamTemp = faltan / 3;
				
				if (teamTemp >= inputData.gettThree()) {
					teamTemp = inputData.gettThree();
				}
				
				teamThree = teamTemp;
				
				if (teamThree != 0) {
					faltan =  faltan - (3 * teamThree);
				}
				if (debug) {
					System.out.println("----------------------");
					System.out.println("Team three pizzas" + teamThree);
					System.out.println("Faltan:" + faltan);
					System.out.println("----------------------");
				}
			}
			
			if (faltan != 0) {
				if (inputData.gettTwo() != 0) {
					teamTemp = faltan / 2;
					
					if (teamTemp >= inputData.gettTwo()) {
						teamTemp = inputData.gettTwo();
					}
					
					teamTwo = teamTemp;
					
					if (teamTwo != 0) {
						faltan =  faltan - (2 * teamTwo);
					}
					
					if (debug) {
						System.out.println("----------------------");
						System.out.println("Team two pizzas" + teamTwo);
						System.out.println("Faltan:" + faltan);
						System.out.println("----------------------");
					}
				}
			}
		}
		
		// Faltan en este punto debe ser 1 o 2
		if (debug) {
			System.out.println("----------------------");
			System.out.println("Faltan por asignar" + faltan);
			System.out.println("----------------------");
		}
		if (faltan != 0) {
		
			if (teamFour != 0 && inputData.gettThree() != 0 && teamThree < inputData.gettThree()) {
				teamFour --;
				teamThree++;
				faltan++; 
			} else if (teamThree != 0 && inputData.gettTwo() != 0 && teamTwo < inputData.gettTwo()) {
				teamThree--;
				teamTwo++;
				faltan++;
			}
			
			if (faltan == 2 && inputData.gettTwo() != 0 && teamTwo < inputData.gettTwo()) {
				faltan = 0;
				teamTwo++;
			} else if (faltan == 3 && inputData.gettThree() != 0 && teamThree < inputData.gettThree()) {
				faltan = 0;
				teamThree++;
			} 
		}
		
		outputData.setFour(teamFour*4);
		outputData.setThree(teamThree*3);
		outputData.setTwo(teamTwo*2);
		outputData.setD(teamFour + teamThree + teamTwo);
		
		if (debug) {
			System.out.println("----------------------");
			System.out.println("Four Pizzas Teams:" + teamFour);
			System.out.println("Three Pizzas Teams:" + teamThree);
			System.out.println("Two Pizzas Teams:" + teamTwo);
			System.out.println("----------------------");
		}
		
		
		int team = 4;
		int numTeams = outputData.getFour();
		
		Delivery delivery = new Delivery();
		delivery.setTeamNumPerson(team);
		delivery.setListaPizzas(new ArrayList<Pizza>());
		
		if (debug) {
			System.out.println("----------------------");
			System.out.println("Numero de pizzas" + processData.getListaPizzasOrder().size());
			System.out.println("----------------------");
		}
		
		for (int i = 0; i < processData.getListaPizzasOrder().size(); i++) {
			
			if (numTeams == 0) {
				team--;
				if (team == 3) {
					numTeams = outputData.getThree();
				} else if (team == 2) {
					numTeams = outputData.getTwo();
				} else {
					//Aqui nunca debe pasar, en teoría nunca debe haber más pizzas que equipos
					if (debug) {
						System.out.println("Hay más pizzas que gente");
					}
					//Fin
					break;
				}
			}
			
			System.out.println("numTeams" + numTeams);
			System.out.println("team" + team);
			
			Pizza pizza = processData.getListaPizzasOrder().get(i);
			
			delivery.getListaPizzas().add(pizza);

			numTeams--;
			
			if (numTeams % team == 0) {
				if (outputData.getDeliverys().get(team) != null) {
					outputData.getDeliverys().get(team).add(delivery);
				} else {
					List<Delivery> listaDeliverys = new ArrayList<Delivery>();
					listaDeliverys.add(delivery);
					outputData.getDeliverys().put(team, listaDeliverys);
				}
				
				delivery = new Delivery();
				delivery.setTeamNumPerson(team);
				delivery.setListaPizzas(new ArrayList<Pizza>());
			}
		}
		
		if (debug) {
			System.out.println("----------------------");
			
			outputData.getDeliverys().forEach((k,v) -> {
				
				System.out.println("Key: " + k );
				
				System.out.println("Deliverys:" + v.size());
				
				v.forEach(d -> {
					System.out.println("Delivery: " + d.getIndex());
					System.out.println("Score delivery: " + d.getScore());
					System.out.println("Numero de personas: " + d.getTeamNumPerson());
					System.out.println("Pizzas: " + d.getListaPizzas().size());
					d.getListaPizzas().forEach(p -> {
						System.out.println("Pizza: " + p.getIndex());
						System.out.println("Num ingredientes: " + p.getNumIngredients());
						System.out.println("Ingredientes: " + p.getListaIngredients().size());
						p.getListaIngredients().forEach((pK, pV) -> {
							System.out.println("Key: " + pK );
							System.out.println("Ingrediente: " + pV.getIndex());
							System.out.println("Nombre: " + pV.getName());
						});
					});
					System.out.println("----------------------");
					
					
				});
				
			});
			
			System.out.println("----------------------");
		}
		
		// Después le metemos la magia y la liamos parda :D
		// Revisamos si alguna de las combinaciones vale 0 de score,
		// y probamos si podemos cambiar alguna pizza por otra para ver si varía el score
		makeMagic(processData, outputData);
		
	}
	
	public static void makeMagic(ProcessData processData, OutputData outputData) {
		
		// En un último intento, revisamos e intentamos modificar entre ellas las agrupaciones que den 0 
		// para intentar aumentar el score
		List<Delivery> deliveryZero = new ArrayList<Delivery>();
		
		for (Map.Entry<Integer, List<Delivery>> entry : outputData.getDeliverys().entrySet()) {
			
			List<Delivery> listaDeliverys = entry.getValue();
			
			for (Delivery delivery : listaDeliverys) {
				if (delivery.getListaPizzas() != null && delivery.getListaPizzas().isEmpty()) {
					int score = 0;
					Map<String, Integer> mapaScore = new HashMap<String, Integer>();
					for (Pizza pizza : delivery.getListaPizzas()) {
						for (Map.Entry<String, Ingredient> entryIngredient : pizza.getListaIngredients().entrySet()) {
							if (mapaScore.get(entryIngredient.getValue().getName()) == null) {
								mapaScore.put(entryIngredient.getValue().getName(), 1);
							} else {
								mapaScore.put(entryIngredient.getValue().getName(), 
										mapaScore.get(entryIngredient.getValue().getName()) + 1);
							}
						}
					}
					
					for (Map.Entry<String, Integer> entryScore : mapaScore.entrySet()) {
						if (entryScore.getValue() == 1) {
							score++;
						}
					}
					
					if (score == 0) {
						deliveryZero.add(delivery);
						if (debug) {
							System.out.println("------------------");
							System.out.println("Delivery con 0 score");
							System.out.println("------------------");
						}
					}
					
				}
			}
			
		}
		
		if (debug) {
			System.out.println("------------------");
			System.out.println("Delivery con 0 score total: " + deliveryZero.size());
			System.out.println("------------------");
		}
		
	}
	
	public static void pizzaOrderCalc(InputData inputData, ProcessData processData) {
		
		// Ordenamos las pizzas en referencia al numero de ingredientes diferentes relacionados entre ellas
		for (Map.Entry<Integer, Pizza> entry : inputData.getPizzas().entrySet()) {
			processData.getListaPizzasOrder().add(entry.getValue());
		}
		
		Collections.sort(processData.getListaPizzasOrder());
		
		// Usando el orden anterior, identificamos la diferencia de ingredientes en pares
		for (int i = 0; i < processData.getListaPizzasOrder().size(); i++) {
			
			Pizza p1 = null;
			Pizza p2 = null;
			
			if (i == (processData.getListaPizzasOrder().size() - 1)) {
				p1 = processData.getListaPizzasOrder().get(i);
				p2 = processData.getListaPizzasOrder().get(0);
			} else {
				p1 = processData.getListaPizzasOrder().get(i);
				p2 = processData.getListaPizzasOrder().get(i+1);
			}
			
			// Obtenemos todos los ingredientes en un mapa y contamos
			Map<String, Integer> mapaCont = new HashMap<String, Integer>();
			
			p1.getListaIngredients().forEach((k, v) -> {
				Ingredient ing = v;
				if (mapaCont.get(ing.getName()) == null) {
					mapaCont.put(ing.getName(), 1);
				} else {
					mapaCont.put(ing.getName(), mapaCont.get(ing.getName()) + 1);
				}
				
			});
			
			p2.getListaIngredients().forEach((k, v) -> {
				Ingredient ing = v;
				if (mapaCont.get(ing.getName()) == null) {
					mapaCont.put(ing.getName(), 1);
				} else {
					mapaCont.put(ing.getName(), mapaCont.get(ing.getName()) + 1);
				}
				
			});
		
			Integer contIntRepe = 0;
			
			for (Map.Entry<String, Integer> entry : mapaCont.entrySet()){
				
				if (entry.getValue() > 1) {
					contIntRepe++;
				}
				
			}
			
			p1.setDifNext(contIntRepe);
			if (debug) {
				System.out.println("Contador repeticiones Pizza " + p1.getIndex() + " - " + contIntRepe);
			}
			
		}
		
		Collections.sort(processData.getListaPizzasOrder(), new Comparator<Pizza>() {

			@Override
			public int compare(Pizza p1, Pizza p2) {
				int result = 0;
				
				if (p2.getDifNext() > p1.getDifNext()) {
					result = -1;
				} else if (p2.getDifNext() < p1.getDifNext()) {
					result = 1;
				}
				
				return result;
			}
			
		});
		
		if (debug) {
			System.out.println("Pizzas: ");
			// Cuanto mayor es el score, más prioridad tiene la pizza
			processData.getListaPizzasOrder().forEach(pizza -> {
				System.out.println("Indice: " + pizza.getIndex());
			});
			System.out.println("----------------------");
		}
			
	}
	
	public static void addIngredientPizza(ProcessData processData, String strIngredient, Pizza pizza) {
		
		Ingredient ingredient = new Ingredient();
		if (processData.getIngredientsPizzas().get(strIngredient) == null) {
			
			List<Pizza> listaPizzas = new ArrayList<Pizza>();
			listaPizzas.add(pizza);
			
			ingredient.setIndex(processData.getIngredientsPizzas().size());
			ingredient.setName(strIngredient);
			ingredient.getPizzas().put(pizza.getIndex(), pizza);
			
			processData.getIngredientsPizzas().put(strIngredient, ingredient);
			
		} else {
			ingredient = processData.getIngredientsPizzas().get(strIngredient);
			processData.getIngredientsPizzas().get(strIngredient).getPizzas().put(pizza.getIndex(), pizza);
		}
		
		pizza.getListaIngredients().put(strIngredient,ingredient);

	}

}
