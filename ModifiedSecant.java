import java.util.*;
import java.util.function.*;

public class ModifiedSecant {
   public static List<Double> findRootWithErrors(Function<Double, Double> f, double x0, double delta, double tol, int maxIter) {
      List<Double> errors = new ArrayList<>();
      for (int i = 0; i < maxIter; i++) {
         double fx0 = f.apply(x0);
         double dfx0 = (f.apply(x0 + delta * x0) - fx0) / (delta * x0);
         if (dfx0 == 0) {
               throw new ArithmeticException("Derivative is zero, no convergence.");
         }
         double x1 = x0 - fx0 / dfx0;
         errors.add(Math.abs((x1 - x0) / x1));
         if (Math.abs((x1 - x0) / x1) < tol) {
               return errors;
         }
         x0 = x1;
      }
      throw new ArithmeticException("Method did not converge.");
   }
}