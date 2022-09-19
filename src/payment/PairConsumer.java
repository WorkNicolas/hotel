package payment;

public interface PairConsumer<A, B> {
    public void accept(A a, B b);
}