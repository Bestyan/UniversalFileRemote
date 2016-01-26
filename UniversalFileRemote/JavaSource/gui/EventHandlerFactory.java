package gui;

import java.io.File;
import java.util.HashMap;

import data.Ids;
import data.Keys;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import log.Log;
import logic.Logic;
import util.GuiUtil;

public class EventHandlerFactory {
	/**
	 * ändert dynamisch die Parameter-Felder je nach ausgewählter Operation
	 * @author bschattenberg
	 *
	 */
	public static EventHandler<ActionEvent> getOperationsEventHandler(){
		return new EventHandler<ActionEvent>() {
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				ComboBox<String> source = (ComboBox<String>)event.getSource();
				Scene scene = ((Node)event.getSource()).getScene();
				GridPane parameterPane = (GridPane) scene.lookup("#" + Ids.main_paneParameter);
				Pane layout = (Pane) parameterPane.getParent();
				setConcernsAllEnabled(source.getValue(), scene);
				
				parameterPane.getChildren().removeAll(parameterPane.getChildren());
				switch(source.getValue()){
					case Keys.Operation_anfuegen: {
						setParametersForAdd(parameterPane, layout);
						break;
					}
					case Keys.Operation_einfuegen: {
						setParametersForInsert(parameterPane, layout);
						break;
					}
					case Keys.Operation_ersetzen: {
						setParametersForReplace(parameterPane, layout);
						break;
					}
					case Keys.Operation_dateiSuchen: {
						setParametersBlank(parameterPane);
						break;
					}
					case Keys.Operation_dateiLoeschen: {
						setParametersBlank(parameterPane);
						break;
					}
					case Keys.Operation_dateiUmbenennen: {
						setParametersRename(parameterPane, layout);
						break;
					}
					case Keys.Operation_dateiVerschieben: {
						setParametersMoveFile(parameterPane, layout);
						break;
					}
					case Keys.Operation_dateiKopieren: {
						setParametersCopyFile(parameterPane, layout);
						break;
					}
					case Keys.Operation_ordnerSuchen: {
						setParametersBlank(parameterPane);
						break;
					}
					case Keys.Operation_ordnerLoeschen: {
						setParametersBlank(parameterPane);
						break;
					}
					case Keys.Operation_ordnerUmbenennen: {
						setParametersRename(parameterPane, layout);
						break;
					}
					case Keys.Operation_ordnerVerschieben: {
						setParametersMoveFile(parameterPane, layout);
						break;
					}
					case Keys.Operation_ordnerKopieren: {
						setParametersCopyFile(parameterPane, layout);
						break;
					}
					default:
						//do nothing
				}
			}
		};
	}
	
	protected static void setParametersCopyFile(GridPane parameterPane, Pane layout) {
		Label lbCopyTo = new Label("Kopieren nach");
		parameterPane.add(lbCopyTo, 0, 0);
		
		TextField tfCopyTo = new TextField();
		tfCopyTo.setId(Ids.parameter_tfNeuerSpeicherort);
		tfCopyTo.setTooltip(TooltipFactory.getPlaceholderTooltip());
		tfCopyTo.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(tfCopyTo, 1, 0);
		
		CheckBox cbOverwrite = new CheckBox("ggf. existierende Dateien überschreiben");
		cbOverwrite.setId(Ids.parameter_cbDateienUeberschreiben);
		parameterPane.add(cbOverwrite, 1, 1, 2, 1);
		
		CheckBox cbCreateDirs = new CheckBox("ggf. fehlende Verzeichnisse erstellen");
		cbCreateDirs.setId(Ids.parameter_cbVerzeichnisseErstellen);
		parameterPane.add(cbCreateDirs, 1, 2, 2, 1);
	}
	
	protected static void setParametersMoveFile(GridPane parameterPane, Pane layout) {
		Label lbMoveTo = new Label("Verschieben nach");
		parameterPane.add(lbMoveTo, 0, 0);
		
		TextField tfMoveTo = new TextField();
		tfMoveTo.setId(Ids.parameter_tfNeuerSpeicherort);
		tfMoveTo.setTooltip(TooltipFactory.getPlaceholderTooltip());
		tfMoveTo.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(tfMoveTo, 1, 0);
		
		CheckBox cbOverwrite = new CheckBox("ggf. existierende Dateien überschreiben");
		cbOverwrite.setId(Ids.parameter_cbDateienUeberschreiben);
		parameterPane.add(cbOverwrite, 1, 1, 2, 1);
		
		CheckBox cbCreateDirs = new CheckBox("ggf. fehlende Verzeichnisse erstellen");
		cbCreateDirs.setId(Ids.parameter_cbVerzeichnisseErstellen);
		parameterPane.add(cbCreateDirs, 1, 2, 2, 1);
	}
	
	protected static void setParametersRename(GridPane parameterPane, Pane layout) {
		Label lbNewName = new Label("Neuer Name");
		parameterPane.add(lbNewName, 0, 0);
		
		TextField tfNewName = new TextField();
		tfNewName.setId(Ids.parameter_tfNeuerDateiname);
		tfNewName.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(tfNewName, 1, 0);
	}
	
	protected static void setParametersBlank(GridPane parameterPane) {
		Label lbDeleteFile = new Label("keine Parameter");
		parameterPane.add(lbDeleteFile, 0, 0);
	}
	
	protected static void setParametersForReplace(GridPane parameterPane, Pane layout) {
		Label lbReplaceString = new Label("Ersetzt wird");
		parameterPane.add(GuiUtil.getWrappedInPane(lbReplaceString), 0, 0);
		
		CheckBox cbRegex = new CheckBox("Regex");
		cbRegex.setId(Ids.parameter_cbErsetzenRegex);
		parameterPane.add(cbRegex, 0, 1);
		
		TextArea taReplaceString = new TextArea();
		taReplaceString.setPrefRowCount(4);
		taReplaceString.setPrefWidth(250);
		taReplaceString.setId(Ids.parameter_taErsetzZielText);
		taReplaceString.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(GuiUtil.getWrappedInPane(taReplaceString), 1, 0, 2, 3);
		
		Label lbTargetString = new Label("Ersetzen durch");
		parameterPane.add(GuiUtil.getWrappedInPane(lbTargetString), 0, 3);
		
		TextArea taTargetString = new TextArea();
		taTargetString.setPrefRowCount(4);
		taTargetString.setPrefWidth(250);
		taTargetString.setId(Ids.parameter_taErsetzText);
		taTargetString.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(GuiUtil.getWrappedInPane(taTargetString), 1, 3, 2, 1);
	}
	
	protected static void setParametersForInsert(GridPane parameterPane, Pane layout) {
		Label lbAddString = new Label("Eingefügt wird");
		parameterPane.add(GuiUtil.getWrappedInPane(lbAddString), 0, 0);
		
		TextArea taAddString = new TextArea();
		taAddString.setPrefRowCount(4);
		taAddString.setPrefWidth(250);
		taAddString.setId(Ids.parameter_taInsertString);
		taAddString.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(GuiUtil.getWrappedInPane(taAddString), 1, 0, 2, 1);
		
		Label lbInsertPosition = new Label("Position");
		parameterPane.add(lbInsertPosition, 0, 1);
		ComboBox<String> comboPosition = new ComboBox<>(FXCollections.observableArrayList("nach", "vor"));
		comboPosition.setId(Ids.parameter_comboPositionEinfuegen);
		comboPosition.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(comboPosition, 1, 1);
		TextField tfPosition = new TextField();
		tfPosition.setId(Ids.parameter_tfPositionZielWert);
		tfPosition.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(tfPosition, 2, 1);
	}
	
	protected static void setParametersForAdd(GridPane parameterPane, Pane layout) {
		Label lbAddString = new Label("Angefügt wird");
		parameterPane.add(GuiUtil.getWrappedInPane(lbAddString), 0, 0);
		
		TextArea taAddString = new TextArea();
		taAddString.setPrefRowCount(4);
		taAddString.setPrefWidth(250);
		taAddString.setId(Ids.parameter_taAddString);
		taAddString.textProperty().addListener(ListenerFactory.getStartOperationValidatorChangeListener(layout));
		parameterPane.add(GuiUtil.getWrappedInPane(taAddString), 1, 0, 2, 1);
	}
	
	public static void setConcernsAllEnabled(String value, Scene scene){
		CheckBox concernsAllFolders = GuiUtil.lookupCheckBox(scene, "#" + Ids.main_cbConcernsAll);
		switch(value){
			case Keys.Operation_anfuegen:
			case Keys.Operation_einfuegen:
			case Keys.Operation_ersetzen:
			case Keys.Operation_dateiSuchen:
			case Keys.Operation_dateiUmbenennen:
			case Keys.Operation_dateiVerschieben:
			case Keys.Operation_ordnerSuchen:
			case Keys.Operation_ordnerVerschieben:
			case Keys.Operation_ordnerUmbenennen:
			case Keys.Operation_dateiLoeschen:
			case Keys.Operation_ordnerLoeschen:
				concernsAllFolders.setSelected(false);
				concernsAllFolders.setDisable(true);
				break;
			case Keys.Operation_dateiKopieren:
			case Keys.Operation_ordnerKopieren:
				concernsAllFolders.setDisable(false);
				break;
			default:
				//do nothing
		}
	}
	
	/**
	 * abschicken der Operation über den Startbutton
	 * @return
	 */
	public static EventHandler<ActionEvent> getStartEventHandler(Pane layout){
		return new EventHandler<ActionEvent>(){
			@SuppressWarnings("unchecked")
			@Override
			public void handle(ActionEvent event) {
				try{
					Scene scene = ((Node)event.getSource()).getScene();
					ComboBox<String> operations = (ComboBox<String>)scene.lookup("#" + Ids.main_comboOperation);
					HashMap<String, Object> params = GuiUtil.getParamsFor(layout, operations.getValue());

					switch(operations.getValue()){
						case Keys.Operation_anfuegen: {
							Logic.add(params);
							break;
						}
						case Keys.Operation_einfuegen: {
							Logic.insertIntoFiles(params);
							break;
						}
						case Keys.Operation_ersetzen: {
							Logic.replace(params);
							break;
						}
						case Keys.Operation_dateiLoeschen: {
							Logic.deleteFiles(params);
							break;
						}
						case Keys.Operation_dateiUmbenennen: {
							Logic.rename(params);
							break;
						}
						case Keys.Operation_dateiVerschieben: {
							Logic.moveFiles(params);
							break;
						}
						case Keys.Operation_dateiKopieren: {
							Logic.copyFiles(params);
							break;
						}
						case Keys.Operation_dateiSuchen: {
							Logic.searchFilenames(params);
							break;
						}
						case Keys.Operation_ordnerLoeschen: {
							Logic.deleteFolders(params);
							break;
						}
						case Keys.Operation_ordnerUmbenennen: {
							Logic.renameFolders(params);
							break;
						}
						case Keys.Operation_ordnerVerschieben: {
							Logic.moveFolders(params);
							break;
						}
						case Keys.Operation_ordnerKopieren: {
							Logic.copyFolders(params);
							break;
						}
						case Keys.Operation_ordnerSuchen: {
							Logic.searchFolders(params);
							break;
						}
						default:
							//do nothing
					}
				} catch(Exception e){
					Log.log(e);
				}
			}
		};
	}
	
	/**
	 * Button Konsole löschen
	 * @return
	 */
	public static EventHandler<ActionEvent> getClearConsoleEventHandler(){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Log.clearConsole();
			}
		};
	}
	
	/**
	 * Button für Explorer zur Workspaceauswahl
	 * @param primaryStage
	 * @return
	 */
	public static EventHandler<ActionEvent> getWorkspaceChooserEventHandler(Stage primaryStage){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Scene scene = ((Node)event.getSource()).getScene();
				
				DirectoryChooser directoryChooser = new DirectoryChooser();
                File selectedDirectory = directoryChooser.showDialog(primaryStage);
                
                if(selectedDirectory != null){
                	GuiUtil.lookupTextField(scene, "#" + Ids.main_tfBereich).setText(selectedDirectory.getAbsolutePath() + "\\");
                }
			}
		};
	}
	
	/**
	 * Abschicken eines eigenen Log-Eintrags
	 * @return
	 */
	public static EventHandler<ActionEvent> getCustomLogMessageEventHandler(){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Scene scene = GuiUtil.getScene(event);
				TextField textfield = GuiUtil.lookupTextField(scene, "#" + Ids.main_tfLogEingabe);
				synchronized(textfield){
					Log.log(textfield.getText(), Log.Level.INFO);
					textfield.setText("");
				}
			}
		};
	}
	
	/**
	 * bedingungsauswahl "diese Datei" / "andere Datei"
	 * @param conditionBox
	 * @return
	 */
	public static EventHandler<ActionEvent> getConditionFileTypeEventHandler(Pane conditionBox){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				String selection = (String)((ComboBox<?>)conditionBox.lookup("#" + Ids.condition_comboFileType)).getValue();
				TextField tfOtherFile = (TextField)conditionBox.lookup("#" + Ids.condition_tfAndereDatei);
				if(selection.equals(Keys.Condition_dieseDatei)){
					tfOtherFile.setDisable(true);
				}else{
					tfOtherFile.setDisable(false);
				}
			}
		};
	}
	
	/**
	 * Bedingungsauswahl "existiert" / enthält / etc
	 * @param conditionBox
	 * @return
	 */
	public static EventHandler<ActionEvent> getConditionTypeEventHandler(Pane conditionBox){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				String selection = (String)((ComboBox<?>)conditionBox.lookup("#" + Ids.condition_comboConditionType)).getValue();
				TextArea taConditionText = (TextArea)conditionBox.lookup("#" + Ids.condition_taConditionText);
				if(selection.equals(Keys.Condition_existiert)){
					taConditionText.setDisable(true);
				}else{
					taConditionText.setDisable(false);
				}
			}
		};
	}
	
	/**
	 * entfernt die bedingungszeile
	 * @param conditionBox
	 * @return
	 */
	public static EventHandler<ActionEvent> getRemoveConditionEventHandler(Pane conditionBox){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Pane parent = (Pane) conditionBox.getParent();
				if(parent != null){
					parent.getChildren().remove(conditionBox);
				}
			}
		};
	}
	
	/**
	 * fügt eine neue Bedingungszeile hinzu
	 * @param parentBox
	 * @return
	 */
	public static EventHandler<ActionEvent> getAddConditionRowEventHandler(Pane parentBox){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				ObservableList<Node> list = parentBox.getChildren();
				list.add(ElementFactory.getOperationConditionLine());
			}
		};
	}
}
