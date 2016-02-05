package logic;

import java.io.File;

import log.Log;
import util.Util;

public class InsertPosition {
	
	public enum Type{
		insertBefore, insertAfter;
	}
	
	public InsertPosition(Operation op, Type type){
		this.setOperation(op);
		this.setType(type);
	}
	
	private Operation operation;
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	private Type type;
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public Object getData(String key){
		return this.getOperation().getOperationData().get(key);
	}
	
	/**
	 * @param file
	 */
	public void insert(File file, String insertString){
		String logMsg = "";
		switch(this.getType()){
			case insertBefore:
			{
				String target = (String) this.getData("target");
				String fileText = Util.readFile(file);
				if(fileText.contains(target)){
					String part1 = fileText.substring(0, fileText.indexOf(target));
					String part2 = fileText.substring(part1.length(), fileText.length());
					String result = part1 + insertString + part2;
					Util.writeFile(file, result);
					logMsg += "\r\n" + "Text eingefügt in " + file.getAbsolutePath();
				} else{
					logMsg += "\r\n" + "[einfügen] Zieltext nicht gefunden";
				}
				break;
			}
			case insertAfter:
			{
				String target = (String) this.getData("target");
				String fileText = Util.readFile(file);
				if(fileText.contains(target)){
					String part1 = fileText.substring(0, fileText.indexOf(target) + target.length());
					String part2 = fileText.substring(part1.length(), fileText.length());
					String result = part1 + insertString + part2;
					Util.writeFile(file, result);
					logMsg += "\r\n" + "Text eingefügt in " + file.getAbsolutePath();
				} else{
					logMsg += "\r\n" + "[einfügen] Zieltext nicht gefunden";
				}
				break;
			}
			default:
				Log.log("unmöglicher Type Wert in InsertPosition.insert(" + file.getAbsolutePath() + ")", Log.Level.ERROR);
		}
		Log.log(logMsg, Log.Level.INFO);
	}
}
