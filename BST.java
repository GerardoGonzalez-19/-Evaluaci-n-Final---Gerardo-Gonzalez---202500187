public class BST {

    static class Node {
        int value;
        Node left, right;

        Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    Node root;

    // INSERCIÓN
    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node node, int value) {
        if (node == null) return new Node(value);
        if (value < node.value) node.left = insertRec(node.left, value);
        else if (value > node.value) node.right = insertRec(node.right, value);
        return node;
    }

    // BÚSQUEDA
    public boolean search(int value) {
        return searchRec(root, value);
    }

    private boolean searchRec(Node node, int value) {
        if (node == null) return false;
        if (value == node.value) return true;
        if (value < node.value) return searchRec(node.left, value);
        return searchRec(node.right, value);
    }

    // ELIMINACIÓN
    public void delete(int value) {
        root = deleteRec(root, value);
    }

    private Node deleteRec(Node node, int value) {
        if (node == null) return null;

        if (value < node.value) {
            node.left = deleteRec(node.left, value);
        } else if (value > node.value) {
            node.right = deleteRec(node.right, value);
        } else {
            // Caso 1: Sin hijos
            if (node.left == null && node.right == null) return null;
            // Caso 2: Un solo hijo
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Caso 3: Dos hijos — reemplazar con el mínimo del subárbol derecho
            Node minNode = findMin(node.right);
            node.value = minNode.value;
            node.right = deleteRec(node.right, minNode.value);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // RECORRIDO IN-ORDER (izquierda - raíz - derecha)
    public void inOrder() {
        System.out.print("In-Order: ");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.print(node.value + " ");
            inOrderRec(node.right);
        }
    }

    // RECORRIDO PRE-ORDER (raíz - izquierda - derecha)
    public void preOrder() {
        System.out.print("Pre-Order: ");
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderRec(node.left);
            preOrderRec(node.right);
        }
    }

    // RECORRIDO POST-ORDER (izquierda - derecha - raíz)
    public void postOrder() {
        System.out.print("Post-Order: ");
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node node) {
        if (node != null) {
            postOrderRec(node.left);
            postOrderRec(node.right);
            System.out.print(node.value + " ");
        }
    }

    // MAIN — Demo con 5 valores
    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);

        tree.inOrder();
        tree.preOrder();
        tree.postOrder();

        System.out.println("Buscar 30: " + tree.search(30));
        System.out.println("Buscar 99: " + tree.search(99));

        tree.delete(30);
        System.out.println("Después de eliminar 30:");
        tree.inOrder();
    }
}