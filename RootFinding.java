import java.io.*;
import java.util.*;
import java.util.function.*;

public class RootFinding {
   public static void main(String[] args) {
      Function<Double, Double> f1 = x -> 2 * Math.pow(x, 3) - 11.7 * Math.pow(x, 2) + 17.7 * x - 5;
      Function<Double, Double> df1 = x -> 6 * Math.pow(x, 2) - 23.4 * x + 17.7;
      Function<Double, Double> f2 = x -> x + 10 - x * Math.cosh(50 / x);
      Function<Double, Double> df2 = x -> 1 - Math.cosh(50 / x) + (50 * Math.sinh(50 / x) / x);

      double tol = 0.001; // 0.1% tolerance
      int maxIter = 100;
      double delta = 0.01;

      try {
         // Finding roots for f1
         findAndWriteErrors(f1, df1, "f1_root1", 0, 1, 0.5, tol, maxIter, delta);
         findAndWriteErrors(f1, df1, "f1_root2", 1, 2, 1.5, tol, maxIter, delta);
         findAndWriteErrors(f1, df1, "f1_root3", 2, 4, 3.5, tol, maxIter, delta);

         // Finding root for f2
         findAndWriteErrors(f2, df2, "f2_root", 120, 130, 125, tol, maxIter, delta);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void findAndWriteErrors(Function<Double, Double> f, Function<Double, Double> df, String filename,
                                          double a, double b, double x0, double tol, int maxIter, double delta) throws IOException {
      List<Double> errors;

      // Bisection method
      errors = Bisection.findRootWithErrors(f, a, b, tol, maxIter);
      writeErrorsToFile(errors, filename + "_bisection.csv");

      // Newton-Raphson method
      errors = NewtonRaphson.findRootWithErrors(f, df, x0, tol, maxIter);
      writeErrorsToFile(errors, filename + "_newton.csv");

      // Secant method
      errors = Secant.findRootWithErrors(f, a, b, tol, maxIter);
      writeErrorsToFile(errors, filename + "_secant.csv");

      // False-Position method
      errors = FalsePosition.findRootWithErrors(f, a, b, tol, maxIter);
      writeErrorsToFile(errors, filename + "_false_position.csv");

      // Modified Secant method
      errors = ModifiedSecant.findRootWithErrors(f, x0, delta, tol, maxIter);
      writeErrorsToFile(errors, filename + "_modified_secant.csv");
   }

   private static void writeErrorsToFile(List<Double> errors, String filename) throws IOException {
      try (FileWriter writer = new FileWriter(filename)) {
         for (int i = 0; i < errors.size(); i++) {
               writer.write(i + "," + errors.get(i) + "\n");
         }
      }
   }
}