package map;

import java.util.Arrays;

/**
 * HashMap implement:
 * 1. a table of bucket(an array of buckets), using array index to denote each bucket
 * 2. For each <Key, Value>, it goes to one of the buckets, the bucket index is determined by a hash function applied on key and size of array
 * 3. Collision control: two keys mapped to same bucket=>
 *                                              separate chaining-the element of each of the bucket is actually a single linked list
 *                                              open address-put the key-value into next available bucket
 *  supported operations:
 *  size();
 *  isEmpty();
 *  clear();
 *  put<k key, V value>
 * get(K key)
 * containsKey(K key)
 * remove(K key)
 * we can achieve an average time complexity of O(1) for the put and get operations and space complexity of O(n)
 */
public class hashMap <K, V>{
    public static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
    }
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] array;
    private int size; // how many key-value pairs are actually stored in the hashmap
    private float loadFactor; // determine when to rehash


    public hashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public hashMap(int cap, float loadFactor) {
        if (cap <= 0) {
            throw new IllegalArgumentException("cap can not be <= 0");
        }
        this.array = (Node<K, V>[]) (new Node[cap]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }
    public int size() {
        return size;
    }
    public void clear() {
        Arrays.fill(this.array, null);
        size = 0;
    }
    public boolean isEmpty() {
        return  size == 0;
    }
    //non-negative
    private int hash(K key) {
        // 1. null key
        if (key == null) {
            return 0;
        }
        return key.hashCode() & 0x7FFFFFFF; // guarantee non-negative
    }
    public int getIndex(K key) {
        return hash(key) % array.length;
    }
    private boolean equalsValue(V v1, V v2) {
        return v1 == v2 || v1 != null && v1.equals(v2);
    }
    //O(n) traverse the whole array, and traverse each of the linkedList in the array
    public boolean containsValue(V value) {
        //special case
        if (isEmpty()) {
            return false;
        }
        for (Node<K, V> node: array) {
            while (node != null) {
                if (equalsValue(node.value, value)) {
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }
    private boolean equalsKey(K k1, K k2) {
        return k1 == k2 || k1 != null && k1.equals(k2);
    }
    public boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }
    public V get(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (equalsKey(node.key, key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
    public V put(K key, V value) {
        int index = getIndex(key);
        //keep the head
        Node<K, V> head = array[index];
        Node<K, V> node = head;
        while (node != null) {
            if (equalsKey(node.key, key)) {
                V result = node.value;
                node.value = value;
                return result;
            }
            node = node.next;
        }
        // append the new node before the head and update the new head
        Node<K, V> newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if (needRehashing()) {
            rehashing();
        }
        return null;
    }
    public boolean needRehashing() {
        float ratio = (size + 0.0f) / array.length;
        return ratio >= loadFactor;
    }
    private void rehashing() {
        // new double size array
        //for each node in the old array
        // put to the new larger array
        Node<K, V>[] oldArray = array;
        array = (Node<K, V>[]) (new Node[array.length * 2]); // * 2 can be replaced by
        for (Node<K, V> node: oldArray) {
            while (node != null) {
                Node<K ,V> next = node.next;
                int index = getIndex(node.key);
                node.next = array[index];
                array[index] = node;
                node = next;
            }
        }
    }
    public V remove(K key) {
        int index = getIndex(key);
        Node<K ,V> node = array[index];
        Node<K, V> pre = null;
        while (node != null) {
            if (equalsKey(node.key, key)) {
                if (pre != null) {
                    pre.next = node.next;
                } else {
                    array[index] = node.next;
                }
                size--;
                return node.value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }
}