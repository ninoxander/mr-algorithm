package ninoxit.mr.src;

import ninoxit.mr.discretemaths.MatrixOperations;
import ninoxit.mr.discretemaths.DiscreteMaths;

public class MultipleLinearRegression {

    public static double[] multipleLinearRegression(double[][] X, double[] y) {
        int n = X.length;
        int p = X[0].length;

        double[][] X_transposed = MatrixOperations.transpose(X);
        double[][] XTX = MatrixOperations.multiply(X_transposed, X);

        double[][] XTX_inv = MatrixOperations.invert(XTX);

        double[] XTy = MatrixOperations.multiply(X_transposed, y);

        double[] beta = new double[p];
        for (int i = 0; i < p; i++) {
            beta[i] = 0;
            for (int j = 0; j < p; j++) {
                beta[i] += XTX_inv[i][j] * XTy[j];
            }
        }

        return beta;
    }

    public static double[] predict(double[][] X, double[] beta) {
        int n = X.length;
        double[] predictions = new double[n];
        for (int i = 0; i < n; i++) {
            predictions[i] = 0;
            for (int j = 0; j < X[i].length; j++) {
                predictions[i] += X[i][j] * beta[j];
            }
        }
        return predictions;
    }

    public static double calculateRSquared(double[] y, double[] predictions) {
        double ssTotal = 0;
        double ssResidual = 0;
        double meanY = DiscreteMaths.mean(y);

        for (int i = 0; i < y.length; i++) {
            ssTotal += Math.pow(y[i] - meanY, 2);
            ssResidual += Math.pow(y[i] - predictions[i], 2);
        }

        return 1 - (ssResidual / ssTotal);
    }

    public static double calculateAdjustedRSquared(double R2, int n, int p) {
        return 1 - ((1 - R2) * (n - 1)) / (n - p - 1);
    }

    public static double[] calculateStandardError(double[][] X, double[] y, double[] beta) {
        int n = X.length;
        int p = X[0].length;

        double[] predictions = predict(X, beta);
        double[] residuals = new double[n];
        for (int i = 0; i < n; i++) {
            residuals[i] = y[i] - predictions[i];
        }

        double sse = 0.0;
        for (double residual : residuals) {
            sse += residual * residual;
        }

        double mse = sse / (n - p);

        double[][] X_transposed = MatrixOperations.transpose(X);
        double[][] XTX_inv = MatrixOperations.invert(MatrixOperations.multiply(X_transposed, X));

        double[] standardErrors = new double[p];
        for (int i = 0; i < p; i++) {
            double variance = XTX_inv[i][i] * mse;
            standardErrors[i] = Math.sqrt(variance);
        }

        return standardErrors;
    }
}
