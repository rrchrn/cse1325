import java.util.ArrayList;

public class Quiz {

    private ArrayList<Question> questions = new ArrayList<Question>();

    public Quiz() {
        loadQuiz();
    }

    private void loadQuiz() {
        questions.add(new Question(null, null, 0));
        questions.add(new Question(null, null, 0));
        questions.add(new Question(null, null, 0));
        questions.add(new Question(null, null, 0));

    }

}
