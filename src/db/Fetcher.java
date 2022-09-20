package db;

public interface Fetcher<A, B> {
    public B fetch(A d);
}