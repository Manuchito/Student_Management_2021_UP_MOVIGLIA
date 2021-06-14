import Exceptions.IntegerVaciaException;
import Exceptions.ClaveDuplicadaException;
import Exceptions.ClaveNoExisteException;
import DAO.TableManager;

import java.sql.SQLException;

public class test {

	public static void main(String [] args) throws SQLException, ClaveDuplicadaException, ClaveNoExisteException, IntegerVaciaException {

		TableManager t = new TableManager();
		t.dropTable("ALUMNOS");
		t.createAlumnoTable();

	}

}
