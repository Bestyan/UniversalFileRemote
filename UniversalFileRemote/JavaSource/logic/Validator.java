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
		Boolean replacementGroup = (Boolean) params.get(Keys.Params_replacementGroup);
		if(insertString == null || insertString.length() == 0){
			valid = false;
		}
		if(positionCondition == null){
			valid = false;
		}
		if(replacementGroup == null){
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
		Boolean replacementGroup = (Boolean) params.get(Keys.Params_replacementGroup);
		if(replacement == null){
			valid = false;
		}
		if(target == null || target.length() == 0){
			valid = false;
		}
		if(replaceRegex == null || replacementGroup == null){
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
		if(conditionType == ConditionType.liesIn && fileType != FileType.thisFile){
			return false;
		}
		return true;
	}
	
	public static boolean validateStartOperation(Pane layout){
		boolean valid = true;
		String operation = (String)((ComboBox<?>)layout.lookup("#" + Ids.main_comboOperation)).getValue();
		
		if(Util.isNullOrEmpty(operation)){
			return false;
		}
		HashMap<String, Object> params = GuiUtil.getParamsFor(layout, operation);
		
		switch(operation){
			case Keys.Operation_anfuegen: {
				valid = Validator.checkAddParams(params);
				break;
			}
			case Keys.Operation_einfuegen: {
				valid = Validator.checkInsertParams(params);
				break;
			}
			case Keys.Operation_ersetzen: {
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
				valid = Validator.checkRenameFilesParams(params);
				break;
			}
			case Keys.Operation_dateiVerschieben:
			case Keys.Operation_ordnerVerschieben:{
				valid = Validator.checkMoveOrCopyFilesParams(params);
				break;
			}
			case Keys.Operation_dateiKopieren:
			case Keys.Operation_ordnerKopieren:{
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
