package ru.ddev.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextAnswer = findViewById(R.id.editTextAnswer);
        Button buttonAnswer = findViewById(R.id.buttonAnswer);
        TextView textViewCorrectAnswer = findViewById(R.id.textViewCorrectAnswer);
        TextView textViewIncorrectAnswer = findViewById(R.id.textViewIncorrectAnswer);
        TextView textViewExample = findViewById(R.id.textViewExample);
        TextView textViewScore = findViewById(R.id.textViewScore);

        Example example = new Example(1, 1);

        Score score = new Score(0);

        Random random = new Random();

        textViewExample.setText(getString(R.string.example, example.left.toString(), example.right.toString()));
        textViewScore.setText(getString(R.string.score, score.value.toString()));

        buttonAnswer.setOnClickListener(v -> {
            String text = editTextAnswer.getText().toString();
            try {
                int answer = Integer.parseInt(text);
                if (answer == example.getAnswer()) {
                    textViewCorrectAnswer.setVisibility(VISIBLE);
                    textViewIncorrectAnswer.setVisibility(GONE);
                    example.right = random.nextInt(100);
                    example.left = random.nextInt(100);
                    textViewExample.setText(getString(R.string.example, example.left.toString(), example.right.toString()));
                    score.setValue(score.getValue() + 1);
                    textViewScore.setText(getString(R.string.score, score.value.toString()));
                } else {
                    textViewCorrectAnswer.setVisibility(GONE);
                    textViewIncorrectAnswer.setVisibility(VISIBLE);
                }
            } catch (NumberFormatException e) {

            }
        });

    }
}

class Example {
    Integer left;
    Integer right;

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }

    public Integer getAnswer() {
        return left + right;
    }



    public Example(Integer left, Integer right) {
        this.left = left;
        this.right = right;
    }
}

class Score {
    Integer value;

    public Score(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}