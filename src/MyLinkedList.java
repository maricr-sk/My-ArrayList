import java.util.Iterator;
import java.util.Objects;

/**
 * Linked list implementation of the MyList interface.
 * @author Marissa Crevecoeur, mac2538
 * @version 1.0 September 27, 2022
 */

public class MyLinkedList<E> implements MyList<E> {
    private Node head, tail;
    private int size;

    /**
     * Constructs an empty list.
     */
    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index    index of the element to return
     * @param element  element to be stored at the specified position
     * @return  the element at the specified position in this list
     * @throws  IndexOutOfBoundsException - if the index is out of range
     *          (index < 0 || index >= size())
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        E oldElement = p.element;
        p.element = element;
        return oldElement;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of the element to return
     * @return       the element at the specified position in this list
     * @throws       IndexOutOfBoundsException - if the index is out of range
     *               (index < 0 || index >= size())
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node p = head;
        for (int i = 0; i < index; i++, p = p.next);
        return p.element;
    }

    /**
     * Appends the specified element to the end of this list.
     * @param element  element to be appended to this list
     * @return true
     */
    public boolean add(E element) {
        Node n = new Node(element);
        if (head == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
        return true;
    }

    /**
     * Removes all the elements from this list.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    public Iterator<E> iterator() {
        return new ListItr();
    }

    private class ListItr implements Iterator<E>    {
        private Node current;

        ListItr() {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E element = current.element;
            current = current.next;
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Node next;
        E element;

        public Node(E element) {
            this.element = element;
        }
    }

    /**
     * Returns a string representation of the list. The string will begin with
     * a '[' and end with a ']'. Inside the square brackets will be a comma-
     * separated list of values, such as [Apple, Banana, Cantaloupe].
     * @return a string representation of the list
     */
    public String toString(){
        Node current = head;
        String answer = "[";
        for(int i = 0; i < size(); i++){
            answer += current.element;
            if(i != size()-1 ) //fix
                answer += ", ";
            current = current.next;
        }
        return answer + "]";
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     * @param index    index at which the specified element is to be inserted
     * @param element  element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index > size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public void add(int index, E element){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node node = new Node(element);
        Node current = head;
        if(head == null){
            head = tail = node;
            head.next = null;
        }
        else if(index == 0){
            node.next = head;
            head = node;
        }
        else {
            int x = 0;
            while (current.next != null && x++ < index - 1) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
        if(index == size()) tail = current;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * @param index  the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (index < 0 || index >= size())
     * The exception message must be:
     * "Index: " + index + ", list size: " + size
     */
    public E remove(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", list size: " + size);
        }
        Node answer = head;
        if(index == 0){
            if(size == 1) {
                head = tail = null;
            }
            else{
                head = head.next;
            }
            size--;
            return (E)answer;
        }
        if(index == size-1){
            Node current = head;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            Node previous = current;
            current = current.next;
            answer = current;
            current = null;
            previous.next=null;
            tail = previous;
            size--;
            return (E)answer;
        }

        Node current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        Node previous = current;
        current = current.next;
        answer = current;
        current = null;
        current = current.next;
        previous.next=current;
        size--;
        return (E)answer;
    }

    /**
     * Returns the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element. More
     * formally, returns the lowest index i such that Objects.equals(o, get(i)),
     * or -1 if there is no such index.
     * @param element element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    public int indexOf(E element){
        Node current = head;
        int index = 0;
        while(index < size()){
            if(Objects.equals(element, current.element)){
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    /**
     * Returns an array of indexes of each occurrence of the specified element
     * in this list, in ascending order. If the specified element is not found,
     * a non-null empty array (not null) is returned.
     * @param elements element to search for
     * @return an array of each occurrence of the specified element in this
     * list, or an empty-array if none of the values are the specified element
     */
    public int[] indexesOf(E elements){
        if(head == null) return new int[0];
        int index = 0;
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements, node.element)) index++;
            node = node.next;
        }
        if(index < 1) return new int[0];

        node=head;
        int [] answer = new int[index];
        int trackA=0;
        for (int i = 0; i < size; i++) {
            if(Objects.equals(elements, node.element)) answer[trackA++] = i;
            node = node.next;
        }
        return answer;
    }

    /**
     * Reverses the data in the list.
     * For MyArrayList, the data inside the underlying array is moved. For
     * MyLinkedList, the tail must become the head, and all the pointers are
     * reversed. Both implementations must run in Theta(n).
     */
    public void reverse(){
        if(size() <2) return;
        Node first = head;
        Node third = first.next;
        Node current = third;
        tail = head;
        int index = 0;

        while(current != null){
            third = third.next;
            current.next = first;
            if(index++ ==0 ) first.next = null;
            first = current;
            current = third;
        }
        head = first;
    }

}
