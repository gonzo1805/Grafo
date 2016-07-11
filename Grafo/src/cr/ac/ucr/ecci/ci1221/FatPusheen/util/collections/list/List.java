package cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.list;

import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Collection;
import cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections.Sortable;

/**
 * Interface of a List model.
 *
 * @param <E> the type of the elements that the list holds.
 */
public interface List<E> extends Collection<E>, Sortable {

  /**
   * Adds the given element to the given position.
   *
   * @param element  the element.
   * @param position the position.
   *
   * @throws IndexOutOfBoundsException if the index is out of range
   * (<tt>index &lt; 0 || index &gt;= size()</tt>)
   */
  void add(E element, int position);

  /**
   * removes the element at the given position.
   *
   * @param position the position.
   *
   * @throws IndexOutOfBoundsException if the index is out of range
   * (<tt>index &lt; 0 || index &gt;= size()</tt>)
   */
  void remove(int position);

  /**
   * Returns the initial position of the given element.
   *
   * @param element the element to find.
   * @return the position of the found element if the element belongs to the list, -1 otherwise.
   */
  int find(E element);


  /**
   * Returns the element at the given position.
   *
   * @param position the position.
   *
   * @throws IndexOutOfBoundsException if the index is out of range
   * (<tt>index &lt; 0 || index &gt;= size()</tt>)
   *
   * @return the element at the given position.
   */
  E get(int position);

  /**
   * Replaces the element in the given position with the given element.
   *
   * @param position the position
   * @param element  the element to replace.
   *
   * @throws IndexOutOfBoundsException if the index is out of range
   * (<tt>index &lt; 0 || index &gt;= size()</tt>)
   *
   * @return the replaced element.
   */
  E set(int position, E element);
}
