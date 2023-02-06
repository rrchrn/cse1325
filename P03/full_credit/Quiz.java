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
        String[] q3 = { "String", "StringBuffer", "StringBuilder", "Float" };
        String[] q4 = { "Interactive Testing", "Regression Testing", "Test-Driven Development", "Compiler" };
        String[] q5 = { "Add", "Set", "Remove", "Clear" };

        questions.add(new Question("In this relationship, an instance can only belong to one class at a time", q1, 2));
        questions.add(new Question("What is an example of non-standard notation for non-primitive comparisons", q2, 3));
        questions.add(new Question("Which is a mutable class?", q3, 2));
        questions.add(new Question("Which is not one of the basic testing options for programming", q4, 4));
        questions.add(new Question("What don't allow you to do?", q5, 4));

    }

    public double takeQuiz() {
        for (int i = 0; i < questions.size(); i++) {
            int count = 0;
            int correctAnswer = 0;
            System.out.println(i + ". " + questions.get(i));
            count++;

            Scanner answer = new Scanner(System.in);
            System.out.println("Your answer? ");
            String proposedAnswer = answer.nextLine();

            answer.close();

            if (checkAnswer()) {
                correctAnswer++;
            }

        }

        return correctAnswer / (questions.size());
    }

}
