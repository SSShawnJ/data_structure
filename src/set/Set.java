package set;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class Set<Key extends Comparable<Key>> implements Iterable<Key> {
    private TreeSet<Key> set;

    /**
     * Initializes an empty set.
     */
    public Set() {
        set = new TreeSet<Key>();
    }

    /**
     * Initializes a new set that is an independent copy of the specified set.
     */
    public Set(Set<Key> x) {
        set = new TreeSet<Key>(x.set);
    }

    /**
     * Adds the key to this set (if it is not already present).
     *
     * @param  key the key to add
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void add(Key key) {
        if (key == null) throw new NullPointerException("called add() with a null key");
        set.add(key);
    }


    /**
     * Returns true if this set contains the given key.
     *
     * @param  key the key
     * @return <tt>true</tt> if this set contains <tt>key</tt>;
     *         <tt>false</tt> otherwise
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("called contains() with a null key");
        return set.contains(key);
    }

    /**
     * Removes the specified key from this set (if the set contains the specified key).
     *
     * @param  key the key
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     */
    public void delete(Key key) {
        if (key == null) throw new NullPointerException("called delete() with a null key");
        set.remove(key);
    }

    /**
     * Returns the number of keys in this set.
     *
     * @return the number of keys in this set
     */
    public int size() {
        return set.size();
    }

    /**
     * Returns true if this set is empty.
     *
     * @return <tt>true</tt> if this set is empty;
     *         <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
 
    /**
     * Returns all of the keys in this set, as an iterator.
     * To iterate over all of the keys in a set named <tt>set</tt>, use the
     * foreach notation: <tt>for (Key key : set)</tt>.
     *
     * @return an iterator to all of the keys in this set
     */
    public Iterator<Key> iterator() {
        return set.iterator();
    }

    /**
     * Returns the largest key in this set.
     *
     * @return the largest key in this set
     * @throws NoSuchElementException if this set is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty set");
        return set.last();
    }

    /**
     * Returns the smallest key in this set.
     *
     * @return the smallest key in this set
     * @throws NoSuchElementException if this set is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty set");
        return set.first();
    }


    /**
     * Returns the smallest key in this set greater than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the smallest key in this set greater than or equal to <tt>key</tt>
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     * @throws NoSuchElementException if there is no such key
     */
    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("called ceiling() with a null key");
        Key k = set.ceiling(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

    /**
     * Returns the largest key in this set less than or equal to <tt>key</tt>.
     *
     * @param  key the key
     * @return the largest key in this set table less than or equal to <tt>key</tt>
     * @throws NullPointerException if <tt>key</tt> is <tt>null</tt>
     * @throws NoSuchElementException if there is no such key
     */
    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("called floor() with a null key");
        Key k = set.floor(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    /**
     * Returns the union of this set and that set.
     *
     * @param  that the other set
     * @return the union of this set and that set
     * @throws NullPointerException if <tt>that</tt> is <tt>null</tt>
     */
    public Set<Key> union(Set<Key> that) {
        if (that == null) throw new NullPointerException("called union() with a null argument");
        Set<Key> c = new Set<Key>();
        for (Key x : this) {
            c.add(x);
        }
        for (Key x : that) {
            c.add(x);
        }
        return c;
    }

    /**
     * Returns the intersection of this set and that set.
     *
     * @param  that the other set
     * @return the intersection of this set and that set
     * @throws NullPointerException if <tt>that</tt> is <tt>null</tt>
     */
    public Set<Key> intersects(Set<Key> that) {
        if (that == null) throw new NullPointerException("called intersects() with a null argument");
        Set<Key> c = new Set<Key>();
        if (this.size() < that.size()) {
            for (Key x : this) {
                if (that.contains(x)) c.add(x);
            }
        }
        else {
            for (Key x : that) {
                if (this.contains(x)) c.add(x);
            }
        }
        return c;
    }

    /**       
     * Compares this set to the specified set.
     * <p>
     * Note that this method declares two empty sets to be equal
     * even if they are parameterized by different generic types.
     * This is consistent with the behavior of <tt>equals()</tt> 
     * within Java's Collections framework.
     *       
     * @param  other the other set
     * @return <tt>true</tt> if this set equals <tt>other</tt>;
     *         <tt>false</tt> otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Set that = (Set) other;
        return this.set.equals(that.set);
    }

    /**
     * This operation is not supported because sets are mutable.
     *
     * @return does not return a value
     * @throws UnsupportedOperationException if called
     */
    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("hashCode() is not supported because sets are mutable");
    }

    /**
     * Returns a string representation of this set.
     *
     * @return a string representation of this set, with the keys separated
     *         by single spaces
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Key key : this)
            s.append(key + " ");
        return s.toString();
    }
}
