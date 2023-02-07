public class Question {

    private static int nextQuestionNumber = 1;
    private final int questionNumber;
    private String question;
    private String[] answers;
    private int rightAnswer;

    public Question(String question, String[] answers, int rightAnswer) {
        Question.nextQuestionNumber = 1;
        this.questionNumber = nextQuestionNumber++;
        this.question = question;
        this.answers = answers;
        this.rightAnswer = rightAnswer;

        if (rightAnswer < 1 || rightAnswer > answers.length) {
            throw new IllegalArgumentException("Right answer is less than 1 or greater than the number of answers");
        }

    }

    public boolean checkAnswer(Integer answer) {
        if (answer.equals(rightAnswer)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        // return questionNumber + ". " + question + "\n" + "1. " + answers[0] + "/n" +
        // "2. " + answers[1] + "/n" + "3. "
        // + answers[2] + "/n" + "4. " + answers[3] + "/n";
        return question + "\n\t" + "1. " + answers[0] + "\n\t" + "2. " + answers[1]
                + "\n\t"
                + "3. "
                + answers[2] + "\n\t" + "4. " + answers[3] + "\n\t";
    }
}
