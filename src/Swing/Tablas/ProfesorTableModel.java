package Swing.Tablas;

import DAO.Profesor.ProfesorDAOH2Impl;
import Entidades.Alumno;
import Entidades.Profesor;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProfesorTableModel extends AbstractTableModel {


    //INDICES DE MIS COLUMNAS

    private static final int COLUMNA_NOMBRE = 0;
    private static final int COLUMNA_APELLIDO = 1;
    private static final int COLUMNA_USUARIO = 2;
    private static final int COLUMNA_PW = 3;


    private String[] nombresColumnas = {"Nombre", "Apellido", "Usuario", "Password"};
    private Class[] tiposColumnas = {String.class, String.class, String.class, String.class};

    private List<Profesor> contenido;

    public ProfesorTableModel() {
        contenido = new ArrayList<Profesor>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public ProfesorTableModel(List<Profesor> contenidoInicial) {
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

        Profesor p = contenido.get(rowIndex);

        Object result = null;
        switch (columnIndex) {
            case COLUMNA_NOMBRE:
                result = p.getNombre();
                break;
            case COLUMNA_APELLIDO:
                result = p.getApellido();
                break;
            case COLUMNA_USUARIO:
                result = p.getUsuario();
                break;
            case COLUMNA_PW:
                result = p.getPw();
                break;

            default:
                result = new String("");
        }
        return result;
    }

    public void setValueAt(Object value, int row, int col){
        Profesor p = contenido.get(row);

        Object result = null;

        switch (col) {
            case COLUMNA_NOMBRE:
                p.setNombre((String) value);
                break;
            case COLUMNA_APELLIDO:
                p.setApellido((String) value);
                break;
            case COLUMNA_USUARIO:
                p.setUsuario((String) value);
                break;
            case COLUMNA_PW:
                p.setPw((String) value);
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
    public List<Profesor> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Profesor> contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 0|| columnIndex ==  1 || columnIndex == 3;
    }

    public void reiniciarTabla(List<Profesor> contenido){
        this.setContenido(new ArrayList<>());
        this.setContenido(contenido);
        this.fireTableDataChanged();


    }
}
