package ninoxit.mr.dataset;

public class PolyDataset {
    private static double[][] X_poly = {
            {1, 1},
            {1, 2},
            {2, 2},
            {2, 3},
            {3, 3}
    };
    private static double[] Y = {1, 2, 2, 3, 3};


    public static double[][] getX_poly() {
        return X_poly;
    }

    public static double[] getY() {
        return Y;
    }

    public void printData() {
        for (int i = 0; i < X_poly.length; i++) {
            System.out.print("X: ");
            for (int j = 0; j < X_poly[i].length; j++) {
                System.out.print(X_poly[i][j] + " ");
            }
            System.out.println(", Y: " + Y[i]);
        }
    }
}
