package pl.sda.poznan;

public class Main {

  public static void main(String[] args) {
    Buffer buffer = new Buffer();
    Thread producerThread = new ProducerThread(buffer);
    Thread consumerThread = new ConsumerThread(buffer);
    producerThread.setName("producerThread");
    producerThread.setDaemon(true);

    consumerThread.setName("consumerThread");
    consumerThread.setDaemon(true);

    producerThread.start();
    consumerThread.start();

    try {
      producerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      consumerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Koniec");
  }
}
