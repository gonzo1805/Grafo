package cr.ac.ucr.ecci.ci1221.FatPusheen.util.collections;

/**
 * Interface for all collection that can be iterated.
 *
 * @param <T> the type of the elements over which the iterator will iterate.
 */
public interface Iterable<T> {

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  Iterator<T> iterator();
}
