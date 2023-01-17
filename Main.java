package com.company;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        /* PlanIdea is a closed source application. These simple codes have nothing to do with the codes
         in the application. I just wanted to show you how PlanIdea basically works. For more, you can
         contact me on mine profile links!*/

        // Our imagine days or agenda creating:
        ArrayList<String> dayOfWeek = new ArrayList<>();
        dayOfWeek.add("Monday");
        dayOfWeek.add("Tuesday");
        dayOfWeek.add("Wednesday");
        dayOfWeek.add("Thursday");
        dayOfWeek.add("Friday");
        dayOfWeek.add("Saturday");
        dayOfWeek.add("Sunday");

        // We are starting... First we download/create some plan down there: "schoolPlan".

        /* You can change the main topics and subtopics below as you wish.
        - Do not forget to give time to the sub-topics in multiples of 15 minutes.
        - Each main topic should have at least 1 subtopic. */

        ArrayList<Subtopic> subtopicsOfMath = new ArrayList<>();
        Subtopic subtopic = new Subtopic("Analytic geometry", "Solve the questions in source X. Watch Y video", 9.5);
        Subtopic subtopic1 = new Subtopic("Negative numbers", "Solve the questions in source A. Watch B video", 7);
        Subtopic subtopic2 = new Subtopic("Simple geometry", "Solve the questions in source O. Watch P video", 10.5);

        subtopicsOfMath.add(subtopic);
        subtopicsOfMath.add(subtopic1);
        subtopicsOfMath.add(subtopic2);

        ArrayList<Subtopic> subtopicsOfEnglish = new ArrayList<>();
        Subtopic subtopicx = new Subtopic("Alphabet and phonics", "Solve the questions in source Z. Watch Q video", 9.25);
        Subtopic subtopicy = new Subtopic("Present Progressive Tense", "Solve the questions in source K. Watch L video", 12);
        Subtopic subtopicz = new Subtopic("Concepts", "Solve the questions in source C. Watch N video", 8);

        subtopicsOfEnglish.add(subtopicx);
        subtopicsOfEnglish.add(subtopicy);
        subtopicsOfEnglish.add(subtopicz);

        MainSubject mainSubjectMath = new MainSubject("Math", subtopicsOfMath);
        MainSubject mainSubjectEnglish = new MainSubject("English", subtopicsOfEnglish);

        ArrayList<MainSubject> mainSubjectArray = new ArrayList();
        mainSubjectArray.add(mainSubjectMath);
        mainSubjectArray.add(mainSubjectEnglish);

        Plan schoolPlan = new Plan("This plan is purely for example. Instead of this plan," +
                " consider the plans we have in practice, such as the 1-year high school curriculum or the 3-month fitness plan." +
                " Hundreds or even thousands of hours of plans" +
                " .", mainSubjectArray);

        /* Our plan ready. Next up is the select your work times daily.
         Choose which day and how many hours you will work.
         */
        SelectorForStart selector = new SelectorForStart(1.5, 2,
                1, 0.5, 1, 1.25, 1);


        automaticAgendaPreparationAlgorithm(schoolPlan, dayOfWeek, selector);


    }


    public static class Subtopic {
        /*Exponential numbers, sub-topics such as the 2nd week,
         how long it will take to end. They can be gathered under the main topic*/
        String name = "";
        String comment = "";
        double time = 0; // hour

        public Subtopic(String name, String comment, double time) {
            this.name = name;
            this.comment = comment;
            this.time = time;
        }
    }

    public static class MainSubject {
        // This Main Topic like Math or Monthly plan. A main topic with many subtopics under it.
        String name = "";
        ArrayList<Subtopic> subtopics = new ArrayList<>();

        public MainSubject(String name, ArrayList<Subtopic> subtopics) {
            this.name = name;
            this.subtopics = subtopics;
        }
    }

    public static class Plan {
        //It's a plan where all things come together. It has a short description.
        String comment = "";
        ArrayList<MainSubject> mainSubjects = new ArrayList<>();

        public Plan(String comment, ArrayList<MainSubject> mainSubjects) {
            this.comment = comment;
            this.mainSubjects = mainSubjects;
        }
    }

    public static void automaticAgendaPreparationAlgorithm(Plan selectedPlan, ArrayList<String> daysOfWeek, SelectorForStart selectorForStart) {
        ArrayList<Double> selectorsArray = selectorForStart.returnWorkTimes();
        /* Example algorithm. You can create different agendas by changing
         the codes below as you wish. The automatic agenda making codes in the app
          are closed source. */

        boolean continueNow = true; // Ending control.
        while (continueNow) {
            int mainCounter = 0;

            for (int i = 0; i <= 6; i++) { // Agenda details, counting days of week.
                String presentDayName = daysOfWeek.get(i);
                double presentDayWorkTime = selectorsArray.get(i);
                System.out.println("\n" + presentDayName + "'s works: " + presentDayWorkTime + " hour");


                double totalWorkTime = 0; // If total time = 0 we must end the algorithm.
                for (MainSubject mainSubject : selectedPlan.mainSubjects) {
                    for (Subtopic subtopic : mainSubject.subtopics) {
                        totalWorkTime += subtopic.time;
                    }

                }
                if (totalWorkTime <= 0) { // All done!
                    continueNow = false;
                    break;
                } else {
                    while (presentDayWorkTime > 0) {

                        if (mainCounter > selectedPlan.mainSubjects.size() - 1) {
                            mainCounter = 0; // This must be incrase when passed another main subject.
                        }

                        for (Subtopic subtopic : selectedPlan.mainSubjects.get(mainCounter).subtopics) {

                            if (presentDayWorkTime <= 0) {
                                break;
                            } else if (subtopic.time <= 0) {
                                selectedPlan.mainSubjects.get(mainCounter).subtopics.remove(subtopic);
                                if (selectedPlan.mainSubjects.get(mainCounter).subtopics.size() <= 0) {
                                    selectedPlan.mainSubjects.remove(mainCounter);
                                }
                                break;
                            }
                            if (subtopic.time >= 1 && presentDayWorkTime >= 1) {
                                // todo You can print the content you want here (Like main topic name, sub topic description).
                                System.out.println(subtopic.name + " time= " + 1 + " hour");
                                subtopic.time -= 1;
                                presentDayWorkTime -= 1;

                                mainCounter++;
                                if (mainCounter > selectedPlan.mainSubjects.size() - 1) {
                                    mainCounter = 0; // This must be incrase when passed another main subject.
                                }
                                break;

                            } else if (subtopic.time >= 0 && presentDayWorkTime >= 0) {
                                double smallone = 0;
                                if (subtopic.time <= presentDayWorkTime) {
                                    smallone = subtopic.time;
                                } else {
                                    smallone = presentDayWorkTime;
                                }
                                // todo You can print the content you want here (Like main topic name, sub topic description).
                                System.out.println(subtopic.name + " time= " + smallone + " hour");
                                subtopic.time -= smallone;
                                presentDayWorkTime -= smallone;

                                mainCounter++;
                                if (mainCounter > selectedPlan.mainSubjects.size() - 1) {
                                    mainCounter = 0; // This must be incrase when passed another main subject.
                                }
                                break;

                            } else {
                                mainCounter++;
                                if (mainCounter > selectedPlan.mainSubjects.size() - 1) {
                                    mainCounter = 0; // This must be incrase when passed another main subject.
                                }
                                break;
                            }


                        }

                        double tt = 0; // If total time = 0 we must end the algorithm.
                        for (MainSubject mainSubject : selectedPlan.mainSubjects) {
                            for (Subtopic subtopic : mainSubject.subtopics) {
                                tt += subtopic.time;
                            }

                        }
                        if (tt == 0) {
                            presentDayWorkTime = 0;
                            break;
                        }


                    }

                }


            }
        }
    }

    public static class SelectorForStart {
        // Choose which day and how many hours you will work.
        // It should be in multiples of 15 minutes (like 1.25, 2.50). Do not enter numbers such as 1.10.
        double mondaysWorkTime = 0;
        double tuesdaysWorkTime = 0;
        double wednesdaysWorkTime = 0;
        double thursdaysWorkTime = 0;
        double fridaysWorkTime = 0;
        double saturdaysWorkTime = 0;
        double sundaysWorkTime = 0;

        public SelectorForStart(double mondaysWorkTime, double tuesdaysWorkTime,
                                double wednesdaysWorkTime, double thursdaysWorkTime,
                                double fridaysWorkTime, double saturdaysWorkTime, double sundaysWorkTime) {
            this.mondaysWorkTime = mondaysWorkTime;
            this.tuesdaysWorkTime = tuesdaysWorkTime;
            this.wednesdaysWorkTime = wednesdaysWorkTime;
            this.thursdaysWorkTime = thursdaysWorkTime;
            this.fridaysWorkTime = fridaysWorkTime;
            this.saturdaysWorkTime = saturdaysWorkTime;
            this.sundaysWorkTime = sundaysWorkTime;
        }

        public ArrayList<Double> returnWorkTimes() {
            ArrayList<Double> workTimes = new ArrayList<>();
            workTimes.add(mondaysWorkTime);
            workTimes.add(tuesdaysWorkTime);
            workTimes.add(wednesdaysWorkTime);
            workTimes.add(thursdaysWorkTime);
            workTimes.add(fridaysWorkTime);
            workTimes.add(saturdaysWorkTime);
            workTimes.add(sundaysWorkTime);
            return workTimes;

        }
    }


}

