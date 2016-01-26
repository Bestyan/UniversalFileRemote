package logic;

import java.util.HashMap;

import data.Ids;
import data.Keys;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import log.Log;
import logic.OperationCondition.ConditionType;
import logic.OperationCondition.FileType;
import util.GuiUtil;
import util.Util;

/**
 * dient der Auslagerung aller möglichen Plausibilitätsprüfungen
 * @author bschattenberg
 *
 */
public class Validator {
	/**
	 * 
	 * @param params
	 * @return {@code true} wenn alle Parameter gültig sind<br/>
	 * {@code false} wenn mindestens ein Parameter ungültig ist
	 */
	public static boolean checkAddParams(HashMap<String, Object> params){
		boolean valid = checkMandatoryParams(params);
		String addString = (String) params.get(Keys.Params_addString);
		if(addString == null || addString.length() == 0){
			valid = false;
		}
		return valid;
	}
	
	/**
	 * 
	 * @param params
	 * @return {@code true} wenn alle Parameter gültig sind<br/>
	 * {@code false} wenn mindestens ein Parameter ungültig ist
	 */
	public static boolean checkInsertParams(HashMap<String, Object> params){
		boolean valid = checkMandatoryParams(params);
		String insertString = (String) params.get(Keys.Params_insertString);
		String positionCondition = (String) params.get(Keys.Params_positionCondition);
		String target = (String) params.get(Keys.Params_target);
		if(insertString == null || insertString.length() == 0){
			valid = false;
		}
		if(positionCondition == null){
			valid = false;
		}
		if(target == null || target.length() == 0){
			valid = false;
		}
		return valid;
	}
	
	/**
	 * 
	 * @param params
	 * @return {@code true} wenn alle Parameter gültig sind<br/>
	 * {@code false} wenn mindestens ein Parameter ungültig ist
	 */
	public static boolean checkMandatoryParams(HashMap<String, Object> params){
		boolean valid = true;
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileName = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean absolute = (Boolean) params.get(Keys.Params_concernsAll);
		//conditions muss nicht geprüft werden wg validateOperationCondition
		if(targetWorkspace == null || targetWorkspace.length() == 0){
			valid = false;
		}
		if(fileName == null || fileName.length() == 0){
			valid = false;
		}
		if(isRegex == null){
			valid = false;
		}
		if(ignoreCase == null){
			valid = false;
		}
		if(withPath == null){
			valid = false;
		}
		if(onlyProjects == null){
			valid = false;
		}
		if(absolute == null){
			valid = false;
		}
		return valid;
	}
	
	public static boolean checkReplaceParams(HashMap<String, Object> params){
		boolean valid = checkMandatoryParams(params);
		String replacement = (String) params.get(Keys.Params_replacement);
		String target = (String) params.get(Keys.Params_target);
		Boolean replaceRegex = (Boolean) params.get(Keys.Params_replaceRegex);
		if(replacement == null){
			valid = false;
		}
		if(target == null || target.length() == 0){
			valid = false;
		}
		if(replaceRegex == null){
			valid = false;
		}
		return valid;
	}
	
	public static boolean checkRenameFilesParams(HashMap<String, Object> params){
		boolean valid = checkMandatoryParams(params);
		String newName = (String) params.get(Keys.Params_newName);
		if(newName == null || newName.length() == 0){
			valid = false;
		}
		return valid;
	}
	
	public static boolean checkMoveOrCopyFilesParams(HashMap<String, Object> params){
		boolean valid = checkMandatoryParams(params);
		String newPath = (String) params.get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) params.get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) params.get(Keys.Params_createDirs);
		if(newPath == null || newPath.length() == 0){
			valid = false;
		}
		if(overwriteExisting == null){
			valid = false;
		}
		if(createDirs == null){
			valid = false;
		}
		return valid;
	}
	
	/**
	 * prüft, ob die bedingung die nötigen Felder gesetzt hat, um aktiv zu sein
	 * @param box
	 * @return
	 */
	public static boolean validateOperationCondition(Pane layout){
		HashMap<String, Object> values = GuiUtil.getConditionValueMap(layout);
		OperationCondition.FileType fileType = (FileType) values.get(Keys.OperationCondition_fileType);
		OperationCondition.ConditionType conditionType = (ConditionType) values.get(Keys.OperationCondition_conditionType);
		String otherFile = (String) values.get(Keys.OperationCondition_otherFile);
		String conditionText = (String) values.get(Keys.OperationCondition_conditionText);
		if(fileType == FileType.otherFile){
			if(otherFile == null || otherFile.length() == 0){
				return false;
			}
		}
		if(conditionType != ConditionType.exists){
			if(conditionText == null || conditionText.length() == 0){
				return false;
			}
		}
		return true;
	}
	
	public static boolean validateStartOperation(Pane layout){
		boolean valid = true;
		HashMap<String, Object> params = GuiUtil.getMandatoryParams(layout.getScene());
		String operation = (String)((ComboBox<?>)layout.lookup("#" + Ids.main_comboOperation)).getValue();
		
		if(Util.isNullOrEmpty(operation)){
			return false;
		}
		
		switch(operation){
			case Keys.Operation_anfuegen: {
				String addString = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taAddString).getText();
				params.put(Keys.Params_addString, addString);
				valid = Validator.checkAddParams(params);
				break;
			}
			case Keys.Operation_einfuegen: {
				String insertString = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taInsertString).getText();
				String positionCondition = (String) GuiUtil.lookupComboBox(layout, "#" + Ids.parameter_comboPositionEinfuegen).getValue();
				String target = GuiUtil.lookupTextField(layout, "#" + Ids.parameter_tfPositionZielWert).getText();
				params.put(Keys.Params_insertString, insertString);
				params.put(Keys.Params_positionCondition, positionCondition);
				params.put(Keys.Params_target, target);
				valid = Validator.checkInsertParams(params);
				break;
			}
			case Keys.Operation_ersetzen: {
				String replacement = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taErsetzText).getText();
				String target = GuiUtil.lookupTextArea(layout, "#" + Ids.parameter_taErsetzZielText).getText();
				Boolean replaceRegex = new Boolean(GuiUtil.lookupCheckBox(layout, "#" + Ids.parameter_cbErsetzenRegex).isSelected());
				params.put(Keys.Params_replacement, replacement);
				params.put(Keys.Params_target, target);
				params.put(Keys.Params_replaceRegex, replaceRegex);
				valid = Validator.checkReplaceParams(params);
				break;
			}
			case Keys.Operation_dateiLoeschen:
			case Keys.Operation_ordnerLoeschen:{
				valid = Validator.checkMandatoryParams(params);
				break;
			}
			case Keys.Operation_dateiUmbenennen:
			case Keys.Operation_ordnerUmbenennen:{
				String newName = GuiUtil.lookupTextField(layout, "#" + Ids.parameter_tfNeuerDateiname).getText();
				params.put(Keys.Params_newName, newName);
				valid = Validator.checkRenameFilesParams(params);
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
				valid = Validator.checkMoveOrCopyFilesParams(params);
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
				valid = Validator.checkMoveOrCopyFilesParams(params);
				break;
			}
			case Keys.Operation_dateiSuchen:
			case Keys.Operation_ordnerSuchen:{
				valid = Validator.checkMandatoryParams(params);
				break;
			}
			default:{
				valid = false;
				Log.log("Undefinierter case in Validator.validateStartOperation()", Log.Level.FATAL);
			}
		}
		return valid;
	}
}
