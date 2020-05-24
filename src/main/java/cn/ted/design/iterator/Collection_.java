package cn.ted.design.iterator;

public interface Collection_<T> {

    Iterator_ iterator();
    void add(T o);
    int size();
}
