package Services;

import DAO.Profesor.ProfesorDAOH2Impl;
import Entidades.Alumno;
import Entidades.Profesor;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.ServiceClaveDuplicadaException;
import Exceptions.ServiceLegajoNoExsiteException;

import java.util.List;

public class ProfesorServicio {

    ProfesorDAOH2Impl profesorDAOH2 = new ProfesorDAOH2Impl();

    public void registrar(String nombre, String apellido, String usuario, String pw) throws ServiceClaveDuplicadaException {
        try{
            profesorDAOH2.crearProfesor(new Profesor(nombre, apellido, usuario, pw));
        } catch (DAOClaveDuplicadaException daoClaveDuplicadaException) {
            throw new ServiceClaveDuplicadaException(daoClaveDuplicadaException.getMessage());
        }
    }

    public void eliminar(String user){
        profesorDAOH2.borraAlumno(user);
    }

    public void editar(String nombre, String apellido, String usuario, String pw) throws ServiceLegajoNoExsiteException {
        try{
            profesorDAOH2.editarProfesor(new Profesor(nombre, apellido, usuario, pw));
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException(legajoNoExisteException.getMessage());
        }
    }

    public static void print(){
        System.out.println("comemla");
    }

    public List<Profesor> listar(){
        return profesorDAOH2.listaTodosLosProfesores();
    }
}
