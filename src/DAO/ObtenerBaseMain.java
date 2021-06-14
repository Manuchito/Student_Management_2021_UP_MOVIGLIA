package DAO;

public class ObtenerBaseMain {

    public static void main(String[] args) {
        System.out.println("Url de conxión para usar en la consola H2 del browser u otro cliente SQL:");
        System.out.println(DBManager.obtenerUbicacionBase());
    }

}
