package Swing.Tablas;

import Entidades.Alumno;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class AlumnoTableModel extends AbstractTableModel {


    //INDICES DE MIS COLUMNAS

    private static final int COLUMNA_LEGAJO = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_APELLIDO = 2;
    private static final int COLUMNA_LIMITECURSOS = 3;


    private String[] nombresColumnas = {"Legajo", "Nombre", "Apellido", "Limite Cursos"};
    private Class[] tiposColumnas = {Integer.class, String.class, String.class, Integer.class};

    private List<Alumno> contenido;

    public AlumnoTableModel() {
        contenido = new ArrayList<Alumno>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public AlumnoTableModel(List<Alumno> contenidoInicial) {
        contenido = contenidoInicial;
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    /**
     * OTRO METODO QUE HAY QUE PISAR
     */
    public int getRowCount() {
        return contenido.size();
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public Object getValueAt(int rowIndex, int columnIndex) {

        Alumno a = contenido.get(rowIndex);

        Object result = null;
        switch (columnIndex) {
            case COLUMNA_LEGAJO:
                result = a.getLegajo();
                break;
            case COLUMNA_NOMBRE:
                result = a.getNombre();
                break;
            case COLUMNA_APELLIDO:
                result = a.getApellido();
                break;
            case COLUMNA_LIMITECURSOS:
                result = a.getLimiteCursos();
                break;

            default:
                result = new String("");
        }
        return result;
    }

    public void setValueAt(Object value, int row, int col){
        Alumno a = contenido.get(row);

        Object result = null;

        switch (col) {
            case COLUMNA_LEGAJO:
                a.setLegajo((Integer) value);
                break;
            case COLUMNA_NOMBRE:
                a.setNombre((String) value);
                break;
            case COLUMNA_APELLIDO:
                a.setApellido((String) value);
                break;
            case COLUMNA_LIMITECURSOS:
                a.setLimiteCursos((Integer) value);
                break;
        }
        fireTableCellUpdated(row,col);
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public String getColumnName(int col) {
        return nombresColumnas[col];
    }

    /**
     * METODO QUE HAY QUE PISAR
     */
    public Class getColumnClass(int col) {
        return tiposColumnas[col];
    }




    /**
     * GETTER DE MIS FILAS
     * @return
     */
    public List<Alumno> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Alumno> contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 1 || columnIndex == 2 || columnIndex == 3;
    }
}
