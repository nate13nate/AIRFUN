package mainfiles;

import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
  private GameScene scene;
  private HashMap<String, Integer> inputValues;
  private HashMap<String, String[]> inputKeyCodes;

  public InputHandler(
      HashMap<String, String[]> inputKeyCodes
  ) {
    this.inputKeyCodes = inputKeyCodes; // stores the key codes that activates each input

    // stores for each input whether the input is active (1) or not (0)
    inputValues = new HashMap<String, Integer>();
    for (String inputName : inputKeyCodes.keySet()) {
      inputValues.put(inputName, 0);
    }
  }

  public void start() {
    scene.getScene().setOnKeyPressed(
        (KeyEvent event) -> {
          setCorrespondingInputValue(event.getCode().toString(), 1);
        }
    );

    scene.getScene().setOnKeyReleased(
        (KeyEvent event) -> {
          setCorrespondingInputValue(event.getCode().toString(), 0);
        }
    );
  }

  public void loop(double deltaTime) {

  }

  private void setCorrespondingInputValue(String inputtedKeyCode, int inputValue) {
    for (Map.Entry<String, String[]> keyCodesForOneInput : inputKeyCodes.entrySet()) {
      String[] keyCodes = keyCodesForOneInput.getValue();
      for (String keyCode : keyCodes) {
        if (keyCode == inputtedKeyCode) inputValues.put(keyCodesForOneInput.getKey(), inputValue);
      }
    }
  }

  public void setScene(GameScene scene) {
    this.scene = scene;
  }

  public int getInputValue(String key) {
    if (!inputValues.containsKey(key)) return -1;
    return inputValues.get(key);
  }
}
