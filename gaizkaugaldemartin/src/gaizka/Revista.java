package gaizka;

public class Revista implements Ileible {

	private String titulo; // minimo 3, maximo 150
	private String isbn; // alfanumerico 10 justos
	private String numeroPaginas; // minimo 1
	private boolean formato; // true digital, false papel

	public Revista() {
		this.titulo = "Por defecto";
		this.isbn = "1234567890";
		this.numeroPaginas = "10";
		this.formato = true;
	}

	public Revista(String titulo, String isbn, String numeroPaginas, boolean formato) throws Exception {
		super();
		setTitulo(titulo);
		setIsbn(isbn);
		setNumeroPaginas(numeroPaginas);
		setFormato(formato);
	}

	public String getTitulo() {
		return titulo;
	}

	/**
	 * 
	 * 
	 * 
	 * Establece el titulo
	 * 
	 * @param titulo String (debe tener entre 3 y 150 caracteres)
	 * @throws Exception Si no tiene esas reglas o es null
	 * 
	 * 
	 * 
	 * 
	 */
	public void setTitulo(String titulo) throws Exception {

		if (titulo != null) {

			if (titulo.length() >= 3 && titulo.length() <= 150) {
				this.titulo = titulo;

			} else {
				throw new RevistaException();
			}

		} else {
			throw new Exception("Ha introducido un titulo nulo");
		}
	}

	public String getIsbn() {
		return isbn;
	}

	/**
	 * 
	 * 
	 * Establece el ISBN
	 * 
	 * @param Isbn String (tener 10 caracteres alfanumericos)
	 * @throws Exception Si no tiene esas reglas o es null
	 * 
	 * 
	 */
	public void setIsbn(String isbn) throws Exception {

		if (titulo != null) {

			if (isbn.length() == 10) {

				this.isbn = isbn;

			} else {

				throw new RevistaException();
			}

		} else {
			throw new Exception("Ha introducido un isbn nulo");
		}

	}

	public String getNumeroPaginas() {
		return numeroPaginas;
	}

	/**
	 * Establece el número de páginas.
	 * 
	 * @param numPaginas String (Será un digito, siempre igual o mayor a 1)
	 * @throws Exception Si es una letra, un numero negativo, 0 o null
	 */
	public void setNumeroPaginas(String numeroPaginas) throws Exception {

		if (titulo != null) {

			if (Integer.parseInt(numeroPaginas) >= 1) {

				this.numeroPaginas = numeroPaginas;

			} else {
				throw new RevistaException();
			}
		} else {
			throw new Exception("Ha introducido un numero de paginas nulo");
		}
	}

	/**
	 * Devuelve true si es digital o false si es papel
	 * 
	 * 
	 * @return boolean true digital ; false papel
	 * @see toString()
	 */
	public boolean isFormato() {
		return formato;
	}

	/**
	 * 
	 * @param formato true "digital", false "papel"
	 */
	public void setFormato(boolean formato) {
		this.formato = formato;
	}

	@Override
	public String toString() {

		String formatoRevista = "";
		String resultado = "";

		if (formato) {
			formatoRevista = "Digital";
		} else {
			formatoRevista = "Papel";
		}

		resultado = String.format("%20s | %12s | %12s | %12s", titulo, isbn, numeroPaginas, formatoRevista);
		return resultado;
	}

}
