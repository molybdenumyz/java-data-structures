import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yinzhe on 17/10/18.
 */
public class IPriorityQueue<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private Object[] queue; // non-private to simplify nested class access

    private int size = 0;

    private final Comparator<? super E> comparator;

    private int modCount = 0;

    public IPriorityQueue(int initialCapacity,
                          Comparator<? super E> comparator) {

        if (initialCapacity < 1) {
            throw new IllegalArgumentException();
        }

        this.queue = new Object[initialCapacity];

        this.comparator = comparator;
    }

    public IPriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public boolean offer(E insert) {
        if (insert == null) {
            throw new NullPointerException();
        }
        int i = size;

        if (i >= queue.length) {

        }

        if (i == 0) {
            queue[0] = insert;
        } else {
            siftUp(i, insert);
        }

        size = size + 1;
        return true;
    }

    public void siftUp(int position, E insert) {


        for (;(comparator.compare(insert,(E) queue[position>>>1]) < 0)&&(position > 0);position = position>>>1){
            queue[position]=queue[position>>>1];
        }

        queue[position] = insert;
    }

    public boolean add(E e) {
        return offer(e);
    }

    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (o.equals(queue[i])) {
                    return i;
                }
            }
        }
        return -1;
    }


    public E poll() {

        if(size == 0) {
            return null;
        }

        E output = (E) queue[0];

        E s = (E) queue[--size];

        queue[size] = null;

        if (size != 0){
            siftDown(0,  s);
        }
        return output;
    }

    public void siftDown(int position,E key) {

        int child;

        int half = size >>> 1;

        for (;position < half;position = child){
            child = (position << 1) +1;


            if (child+1 < size && comparator.compare((E)queue[child],(E) queue[child+1]) > 0 ){
                child = child + 1 ;
            }

            if (comparator.compare(key,(E) queue[child]) <= 0) {
                break;
            }

            queue[position] = queue[child];
        }

        queue[position] = key;
    }
}
