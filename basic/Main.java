import dataset.PolyDataset;
import src.MultipleLinearRegression;

class Main {
    public static void main(String[] args) {
        double[][] X = PolyDataset.getX_poly();
        double[] Y = PolyDataset.getY();

        double[] beta = MultipleLinearRegression.multipleLinearRegression(X, Y);

        double[] predictions = MultipleLinearRegression.predict(X, beta);
        System.out.println(" ");

        double R2 = MultipleLinearRegression.calculateRSquared(Y, predictions);
        System.out.println("R^2: " + R2);

        double adjustedR2 = MultipleLinearRegression.calculateAdjustedRSquared(R2, X.length, X[0].length);
        System.out.println("Adjusted R^2: " + adjustedR2);

        double[] standardErrors = MultipleLinearRegression.calculateStandardError(X, Y, beta);
        System.out.println("Errores estándar de los coeficientes beta:");
        for (int i = 0; i < standardErrors.length; i++) {
            System.out.println("Beta[" + i + "]: " + standardErrors[i]);
        }

        System.out.println("Coeficientes beta:");
        for (int i = 0; i < beta.length; i++) {
            System.out.println("Beta[" + i + "]: " + beta[i]);
        }
    }
}