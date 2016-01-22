package logic;

import java.io.File;
import java.util.ArrayList;

import log.Log;
import log.Log.Level;
import util.Util;

/**
 * Diese Klasse lädt nur Dateien. Nur. Dateien.
 * @author bschattenberg
 *
 */
public class FileLoader {
	
	public FileLoader(File rootFolder){
		this.root(rootFolder);
	}
	
	protected FileLoader(){
		//verhindert Instanziierung ohne root
	}
	
	/**
	 * sollte nur einmal über den Konstruktor initialisiert werden
	 */
	private File root;
	protected void root(File file){
		root = file;
	}
	protected File root(){
		return root;
	}
	
	/**
	 * 
	 * @param filenames nur Kleinbuchstaben
	 * @return
	 */
	public ArrayList<File> loadFilesRecursive (ArrayList<String> filenames, boolean filenameIsRegex, boolean ignoreCase, boolean onlyProjects, boolean withPath){
		if(!this.root().isDirectory()){
			Log.log("root is invalid: " + this.root().getAbsolutePath(), Level.ERROR);
		}
		if(filenameIsRegex){
//			return this.collectRegexFilesRecursive(this.root(), filenames);
			return this.startCollectRegexFilesRecursive(this.root(), filenames, onlyProjects, withPath);
		} else{
			filenames = Util.toLowerCase(filenames);
//			return this.collectFilesRecursive(this.root(), filenames, ignoreCase);
			return this.startCollectFilesRecursive(this.root(), filenames, onlyProjects, ignoreCase, withPath);
		}
	}

	/**
	 * filtert nach Projekten falls {@code onlyProjects == true}
	 * @param dir
	 * @param filenames
	 * @param onlyProjects
	 * @param ignoreCase
	 * @return
	 */
	protected ArrayList<File> startCollectFilesRecursive(File dir, ArrayList<String> filenames, boolean onlyProjects,
			boolean ignoreCase, boolean withPath) {
		
		ArrayList<File> list = new ArrayList<>();
		for(File file : dir.listFiles()){
			if(onlyProjects && !file.getAbsolutePath().contains("com.athos.")){
				continue;
			}
			if(file.isDirectory()){
				list.addAll(this.collectFilesRecursive(file, filenames, ignoreCase, withPath));
			} else {
				String filename = file.getName();
				if(ignoreCase){
					filename = filename.toLowerCase();
				}
				if(withPath){
					if(file.exists() && Util.containsContainsMatch(filenames, file.getAbsolutePath())){
						list.add(file);
					}
				} else if(file.exists() && filenames.contains(filename)){
					list.add(file);
				}
			}
		}
		return list;
	}

	/**
	 * filtert nach Projekten falls {@code onlyProjects == true}
	 * @param dir
	 * @param filenames
	 * @param onlyProjects
	 * @return
	 */
	protected ArrayList<File> startCollectRegexFilesRecursive(File dir, ArrayList<String> filenames, boolean onlyProjects, boolean withPath) {
		ArrayList<File> list = new ArrayList<>();
		for(File file : dir.listFiles()){
			if(onlyProjects && !file.getAbsolutePath().contains("com.athos.")){
				continue;
			}
			if(file.isDirectory()){
				list.addAll(this.collectRegexFilesRecursive(file, filenames, withPath));
			} else {
				if(withPath){
					if(file.exists() && Util.containsRegexMatch(filenames, file.getAbsolutePath())){
						list.add(file);
					}
				} else if(file.exists() && Util.containsRegexMatch(filenames, file.getName())){
					list.add(file);
				}
			}
		}
		return list;
	}
	
	/**
	 * sucht rekursiv alle Verzeichnisse nach den in filenames spezifizierten Dateien
	 * @param dir
	 * @return
	 */
	protected ArrayList<File> collectFilesRecursive(File dir, ArrayList<String> filenames, boolean ignoreCase, boolean withPath){
		ArrayList<File> list = new ArrayList<>();
		for(File file : dir.listFiles()){
			if(file.isDirectory()){
				list.addAll(this.collectFilesRecursive(file, filenames, ignoreCase, withPath));
			} else {
				String filename = withPath ? file.getAbsolutePath() : file.getName();
				if(ignoreCase){
					filename = filename.toLowerCase();
				}
				if(withPath){
					if(file.exists() && Util.containsContainsMatch(filenames, filename)){
						list.add(file);
					}
				} else if(file.exists() && filenames.contains(filename)){
					list.add(file);
				}
			}
		}
		return list;
	}
	
	/**
	 * sucht rekursiv alle Verzeichnisse nach den in filenames spezifizierten Dateien, die als Regex interpretiert werden
	 * @param dir
	 * @return
	 */
	protected ArrayList<File> collectRegexFilesRecursive(File dir, ArrayList<String> filenames, boolean withPath){
		ArrayList<File> list = new ArrayList<>();
		for(File file : dir.listFiles()){
			if(file.isDirectory()){
				list.addAll(this.collectRegexFilesRecursive(file, filenames, withPath));
			} else{
				if(withPath){
					if(file.exists() && Util.containsContainsRegexMatch(filenames, file.getAbsolutePath())){
						list.add(file);
					}
				} else if(file.exists() && Util.containsRegexMatch(filenames, file.getName())){
					list.add(file);
				}
			}
		}
		return list;
	}
	
	/**
	 * sucht und lädt rekursiv die Dateien
	 * @param rootDir
	 * @param filenameIsRegex
	 * @param filenames
	 * @return
	 */
	public static ArrayList<File> loadFiles(String rootDir, boolean filenameIsRegex, boolean ignoreCase, boolean onlyProjects, boolean withPath, ArrayList<String> filenames){
		ArrayList<String> filenameList = new ArrayList<>();
		for(String s : filenames){
			//falls ignoreCase true und isRegex false sind, wird der Dateiname lowercase gemacht
			filenameList.add((ignoreCase && !filenameIsRegex ? s.toLowerCase() : s));
		}
		FileLoader fileLoader = new FileLoader(new File(rootDir));
		ArrayList<File> results = fileLoader.loadFilesRecursive(filenameList, filenameIsRegex, ignoreCase, onlyProjects, withPath);
		if(results.isEmpty()){
			Log.log("\r\n" + "Keine Dateien für " + Util.ArrayListToString(filenames) + " gefunden", Log.Level.INFO);
		} else{
			Log.log("\r\n" + results.size() + " Dateien geladen", Log.Level.INFO);
		}
		return results;
	}
	
	public static File loadFile(String... pathParts){
		String fullPath = "";
		for(String part : pathParts){
			part = part.replace("/", "\\");
			if(part.endsWith("\\")){
				part = part.substring(0, part.length() - 1);
			}
			fullPath += part + "\\";
		}
		if(fullPath.length() > 0){
			fullPath = fullPath.substring(0, fullPath.length() - 1);
		}
		return new File(fullPath);
	}
	
//	/**
//	 * gibt nur StartPages aus dem StartPages-Ordner zurück
//	 * @return
//	 */
//	public static ArrayList<File> loadStartPagesFiles(String rootDir, boolean isRegex, String fileName){
//		fileName = fileName.toLowerCase();
//		ArrayList<File> allFiles = FileLoader.loadFiles(rootDir, isRegex, true, fileName);
//		ArrayList<File> files = new ArrayList<>();
//		for(File file : allFiles){
//			if(file.getParentFile().getName().equals("StartPages")){
//				files.add(file);
//			}
//		}
//		return files;
//	}
}
