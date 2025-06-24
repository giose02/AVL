package implementaciones;

import interfaces.ABBTDA;

public class ABB implements ABBTDA {

    class NodoABB {
        int info;
        int altura;
        ABB hijoIzq;
        ABB hijoDer;

        NodoABB(int x) {
            info = x;
            altura = 1;
            hijoIzq = new ABB();
            hijoDer = new ABB();
            hijoIzq.inicializarArbol();
            hijoDer.inicializarArbol();
        }
    }

    NodoABB raiz;

    public void inicializarArbol() {
        raiz = null;
    }

    public boolean arbolVacio() {
        return raiz == null;
    }

    public void agregarElemento(int x) {
        raiz = insertarAVL(raiz, x);
    }

    public void eliminarElemento(int x) {
        raiz = eliminarAVL(raiz, x);
    }

    public int raiz() {
        return raiz.info;
    }

    public ABBTDA hijoIzq() {
        return raiz.hijoIzq;
    }

    public ABBTDA hijoDer() {
        return raiz.hijoDer;
    }

    private NodoABB insertarAVL(NodoABB nodo, int x) {
        if (nodo == null) return new NodoABB(x);

        if (x < nodo.info)
            nodo.hijoIzq.agregarElemento(x);
        else if (x > nodo.info)
            nodo.hijoDer.agregarElemento(x);
        else
            return nodo;

        actualizarAltura(nodo);
        return balancear(nodo);
    }

    private NodoABB eliminarAVL(NodoABB nodo, int x) {
        if (nodo == null) return null;

        if (x < nodo.info)
            nodo.hijoIzq.eliminarElemento(x);
        else if (x > nodo.info)
            nodo.hijoDer.eliminarElemento(x);
        else {
            if (nodo.hijoIzq.arbolVacio() && nodo.hijoDer.arbolVacio()) {
                return null;
            } else if (nodo.hijoIzq.arbolVacio()) {
                return ((ABB) nodo.hijoDer).raiz;
            } else if (nodo.hijoDer.arbolVacio()) {
                return ((ABB) nodo.hijoIzq).raiz;
            } else {
                NodoABB sucesor = minimoNodo(((ABB) nodo.hijoDer).raiz);
                nodo.info = sucesor.info;
                nodo.hijoDer.eliminarElemento(sucesor.info);
            }
        }

        actualizarAltura(nodo);
        return balancear(nodo);
    }

    private NodoABB minimoNodo(NodoABB nodo) {
        while (!nodo.hijoIzq.arbolVacio()) {
            nodo = ((ABB) nodo.hijoIzq).raiz;
        }
        return nodo;
    }

    private void actualizarAltura(NodoABB nodo) {
        int altIzq = nodo.hijoIzq.arbolVacio() ? 0 : ((ABB) nodo.hijoIzq).raiz.altura;
        int altDer = nodo.hijoDer.arbolVacio() ? 0 : ((ABB) nodo.hijoDer).raiz.altura;
        nodo.altura = 1 + Math.max(altIzq, altDer);
    }

    private int balance(NodoABB nodo) {
        int altIzq = nodo.hijoIzq.arbolVacio() ? 0 : ((ABB) nodo.hijoIzq).raiz.altura;
        int altDer = nodo.hijoDer.arbolVacio() ? 0 : ((ABB) nodo.hijoDer).raiz.altura;
        return altIzq - altDer;
    }

    private NodoABB rotarDer(NodoABB a) {
        NodoABB b = ((ABB) a.hijoIzq).raiz;
        a.hijoIzq = b.hijoDer;
        b.hijoDer = armarDesdeNodo(a);
        actualizarAltura(a);
        actualizarAltura(b);
        return b;
    }

    private NodoABB rotarIzq(NodoABB a) {
        NodoABB b = ((ABB) a.hijoDer).raiz;
        a.hijoDer = b.hijoIzq;
        b.hijoIzq = armarDesdeNodo(a);
        actualizarAltura(a);
        actualizarAltura(b);
        return b;
    }

    private NodoABB balancear(NodoABB nodo) {
        int bal = balance(nodo);

        if (bal > 1) {
            if (balance(((ABB) nodo.hijoIzq).raiz) < 0)
                nodo.hijoIzq = armarDesdeNodo(rotarIzq(((ABB) nodo.hijoIzq).raiz));
            return rotarDer(nodo);
        }

        if (bal < -1) {
            if (balance(((ABB) nodo.hijoDer).raiz) > 0)
                nodo.hijoDer = armarDesdeNodo(rotarDer(((ABB) nodo.hijoDer).raiz));
            return rotarIzq(nodo);
        }

        return nodo;
    }

    private ABB armarDesdeNodo(NodoABB nodo) {
        ABB nuevo = new ABB();
        nuevo.raiz = nodo;
        return nuevo;
    }
}
