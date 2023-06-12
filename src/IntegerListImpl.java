import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private final Integer[] values;
    private int count = 0;

    public IntegerListImpl(int size) {
        this.values = new Integer[size];
    }


    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (count == values.length) {
            throw new ArrayStoreException("List is full");
        }
        values[count++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index >= count) {
            throw new IndexOutOfBoundsException();
        }
        values[index] = item;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        return add(index,item);
    }

    @Override
    public Integer remove(Integer item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        int removed = values[index];
        if (index == count -1) {
            count--;
            return removed;
        }
        for (int i = index; i < count -1; i++) {
            values[index] = values[index + 1];
        }
        count--;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        for (int i = 0; i < count; i++) {
            if (values[i].equals(item)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < count; i++) {
            if (values[i].equals(item)) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = count - 1; i >= 0; i--) {
            if (values[i].equals(item)) {
                return i;
            }

        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return values[index];
    }

    private void checkIndex(int index) {
        if (index >= count) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public boolean equals(IntegerList other) {
        if (other == null || other.size() != size()) return false;
        for (int i = 0; i < count; i++) {
            if (get(i).equals(other.get(i))) return false;
            }
        return true;

    }



    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public void clear() {

        for (int i = 0; i < count; i++) {
            values[i] = 0;

        }
        count = 0;

    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(values,count);
    }
}
