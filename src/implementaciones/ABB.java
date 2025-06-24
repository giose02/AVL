package implementaciones;

import interfaces.ABBTDA;
public class ABB implements ABBTDA {
    private class NodoAVL {
        int valor;
        int altura;
        NodoAVL izq;
        NodoAVL der;

        NodoAVL(int valor) {
            this.valor = valor;
            this.altura = 1;
        }
    }

    private NodoAVL raiz;

    @Override
    public void inicializarArbol() {
        raiz = null;
    }

    @Override
    public void agregarElemento(int x) {
        raiz = insertar(raiz, x);
    }

    @Override
    public void eliminarElemento(int x) {
        raiz = eliminar(raiz, x);
    }

    @Override
    public boolean arbolVacio() {
        return raiz == null;
    }

    @Override
    public int raiz() {
        return raiz.valor;
    }

    @Override
    public ABBTDA hijoIzq() {
        ABB hi = new ABB();
        hi.raiz = this.raiz.izq;
        return hi;
    }

    @Override
    public ABBTDA hijoDer() {
        ABB hd = new ABB();
        hd.raiz = this.raiz.der;
        return hd;
    }

    private int altura(NodoAVL nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    private int mayor(int a, int b) {
        if (a > b) return a;
        else return b;
    }

    private int factorBalanceo(NodoAVL nodo) {
        if (nodo == null) return 0;
        return altura(nodo.izq) - altura(nodo.der);
    }

    private NodoAVL rotarDerecha(NodoAVL y) {
        NodoAVL x = y.izq;
        NodoAVL T2 = x.der;

        x.der = y;
        y.izq = T2;

        y.altura = mayor(altura(y.izq), altura(y.der)) + 1;
        x.altura = mayor(altura(x.izq), altura(x.der)) + 1;

        return x;
    }

    private NodoAVL rotarIzquierda(NodoAVL x) {
        NodoAVL y = x.der;
        NodoAVL T2 = y.izq;

        y.izq = x;
        x.der = T2;

        x.altura = mayor(altura(x.izq), altura(x.der)) + 1;
        y.altura = mayor(altura(y.izq), altura(y.der)) + 1;

        return y;
    }

    private NodoAVL insertar(NodoAVL nodo, int valor) {
        if (nodo == null) return new NodoAVL(valor);
        if (valor < nodo.valor) nodo.izq = insertar(nodo.izq, valor);
        else if (valor > nodo.valor) nodo.der = insertar(nodo.der, valor);
        else return nodo;

        nodo.altura = 1 + mayor(altura(nodo.izq), altura(nodo.der));

        int balance = factorBalanceo(nodo);

        if (balance > 1 && valor < nodo.izq.valor) return rotarDerecha(nodo);
        if (balance < -1 && valor > nodo.der.valor) return rotarIzquierda(nodo);
        if (balance > 1 && valor > nodo.izq.valor) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && valor < nodo.der.valor) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }

        return nodo;
    }

    private NodoAVL eliminar(NodoAVL nodo, int valor) {
        if (nodo == null) return nodo;

        if (valor < nodo.valor) nodo.izq = eliminar(nodo.izq, valor);
        else if (valor > nodo.valor) nodo.der = eliminar(nodo.der, valor);
        else {
            if ((nodo.izq == null) || (nodo.der == null)) {
                NodoAVL temp = (nodo.izq != null) ? nodo.izq : nodo.der;
                if (temp == null) {
                    temp = nodo;
                    nodo = null;
                } else nodo = temp;
            } else {
                NodoAVL temp = minValorNodo(nodo.der);
                nodo.valor = temp.valor;
                nodo.der = eliminar(nodo.der, temp.valor);
            }
        }

        if (nodo == null) return nodo;

        nodo.altura = 1 + mayor(altura(nodo.izq), altura(nodo.der));

        int balance = factorBalanceo(nodo);

        if (balance > 1 && factorBalanceo(nodo.izq) >= 0) return rotarDerecha(nodo);
        if (balance > 1 && factorBalanceo(nodo.izq) < 0) {
            nodo.izq = rotarIzquierda(nodo.izq);
            return rotarDerecha(nodo);
        }
        if (balance < -1 && factorBalanceo(nodo.der) <= 0) return rotarIzquierda(nodo);
        if (balance < -1 && factorBalanceo(nodo.der) > 0) {
            nodo.der = rotarDerecha(nodo.der);
            return rotarIzquierda(nodo);
        }
        return nodo;
    }

    private NodoAVL minValorNodo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.izq != null) actual = actual.izq;
        return actual;
    }

    public void preOrder() {
        System.out.print("PreOrder: ");
        preOrderRec(raiz);
        System.out.println();
    }

    private void preOrderRec(NodoAVL nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preOrderRec(nodo.izq);
            preOrderRec(nodo.der);
        }
    }

    public void inOrder() {
        System.out.print("InOrder: ");
        inOrderRec(raiz);
        System.out.println();
    }

    private void inOrderRec(NodoAVL nodo) {
        if (nodo != null) {
            inOrderRec(nodo.izq);
            System.out.print(nodo.valor + " ");
            inOrderRec(nodo.der);
        }
    }

    public void postOrder() {
        System.out.print("PostOrder: ");
        postOrderRec(raiz);
        System.out.println();
    }

    private void postOrderRec(NodoAVL nodo) {
        if (nodo != null) {
            postOrderRec(nodo.izq);
            postOrderRec(nodo.der);
            System.out.print(nodo.valor + " ");
        }
    }

}

