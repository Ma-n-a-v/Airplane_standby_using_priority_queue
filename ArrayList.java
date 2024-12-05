/**
 * ACS-2947 Assignment 2 Part A
 * Name: Manav B. Patel
 * Student Number: 3141849
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 4;
    private E[] data;
    private int size = 0;
    
    //Constructors
    public ArrayList(){
        this(CAPACITY);
    }
    
    public ArrayList(int capacity){
        data = (E[])new Object[capacity];
    }

    //public methods
    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public E get(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        return data[i];
    }

    public E set(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    public void add(int i, E e) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        if(i == data.length){
            resize(2*data.length);
        }
        for(int k = size;k>i;k--){
            data[k] = data[k-1];
        }
        data[i] = e;
        size++;
    }
    //overloaded add method
    public void add(E e){
        add(size,e);
    }

    public E remove(int i) throws IndexOutOfBoundsException{
        checkIndex(i, size);
        E temp = data[i];
        for(int k=i;k<size-1;k++){
            data[k] = data[k+1];
        }
        data[size-1] = null;            //garbage collector
        size--;
        if(size<(data.length/2)){
            resize((data.length/2));
        }
        return temp;
    }

    //Overridden equals method
    public boolean equals(Object o){
        if(o==null){
            return false;
        }
        if(getClass()!=o.getClass()){
            return false;
        }
        ArrayList other = (ArrayList) o;
        if(size != other.size){
            return false;
        }
        for(int i=0;i<size;i++){
            if(!(data[i].equals(other.data[i]))){
                return false;
            }
        }
        return true;
    }
    //---------------- nested ArrayIterator class ----------------
    /*
    ∗ A (nonstatic) inner class. Note well that each instance contains an implicit 
    * reference to the containing list, allowing it to access the list's members. 
    */
    private class  ArrayIterator implements Iterator<E> {
        private int j = 0;              //index of next element to report
        private boolean removable = false;     //can remove be called at this time?

        public boolean hasNext(){
            return j < size;
        }

        public E next() throws NoSuchElementException{
            if(j==size){
                throw new NoSuchElementException("No next element");
            }
            removable = true;
            return data[j++];
        }

        public void remove() throws IllegalStateException{
            if(!removable){
                throw new IllegalStateException("Nothing to remove");
            }
            ArrayList.this.remove(j-1);     //that was the last element returned
            j--;
            removable = false;
        }
    }

    public Iterator<E> iterator(){
        return new ArrayIterator();
    }

    //Utility Methods
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
        if(i<0 || i>n){
            throw new IndexOutOfBoundsException("Illegal index: " + i);
        }
    }
    protected void resize(int capacity){
        E[] temp = (E[])new Object[capacity];
        for(int k=0;k<size;k++){
            temp[k] = data[k];
        }
        data = temp;
    }
}
