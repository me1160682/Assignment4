import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Decompress
{
    //Instance variables
    public Pair[] arr;
    public int i;

    public Decompress()
    {
        arr = new Pair[65537];
        i = 0;
        while (i < 128) {
            this.add(Character.toString((char)i));
        }
    }


    public static void main(String[] args) {
        try {
            byte[] array = Files.readAllBytes(new File(args[0]).toPath());
            Decompress h = new Decompress();
            FileOutputStream os = new FileOutputStream(args[1]);
            PrintStream p = new PrintStream(os);
            int index = 0;
            int n = array.length;
            int k = h.getInt(array[index],array[index+1]);
            index = index + 2;
            p.print(h.arr[k].key);
            String w = h.arr[k].key;
            String ent;
            while (index < n)
            {
                k = h.getInt(array[index],array[index + 1]);
                index = index + 2;
                if (k < h.i)
                {
                    ent = h.arr[k].key;
                    p.print(ent);
                    h.add(w + Character.toString(ent.charAt(0)));
                    w = ent;
                }
                else
                {
                    ent = w + Character.toString(w.charAt(0));
                    p.print(ent);
                    h.add(ent);
                    w = ent;
                }
            }
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public void add(String s)
    {
        if (i == 65537) return;
        arr[i] = new Pair(s,i);
        i++;
    }

    public int getInt(byte b1, byte b2)
    {
        int a = intFromByte(b1);
        int b = intFromByte(b2);
        return ((256*a) + b) ;
    }
    public int intFromByte(byte b)
    {
        int a = (int)b;
        if (a < 0) return a+256;
        else return a;
    }
}
