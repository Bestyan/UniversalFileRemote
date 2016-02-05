package logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import data.Keys;
import logic.Operation.Type;
import util.Util;

public class FileOperatorFactory {

	public static FileOperator getFileOperatorBasicReplace(ArrayList<File> files, ArrayList<OperationCondition> conditions, String target, String replacement, boolean isRegex, boolean isReplacementGroup){
		Operation operation = new Operation(Type.replace);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_target, target);
			operationData.put(Keys.Params_replacement, replacement);
			operationData.put(Keys.Params_isRegex, new Boolean(isRegex));
			operationData.put(Keys.Params_replacementGroup, new Boolean(isReplacementGroup));
			operationData.put(Keys.Params_conditions, conditions);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorBasicRename(ArrayList<File> files, ArrayList<OperationCondition> conditions, String newName){
		Operation operation = new Operation(Type.renameFile);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_newName, newName);
			operationData.put(Keys.Params_conditions, conditions);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorBasicAdd(ArrayList<File> files, ArrayList<OperationCondition> conditions, String addString){
		Operation operation = new Operation(Type.add);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_addString, addString);
			operationData.put(Keys.Params_conditions, conditions);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorCopyFile(ArrayList<File> files, ArrayList<OperationCondition> conditions, String newName, Boolean overwriteExisting, Boolean createDirs){
		Operation operation = new Operation(Type.copyFile);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_newPath, newName);
			operationData.put(Keys.Params_overwriteExisting, overwriteExisting);
			operationData.put(Keys.Params_createDirs, createDirs);
			operationData.put(Keys.Params_conditions, conditions);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorConditionalInsert(ArrayList<File> files, ArrayList<OperationCondition> conditions, String insertString, String target, InsertPosition.Type type, boolean isReplacementGroup){
		Operation operation = new Operation(Type.insert);
		Util.setConditionsLink(operation, conditions);
		InsertPosition condition = new InsertPosition(operation, type);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_insertString, insertString);
			operationData.put(Keys.Params_condition, condition);
			operationData.put(Keys.Params_target, target);
			operationData.put(Keys.Params_conditions, conditions);
			operationData.put(Keys.Params_replacementGroup, new Boolean(isReplacementGroup));
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorMoveFiles(ArrayList<File> files, ArrayList<OperationCondition> conditions, String newPath, Boolean overwriteExisting, Boolean createDirs){
		Operation operation = new Operation(Type.moveFile);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_newPath, newPath);
			operationData.put(Keys.Params_overwriteExisting, overwriteExisting);
			operationData.put(Keys.Params_createDirs, createDirs);
			operationData.put(Keys.Params_conditions, conditions);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorDeleteFiles(ArrayList<File> files, ArrayList<OperationCondition> conditions){
		Operation operation = new Operation(Type.deleteFile);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_conditions, conditions);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
	
	public static FileOperator getFileOperatorCopyAbsoluteFile(ArrayList<File> files,
			ArrayList<OperationCondition> conditions, String targetWorkspace, String newPath, Boolean overwriteExisting,
			Boolean createDirs, Boolean onlyProjects) {
		Operation operation = new Operation(Type.copyAbsoluteFile);
		Util.setConditionsLink(operation, conditions);
		{
			HashMap<String, Object> operationData = new HashMap<>();
			operationData.put(Keys.Params_conditions, conditions);
			operationData.put(Keys.Params_targetWorkspace, targetWorkspace);
			operationData.put(Keys.Params_newPath, newPath);
			operationData.put(Keys.Params_onlyProjects, onlyProjects);
			operationData.put(Keys.Params_overwriteExisting, overwriteExisting);
			operationData.put(Keys.Params_createDirs, createDirs);
			operation.setOperationData(operationData);
		}
		FileOperator fileOp = new FileOperator(files, operation);
		return fileOp;
	}
}
