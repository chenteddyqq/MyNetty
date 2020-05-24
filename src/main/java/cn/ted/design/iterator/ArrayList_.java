package cn.ted.design.iterator;

public class ArrayList_<T> implements Collection_<T> {


    private int index = 0;
    Object[] objects =  new Object[10];

    @Override
    public Iterator_<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public void add(T o) {
        if (index == objects.length) {
            Object[] newObjects = new Object[objects.length * 2];
            System.arraycopy(objects, 0, newObjects, 0, objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index++;
    }

    @Override
    public int size() {
        return index;
    }

    private class ArrayListIterator implements Iterator_<T>{

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < index;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            return (T) objects[currentIndex++];
        }
    }
}
