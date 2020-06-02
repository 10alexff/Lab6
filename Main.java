package com.company;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.OutputStream;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Coder coder = new Coder();
        coder.encode("1.txt", "2.txt", 'A');
        coder.decode("2.txt", "3.txt", 'A');
    }
}


class Coder {

    public void encode(String text,String encoded, char code) throws IOException
    {
        FileInputStream input_F = new FileInputStream(text);

        FileOutputStream outout_F = new FileOutputStream(encoded);;

        CustomOutputFilter output_Filter = new CustomOutputFilter(outout_F);
        
        int j = -1;
        while ((j=input_F.read())!=-1)
        {
            output_Filter.write(j,code);
        }
        output_Filter.close();
    }
    public void decode(String text,String encoded, char code) throws IOException
    {
        FileInputStream input_F = new FileInputStream(text);

        FileOutputStream outout_F = new FileOutputStream(encoded);;

        CustomOutputFilter output_Filter = new CustomOutputFilter(outout_F);

        int j = -1;
        while ((j=input_F.read())!=-1)
        {
            output_Filter.write(j,-code);
        }
        output_Filter.close();
    }
}
class CustomOutputFilter extends FilterOutputStream {
    public CustomOutputFilter(OutputStream out) {
        super(out);
    }

    public void write(int b,int code) throws IOException
    {
        super.write(b+code);
    }
}