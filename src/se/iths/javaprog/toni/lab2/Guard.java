package se.iths.javaprog.toni.lab2;

import java.math.BigDecimal;

public class Guard {

    public class Against {

        public static void NullOrBlank(String object){
            if (object == null || object.isBlank())
                throw new IllegalArgumentException();
        }

        public static <T> void Null(T object){
            if (object == null)
                throw new IllegalArgumentException();
        }

        public static void Negative (BigDecimal number){
            if (number.compareTo(BigDecimal.ZERO) < 0)
                throw new IllegalArgumentException();
        }
    }
}
