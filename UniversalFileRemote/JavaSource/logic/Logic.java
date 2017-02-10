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
		Boolean isReplacementGroup = (Boolean) params.get(Keys.Params_replacementGroup);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String target = (String) params.get(Keys.Params_target);
		String replacement = (String) params.get(Keys.Params_replacement);
		Boolean replaceRegex = (Boolean) params.get(Keys.Params_replaceRegex);
		Boolean placeholdersEnabled = (Boolean) params.get(Keys.Params_replacementPlaceholdersEnabled);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorBasicReplace(files, conditions, target, replacement, replaceRegex, isReplacementGroup, placeholdersEnabled);
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
	 * <li>String newName</li>
	 * </ul>
	 */
	public static void renameFolders(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String newName = (String) params.get(Keys.Params_newName);
		ArrayList<File> files = FileLoader.loadFolders(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
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
		Boolean concernsAll = (Boolean) params.get(Keys.Params_concernsAll);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String newPath = (String) params.get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) params.get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) params.get(Keys.Params_createDirs);
		FileOperator fileOp = null;
		if(concernsAll){
			ArrayList<File> files = FileLoader.loadFiles(Util.splitFilenames(fileNames));
			fileOp = FileOperatorFactory.getFileOperatorCopyAbsoluteFile(files, conditions, targetWorkspace, newPath, overwriteExisting, createDirs, onlyProjects);
		} else{
			ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
			fileOp = FileOperatorFactory.getFileOperatorCopyFile(files, conditions, newPath, overwriteExisting, createDirs);
		}
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
	public static void copyFolders(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		Boolean concernsAll = (Boolean) params.get(Keys.Params_concernsAll);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String newPath = (String) params.get(Keys.Params_newPath);
		Boolean overwriteExisting = (Boolean) params.get(Keys.Params_overwriteExisting);
		Boolean createDirs = (Boolean) params.get(Keys.Params_createDirs);
		FileOperator fileOp = null;
		if(concernsAll){
			ArrayList<File> files = FileLoader.loadFiles(Util.splitFilenames(fileNames));
			fileOp = FileOperatorFactory.getFileOperatorCopyAbsoluteFile(files, conditions, targetWorkspace, newPath, overwriteExisting, createDirs, onlyProjects);
		} else{
			ArrayList<File> files = FileLoader.loadFolders(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
			fileOp = FileOperatorFactory.getFileOperatorCopyFile(files, conditions, newPath, overwriteExisting, createDirs);
		}
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
		Boolean isReplacementGroup = (Boolean) params.get(Keys.Params_replacementGroup);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		String target = (String) params.get(Keys.Params_target);
		String insertString = (String) params.get(Keys.Params_insertString);
		String positionCondition = (String) params.get(Keys.Params_positionCondition);
		ArrayList<File> files = FileLoader.loadFiles(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
		FileOperator fileOp = FileOperatorFactory.getFileOperatorConditionalInsert(files, conditions, insertString, target, Util.getType(positionCondition), isReplacementGroup);
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
	 * 
	 * <li>String newPath</li>
	 * <li>Boolean overwriteExisting</li>
	 * <li>Boolean createDirs</li>
	 * </ul>
	 */
	public static void moveFolders(HashMap<String, Object> params){
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
		ArrayList<File> files = FileLoader.loadFolders(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
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
	public static void deleteFolders(HashMap<String, Object> params){
		String targetWorkspace = (String) params.get(Keys.Params_targetWorkspace);
		String fileNames = (String) params.get(Keys.Params_fileName);
		Boolean isRegex = (Boolean) params.get(Keys.Params_isRegex);
		Boolean ignoreCase = (Boolean) params.get(Keys.Params_ignoreCase);
		Boolean withPath = (Boolean) params.get(Keys.Params_withPath);
		Boolean onlyProjects = (Boolean) params.get(Keys.Params_onlyProjects);
		ArrayList<OperationCondition> conditions = (ArrayList<OperationCondition>) params.get(Keys.Params_conditions);
		
		ArrayList<File> files = FileLoader.loadFolders(targetWorkspace, isRegex, ignoreCase, onlyProjects, withPath, Util.splitFilenames(fileNames));
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
		Log.log("\r\n" + "Gefundene Dateien für " + fileName + ":", Log.Level.INFO);
		outerLoop : for(File file : list){
			if(conditions != null){
				for(OperationCondition con : conditions){
					if(!con.isMet(file)){
						Log.log("Bedingung " + con.toString(file) + " nicht erfüllt in " + file.getAbsolutePath(), Log.Level.INFO);
						continue outerLoop;
					}
				}
			}
			Log.log(file.getAbsolutePath(), Log.Level.DEBUG);
			Log.writeToConsole(file.getAbsolutePath());
		}
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
	public static void searchFolders(HashMap<String, Object> params){
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
		
		ArrayList<File> list = fileLoader.loadFoldersRecursive(filenames, isRegex, ignoreCase, onlyProjects, withPath);
		Log.log("\r\n" + "Gefundene Ordner für " + fileName + ":", Log.Level.INFO);
		outerLoop : for(File file : list){
			if(conditions != null){
				for(OperationCondition con : conditions){
					if(!con.isMet(file)){
						Log.log("Bedingung " + con.toString(file) + " nicht erfüllt in " + file.getAbsolutePath(), Log.Level.INFO);
						continue outerLoop;
					}
				}
			}
			Log.log(file.getAbsolutePath(), Log.Level.DEBUG);
			Log.writeToConsole(file.getAbsolutePath());
		}
	}
}
