package org.Aston1;

public class Main {
    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap<>();
        System.out.println(hashMap.put(1,1));
        System.out.println(hashMap.get(1));
        System.out.println(hashMap.remove(1));
    }
}