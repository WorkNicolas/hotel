package auth;
public interface LoginObserver {
    public void onSuccess(User u);
    public void onFail();
    public void onMaxTries();
}