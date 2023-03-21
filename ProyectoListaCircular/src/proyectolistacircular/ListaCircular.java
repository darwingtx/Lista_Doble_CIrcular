
package proyectolistacircular;

/**
 *
 * Darwin C
 * Andres Alvarez
 */
import javax.swing.JOptionPane;

public class ListaCircular {
    public static void main(String[] args)  {

        Lista l1 = new Lista();
        Lista l2 = new Lista();
        Lista l3 = new Lista();
        int list = 0;
        do {
            list = Integer.parseInt(JOptionPane.showInputDialog(null, "****SELECCIONE LA LISTA A UTILIZAR****\n"
                    + "1.Lista L1.\n"
                    + "2.Lista L2.\n"
                    + "3.Salir."));

            if (list == 1) {
                l1.MenuList(l2,l3,1);
            } else if (list == 2) {
                l2.MenuList(l1,l3,2);
            } else if (list == 3) {
                JOptionPane.showMessageDialog(null, "***QUE TENGAS UN HERMOSO DIA***");
            } 
            
        } while (list != 3);

    } 
}