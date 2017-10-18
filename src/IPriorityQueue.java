import java.util.Comparator;

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

        if (initialCapacity < 1)
            throw new IllegalArgumentException();

        this.queue = new Object[initialCapacity];

        this.comparator = comparator;
    }
    public IPriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public boolean offer(E insert){
        if (insert == null){
            throw new NullPointerException();
        }
        int i = size;

        if (i>=queue.length){

        }

        if (i == 0)
            queue[0] = insert;
        else
            siftUp(i,insert);

        size = size + 1;
        return true;
    }

    public void siftUp(int position,E insert){


        for (;(comparator.compare(insert,(E) queue[position>>>1]) < 0)&&(position > 0);position = position>>>1){
            queue[position]=queue[position>>>1];
        }

        queue[position] = insert;
    }

    public boolean add(E e){
        return offer(e);
    }

    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    private int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equals(queue[i]))
                    return i;
        }
        return -1;
    }



}
