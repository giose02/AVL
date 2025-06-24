package interfaces;

public interface ABBTDA {
    void inicializarArbol();
    void agregarElemento(int x);
    void eliminarElemento(int x);
    boolean arbolVacio();
    int raiz();
    ABBTDA hijoIzq();
    ABBTDA hijoDer();
}
