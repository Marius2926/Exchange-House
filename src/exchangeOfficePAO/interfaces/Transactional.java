package exchangeOfficePAO.interfaces;

public interface Transactional {
    void buyCurrency(int value);
    void sellCurrency(int value);
}
