package problema2;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Main {
    private static final int BUFFER_SIZE = 5;
    private static final LinkedList<String> buffer = new LinkedList<>();
    private static final Semaphore empty = new Semaphore(BUFFER_SIZE);
    private static final Semaphore fullSemaphore = new Semaphore(0);
    private static final Semaphore mutex = new Semaphore(1);

    //testarea pentru 90 de documente
    private static final int testLimit = 90;

    private static int papersProduced = 0;

    public static void main(String[] args) {

        Thread producerThread1 = new Thread(new Producer());
        Thread producerThread2 = new Thread(new Producer());
        Thread producerThread3 = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        //3 devices + 1 printer
        producerThread1.start();
        producerThread2.start();
        producerThread3.start();
        consumerThread.start();

        try {
                producerThread1.join();
                producerThread2.join();
                producerThread3.join();
                consumerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    static class Producer implements Runnable {

        @Override
        public void run() {
            while (papersProduced <= testLimit) {
                try {
                    empty.acquire();
                    mutex.acquire();

                    String  paper = "Paper" + " " + (int) (Math.random() * 100);
                    buffer.add(paper);
                    System.out.println("Produced: " + paper);
                    papersProduced++;
                    mutex.release();
                    fullSemaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (papersProduced <= testLimit) {
                try {
                    fullSemaphore.acquire();
                    mutex.acquire();

                    String data = buffer.removeFirst();
                    System.out.println("Printed: " + data);

                    mutex.release();
                    empty.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}