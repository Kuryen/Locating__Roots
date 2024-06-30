import java.util.*;
import java.util.function.*;

public class NewtonRaphson {
   public static List<Double> findRootWithErrors(Function<Double, Double> f, Function<Double, Double> df, double x0, double tol, int maxIter) {
      double x = x0;
      List<Double> errors = new ArrayList<>();
      for (int i = 0; i < maxIter; i++) {
         double fx = f.apply(x);
         double dfx = df.apply(x);
         if (dfx == 0) {
            throw new ArithmeticException("Derivative is zero, no convergence.");
         }
         double x1 = x - fx / dfx;
         errors.add(Math.abs((x1 - x) / x1));
         if (Math.abs((x1 - x) / x1) < tol) {
            return errors;
         }
         x = x1;
      }
      throw new ArithmeticException("Method did not converge.");
   }
}