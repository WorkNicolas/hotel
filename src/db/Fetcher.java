package db;
/**
 * @author JCSJ
 */
public interface Fetcher<A, B> {
    public B fetch(A d);
}