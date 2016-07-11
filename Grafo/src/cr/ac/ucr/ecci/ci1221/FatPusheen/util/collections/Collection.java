package cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections;

/**
 * Represents a collection model.
 *
 * @param <E> the type of the elements the collection holds.
 */
public interface Collection<E> extends Iterable<E> {

  /**
   * Adds an element to the collection.
   *
   * @param element the element to add to the collection.
   */
  void add(E element);

  /**
   * Removes an element to the collection. Returns the deleted element.
   *
   * @param element the element to remove to the collection.
   */
  E remove(E element);

  /**
   * Checks if the collection contains the given Element.
   *
   * @param element the element to look for.
   * @return true if the element is in the collection, false otherwise.
   */
  boolean contains(E element);

  /**
   * Returns the size of the collection.
   *
   * @return the size of the collection.
   */
  int size();

  /**
   * Whether the collection is empty or not.
   *
   * @return Whether the collection is empty or not
   */
  boolean isEmpty();

  /**
   * Removes all the elements from the collection.
   */
  void clear();
}
