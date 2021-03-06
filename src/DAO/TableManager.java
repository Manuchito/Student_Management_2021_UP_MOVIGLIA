package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

	public void createAlumnoTable() {

		Connection c = DBManager.connect();
		
		String sql = "CREATE TABLE alumnos ( ID_ALUMNO integer primary key, nombres VARCHAR(256), apellidos VARCHAR(256), limite_cursos integer)";

		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}
	
	public void dropTable(String table) {

		Connection c = DBManager.connect();
		String sql = "DROP TABLE " + table;

		try {
			Statement s = c.createStatement();
			s.execute(sql);
			c.commit();
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

	}

	public void createCursosTable() {

		Connection c = DBManager.connect();

		String sql = "CREATE TABLE cursos(id_curso integer primary key, nombre VARCHAR(256) unique, precio INTEGER not null, cupo_maximo INTEGER not null, cantidad_parciales INTEGER not null)";



		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

	public void createCursadaTable() {

		Connection c = DBManager.connect();

		String sql = "CREATE TABLE cursada (ID_CURSADA int NOT NULL AUTO_INCREMENT, ID_ALUMNO integer not null, ID_CURSO integer not null, CONSTRAINT cursada PRIMARY KEY(ID_ALUMNO, ID_CURSO), FOREIGN KEY (ID_ALUMNO) REFERENCES ALUMNOS (ID_ALUMNO) ON DELETE CASCADE, FOREIGN KEY (ID_CURSO) REFERENCES CURSOS (ID_CURSO) ON DELETE CASCADE )";

		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void createParcialesTable(){
		Connection c = DBManager.connect();

		String sql = "CREATE TABLE notas (ID_ALUMNO integer not null, ID_CURSO integer not null, TIPO_NOTA VARCHAR(256) not null, NOTA integer not null, CONSTRAINT pk_parcial PRIMARY KEY(ID_ALUMNO, ID_CURSO, TIPO_NOTA), FOREIGN KEY (ID_ALUMNO) REFERENCES ALUMNOS (ID_ALUMNO) ON DELETE CASCADE, FOREIGN KEY (ID_CURSO) REFERENCES CURSOS (ID_CURSO) ON DELETE CASCADE )";

		try {
			Statement s = c.createStatement();
			s.execute(sql);
		} catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
