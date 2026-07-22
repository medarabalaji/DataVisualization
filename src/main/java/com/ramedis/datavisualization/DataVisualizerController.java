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
private MFXButton logClearBtn;
  private String selectedKeyType;
  private String selectedValueType;
  private String selectedDataType;
  private ArrayList<String> list = new ArrayList<>();
  private ArrayList<Label> valueLabels = new ArrayList<>();
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
    if(valueLabels.size()<capacity){

      for(int i =valueLabels.size(); i< capacity; i++){
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
    for(int i=0; i<capacity; i++){
      valueLabels.get(i).getStyleClass().remove("listValueLabel");
      if(i<list.size()){
        valueLabels.get(i).setText(list.get(i));

      }else {
        valueLabels.get(i).setText("");
      }
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
      mapLabel.getStyleClass().add("mapLabel");

      visualizationPanelCard.getChildren().add(mapLabel);
    }
  }

  @FXML
  private void refreshVisualization3(){
    visualizationPanelCard.getChildren().clear();
    List<String> values = new ArrayList<>(set);
    for(int i=0; i<capacity; i++){
      Label indexCell = new Label(String.valueOf(i));
      indexCell.setAlignment(Pos.CENTER);
      indexCell.getStyleClass().add("setIndexCell");

      Label valueCell = new Label();
      valueCell.setPrefSize(100,40);
      valueCell.setAlignment(Pos.CENTER);
      valueCell.getStyleClass().add("setValueCell");

      if(i<values.size()){
        valueCell.setText(values.get(i));
      }

      VBox box = new VBox(5);
      box.setAlignment(Pos.CENTER);
      box.getChildren().addAll(indexCell,valueCell);
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

    addLog(implementCmb.getValue()+" is implemented");


    String selected = implementCmb.getValue();
    if(selected == null){
      return;
    }
    switch (selected){
      case "ArrayList":
        configureArrayListUI();
        addLog("Configuring ArrayList UI");
        break;
      case "LinkedList":
        addLog("Configuring LinkedList UI");
        break;
      case "HashSet":
        configureSetListUI();
        addLog("Configuring HashSet UI");
        break;
      case "TreeSet":
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
    try{
      capacity = Integer.parseInt(sizeField.getText());
      addLog("the size value is "+capacity);
      list.clear();
      refreshVisualization();
    }catch (NumberFormatException e){
      addErrorLog("Please Enter a valid Size");
    }
  }
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
    String value = setInpField.getText();
    if(!isValidDataType(value)){
      addErrorLog("Please Enter a "+selectedDataType+" value.");
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
      addErrorLog("Duplicate Element is not allowed");
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
      addErrorLog("Element not found");
    }
    setInpField.clear();
  }

  public void searchSetBtn(ActionEvent actionEvent) {
    String value = setInpField.getText();
    if(set.contains(value)){
      addLog("Found : "+value);
    }else {
      addErrorLog(value+" not found");
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
      addErrorLog("Please Enter both key and value");
      return;
    }
    try{
      Object key = convertValue(keyText,selectedKeyType);
      addLog(key+" is entered");
      Object value = convertValue(valueText,selectedValueType);
      addLog(value+" is entered");
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
      addErrorLog("Please enter a key");
    }
    try{
      Object key = convertValue(keyText,selectedKeyType);
      if(map.containsKey(key)){
        map.remove(key);
        refreshVisualization2();
        addLog(key+" removed Successfully");
      }else {
        addErrorLog(key+" not found");
      }
    }catch (Exception e){
      addErrorLog("Invalid Key");
    }
    keyField.clear();
  }

  public void mapSearchBtn(ActionEvent actionEvent) {
    String keyText = keyField.getText();
    if(keyText.isBlank()){
      addErrorLog("Please enter a key");
      return;
    }
    try{
      Object key = convertValue(keyText, selectedKeyType);
      if(map.containsKey(key)){
        Object value = map.get(key);
        addLog("Key is "+key+" and value is "+value);
      }else {
        addErrorLog("Key not found");
      }
    }catch (Exception e){
      addErrorLog("Invalid key");
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
    resetBtn.setStyle("-fx-text-fill: #000000; -fx-background-color: #aaaaaa;");
    themeBtn.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
    applyBtn.setStyle("-fx-text-fill: #000000;-fx-background-color: #aaaaaa; ");
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
  private void applyDarkMode(){
    totalScreen.setStyle("-fx-background-color: #000000; ");
    themeBtn.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #2a2a2a");
    applyBtn.setStyle("-fx-text-fill: #ffffff; -fx-background-color: #2a2a2a");
    resetBtn.setStyle("-fx-text-fill: #ffffff;-fx-background-color: #2a2a2a");
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
      keyTypeCmb.clearSelection();
      valueTypeCmb.clearSelection();
      dataTypeCmb.clearSelection();
      logEventsField.getChildren().clear();
      inpField.clear();
      visualizationPanelCard.getChildren().clear();
  }

  public void logClearBtn(ActionEvent actionEvent) {

    logEventsField.getChildren().clear();
  }
@FXML
  public void listAddBtn(ActionEvent actionEvent) {
    String value = inpField.getText();
//  System.out.println(sizeField.getStyleClass());
    if(!isValidDataType(value)){
      addErrorLog("Please Enter a "+selectedDataType+ " value.");
      return;
    }
    if(value.isBlank()){
      return;
    }
    if(list.size() == capacity){
      capacity = capacity+capacity/2;
      addLog("Array Capacity is increased by : "+capacity);
      refreshVisualization();
    }
    list.add(value);
    addLog("Added value : "+value+" and its Index is "+(list.size()-1));
    refreshVisualization();
    inpField.clear();
  }
@FXML
  public void listRemoveBtn(ActionEvent actionEvent) {
  String value = inpField.getText();
  if(list.remove(value)){
    refreshVisualization();
    addLog(value +" is successfully removed");
  }else{
    addErrorLog("Value not found.");
  }
  inpField.clear();
  }

  public void listSearchBtn(ActionEvent actionEvent) {
    String value = inpField.getText();
    if(value.isBlank()){
      return;
    }

    for(Label label : valueLabels){
      label.getStyleClass().remove("listValueLabel");
      if(!label.getStyleClass().contains("listValueCell")){
        label.getStyleClass().add("listValueCell");
      }
    }
    boolean found = false;
    for(int i=0; i<list.size(); i++){
      if(list.get(i).equals(value)){
        valueLabels.get(i).getStyleClass().add("listValueLabel");
        addLog("Element found at index : "+i);
        found=true;
        break;
      }
    }
    if(!found){
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
    list.clear();
    visualizationPanelCard.getChildren().clear();
    inpField.clear();
    sizeField.clear();
  }
  private void addErrorLog(String message){
    Label label = new Label(message);
    label.setStyle("-fx-font-size: 20; -fx-text-fill: red");
    logEventsField.getChildren().add(label);
  }

}