import java.util.*;
import java.util.function.*;

public class Secant {
   public static List<Double> findRootWithErrors(Function<Double, Double> f, double x0, double x1, double tol, int maxIter) {
      List<Double> errors = new ArrayList<>();
      for (int i = 0; i < maxIter; i++) {
         double fx0 = f.apply(x0);
         double fx1 = f.apply(x1);
         if (fx0 == fx1) {
            throw new ArithmeticException("Division by zero.");
         }
         double x2 = x1 - fx1 * (x1 - x0) / (fx1 - fx0);
         errors.add(Math.abs((x2 - x1) / x2));
         if (Math.abs((x2 - x1) / x2) < tol) {
            return errors;
         }
         x0 = x1;
         x1 = x2;
      }
      throw new ArithmeticException("Method did not converge.");
   }
}