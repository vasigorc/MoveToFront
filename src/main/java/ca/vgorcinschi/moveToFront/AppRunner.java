package ca.vgorcinschi.moveToFront;

import java.util.Iterator;

import static java.lang.Character.valueOf;

/**
 * Created by vgorcinschi on 22/09/16.
 */
public class AppRunner {
    public static void main(String[] args) {
        MoveToFront<Character> mtf = new MoveToFront<>();
        String s = args[0];

        for (int i = 0; i < s.length(); i++) {
            StdOut.print(mtf.add(valueOf(s.charAt(i))));
        }
        StdOut.println();

        Iterator<Character> iter = mtf.iterator();
        while (iter.hasNext())
            StdOut.print(iter.next());
    }
}
