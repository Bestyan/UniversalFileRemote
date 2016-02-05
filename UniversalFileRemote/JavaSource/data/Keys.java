package data;

public class Keys {
	//parameter hashmap conditions
	public static final String OperationCondition_fileType = "fileType"; 
	public static final String OperationCondition_conditionType = "conditionType"; 
	public static final String OperationCondition_otherFile = "otherFile"; 
	public static final String OperationCondition_conditionText = "conditionText"; 
	public static final String OperationCondition_active = "active"; 
	public static final String OperationCondition_textRegex = "textRegex"; 
	public static final String OperationCondition_negated = "negation"; 
	
	//verpflichtende Parameter
	public static final String Params_targetWorkspace = "targetWorkspace";
	public static final String Params_fileName = "fileName";
	public static final String Params_isRegex = "isRegex";
	public static final String Params_ignoreCase = "ignoreCase";
	public static final String Params_withPath = "withPath";
	public static final String Params_onlyProjects = "onlyProjects";
	public static final String Params_conditions = "conditions";
	public static final String Params_concernsAll = "concernsAll";
	
	//optionale Parameter (je nach Operation)
	public static final String Params_insertString = "insertString";
	public static final String Params_positionCondition = "positionCondition";
	public static final String Params_target = "target";
	public static final String Params_replacement = "replacement";
	public static final String Params_replaceRegex = "replaceRegex";
	public static final String Params_newName = "newName";
	public static final String Params_newPath = "newPath";
	public static final String Params_overwriteExisting = "overwriteExisting";
	public static final String Params_createDirs = "createDirs";
	public static final String Params_addString = "addString";
	public static final String Params_condition = "condition";
	public static final String Params_replacementGroup = "replacementGroup";
	
	//combobox Operationen
	public static final String Operation_anfuegen = "Anfügen";
	public static final String Operation_einfuegen = "Einfügen";
	public static final String Operation_ersetzen = "Ersetzen";
	public static final String Operation_dateiSuchen = "Datei suchen";
	public static final String Operation_dateiKopieren = "Datei kopieren";
	public static final String Operation_dateiUmbenennen = "Datei umbenennen";
	public static final String Operation_dateiVerschieben = "Datei verschieben";
	public static final String Operation_dateiLoeschen = "Datei löschen";
	public static final String Operation_ordnerSuchen = "Ordner suchen";
	public static final String Operation_ordnerKopieren = "Ordner kopieren";
	public static final String Operation_ordnerUmbenennen = "Ordner umbenennen";
	public static final String Operation_ordnerVerschieben = "Ordner verschieben";
	public static final String Operation_ordnerLoeschen = "Ordner löschen";
	
	//conditions fileType
	public static final String Condition_dieseDatei = "diese Datei";
	public static final String Condition_andereDatei = "andere Datei";
	public static final String Condition_existiert = "existiert";
	public static final String Condition_enthaelt = "enthält";
	public static final String Condition_beginntMit = "beginnt mit";
	public static final String Condition_endetMit = "endet mit";
	public static final String Condition_liegtIn = "liegt in";
	
	
}
