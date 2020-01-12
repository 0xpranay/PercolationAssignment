/* *****************************************************************************
 *  Name:Pranay Reddy
 *  Date:11-01-2020
 *  Description:Percolation Assignment
 **************************************************************************** */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private boolean[] openLog;
    private WeightedQuickUnionUF ufGrid;
    private WeightedQuickUnionUF ufFull;
    private int count = 0;


    public Percolation(int N)
    {
        if (N > 0)
        {
            this.N = N;
            ufGrid = new WeightedQuickUnionUF(N*N + 2);
            ufFull = new WeightedQuickUnionUF(N*N + 1);
            for (int j = 0;j <= N;j++)
            {
                ufGrid.union(0, flatten(1,j));
                ufFull.union(0, flatten(1,j));

                ufGrid.union(N * N + 1, flatten(N,j));
            }
            openLog = new boolean[(N * N) + 2];

            //open the virtual top site
            openLog[0] = true;
        }
        else throw new IllegalArgumentException("Enter N value greater than 0");

    }
    public void open(int i,int j)
    {
        if (validQuery(i,j))
        {
            if (!isOpen(i,j))
            {
                if (validQuery(i,j-1) && isOpen(i,j-1))
                {
                    ufGrid.union(flatten(i,j), flatten(i,j-1));
                    ufFull.union(flatten(i,j), flatten(i,j-1));
                }

                if (validQuery(i,j+1) && isOpen(i,j+1))
                {
                    ufGrid.union(flatten(i,j), flatten(i,j+1));
                    ufFull.union(flatten(i,j), flatten(i,j+1));
                }

                if (validQuery(i-1,j) && isOpen(i-1,j))
                {
                    ufGrid.union(flatten(i,j), flatten(i-1,j));
                    ufFull.union(flatten(i,j), flatten(i-1,j));
                }

                if (validQuery(i+1,j) && isOpen(i+1,j))
                {
                    ufGrid.union(flatten(i,j), flatten(i+1,j));
                    ufFull.union(flatten(i,j), flatten(i+1,j));
                }
                openLog[flatten(i,j)] = true;
                count++;
                if (i == N)
                {
                  openLog[N*N + 1] = true;
                }
            }

        }
        else throw new IllegalArgumentException("Index Out Of Bounds");
    }

    public boolean isOpen(int i , int j)
    {
        if (validQuery(i,j))
        {
            return openLog[flatten(i,j)];
        }
        else throw new IllegalArgumentException("Index Out Of Bounds");
    }
    public boolean isFull(int i , int j)
    {
        if (validQuery(i,j))
        {
            return isOpen(i,j) && ufFull.connected(0 , flatten(i,j));
        }
        else throw new IllegalArgumentException("Index Out Of Bounds");
    }
    public int numberOfOpenSites()
    {
        return count;
    }
    public boolean percolates()
    {
        return openLog[N*N + 1] && ufGrid.connected(0,N*N + 1);
    }
    public int flatten(int i , int j)
    {
        return ((N * i) - (N - j));
    }
    public boolean validQuery(int i , int j)
    {
        if (i >= 1 && i <=N && j >=1 && j<=N)
        {
            return true;
        }
        else return false;
    }

}
