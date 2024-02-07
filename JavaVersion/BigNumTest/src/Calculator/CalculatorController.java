package Calculator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;


public class CalculatorController {

    private StringBuilder inputBuffer = new StringBuilder();
    private Calc calculator = new Calc();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDivision;

    @FXML
    private Button btnEight;

    @FXML
    private Button btnEqual;

    @FXML
    private Button btnFive;

    @FXML
    private Button btnFour;

    @FXML
    private Button btnMC;

    @FXML
    private Button btnMPlus;

    @FXML
    private Button btnMemRecall;

    @FXML
    private Button btnMemStore;

    @FXML
    private Button btnBackspace;

    @FXML
    private Button btnModule;

    @FXML
    private Button btnMultiply;

    @FXML
    private Button btnNine;

    @FXML
    private Button btnOff;

    @FXML
    private Button btnOne;

    @FXML
    private Button btnSeven;

    @FXML
    private Button btnSix;

    @FXML
    private Button btnSubtract;

    @FXML
    private Button btnSum;

    @FXML
    private Button btnThree;

    @FXML
    private Button btnTwo;

    @FXML
    private Button btnZero;

    @FXML
    private TextArea txtResults;

    private void applyButtonColorAnimation(Button button) {
        Timeline timeline = new Timeline();

        // Define keyframes for color animation
        KeyValue keyValue1 = new KeyValue(button.styleProperty(), "-fx-background-color: linear-gradient(to bottom, #323232, #525252);");
        KeyValue keyValue2 = new KeyValue(button.styleProperty(), "-fx-background-color: linear-gradient(to bottom, #525252, #323232);");

        // Add keyframes to the timeline
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, keyValue1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(500), keyValue2);

        // Set the keyframes to the timeline
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2);

        // Make the animation cycle indefinitely
        timeline.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        timeline.play();
    }
    @FXML
    void btnClearOnClick(ActionEvent event) {
        inputBuffer.setLength(0);
        updateResults();
    }

    @FXML
    void btnDivisionOnClick(ActionEvent event) {
        inputBuffer.append("/");
        updateResults();
    }

    @FXML
    void btnEightOnClick(ActionEvent event) {
        inputBuffer.append("8");
        updateResults();

    }

    @FXML
    void btnEqualOnClick(ActionEvent event) {
        String expression = inputBuffer.toString();
        BigNumber result = calculator.evaluateExpression(expression);

        if (result != null) {
            // Clear the text area
            txtResults.clear();

            // Append the expression and result with appropriate formatting
            StringBuilder newText = new StringBuilder();
            newText.append(expression).append(" = ").append(result.getNumber());

            // Set the updated text to the text area
            txtResults.setText(newText.toString());

            // Update the input buffer
            inputBuffer.setLength(0);
            inputBuffer.append(result.getNumber());
        }
    }

    @FXML
    void btnFiveOnClick(ActionEvent event) {
        inputBuffer.append("5");
        updateResults();
    }

    @FXML
    void btnFourOnClick(ActionEvent event) {
        inputBuffer.append("4");
        updateResults();
    }

    @FXML
    void btnMCOnClick(ActionEvent event) {
        calculator.StoreMemory(new BigNumber("0"));
    }

    @FXML
    void btnMPlusOnClick(ActionEvent event) {
        BigDecimal inputNumber = new BigDecimal(inputBuffer.toString());
        calculator.storeInMemory(calculator.add(calculator.recallMemory(), new BigNumber(inputNumber.toString())));
    }

    @FXML
    void btnMemRecallOnClick(ActionEvent event) {
        inputBuffer.append(calculator.recallMemory().getNumber());
        updateResults();
    }

    @FXML
    void btnMemStoreOnClick(ActionEvent event) {
        BigDecimal inputNumber = new BigDecimal(inputBuffer.toString());
        calculator.StoreMemory(new BigNumber(inputNumber.toString()));
    }
    @FXML
    void btnModuleOnClick(ActionEvent event) {
        inputBuffer.append("%");
        updateResults();
    }

    @FXML
    void btnMultiplyOnClick(ActionEvent event) {
        inputBuffer.append("*");
        updateResults();
    }

    @FXML
    void btnBackspaceOnClick(ActionEvent event) {
        if (inputBuffer.length() > 0) {
            inputBuffer.deleteCharAt(inputBuffer.length() - 1);
            updateResults();
        }
    }


    @FXML
    void btnNineOnClick(ActionEvent event) {
        inputBuffer.append("9");
        updateResults();
    }

    @FXML
    void btnOffOnClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void btnOneOnClick(ActionEvent event) {
        inputBuffer.append("1");
        updateResults();
    }

    @FXML
    void btnSevenOnClick(ActionEvent event) {
        inputBuffer.append("7");
        updateResults();
    }

    @FXML
    void btnSixOnClick(ActionEvent event) {
        inputBuffer.append("6");
        updateResults();
    }

    @FXML
    void btnSubtractOnClick(ActionEvent event) {
        inputBuffer.append("-");
        updateResults();
    }

    @FXML
    void btnSumOnClick(ActionEvent event) {
        inputBuffer.append("+");
        updateResults();
    }

    @FXML
    void btnThreeOnClick(ActionEvent event) {
        inputBuffer.append("3");
        updateResults();
    }

    @FXML
    void btnTwoOnClick(ActionEvent event) {
        inputBuffer.append("2");
        updateResults();
    }

    @FXML
    void btnZeroOnClick(ActionEvent event) {
        inputBuffer.append("0");
        updateResults();
    }

    private void updateResults()
    {
        txtResults.setText(inputBuffer.toString());

        String currentInput = inputBuffer.toString();
        int lastIndex = currentInput.length() - 1;

        if (lastIndex >= 0 && currentInput.charAt(lastIndex) == '-') {
            if (lastIndex == 0 || !Character.isDigit(currentInput.charAt(lastIndex - 1))) {
                return;  // Avoid appending negative sign if it's the first character or after an operator
            }
        }

        txtResults.setText(currentInput);
    }

    private void handleKeyEvent(KeyEvent event, String input) {
        if (input.matches("[0-9+\\-*/]") && event.getCode() == KeyCode.ENTER) {
            int caretPosition = txtResults.getCaretPosition();
            StringBuilder currentText = new StringBuilder(txtResults.getText());
            currentText.insert(caretPosition, input);

            txtResults.setText(currentText.toString());

            // Move the caret to the next position
            txtResults.positionCaret(caretPosition + 1);
        }
    }

    @FXML
    void initialize() {
        assert btnBackspace != null : "fx:id=\"btnBackspace\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnDivision != null : "fx:id=\"btnDivision\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnEight != null : "fx:id=\"btnEight\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnEqual != null : "fx:id=\"btnEqual\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnFive != null : "fx:id=\"btnFive\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnFour != null : "fx:id=\"btnFour\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnMC != null : "fx:id=\"btnMC\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnMPlus != null : "fx:id=\"btnMPlus\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnMemRecall != null : "fx:id=\"btnMemRecall\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnMemStore != null : "fx:id=\"btnMemStore\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnModule != null : "fx:id=\"btnModule\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnMultiply != null : "fx:id=\"btnMultiply\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnNine != null : "fx:id=\"btnNine\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnOff != null : "fx:id=\"btnOff\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnOne != null : "fx:id=\"btnOne\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnSeven != null : "fx:id=\"btnSeven\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnSix != null : "fx:id=\"btnSix\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnSubtract != null : "fx:id=\"btnSubtract\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnSum != null : "fx:id=\"btnSum\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnThree != null : "fx:id=\"btnThree\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnTwo != null : "fx:id=\"btnTwo\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert btnZero != null : "fx:id=\"btnZero\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        assert txtResults != null : "fx:id=\"txtResults\" was not injected: check your FXML file 'CalculatorApp.fxml'.";
        applyButtonColorAnimation(btnZero);
        txtResults.setWrapText(true);

        // Apply animation to buttons
        applyButtonColorAnimation(btnZero);

        // Set action for backspace button
        btnBackspace.setOnAction(event -> btnBackspaceOnClick(event));

        // Request focus for the text area
        txtResults.requestFocus();
    }

}