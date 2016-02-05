package logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import data.Keys;
import log.Log;
import util.Util;

@SuppressWarnings("unchecked")
public class Operation {
	public enum Type{
		insert, add, replace, deleteFile, renameFile, moveFile, copyFile, copyAbsoluteFile;
	}

	public Operation(Type type){
		this.setType(type);
	}

	private Type type;
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}

	private static ArrayList<String> operationsList;
	public static ArrayList<String> getOperationsList(){
		if(operationsList == null){
			operationsList = new ArrayList<String>();
			operationsList.add(Keys.Operation_anfuegen);
			operationsList.add(Keys.Operation_einfuegen);
			operationsList.add(Keys.Operation_ersetzen);
			operationsList.add(Keys.Operation_dateiSuchen);
			operationsList.add(Keys.Operation_dateiKopieren);
			operationsList.add(Keys.Operation_dateiUmbenennen);
			operationsList.add(Keys.Operation_dateiVerschieben);
			operationsList.add(Keys.Operation_dateiLoeschen);
			operationsList.add(Keys.Operation_ordnerSuchen);
			operationsList.add(Keys.Operation_ordnerKopieren);
			operationsList.add(Keys.Operation_ordnerUmbenennen);
			operationsList.add(Keys.Operation_ordnerVerschieben);
			operationsList.add(Keys.Operation_ordnerLoeschen);
		}
		return operationsList;
	}

	private HashMap<String, Object> operationData = new HashMap<>();
	/**
	 * erwartet wird:<ul>
	 * 	<li>delete
	 * 		<ul>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 			<li>deleteString - String</li>
	 * 			<li>isRegex - Boolean</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>insert
	 * 		<ul>
	 * 			<li>condition - Condition/null</li>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 			<li>insertString - String</li>
	 * 			<li>(target - String)</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>add
	 * 		<ul>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 			<li>addString - String</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>replace
	 * 		<ul>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 			<li>replaceString - String</li>
	 * 			<li>replacement - String</li>
	 * 			<li>isRegex - Boolean</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>deleteFile
	 * 		<ul>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>renameFile
	 * 		<ul>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 			<li>oldName - String</li>
	 * 			<li>newName - String</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>moveFile
	 * 		<ul>
	 * 			<li>conditions- ArrayList<OperationCondition>/null</li>
	 * 			<li>newPath - String</li>
	 * 			<li>absolutePath - Boolean</li>
	 * 		</ul>
	 * 	</li>
	 * 	<li>copyFile
	 * 		<ul>
	 * 			<li>conditions - ArrayList<OperationCondition>/null</li>
	 * 			<li>newPath - String</li>
	 * 			<li>absolutePath - Boolean</li>
	 * 			<li>relativeTo - ParentLevel/null</li>
	 * 		</ul>
	 * 	</li>
	 * 
	 * </ul>
	 * @return
	 */
	public HashMap<String, Object> getOperationData() {
		return operationData;
	}
	public void setOperationData(HashMap<String, Object> operationData) {
		this.operationData = operationData;
	}

	public void start(ArrayList<File> files){
		for(File f : files){
			this.operate(f);
		}
	}

	protected void operate(File f){
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) this.getOperationData().get(Keys.Params_conditions);
		for(OperationCondition con : conditions){
			if(!con.isMet(f)){
				Log.log("Bedingung " + con.toString(f) + " nicht erfüllt in " + f.getAbsolutePath(), Log.Level.INFO);
				return;
			}
		}
		switch(this.getType()){
			case insert:
				this.insert(f);
				break;
			case add:
				this.add(f);
				break;
			case replace:
				this.replace(f);
				break;
			case deleteFile:
				this.deleteFile(f);
				break;
			case renameFile:
				this.renameFile(f);
				break;
			case moveFile:
				this.moveFile(f);
				break;
			case copyFile:
				this.copyFile(f);
				break;
			case copyAbsoluteFile:
				this.copyAbsoluteFile(f);
				break;
			default:
		}
	}

	/**
	 * ersetzt alle Vorkommnisse des {@code replaceStrings} mit {@code replacement}
	 * @param file
	 */
	protected void replace(File file){
		String target = (String) this.getOperationData().get(Keys.Params_target);
		String replacement = (String) this.getOperationData().get(Keys.Params_replacement);
		Boolean isRegex = (Boolean) this.getOperationData().get(Keys.Params_isRegex);
		String fileText = Util.readFile(file);

		String result;
		if(isRegex == null || isRegex.booleanValue()){
			result = fileText.replaceAll(target, replacement);
		} else{
			result = fileText.replace(target, replacement);
		}
		if(result.equals(fileText)){
			Log.log("Zu ersetzender Text nicht gefunden in " + file.getAbsolutePath(), Log.Level.INFO);
		} else{
			Util.writeFile(file, result);
			Log.log("Text ersetzt in " + file.getAbsolutePath(), Log.Level.INFO);
		}
	}

	protected void deleteFile(File file){
		try {
			if(file.isDirectory()){
				FileUtils.deleteDirectory(file);
			} else{
				file.delete();
			}
			Log.log("Datei/Ordner gelöscht: " + file.getAbsolutePath(), Log.Level.INFO);
		} catch (IOException e) {
			Log.log(e);
		}
	}
	
	/**
	 * fügt den insertString, falls nicht durch Condition anders spezifiziert, am Anfang der Datei ein.
	 * @param file
	 */
	protected void insert(File file){
		InsertPosition condition = (InsertPosition) this.getOperationData().get(Keys.Params_condition);
		String insertString = (String) this.getOperationData().get(Keys.Params_insertString);

		if(condition != null){
			condition.insert(file);
		} else{
			String fileText = Util.readFile(file);
			fileText = insertString + fileText;
			Util.writeFile(file, fileText);
		}
	}

	/**
	 * fügt den addString, falls nicht durch Condition anders spezifiziert, ans Ende der Datei an
	 * @param file
	 */
	protected void add(File file){
		String addString = (String) this.getOperationData().get(Keys.Params_addString);
		Util.appendFile(file, addString);
		Log.log("Text angefügt an " + file.getAbsolutePath(), Log.Level.INFO);
	}

	/**
	 * benennt die Datei um
	 * @param file
	 */
	protected void renameFile(File file){
		String newName = (String) this.getOperationData().get(Keys.Params_newName);

		try {
			File output = new File(file.getParent() + "\\" + newName);
			if(output.exists()){
				Log.log("Datei/Ordner existiert bereits: " + file.getAbsolutePath(), Log.Level.INFO);
				return;
			}
			if(file.isDirectory()){
				FileUtils.moveDirectory(file, output);
			} else{
				FileUtils.moveFile(file, output);
			}
			Log.log("Umbenennen: " + file.getAbsolutePath() + " nach " + output.getAbsolutePath(), Log.Level.INFO);
		} catch (IOException e) {
			Log.log(e);
		}
	}
	
	protected void moveFile(File file){
		String newPath = (String) this.getOperationData().get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) this.getOperationData().get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) this.getOperationData().get(Keys.Params_createDirs);

		String targetPath = "";
		try {
			targetPath = Util.replacePlaceholders(newPath, file, true);
			File output = new File(targetPath);
			if(!overwriteExisting && !this.checkSurroundings(output, createDirs)){
				return;
			}
			if(file.isDirectory()){
				FileUtils.moveDirectory(file, output);
			} else{
				FileUtils.moveFile(file, output);
			}
			if(output.exists()){
				Log.log("Verschoben: " + file.getAbsolutePath() + " nach " + targetPath, Log.Level.INFO);
			}
		} catch (IOException e) {
			Log.log("Verschieben fehlgeschlagen: " + file.getAbsolutePath() + " nach " + targetPath, Log.Level.ERROR);
			Log.log(e);
		}
	}
	
	protected boolean checkSurroundings(File output, Boolean createDirs) {
		boolean valid = true;
		if(output.exists()){
			Log.log("Datei/Ordner existiert bereits: " + output.getAbsolutePath(), Log.Level.INFO);
			valid = false;
		}
		if(createDirs){
			output.getParentFile().mkdirs();
		} else if(!output.getParentFile().exists()){
			Log.log("Übergeordnetes Verzeichnis fehlt: " + output.getParentFile().getAbsolutePath(), Log.Level.INFO);
			valid = false;
		}
		return valid;
	}

	protected void copyFile(File file){
		String newPath = (String) this.getOperationData().get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) this.getOperationData().get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) this.getOperationData().get(Keys.Params_createDirs);
		
		String targetPath = "";
		try {
			targetPath = Util.replacePlaceholders(newPath, file, true);
			File output = new File(targetPath);
			if(createDirs){
				output.getParentFile().mkdirs();
			}
			if(overwriteExisting){
				Files.copy(file.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} else{
				if(!this.checkSurroundings(output, createDirs)){
					return;
				}
				Files.copy(file.toPath(), output.toPath());
			}
			if(output.exists()){
				Log.log("Kopieren: " + file.getAbsolutePath() + " nach " + targetPath, Log.Level.INFO);
			}
		} catch (IOException e) {
			Log.log("Kopieren fehlgeschlagen: " + file.getAbsolutePath() + " nach " + targetPath, Log.Level.ERROR);
			Log.log(e);
		}
	}
	
	protected void copyAbsoluteFile(File file){
		String newPath = (String) this.getOperationData().get(Keys.Params_newPath);
		String targetWorkspace = (String) this.getOperationData().get(Keys.Params_targetWorkspace);
		Boolean overwriteExisting = (Boolean) this.getOperationData().get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) this.getOperationData().get(Keys.Params_createDirs);
		Boolean onlyProjects = (Boolean) this.getOperationData().get(Keys.Params_onlyProjects);
		
		String targetPath = "";
		File root = new File(targetWorkspace);
		for(File subdir : root.listFiles()){
			try {
				if(onlyProjects && !subdir.getName().startsWith("com.athos.")){ //onlyProjects
					continue;
				}
				if(!newPath.contains("<project>")){ //<project> Platzhalter ist bei bezug auf alle pflicht
					break;
				}

				targetPath = newPath.replace("<project>", subdir.getAbsolutePath());
				targetPath = targetPath + (targetPath.endsWith("\\") ? "" : "\\") + file.getName();
				
				File output = new File(targetPath);
				if(createDirs){ //evtl fehlende Verzeichnisse erstellen
					output.getParentFile().mkdirs();
				}
				if(overwriteExisting){
					if(output.getParentFile().exists()){
						if(file.isDirectory()){
							FileUtils.copyDirectory(file, output);
						} else{
							Files.copy(file.toPath(), output.toPath(), StandardCopyOption.REPLACE_EXISTING);
						}
					}
				} else{
					if(!this.checkSurroundings(output, createDirs)){
						continue;
					}
					if(file.isDirectory()){
						FileUtils.copyDirectory(file, output);
					} else{
						Files.copy(file.toPath(), output.toPath());
					}
				}
				if(output.exists()){
					Log.log("Kopieren: " + file.getAbsolutePath() + " nach " + targetPath, Log.Level.INFO);
				}
			} catch (IOException e) {
				Log.log("Kopieren fehlgeschlagen: " + file.getAbsolutePath() + " nach " + targetPath, Log.Level.ERROR);
				Log.log(e);
			}
		}
	}
}
