package SQL_DBManager;
//Exceptions own http://www.sc.ehu.es/sbweb/fisica/cursoJava/fundamentos/excepciones/propias.htm
public class SQL_DBExceptions extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SQL_DBExceptions() {
		// TODO Auto-generated constructor stub
	}
	public String ExceptionNoMatch(){
		return "no match";
	}

}
