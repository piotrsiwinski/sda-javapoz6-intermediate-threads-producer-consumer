package pl.sda.poznan;

public class ProducerThread extends Thread {

  private Buffer buffer;

  public ProducerThread(Buffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.flush();
      buffer.insert(i);
    }
  }
}
