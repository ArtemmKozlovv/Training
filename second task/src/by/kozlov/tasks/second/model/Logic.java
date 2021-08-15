package by.kozlov.tasks.second.model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {
    public Logic(){
        Parser parser = new Parser("book.txt");

        List<String> allMatches = new ArrayList<String>();
        String str = parser.getText();
        Pattern pattern = Pattern.compile("([A-ZА-Я][^.!?]*)\\?");
        Matcher m = pattern.matcher(str);
        while (m.find())
            allMatches.add((m.group()));

        Map<String, String> map = new HashMap<>();

        for (int i = 1; i < allMatches.size(); i++){
            String temp = allMatches.get(i);
            List<String> temp1 = Arrays.asList(temp.split(" "));
            for(String string: temp1){
                if (string.length() == 7){
                    map.put(string, temp);
                }
            }
        }

        // проверка найденных слов на наличиев них знаков и вывод
        for (Map.Entry<String, String> entry : map.entrySet()){
            int size = entry.getKey().length();
            if (entry.getKey().charAt(size-1) == '?' || entry.getKey().charAt(size-1) == ',' || entry.getKey().charAt(size-1) == '\t' || entry.getKey().charAt(size-1) == '\n'){}
            else System.out.println(entry.getKey());
        }
    }
}
