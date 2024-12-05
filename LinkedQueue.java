/*
 * Name: Manav B. Patel
 * Student Number: 3141849
 * Assignment-3
 * 
 */
public class LinkedQueue<E> implements Queue<E> {
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    // Defining SinglyLinkedList here.
    private static class SinglyLinkedList<E> {
        private Node<E> head = null;
        private Node<E> tail = null;
        private int size = 0;

        private static class Node<E> {
            private E element;
            private Node<E> next;

            public Node(E element, Node<E> next) {
                this.element = element;
                this.next = next;
            }

            public E getElement() {
                return element;
            }

            public Node<E> getNext() {
                return next;
            }

            public void setNext(Node<E> next) {
                this.next = next;
            }
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addLast(E element) {
            Node<E> newNode = new Node<>(element, null);
            if (isEmpty()) {
                head = newNode;
            } else {
                tail.setNext(newNode);
            }
            tail = newNode;
            size++;
        }

        public E first() {
            if (isEmpty()) {
                return null;
            }
            return head.getElement();
        }

        public E removeFirst() {
            if (isEmpty()) {
                return null;
            }
            E removedElement = head.getElement();
            head = head.getNext();
            size--;
            if (size == 0) {
                tail = null;
            }
            return removedElement;
        }
    }

    //Queue implementation.
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enqueue(E element) {
        list.addLast(element);
    }

    public E first() {
        return list.first();
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        return list.removeFirst();
    }
}

