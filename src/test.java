import Exceptions.IntegerVaciaException;
import Exceptions.ClaveDuplicadaException;
import Exceptions.ClaveNoExisteException;
import Main.DAO.TableManager;

import java.sql.SQLException;

public class test {

	public static void main(String [] args) throws SQLException, ClaveDuplicadaException, ClaveNoExisteException, IntegerVaciaException {

		TableManager t = new TableManager();
		t.dropTable("USUARIOS");

	}

}
