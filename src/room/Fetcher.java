package room;

public interface Fetcher<A, B> {
    public B fetch(A d);
}