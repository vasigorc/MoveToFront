package ca.vgorcinschi.moveToFront;

import java.util.Iterator;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Created by vgorcinschi on 22/09/16.
 */
public class MoveToFront<Item extends Comparable<Item>> implements Iterable<Item>{
    private DoubleNode first;
    private int N;

    private class DoubleNode {
        private Item item;
        private DoubleNode next;
        private DoubleNode previous;

        public DoubleNode(Item item, DoubleNode next){
            this.item = item;
            this.next = next;
        }

        public void setNext(DoubleNode node){
            this.next = node;
            if(node != null){
                node.previous = this;
            }
        }
    }

    public MoveToFront(){
        N = 0;
        first = null;
    }

    public boolean isEmpty(){ return first == null; }

    public int size(){ return N;}

    //return location of item, or 0 if not found
    private int find(Item item){
        int i = 1;
        for (DoubleNode x = first; x!=null; x = x.next){
            if (x.item == item) return i;
            i++;
        }
        return 0;
    }

    //delete node with given item if it exists, and return location
    private Optional<DoubleNode> deletable(DoubleNode x, Item item){
        if (x == null) return empty();
        if(x.item.compareTo(item)==0) {
            if (x.previous != null){
                x.previous.setNext(x.next);
            }
            return  of(x);
        }
        return deletable(x.next, item);
    }

    public int add(Item item){
        int location = find(item);
        DoubleNode oldFirst = first;
        first = deletable(first, item).orElse(new DoubleNode(item, first));
        first.setNext(oldFirst);
        if (location != 0)
            N++;
        return location;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private DoubleNode current = first;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }
}
