package az.edu.demo;

import java.io.*;
import java.util.Scanner;

public class ConsoleApp {

    private static ListOfTags content = null;

    private static void getTags() {
        File file = new File("src/main/resources/tags.ser");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            content = (ListOfTags) ois.readObject();
        } catch (EOFException e) {
            content = new ListOfTags();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveTags() {
        File file = new File("src/main/resources/tags.ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getTags();
        run();
    }

    public static void run() {
        Scanner scan = new Scanner(System.in);
        outerloop:
        while (true) {

            System.out.println("1)Add new tag");
            System.out.println("2)Update tag");
            System.out.println("3)Remove tag");
            System.out.println("0)Exit");
            System.out.print("Your choosing: ");

            String ch = scan.nextLine();
            switch (ch.charAt(0)) {
                case '1':
                    addNewTag();
                    break;
                case '2':
                    showAboutTags();
                    System.out.print("Id: ");
                    updateTag(Integer.parseInt(scan.nextLine()));
                    break;
                case '3':
                    showAboutTags();
                    System.out.print("Id: ");
                    content.tags.remove(Integer.parseInt(scan.nextLine()));
                    saveTags();
                    break;
                case '0':
                    break outerloop;
            }
        }
    }

    private static void showAboutTags() {

        content.tags.entrySet().stream().forEach(entry -> {
            System.out.println(String.format("%d: %s", entry.getKey(), entry.getValue().stringfy()));
        });
        saveTags();
    }

    private static void addNewTag() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter id for tag: ");
        int id = Integer.parseInt(scan.nextLine());

        if (content.tags.containsKey(id)) System.out.println("Id must be unique");
        else {
            System.out.print("Enter tag name: ");
            String tagName = scan.nextLine();
            System.out.print("Enter content: ");
            String tagContent = scan.nextLine();
            content.tags.put(id, new HtmlTag(tagName, tagContent));
            saveTags();
            addStyle(id);
        }
    }

    private static void addStyle(int id) {
        System.out.println("Press Enter at the end");
        HtmlTag tag = content.tags.get(id);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Style: ");
            String style = scan.nextLine();
            if (style.equals("")) break;
            System.out.print("Value: ");
            String value = scan.nextLine();
            tag.setCssStyle(style, value);
            saveTags();
        }
    }

    private static void updateTag(int id) {
        System.out.println("1)Change tag name");
        System.out.println("2)Change content");
        System.out.println("3)Update style");

        HtmlTag tag = content.tags.get(id);
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter choosing: ");
        String ch = scan.nextLine();
        switch (ch.charAt(0)) {
            case '1':
                System.out.print("Enter new tag name: ");
                tag.setTagname(scan.nextLine());
                break;
            case '2':
                System.out.print("Enter new content: ");
                tag.setContent(scan.nextLine());
                break;
            case '3':
                addStyle(id);
                break;
        }
        saveTags();

    }
}
