/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation perc;
    private int openSites;
    private int x;
    private int y;
    private double[] threshold;
    private int index = 0;
    //@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("UWF_UNWRITTEN_FIELD")
    public PercolationStats(int n, int trials)
    {
        perc = new Percolation(n);
        threshold = new double[trials];
        for (int i = 0;i < trials;i++)
        {
            //How many sites are to be opened
            openSites = StdRandom.uniform((n*n)+1);

            for (int j = 0; j < openSites; j++) {

                do{
                    x = StdRandom.uniform(1,n + 1);
                    y = StdRandom.uniform(1,n + 1);
                } while (perc.isOpen(x, y));

                perc.open(x, y);

            }
        }
    }
    public double mean()
    {
        int sum = 0;
        for (int i = 0;i<threshold.length;i++)
        {
            sum += threshold[i];
        }

        return sum/threshold.length;
    }
    public double stddev()
    {
        double dev = 0;
        double mean = mean();
        for (int i = 0;i<threshold.length;i++)
        {
            dev += Math.pow((threshold[i] - mean) , 2);
        }

        return dev/threshold.length - 1;
    }
    public double confidenceLo()
    {
        return mean()-((1.96 * Math.sqrt(stddev()))/Math.sqrt(threshold.length));
    }
    public double confidenceHi()
    {
        return mean()+((1.96 * Math.sqrt(stddev()))/Math.sqrt(threshold.length));
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(2,10000);
        System.out.println(ps.mean());
        System.out.println("Hello");
    }
}
