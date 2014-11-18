using System;

 
public class HelloWorld
{
    static public void Main ()
    {
        Adder add = new Adder(3);
        Console.WriteLine ("Hello Mono World: " + add.addOne());
    }
 
}



