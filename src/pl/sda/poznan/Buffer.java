package pl.sda.poznan;

public class Buffer {

  private int[] buffer = new int[10];
  private int producerIndex = 0;
  private int consumerIndex = 0;
  private int counter = 0;

  public synchronized void insert(int value) {
    while (counter == buffer.length) {
      System.out.println("PRODUCER - Buffer full - going to wait");
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println("PRODUCER - Inserting value" + value + " at index " + producerIndex
        + " this is " + counter + " value");

    buffer[producerIndex] = value;
    producerIndex = (producerIndex + 1) % buffer.length;
    counter++;
    notifyAll();
  }

  public synchronized int get() {
    while (counter == 0) {
      System.out.println("CONSUMER - Nothing to consume... Waiting");
      try {
        this.wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    int value = buffer[consumerIndex];
    consumerIndex = (consumerIndex + 1) % buffer.length;
    counter--;
    System.out.println("CONSUMER - Getting value " + value);

    notifyAll();

    return value;
  }

}
