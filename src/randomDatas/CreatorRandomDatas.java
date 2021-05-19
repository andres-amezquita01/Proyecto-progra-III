package randomDatas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import model.Gender;
import model.Person;


/**
 * Crea datos aleatoriamente
 * @author Andres Felipe Amezquita Gordillo
 *
 */
public class CreatorRandomDatas {
	private Gender[] gender = Gender.values();
	private Random r = new Random();
	/**
	 * Crea numero entero random
	 * @param minimum 
	 * @param maximum
	 * @return
	 */
	public  int  generateRandomInt(int minimum, int maximum) {
		return r.nextInt(maximum) + minimum;
	}
	/**
	 * Crea un numero long random en el intervalo deseado.
	 * @param minimum
	 * @param maximum
	 * @return
	 */
	public  long generateRandomlong(int minimum, int maximum) {
		return (long)r.nextInt(maximum) + minimum;
	}
	public  double generateRandomDouble(int minimum) {
		return r.nextDouble() + minimum;

	}
	public  int generateRandomHour( int minumun, int maximum) {
		int numero = (int)(Math.random()*(minumun-maximum+1)+maximum);
		return numero ;
	
	}
	/**
	 * Genera un texto de perfil de forma aleatoria 
	 * @return string random con texto de loremipzum;
	 */
	public String generateRandomProfile() {
		String[] profile = new String[] {"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, "
						,"quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, "
						,"sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, "
						,"consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam",
						"quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, "
						,"nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, "
						,"quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur?At vero eos et "
						,"accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores "
						,"et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia"
						," animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore,"
						,"cum soluta nobis est eligendi optio, cumque nihil impedit, quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est,"
						,"omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet, ut et voluptates repudiandae sint et molestiae non recusandae."
						,"Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat"};
		return profile[r.nextInt((profile.length-1))];
	}
	
	/**
	 * Crea una cadena de caracteres aleatorea.
	 * @return
	 */
	public String generateRandomPassPort() {
		char[] alphabet = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		char[] numbers = new char[] {'0','1','2','3','4','5','6','7','8','9'};
		String passPort = "";
		for (int i = 0; i < 20; i++) {
			passPort = passPort + alphabet[r.nextInt((alphabet.length-1))] + numbers[r.nextInt((numbers.length-1))];
		}
		return passPort;
	}
	public  Gender generateRandomGender() {
		return gender[r.nextInt(gender.length )];
	}
	/**
	 * Genera un nombre aleatorio.
	 * @param quantity
	 * @return
	 */
	public  String autogenerateNames(int quantity, int gender) {
		String nameRandom = "";
		if(gender == 1) {
			String[] menNames = {"David", "Carlos", "Antonio", "Jesus", "Luis", "Daniel", "Tomas", 
								"Santiago","Brayan", "Oscar", "Diego", "Jose", "Pepe", "Manuel", "Lucas", "Jasinto", "Cristobal"
								,"Alonso", "Julio", "Leon", "Hector", "Henry", "Kevin", "Martin", "Andres", "Felipe"};
			nameRandom = menNames[(int) (Math.floor(Math.random() * ((menNames.length - 1) - 0 + 1) + 0))] + " "
					+ menNames[(int) (Math.floor(Math.random() * ((menNames.length - 1) - 0 + 1) + 0))];
		}else {
			String[] femaleNames = { "Andrea", "Maria", "Luisa", "Catalina", "Omaira", "Clara",  "Olga","Eva","Cristina", "Angelica"
							 , "Adriana", "Samantha", "Claudia", "Mirian", "Wendy", "Ana", "Nicole", "Ximena", "Monica","Isabel",
							 "Karen", "Sara", "Yazmin", "Cecilia", "Tania", "Sofia"};
			for (int i = 0; i < quantity; i++) {
				nameRandom = femaleNames[(int) (Math.floor(Math.random() * ((femaleNames.length - 1) - 0 + 1) + 0))] + " "
						+ femaleNames[(int) (Math.floor(Math.random() * ((femaleNames.length - 1) - 0 + 1) + 0))];
			}
		}
		return nameRandom;
	}
	/**
	 * Genera un apellido aleatorio.
	 * @param quantity
	 * @return
	 */
	public  String autogenerateLastNames(int quantity) {
		 String nameRandom = "";
		String[] lastNames = { "Gomez", "Guerrero", "Cardenas", "Gutierrez", "Cardona", "Cardoso", "Sanchez", "Garcia",
				"Lozano", "Castillo", "Lopez", "Castro", "Grande", "Cardenaz", "Pinto", "Rodriguez", "Romero",
				"Monsalve","Perez", "Castro", "Blanco", "Suarez", "Macallan", "Ruiz", "Pinto", "Rodriguez",
				 "Perdomo", "Cardoso", "Sanchez" };
		for (int i = 0; i < quantity; i++) {
			nameRandom = lastNames[(int) (Math.floor(Math.random() * ((lastNames.length - 1) - 0 + 1) + 0))] 
					 +" "+lastNames[(int) (Math.floor(Math.random() * ((lastNames.length - 1) - 0 + 1) + 0))];

		}
		return nameRandom;
	}
	public  LocalDate generateLocalDate() {
		LocalDate date = LocalDate.of(2020, generateRandomInt(1, 8), generateRandomInt(1,27));
		return date;
	}
	public  String generateLocation() {
		 String nameRandom = "";
		String[] locations = { "Tunja", "Bogota", "Duitama", "Garagoa", "Sogamoso", "Popayan", "Cali", "Medellin",
				 "Tuta", "Cucuta", "Huila"};
		nameRandom = locations[generateRandomInt(0, locations.length-1)];
		return nameRandom;
	}
	/**
	 * Genera un listado con la cantidad indicada de personas.
	 * @param quantityDatas cantidad de personas que se desean generar.
	 * @return listado de personas.
	 */
	public ArrayList<Person> generateDatas(int quantityDatas){
		ArrayList<Person> personList = new ArrayList<>();
		for (int i = 0; i < quantityDatas; i++) {
			Gender gender = generateRandomGender();
			Person p = new Person(generateRandomlong(6000000, 40000000), autogenerateNames(20, gender.ordinal()), autogenerateLastNames(20),gender, generateLocalDate(),
					generateRandomDouble(10000),  generateRandomProfile(), null, generateRandomPassPort());
			personList.add(p);
		}
		return personList;
	}
	public  Gender stringToGender(String gender) {
		switch (gender) {
			case "Masculino":
				return Gender.MALE;
			case "Femenino":
				return Gender.FEMALE;
			default:
				return Gender.MALE;
		}
	}
	public  LocalDate parseStringToLocalDate(String date) { 
		LocalDate localDate1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return localDate1;
	}
}
