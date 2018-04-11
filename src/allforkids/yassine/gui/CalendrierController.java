/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allforkids.yassine.gui;

import allforkids.yassine.entities.Model;
import allforkids.yassine.entities.evenement;
import allforkids.yassine.services.crudEvenement;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
 
import java.net.URL;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CalendrierController implements Initializable {
    @FXML
    private JFXListView<String> monthSelect;
    @FXML
    private GridPane calendarGrid;
    @FXML
    private HBox weekdayHeader;
    @FXML
    private JFXComboBox<String> selectedYear;
    @FXML
    private Label monthLabel;


    public void initializeCalendarGrid(){
        int rows = 6;
        int cols = 7;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
 
                VBox vPane = new VBox();
                vPane.getStyleClass().add("calendar_pane");
                vPane.setMinWidth(weekdayHeader.getPrefWidth()/7);
                GridPane.setVgrow(vPane, Priority.ALWAYS);
                calendarGrid.add(vPane, j, i);  
            }
        }       
        
        for (int i = 0; i < 7; i++) {
         RowConstraints row = new RowConstraints();
         calendarGrid.getRowConstraints().add(row);
        }
    }

    
    public void initializeCalendarWeekdayHeader(){
    
        int weekdays = 7;
        
        String[] weekAbbr = {"Sun","Mon","Tue", "Wed", "Thu", "Fri", "Sat"};
        
        for (int i = 0; i < weekdays; i++){
            
            StackPane pane = new StackPane();
            pane.getStyleClass().add("weekday-header");
            HBox.setHgrow(pane, Priority.ALWAYS);
            pane.setMaxWidth(Double.MAX_VALUE);
            weekdayHeader.getChildren().add(pane);
            pane.getChildren().add(new Label(weekAbbr[i]));
        }
    }
 public void calendarGenerate(){
         Model.getInstance().calendar_start_date = "" + "4/1/2018";
                Model.getInstance().calendar_start = 2018;
                Model.getInstance().calendar_end = 2019 ;
                Model.getInstance().calendar_name = "calendrier des èvenements";
        
        selectedYear.getItems().clear(); 
        
        
        selectedYear.getItems().addAll("2018","2019");
        selectedYear.getSelectionModel().selectFirst();
        Model.getInstance().viewing_year = Integer.parseInt(selectedYear.getSelectionModel().getSelectedItem());
        
        selectedYear.setVisible(true);
        DateFormatSymbols dateFormat = new DateFormatSymbols();
        String months[] = dateFormat.getMonths();
        String[] spliceMonths = Arrays.copyOfRange(months, 0, 12);
        monthSelect.getItems().clear();
        monthSelect.getItems().addAll(spliceMonths);   
        monthSelect.getSelectionModel().selectFirst();
        monthLabel.setText(monthSelect.getSelectionModel().getSelectedItem());
                System.out.println("month = "+monthSelect.getSelectionModel().getSelectedItem());
        Model.getInstance().viewing_month = 
                Model.getInstance().getMonthIndex(monthSelect.getSelectionModel().getSelectedItem());
        System.out.println("month index "+Model.getInstance().viewing_month);
        // Update view
     loadCalendarLabels();
    }
      private void loadCalendarLabels(){
        int year = Model.getInstance().viewing_year;
        int month = Model.getInstance().viewing_month;
        GregorianCalendar gc = new GregorianCalendar(year, month, 1);
        int firstDay = gc.get(Calendar.DAY_OF_WEEK);

        int daysInMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        int offset = firstDay;
        int gridCount = 1;
        int lblCount = 1;
       for(Node node : calendarGrid.getChildren()){
           
           VBox day = (VBox) node;
           
           day.getChildren().clear();
           day.setStyle("-fx-backgroud-color: white");
           day.setStyle("-fx-font: 14px \"System\" ");
           if (gridCount < offset) {
               gridCount++;
               day.setStyle("-fx-background-color: #E9F2F5"); 
           } else {
            if (lblCount > daysInMonth) {
                // Instead, darken day color
                day.setStyle("-fx-background-color: #E9F2F5"); 
            } else {
                
                // Make a new day label   
                Label lbl = new Label(Integer.toString(lblCount));
                lbl.setPadding(new Insets(5));
                lbl.setStyle("-fx-text-fill:darkslategray");

                day.getChildren().add(lbl);
            }
               
            lblCount++;           
           }
       }
    }
       private void populateMonthWithEvents(){
        
        String calendarName = Model.getInstance().calendar_name;
        String currentMonth = monthLabel.getText();
        
        int currentMonthIndex = Model.getInstance().getMonthIndex(currentMonth) + 1;
        int currentYear = Integer.parseInt(selectedYear.getValue());
        crudEvenement crud =new crudEvenement();
        List<evenement> l=crud.afficherEvenement();
        
             for(evenement e:l){
                 Date eventDate = e.getDate_debut();
                 String eventDescript = e.getNom();
                 int eventTermID = e.getDate_fin().getDay() - e.getDate_debut().getDay();
                 Label lb=new Label(e.getNom());
                 if (currentYear == eventDate.toLocalDate().getYear()){
                    if (currentMonthIndex == eventDate.toLocalDate().getMonthValue()){
                        int day = eventDate.toLocalDate().getDayOfMonth();
                        showDate(day, eventDescript, eventTermID);
                   
                    }         
                 }
             }
         
    }
        public void showDate(int dayNumber, String descript, int termID){
 
        for (Node node: calendarGrid.getChildren()) {
         
            VBox day = (VBox) node;
            
            if (!day.getChildren().isEmpty()) {
                
                Label lbl = (Label) day.getChildren().get(0);
                int currentNumber = Integer.parseInt(lbl.getText());
                
                if (currentNumber == dayNumber) {
                    
                    Label eventLbl = new Label(descript); 
                    
                    eventLbl.getStyleClass().add("event-label");
                
                    eventLbl.setAccessibleText(Integer.toString(termID));
                                   
                   eventLbl.setStyle("-fx-background-color:  #86BAAB ;" ); 
                   eventLbl.setTextFill(Color.web("#ffffff")); 

                   day.getChildren().add(eventLbl);
                }
            }
        }
    }
    
         public void repaintView(){
        loadCalendarLabels();
        populateMonthWithEvents();
      
           
        }
        
    
        private void initializeMonthSelector(){
            
                monthSelect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    
                    if (newValue != null) {
                        
                        monthLabel.setText(newValue);
                         
                        Model.getInstance().viewing_month = Model.getInstance().getMonthIndex(newValue);
                      
                        populateMonthWithEvents();
                       repaintView();
                    }
                    
                }
            });
        
        selectedYear.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    
                    if (newValue != null){
                        Model.getInstance().viewing_year = Integer.parseInt(newValue);
                     loadCalendarLabels();
                    }
                }
            });
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
initializeCalendarGrid();
    calendarGenerate();
    initializeCalendarWeekdayHeader();
     initializeMonthSelector();
   
          populateMonthWithEvents();
          
     
   
        
    }    

    
}
