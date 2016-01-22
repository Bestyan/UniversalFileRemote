package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.Keys;
import log.Log;
import util.Util;

/**
 * Bedient die FileOperatorFactory und ist die Schnittstelle zur GUI
 * 
 * @author bschattenberg
 *
 */
@SuppressWarnings("unchecked")
public class Logic {
	
	/**
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * 
	 * <li>String target</li>
	 * <li>String replacement</li>
	 * <li>String replaceRegex</li>
	 * </ul>
	 */
	public static void replace(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String target = (String) params.get(Keys.Params_target);
		String replacement = (String) params.get(Keys.Params_replacement);
		Boolean replaceRegex = (Boolean) params.get(Keys.Params_replaceRegex);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorBasicReplace(files, conditions, target, replacement, replaceRegex);
		fileOp.start();
	}
	
	/**
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * 
	 * <li>String addString</li>
	 * </ul>
	 */
	public static void add(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String addString = (String) params.get(Keys.Params_addString);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorBasicAdd(files, conditions, addString);
		fileOp.start();
	}
	
	/**
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * 
	 * <li>String newName</li>
	 * </ul>
	 */
	public static void rename(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String newName = (String) params.get(Keys.Params_newName);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorBasicRename(files, conditions, newName);
		fileOp.start();
	}
	
	/**
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * 
	 * <li>String newPath</li>
	 * <li>Boolean overwriteExisting</li>
	 * <li>Boolean createDirs</li>
	 * </ul>
	 */
	public static void copyFiles(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String newPath = (String) params.get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) params.get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) params.get(Keys.Params_createDirs);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorCopyFile(files, conditions, newPath, overwriteExisting, createDirs);
		fileOp.start();
	}
	
	/**
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * 
	 * <li>String target</li>
	 * <li>String positionCondition</li>
	 * <li>String insertString</li>
	 * </ul>
	 */
	public static void insertIntoFiles(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String target = (String) params.get(Keys.Params_target);
		String insertString = (String) params.get(Keys.Params_insertString);
		String positionCondition = (String) params.get(Keys.Params_positionCondition);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorConditionalInsert(files, conditions, insertString, target, Util.getType(positionCondition));
		fileOp.start();
	}
	
	/** 
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * 
	 * <li>String newPath</li>
	 * <li>Boolean overwriteExisting</li>
	 * <li>Boolean createDirs</li>
	 * </ul>
	 */
	public static void moveFiles(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String newPath = (String) params.get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) params.get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) params.get(Keys.Params_createDirs);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorMoveFiles(files, conditions, newPath, overwriteExisting, createDirs);
		fileOp.start();
	}
	
	/** 
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * </ul>
	 */
	public static void deleteFiles(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorDeleteFiles(files, conditions);
		fileOp.start();
	}
	
	/**
	 * 
	 * @param params
	 * <ul>
	 * <li>String targetWorkspace</li>
	 * <li>String fileName</li>
	 * <li>Boolean isRegex</li>
	 * <li>Boolean ignoreCase</li>
	 * <li>Boolean onlyProjects</li>
	 * <li>ArrayList<OperationCondition> conditions</li>
	 * </ul>
	 */
	public static void searchFilenames(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileName = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		
		File workspace = new File(targetWorkspace);
		FileLoader fileLoader = new FileLoader(workspace);
		
		ArrayList<String> filenames = Util.splitFilenames(fileName);
		if(ignoreCase && !isRegex){
			filenames = Util.toLowerCase(filenames);
		}
		
		ArrayList<File> list = fileLoader.loadFilesRecursive(filenames, isRegex, ignoreCase, onlyProjects, withPath);
		String logMsg = "\r\n" + "Gefundene Dateien:";
		outerLoop : for(File file : list){
			for(OperationCondition con : conditions){
				if(!con.isMet(file)){
					logMsg += "\r\n" + "Bedingung " + con.toString(file) + " nicht erf�llt in " + file.getAbsolutePath();
					continue outerLoop;
				}
			}
			logMsg += "\t\r\n" + file.getAbsolutePath();
		}
		Log.log(logMsg, Log.Level.INFO);
	}
}