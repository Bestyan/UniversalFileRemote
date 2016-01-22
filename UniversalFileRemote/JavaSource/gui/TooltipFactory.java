package gui;

import javafx.scene.control.Tooltip;

public class TooltipFactory {
	public static Tooltip getBereichTooltip(){
		Tooltip tooltip = new Tooltip("Der 'Bereich' stellt die Wurzel des betroffenen Ordnerbaumes dar. \r\nIm Normalfall ist dies der workspace-Ordner");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getDateinameTooltip(){
		Tooltip tooltip = new Tooltip("Pro Zeile darf ein Dateiname eingegeben werden. "
				+ "Falls Leerzeichen enthalten sind, muss der Dateiname in Anf�hrungszeichen gesetzt werden. "
				+ "Mehr Infos bei den Optionen.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getRegexTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgew�hlt, wird jeder Dateiname als regul�rer Ausdruck interpretiert. Dies bezieht sich jedoch nur auf den Namen, "
				+ "wenn \"Mit Pfad\" nicht ausgew�hlt ist. Kann nicht zusammen mit Ignore Case eingesetzt werden. Falls beide ausgew�hlt sind, wird Regex bevorzugt.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getIgnoreCaseTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgew�hlt, wird die Gro�/Kleinschreibung der Dateinamen ignoriert. "
				+ "Kann nicht zusammen mit Ignore Case eingesetzt werden. Falls beide ausgew�hlt sind, wird Regex bevorzugt.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getMitPfadTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgew�hlt, k�nnen Teile des Pfades bei Dateiname mit angegeben werden (z.B. workspace\\test\\test.txt). "
				+ "Kompatibel mit Regex und Ignore Case");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getNurAthosComTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgew�hlt, werden im Bereichsordner nur Ordner miteinbezogen, die mit com.athos. beginnen. "
				+ "F�r Dateien im Bereichsordner gilt dies allerdings nicht"
				+ "Dies ist eine Performance-Option, da viele Dateien oft doppelt vorhanden sind (im Projektordner und wtbwebapps-Ordner)");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getPlaceholderTooltip(){
		Tooltip tooltip = new Tooltip("Gibt den neuen Speicherort an. Hierbei k�nnen folgende Platzhalter verwendet werden:\r\n\r\n"
				+ "<parent> \r\nPlatzhalter f�r Parent-Ordner (C:\\test\\file.txt -> <parent> = C:\\test) \r\n\r\n"
				+ "<parent[1-99] \r\n�hnlich wie <parent>. Die Zahl gibt an, wie viele Ebenen nach oben gegangen wird (C:\\test\\file.txt -> <parent2> = C:) \r\n\r\n"
				+ "<project> \r\nPlatzhalter f�r den Pfad zum Projectordner (D:\\workspace\\com.athos.test\\asd\\asd\\file.txt -> <project> = D:\\workspace\\com.athos.test)");
		tooltip.setPrefWidth(400);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getConditionPlaceholderTooltip(){
		Tooltip tooltip = new Tooltip("Gibt den Speicherort an. Hierbei k�nnen folgende Platzhalter verwendet werden:\r\n\r\n"
				+ "<parent> \r\nPlatzhalter f�r Parent-Ordner (C:\\test\\file.txt -> <parent> = C:\\test) \r\n\r\n"
				+ "<parent[1-99] \r\n�hnlich wie <parent>. Die Zahl gibt an, wie viele Ebenen nach oben gegangen wird (C:\\test\\file.txt -> <parent2> = C:) \r\n\r\n"
				+ "<project> \r\nPlatzhalter f�r den Pfad zum Projectordner (D:\\workspace\\com.athos.test\\asd\\asd\\file.txt -> <project> = D:\\workspace\\com.athos.test)");
		tooltip.setPrefWidth(400);
		tooltip.setWrapText(true);
		return tooltip;
	}
}