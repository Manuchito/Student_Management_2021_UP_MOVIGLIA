package Swing.Tablas;

import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Entidades.Profesor;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class NotaTableModel extends AbstractTableModel {


    //INDICES DE MIS COLUMNAS

    private static final int COLUMNA_ALUMNO = 0;
    private static final int COLUMNA_CURSO = 1;
    private static final int COLUMNA_TIPONOTA = 2;
    private static final int COLUMNA_NOTAPARCIAL = 3;


    private String[] nombresColumnas = {"Legajo", "Curso", "Tipo Nota", "Nota"};
    private Class[] tiposColumnas = {Integer.class, String.class, String.class, Integer.class};

    private List<Nota> contenido;


    public NotaTableModel() {
        contenido = new ArrayList<Nota>();
    }

    /**
     * CONSTRUCTOR CON CONTENIDO INICIAL
     * @param contenidoInicial
     */
    public NotaTableModel(List<Nota> contenidoInicial) {
        contenido = contenidoInicial;
    }

    public int getColumnCount() {
        return nombresColumnas.length;
    }


    public int getRowCount() {
        return contenido.size();
    }


    public Object getValueAt(int rowIndex, int columnIndex) {

        Nota p = contenido.get(rowIndex);

        Object result = null;
        switch (columnIndex) {
            case COLUMNA_ALUMNO:
                result = p.getAlumno().getLegajo();
                break;
            case COLUMNA_CURSO:
                result = p.getCurso().getNombre();
                break;
            case COLUMNA_TIPONOTA:
                result = p.getTipoNota();
                break;
            case COLUMNA_NOTAPARCIAL:
                result = p.getNotaParcial();
                break;
            default:
                result = new String("");
        }
        return result;
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        Nota p = contenido.get(rowIndex);

        Object result = null;

        switch (columnIndex) {
            case COLUMNA_ALUMNO:
                p.setAlumno((Alumno) value);
                break;
            case COLUMNA_CURSO:
                p.setCurso((Curso) value);
                break;
            case COLUMNA_TIPONOTA:
                p.setTipoNota((String) value);
                break;
            case COLUMNA_NOTAPARCIAL:
                p.setNotaParcial((Integer) value);
                break;

        }
        fireTableCellUpdated(rowIndex,columnIndex);
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
    public List<Nota> getContenido() {
        return contenido;
    }
    /**
     * SETTER DE MIS FILAS
     *
     * @param contenido
     */
    public void setContenido(List<Nota> contenido) {
        this.contenido = contenido;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex){
        return columnIndex == 3;
    }

    public void reiniciarTabla(List<Nota> contenido){
        this.setContenido(new ArrayList<>());
        this.setContenido(contenido);
        this.fireTableDataChanged();


    }
}
