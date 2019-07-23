package pl.bykowski.springbootrestclient.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@StyleSheet("/css/style.css")
@Route("hello")
public class HelloGui extends VerticalLayout {

    public HelloGui() {

        TextField textField = new TextField("Podaj imie:");
        Button buttonHello = new Button("Ok", new Icon(VaadinIcon.COFFEE));
        Label label = new Label();

        label.getStyle().set("font-size", "30px");
        label.getStyle().set("color", "red");

        buttonHello.addClickListener(clint -> {
            label.setText("Hello " + textField.getValue());
        });

        add(textField, buttonHello, label);

    }
}
