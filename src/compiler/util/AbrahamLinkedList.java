package compiler.util;

import compiler.tokenizer.Token;

public class AbrahamLinkedList<T> {

    private LLNode<T> first;
    private LLNode<T> last;
    private int count;

    public AbrahamLinkedList() {
    }

    public LLNode<T> insertFirst(T value) {
        LLNode<T> node = new LLNode<T>();
        node.setValue(value);
        count += 1;

        if (first == null) {
            this.first = this.last = node;
        } else {
            this.first.setPrevious(node);
            node.setNext(this.first);
            this.first = node;
        }

        return node;
    }

    public LLNode<T> insertLast(T value) {
        LLNode<T> node = new LLNode<T>();
        node.setValue(value);
        count += 1;

        if (first == null) {
            this.first = this.last = node;
        } else {
            this.last.setNext(node);
            node.setPrevious(this.last);
            this.last = node;
        }

        return node;
    }

    public AbrahamLinkedList<T> insertLast(AbrahamLinkedList<T> list) {
        LLNode<T> next = list.getFirst();
        while (next != null) {
            this.insertLast(next.getValue());
            next = next.getNext();
        }
        
        return this;
    }

    public AbrahamLinkedList<T> insertFirst(AbrahamLinkedList<T> list) {
        LLNode<T> next = list.getLast();
        while (next != null) {
            this.insertFirst(next.getValue());
            next = next.getPrevious();
        }
        
        return this;
    }

    public AbrahamLinkedList<T> insertBefore(LLNode<T> right, AbrahamLinkedList<T> list) {
        LLNode<T> next = list.getLast();
        while (next != null) {
            this.insertBefore(right, next.getValue());
            next = next.getPrevious();
        }
        
        return this;
    }

    public void printAllNodes() {
        System.out.println();
        LLNode<T> t = first;
        while (t != null){
            System.out.println(t.getValue().toString());
            t = t.getNext();
        }
    }

    public LLNode<T> insertBefore(LLNode<T> right, T value) {
        if (right == null) {
            return this.insertLast(value);
        } else {
            LLNode<T> node = new LLNode<T>();
            LLNode<T> left = right.getPrevious();

            node.setValue(value);
            count += 1;

            node.setNext(right);
            right.setPrevious(node);

            if (left == null) {
                this.first = node;
            } else {
                left.setNext(node);
                node.setPrevious(left);
            }

            return node;
        }
    }

    public LLNode<T> getFirst() {
        return this.first;
    }

    public LLNode<T> getLast() {
        return this.last;
    }

    public int getCount() {
        return this.count;
    }
}
