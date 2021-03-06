package Swing.Tablas;

import Entidades.Alumno;
import Entidades.Curso;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CursoTableModel extends AbstractTableModel {


    //INDICES DE MIS COLUMNAS

    private static final int COLUMNA_IDCURSO = 0;
    private static final int COLUMNA_NOMBRE = 1;
    private static final int COLUMNA_PRECIO = 2;
    private static final int COLUMNA_CUPO = 3;
    private static final int COLUMNA_CANTIDADPARCIALES = 4;


    private String[] nombresColumnas = {"Id Curso", "Nombre", "Precio", "Cupo", "Cantidad Parciales"};
    private Class[] tiposColumnas = {Integer.class, String.class, Integer.class, Integer.class, Integer.class};

    private List<Curso> contenido;


    public CursoTableModel() {
        contenido = new ArrayList<Curso>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public CursoTableModel(List<Curso> contenidoInicial) {
        contenido = contenidoInicial;
    }

    public int getColumnCount() {
        return nombresColumnas.length;
    }


    public int getRowCount() {
        return contenido.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        Curso c = contenido.get(rowIndex);

        Object result = null;
        switch (columnIndex) {
            case COLUMNA_IDCURSO:
                result = c.getId();
                break;
            case COLUMNA_NOMBRE:
                result = c.getNombre();
                break;
            case COLUMNA_PRECIO:
                result = c.getPrecio();
                break;
            case COLUMNA_CUPO:
                result = c.getCupo();
                break;
            case COLUMNA_CANTIDADPARCIALES:
                result = c.getCantidad_parciales();
                break;
            default:
                result = new String("");
        }
        return result;
    }

    public void setValueAt(Object value, int row, int col){
        Curso c = contenido.get(row);

        Object result = null;

        switch (col) {
            case COLUMNA_IDCURSO:
                c.setId((Integer) value);
                break;
            case COLUMNA_NOMBRE:
                c.setNombre((String) value);
                break;
            case COLUMNA_PRECIO:
                c.setPrecio((Integer) value);
                break;
            case COLUMNA_CUPO:
                c.setCupo((Integer) value);
                break;
            case COLUMNA_CANTIDADPARCIALES:
                c.setCantidad_parciales((Integer) value);
                break;
        }
        fireTableCellUpdated(row,col);
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
    public List<Curso> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Curso> contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 4;
    }

    public void reiniciarTabla(List<Curso> contenido){
        this.setContenido(new ArrayList<>());
        this.setContenido(contenido);
        this.fireTableDataChanged();

    }
}
