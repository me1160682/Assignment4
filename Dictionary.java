import java.lang.Math;

public class Dictionary
{
    //Instance variables
    public Pair[] arr;
    public int i;

    public Dictionary()
    {
        arr = new Pair[65537];
        i = 0;
        while (i < 128) {
            this.add(Character.toString((char)i));
        }
    }

    public int hash(String s)
    {
        int n = s.length();
        int sum = (int)s.charAt(n-1);
        for( int i = n-2; i >=0 ; i-- )
        {
            sum = (int)s.charAt(i) + sum*37;
        }
        return Math.abs(sum);
    }

    public int comp(int l)
    {
        return (int)(l%65537);
    }

    public void add(String s)
    {
        if (i == 65537) return;
        int index = comp(hash(s));
        if (arr[index] == null)
        {
            arr[index] = new Pair(s,i);
            i++;
        }
        else
        {
            int n = 1;
            while (arr[(index + n*n)%65537] != null)
            {
                n++;
            }
            arr[(index + n*n)%65537] = new Pair(s,i);
            i++;
        }
    }

    public Pair find(String s)
    {
        int index = comp(hash(s));
        int n = 0;
        while (!(arr[(index + n*n)%65537].key.equals(s)))
        {
            n++;
        }
        return arr[(index + n*n)%65537];
    }

    public boolean has(String s)
    {
        int index = comp(hash(s));
        int n = 0;
        do
        {
            if (arr[(index + n*n)%65537] == null)
            {
                return false;
            }
            else
            {
                if ((arr[(index + n*n)%65537].key.equals(s)))
                {
                    return true;
                }
            }
            n++;
        } while ((index + n*n)%65537 != index);
        return false;
    }
}
