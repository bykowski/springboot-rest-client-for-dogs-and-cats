package pl.bykowski.springbootrestclient.gui;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.bykowski.springbootrestclient.client.CatFactClient;
import pl.bykowski.springbootrestclient.client.CatImageClient;
import pl.bykowski.springbootrestclient.client.DogImageClient;
import pl.bykowski.springbootrestclient.model.AnimalFact;
import pl.bykowski.springbootrestclient.model.AnimalType;

@Route
public class AnimalFactGui extends VerticalLayout {

    @Autowired
    public AnimalFactGui(CatFactClient catFactClient, CatImageClient catImageClient, DogImageClient dogImageClient) {
        ComboBox<AnimalType> comboBox = new ComboBox<>("Select animal", AnimalType.values());
        add(comboBox);

        comboBox.addValueChangeListener(selected -> {
            removeAll();
            add(comboBox);

            Grid<AnimalFact> grid = new Grid<>(AnimalFact.class);
            grid.setItems(catFactClient.getCatFactFormApi(comboBox.getValue()));
            grid.removeColumnByKey("used");
            grid.removeColumnByKey("source");
            grid.removeColumnByKey("type");
            grid.removeColumnByKey("deleted");
            grid.removeColumnByKey("id");
            grid.removeColumnByKey("updatedAt");
            grid.removeColumnByKey("createdAt");
            grid.removeColumnByKey("user");
            grid.removeColumnByKey("v");
            grid.removeColumnByKey("additionalProperties");


            if (comboBox.getValue() == AnimalType.CAT) {
                grid.addColumn(new ComponentRenderer<>(x -> {
                    Image image = new Image(catImageClient.getCatImage(), catImageClient.getCatImage());
                    image.setHeight("200px");
                    return image;
                })).setHeader("Image");
            } else {
                grid.addColumn(new ComponentRenderer<>(x -> {
                    Image image = new Image(dogImageClient.getDogImage(), dogImageClient.getDogImage());
                    image.setHeight("200px");
                    return image;
                })).setHeader("Image");
            }


            grid.setHeight("1000px");

            add(grid);
        });


    }
}
