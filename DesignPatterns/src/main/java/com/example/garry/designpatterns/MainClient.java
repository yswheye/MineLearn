package com.example.garry.designpatterns;

import java.util.ArrayList;

/**
 * class description
 *
 * @author garry
 * @version 1.0.0
 * @project DesignPatterns
 * @package com.example.garry.designpatterns
 * @date 2018-01-05 17:43
 */

public class MainClient {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            arrayList.add(i);
        }
        for (int i = 0; i < arrayList.size(); i++) {
            PrintUtils.println(arrayList.get(i) + "");
        }
    }
}
