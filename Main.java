import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    // Define function (a) f(x) = 2x^3 - 11.7x^2 + 17.7x - 5
    public static double f(double x) {
        return 2 * Math.pow(x, 3) - 11.7 * Math.pow(x, 2) + 17.7 * x - 5;
    }

    // Define function (b) f(x) = x + 10 - x * cosh(50/x)
    public static double f2(double x) {
        return x + 10 - x * Math.cosh(50.0 / x);
    }

    // Bisection Method (a)
    public static double[] bisectionMethodA(double a, double b, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2]; // To store root and error
        double ea = 100.0; // Approximate relative error
        int iter = 0;
        double c = a;

        while (ea > tol && iter < maxIter) {
            double prevC = c;
            c = (a + b) / 2.0;
            if (iter != 0) {
                ea = Math.abs((c - prevC) / c) * 100.0;
            }
            log.println(iter + "," + ea);
            if (f(a) * f(c) < 0) {
                b = c;
            } else {
                a = c;
            }
            iter++;

            // Check for divergence
            if (Double.isNaN(c) || Double.isInfinite(c)) {
                System.out.println("Bisection Method: Divergence detected. Stopping iterations.");
                break;
            }
        }

        log.close();
        result[0] = c; // Root found
        result[1] = ea; // Final approximate relative error
        return result;
    }

    // Bisection Method (b)
    public static double[] bisectionMethodB(double a, double b, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double c = a;

        while (ea > tol && iter < maxIter) {
            double prevC = c;
            c = (a + b) / 2.0;
            if (iter != 0) {
                ea = Math.abs((c - prevC) / c) * 100.0;
            }
            log.println(iter + "," + ea);
            if (f2(a) * f2(c) < 0) {
                b = c;
            } else {
                a = c;
            }
            iter++;

            if (Double.isNaN(c) || Double.isInfinite(c)) {
                System.out.println("Bisection Method: Divergence detected. Stopping iterations.");
                break;
            }
        }

        log.close();
        result[0] = c;
        result[1] = ea;
        return result;
    }

    // Newton-Raphson Method (a)
    public static double[] newtonRaphsonMethodA(double x0, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double x = x0;

        while (ea > tol && iter < maxIter) {
            double prevX = x;
            double fx = f(x);
            double dfx = derivativeOfF(x);
            if (dfx == 0) {
                System.out.println("Newton-Raphson Method: Derivative is zero. Stopping iterations.");
                break;
            }
            x = x - fx / dfx;
            if (iter != 0) {
                ea = Math.abs((x - prevX) / x) * 100.0;
            }
            log.println(iter + "," + ea);
            iter++;

            if (iter == maxIter && ea > tol) {
                System.out.println("Newton-Raphson Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = x;
        result[1] = ea;
        return result;
    }

    // Newton-Raphson Method (b)
    public static double[] newtonRaphsonMethodB(double x0, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double x = x0;

        while (ea > tol && iter < maxIter) {
            double prevX = x;
            double fx = f2(x);
            double dfx = derivativeOfF2(x);
            if (dfx == 0) {
                System.out.println("Newton-Raphson Method: Derivative is zero. Stopping iterations.");
                break;
            }
            x = x - fx / dfx;
            if (iter != 0) {
                ea = Math.abs((x - prevX) / x) * 100.0;
            }
            log.println(iter + "," + ea);
            iter++;

            if (iter == maxIter && ea > tol) {
                System.out.println("Newton-Raphson Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = x;
        result[1] = ea;
        return result;
    }

    // Secant Method (a)
    public static double[] secantMethodA(double x0, double x1, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double xPrev = x0;
        double x = x1;

        while (ea > tol && iter < maxIter) {
            double temp = x;
            double fx = f(x);
            double fxPrev = f(xPrev);
            x = x - fx * (x - xPrev) / (fx - fxPrev);
            xPrev = temp;
            if (iter != 0) {
                ea = Math.abs((x - xPrev) / x) * 100.0;
            }
            log.println(iter + "," + ea);
            iter++;

            if (Double.isNaN(x) || Double.isInfinite(x)) {
                System.out.println("Secant Method: Divergence detected. Stopping iterations.");
                break;
            }

            if (iter == maxIter && ea > tol) {
                System.out.println("Secant Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = x;
        result[1] = ea;
        return result;
    }

    // Secant Method (b)
    public static double[] secantMethodB(double x0, double x1, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double xPrev = x0;
        double x = x1;

        while (ea > tol && iter < maxIter) {
            double temp = x;
            double fx = f2(x);
            double fxPrev = f2(xPrev);
            x = x - fx * (x - xPrev) / (fx - fxPrev);
            xPrev = temp;
            if (iter != 0) {
                ea = Math.abs((x - xPrev) / x) * 100.0;
            }
            log.println(iter + "," + ea);
            iter++;

            if (Double.isNaN(x) || Double.isInfinite(x)) {
                System.out.println("Secant Method: Divergence detected. Stopping iterations.");
                break;
            }

            if (iter == maxIter && ea > tol) {
                System.out.println("Secant Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = x;
        result[1] = ea;
        return result;
    }

    // False-Position Method (a)
    public static double[] falsePositionMethodA(double a, double b, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double c = a;

        while (ea > tol && iter < maxIter) {
            double prevC = c;
            double fa = f(a);
            double fb = f(b);
            c = (a * fb - b * fa) / (fb - fa);
            if (iter != 0) {
                ea = Math.abs((c - prevC) / c) * 100.0;
            }
            log.println(iter + "," + ea);
            if (fa * f(c) < 0) {
                b = c;
            } else {
                a = c;
            }
            iter++;

            if (Double.isNaN(c) || Double.isInfinite(c)) {
                System.out.println("False-Position Method: Divergence detected. Stopping iterations.");
                break;
            }

            if (iter == maxIter && ea > tol) {
                System.out.println("False-Position Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = c;
        result[1] = ea;
        return result;
    }

    // False-Position Method (b)
    public static double[] falsePositionMethodB(double a, double b, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double c = a;

        while (ea > tol && iter < maxIter) {
            double prevC = c;
            double fa = f2(a);
            double fb = f2(b);
            c = (a * fb - b * fa) / (fb - fa);
            if (iter != 0) {
                ea = Math.abs((c - prevC) / c) * 100.0;
            }
            log.println(iter + "," + ea);
            if (fa * f2(c) < 0) {
                b = c;
            } else {
                a = c;
            }
            iter++;

            // Check for divergence
            if (Double.isNaN(c) || Double.isInfinite(c)) {
                System.out.println("False-Position Method: Divergence detected. Stopping iterations.");
                break;
            }

            // Check for slow convergence
            if (iter == maxIter && ea > tol) {
                System.out.println("False-Position Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = c;
        result[1] = ea;
        return result;
    }

    // Modified Secant Method (a)
    public static double[] modifiedSecantMethodA(double x, double delta, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double xPrev = x;

        while (ea > tol && iter < maxIter) {
            x = x - f(x) * delta / (f(x + delta) - f(x));
            if (iter != 0) {
                ea = Math.abs((x - xPrev) / x) * 100.0;
            }
            log.println(iter + "," + ea);
            xPrev = x;
            iter++;

            if (Double.isNaN(x) || Double.isInfinite(x)) {
                System.out.println("Modified Secant Method: Divergence detected. Stopping iterations.");
                break;
            }

            if (iter == maxIter && ea > tol) {
                System.out.println("Modified Secant Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = x;
        result[1] = ea;
        return result;
    }

    // Modified Secant Method (b)
    public static double[] modifiedSecantMethodB(double x, double delta, double tol, int maxIter, String logFileName) throws IOException {
        PrintWriter log = new PrintWriter(new FileWriter(logFileName));
        double[] result = new double[2];
        double ea = 100.0;
        int iter = 0;
        double xPrev = x;

        while (ea > tol && iter < maxIter) {
            x = x - f2(x) * delta / (f2(x + delta) - f2(x));
            if (iter != 0) {
                ea = Math.abs((x - xPrev) / x) * 100.0;
            }
            log.println(iter + "," + ea);
            xPrev = x;
            iter++;

            if (Double.isNaN(x) || Double.isInfinite(x)) {
                System.out.println("Modified Secant Method: Divergence detected. Stopping iterations.");
                break;
            }

            if (iter == maxIter && ea > tol) {
                System.out.println("Modified Secant Method: Slow convergence. Stopping iterations.");
            }
        }

        log.close();
        result[0] = x;
        result[1] = ea;
        return result;
    }

    // Utility method to calculate derivative of f(x) for (a)
    public static double derivativeOfF(double x) {
        return 6 * x * x - 23.4 * x + 17.7;
    }

    // Utility method to calculate derivative of f(x) for (b)
    public static double derivativeOfF2(double x) {
        return 1 - Math.sinh(50.0 / x) * (50.0 / x);
    }

    // Main testing method
    public static void main(String[] args) throws IOException {
        // Roots and errors for (a)
        double[] rootA1 = bisectionMethodA(0.5, 2.5, 1e-6, 100, "bisectionA.csv");
        double[] rootA2 = newtonRaphsonMethodA(1.0, 1e-6, 100, "newtonRaphsonA.csv");
        double[] rootA3 = secantMethodA(1.0, 2.0, 1e-6, 100, "secantA.csv");
        double[] rootA4 = falsePositionMethodA(0.5, 2.5, 1e-6, 100, "falsePositionA.csv");
        double[] rootA5 = modifiedSecantMethodA(2.0, 0.01, 1e-6, 100, "modifiedSecantA.csv");

        // Print roots for (a)
        System.out.println("Roots of function (a):");
        System.out.println("Bisection Method: Root = " + rootA1[0] + ", Error = " + rootA1[1]);
        System.out.println("Newton-Raphson Method: Root = " + rootA2[0] + ", Error = " + rootA2[1]);
        System.out.println("Secant Method: Root = " + rootA3[0] + ", Error = " + rootA3[1]);
        System.out.println("False-Position Method: Root = " + rootA4[0] + ", Error = " + rootA4[1]);
        System.out.println("Modified Secant Method: Root = " + rootA5[0] + ", Error = " + rootA5[1]);

        // Roots and errors for (b)
        double[] rootB1 = bisectionMethodB(120, 130, 1e-6, 100, "bisectionB.csv");
        double[] rootB2 = newtonRaphsonMethodB(125, 1e-6, 100, "newtonRaphsonB.csv");
        double[] rootB3 = secantMethodB(125, 130, 1e-6, 100, "secantB.csv");
        double[] rootB4 = falsePositionMethodB(120, 130, 1e-6, 100, "falsePositionB.csv");
        double[] rootB5 = modifiedSecantMethodB(125, 0.01, 1e-6, 100, "modifiedSecantB.csv");

        // Print roots for function (b)
        System.out.println("\nRoots of (b):");
        System.out.println("Bisection Method: Root = " + rootB1[0] + ", Error = " + rootB1[1]);
        System.out.println("Newton-Raphson Method: Root = " + rootB2[0] + ", Error = " + rootB2[1]);
        System.out.println("Secant Method: Root = " + rootB3[0] + ", Error = " + rootB3[1]);
        System.out.println("False-Position Method: Root = " + rootB4[0] + ", Error = " + rootB4[1]);
        System.out.println("Modified Secant Method: Root = " + rootB5[0] + ", Error = " + rootB5[1]);
    }
}