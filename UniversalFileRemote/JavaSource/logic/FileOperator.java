package logic;

import java.io.File;
import java.util.ArrayList;

/**
 * Verwaltet Dateien und Operationen<br/>
 * ist mehr oder weniger ein Layer
 * @author bschattenberg
 *
 */
public class FileOperator {
	
	public FileOperator(ArrayList<File> files, Operation op){
		this.setFiles(files);
		this.setOperation(op);
	}
	
	private ArrayList<File> files = new ArrayList<>();
	public ArrayList<File> getFiles() {
		return files;
	}
	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}
	
	private Operation operation;
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	
	public void start(){
		this.getOperation().start(this.getFiles());
	}
	
}
