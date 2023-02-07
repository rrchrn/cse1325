import java.util.ArrayList;
import java.util.Scanner;

public class Quiz {

    private ArrayList<Question> questions = new ArrayList<Question>();

    public Quiz() {
        loadQuiz();
    }

    private void loadQuiz() {

        String[] q1 = { "aggregation", "composition", "association", "adaptation" };
        String[] q2 = { "+", "-", "==", "*" };
        String[] q3 = { "String", "StringBuffer", "StringBuilder", "Dog" };
        String[] q4 = { "Interactive Testing", "Regression Testing", "Test-Driven Development", "Compiler" };
        String[] q5 = { "Add", "Set", "Remove", "Pause" };

        questions.add(new Question("In this relationship, an instance can only belong to one class at a time", q1, 2));
        questions.add(new Question("What is an example of non-standard notation for non-primitive comparisons", q2, 3));
        questions.add(new Question("Which is a mutable class?", q3, 3));
        questions.add(new Question("Which is not one of the basic testing options for programming", q4, 4));
        questions.add(new Question("What don't dynamic arrays allow you to do?", q5, 4));

    }

    public double takeQuiz() {
        int correctAnswer = 0;
        Scanner answer = new Scanner(System.in);

        for (int i = 0; i < questions.size(); i++) {

            System.out.println((i + 1) + ". " + questions.get(i)); // getting each instance of question,, will get all
            // elements?
            System.out.print("Your answer? ");
            String proposedAnswer = answer.nextLine();

            if (questions.get(i).checkAnswer(Integer.valueOf(proposedAnswer))) {
                correctAnswer++;
                System.out.println("You got it right!! numCorrect: " + correctAnswer);
            }

        }
        answer.close();

        double numRight = (double) correctAnswer;
        double total = (double) questions.size();

        return numRight / total;
    }

}
