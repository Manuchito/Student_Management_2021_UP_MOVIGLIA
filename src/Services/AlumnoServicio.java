package Services;

import Entidades.Alumno;
import Main.DAO.AlumnoDAOH2Impl;
import Exceptions.AlumnoNoExiste;
import Exceptions.ClaveDuplicadaException;
import Exceptions.IntegerVaciaException;

public class AlumnoServicio {

    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();

    private void validad(int legajo, String nombre, String apellido){
        if(nombre.matches(".*\\d.*") || apellido.matches(".*\\d.*") || nombre.matches("[^A-Za-z0-9]") || apellido.matches("[^A-Za-z0-9]")){
            throw new NumberFormatException("Nombre y/o Apellido no pueden tener numeros o caracteres especiales en su contenido");
        }
        else if(nombre.isEmpty() || apellido.isEmpty() || Integer.valueOf(legajo) == null){
            throw new NumberFormatException("Nombre, id y/o apellido no puede estar vacio");
        }
    }

    public void registrar(int legajo, String nombre, String apellido) throws ClaveDuplicadaException {

        validad(legajo,nombre,apellido);

        Alumno alumno = new Alumno(legajo, nombre, apellido);

        alumnoDAO.crearAlumno(alumno);
    }

    public void eliminar(int legajo) throws IntegerVaciaException, AlumnoNoExiste {
        if(Integer.valueOf(legajo) == null){
            throw new IntegerVaciaException("El legajo esta vacio o es nulo");
        }

        alumnoDAO.borraAlumno(legajo);
    }

    public void editar(int legajo, String nombre, String apellido) throws AlumnoNoExiste {

        validad(legajo,nombre,apellido);

        Alumno alumno = new Alumno(legajo, nombre, apellido);

        alumnoDAO.actualizaAlumno(alumno);


    }

    public Alumno mostrar(int legajo)throws IntegerVaciaException{
        if(Integer.valueOf(legajo) == null){
            throw new IntegerVaciaException("El legajo esta vacio o es nulo");
        }

        return alumnoDAO.muestraAlumno(legajo);

    }





}
