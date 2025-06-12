package cat.udl.eps.ed.experiments;

import static cat.udl.eps.ed.experiments.ExperimentUtils.makeList;
import static cat.udl.eps.ed.experiments.ExperimentUtils.sequentialTreapInsertions;

import java.util.ArrayList;

public class Experiment3 {

    public static void main(String[] args) {
        int numElements = 1000;
        int numTrials = 100;

        ArrayList<Integer> elements = makeList(numElements);
        ArrayList<Double> heightRatios = new ArrayList<>();

        for (int trial = 0; trial < numTrials; trial++) {
            int treapHeight = sequentialTreapInsertions(elements).height();
            int minHeight = (int) (Math.log(numElements) / Math.log(2)) + 1;
            double ratio = (double) treapHeight / minHeight;
            heightRatios.add(ratio);
        }

        System.out.println("Height Ratios:");
        for (double ratio : heightRatios) {
            System.out.println(ratio);
        }

        exportResults(heightRatios);
    }

    private static void exportResults(ArrayList<Double> heightRatios) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("experiment3_results.txt")) {
            for (double ratio : heightRatios) {
                writer.println(ratio);
            }
            System.out.println("Results exported to 'experiment3_results.txt'");
        } catch (java.io.IOException e) {
            System.err.println("Error exporting results: " + e.getMessage());
        }
    }

}
