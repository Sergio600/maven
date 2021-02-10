package com.sergio;

import org.apache.log4j.Logger;
import java.util.Scanner;

public class Main {
    final static Logger MAINLOG = Logger.getLogger(Main.class);

    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
        int choose = 0;
        TaskCalendar tc = new TaskCalendar();
        //считываем информацию из текстового файла, разбиваем ее и добавляем все объекты в LinkedList
        tc.checkList();

        // запуск меню и всех подменю
        while (choose != 4) {
            Menu.showMenu();

            try {
                choose = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Your input is wrong!");
                continue;
            }

            MAINLOG.info("Выбор пользователя: " + choose);

            switch (choose) {
                case 1: {
                    System.out.println("Your input is 1. Add task.");
                    Task task;

                    try {
                        task= tc.addTask();
                    } catch (Exception e) {
                        System.out.println("Wrong date format!");
                        break;
                    }

                    tc.list.add(task);
                    break;
                }

                case 2: {
                    System.out.println("Your input is 2. Edit task.");
                    tc.changeTask();
                    break;
                }

                case (3): {
                    System.out.println("Your input is 3. Show tasks.");
                    tc.showTasks();
                    break;
                }

                case (4): {
                    System.out.println("Your input is 4. Exit");
                    break;
                }

                default: {
                    System.out.println("Wrong input. Please try again");
                }
            }
        }
        // сохранение в текстовый файл всех заданий (date, toDo, status)
        tc.saveList();
        sc.close();
    }
}