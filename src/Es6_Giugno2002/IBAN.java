package Es6_Giugno2002;

import java.io.Serializable;
import java.util.HashMap;

public class IBAN implements Serializable {

    private int id;

    public IBAN(int ID) {
        this.id = ID;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        IBAN iban = (IBAN) obj;
        return id == iban.id;
    }

    public int hashCode() {
        return Integer.hashCode(id);
    }
}
