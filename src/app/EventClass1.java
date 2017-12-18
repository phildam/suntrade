/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.Color;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Tooltip;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author g33k5qu4d
 */
public class EventClass1 {

    TextInputControl tic;
    boolean isSet = false;
    Tooltip tip = new Tooltip();
    StringProperty textFieldStr = new SimpleStringProperty();
    public BooleanProperty draggable = new SimpleBooleanProperty(true);
    static Color color = Color.red;

    public EventClass1(TextInputControl tic) {
        this.tic = tic;
    }

    public EventClass1() {

    }

    public EventHandler<Event> textfieldHandler = (Event event) -> {
        if (tic.getText().length() < 3) {

            tip = new Tooltip("wordLength short");
            tic.setTooltip(tip);
            tic.clear();
            tip.setGraphic(null);
            //tip.setConsumeAutoHidingEvents(true);
        } else {
            textFieldStr.set(tic.getText());
        }
        //event.consume();
    };

    private static final class DragContext {

        public double mouseAnchorX;
        public double mouseAnchorY;
        public double initialTranslateX;
        public double initialTranslateY;
    }

    public void Dragger(Node node) {
        final DragContext dragContext = new DragContext();
        node.addEventFilter(MouseEvent.ANY, (MouseEvent event) -> {
            if (draggable.get()) {
                event.consume();
            }
        });

        node.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent event) -> {
            if (draggable.get()) {
                dragContext.mouseAnchorX = event.getX();
                dragContext.mouseAnchorY = event.getY();
                dragContext.initialTranslateX
                        = node.getTranslateX();
                dragContext.initialTranslateY
                        = node.getTranslateY();
            }
        });

        node.addEventFilter(MouseEvent.MOUSE_DRAGGED, (final MouseEvent mouseEvent) -> {
            if (draggable.get()) {
                // shift node from its initial position by delta
                // calculated from mouse cursor movement
                node.setTranslateX(
                        dragContext.initialTranslateX
                        + mouseEvent.getX()
                        - dragContext.mouseAnchorX);
                node.setTranslateY(
                        dragContext.initialTranslateY
                        + mouseEvent.getY()
                        - dragContext.mouseAnchorY);
            }
        });
       // System.out.println();
    }

    public void addSelectionListener(ChoiceBox choiceBox) {

        SingleSelectionModel smodel = choiceBox.getSelectionModel();
        smodel.selectedIndexProperty().addListener((Observable observable) -> {
            int selectedIndex = smodel.selectedIndexProperty().getValue();
            String item = (String) choiceBox.getItems().get(selectedIndex);
            //System.out.println("selectedIndex" + selectedIndex);
           // System.out.println(item);
        });

    }

    public static void visibleProperty(TextField tField, Label label) {

        EventHandler<InputEvent> filter = (EventHandler) new EventHandler() {

            @Override
            public void handle(Event event) {
                if (event.getEventType() == KeyEvent.KEY_TYPED
                        || event.getEventType() == KeyEvent.KEY_PRESSED) {
                    label.setTextFill(javafx.scene.paint.Color.ROYALBLUE);
                    label.setText("Typing.....");
                    label.setVisible(true);
                } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {

                    label.setVisible(false);
                }

            }
        };
        tField.addEventFilter(KeyEvent.KEY_TYPED, filter);
        tField.addEventFilter(KeyEvent.KEY_PRESSED, filter);
        tField.addEventFilter(KeyEvent.KEY_RELEASED, filter);
    }
}
