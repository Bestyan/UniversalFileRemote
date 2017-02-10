package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.Keys;
import javafx.scene.layout.Pane;
import log.Log;
import util.GuiUtil;
import util.Util;

/**
 * has nothing in common with InsertCondition
 * @author bschattenberg
 *
 */
public class OperationCondition {
	public enum FileType{
		thisFile, otherFile;
	}
	public enum ConditionType{
		exists, contains, startsWith, endsWith, liesIn;
	}
	
	public static ArrayList<String> getOperationConditionFileTypeList(){
		ArrayList<String> list = new ArrayList<>();
		list.add(Keys.Condition_dieseDatei);
		list.add(Keys.Condition_andereDatei);
		return list;
	}
	
	public static ArrayList<String> getOperationConditionTypeList(){
		ArrayList<String> list = new ArrayList<>();
		list.add(Keys.Condition_existiert);
		list.add(Keys.Condition_enthaelt);
		list.add(Keys.Condition_beginntMit);
		list.add(Keys.Condition_endetMit);
		list.add(Keys.Condition_liegtIn);
		return list;
	}
	
	/**
	 * @param type
	 * @param operation
	 */
	public OperationCondition(FileType fileType, ConditionType type, Operation operation, String conditionText, String fileText, boolean isTextRegex, boolean isNegated, boolean active){
		this.setFileType(fileType);
		this.setType(type);
		this.setOperation(operation);
		this.setActive(active);
		this.setTextRegex(isTextRegex);
		this.setNegated(isNegated);
		this.setConditionText(conditionText);
		this.setFileText(fileText);
	}
	
	private ConditionType type;
	public ConditionType getConditionType() {
		return type;
	}
	protected void setType(ConditionType type) {
		this.type = type;
	}
	
	private Operation operation;
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public Object getData(String key){
		if(this.getOperation() != null){
			return this.getOperation().getOperationData().get(key);
		} else{
			return null;
		}
	}
	public boolean isMet(File file){
		if(!this.isActive()){
			return true; //nicht aktive Bedingungen sind immer erfüllt
		}
		
		File targetFile = null;
		if(fileType == FileType.thisFile){
			targetFile = file;
		} else if(fileType == FileType.otherFile){
			String newPath = this.getFileText();
			String actualPath = Util.replacePathPlaceholders(newPath, file, true);
			targetFile = new File(actualPath);
		} else{
			Log.log("unmöglicher fileType Wert in OperationCondition.isMet", Log.Level.FATAL);
		}
		
		boolean isMet = true;
		switch(this.getConditionType()){
			case exists:{
				isMet = targetFile.exists();
				break;
			}
			case contains:{
				isMet = Util.fileContains(targetFile, this.getConditionText(), this.isTextRegex());
				break;
			}
			case startsWith:{
				isMet = Util.fileStartsWith(targetFile, this.getConditionText(), this.isTextRegex());
				break;
			}
			case endsWith:{
				isMet = Util.fileEndsWith(targetFile, this.getConditionText(), this.isTextRegex());
				break;
			}
			case liesIn:{
				isMet = Util.fileLiesIn(targetFile, this.getConditionText(), this.isTextRegex());
				break;
			}
			default:{
				isMet = false;
				Log.log("unmöglicher conditionType Wert in OperationCondition.isMet", Log.Level.FATAL);
			}
		}
		
		if(this.isNegated()){
			isMet = !isMet;
		}
		return isMet;
	}
	
	private FileType fileType;
	public FileType getFileType() {
		return fileType;
	}
	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}
	
	private boolean active, negated, textRegex;
	public boolean isNegated() {
		return negated;
	}
	public void setNegated(boolean negated) {
		this.negated = negated;
	}

	public boolean isTextRegex() {
		return textRegex;
	}
	public void setTextRegex(boolean textRegex) {
		this.textRegex = textRegex;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	String conditionText, fileText;
	public String getConditionText() {
		return conditionText;
	}
	public void setConditionText(String conditionText) {
		this.conditionText = conditionText;
	}

	public String getFileText() {
		return fileText;
	}
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}

	public static OperationCondition getOperationConditionFromConditionLine(Pane layout){
		HashMap<String, Object> values = GuiUtil.getConditionValueMap(layout);
		FileType fileType = (FileType) values.get(Keys.OperationCondition_fileType);
		ConditionType conditionType = (ConditionType) values.get(Keys.OperationCondition_conditionType);
		String conditionText = (String) values.get(Keys.OperationCondition_conditionText);
		String otherFile = (String)	values.get(Keys.OperationCondition_otherFile);
		boolean negated = (Boolean) values.get(Keys.OperationCondition_negated);
		boolean isTextRegex = (Boolean) values.get(Keys.OperationCondition_textRegex);
		boolean isActive = (Boolean) values.get(Keys.OperationCondition_active);
		OperationCondition condition = new OperationCondition(fileType, conditionType, null, conditionText, otherFile, isTextRegex, negated, isActive);
		return condition;
	}
	
	public String toString(File file){
		String result = ">";
		if(this.getFileType() == FileType.thisFile){
			result += "diese Datei '" + file.getAbsolutePath() + "' ";
		} else{
			result += "andere Datei '" + new File(Util.replacePathPlaceholders(this.getFileText(), file, true)).getAbsolutePath() + "' ";
		}
		
		switch(this.getConditionType()){
			case exists:{
				result += "existiert " + (this.isNegated() ? "nicht" : "");
				break;
			}
			case contains:{
				result += "enthält " + (this.isNegated() ? "nicht " : "") + "\"" + this.getConditionText() + "\"" + (this.isTextRegex() ? " (Regex)" : "");
				break;
			}
			case startsWith:{
				result += "beginnt " + (this.isNegated() ? "nicht " : "") + "mit \"" + this.getConditionText() + "\"" + (this.isTextRegex() ? " (Regex)" : "");
				break;
			}
			case endsWith:{
				result += "endet " + (this.isNegated() ? "nicht " : "") + "mit \"" + this.getConditionText() + "\"" + (this.isTextRegex() ? " (Regex)" : "");
				break;
			}
			case liesIn:{
				result += "liegt " + (this.isNegated() ? "nicht " : "") + "in \"" + Util.replacePathPlaceholders(this.getConditionText(), file, false) + "\"" + (this.isTextRegex() ? " (Regex)" : "");
				break;
			}
			default:{
				Log.log("impossible conditionType state in OperationCondition.toString", Log.Level.FATAL);
			}
		}
		result += "<";
		return result;
	}
}
