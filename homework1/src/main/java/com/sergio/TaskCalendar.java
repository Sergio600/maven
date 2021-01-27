package com.sergio;

import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class TaskCalendar {
    final static Logger TASKCALENDARLOG = Logger.getLogger(TaskCalendar.class);

    LinkedList<Task> list = new LinkedList<>();
    LinkedList<Task> list2 = new LinkedList<>();
    String temp ="";

    /**
     * This method reads all tasks from file and saves tasks to ArrayList
     */
    public void checkList() {

        try {
            FileReader fr = new FileReader("hw.txt");
            char[] buf = new char[5];
            while (true) {
                // n - количество прочитанных символов
                int n = fr.read(buf);
                if (n == -1) {
                    break;
                }
                buf = Arrays.copyOf(buf, n);
                temp += new String(buf);
                if (n < 5) {
                    break;
                }
            }
            fr.close();
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**
         * Split .txt file by reg \n
         */
        String[] arrTempStrings = temp.split("\n");
        /**
         * Split arrTempStrings by reg -----
         */
            for (int i = 0; i < arrTempStrings.length; i++) {
                String[] arrTemp = arrTempStrings[i].split("-----");
                Task task = new Task(arrTemp[0], arrTemp[1], IsCompleted.valueOf(arrTemp[2]));
                list.add(task);
            }
            TASKCALENDARLOG.info("Read file is successful");
    }

    /** This method create task and adds to taskList
     *  Task contain date, text of task and information about complete/ uncomplete
     * @return
     * @throws ParseException
     */
    public Task addTask() throws ParseException {
        Task task = new Task();
        System.out.println("Input date in format DD/MM/YYYY");
        Scanner sc = new Scanner(System.in);
        task.setDate(sc.nextLine());

        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");

        task.setDate1(s.parse(task.getDate()));
        System.out.println(s.format(task.getDate1()));

        System.out.println("Input description of task");
        task.setToDo(sc.nextLine());

        System.out.println(task.getToDo());
        task.setStatus(IsCompleted.NOTCOMPLETED);

        TASKCALENDARLOG.info("Task added seccessfull");
        return task;
    }

    /** This method change existing task in taskList
     *  Shows all tasks on choosed date;
     *  Change/delete/mark os complete/uncomplete
     */
    public void changeTask() {
        System.out.println("Input date in format DD/MM/YYYY");
        Scanner sc = new Scanner(System.in);
        String chooseDate = sc.next();
        System.out.println("Tasks for the date: " + chooseDate);
        int count = 0;

        // формируем список дел на выбранную дату в LIST2:
        for (int i = 0; i < list.size(); i++) {
            if ((list.get(i).getDate()).equals(chooseDate)) {
                list2.add(list.get(i));
                count++;
            }
        }

        for (int i = 0; i < list2.size(); i++) {
            System.out.println(i + 1 + ". " + list2.get(i).getToDo());
        }

        // выбираем номер дела которое будем редактировать
        if (count != 0) {
            System.out.println("Choose number of task wich you want to change");
            int chooseUserTodo = sc.nextInt();

            System.out.println("Choose what you want to do: ");
            int chooseUserMenu2 = 0;
            // Выбираем что с ним нужно сделать

            Menu.showMenu2();
            Scanner sc1 = new Scanner(System.in);
            chooseUserMenu2 = sc1.nextInt();

            switch (chooseUserMenu2) {

                case (1): {
                    System.out.println("Your choise is 1. Change description.");
                    System.out.println("Input new description: ");
                    Scanner sc2 = new Scanner(System.in);
                    String readNewTodo = sc2.nextLine();
                    list2.get(chooseUserTodo - 1).setToDo(readNewTodo);
                    break;
                }

                case (2): {
                    System.out.println("Your choise is 2. Delete.");
                    System.out.println("Do you realy wants to delete? Press y/n? ");
                    String deleteChoose;
                    while (true) {
                        deleteChoose = sc.next();
                        if (deleteChoose.equals("y")) {
                            // Удаляется запись (ссылка) из List2 - в List ссылка на объект остается
                            // поэтому ищем выбранный объект лист2 - в лист и когда находим просто его удаляем
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).equals(list2.get(i))) {
                                    list.remove(i);
                                }
                            }
                            System.out.println("Task is deleted.");
                            break;
                        } else {
                            if (deleteChoose.equals("n")) {
                                System.out.println("Task will not be deleted.");
                                break;
                            }
                        }
                    }
                    break;
                }

                case (3): {
                    System.out.println("Your choise is 3. Mark as comleted.");
                    list2.get(chooseUserTodo - 1).setIsCompleted(IsCompleted.COMPLETED);
                    System.out.println("Task marked as completed!");
                    break;
                }

                case (4): {
                    System.out.println("Your choise is 4. Mark as uncomleted.");
                    list2.get(chooseUserTodo - 1).setIsCompleted(IsCompleted.NOTCOMPLETED);
                    System.out.println("Task marked as uncompleted!");
                    break;
                }

                case (5): {
                    System.out.println("Your choise is 5. Cancel.");
                    break;
                }

                default: {
                    System.out.println("Choise is wrong. ");
                }
            }

        } else {
            System.out.println("For choosed date no planned tasks.");
        }

        TASKCALENDARLOG.info("Task changed seccessfull");
    }

    /**
     * This method displays task on choosed preiod:
     * for today;
     * for week;
     * for month;
     * all tasks.
     */
    public void showTasks() {
        int choosePeriod = 0;

        while (choosePeriod != 5) {
            // вывод меню за какой период выводить задания
            Menu.showPeriods();
            //считываем выбор
            Scanner sc1 = new Scanner(System.in);
            choosePeriod = sc1.nextInt();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dateToday = new Date();
            Date dateWeek = new Date(dateToday.getTime() + 604_800_000);
            Date dateMonth = new Date(dateToday.getTime() + 2_592_000_000L);

            switch (choosePeriod) {
                case (1): {
                    System.out.println("Tasks for today: ");
                    String dateTemp = sdf.format(dateToday);
                    System.out.println(dateTemp);
                    int count = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getDate().equals(dateTemp)) {
                            count++;
                            System.out.println(count + ". " + list.get(i).getToDo());
                        }
                    }
                    break;
                }

                case (2): {
                    System.out.println("Tasks for this week: ");
                    int count = 0;
                    for (int i = 0; i < list.size(); i++) {

                        try {
                            if (sdf.parse(list.get(i).getDate()).getTime() >= dateToday.getTime() &&
                                    sdf.parse(list.get(i).getDate()).getTime() <= dateWeek.getTime()) {
                                count++;
                                System.out.println(count + ". " + list.get(i).getDate() + " " + list.get(i).getToDo());
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

                case (3): {
                    System.out.println("Tasks for this month: ");
                    int count = 0;
                    for (int i = 0; i < list.size(); i++) {

                        try {
                            if (sdf.parse(list.get(i).getDate()).getTime() >= dateToday.getTime() &&
                                    sdf.parse(list.get(i).getDate()).getTime() <= dateMonth.getTime()) {
                                count++;
                                System.out.println(count + ". " + list.get(i).getDate() + " " + list.get(i).getToDo());
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }

                case (4): {
                    System.out.println("All planned tasks: ");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i).getDate() + ": " + list.get(i).getToDo() + " - " + list.get(i).getIsCompleted());
                    }
                    break;
                }

                case (5): {
                    System.out.println("Cancel! ");
                    break;
                }
                default: {
                    System.out.println("Не верно сделан выбор. Повторите!");
                }
            }
        }
        TASKCALENDARLOG.info("Task showed");
    }

    /**
     * This method saves all tasks to .txt file
     * format DD/MM/YYYY-----text of task-----complete/uncomplete
     */
    public void saveList() {
        try {
            FileWriter fw = new FileWriter("hw.txt");
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).getDate() + "-----");
                fw.write(list.get(i).getToDo() + "-----");
                fw.write(list.get(i).getIsCompleted() + "-----\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TASKCALENDARLOG.info("Tasks saved");
    }

}