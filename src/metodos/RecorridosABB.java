package metodos;

import interfaces.ABBTDA;

public class RecorridosABB {

    public static void preOrden(ABBTDA arbol) {
        if (!arbol.arbolVacio()) {
            System.out.print(arbol.raiz() + " ");
            preOrden(arbol.hijoIzq());
            preOrden(arbol.hijoDer());
        }
    }

    public static void inOrden(ABBTDA arbol) {
        if (!arbol.arbolVacio()) {
            inOrden(arbol.hijoIzq());
            System.out.print(arbol.raiz() + " ");
            inOrden(arbol.hijoDer());
        }
    }

    public static void postOrden(ABBTDA arbol) {
        if (!arbol.arbolVacio()) {
            postOrden(arbol.hijoIzq());
            postOrden(arbol.hijoDer());
            System.out.print(arbol.raiz() + " ");
        }
    }
    public static void imprimirArbol(ABBTDA arbol) {
        imprimirRecursivo(arbol, 0);
    }

    private static void imprimirRecursivo(ABBTDA arbol, int nivel) {
        if (!arbol.arbolVacio()) {
            imprimirRecursivo(arbol.hijoDer(), nivel + 1);
            System.out.println("  ".repeat(nivel) + arbol.raiz());
            imprimirRecursivo(arbol.hijoIzq(), nivel + 1);
        }
    }
}
