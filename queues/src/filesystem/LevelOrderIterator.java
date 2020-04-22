package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;
import structures.UnboundedQueueInterface;

/**
 * An iterator to perform a level order traversal of part of a filesystem. A
 * level-order traversal is equivalent to a breadth- first search.
 */
public class LevelOrderIterator extends FileIterator<File> {

  Queue<File> main = new Queue<File>();

  /**
   * Instantiate a new LevelOrderIterator, rooted at the rootNode.
   * 
   * @param rootNode : node of the root.
   * @throws FileNotFoundException if the rootNode does not exist.
   */
  public LevelOrderIterator(File rootNode) throws FileNotFoundException {
    if (!rootNode.exists()) {
      throw new FileNotFoundException();
    }
    main.enqueue(rootNode);
  }

  @Override
  public boolean hasNext() {
    return !main.isEmpty();
  }

  @Override
  public File next() throws NoSuchElementException {
    if (main.isEmpty())
      throw new NoSuchElementException();
    else {
      File temp = main.dequeue();
      if (temp.isDirectory()) {
        File[] all = temp.listFiles();// new
        Arrays.sort(all);
        for (File i : all) {
          main.enqueue(i);
        }
      }
      return temp;
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
