public class LCK01
{
    /*
     * This example uses a String object that is defined as a noninterned new instance
     * Avoiding the use of synchronize on objects that may be reused
     */
    public static void main(String[] args)
    {
        StringLockExample example = new StringLockExample();

        Thread thread1 = new Thread(() -> 
        {
            for (int i = 0; i < 1000; i++) 
            {
                example.imcrement();
            }
        });

        Thread thread2 = new Thread(() -> 
        {
            for (int i = 0; i < 1000; i++) 
            {
                example.imcrement();
            }
        });

        thread1.start();
        thread2.start();

        try 
        {
            thread1.join();
            thread2.join();
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        //should output 2000 (each thread incremented to 1000)
        System.out.println("Final Count: " + example.getCount());
    }//end main
}//end class

class StringLockExample 
{
    //lock object defined as a noninterned new instance!
    private final String lock = new String("LOCK");
    private int count = 0;

    //increments by 1
    public void imcrement() 
    {
        synchronized (lock) 
        {
            count++;
        }
    }

    public int getCount() 
    {
        synchronized (lock) 
        {
            return count;
        }
    }
}


