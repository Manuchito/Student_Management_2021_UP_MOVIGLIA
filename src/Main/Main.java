package Main;

public class Main {

    private PanelManager manager;

    public static void main(String[] args) {
        Main ppal = new Main();
        ppal.iniciarManager();
        ppal.mostrarFrame();
    }

    public void iniciarManager(){
        manager = new PanelManager();
        manager.armarManager();
        manager.mostrarPanelParcial();
    }

    public void mostrarFrame() {
        manager.showFrame();
    }

}
