public class Quizzer {
    public static void main(String[] args) {

        try {
            Quiz quiz = new Quiz(); // instance of quiz

            System.out.println(quiz.takeQuiz() * 100);

        } catch (Exception e) {
            System.err.println("Error: " + e);
            System.exit(-1);
        }
    }
}