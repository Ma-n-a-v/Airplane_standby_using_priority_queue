/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class LinkedBinaryTree<E> implements BinaryTree<E>{
    protected static class Node<E> implements Position<E>{
        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        public Node(E element, Node<E> parent, Node<E> left, Node<E> right){
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        //Getters
        public E getElement(){
            return element;
        }

        public Node<E> getParent(){
            return parent;
        }

        public Node<E> getLeft(){
            return left;
        }

        public Node<E> getRight(){
            return right;
        }

        //Setters
        public void setElement(E element){
            this.element = element;
        }

        public void setParent(Node<E> parent){
            this.parent = parent;
        }

        public void setLeft(Node<E> left){
            this.left = left;
        }

        public void setRight(Node<E> right){
            this.right = right;
        }
    }

    //Factory function to create a new node.
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right){ 
        return new Node<E>(e, parent, left, right);
    }


    private class PositionTree implements Position<E>{
        private Node<E> node;
        public PositionTree(Node<E> node){
            this.node = node;
        }
        public E getElement(){
            return node.getElement();
        }
    }

   //LinkedBinaryTree Instances
    protected Node<E> root;
    private int size;

    public LinkedBinaryTree(){
        root = null;
        size = 0;
    }

    //private utility methods
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree"); 
        return node;
    }

    public int size(){
        return size;
    }

    //Public returning methods
    public Position<E> sibling(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        Node<E> parent = node.getParent();
        if(parent==null){
            return null;
        }
        if(node==parent.getLeft()){
            return new PositionTree(parent.getRight());
        }
        else{
            return new PositionTree(parent.getLeft());
        }
    }

    public int numChildren(Position<E> p) {
        Node<E> node = validate(p);
        int count=0;
        if (node.getLeft() != null){
            count++;
        }
        if (node.getRight() != null){
            count++; 
        }
        return count;
    }

    public Position<E> left(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        Node<E> leftChild = node.getLeft();
        if (leftChild != null) {
            return new PositionTree(leftChild);
        }else{
            return null;
        }
    }

    public Position<E> right(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        Node<E> rightChild = node.getRight();
        if(rightChild != null){
            return new PositionTree(rightChild);
        }else{
            return null;
        }
    }

    //Tree implementation
    public Position<E> root(){
        if(root==null){
            return null;
        }
        else{
            return new PositionTree(root);
        }
    }

    public Position<E> parent(Position<E> p) throws IllegalArgumentException{
        Node<E> node = validate(p);
        return new PositionTree(node.getParent());
    }

    public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        List<Position<E>> children = new ArrayList<>(2);
        if (node.getLeft() != null) {
            children.add(node.getLeft());
        }
        if (node.getRight() != null) {
            children.add(node.getRight());
        }
        return children;
    }

    public Position<E> addRoot(E element) {
        if (!isEmpty()) {
            throw new IllegalArgumentException("The tree is not empty. Cannot add roots to it!");
        }
        root = createNode(element, null, null, null);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> p, E element) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) {
            throw new IllegalArgumentException("Left child already exists for this node.");
        }
        Node<E> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> p, E element) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) {
            throw new IllegalArgumentException("Right child already exists for this node.");
        }
        Node<E> child = createNode(element, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public boolean isInternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) > 0;
    }
    
    public boolean isExternal(Position<E> p) throws IllegalArgumentException {
        return numChildren(p) == 0;
    }

    public boolean isRoot(Position<E> p) throws IllegalArgumentException {
        return p == root();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    //Tree traversal implementation.

    //Postorder Subtree
    private void postorderSubtree(Position<E> position, List<Position<E>> snapshot){
        for(Position<E> c : children(position)){
            postorderSubtree(c, snapshot);
        }
        snapshot.add(position);
    }

    public Iterable<Position<E>> postorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            postorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    //preorder subtree
    private void preorderSubtree(Position<E> position, List<Position<E>> snapshot){
        snapshot.add(position);
        for(Position<E> c : children(position)){
            postorderSubtree(c, snapshot);
        }
    }

    public Iterable<Position<E>> preorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            preorderSubtree(root(), snapshot);
        }
        return snapshot;
    }

    //Inorder subtree
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot){
        if(left(p)!=null){
            inorderSubtree(left(p), snapshot);
        }
        snapshot.add(p);
        if(right(p)!=null){
            inorderSubtree(right(p), snapshot);
        }
    }
    public Iterable<Position<E>> inorder(){
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            inorderSubtree(root(), snapshot);
        }
        return snapshot;
    }


    private class PositionIterator implements Iterator<Position<E>>{
        private Iterator<Position<E>> iterator;

        public PositionIterator(Iterator<Position<E>> iterator) {
            this.iterator = iterator;
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public Position<E> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree");
            }
            return iterator.next();
    }
}
    private class PositionIterable implements Iterable<Position<E>>{
        public Iterator<Position<E>> iterator(){
            return new PositionIterator(postorder().iterator());
        }
    }

    //Required implementation of the method, since the LinkedBinaryTree is implementing BinaryTree which has an abstract method Iterable<Position<E>> positions();
    public Iterable<Position<E>> positions(){
        return new PositionIterable();
    }

    
    //Inner class for Element Iterator
    private class ElementIterator implements Iterator<E>{
        Iterator<Position<E>> posIterator = positions().iterator();
    
    public boolean hasNext(){
        return posIterator.hasNext();
    }

    public E next(){
        return posIterator.next().getElement();
    }

    public void remove(){
        posIterator.remove();
    }

}
    //Required implementation of the method, since the LinkedBinaryTree is implementing BinaryTree which has an abstract method Iterable<E> iterator();
    public Iterator<E> iterator(){
        return new ElementIterator();
    }

    //Required toString method as asked
    public String toString(){
        if(root == null){
            return "Tree is Empty";
        }

        StringBuilder sb = new StringBuilder();
        printPreorderIndent(root, sb, 0);
        return sb.toString();
    }

    private void printPreorderIndent(Node<E> node, StringBuilder sb, int depth){
        if(node==null){
            return;
        }
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        sb.append("-").append(node.getElement()).append("\n");
    
        printPreorderIndent(node.getLeft(), sb, depth + 1);
        printPreorderIndent(node.getRight(), sb, depth + 1);
    }

    /*
     * From here I am implementing inorder representation and postorder representation 
     */

    // Inorder representation.
    public void inorderRepresentation(Position<E> p) {
        System.out.println("[");
        if (p != null) {
            Node<E> node = validate(p);
            if (node.getLeft() != null) {
                inorderRepresentation(new PositionTree(node.getLeft()));
            }
            System.out.print(node.getElement() + ", ");
            if (node.getRight() != null) {
                inorderRepresentation(new PositionTree(node.getRight()));
            }
        }
        System.out.println("]");
    }

    //Postorder representation.
    public void postorderRepresentation(Position<E> position) {
        System.out.println("[");
        if (position != null) {
            Node<E> node = validate(position);
            if (node.getLeft() != null) {
                postorderRepresentation(new PositionTree(node.getLeft()));
            }
            if (node.getRight() != null) {
                postorderRepresentation(new PositionTree(node.getRight()));
            }
            System.out.print(node.getElement() + " ");
        }
        System.out.println("]");
    }
}
