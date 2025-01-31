package net.ababab.kingsword.util;

import java.util.ArrayList;
import java.util.List;

public class Addt {
    public static final List<String> Players = new ArrayList<String>();

    public static void Add(String Name) {
        Players.add(Name);
    }

    public static boolean Contains(String Name) {
        return Players.contains(Name);
    }
}
