import Entidades.Alumno;
import Service.Exceptions.AlumnoNoExiste;
import Service.Exceptions.ClaveDuplicadaException;
import Service.Exceptions.ClaveNoExisteException;
import Service.TableManager;

import java.sql.SQLException;

public class test {

	public static void main(String [] args) throws SQLException, ClaveDuplicadaException, ClaveNoExisteException, AlumnoNoExiste {

		TableManager t = new TableManager();
		t.dropTable("USUARIOS");

	}

}
