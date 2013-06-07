package edu.unitec.adt;

import java.io.Serializable;
import java.util.Objects;

/**
 * Implementación de ADTTree. Solamente puede tenes dos subárboles como hijos.
 * Se utiliza para generar códigos de Huffman. Implementa Serializable para
 * ser almacenado en archivos binarios.
 * @author EdilsonFernando
 */
public class HuffmanTree extends ADTTree implements Serializable {

    /**
     * Subárbol derecho.
     */
    private HuffmanTree rightChild;
    /**
     * Subárbol izquierdo.
     */
    private HuffmanTree leftChild;
    /**
     * Padre del árbol.
     */
    private HuffmanTree father;
    /**
     * Información que almacena el árbol.
     */
    private Comparable data;
    
    /**
     * Contructor por defecto. Crea un árbol con todos sus atributos en null.
     */
    public HuffmanTree() {
        rightChild = leftChild = father = null;
        data = null;
    }
    
    /**
     * Contructor que recibe como parámetro el objeto que guardará el árbol.
     * @param d Objeto comparable para mantener ordenado el árbol.
     */
    public HuffmanTree(Comparable d) {
        rightChild = leftChild = father = null;
        data = d;
    }
    
    /**
     * Método accesor para el objeto que guarda el árbol.
     * @return El objeto almacenado.
     */
    public Object getData() {
        return data;
    }
    
    /**
     * Método que agrega dos subárboles al arbol raíz, retornando un nuevo 
     * árbol con los arreglos.
     * @param r Árbol raíz.
     * @param trees Árboles que serán los hijos del árbol raíz. Esta
     * impletentación solo acepta dos parámetros como máximo
     * @return Retorna un nuevo árbol con los nodos añadidos.
     * @throws NumberChildrenException Es lanzada si sobrepasa la cantidad 
     * de hijos soportados.
     * @throws TreeImplementException  Es lanzada si los árboles que se dan
     * como parámetro no son de ésta implementación.
     */
    public static ADTTree create(ADTTree r, ADTTree... trees) throws NumberChildrenException, TreeImplementException {
        if (trees.length != 2) {
            throw new NumberChildrenException();
        }
        
        if (r instanceof HuffmanTree && trees[0] instanceof HuffmanTree && trees[1] instanceof HuffmanTree) {
            ((HuffmanTree) trees[0]).father = (HuffmanTree) r;
            ((HuffmanTree) trees[1]).father = (HuffmanTree) r;
            ((HuffmanTree) r).leftChild = (HuffmanTree) trees[0];
            ((HuffmanTree) r).rightChild = (HuffmanTree) trees[1];
        } else {
            throw new TreeImplementException("A parameter is not a HuffmanTree");
        }
        
        return r;
    }

    /**
     * Implementación del método father de ADTTree. Retorna el padre del árbol.
     * @return Una referencia al padre del árbol.
     * @see ADTTree#father()
     */
    @Override
    public ADTTree father() {
        return father;
    }

    /**
     * Implementación del método leftestSon de ADTTree. Retorna el hijo
     * izquierdo de un árbol.
     * @return Una referencia al hijo izquierdo de un árbol.
     * @see ADTTree#rightBrother() 
     */
    @Override
    public ADTTree leftestSon() {
        return leftChild;
    }

    /**
     * Implementación del método rightBrother de ADTTree. Retorna el hermano
     * hacia la derecha de ese árbol.
     * @return Una referencia al hermano derecho del árbol.
     * @see ADTTree#rightBrother() 
     */
    @Override
    public ADTTree rightBrother() {
        if (father == null) {
            return null;
        }
        
        if (father.leftChild.equals(this)) {
            return father.rightChild;
        } else {
            return null;
        }
    }

    /**
     * Implementación del método root de ADTTree. Retorna la raíz de todo
     * el árbol.
     * @return Una referencia a la raíz del árbol.
     * @see ADTTree#root() 
     */
    @Override
    public ADTTree root() {
        HuffmanTree tmp = father;
        
        while (tmp.father != null) {
            tmp = tmp.father;
        }
        
        return tmp;
    }

    /**
     * Implementación del método clear de ADTTree. Limpia todas las referencias
     * del árbol.
     * @see ADTTree#clear()
     */
    @Override
    public void clear() {
        father = rightChild = leftChild = null;
        data = null;
    }

    /**
     * Método accesor para el hijo derecho del árbol.
     * @return Una referencia al hijo derecho del árbol.
     */
    public HuffmanTree getRightChild() {
        return rightChild;
    }
    
    /**
     * Método accesor para el hijo izquierdo del árbol.
     * @return Una referencia al hijo izquierdo del árbol.
     */
    public HuffmanTree getLeftChild() {
        return leftChild;
    }
    
    /**
     * Implementación del método traverse de ADTTree. Recorre el árbol a 
     * partir del recorrido especificado y procesa cada nodo según las
     * intrucciónes del TreeProcessor.
     * @param j Tipo de recorrido del árbol
     * @see Journeys#INORDER
     * @see Journeys#POSTORDER
     * @see Journeys#PREORDER
     * @param p Proceso a realizar en cada nodo.
     * @see TreeProcessor
     */
    @Override
    public void traverse(Journeys j, TreeProcessor p) {
        if (j == Journeys.INORDER) {
            inorder(this, p);
        } else if (j == Journeys.PREORDER) {
            preorder(this, p);
        } else if (j == Journeys.POSTORDER) {
            postorder(this, p);
        }
    }
    
    /**
     * Método para recorrer el árbol en orden simétrico.
     * @param t Árbol a recorrer.
     * @param p Proceso a realizar en cada nodo.
     * @see TreeProcessor
     */
    public static void inorder(HuffmanTree t, TreeProcessor p) {
        if (t == null) {
            return;
        }
        
        inorder(t.leftChild, p);
        p.process(t);
        inorder(t.rightChild, p);
    }
    
    /**
     * Método para recorrer el árbol en orden previo.
     * @param t Árbol a recorrer.
     * @param p Proceso a realizar en cada nodo.
     * @see TreeProcessor
     */
    public static void preorder(HuffmanTree t, TreeProcessor p) {
        if (t == null) {
            return;
        }
        
        p.process(t);
        preorder(t.leftChild, p);
        preorder(t.rightChild, p);
    }
    
    /**
     * Método para recorrer el árbol en orden posterior.
     * @param t Árbol a recorrer.
     * @param p Proceso a realizar en cada nodo.
     * @see TreeProcessor
     */
    public static void postorder(HuffmanTree t, TreeProcessor p) {
        if (t == null) {
            return;
        }
        
        postorder(t.rightChild, p);
        postorder(t.rightChild, p);
        p.process(t);
    }
    
    /**
     * Compara dos árboles, si es mayor, menor o igual. Utiliza el método 
     * compareTO de @see HuffmanTree#data para funcionar. 
     * @param t Árbol a comprar.
     * @return Retorna 0 si ambos árboles son iguales, 1 si this es mayor que
     * t, y -1 si this es menor que t. 
     */
    public int compareTo(HuffmanTree t) {
        return data.compareTo(t.data);
    }

    /**
     * Comprar un HuffmanTree con otra implementación de árbol. Este método
     * no fue implementado para esta versión del programa.
     * @param t Árbol a comparar.
     * @return lanza una excepción porque no está completa la defición del método
     */
    @Override
    public int compareTo(ADTTree t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /*
     * Identifica de manera unívoca una instancia de HuffmanTree.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.rightChild);
        hash = 37 * hash + Objects.hashCode(this.leftChild);
        hash = 37 * hash + Objects.hashCode(this.father);
        hash = 37 * hash + Objects.hashCode(this.data);
        return hash;
    }

    /**
     * Verifica la igualdad entre dos objetos: el this y el objeto que entra
     * como parámetro.
     * @param obj Objeto a comprar.
     * @return Retorna true si ambos objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!this.data.equals(((HuffmanTree) obj).data)) {
            return false;
        }
        return true;
    }

    /**
     * Representación en String de un HuffmanTree, se ayuda del método toString
     * del objeto almacenado.
     * @return Una representación en String de la etiqueta del HuffmanTree.
     */
    @Override
    public String toString() {
        return data.toString();
    }
    
    
}
