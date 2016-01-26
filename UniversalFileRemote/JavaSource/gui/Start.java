package gui;

import data.Css;
import data.Ids;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import log.Log;
import logic.Operation;
import util.GuiUtil;

public class Start extends Application{
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Universal File Remote");
		
		HBox layout = new HBox(20);
		layout.setPadding(new Insets(25, 25, 25, 25));
		this.createLeftPart(layout, primaryStage);
		this.createRightPart(layout);
		
		Scene scene = new Scene(layout, 850, 650);
		scene.getStylesheets().add("/layout.css");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(false);
		primaryStage.show();
		Log.setGuiAusgabe(GuiUtil.lookupTextArea(primaryStage.getScene(), "#" + Ids.main_taLog));
		Log.log("Application started", Log.Level.INFO);
	}

	protected void createRightPart(HBox layout) {
		GridPane rightLayout = new GridPane();
		final int gap = 10;
		rightLayout.setVgap(gap);
		rightLayout.setHgap(gap);
		layout.getChildren().add(rightLayout);
		rightLayout.setPrefWidth(400);
		
		Label lbLog = new Label("Log");
		rightLayout.add(lbLog, 0, 0);
		TextArea taLog = new TextArea();
		taLog.setId(Ids.main_taLog);
		taLog.setEditable(false);
		taLog.setPrefWidth(400);
		taLog.setPrefHeight(550);
		rightLayout.add(taLog, 0, 1, 2, 1);
		taLog.textProperty().addListener(new ChangeListener<Object>() { //regelt den autoscroll ans ende
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				taLog.setScrollTop(Double.MAX_VALUE);
			}
		});
		
		Button btnDeleteConsole = new Button("Log Leeren");
		btnDeleteConsole.setOnAction(EventHandlerFactory.getClearConsoleEventHandler());
		btnDeleteConsole.setPrefWidth(80);
		rightLayout.add(btnDeleteConsole, 1, 2);
		
		TextField tfLogEingabe = new TextField();
		tfLogEingabe.setPromptText("wird ins Log geschrieben");
		tfLogEingabe.setPrefWidth(400 - btnDeleteConsole.getPrefWidth() - gap);
		tfLogEingabe.setId(Ids.main_tfLogEingabe);
		tfLogEingabe.setOnAction(EventHandlerFactory.getCustomLogMessageEventHandler());
		rightLayout.add(tfLogEingabe, 0, 2);
		
	}

	protected void createLeftPart(HBox layout, Stage primaryStage) {
		int row = 0;
		GridPane leftLayout = new GridPane();
		layout.getChildren().add(leftLayout);
		leftLayout.setPrefWidth(400);
		leftLayout.setAlignment(Pos.TOP_LEFT);
		leftLayout.setHgap(10);
		leftLayout.setVgap(10);
		
		//Bereich
		Label lbBereich = new Label("Bereich");
		leftLayout.add(lbBereich, 0, row);
		TextField tfBereich = new TextField();
		tfBereich.setPromptText("D:/EclipseMars/workspace");
		tfBereich.setId(Ids.main_tfBereich);
		tfBereich.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(leftLayout));
		tfBereich.setTooltip(TooltipFactory.getBereichTooltip());
		leftLayout.add(tfBereich, 1, row);
		Button btnBereichExplorer = new Button("...");
		btnBereichExplorer.setOnAction(EventHandlerFactory.getWorkspaceChooserEventHandler(primaryStage));
		leftLayout.add(btnBereichExplorer, 2, row);
		row++;
		
		//Dateiname
		Label lbDateiname = new Label("Dateiname");
		leftLayout.add(GuiUtil.getWrappedInPane(lbDateiname), 0, row);
		TextArea taDateiname = new TextArea();
		taDateiname.setId(Ids.main_tfDateiname);
		taDateiname.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(leftLayout));
		taDateiname.setPromptText("pro Zeile ein Dateiname; wenn Leerzeichen enthalten sind, in Anführungszeichen setzen");
		taDateiname.setPrefRowCount(3);
		taDateiname.setPrefWidth(200);
		taDateiname.setTooltip(TooltipFactory.getDateinameTooltip());
		leftLayout.add(taDateiname, 1, row);
		CheckBox cbConcernsAll = new CheckBox("Bezug auf alle");
		cbConcernsAll.setId(Ids.main_cbConcernsAll);
		leftLayout.add(GuiUtil.getWrappedInPane(cbConcernsAll), 2, row);
		row++;
		
		//Dateiname Settings
		GridPane settings = new GridPane();
		settings.setHgap(40);
		leftLayout.add(settings, 1, row);
		CheckBox cbDateinameRegex = new CheckBox("Regex");
		cbDateinameRegex.setId(Ids.main_cbDateinameRegex);
		cbDateinameRegex.setTooltip(TooltipFactory.getRegexTooltip());
		settings.add(cbDateinameRegex, 0, 0);
		CheckBox cbDateinameIgnoreCase = new CheckBox("Ignore Case");
		cbDateinameIgnoreCase.setId(Ids.main_cbDateinameIgnoreCase);
		cbDateinameIgnoreCase.setTooltip(TooltipFactory.getIgnoreCaseTooltip());
		settings.add(cbDateinameIgnoreCase, 1, 0);
		CheckBox cbDateinamePath = new CheckBox("mit Pfad");
		cbDateinamePath.setId(Ids.main_cbDateinameMitPfad);
		cbDateinamePath.setTooltip(TooltipFactory.getMitPfadTooltip());
		leftLayout.add(cbDateinamePath, 2, row);
		row++;
		
		//Operation
		Label lbOperation = new Label("Operation");
		leftLayout.add(lbOperation, 0, row);
		ComboBox<String> comboOperations = new ComboBox<>();
		comboOperations.setId(Ids.main_comboOperation);
		ObservableList<String> options = FXCollections.observableArrayList(Operation.getOperationsList());
		comboOperations.getItems().addAll(options);
		comboOperations.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(leftLayout));
		leftLayout.add(comboOperations, 1, row);
		comboOperations.setOnAction(EventHandlerFactory.getOperationsEventHandler());
		CheckBox cbNurProjekte = new CheckBox("nur com.athos.*");
		cbNurProjekte.setId(Ids.main_cbNurProjekte);
		cbNurProjekte.setMinWidth(110);
		cbNurProjekte.setTooltip(TooltipFactory.getNurAthosComTooltip());
		cbNurProjekte.setSelected(true);
		leftLayout.add(cbNurProjekte, 2, row, 2, 1);
		row++;
		
		//Parameter
		GridPane parameterPane = new GridPane();
		parameterPane.getStyleClass().add(Css.borderGray);
		parameterPane.setId(Ids.main_paneParameter);
		parameterPane.setHgap(10);
		parameterPane.setVgap(10);
		parameterPane.setPrefSize(400, 196);
		parameterPane.setAlignment(Pos.TOP_LEFT);
		leftLayout.add(parameterPane, 0, row, 4, 1);
		row++;
		
		//Bedingungen
		VBox conditionBox = new VBox();
		conditionBox.setId(Ids.main_paneCondition);
		conditionBox.setMaxWidth(373); //14px für Scrollpane
		conditionBox.setPrefWidth(373); //14px für Scrollpane
		conditionBox.setAlignment(Pos.TOP_LEFT);
		ScrollPane scrollpane = GuiUtil.getWrappedInScrollPane(conditionBox, 400, 182);
		scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		leftLayout.add(scrollpane, 0, row, 4, 1);
		row++;
		
		//Start Button
		Button btnStart = new Button("Start");
		btnStart.setId(Ids.main_btnStart);
		btnStart.setDisable(true);
		btnStart.setPrefWidth(195);
		btnStart.setOnAction(EventHandlerFactory.getStartEventHandler(leftLayout));
		
		//+Bedingung button
		Button btnAddCondition = new Button("Bedingung hinzufügen");
		btnAddCondition.setOnAction(EventHandlerFactory.getAddConditionRowEventHandler(conditionBox));
		btnAddCondition.setPrefWidth(195);
		HBox buttonbox = GuiUtil.getWrappedInHBox(btnStart, btnAddCondition);
		buttonbox.setSpacing(10);
		buttonbox.setPrefWidth(400);
		buttonbox.setAlignment(Pos.CENTER);
		leftLayout.add(buttonbox, 0, row, 4, 1);
		row++;
		
	}
	
	
}
