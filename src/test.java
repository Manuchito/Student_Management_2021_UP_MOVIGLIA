import Entidades.Alumno;
import Service.Exceptions.AlumnoNoExiste;
import Service.Exceptions.ClaveDuplicadaException;
import Service.Exceptions.ClaveNoExisteException;

import java.sql.SQLException;

public class test {

	public static void main(String [] args) throws SQLException, ClaveDuplicadaException, ClaveNoExisteException, AlumnoNoExiste {

		int a = 3;
		int b = a++;

		System.out.println(b);
		System.out.println(a);

	}

}
