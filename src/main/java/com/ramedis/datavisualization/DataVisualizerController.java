package com.ramedis.datavisualization;


import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.mfxcore.base.beans.range.IntegerRange;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class DataVisualizerController {
    @FXML
    private BorderPane totalScreen;
    @FXML
    private MFXComboBox<String> categoryCmb;
    @FXML
    private MFXComboBox<String> implementCmb;
    @FXML
    private MFXButton themeBtn;
    @FXML
    private Label capacityLabel;
    @FXML
    private MFXTextField sizeField;
    @FXML
    private MFXButton applyBtn;
    @FXML
    private MFXComboBox<String> dataTypeCmb;
    @FXML
    private MFXComboBox<String> keyTypeCmb;
    @FXML
    private MFXComboBox<String> valueTypeCmb;
    @FXML
    private HBox keyValueTypeCmb;
    @FXML
    private VBox operationPanelCard;
    @FXML
    private MFXTextField inpField;
    @FXML
    private VBox operationPanelCard2;
    @FXML
    private MFXTextField keyField;
    @FXML
    private MFXTextField valueField;
    @FXML
    private VBox operationPanelCard3;
    @FXML
    private MFXTextField setInpField;
    @FXML
    private MFXScrollPane scrollPane;
    @FXML
    private HBox visualizationPanelCard;
    @FXML
    private VBox logEventPanel;
    @FXML
    private MFXScrollPane logEventScrollPanel;
    @FXML
    private VBox logEventsField;
    @FXML
    private FontIcon themeLogoColor;
    @FXML
    private MFXButton resetBtn;
    @FXML
    private MFXButton listAddButton;
    @FXML
    private MFXButton listRemoveButton;
    @FXML
    private MFXButton listSearchButton;
    @FXML
    private MFXButton listClearButton;
    @FXML
    private MFXButton setAddButton;
    @FXML
    private MFXButton setSearchButton;
    @FXML
    private MFXButton setRemoveButton;
    @FXML
    private MFXButton setClearButton;
    @FXML
    private MFXButton mapAddButton;
    @FXML
    private MFXButton mapSearchButton;
    @FXML
    private MFXButton mapRemoveButton;
    @FXML
    private MFXButton mapClearButton;
    @FXML
    private VBox linkedListOperationPanelCard;
    @FXML
    private MFXTextField linkedListInpField;
    @FXML
    private MFXButton linkedListAddButton;
    @FXML
    private MFXButton linkedListRemoveButton;
    @FXML
    private MFXButton linkedListSearchButton;
    @FXML
    private MFXButton linkedListClearButton;
    @FXML
    private VBox treeSetOperationPanelCard;
    @FXML
    private MFXTextField treeSetInpField;
    @FXML
    private MFXButton treeSetAddButton;
    @FXML
    private MFXButton treeSetRemoveButton;
    @FXML
    private MFXButton treeSetSearchButton;
    @FXML
    private MFXButton treeSetClearButton;
    @FXML
    private MFXButton logClearBtn;
    private String selectedKeyType;
    private String selectedValueType;
    private String selectedDataType;
//    private ArrayList<String> list = new ArrayList<>();
    private ArrayListModel<String> arrayListModel;
    private LinkedListModel<String> linkedListModel;
    private HashSetModel<Object> hashSetModel;
    private HashMapModel<Object, Object> hashMapModel;
    private TreeSetModel<String> treeSetStringModel;
    private TreeSetModel<Integer> treeSetIntegerModel;

    private TreeSetModel<String> getStringTreeSet() {
        return treeSetStringModel;
    }

    private TreeSetModel<Integer> getIntegerTreeSet() {
        return treeSetIntegerModel;
    }

    private final ArrayList<Label> valueLabels = new ArrayList<>();
//    private Map<Object, Object> map = new HashMap<>();
//    private HashSet<String> hashSet = new HashSet<>();
    private int capacity = 10;
    private boolean darkMode = true;
    private Map<String, List<String>> implement = new HashMap<>();
//    private LinkedList<String> linkedList = new LinkedList<>();
    private final List<Label> linkedNodeLabels = new ArrayList<>();
//    private TreeSet<Object> treeSet= new TreeSet<>();
    private Object searchedValue = null;
    private final List<Label> hashSetValueLabels = new ArrayList<>();
    private final List<Label> hashMapLabels = new ArrayList<>();
    private final Map<Object,Label> hashMapKeyLabels = new LinkedHashMap<>();

    private Timeline hashMapSearchTimeline;
    private ScaleTransition hashMapScaleTransition;
    public void initialize() {

        arrayListModel=new ArrayListModel<>();
        linkedListModel = new LinkedListModel<>();
        hashSetModel = new HashSetModel<>();
        hashMapModel = new HashMapModel<>();
        treeSetStringModel = new TreeSetModel<>();
        treeSetIntegerModel = new TreeSetModel<>();
        implement.put("List", List.of("ArrayList", "LinkedList"));
        implement.put("Set", List.of("HashSet", "TreeSet"));
        implement.put("Map", List.of("HashMap", "TreeMap"));
        categoryCmb.getItems().addAll("None", "List", "Set", "Map");
        dataTypeCmb.getItems().addAll("String", "Integer");
        keyTypeCmb.getItems().addAll("String", "Integer");
        valueTypeCmb.getItems().addAll("String", "Integer");
        visualizationPanelCard.setAlignment(Pos.CENTER_LEFT);
    }

    private Object convertValue(String value, String type) {
        switch (type) {
            case "Integer":
                return Integer.parseInt(value);
            case "String":
                return value;
            default:
                return value;
        }
    }

    @FXML
    private void refreshArrayListVisualization() {

        if (valueLabels.size() < capacity) {
            for (int i = valueLabels.size(); i < capacity; i++) {
                Label indexCell = new Label(String.valueOf(i));
                indexCell.setAlignment(Pos.CENTER);
                indexCell.getStyleClass().add("listIndexCell");


                Label valueCell = new Label();
                valueCell.setPrefSize(100, 40);
                valueCell.setAlignment(Pos.CENTER);
                valueCell.getStyleClass().add("listValueCell");

                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.getChildren().addAll(indexCell, valueCell);
                visualizationPanelCard.getChildren().add(vbox);

                valueLabels.add(valueCell);
            }
        }
        for (int i = 0; i < capacity; i++) {
//            valueLabels.get(i).getStyleClass().remove("listValueLabel");
            if (i < arrayListModel.size()) {
                valueLabels.get(i).setText(arrayListModel.get(i).toString());

            } else {
                valueLabels.get(i).setText("");
            }
        }
    }

    @FXML
    private void refreshHashMapVisualization() {
        visualizationPanelCard.getChildren().clear();
        hashMapLabels.clear();
        hashMapKeyLabels.clear();

        for (Map.Entry<Object, Object> entry : hashMapModel.getValues().entrySet()) {
            VBox box = new VBox(5);
            box.setAlignment(Pos.CENTER);
            box.setPrefWidth(180);

            Label keyLabel = new Label("Key: "+entry.getKey());
            keyLabel.setPrefSize(180,40);
            keyLabel.setAlignment(Pos.CENTER);
            keyLabel.getStyleClass().add("hashMapKeyCell");

            Label valueLabel = new Label("Value: "+entry.getValue());
            valueLabel.setPrefSize(180,40);
            valueLabel.setAlignment(Pos.CENTER);
            valueLabel.getStyleClass().add("hashMapValueCell");

            hashMapLabels.add(keyLabel);
            hashMapKeyLabels.put(entry.getKey(),keyLabel);

            box.getChildren().addAll(keyLabel,valueLabel);
            visualizationPanelCard.getChildren().add(box);
//            mapLabel.setTextFill(Color.BLUE);
//            mapLabel.setPrefSize(200, 50);
//            mapLabel.setAlignment(Pos.CENTER);
//            mapLabel.getStyleClass().add("mapLabel");
//
//            visualizationPanelCard.getChildren().add(mapLabel);
        }
    }

    @FXML
    private void refreshHashSetVisualization() {
        visualizationPanelCard.getChildren().clear();
        hashSetValueLabels.clear();
        List<Object> values = new ArrayList<>(hashSetModel.getValues());
        for (int i = 0; i < capacity; i++) {
            Label indexCell = new Label(String.valueOf(i));
            indexCell.setAlignment(Pos.CENTER);
            indexCell.getStyleClass().add("setIndexCell");

            Label valueCell = new Label();
            valueCell.setPrefSize(100, 40);
            valueCell.setAlignment(Pos.CENTER);
            valueCell.getStyleClass().add("setValueCell");

            if (i < values.size()) {
                valueCell.setText(String.valueOf(values.get(i)));
                hashSetValueLabels.add(valueCell);
            }

            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(indexCell, valueCell);
            visualizationPanelCard.getChildren().add(box);
        }
    }

    private void refreshTreeSetVisualization(){
        visualizationPanelCard.getChildren().clear();
        List<?> values;
        if ("Integer".equals(selectedDataType)) {

            values = new ArrayList<>(treeSetIntegerModel.getValues());

        } else {

            values = new ArrayList<>(treeSetStringModel.getValues());
        }
        for (int i = 0; i < capacity; i++) {
            Label indexCell = new Label(String.valueOf(i));
            indexCell.setAlignment(Pos.CENTER);
            indexCell.getStyleClass().add("setIndexCell");

            Label valueCell = new Label();
            valueCell.setPrefSize(100, 40);
            valueCell.setAlignment(Pos.CENTER);
            valueCell.getStyleClass().add("setValueCell");

            if (i < values.size()) {
                Object currentValue = values.get(i);
                valueCell.setText(String.valueOf(currentValue));
                if(searchedValue != null && currentValue.equals(searchedValue)){
                    valueCell.getStyleClass().add("listValueLabel");
                };
            }

            VBox box = new VBox(5);
            box.setAlignment(Pos.CENTER);
            box.getChildren().addAll(indexCell, valueCell);
            visualizationPanelCard.getChildren().add(box);
        }
    }

    //  @FXML
//  public void categorySelectionClick(ActionEvent actionEvent) {
//    if(categoryCmb.getSelectionModel().getSelectedIndex()!=0){
//      addLog("Event triggered");
//      implementCmb.setDisable(false);
//
//      String category = categoryCmb.getValue();
//      if(category== null || category.equals("None")){
//        implementCmb.clearSelection();
//        implementCmb.getItems().clear();
//        implementCmb.setDisable(true);
//        return;
//      }
////      implementCmb.getItems().addAll(
////              implement.get(category)
////      );
//    } else if (categoryCmb.getSelectionModel().getSelectedIndex()==0) {
//      implementCmb.setDisable(true);
//    }else{
//      addLog("Error Found at 96");
//      System.out.println("Error");
//    }
//  }
    @FXML
    public void categorySelectionClick(ActionEvent event) {

        String category = categoryCmb.getValue();
        addLog(category + " Category is selected ");
        if (category == null || category.equals("None")) {
            implementCmb.clearSelection();
            implementCmb.getItems().clear();
            implementCmb.setDisable(true);
            return;
        }

        implementCmb.setDisable(false);

        implementCmb.clearSelection();
        implementCmb.getItems().clear();
        implementCmb.getItems().addAll(implement.get(category));
    }

    public void implementationSelectedCombo(ActionEvent actionEvent) {

        addLog(implementCmb.getValue() + " is implemented");


        String selected = implementCmb.getValue();
        if (selected == null) {
            return;
        }
        switch (selected) {
            case "ArrayList":
                configureArrayListUI();
                addLog("Configuring ArrayList UI");
                break;
            case "LinkedList":
                configureLinkedListUI();
                addLog("Configuring LinkedList UI");
                break;
            case "HashSet":
                configureSetListUI();
                addLog("Configuring HashSet UI");
                break;
            case "TreeSet":
                configureTreeSetUI();
                addLog("Configuring TreeSet UI");
                break;
            case "HashMap":
                configureMapListUI();
                addLog("Configuring HashMap UI");
                break;
            case "TreeMap":
                addLog("Configuring TreeMap UI");
                break;

        }
    }

    public void applyBtn(ActionEvent actionEvent) {
        String implementation = implementCmb.getValue();
        if (implementation == null) {
            addErrorLog("Please select an Implementation");
            return;
        }
        switch (implementation) {
            case "ArrayList":
                String capacityText = sizeField.getText().trim();

                if (capacityText.isEmpty()) {
                    addErrorLog("Please Enter a valid capacity");
                    return;
                }
                if(selectedDataType==null){
                    addErrorLog("Please Select a data type.");
                    return;
                }
                try {
                    capacity = Integer.parseInt(sizeField.getText());
                    arrayListModel.clear();
                    valueLabels.clear();
                    visualizationPanelCard.getChildren().clear();
                    refreshArrayListVisualization();
//                    addLog("ArrayList visualisation initialized with capacity of "+capacity);
                } catch (NumberFormatException e) {
                    addErrorLog("Please Enter a valid capacity");
                }
                break;

            case "LinkedList":

                if (selectedDataType == null) {
                    addErrorLog("Please select a data type.");
                    return;
                }

                linkedListModel.clear();
                visualizationPanelCard.getChildren().clear();
                refreshLinkedList();
                addLog("Linkedlist visualization appeared");
                break;

            case "HashSet":
                String capacityHashSet = sizeField.getText().trim();
                if(capacityHashSet.isEmpty()){
                    addErrorLog("Please Enter a valid capacity.");
                    return;
                }
                if(selectedDataType==null){
                    addErrorLog("Please Select a datatype");
                    return;
                }
                try {
                    capacity = Integer.parseInt(capacityHashSet);
                    if(capacity<=0){
                        addErrorLog("Capacity must be greater than 0");
                        return;
                    }
                    hashSetModel.clear();
                    visualizationPanelCard.getChildren().clear();

                    refreshHashSetVisualization();

                    addLog("HashSet visualization initialized with capacity " + capacity);

                } catch (NumberFormatException e) {
                    addErrorLog("Please enter a valid capacity.");
                }
                break;
            case "TreeSet":
                String capacityTreeSet = sizeField.getText().trim();

                if(capacityTreeSet.isEmpty()){
                    addErrorLog("Please Enter a valid capacity");
                    return;
                }
                if(selectedDataType==null){
                    addErrorLog("Please Select a data type.");
                    return;
                }
                try {
                    capacity=Integer.parseInt(sizeField.getText());
                    if(capacity<=0){
                        addErrorLog("Capacity must be greater than 0.");
                        return;
                    }
                    treeSetStringModel.clear();
                    treeSetIntegerModel.clear();
                    searchedValue=null;
                    visualizationPanelCard.getChildren().clear();
                    refreshTreeSetVisualization();
                    addLog("TreeSet visualization initilized with capacity of "+capacity);
                }catch (NumberFormatException e){
                    addErrorLog("Please enter a valid capacity");
                }
                break;
            case "HashMap":
                String hashMapCapacityText = sizeField.getText().trim();

                if(hashMapCapacityText.isEmpty()){
                    addErrorLog("Please enter a valid capacity.");
                    return;
                }
                if(selectedKeyType == null){
                    addErrorLog("Please select a key data type.");
                    return;
                }
                if(selectedValueType == null){
                    addErrorLog("Please select a value data type.");
                    return;
                }
                try {
                    capacity = Integer.parseInt(hashMapCapacityText);
                    if (capacity <= 0){
                        addErrorLog("Capacity must be greater than 0.");
                        return;
                    }

                    hashMapModel.clear();
                    visualizationPanelCard.getChildren().clear();
                    hashMapLabels.clear();
                    refreshHashMapVisualization();

                    addLog("HashMap visualization initialized with capacity of "+capacity);

                } catch (NumberFormatException e) {
                    addErrorLog("Please enter a valid capacity.");
                }
                break;

            default:
                addErrorLog("Unsupported implementation.");
        }
    }

    //    try{
//      capacity = Integer.parseInt(sizeField.getText());
//      addLog("the size value is "+capacity);
//      list.clear();
//      valueLabels.clear();
//      visualizationPanelCard.getChildren().clear();
//      refreshArrayListVisualization();
//    }catch (NumberFormatException e){
//      addErrorLog("Please Enter a valid Size");
//    }
//  }
//  public void clearBtn(ActionEvent actionEvent) {
//    list.clear();
//    visualizationPanelCard.getChildren().clear();
//    inpField.clear();
//    sizeField.clear();
//  }
//  public void removeBtn(ActionEvent actionEvent) {
//    String value = inpField.getText();
//    if(list.remove(value)){
//      refreshVisualization();
//    }else{
//      addErrorLog("Value not found.");
//    }
//    inpField.clear();
//  }




    private void configureArrayListUI() {
        operationPanelCard.setVisible(true);
        linkedListOperationPanelCard.setVisible(false);
        operationPanelCard2.setVisible(false);
        treeSetOperationPanelCard.setVisible(false);
        operationPanelCard3.setVisible(false);
        dataTypeCmb.setVisible(true);
        keyValueTypeCmb.setVisible(false);
        visualizationPanelCard.getChildren().clear();
        capacityLabel.setVisible(true);
        sizeField.setVisible(true);
        dataTypeCmb.clearSelection();
        selectedDataType=null;
        arrayListModel.clear();
        inpField.clear();
        sizeField.clear();
        capacity = 0;
        valueLabels.clear();
    }

    private void configureLinkedListUI() {
        operationPanelCard.setVisible(false);
        linkedListOperationPanelCard.setVisible(true);
        operationPanelCard2.setVisible(false);
        treeSetOperationPanelCard.setVisible(false);
        operationPanelCard3.setVisible(false);
        dataTypeCmb.setVisible(true);
        keyValueTypeCmb.setVisible(false);
        visualizationPanelCard.getChildren().clear();
        capacityLabel.setVisible(false);
        sizeField.setVisible(false);
        dataTypeCmb.clear();
        linkedListModel.clear();
        inpField.clear();
        sizeField.clear();
        capacity = 0;
    }

    private void configureMapListUI() {
        operationPanelCard.setVisible(false);
        linkedListOperationPanelCard.setVisible(false);
        treeSetOperationPanelCard.setVisible(false);

        operationPanelCard2.setVisible(true);
        operationPanelCard3.setVisible(false);

        capacityLabel.setVisible(true);
        sizeField.setVisible(true);

        dataTypeCmb.setVisible(false);
        dataTypeCmb.clearSelection();
        selectedDataType = null;

        keyValueTypeCmb.setVisible(true);

        keyTypeCmb.clearSelection();
        valueTypeCmb.clearSelection();

        selectedKeyType = null;
        selectedValueType = null;

        visualizationPanelCard.getChildren().clear();

        hashMapModel.clear();
        hashMapLabels.clear();

        sizeField.clear();
        keyField.clear();
        valueField.clear();

        capacity = 0;
    }

    private void configureSetListUI() {
        operationPanelCard.setVisible(false);
        linkedListOperationPanelCard.setVisible(false);
        operationPanelCard2.setVisible(false);
        operationPanelCard3.setVisible(true);
        treeSetOperationPanelCard.setVisible(false);
        dataTypeCmb.setVisible(true);
        dataTypeCmb.clear();
        capacityLabel.setVisible(true);
        sizeField.setVisible(true);
        keyValueTypeCmb.setVisible(false);
        visualizationPanelCard.getChildren().clear();
        arrayListModel.clear();
        inpField.clear();
        sizeField.clear();
        capacity = 0;
    }

    private void configureTreeSetUI(){
        operationPanelCard.setVisible(false);
        linkedListOperationPanelCard.setVisible(false);
        operationPanelCard2.setVisible(false);
        operationPanelCard3.setVisible(false);
        treeSetOperationPanelCard.setVisible(true);
        dataTypeCmb.setVisible(true);
        dataTypeCmb.clearSelection();
        selectedDataType=null;
        capacityLabel.setVisible(true);
        sizeField.setVisible(true);
        keyValueTypeCmb.setVisible(false);
        visualizationPanelCard.getChildren().clear();
        arrayListModel.clear();
        inpField.clear();
        sizeField.clear();
        capacity=0;
    }

    //@FXML
//  public void searchBtn(ActionEvent actionEvent) {
//    String value = inpField.getText();
//    for(int i=0; i<visualizationPanelCard.getChildren().size(); i++){
//      VBox bx = (VBox) visualizationPanelCard.getChildren().get(i);
//      Label valueLabel = (Label) bx.getChildren().get(1);
//      valueLabel.setStyle("-fx-font-size: 20;-fx-border-color: blue;-fx-border-radius: 10; -fx-border-style: dotted; -fx-border-width: 2; -fx-text-fill: blue");
//    }
//    for(int i=0; i<list.size(); i++){
//      if(list.get(i).equals(value)){
//        VBox box = (VBox) visualizationPanelCard.getChildren().get(i);
//        Label valueLabel = (Label) box.getChildren().get(1);
//        valueLabel.setStyle("-fx-font-size: 20; -fx-border-color: blue; -fx-border-radius: 10; -fx-border-width: 2; -fx-background-color: white; -fx-text-fill: blue; ");
//        break;
//      }
//      inpField.clear();
//    }
//  }
    public void changeBgBtn(ActionEvent actionEvent) {
        if (darkMode) {
            applyDarkMode();
            darkMode = false;
        } else {
            applyLightMode();
            darkMode = true;
        }
    }


    @FXML
    public void dataTypeSelectionClick(ActionEvent actionEvent) {
        selectedDataType = dataTypeCmb.getValue();
        addLog("Selected Data type : " + selectedDataType);
    }

    public void keyTypeSelectionClick(ActionEvent actionEvent) {
        selectedKeyType = keyTypeCmb.getValue();
    }

    public void valueTypeSelectionClick(ActionEvent actionEvent) {
        selectedValueType = valueTypeCmb.getValue();
    }
//@FXML
//  public void onAdd(ActionEvent actionEvent) {
//      String value = inpField.getText();
////  System.out.println(sizeField.getStyleClass());
//      if(!isValidDataType(value)){
//        addErrorLog("Please Enter a "+selectedDataType+ " value.");
//        return;
//      }
//      if(value.isBlank()){
//        return;
//      }
//      if(list.size() == capacity){
//        capacity = capacity+capacity/2;
//        addLog("Array Capacity is increased by : "+capacity);
//      }
//      list.add(value);
//      refreshVisualization();
//      inpField.clear();
//  }


    public void onSetAdd(ActionEvent actionEvent) {
        String value = setInpField.getText().trim();
        if (!isValidDataType(value)) {
            addErrorLog("Please Enter a " + selectedDataType + " value.");
            return;
        }
        if(selectedDataType==null){
            addErrorLog("Please select a data type.");
        }
        if (value.isBlank()) {
            addErrorLog("Please Enter a Value.");
            return;
        }
        Object convertedValue = convertValue(value,selectedDataType);
        if (hashSetModel.size() >= capacity) {
            capacity = capacity + Math.max(1, capacity / 2);
            addLog("Set capacity is increased by : " + capacity);
            refreshHashSetVisualization();
        }
        if (hashSetModel.add(convertedValue)) {
            addLog("Added : " + convertedValue);
            refreshHashSetVisualization();
        } else {
            addErrorLog("Duplicate Element is not allowed");
            addLog("Here the duplicate element is : " + convertedValue);
        }
        setInpField.clear();
    }

    public void removeSetBtn(ActionEvent actionEvent) {
        String value = setInpField.getText();
        if(value.isBlank()){
            addErrorLog("Please Enter a value to remove.");
            return;
        }
        Object removedValue = convertValue(value,selectedDataType);
        if (hashSetModel.remove(removedValue)) {
            addLog("Removed : " + removedValue);
            refreshHashSetVisualization();
        } else {
            addErrorLog(removedValue+" is not found");
        }
        setInpField.clear();
    }

    public void searchSetBtn(ActionEvent actionEvent) {
        String value = setInpField.getText().trim();
        if(value.isBlank()){
            addErrorLog("Please Enter a value to search.");
            return;
        }
        if(!isValidDataType(value)){
            addErrorLog("Please Enter a "+selectedDataType+" value.");
            return;
        }
        Object searchValue = convertValue(value, selectedDataType);
        for (Label label : hashSetValueLabels){
            label.getStyleClass().removeAll("presentNode","finalNode");
        }
        List<Object> values = new ArrayList<>(hashSetModel.getValues());

        Timeline timeline = new Timeline();

        for(int i=0; i< values.size(); i++){
            final int index =i;
            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds((i+1)*0.7),
                    event ->{
                        for(Label label:hashSetValueLabels){
                            label.getStyleClass().remove("presentNode");
                        }
                        //safety check
                        if(index >= hashSetValueLabels.size()){
                            return;
                        }
                        Label presentLabel = hashSetValueLabels.get(index);
                        Object presentValue = values.get(index);

                        if(presentValue.equals(searchValue)){
                            presentLabel.getStyleClass().add("finalNode");
                            addLog(searchValue+ " is found.");
                            timeline.stop();
                        }else{
                            presentLabel.getStyleClass().add("presentNode");
                        }
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setOnFinished(event ->{
            if(!hashSetModel.contains(searchValue)){
                addErrorLog(searchValue+" is not found.");
            }
        });
        timeline.play();
        setInpField.clear();

//        if (hashSetModel.contains(searchValue)) {
//            addLog("Found : " + searchValue);
//        } else {
//            addErrorLog(searchValue + " not found");
//        }
//        setInpField.clear();
    }

    public void clearSetBtn(ActionEvent actionEvent) {
        visualizationPanelCard.getChildren().clear();
        hashSetModel.clear();
        addLog("Hashset cleared");
    }

    private void clearHashMapSearchStyles(){
        for(Label label : hashMapLabels){
            label.getStyleClass().removeAll("presentHashMapNode","foundHashMapNode");
            label.setScaleX(1);
            label.setScaleY(1);
        }
    }

    private void playHashMapSearchAnimation(Label label){
        if(hashMapScaleTransition != null){
            hashMapScaleTransition.stop();
        }

        hashMapScaleTransition = new ScaleTransition(
                Duration.millis(250),label
        );

        hashMapScaleTransition.setFromX(1.0);
        hashMapScaleTransition.setFromY(1.0);

        hashMapScaleTransition.setToX(1.15);
        hashMapScaleTransition.setToY(1.15);

        hashMapScaleTransition.setAutoReverse(true);
        hashMapScaleTransition.setCycleCount(2);

        hashMapScaleTransition.play();
    }

    public void putBtn(ActionEvent actionEvent) {
        String keyText = keyField.getText().trim();
        String valueText = valueField.getText().trim();
        if(selectedKeyType == null){
            addErrorLog("Please Select a key data type");
            return;
        }
        if(selectedValueType == null){
            addErrorLog("Please Select a value data type.");
            return;
        }
        if(keyText.isBlank()){
            addErrorLog("Please Enter a key.");
            return;
        }
        if(valueText.isBlank()){
            addErrorLog("Please Enter a value.");
            return;
        }

        try {
            Object key = convertValue(keyText, selectedKeyType);
            addLog(key + " is entered");
            Object value = convertValue(valueText, selectedValueType);
            addLog(value + " is entered");
            if(hashMapModel.containsKey(key)){
                Object oldValue = hashMapModel.get(key);
                hashMapModel.put(key,value);
                addLog("Key "+key+" already exists. Value changed from "+oldValue+" to "+value);
            }else {
                hashMapModel.put(key,value);
                addLog("Added Key : "+key+" | Value: "+value);
            }
            refreshHashMapVisualization();
        }catch (NumberFormatException e){
            addErrorLog("Invalid key/value data type.");
        }catch (Exception e) {
            addLog("Unable to add key-value pair.");
        }
        keyField.clear();
        valueField.clear();
    }

    public void mapRemoveBtn(ActionEvent actionEvent) {
        String keyText = keyField.getText().trim();
        if (keyText.isBlank()) {
            addErrorLog("Please enter a key to remove.");
            return;
        }
        if(selectedKeyType==null){
            addErrorLog("Please Select a key data type.");
            return;
        }

        try {
            Object key = convertValue(keyText, selectedKeyType);
            if (!hashMapModel.containsKey(key)) {
                addErrorLog("Key " + key + " is not found.");
                keyField.clear();
                return;
            }
            Object value = hashMapModel.get(key);
            hashMapModel.removeKey(key);
            addLog("Removed Key: "+key+" | Value: "+value);
            refreshHashMapVisualization();
        }catch(Exception e){
            addErrorLog("Invalid Key.");
        }
        keyField.clear();
    }

    public void mapSearchBtn(ActionEvent actionEvent) {
        String keyText = keyField.getText().trim();
        if (keyText.isEmpty()) {
            addErrorLog("Please enter a key to search.");
            return;
        }
        if(selectedKeyType==null){
            addErrorLog("Please select a key data type.");
            return;
        }

        Object searchKey;
        try{
            searchKey = convertValue(keyText,selectedKeyType);
        }catch (NumberFormatException e){
            addErrorLog("Please enter a valid "+selectedKeyType+" key.");
            keyField.clear();
            return;
        }
        if(hashMapSearchTimeline != null){
            hashMapSearchTimeline.stop();
        }

        if(hashMapScaleTransition != null){
            hashMapScaleTransition.stop();
        }

        List<Map.Entry<Object, Object>> entries = new ArrayList<>(hashMapModel.getValues().entrySet());
        if(entries.isEmpty()){
            addErrorLog("HashMap is empty.");
            keyField.clear();
            return;
        }

        clearHashMapSearchStyles();

        final int[] currentIndex = {0};
        

        hashMapSearchTimeline = new Timeline(
                new KeyFrame(Duration.millis(700),
                        event -> {
                    if(currentIndex[0] >= entries.size()){
                        hashMapSearchTimeline.stop();
                        addErrorLog("Key "+searchKey+" is not found.");
                        return;
                    }

                    for(Label label :hashMapLabels){
                        label.getStyleClass().remove("presentHashMapNode");
                    }

                    Map.Entry<Object,Object> entry = entries.get(currentIndex[0]);
                    Object currentKey = entry.getKey();
                    Object currentValue = entry.getValue();

                    Label currentKeyLabel = hashMapKeyLabels.get(currentKey);
                    if(currentKeyLabel == null){
                        currentIndex[0]++;
                        return;
                    }

                    if(Objects.equals(currentKey,searchKey)){
                        if(hashMapScaleTransition != null){
                            hashMapScaleTransition.stop();
                        }
                        currentKeyLabel.getStyleClass().remove("presentHashMapNode");
                        if(!currentKeyLabel.getStyleClass().contains("foundHashMapNode")) {
                            currentKeyLabel.getStyleClass().add("foundHashMapNode");
                        }

                        currentKeyLabel.setScaleX(1);
                        currentKeyLabel.setScaleY(1);

                        currentKeyLabel.applyCss();
//                        playHashMapSearchAnimation(currentKeyLabel);
                        addLog("Key = "+currentKey+" found. Value = "+currentValue);
                        hashMapSearchTimeline.stop();
                        return;
                    }
                    currentKeyLabel.getStyleClass().add("presentHashMapNode");
                    playHashMapSearchAnimation(currentKeyLabel);
                    addLog("Searching...checking key = "+currentKey);
                    currentIndex[0]++;
                        }
                )
        );
        hashMapSearchTimeline.setCycleCount(Timeline.INDEFINITE);
        hashMapSearchTimeline.playFromStart();
        keyField.clear();

//        for(Label label: hashMapLabels){
//            label.getStyleClass().removeAll("presentHashMapNode","foundHashMapNode");
//        }
//
//
//        hashMapSearchTimeline = new Timeline();
//
//        final boolean[] found = {false};
//
//        for(int i=0; i<entries.size(); i++){
//            final int index = i;
//            KeyFrame keyFrame = new KeyFrame(Duration.seconds((i+1)*0.7),
//                    event ->{
//                        for(Label label : hashMapLabels){
//                            label.getStyleClass().remove("presentHashMapNode");
//                        }
//                        if(index >= hashMapLabels.size()){
//                            return;
//                        }
//
//                        Map.Entry<Object,Object> entry = entries.get(index);
//                        Object currentKey = entry.getKey();
//                        Object currentValue = entry.getValue();
//                        Label currentKeyLabel = hashMapLabels.get(index);
//
//                        if(Objects.equals(currentKey,searchKey)){
//                            found[0] = true;
//                            currentKeyLabel.getStyleClass().remove("presentHashMapNode");
//                            currentKeyLabel.getStyleClass().add("foundHashMapNode");
//                            addLog("Key = "+searchKey+" found. Value = "+currentValue);
//                        }else {
//                            currentKeyLabel.getStyleClass().add("presentHashMapNode");
//                            addLog("Searching... checking key = "+currentKey);
//                        }
//                    });
//            hashMapSearchTimeline.getKeyFrames().add(keyFrame);
//        }
//        hashMapSearchTimeline.setOnFinished(event ->{
//            if(!found[0]){
//                addErrorLog("Key "+searchKey+" is not found.");
//            }
//
//            for(Label label: hashMapLabels){
//                label.getStyleClass().remove("presentHashMapNode");
//            }
//        });
//        hashMapSearchTimeline.playFromStart();
//        keyField.clear();
    }

    public void mapClearBtn(ActionEvent actionEvent) {
        visualizationPanelCard.getChildren().clear();
        hashMapModel.clear();
        addLog("HashMap cleared.");
    }


    private void addLog(String message) {
        Label log = new Label(message);
        log.setStyle("-fx-font-size: 20; -fx-text-fill: green;");
        logEventsField.getChildren().add(log);
    }


    private boolean isValidDataType(String value) {
        if(selectedDataType==null){
            return false;
        }
        switch (selectedDataType) {
            case "String":

                return true;
            case "Integer":
                try {
                    Integer.parseInt(value);

                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            default:
                return false;
        }
    }

    private void applyLightMode() {
        totalScreen.setStyle("-fx-background-color: white");
        categoryCmb.setStyle("-fx-text-fill: #000000; -fx-background-color: #aaaaaa;-fx-border-color: #aaaaaa");
        implementCmb.setStyle("-fx-text-fill: #000000; -fx-background-color: #aaaaaa;-fx-border-color: #aaaaaa");
        resetBtn.setStyle("-fx-text-fill: #000000; -fx-background-color: #aaaaaa;");
        themeBtn.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        applyBtn.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        linkedListAddButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        linkedListSearchButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        linkedListRemoveButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        linkedListClearButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        listAddButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        listSearchButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        listRemoveButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        listClearButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        setAddButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        setSearchButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        setRemoveButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        setClearButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        mapAddButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        mapSearchButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        mapRemoveButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        mapClearButton.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
        logClearBtn.setStyle("-fx-text-fill: #000000; -fx-background-color: #aaaaaa");
    }

    private void applyDarkMode() {
        totalScreen.setStyle("-fx-background-color: #000000; ");
        categoryCmb.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #2a2a2a;-fx-border-color: #2a2a2a;");
        implementCmb.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #2a2a2a;-fx-border-color: #2a2a2a;");
        themeBtn.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #2a2a2a");
        applyBtn.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #2a2a2a");
        resetBtn.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        linkedListAddButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        linkedListSearchButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        linkedListRemoveButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        linkedListClearButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        listAddButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        listSearchButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        listRemoveButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        listClearButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        setAddButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        setSearchButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        setRemoveButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        setClearButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        mapAddButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        mapSearchButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        mapRemoveButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        mapClearButton.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
        logClearBtn.setStyle("-fx-text-fill: #ffffff;");
    }


    public void resetBtn(ActionEvent actionEvent) {
        categoryCmb.clearSelection();
        implementCmb.clearSelection();
        sizeField.clear();
//      keyTypeCmb.clearSelection();
//      valueTypeCmb.clearSelection();
//      dataTypeCmb.clearSelection();
        logEventsField.getChildren().clear();
        inpField.clear();
        visualizationPanelCard.getChildren().clear();
        operationPanelCard.setVisible(false);
        operationPanelCard2.setVisible(false);
        operationPanelCard3.setVisible(false);
        linkedListOperationPanelCard.setVisible(false);
        treeSetOperationPanelCard.setVisible(false);
        keyValueTypeCmb.setVisible(false);
        dataTypeCmb.setVisible(false);
        arrayListModel.clear();
        linkedListModel.clear();
        hashSetModel.clear();
        hashMapModel.clear();
        valueLabels.clear();
        capacity = 0;
    }

    public void logClearBtn(ActionEvent actionEvent) {

        logEventsField.getChildren().clear();
    }

    @FXML
    public void listAddBtn(ActionEvent actionEvent) {
        String value = inpField.getText();
//  System.out.println(sizeField.getStyleClass());

        if (value.isBlank()) {
            addErrorLog("Please Enter a value.");
            return;
        }
        if(selectedDataType == null){
            addErrorLog("Please Select a data type.");
            return;
        }
        if (!isValidDataType(value)) {
            addErrorLog("Please Enter a " + selectedDataType + " value.");
            return;
        }
        if (arrayListModel.size() == capacity) {
            capacity = capacity + Math.max(1,capacity / 2);
//            addLog("Array Capacity is increased by : " + capacity);
            refreshArrayListVisualization();
        }
        arrayListModel.add(value);
        addLog("Added value : " + value + " and its Index is " + (arrayListModel.size() - 1));
        refreshArrayListVisualization();
        inpField.clear();
    }

    @FXML
    public void listRemoveBtn(ActionEvent actionEvent) {
        String value = inpField.getText();
        if(value.isBlank()){
            return;
        }
        if (arrayListModel.remove(value)) {
            addLog(value + " is successfully removed");
            refreshArrayListVisualization();
        } else {
            addErrorLog("Value not found.");
        }
        inpField.clear();
    }

    public void listSearchBtn(ActionEvent actionEvent) {
        String value = inpField.getText();
        if (value.isBlank()) {
            return;
        }

        for (Label label : valueLabels) {
            label.getStyleClass().remove("listValueLabel");
            if (!label.getStyleClass().contains("listValueCell")) {
                label.getStyleClass().add("listValueCell");
            }
        }
        boolean found = false;
        for (int i = 0; i < arrayListModel.size(); i++) {
            if (arrayListModel.get(i).equals(value)) {
                valueLabels.get(i).getStyleClass().add("listValueLabel");
                addLog("Element found at index : " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            addErrorLog("Element not found");
        }
        inpField.clear();
//    for(int i=0; i<visualizationPanelCard.getChildren().size(); i++){
//      VBox bx = (VBox) visualizationPanelCard.getChildren().get(i);
//      Label valueLabel = (Label) bx.getChildren().get(1);
//      valueLabel.getStyleClass().add("listValueCell");
//    }
//    for(int i=0; i<list.size(); i++){
//      if(list.get(i).equals(value)){
//        VBox box = (VBox) visualizationPanelCard.getChildren().get(i);
//        Label valueLabel = (Label) box.getChildren().get(1);
//        valueLabel.getStyleClass().add("listValueLabel");
//        break;
//      }
//      inpField.clear();
//    }
    }

    public void listClearBtn(ActionEvent actionEvent) {
        arrayListModel.clear();
        visualizationPanelCard.getChildren().clear();
        inpField.clear();
        sizeField.clear();
        valueLabels.clear();
    }

    private void addErrorLog(String message) {
        Label label = new Label(message);
        label.setStyle("-fx-font-size: 20; -fx-text-fill: red");
        logEventsField.getChildren().add(label);
    }

    public void linkedListAddBtn(ActionEvent actionEvent) {
        String value = linkedListInpField.getText();
        if (value.isBlank()) {
            addErrorLog("Please Enter a value.");
            return;
        }
        if (!isValidDataType(value)) {
            addErrorLog("Please enter a " + selectedDataType + " value.");
            return;
        }
        linkedListModel.add(value);
        addLog("Added value: " + value);
        refreshLinkedList();
        linkedListInpField.clear();
//    if(!value.isEmpty()){
//      linkedList.add(value);
//      addLog("The value of "+value+" is given.");
//      refreshLinkedList();
//    }else{
//      addErrorLog("Please check the input field");
//    }
//    linkedListInpField.clear();
    }

    public void linkedListRemoveBtn(ActionEvent actionEvent) {

        String value = linkedListInpField.getText().trim();
        if(value.isEmpty()){
            addErrorLog("Please Enter a value to remove");
            return;
        }
        boolean empty = linkedListModel.isEmpty();
        if(empty){
            addErrorLog("Linked list is empty.");
            return;
        }
        boolean removed = linkedListModel.remove(value);
        if(removed){
            refreshLinkedList();
            linkedListInpField.clear();
            addLog(value+" removed successfully.");
        }else {
            addErrorLog(value+" not found.");
        }
    }

    public void linkedListSearchBtn(ActionEvent actionEvent) {
        String searchValue = linkedListInpField.getText().trim();

        if(searchValue.isBlank()){
            addErrorLog("Please Enter a value to search");
        }

        for(Label label:linkedNodeLabels){
            label.getStyleClass().removeAll("currentNode","foundNode");
        }
        Timeline timeline = new Timeline();
        for(int i=0; i<linkedListModel.size();i++){
            final int index = i;

            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds((i+1)*0.7),
                    e->{
                        for (Label label:linkedNodeLabels){
                            label.getStyleClass().remove("currentNode");

                        }
                        Label current = linkedNodeLabels.get(index);

                        if(linkedListModel.get(index).equals(searchValue)){
                            current.getStyleClass().remove("currentNode");
                            current.getStyleClass().add("foundNode");
                            addLog(searchValue +" found at index "+index);
                            timeline.stop();
                        }else {
                            current.getStyleClass().add("currentNode");
                        }
                    }
            );
            timeline.getKeyFrames().add(keyFrame);
        }
        timeline.setOnFinished(e->{
            if(!linkedListModel.contains(searchValue)){
                addErrorLog(searchValue +" not found");
            }
        });
        timeline.play();
    }

    public void linkedListClearBtn(ActionEvent actionEvent) {
        visualizationPanelCard.getChildren().clear();
        linkedListInpField.clear();
        linkedListModel.clear();
    }

    private void refreshLinkedList() {

        visualizationPanelCard.getChildren().clear();
        visualizationPanelCard.setAlignment(Pos.CENTER);
        visualizationPanelCard.setSpacing(15);
        linkedNodeLabels.clear();
        for (int i = 0; i < linkedListModel.size(); i++) {

            VBox node = new VBox();
            node.setAlignment(Pos.CENTER);
            Label value = new Label(linkedListModel.get(i));
            value.setPrefSize(100, 40);
            value.setAlignment(Pos.CENTER);
            value.getStyleClass().add("listValueCell");
            linkedNodeLabels.add(value);
            Label next = new Label("Next");
            next.setPrefSize(100, 25);
            next.getStyleClass().add("listIndexCell");

            node.getChildren().addAll(value, next);

            visualizationPanelCard.getChildren().add(node);

            if (i != linkedListModel.size() - 1) {

                Label arrow = new Label("➜");
                arrow.setStyle("-fx-font-size:28;");
                arrow.setAlignment(Pos.CENTER);
                arrow.setPrefSize(40, 70);
                visualizationPanelCard.getChildren().add(arrow);

            } else {

                Label nullNode = new Label("NULL");
                nullNode.setAlignment(Pos.CENTER);
                nullNode.setPrefSize(60, 70);
                nullNode.setStyle("-fx-text-fill:red;-fx-font-size:18;");
                visualizationPanelCard.getChildren().add(nullNode);
            }
        }
    }

    public void treeSetAddBtn(ActionEvent actionEvent) {
        String value = treeSetInpField.getText().trim();
        if (value.isBlank()) {
            addErrorLog("Please Enter a value.");
            return;
        }
        if(selectedDataType==null){
            addErrorLog("Please Select a data type.");
            return;
        }
        if(!isValidDataType(value)){
            addErrorLog("Please Enter a " + selectedDataType + " value.");
            return;
        }
//        int currentSize;
        Object convertedValue = convertValue(value,selectedDataType);
        boolean added;
        if("Integer".equals(selectedDataType)){
            added = treeSetIntegerModel.add((Integer) convertedValue);
        }else {
            added = treeSetStringModel.add((String) convertedValue);
        }
        if(added){
            int currentSize;
            if("Integer".equals(selectedDataType)){
                currentSize = treeSetIntegerModel.size();
            }else {
                currentSize = treeSetStringModel.size();
            }
            if(currentSize > capacity){
                capacity = capacity+Math.max(1,capacity/2);
                addLog("TreeSet capacity increased to "+capacity);
            }
            searchedValue = null;
            refreshTreeSetVisualization();
            addLog("Added : "+convertedValue);
        }else {
            addErrorLog("Duplicate Element is not allowed.");
            addLog("Duplicate Element : "+convertedValue);
        }
            treeSetInpField.clear();
    }

    public void treeSetRemoveBtn(ActionEvent actionEvent) {
            String value = treeSetInpField.getText().trim();
            if (value.isBlank()) {
                addErrorLog("Please Enter a value.");
                return;
            }
            if (!isValidDataType(value)) {
                addErrorLog("Please Enter a " + selectedDataType + " value.");
                return;
            }

            Object removedTreeSetValue = convertValue(value,selectedDataType);
            boolean removed;

            if("Integer".equals(selectedDataType)){
                removed = treeSetIntegerModel.remove((Integer) removedTreeSetValue);
            }else{
                removed = treeSetStringModel.remove((String) removedTreeSetValue);
            }
            if(removed){
                searchedValue=null;
                refreshTreeSetVisualization();
                addLog(removedTreeSetValue+" is removed successfully.");
            }else{
                addErrorLog(removedTreeSetValue+" is not found.");
            }
            treeSetInpField.clear();
    }

    public void treeSetSearchBtn(ActionEvent actionEvent) {
        String value = treeSetInpField.getText().trim();
        if(value.isBlank()){
            addErrorLog("Please Enter a value");
            return;
        }
        try {
            Object searchValue;
            boolean found;
            if("Integer".equals(selectedDataType)){
                Integer intValue = Integer.parseInt(value);
                found = treeSetIntegerModel.contains(intValue);
                searchValue = intValue;
            }else{
                found = treeSetStringModel.contains(value);
                searchValue = value;
            }

            if(found){
                searchedValue = searchValue;
                refreshTreeSetVisualization();
                addLog(searchValue + " is found.");
            }else {
                searchedValue = null;
                refreshTreeSetVisualization();
                addErrorLog(searchValue+" is not found.");
            }
        }catch (Exception e){
            addErrorLog("Invalid Input.");
        }
//        Object searchValue = convertValue(value,selectedDataType);
//        if(treeSet.contains(searchValue)){
//            searchedValue = searchValue;
//            refreshTreeSetVisualization();
//            addLog(searchValue+" is found");
//        }else {
//            searchedValue=null;
//            refreshTreeSetVisualization();
//            addErrorLog(searchValue+" is not found.");
//        }
        treeSetInpField.clear();
    }

    public void treeSetClearBtn(ActionEvent actionEvent) {
        treeSetIntegerModel.clear();
        treeSetStringModel.clear();
        searchedValue = null;
        visualizationPanelCard.getChildren().clear();
        treeSetInpField.clear();
        addLog("TreeSet Cleared.");
    }
}