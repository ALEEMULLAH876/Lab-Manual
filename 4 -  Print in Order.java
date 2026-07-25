import java.util.concurrent.atomic.AtomicInteger;

class Foo {

    private AtomicInteger state = new AtomicInteger(0);

    public Foo() {}

    public void first(Runnable printFirst) {
        printFirst.run();
        state.incrementAndGet();
    }

    public void second(Runnable printSecond) {
        while (state.get() != 1) {}

        printSecond.run();
        state.incrementAndGet();
    }

    public void third(Runnable printThird) {
        while (state.get() != 2) {}
        
        printThird.run();
    }
}
