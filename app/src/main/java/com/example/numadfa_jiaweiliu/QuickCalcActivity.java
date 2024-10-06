package com.example.numadfa_jiaweiliu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Stack;

public class QuickCalcActivity extends AppCompatActivity {

    private TextView calcDisplay;
    private StringBuilder currentInput = new StringBuilder(); // To hold the input expression
    private boolean isResultDisplayed = false;  // To check if the result is currently displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_calc);

        calcDisplay = findViewById(R.id.calcDisplay);

        // Set up button click listeners
        setButtonListeners();
    }

    private void setButtonListeners() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonPlus,
                R.id.buttonMinus, R.id.buttonEquals, R.id.buttonX
        };

        // Loop through all buttons and assign click listeners
        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String buttonText = button.getText().toString();

                    if (buttonText.equals("x")) {
                        // Remove the last character if input exists
                        if (currentInput.length() > 0) {
                            currentInput.deleteCharAt(currentInput.length() - 1);
                        }
                    } else if (buttonText.equals("=")) {
                        // Evaluate the expression
                        String result = evaluateExpression(currentInput.toString());
                        calcDisplay.setText(result);  // Show result
                        isResultDisplayed = true;     // Mark that result is displayed
                    } else {
                        // Append button text to current input
                        if (isResultDisplayed) {
                            // If the result was displayed previously, reset the input
                            currentInput.setLength(0);
                            isResultDisplayed = false;  // Reset result displayed flag
                        }
                        currentInput.append(buttonText);
                    }

                    // Display either the current input or "CALC" if input is empty
                    if (!isResultDisplayed) {
                        calcDisplay.setText(currentInput.toString().isEmpty() ? "CALC" : currentInput.toString());
                    }
                }
            });
        }
    }

    // Evaluate the expression using a simple stack-based approach
    private String evaluateExpression(String expression) {
        try {
            // Use stacks to evaluate the expression
            Stack<Integer> numbers = new Stack<>();
            Stack<Character> operators = new Stack<>();

            int num = 0;
            boolean hasNumber = false;

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if (Character.isDigit(ch)) {
                    // If the character is a digit, build the number
                    num = num * 10 + (ch - '0');
                    hasNumber = true;
                } else {
                    // If an operator is encountered, push the number to the stack
                    if (hasNumber) {
                        numbers.push(num);
                        num = 0;  // Reset the number
                        hasNumber = false;
                    }

                    // Process operators based on precedence
                    if (ch == '+' || ch == '-') {
                        // Resolve all higher precedence operators first
                        while (!operators.isEmpty() && (operators.peek() == '+' || operators.peek() == '-')) {
                            process(numbers, operators.pop());
                        }
                        operators.push(ch);  // Push the current operator
                    }
                }
            }

            // Push the last number if available
            if (hasNumber) {
                numbers.push(num);
            }

            // Process remaining operators
            while (!operators.isEmpty()) {
                process(numbers, operators.pop());
            }

            // The result is the last number remaining in the stack
            return String.valueOf(numbers.pop());

        } catch (Exception e) {
            return "Error";  // Handle any error cases
        }
    }

    // Process two numbers and an operator (+ or -)
    private void process(Stack<Integer> numbers, char operator) {
        int b = numbers.pop();  // Pop second operand
        int a = numbers.pop();  // Pop first operand
        switch (operator) {
            case '+':
                numbers.push(a + b);  // Add the two numbers
                break;
            case '-':
                numbers.push(a - b);  // Subtract the two numbers
                break;
        }
    }
}
