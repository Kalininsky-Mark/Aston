package org.Aston1;

import java.util.LinkedList;
import java.util.Objects;

public class MyHashMap<K, V> {
    private final LinkedList[] buckets;
    private int size;

    private static class Pair<K, V> {
        V value;
        K key;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private LinkedList getBucket(int index) {
        if (this.buckets[index] == null) {
            return (this.buckets[index] = new LinkedList());
        }

        return this.buckets[index];
    }

    public MyHashMap() {
        this.buckets = new LinkedList[16];
    }

    private Pair<K, V> getPair(K key) {
        LinkedList<Pair<K,V>> bucket = getBucket(Objects.hashCode(key) % buckets.length);

        for (Pair<K,V> pair : bucket) {
            if (Objects.equals(pair.key, key)) {
                return pair;
            }
        }

        return null;
    }

    public V get(K key) {
        Pair<K, V> pair = getPair(key);
        if (pair == null) {
            return null;
        }
        return pair.value;
    }

    public V put(K key, V value) {
        LinkedList bucket = getBucket(Objects.hashCode(key) % buckets.length);

        for (int i = 0; i < bucket.size(); i++) {
            Pair<K, V> pair = (Pair<K, V>) bucket.get(i);

            if (Objects.equals(pair.key, key)) {
                bucket.set(i, new Pair(key, value));
                return value;
            }
        }

        bucket.addLast(new Pair(key, value));
        this.size++;
        return value;
    }
    public V remove(K key) {
        LinkedList<Pair<K, V>> bucket = getBucket(Objects.hashCode(key) % buckets.length);
        int index = 0;

        for (Pair<K,V> pair : bucket) {

            if (Objects.equals(pair.key, key)) {
                Pair<K, V> previousPair = bucket.remove(index);
                this.size--;
                return previousPair.value;
            }

            index++;
        }

        return null;
    }
}