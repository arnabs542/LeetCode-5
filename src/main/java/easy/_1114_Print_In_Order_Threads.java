package easy;

import java.util.concurrent.Semaphore;

/**
 * Created by udaythota on 2/16/20.
 * <p>
 * The same instance of Foo will be passed to three different threads. Thread A will call first(), thread B will call second(), and thread C will call third(). Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().
 * </p>
 */
public class _1114_Print_In_Order_Threads {
    private Semaphore run2, run3;

    // semaphores are kind of locks. 2nd and 3rd are locked in constructor.
    public _1114_Print_In_Order_Threads() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    // after printing the first one, release the 2nd one
    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        run2.release();
    }

    // acquire the run2 lock (released by first process) and print this. after printing 2nd one, release the 3rd lock
    public void second(Runnable printSecond) throws InterruptedException {
        run2.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        run3.release();
    }

    // acquire the 3rd lock and print it
    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
