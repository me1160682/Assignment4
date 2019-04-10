import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compress
{
    public static void main(String[] args) {
        try {
            String text = new String(Files.readAllBytes(Paths.get(args[0])));
            FileOutputStream os = new FileOutputStream(args[1]);
            byte[] code = new byte[2];
            int n = text.length();
            int i = 0;
            String w = "";
            Dictionary h = new Dictionary();
            int p;
            int q;
            int r;
            while (i < n) {
                String k = String.valueOf(text.charAt(i));
                i++;
                if (h.has(w+k))
                {
                    w = w + k;
                }
                else
                {
                    h.add(w+k);
                    //System.out.println(w);
                    p = h.find(w).value;
                    q = p/256;
                    r = p%256;
                    if (r <= 127)
                    {
                        code[1] = (byte)r;
                    }
                    else
                    {
                        code[1] = (byte)(r-256);
                    }
                    if (q <= 127)
                    {
                        code[0] = (byte)q;
                    }
                    else
                    {
                        code[0] = (byte)(q - 256);
                    }
                    os.write(code);
                    w = k;
                }
            }
            p = h.find(w).value;
            q = p/256;
            r = p%256;
            if (r <= 127)
            {
                code[1] = (byte)r;
            }
            else
            {
                code[1] = (byte)(r-256);
            }
            if (q <= 127)
            {
                code[0] = (byte)q;
            }
            else
            {
                code[0] = (byte)(q - 256);
            }
            os.write(code);
        } catch(IOException e) {
            System.out.println(e);
        }
        /*catch(FileNotFoundException e){
            System.out.println(e);
        }*/
    }
}
