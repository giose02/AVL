import interfaces.ABBTDA;
import implementaciones.ABB;
import metodos.RecorridosABB;

public class prin {
    public static void main(String[] args) {
        ABBTDA arbol = new ABB();
        arbol.inicializarArbol();

        // Insertamos elementos que generen rebalanceo
        int[] elementos = { 30, 20, 10, 25, 40, 35, 50, 5, 4};
        for (int x : elementos) {
            arbol.agregarElemento(x);
        }

        System.out.println("Después de insertar elementos:");
        System.out.print("Visualizacion del arbol: \n");
        RecorridosABB.imprimirArbol(arbol);
        System.out.print("PreOrden: ");
        RecorridosABB.preOrden(arbol);
        System.out.print("\nInOrden: ");
        RecorridosABB.inOrden(arbol);
        System.out.print("\nPostOrden: ");
        RecorridosABB.postOrden(arbol);

        // Eliminamos un nodo con dos hijos
        arbol.eliminarElemento(20);

        System.out.println("\n\nDespués de eliminar el nodo 20:");
        System.out.print("Visualizacion del arbol: \n");
        RecorridosABB.imprimirArbol(arbol);
        System.out.print("PreOrden: ");
        RecorridosABB.preOrden(arbol);
        System.out.print("\nInOrden: ");
        RecorridosABB.inOrden(arbol);
        System.out.print("\nPostOrden: ");
        RecorridosABB.postOrden(arbol);

        System.out.println("\n\nRaíz actual del árbol: " + arbol.raiz());
        System.out.println("\nAltura de la raíz actual del árbol: " +((ABB) arbol).raiz.altura);
    }
}
