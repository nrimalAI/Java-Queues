package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check
 * support/structures/UnboundedQueueInterface.java for detailed explanation of
 * each interface method, including the parameters, return values, assumptions,
 * and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {

  class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
      this.data = data;
    }

    public Node(T data, Node<T> next) {
      this.data = data;
      this.next = next;
    }
  }

  protected Node<T> front;
  protected Node<T> rear;
  private int size;

  public Queue() {
    this.front = null;
    this.rear = null;
    this.size = 0;
  }

  public Queue(Queue<T> other) {
    if (other.front == null) {
      return;
    }
    for (int i = 0; i < other.size; i++) {
      T hold = other.dequeue();
      other.enqueue(hold);
      this.enqueue(hold);
    }
  }

  @Override
  public boolean isEmpty() {
    return (rear == null && front == null);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void enqueue(T element) {
    size++;
    if (element == null)
      return;
    else {
      if (isEmpty()) {
        rear = new Node<T>(element);
        front = rear;
      } else {
        rear.next = new Node<T>(element);
        rear = rear.next;
      }
    }
  }

  @Override
  public T dequeue() throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      size--;
      T hold = front.data;
      front = front.next;
      if (front == null)
        rear = null;
      return hold;
    }
  }

  @Override
  public T peek() throws NoSuchElementException {
    if (isEmpty())
      throw new NoSuchElementException();
    else
      return front.data;
  }

  @Override
  public UnboundedQueueInterface<T> reversed() {
    Queue<T> actual = new Queue<T>();
    Node<T> temp = front;
    while (temp != null) {
      Node<T> curr = new Node<T>(temp.data, actual.front);
      actual.front = curr;
      actual.size++;
      temp = temp.next;
    }
    return actual;
  }
}
