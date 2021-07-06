package Swing.Tablas;

import Entidades.Cursada;
import Entidades.Curso;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CursadaTableModel extends AbstractTableModel {
    //INDICES DE MIS COLUMNAS

    private static final int COLUMNA_IDCURSADA = 0;
    private static final int COLUMNA_IDALUMNO = 1;
    private static final int COLUMNA_IDCURSO = 2;



    private String[] nombresColumnas = {"Id Cursada", "Id Alumno", "Id Curso"};
    private Class[] tiposColumnas = {Integer.class, Integer.class, Integer.class};

    private List<Cursada> contenido;


    public CursadaTableModel() {
        contenido = new ArrayList<Cursada>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public CursadaTableModel(List<Cursada> contenidoInicial) {
        contenido = contenidoInicial;
    }

    public int getColumnCount() {
        return nombresColumnas.length;
    }


    public int getRowCount() {
        return contenido.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        Cursada c = contenido.get(rowIndex);
        Object result = null;

        switch (columnIndex) {
            case COLUMNA_IDCURSADA:
                result = c.getCursada();
                break;
            case COLUMNA_IDALUMNO:
                result = c.getAlumno().getLegajo();
                break;
            case COLUMNA_IDCURSO:
                result = c.getCurso().getId();
                break;
            default:
                result = new String("");
        }
        return result;
    }


    public String getColumnName(int col) {
        return nombresColumnas[col];
    }


    public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }

    /**
     * GETTER DE MIS FILAS
     * @return
     */
    public List<Cursada> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Cursada> contenido) {
        this.contenido = contenido;
    }

}
