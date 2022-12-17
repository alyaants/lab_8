package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //12457qwert
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> arrayList_2 = new ArrayList<>(); //123 чсмсморло
        int size = 4; //qwrtct
        while (true) {
            System.out.println("\n----------Меню----------"); ///sdfghnj
            System.out.println("1. Добавление и удаление объектов;");
            System.out.println("2. Поиск одинаковых элементов с подсчетом совпадений;");
            System.out.println("3. Выгрузка в XML-файл;");
            System.out.println("4. Реверс всех строк;");
            System.out.println("5. Статистика по всем символам, содержащимся в строках коллекции;");
            System.out.println("6. Поиск слова коллекции;");
            System.out.println("7. Инициализация листа по текстовому файлу и вывод содержимого коллекции на экран;");
            System.out.println("8. Посчитать длины строк входящих в коллекцию и вывести результат в упорядоченном виде;");
            System.out.println("9. Просмотр;");
            System.out.println("10. Выход;");
            System.out.println("\nВаш выбор:");
            String ch = in.nextLine(); //mycode
            switch (ch) {
                case "1":
                    String word, ch2, number;
                    System.out.println("-----------Меню-----------");
                    System.out.println("1. Добавить;");
                    System.out.println("2. Удалить;");
                    System.out.println("3. Вернуться обратно;");
                    ch2 = in.nextLine();
                    switch (ch2) {
                        case "1":
                            System.out.println("Введите слово, которое хотите добавить:");
                            word = in.nextLine();
                            if (list.size() < size) {
                                list.add(word);
                            } else {
                                list.remove(0);
                                list.add(0, word);
                            }
                            break;
                        case "2":
                            System.out.println("Введите индекс слова, которое вы хотите удалить (0 - 9):");
                            number = in.nextLine();
                            list.remove(Integer.parseInt(number));
                            break;
                        case "3":

                            break;
                    }
                    break;
                case "2":
                    int count = 0;
                    for (int i = 0; i < list.size(); i++) {
                        for (int j = i + 1; j < list.size(); j++) {
                            if (list.get(i).equals(list.get(j))) {
                                count++;
                            }
                        }
                    }
                    System.out.println("Найденное количество совпадений: " + count);
                    break;
                case "3":
                    try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        File f = new File("src/main/java/file_2.xml");
                        Document document = builder.newDocument();
                        Element text = document.createElement("text");
                        Element  text_List = document.createElement("text_List");
                        document.appendChild(text);
                        text.appendChild(text_List);
                        for(int i = 0; i < list.size(); i++){
                            Element  words = document.createElement("words");
                            text_List.appendChild(words);
                            Text w = document.createTextNode(list.get(i));
                            words.appendChild(w);
                        }
                        Transformer t = TransformerFactory.newInstance().newTransformer();
                        t.setOutputProperty(OutputKeys.INDENT, "yes");
                        t.transform(new DOMSource(document), new StreamResult(f));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    for (int i = list.size() - 1; i >= 0; i--){
                        StringBuffer sb = new StringBuffer(list.get(i));
                        sb.reverse();
                        arrayList_2.add(sb.toString());
                    }
                   arrayList_2.forEach(System.out::println);
                    arrayList_2.clear();
                    break;
                case "5":
                    for(int i = 0; i < list.size(); i++) {
                        System.out.println("Статистика по слову: " + list.get(i).toLowerCase());
                        StringBuffer sb_2 = new StringBuffer(list.get(i).toLowerCase());
                        char[] ar_3 = new char[sb_2.length()];
                        sb_2.getChars(0, sb_2.length(), ar_3, 0);
                        for (char j = 'а'; j <= 'я'; j++) {
                            int c = 0;
                        for (int k = 0; k < ar_3.length; k++) {
                                if (ar_3[k] == j) {
                                    c++;
                                }
                            }
                        if (c>0){
                            System.out.println("Буква " + j + ": " + c + " раз");
                        }
                        }
                    }
                    break;
                case "6":
                    System.out.println("Введите слово, которое хотите найти:");
                    String w = in.nextLine();
                    boolean find = false;
                    int index;
                    for(int i = 0; i < list.size(); i++){
                        if((list.get(i).toLowerCase()).equals(w.toLowerCase())){
                            find = true;
                            System.out.println("Слово \"" + w.toLowerCase() + "\" найдено в коллекции. Оно " + (i+1) + " по счету");
                        }
                    }
                    if(find == false) {
                        System.out.println("Слово \"" + w.toLowerCase() + "\" отсутствует в коллекции");
                    }
                    break;
                case "7":
                    try{
                        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        File f = new File("src/main/java/file.xml");
                        Document document = builder.parse(f);
                        Element root = document.getDocumentElement();
                        NodeList manufacturerList = root.getElementsByTagName("word");
                        for(int i = 0; i < manufacturerList.getLength(); i++){
                            list.add(manufacturerList.item(i).getTextContent().trim());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    list.forEach(System.out::println);
                    break;
                case "8":
                    boolean isSorted = false;
                    String s;
                    while(!isSorted){
                        isSorted = true;
                        for (int i=0; i < list.size() - 1; i++){
                           if (list.get(i).length() > list.get(i + 1).length()){
                               isSorted = false;
                               s = list.get(i);
                               list.set(i, list.get(i+1));
                               list.set(i+1, s);
                           }
                        }
                    }
                    list.forEach(System.out::println);
                    break;
                case "9":
                    list.forEach(System.out::println);
                    break;
                case "10":
                    return;
                default:
                    System.out.println("Вы выбрали неверный пункт.");
                    break;
            }
        }
    }
}
