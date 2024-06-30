import java.util.*;
import java.util.function.*;

public class FalsePosition {
   public static List<Double> findRootWithErrors(Function<Double, Double> f, double a, double b, double tol, int maxIter) {
      double c = a;
      List<Double> errors = new ArrayList<>();
      for (int i = 0; i < maxIter; i++) {
         c = a - (f.apply(a) * (b - a)) / (f.apply(b) - f.apply(a));
         if (f.apply(c) == 0.0 || Math.abs((b - a) / 2) < tol) {
               break;
         }
         errors.add(Math.abs((b - a) / 2));
         if (f.apply(c) * f.apply(a) > 0) {
               a = c;
         } else {
               b = c;
         }
      }
      return errors;
   }
}