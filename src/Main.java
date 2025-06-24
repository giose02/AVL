import implementaciones.ABB;
import interfaces.ABBTDA;

public class Main {
     public static void main(String[] args) {
        ABBTDA arbol = new ABB();
        arbol.inicializarArbol();

        int[] elementos = {30, 20, 10, 25, 40, 35, 50, 5, 4};
        for (int x : elementos) {
            arbol.agregarElemento(x);
        }

        System.out.println("Después de insertar elementos:");
        ((ABB) arbol).preOrder();   // Raíz primero
        ((ABB) arbol).inOrder();    // ordenado
        ((ABB) arbol).postOrder();  // Raíz al final

        arbol.eliminarElemento(20); //Tiene dos hijos

        System.out.println("\nDespués de eliminar el nodo 20:");
        ((ABB) arbol).preOrder();
        ((ABB) arbol).inOrder();
        ((ABB) arbol).postOrder();

        // Mostrar raíz actual
        System.out.println("\nRaíz actual del árbol: " + arbol.raiz());
    }
}
