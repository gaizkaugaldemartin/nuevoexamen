package gaizka;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Gaizka Ugalde Martin
 * 
 *         Guardar un catálogo de revistas, permitir introducirlas y mostarlas.
 *         
 * 
 */
public class main {

	private static Revista revistaNueva;
	public static Scanner teclado = new Scanner(System.in);
	private static ArrayList<Revista> listaRevistas = new ArrayList<Revista>();

	private static final String MOSTRAR_REVISTA = "1";
	private static final String INTRODUCIR_REVISTA = "2";
	private static final String GUARDAR_REVISTA = "3";
	private static final String SALIR = "4";

	/**
	 * Crea un objeto Revista y pide los datos
	 * Si el dato es incorrecto volvera a pedirlo. 
	 * Una vez introducidos los datos, muestra un resumen de estos y pregunta si se
	 * desea guardar en listado o cancelar
	 * 
	 * @throws RevistaException
	 */
	private static void nuevaRevista() throws RevistaException {

		revistaNueva = new Revista();

		System.out.println("\nIntroduce los datos de la revista");

		boolean datos = true;

		do {
			datos = insertarTitulo();
		} while (!datos);

		do {
			datos = insertarIsbn();
		} while (!datos);

		do {
			datos = insertarNumPaginas();
		} while (!datos);

		String formato = "";

		do {
			System.out.println("\nIntroduce el formato: \n(1) -> Digital \n(2)-> Papel");

			formato = teclado.next();

			if (formato.equals("1")) {
				revistaNueva.setFormato(true);
			} else if (formato.equals("2")) {
				revistaNueva.setFormato(false);
			} else {
				System.out.println("Formato introducido incorrecto.");
			}

		} while (!formato.equals("1") && !formato.equals("2"));

		System.out.println("\nResumen de los datos introducidos");
		System.out.println("-------------------------------------");
		System.out.printf("%20s %12s %12s %12s", "Titulo |", "ISBN|", "Núm. Páginas|", "Formato");
		System.out.println("\n--------------------------------------------");
		System.out.println(revistaNueva.toString());

		String confirmacion = "";

		do {

			System.out.println("Desea guardar la revista?\n(1) - Sí \n(2) - No");

			confirmacion = teclado.next();

			if (confirmacion.equals("1")) {

				listaRevistas.add(revistaNueva);
				System.out.println("Revista añadida al catálogo.");

			} else if (confirmacion.equals("2")) {

				System.out.println("Operación de introducir revista cancelada.");
				break;

			} else {
				System.out.println("Opción introducida incorrecta.");
			}

		} while (!confirmacion.equals("1") && !confirmacion.equals("2"));

	}

	/**
	 * Inserta titulo y devuelve true si se ha introducido correctamente
	 * 
	 * @return boolean true = si se ha introducido bien, false = si ha fallado
	 */
	private static boolean insertarTitulo() throws RevistaException {

		boolean resultado = false;

		try {

			System.out.println("\nIntroduce el título: ");

			revistaNueva.setTitulo(teclado.nextLine());
			resultado = true;

		} catch (Exception e) {

			System.out.println("Error");
		}

		return resultado;
	}

	/**
	 * Inserta ISBN y devuelve true si se ha introducido correctamente
	 * 
	 * @return boolean true = si se ha introducido bien, false = si falla
	 */
	private static boolean insertarIsbn() throws RevistaException {

		boolean resultado = false;

		try {

			System.out.println("\nIntroduce el ISBN: ");

			revistaNueva.setIsbn(teclado.next());
			resultado = true;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return resultado;
	}

	/**
	 * Inserta Número de páginas y devuelve true si se ha introducido correctamente
	 * 
	 * @return boolean true = si se ha introducido bien, false = si no
	 */
	private static boolean insertarNumPaginas() throws RevistaException {

		boolean resultado = false;

		try {

			System.out.println("\nIntroduce el número de páginas: ");
			revistaNueva.setNumeroPaginas(teclado.next());
			resultado = true;

		} catch (Exception e) {

			System.out.println("Error, introduce un número");
		}

		return resultado;
	}

	private static void listar() {

		if (listaRevistas.size() > 0) {

			Collections.sort(listaRevistas, new ComparadorRevistas());

			System.out.println("            CATALOGO DE REVISTAS ");
			System.out.println("-----------------------------------------------------");
			System.out.printf("%20s %12s %12s %12s", "Titulo |", "ISBN|", "Núm. Páginas|", "Formato");
			System.out.println(
					"\n--------------------------------------------------------------------------------------");
			System.out.println("");
			for (Revista revista : listaRevistas) {

				System.out.println(revista);
			}

		} else {

			System.out.println("No hay revistas en el catálogo.");
		}

	}

	/**
	 * 
	 * 
	 * Guardar en un fichero externo
	 * 
	 * 
	 */
	private static void guardarRevistas() {

		try {

			FileWriter almacen = new FileWriter("catalogo.txt");
			BufferedWriter buff = new BufferedWriter(almacen);

			for (Revista revista : listaRevistas) {
				buff.write(revista.toString() + "\n");
			}

			System.out.println("Catálogo guardado. ");
			buff.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 
	 * Menu
	 */
	private static void menuPrincipal() {

		System.out.println("\n--------------------------------------------------" + "\n        Introduce una opcion:"
				+ "\n\n            1 -> Ver la revista" + "\n            2 -> Introduce una nueva revista"
				+ "\n            3 -> Guardar la revista" + "\n\n            4 -> Salir"
				+ "\n\n--------------------------------------------------" + "\n\nIntroduce un número: ");

	}

	public static void main(String[] args) throws RevistaException {

		System.out.println("Catálogo de revistas");

		String opcion = "4";

		do {

			menuPrincipal();

			opcion = teclado.next();

			teclado = new Scanner(System.in);

			switch (opcion) {

			case MOSTRAR_REVISTA:
				listar();
				break;

			case INTRODUCIR_REVISTA:
				nuevaRevista();
				break;

			case GUARDAR_REVISTA:
				guardarRevistas();
				break;

			case SALIR:
				System.out.println("Aguur!!!");
				break;

			default:
				System.out.println("Introduce una opcion correcta (1 al 3 o 4 para salir)");
			}

		} while (!opcion.equals(SALIR));

		teclado.close();
	}

}