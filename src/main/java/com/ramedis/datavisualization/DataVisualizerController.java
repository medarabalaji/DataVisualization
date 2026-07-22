package com.ramedis.datavisualization;


import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.javafx.FontIcon;

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

  private String selectedKeyType;
  private String selectedValueType;
  private String selectedDataType;
  private ArrayList<String> list = new ArrayList<>();
  private Map<Object, Object> map = new HashMap<>();
  private HashSet<String> set = new HashSet<>();
  private int capacity = 10;
  private boolean darkMode = true;
  private Map<String, List<String>> implement = new HashMap<>();


  public void initialize(){

    implement.put("List", List.of("ArrayList","LinkedList"));
    implement.put("Set",List.of("HashSet","TreeSet"));
    implement.put("Map",List.of("HashMap","TreeMap"));
    categoryCmb.getItems().addAll("None","List","Set","Map");
    dataTypeCmb.getItems().addAll("String","Integer");
    keyTypeCmb.getItems().addAll("String","Integer");
    valueTypeCmb.getItems().addAll("String","Integer");
    visualizationPanelCard.setAlignment(Pos.CENTER_LEFT);
  }
  private Object convertValue(String value, String type){
    switch (type){
      case "Integer":
        return Integer.parseInt(value);
      case "String":
        return value;
      default:
        return value;
    }
  }

  @FXML
  private void refreshVisualization(){
    visualizationPanelCard.getChildren().clear();
    for(int i=0; i<capacity; i++){
      Label indexCell = new Label(String.valueOf(i));
      indexCell.setAlignment(Pos.CENTER);
      indexCell.setStyle("-fx-font-size: 14; -fx-text-fill: red;");

      Label valueCell = new Label();
      valueCell.setPrefSize(100,40);
      valueCell.setAlignment(Pos.CENTER);
      valueCell.setStyle("-fx-font-size: 20;-fx-text-fill: blue; -fx-border-color: #ec641c; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-style: dotted");
      if(i<list.size()){
        valueCell.setText(list.get(i));
      }
      VBox vBox = new VBox();
      vBox.setAlignment(Pos.CENTER);
      vBox.getChildren().addAll(indexCell,valueCell);
      visualizationPanelCard.getChildren().add(vBox);
    }
  }
  @FXML
  private void refreshVisualization2(){
    visualizationPanelCard.getChildren().clear();
    for(Map.Entry<Object, Object> entry:map.entrySet()) {
      Label mapLabel = new Label(entry.getKey() + " : " + entry.getValue());
      mapLabel.setTextFill(Color.BLUE);
      mapLabel.setPrefSize(250, 40);
      mapLabel.setAlignment(Pos.CENTER);
      mapLabel.setStyle("-fx-border-color: #ec641c; -fx-border-width: 2; -fx-border-radius: 10; -fx-border-style: dotted; -fx-text-fill: blue;-fx-font-size: 16");

      visualizationPanelCard.getChildren().add(mapLabel);
    }
  }

  @FXML
  private void refreshVisualization3(){
    visualizationPanelCard.getChildren().clear();
    int index = 0;
    for(String value: set){
      Label indexCell = new Label(String.valueOf(index));
      indexCell.setAlignment(Pos.CENTER);
      indexCell.setStyle("-fx-font-size: 16; -fx-text-fill: blue;");

      Label valueCell = new Label(value);
      valueCell.setPrefSize(100,40);
      valueCell.setAlignment(Pos.CENTER);
      valueCell.setStyle("-fx-font-size: 20;-fx-text-fill: blue; -fx-border-color: #ec641c;-fx-border-radius: 10; -fx-border-style: dotted; -fx-border-width: 2");

      VBox box = new VBox(5);
      box.setAlignment(Pos.CENTER);
      box.getChildren().addAll(indexCell,valueCell);
      visualizationPanelCard.getChildren().add(box);
      index++;
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

    addLog("Implementation type Selected");
    addLog("Value "+implementCmb.getValue());

    String selected = implementCmb.getValue();
    if(selected == null){
      return;
    }
    addLog(selected+" is selected");
    switch (selected){
      case "ArrayList":
        configureArrayListUI();
        addLog("Configure ArrayList UI");
        break;
      case "LinkedList":
        addLog("Configure LinkedList UI");
        break;
      case "HashSet":
        configureSetListUI();
        addLog("Configure HashSet UI");
        break;
      case "TreeSet":
        addLog("Configure TreeSet UI");
        break;
      case "HashMap":
        configureMapListUI();
        addLog("Configure HashMap UI");
        break;
      case "TreeMap":
        addLog("Configure TreeMap UI");
        break;

    }
  }
  public void applyBtn(ActionEvent actionEvent) {
    try{
      capacity = Integer.parseInt(sizeField.getText());
      list.clear();
      refreshVisualization();
    }catch (NumberFormatException e){
      addLog("Please Enter a valid Size");
    }
  }
  public void clearBtn(ActionEvent actionEvent) {
    list.clear();
    visualizationPanelCard.getChildren().clear();
    inpField.clear();
    sizeField.clear();
  }
  public void removeBtn(ActionEvent actionEvent) {
    String value = inpField.getText();
    if(list.remove(value)){
      refreshVisualization();
    }else{
      addLog("Value not found.");
    }
    inpField.clear();
  }
  private void configureArrayListUI(){
    operationPanelCard.setVisible(true);
    operationPanelCard2.setVisible(false);
    operationPanelCard3.setVisible(false);
    dataTypeCmb.setVisible(true);
    keyValueTypeCmb.setVisible(false);
    visualizationPanelCard.getChildren().clear();
    list.clear();
    inpField.clear();
    sizeField.clear();
    capacity=0;
  }
  private void configureMapListUI(){
    operationPanelCard.setVisible(false);
    operationPanelCard2.setVisible(true);
    operationPanelCard3.setVisible(false);
    dataTypeCmb.setVisible(false);
    keyValueTypeCmb.setVisible(true);
    visualizationPanelCard.getChildren().clear();
  }
  private void configureSetListUI(){
    operationPanelCard.setVisible(false);
    operationPanelCard2.setVisible(false);
    operationPanelCard3.setVisible(true);
    dataTypeCmb.setVisible(true);
    keyValueTypeCmb.setVisible(false);
    visualizationPanelCard.getChildren().clear();
    list.clear();
    inpField.clear();
    sizeField.clear();
    capacity=0;
  }
@FXML
  public void searchBtn(ActionEvent actionEvent) {
    String value = inpField.getText();
    for(int i=0; i<visualizationPanelCard.getChildren().size(); i++){
      VBox bx = (VBox) visualizationPanelCard.getChildren().get(i);
      Label valueLabel = (Label) bx.getChildren().get(1);
      valueLabel.setStyle("-fx-font-size: 20;-fx-border-color: blue;-fx-border-radius: 10; -fx-border-style: dotted; -fx-border-width: 2; -fx-text-fill: blue");
    }
    for(int i=0; i<list.size(); i++){
      if(list.get(i).equals(value)){
        VBox box = (VBox) visualizationPanelCard.getChildren().get(i);
        Label valueLabel = (Label) box.getChildren().get(1);
        valueLabel.setStyle("-fx-font-size: 20; -fx-border-color: blue; -fx-border-radius: 10; -fx-border-width: 2; -fx-background-color: white; -fx-text-fill: blue; ");
        break;
      }
      inpField.clear();
    }
  }
  public void changeBgBtn(ActionEvent actionEvent) {
    if(darkMode){
      applyDarkMode();
      darkMode=false;
    }else {
      applyLightMode();
      darkMode=true;
    }
  }


@FXML
  public void dataTypeSelectionClick(ActionEvent actionEvent) {
    selectedDataType = dataTypeCmb.getValue();
    addLog("Selected Data type : "+selectedDataType);
  }

  public void keyTypeSelectionClick(ActionEvent actionEvent) {
    selectedKeyType = keyTypeCmb.getValue();
  }

  public void valueTypeSelectionClick(ActionEvent actionEvent) {
    selectedValueType = valueTypeCmb.getValue();
  }
@FXML
  public void onAdd(ActionEvent actionEvent) {
      String value = inpField.getText();
//  System.out.println(sizeField.getStyleClass());
      if(!isValidDataType(value)){
        addLog("Please Enter a "+selectedDataType+ " value.");
        return;
      }
      if(value.isBlank()){
        return;
      }
      if(list.size() == capacity){
        capacity = capacity+capacity/2;
        addLog("Array Capacity is increased by : "+capacity);
      }
      list.add(value);
      refreshVisualization();
      inpField.clear();
  }







  public void onSetAdd(ActionEvent actionEvent) {
    String value = setInpField.getText();
    if(!isValidDataType(value)){
      addLog("Please Enter a "+selectedDataType+" value.");
      return;
    }
    if(value.isBlank()){
      return;
    }
    if(set.size() ==capacity){
      capacity = capacity + capacity/2;
      addLog("Set capacity is increased by : "+capacity);
    }
    if(set.add(value)){
      addLog("Added : "+value);
      refreshVisualization3();
    }else {
      addLog("Duplicate Element is not allowed");
      addLog("Here the duplicate element is : "+value);
    }
    setInpField.clear();
  }

  public void removeSetBtn(ActionEvent actionEvent) {
    String value = setInpField.getText();
    if(set.remove(value)){
      addLog("Removed : "+value);
      refreshVisualization3();
    }else {
      addLog("Element not found");
    }
    setInpField.clear();
  }

  public void searchSetBtn(ActionEvent actionEvent) {
    String value = setInpField.getText();
    if(set.contains(value)){
      addLog("Found : "+value);
    }else {
      addLog(value+" not found");
    }
    setInpField.clear();
  }

  public void clearSetBtn(ActionEvent actionEvent) {
    visualizationPanelCard.getChildren().clear();
    set.clear();
    addLog("cleared");
  }

  public void putBtn(ActionEvent actionEvent) {
    String keyText = keyField.getText();
    String valueText = valueField.getText();
    if(keyText.isBlank() || valueText.isBlank()){
      addLog("Please Enter both key and value");
      return;
    }
    try{
      Object key = convertValue(keyText,selectedKeyType);
      Object value = convertValue(valueText,selectedValueType);
      map.put(key, value);
      refreshVisualization2();
      keyField.clear();
      valueField.clear();
    }catch (Exception e){
      addLog("Invalid key-value type");
    }
  }

  public void mapRemoveBtn(ActionEvent actionEvent) {
    String keyText = keyField.getText();
    if(keyText.isBlank()){
      addLog("Please enter a key");
    }
    try{
      Object key = convertValue(keyText,selectedKeyType);
      if(map.containsKey(key)){
        map.remove(key);
        refreshVisualization2();
        addLog("Key removed Successfully");
      }else {
        addLog("Key not found");
      }
    }catch (Exception e){
      addLog("Invalid Key");
    }
    keyField.clear();
  }

  public void mapSearchBtn(ActionEvent actionEvent) {
    String keyText = keyField.getText();
    if(keyText.isBlank()){
      addLog("Please enter a key");
      return;
    }
    try{
      Object key = convertValue(keyText, selectedKeyType);
      if(map.containsKey(key)){
        Object value = map.get(key);
        addLog("Key is "+key+" and value is "+value);
      }else {
        addLog("Key not found");
      }
    }catch (Exception e){
      addLog("Invalid key");
    }
    keyField.clear();
  }

  public void mapClearBtn(ActionEvent actionEvent) {
    visualizationPanelCard.getChildren().clear();
    map.clear();
  }




  private void addLog(String message){
    Label log = new Label(message);
    log.setStyle("-fx-font-size: 20; -fx-text-fill: green;");
    logEventsField.getChildren().add(log);
  }




  private boolean isValidDataType(String value){
    switch (selectedDataType){
      case "String":
        addLog("String is selected");
        return true;
      case "Integer":
        try{
          Integer.parseInt(value);
          return true;
        }catch (NumberFormatException e){
          return false;
        }
      default:
        return false;
    }
  }
  private void applyLightMode(){
    totalScreen.setStyle("-fx-background-color: white");
    themeBtn.setStyle("-fx-text-fill: #000000;-fx-background-color: transparent; ");
    applyBtn.setStyle("-fx-text-fill: #000000;-fx-background-color: transparent; ");
  }
  private void applyDarkMode(){
    totalScreen.setStyle("-fx-background-color: #000000; ");
    themeBtn.setStyle(" -fx-background-color: #2a2a2a");
    applyBtn.setStyle(" -fx-background-color: #2a2a2a");

  }


    public void resetBtn(ActionEvent actionEvent) {
      categoryCmb.clearSelection();
      implementCmb.clearSelection();
      sizeField.clear();
      keyTypeCmb.clearSelection();
      valueTypeCmb.clearSelection();
      dataTypeCmb.clearSelection();
      logEventsField.getChildren().clear();
      inpField.clear();
      visualizationPanelCard.getChildren().clear();
  }
}