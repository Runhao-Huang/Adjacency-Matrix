import java.util.Hashtable;

public class sumOfPair
{
    //This method finds a pair of integers in an array given the sum of the two integers.
    //This method is \theta(n) since there is only one for loop with n loops maximum.
    public static void findSumPair(int target, int[] array)
    {
        Hashtable<Integer,Boolean> hTable = new Hashtable<Integer, Boolean>();
        for(int i = 0; i < array.length; ++i)
        {
            //Finding whether there is paired value in the hTable before putting the current number into the table.
            if(hTable.containsKey(target - array[i]))
            {
                System.out.println("Pair found: " + (target - array[i]) + ", " + array[i]);
                return;
            }
            else
            {
                hTable.put(array[i], true);
            }
        }

        System.out.println("The sum pair is not found.");
    }


    //This method returns the number of distinct values in an integer array. If the absolute values of two integers are the same, they are considered the same.
    //This method is \theta(n) since there is only one for loop with n loops maximum.
    public static int DistinctValues(int[] array)
    {
        Hashtable<Integer,Boolean> hTable = new Hashtable<Integer, Boolean>();
        for(int i = 0; i < array.length; ++i)
        {
            hTable.put(Math.abs(array[i]), true);
        }
        return hTable.size();
    }

    //This is used for testing the methods above.
    public static void main(String[] args)
    {
        int[] array = {1, -1, -3, 5, 3, 8, 9};
        System.out.println(DistinctValues(array));
        findSumPair(2, array);
    }


}
