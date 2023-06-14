import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] values;
    private int count = 0;

    public IntegerListImpl(int size) {
        this.values = new Integer[size];
    }


    @Override
    public Integer add(Integer item) {
        if (item == null) throw new NullPointerException();
        if (count == values.length) grow();
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
        return add(index, item);
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
        if (index == count - 1) {
            count--;
            return removed;
        }
        for (int i = index; i < count - 1; i++) {
            values[index] = values[index + 1];
        }
        count--;
        return removed;
    }

    @Override
    public boolean contains(Integer item) {
        quickSort(0, count - 1);
        return binarySearch(item);

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
        return Arrays.copyOf(values, count);
    }

    private void grow() {
        int newLength = (int) (values.length * 1.5);
        Integer[] newValues = new Integer[newLength];
        System.arraycopy(values, 0, newValues, 0, values.length);
        System.out.println("Размер увеличен с " + values.length + " до " + newLength);
        values = newValues;


    }

    public void sortInsertion() {
        for (int i = 1; i < values.length; i++) {
            int temp = values[i];
            int j = i;
            while (j > 0 && values[j - 1] >= temp) {
                values[j] = values[j - 1];
                j--;

            }
            values[j] = temp;

        }
    }

    public boolean binarySearch(int element) {
        int min = 0;
        int max = values.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == values[mid]) {
                return true;
            }
            if (element < values[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;

    }

    public void quickSort(int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(begin, end);

            quickSort(begin, partitionIndex - 1);
            quickSort(partitionIndex + 1, end);
        }
    }

    private int partition(int begin, int end) {
        int pivot = values[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (values[j] <= pivot) {
                i++;

                swapElements(i, j);
            }
        }
        swapElements( i +1, end);
        return i + 1;
    }
    private void swapElements(int left, int right) {
        int temp = values[left];
        values[left] = values[right];
        values[right] = temp;
    }

}
