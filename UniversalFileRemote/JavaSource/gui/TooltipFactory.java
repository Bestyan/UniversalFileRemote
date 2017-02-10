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
				+ "Falls Leerzeichen enthalten sind, muss der Dateiname in Anführungszeichen gesetzt werden. "
				+ "Mehr Infos bei den Optionen.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getRegexTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgewählt, wird jeder Dateiname als regulärer Ausdruck interpretiert. Dies bezieht sich jedoch nur auf den Namen, "
				+ "wenn \"Mit Pfad\" nicht ausgewählt ist. Kann nicht zusammen mit Ignore Case eingesetzt werden. Falls beide ausgewählt sind, wird Regex bevorzugt.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getIgnoreCaseTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgewählt, wird die Groß/Kleinschreibung der Dateinamen ignoriert. "
				+ "Kann nicht zusammen mit Ignore Case eingesetzt werden. Falls beide ausgewählt sind, wird Regex bevorzugt.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getMitPfadTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgewählt, können Teile des Pfades bei Dateiname mit angegeben werden (z.B. workspace\\test\\test.txt). "
				+ "Kompatibel mit Regex und Ignore Case");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getNurAthosComTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgewählt, werden im Bereichsordner nur Ordner miteinbezogen, die mit com.athos. beginnen. "
				+ "Für Dateien im Bereichsordner gilt dies allerdings nicht"
				+ "Dies ist eine Performance-Option, da viele Dateien oft doppelt vorhanden sind (im Projektordner und wtbwebapps-Ordner)");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getPlaceholderTooltip(){
		Tooltip tooltip = new Tooltip("Gibt den neuen Speicherort an. Hierbei können folgende Platzhalter verwendet werden:\r\n\r\n"
				+ "<parent> \r\nPlatzhalter für Parent-Ordner (C:\\test\\file.txt -> <parent> = C:\\test) \r\n\r\n"
				+ "<parent[1-99] \r\nähnlich wie <parent>. Die Zahl gibt an, wie viele Ebenen nach oben gegangen wird (C:\\test\\file.txt -> <parent2> = C:) \r\n\r\n"
				+ "<project> \r\nPlatzhalter für den Pfad zum Projectordner (D:\\workspace\\com.athos.test\\asd\\asd\\file.txt -> <project> = D:\\workspace\\com.athos.test)");
		tooltip.setPrefWidth(400);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getConditionPlaceholderTooltip(){
		Tooltip tooltip = new Tooltip("Gibt den Speicherort an. Hierbei können folgende Platzhalter verwendet werden:\r\n\r\n"
				+ "<parent> \r\nPlatzhalter für Parent-Ordner (C:\\test\\file.txt -> <parent> = C:\\test) \r\n\r\n"
				+ "<parent[1-99] \r\nähnlich wie <parent>. Die Zahl gibt an, wie viele Ebenen nach oben gegangen wird (C:\\test\\file.txt -> <parent2> = C:) \r\n\r\n"
				+ "<project> \r\nPlatzhalter für den Pfad zum Projectordner (D:\\workspace\\com.athos.test\\asd\\asd\\file.txt -> <project> = D:\\workspace\\com.athos.test)");
		tooltip.setPrefWidth(400);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getConcernsAllToolTip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgewählt, werden die Dateinamen absolut interpretiert. Dies dient dazu, eine/n Datei/Ordner in alle Projekte zu kopieren. "
				+ "Im Feld für 'Neuer Name' muss hierbei der <project>-Platzhalter verwendet werden. Kann nur mit Datei/Ordner kopieren verwendet werden. Nicht kompatibel mit Regex/Ignore Case/mit Pfad.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getMatcherGroupTooltip(){
		Tooltip tooltip = new Tooltip("Ist diese Option ausgewählt, wird der Inhalt des Feldes als Regex interpretiert und der Ersetzungstext aus Matcher.group(0) genommen.");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
	
	public static Tooltip getReplacementPlaceholderTooltip(){
		Tooltip tooltip = new Tooltip("Spezielle Tags werden als Platzhalter interpretiert. Momentan implementiert sind:"
				+ "\r\n<project> = Pfad zum Projekt (D:\\eclipseMars\\workspace\\..)"
				+ "\r\n<projectname> = Name des Projekts (com.athos.test)");
		tooltip.setPrefWidth(200);
		tooltip.setWrapText(true);
		return tooltip;
	}
}