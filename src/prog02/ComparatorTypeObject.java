package prog02;

import java.util.Comparator;

//сначала просто люди, потом работники
public class ComparatorTypeObject implements Comparator<User> {
    @Override
    public int compare(User a, User b) {
        if ((a instanceof  Employee) && !(b instanceof  Employee)) {
            return 1;
        }
        else if (!(a instanceof  Employee) && (b instanceof  Employee)) {
            return -1;
        }
        return 0;
    }
}

