package cn.ted.design.iterator;

import java.awt.image.BufferedImage;

public class LinkedList_<T> implements Collection_<T>  {
    Node head = null;
    Node tail = null;
    private int size=0;
    public void add(T o){
        Node node = new Node(o);
        node.next=null;
        if (head == null){
            head = node;
            tail = node;
        }else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public Iterator_ iterator(){
        return new LinkedIterator_();
    }

    @Override
    public int size() {
        return size;
    }

    private class Node{
        T o;
        Node next;
        public Node(T o) {
            this.o = o;
        }
    }

    private class LinkedIterator_ implements Iterator_{

        Node currentNode = head;

        @Override
        public boolean hasNext() {
            return currentNode!=null?true:false;
        }

        @Override
        public T next() {
            T o = currentNode.o;
            currentNode = currentNode.next;
            return o;
        }
    }
}
