/**
* @Project: AllForKids
* @Classe: ListeOffreBackController
* @Date: 11 avr. 2018
* @Time: 17:30:43
*
* @author Lauris
*/


package allforkids.controllers;

import static allforkids.controllers.ListeOffreController.idOffre;
import allforkids.entities.OffreTransport;
import allforkids.entities.User;
import allforkids.services.OffreTransportService;
import allforkids.services.ReservationOffreTransportService;
import allforkids.services.UserService;
import allforkids.utils.MyConnexion;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;


public class ListeOffreBackController implements Initializable{
    String dateD = "Date de Debut";
    String dateF = "Date de Fin";
    String prix = "Prix";
    
    Connection cn = MyConnexion.getInstance().getConnection();
    
    ObservableList<OffreTransport> offreTransportDATA = FXCollections.observableArrayList();
    
    OffreTransport offre = new OffreTransport();
    OffreTransportService ots = new OffreTransportService();
    ReservationOffreTransportService rots = new ReservationOffreTransportService();
    
    int reserve = 0;
    UserService uss = new UserService();
    public static int idOffre;

    public int getIdOffre() {
        return idOffre;
    }

    DetailOffreController typeControl;
    @FXML
    private TableView<OffreTransport> listeOffreTable;
    @FXML
    private TableColumn<OffreTransport, String> descriptionColumn;
    @FXML
    private TableColumn<OffreTransport, Boolean> detailColumn;
    @FXML
    private TableColumn<OffreTransport, String> destinationColumn;
    @FXML
    private TableColumn<OffreTransport, Date> dateColumn;
    @FXML
    private FontAwesomeIconView searchButton;
    @FXML
    private JFXTextField searchOffre;
    @FXML
    private AnchorPane choixAnchorPane;
    @FXML
    private JFXComboBox<String> choix;
    @FXML
    private JFXDatePicker dateDebutPicker;
    @FXML
    private JFXDatePicker dateFinPicker;
    @FXML
    private JFXTextField prixTextfield;
    @FXML
    private JFXButton validerButton;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(choix);
        choixAnchorPane.setVisible(false);
        getChoice(choix);
        ObservableList<String> options = FXCollections.observableArrayList(
                dateD,
                dateF,
                prix
        );
        choix.setItems(options);
        choix.getSelectionModel().select(0);
        
        initColumn();
        loadData();

        FilteredList<OffreTransport> filteredData = new FilteredList<>(offreTransportDATA, o -> true);
        searchOffre.setOnKeyReleased(o -> {
            searchOffre.textProperty().addListener((observableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super OffreTransport>) offer->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(offer.getDestination().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<OffreTransport> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(listeOffreTable.comparatorProperty());
            listeOffreTable.setItems(sortedData);
        });
    }
    
    Callback<TableColumn<OffreTransport, Boolean>, TableCell<OffreTransport, Boolean>> cellIcon =
            new Callback<TableColumn<OffreTransport, Boolean>, TableCell<OffreTransport, Boolean>>()
    {
        @Override
        public TableCell<OffreTransport, Boolean> call(final TableColumn<OffreTransport, Boolean> param)
        {
            final TableCell<OffreTransport, Boolean> cell = new TableCell<OffreTransport, Boolean>()
            {
                Image imgdetail = new Image(getClass().getResourceAsStream("/allforkids/ressources/images/detail.png"));
                final Button detailButton = new Button();
                
                @Override
                public void updateItem(Boolean check, boolean  empty)
                {
                    super.updateItem(check, empty);
                    if(empty)
                    {
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        detailButton.setOnAction(e ->{
                            
                            FXMLLoader loader  = new FXMLLoader();
                            loader.setLocation(getClass().getClassLoader().getResource("allforkids/GUI/DetailOffre.fxml"));
                            
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                            OffreTransport off = getTableView().getItems().get(getIndex());
                            User user = new User();
                            
                            try {
                                user = uss.getUser(off.getUser());
                            } catch (SQLException | IOException ex) {
                                System.err.println(ex.getMessage());
                            }
                            
                            File file = new File("C:\\wamp64\\www\\AllForKids\\web\\image_user\\" + user.getImage().toString());
                            Image image = new Image(file.toURI().toString());
                            
                            
                            DetailOffreController detailControl = loader.getController();
                            detailControl.setOffreDetail(user.getUsername(), off.getDescription(), off.getDestination(), String.valueOf(off.getDateDebut()), 
                                    String.valueOf(off.getDateFin()), String.valueOf(off.getPlaceRestant()), String.valueOf(off.getPrix()), image);
                            
                            
                            idOffre = off.getId();
                            try {
                                reserve = rots.dejaReserver(uss.getId(), off.getId());
                            } catch (SQLException ex) {
                                System.err.println(ex.getMessage());
                            }
                            if(reserve == 0){
                                System.err.println("erreur");
                                detailControl.getAnnulerReservationLabel().setVisible(false);
                            }
                            
                            if(off.getUser() == uss.getId()){
                                detailControl.getReserverButton().setVisible(false);
                                detailControl.getAnnulerReservationLabel().setVisible(false);
                            }else{
                                detailControl.getModifierButton().setVisible(false);
                                detailControl.getSupprimerButton().setVisible(false);
                            }
                            
                            
                            
                            Parent pa = loader.getRoot();
                            Stage stage = new Stage();
                            
                            stage.getIcons().add(new Image(getClass().getResourceAsStream("/allforkids/ressources/images/logo1.png")));
                            stage.setTitle("AllForKids");
                            
                            stage.initModality(Modality.WINDOW_MODAL);
                            stage.initOwner(detailButton.getScene().getWindow());
                            
                            stage.setScene(new Scene(pa));
                            stage.showAndWait();
                        });
                        
                        detailButton.setStyle("-fx-background-color: transparent");
                        ImageView iv = new ImageView();
                        iv.setImage(imgdetail);
                        iv.setPreserveRatio(true);
                        iv.setSmooth(true);
                        iv.setCache(true);
                        detailButton.setGraphic(iv);
                        
                        setGraphic(detailButton);
                        setAlignment(Pos.CENTER);
                        setText(null);
                    }
                }
                
            };
            return cell;
        }
    };

    
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void searchOffreAction(MouseEvent event) {
    }

    @FXML
    private void showChoicesPane(MouseEvent event) {
        choixAnchorPane.setVisible(true);
    }
    
    public void getChoice(JFXComboBox<String> choice){
        choice.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(observableValue.getValue().contains("Date de Debut")){
                dateDebutPicker.setVisible(true);
                dateFinPicker.setVisible(false);
                prixTextfield.setVisible(false);
            }
            if(observableValue.getValue().contains("Date de Fin")){
                dateDebutPicker.setVisible(false);
                dateFinPicker.setVisible(true);
                prixTextfield.setVisible(false);
            }
            if(observableValue.getValue().contains("Prix")){
                dateDebutPicker.setVisible(false);
                dateFinPicker.setVisible(false);
                prixTextfield.setVisible(true);
            }
        });
    }

    @FXML
    private void validerChoixAction(ActionEvent event) throws SQLException {
        Alert al = new Alert(Alert.AlertType.ERROR);
        String ch = choix.getValue();
        if(ch.contains("Date de Debut")){
            LocalDate dateDebut = dateDebutPicker.getValue();
            listeOffreTable.setItems(ots.searchOffreTransportByDateDebut(dateDebut));
            choixAnchorPane.setVisible(false);
        }
        if(ch.contains("Date de Fin")){
            LocalDate dateFin = dateFinPicker.getValue();
            listeOffreTable.setItems(ots.searchOffreTransportByDateFin(dateFin));
            choixAnchorPane.setVisible(false);
        }
        if(ch.contains("Prix")){
            double prx = 0;
            boolean doubleExiste = isDouble(prixTextfield);
            if(doubleExiste == true){
                prx = Double.valueOf(prixTextfield.getText());  
            }
            listeOffreTable.setItems(ots.searchOffreTransportByPrix(prx));
            choixAnchorPane.setVisible(false);
        }
    }
    

    private boolean isDouble(TextField input){
        try{
            double age = Double.parseDouble(input.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    
    public void initColumn(){
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        detailColumn.setCellFactory(cellIcon);
    }
    
    public void loadData(){
        offreTransportDATA.clear();
        
        List<OffreTransport> myList = ots.listerOffreTransport();
        offreTransportDATA.addAll(myList);
//        listeOffreTable.getItems().clear();
        
        listeOffreTable.setItems(offreTransportDATA);
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(listeOffreTable);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
}



/**
*@Lau82 © 2018
*/
