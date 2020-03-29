package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Transaction;

import java.util.Comparator;

public class valueTransactionsComparator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        Transaction o1 = (Transaction) o;
        Transaction o2 = (Transaction) t1;
        if(o1.getValue() > o2.getValue())
            return -1;
        else{
            if(o1.getValue() < o2.getValue())
                return 1;
            else
                return 0;
        }
    }
}
