package util;

import java.util.ArrayList;
import java.util.HashMap;

import data.Ids;
import data.Keys;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import log.Log;
import logic.OperationCondition;
import logic.OperationCondition.ConditionType;
import logic.OperationCondition.FileType;

public class GuiUtil {
	
	public static StackPane getWrappedInPane(Node node){
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.getChildren().add(node);
		return pane;
	}
	
	public static TextField lookupTextField(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof TextField)){
			return (TextField) result;
		} else{
			Log.log("Util.lookupTextField(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static TextArea lookupTextArea(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof TextArea)){
			return (TextArea) result;
		} else{
			Log.log("Util.lookupTextArea(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static CheckBox lookupCheckBox(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof CheckBox)){
			return (CheckBox) result;
		} else{
			Log.log("Util.lookupCheckBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static RadioButton lookupRadioButton(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof RadioButton)){
			return (RadioButton) result;
		} else{
			Log.log("Util.lookupRadioButton(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static ComboBox<?> lookupComboBox(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof ComboBox)){
			return (ComboBox<?>) result;
		} else{
			Log.log("Util.lookupComboBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static TextField lookupTextField(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof TextField)){
			return (TextField) result;
		} else{
			Log.log("Util.lookupTextField(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static TextArea lookupTextArea(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof TextArea)){
			return (TextArea) result;
		} else{
			Log.log("Util.lookupTextArea(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static CheckBox lookupCheckBox(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof CheckBox)){
			return (CheckBox) result;
		} else{
			Log.log("Util.lookupCheckBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static RadioButton lookupRadioButton(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof RadioButton)){
			return (RadioButton) result;
		} else{
			Log.log("Util.lookupRadioButton(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static ComboBox<?> lookupComboBox(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof ComboBox)){
			return (ComboBox<?>) result;
		} else{
			Log.log("Util.lookupComboBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static Scene getScene(Event event){
		return ((Node)event.getSource()).getScene();
	}
	
	public static ScrollPane getWrappedInScrollPane(Node node, int width, int height){
		ScrollPane wrapper = new ScrollPane();
		wrapper.setContent(node);
		wrapper.setPrefSize(width, height);
		return wrapper;
	}
	
	public static HBox getWrappedInHBox(Node... nodes){
		HBox result = new HBox();
		for(Node node : nodes){
			result.getChildren().add(node);
		}
		return result;
	}
	
	public static HashMap<String, Object> getConditionValueMap(Pane layout){
		HashMap<String, Object> result = new HashMap<>();
		String fileTypeString = (String) ((ComboBox<?>) layout.lookup("#" + Ids.condition_comboFileType)).getValue();
		String conditionTypeString = (String) ((ComboBox<?>) layout.lookup("#" + Ids.condition_comboConditionType)).getValue();
		OperationCondition.FileType fileType = null;
		OperationCondition.ConditionType conditionType = null;
		String otherFileString = null;
		String conditionTextString = ((TextArea) layout.lookup("#" + Ids.condition_taConditionText)).getText();
		boolean isActive = ((CheckBox) layout.lookup("#" + Ids.condition_cbActive)).isSelected();
		boolean textRegex = ((CheckBox) layout.lookup("#" + Ids.condition_cbTextRegex)).isSelected();
		boolean negation = ((CheckBox) layout.lookup("#" + Ids.condition_cbNegation)).isSelected();
		if(fileTypeString != null){
			switch(fileTypeString){
				case Keys.Condition_andereDatei:{
					otherFileString = ((TextField) layout.lookup("#" + Ids.condition_tfAndereDatei)).getText();
					fileType = FileType.otherFile;
					break;
				}
				case Keys.Condition_dieseDatei:{
					fileType = FileType.thisFile;
					break;
				}
				default:{
					Log.log("Unmöglicher fileTypeString Wert in GuiUtil.getConditionValueMap()", Log.Level.FATAL);
					return null;
				}
			}
		}
		if(conditionTypeString != null){
			switch(conditionTypeString){
				case Keys.Condition_existiert:
					conditionType = ConditionType.exists;
					break;
				case Keys.Condition_enthaelt:
					conditionType = ConditionType.contains;
					break;
				case Keys.Condition_beginntMit:
					conditionType = ConditionType.startsWith;
					break;
				case Keys.Condition_endetMit:
					conditionType = ConditionType.endsWith;
					break;
				default:
					Log.log("Unmöglicher conditionTypeString Wert in GuiUtil.getConditionValueMap()", Log.Level.FATAL);
					return null;
			}
		}
		result.put(Keys.OperationCondition_fileType, fileType);
		result.put(Keys.OperationCondition_conditionType, conditionType);
		result.put(Keys.OperationCondition_otherFile, otherFileString);
		result.put(Keys.OperationCondition_conditionText, conditionTextString);
		result.put(Keys.OperationCondition_active, new Boolean(isActive));
		result.put(Keys.OperationCondition_textRegex, new Boolean(textRegex));
		result.put(Keys.OperationCondition_negated, new Boolean(negation));
		return result;
	}
	
	public static HashMap<String, Object> getMandatoryParams(Scene scene){
		HashMap<String, Object> result = new HashMap<>();
		String targetWorkspace = GuiUtil.lookupTextField(scene, "#" + Ids.main_tfBereich).getText().trim();
		String fileName = GuiUtil.lookupTextArea(scene, "#" + Ids.main_tfDateiname).getText().trim();
		Boolean isRegex = new Boolean(GuiUtil.lookupCheckBox(scene, "#" + Ids.main_cbDateinameRegex).isSelected());
		Boolean ignoreCase = new Boolean(GuiUtil.lookupCheckBox(scene, "#" + Ids.main_cbDateinameIgnoreCase).isSelected());
		Boolean withPath = new Boolean(GuiUtil.lookupCheckBox(scene, "#" + Ids.main_cbDateinameMitPfad).isSelected());
		Boolean onlyProjects = new Boolean(GuiUtil.lookupCheckBox(scene, "#" + Ids.main_cbNurProjekte).isSelected());
		Boolean absoluteFile = new Boolean(GuiUtil.lookupCheckBox(scene, "#" + Ids.main_cbConcernsAll).isSelected());
		
		Pane conditionsPane = (Pane) scene.lookup("#" + Ids.main_paneCondition);
		ArrayList<OperationCondition> conditions = new ArrayList<>();
		for(Node node : conditionsPane.getChildren()){
			if(node instanceof Pane){
				Pane pane = (Pane) node;
				conditions.add(OperationCondition.getOperationConditionFromConditionLine(pane));
			}
		}
		result.put(Keys.Params_targetWorkspace, targetWorkspace);
		result.put(Keys.Params_fileName, fileName);
		result.put(Keys.Params_isRegex, isRegex);
		result.put(Keys.Params_ignoreCase, ignoreCase);
		result.put(Keys.Params_withPath, withPath);
		result.put(Keys.Params_onlyProjects, onlyProjects);
		result.put(Keys.Params_conditions, conditions);
		result.put(Keys.Params_concernsAll, absoluteFile);
		return result;
	}
	
	public static HashMap<String, Object> getParamsFor(Pane layout, String operation){
		HashMap<String, Object> params = getMandatoryParams(layout.getScene());
		switch(operation){
			case Keys.Operation_anfuegen: {
				String addString = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taAddString).getText();
				params.put(Keys.Params_addString, addString);
				break;
			}
			case Keys.Operation_einfuegen: {
				String insertString = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taInsertString).getText();
				String positionCondition = (String) GuiUtil.lookupComboBox(layout, "#" + Ids.parameter_comboPositionEinfuegen).getValue();
				String target = GuiUtil.lookupTextField(layout, "#" + Ids.parameter_tfPositionZielWert).getText();
				params.put(Keys.Params_insertString, insertString);
				params.put(Keys.Params_positionCondition, positionCondition);
				params.put(Keys.Params_target, target);
				break;
			}
			case Keys.Operation_ersetzen: {
				String replacement = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taErsetzText).getText();
				String target = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taErsetzZielText).getText();
				Boolean replaceRegex = new Boolean(GuiUtil.lookupCheckBox(layout, "#" + Ids.parameter_cbErsetzenRegex).isSelected());
				params.put(Keys.Params_replacement, replacement);
				params.put(Keys.Params_target, target);
				params.put(Keys.Params_replaceRegex, replaceRegex);
				break;
			}
			case Keys.Operation_dateiLoeschen:
			case Keys.Operation_ordnerLoeschen:{
				break;
			}
			case Keys.Operation_dateiUmbenennen:
			case Keys.Operation_ordnerUmbenennen:{
				String newName = GuiUtil.lookupTextField(layout, "#" + Ids.parameter_tfNeuerDateiname).getText();
				params.put(Keys.Params_newName, newName);
				break;
			}
			case Keys.Operation_dateiVerschieben:
			case Keys.Operation_ordnerVerschieben:{
				String newPath = GuiUtil.lookupTextField(layout, "#" + Ids.parameter_tfNeuerSpeicherort).getText();
				params.put(Keys.Params_newPath, newPath);
				Boolean overwriteExisting = new Boolean(GuiUtil.lookupCheckBox(layout, "#" + Ids.parameter_cbDateienUeberschreiben).isSelected());
				params.put(Keys.Params_overwriteExisting, overwriteExisting);
				Boolean createDirs = new Boolean(GuiUtil.lookupCheckBox(layout, "#" + Ids.parameter_cbVerzeichnisseErstellen).isSelected());
				params.put(Keys.Params_createDirs, createDirs);
				break;
			}
			case Keys.Operation_dateiKopieren:
			case Keys.Operation_ordnerKopieren:{
				String newPath = GuiUtil.lookupTextField(layout, "#" + Ids.parameter_tfNeuerSpeicherort).getText();
				params.put(Keys.Params_newPath, newPath);
				Boolean overwriteExisting = new Boolean(GuiUtil.lookupCheckBox(layout, "#" + Ids.parameter_cbDateienUeberschreiben).isSelected());
				params.put(Keys.Params_overwriteExisting, overwriteExisting);
				Boolean createDirs = new Boolean(GuiUtil.lookupCheckBox(layout, "#" + Ids.parameter_cbVerzeichnisseErstellen).isSelected());
				params.put(Keys.Params_createDirs, createDirs);
				break;
			}
			case Keys.Operation_dateiSuchen:
			case Keys.Operation_ordnerSuchen:{
				break;
			}
			default:{
				Log.log("Undefinierter case in GuiUtil.getParamsFor()", Log.Level.FATAL);
				return new HashMap<String, Object>();
			}
		}
		return params;
	}
}
