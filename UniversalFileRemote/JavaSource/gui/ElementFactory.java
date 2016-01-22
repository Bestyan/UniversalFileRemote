package gui;

import data.Css;
import data.Ids;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.OperationCondition;
import util.GuiUtil;

public class ElementFactory {
	public static Pane getOperationConditionLine(){
		GridPane layout = new GridPane();
		layout.setVgap(10);
		layout.setHgap(10);
		layout.getStyleClass().addAll(Css.borderGray);
		int row = 0;
		
		//ComboBox f. FileType
		ComboBox<String> cbFileType = new ComboBox<>();
		cbFileType.getItems().addAll(OperationCondition.getOperationConditionFileTypeList());
		cbFileType.setId(Ids.condition_comboFileType);
		cbFileType.setPrefWidth(110);
		cbFileType.setOnAction(EventHandlerFactory.getConditionFileTypeEventHandler(layout));
		cbFileType.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getConditionValidatorChangeListener(layout));
		layout.add(cbFileType, 0, row);
		
		//TextFeld für evtl. anderen Dateinamen
		TextField tfOtherFile = new TextField();
		tfOtherFile.setId(Ids.condition_tfAndereDatei);
		tfOtherFile.setDisable(true);
		tfOtherFile.setTooltip(TooltipFactory.getConditionPlaceholderTooltip());
		tfOtherFile.textProperty().addListener(ListenerFactory.getConditionValidatorChangeListener(layout));
		layout.add(tfOtherFile, 1, row);
		
		//Checkbox aktiv
		CheckBox cbAktiv = new CheckBox("aktiv");
		cbAktiv.setId(Ids.condition_cbActive);
		cbAktiv.setSelected(true);
		cbAktiv.selectedProperty().addListener(ListenerFactory.getConditionActiveStateChangeListener(layout));
		cbAktiv.setSelected(false); //nötig, damit ein onChange-Event gefeuert wird
		cbAktiv.setDisable(true);
		layout.add(cbAktiv, 2, row);
		row++;
		
		//ComboBox für OperationType
		ComboBox<String> cbConditionType = new ComboBox<>();
		cbConditionType.getItems().addAll(OperationCondition.getOperationConditionTypeList());
		cbConditionType.setId(Ids.condition_comboConditionType);
		cbConditionType.setPrefWidth(110);
		cbConditionType.setOnAction(EventHandlerFactory.getConditionTypeEventHandler(layout));
		cbConditionType.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getConditionValidatorChangeListener(layout));
		layout.add(GuiUtil.getWrappedInPane(cbConditionType), 0, row);
		
		//Textarea für OperationType
		TextArea taConditionText = new TextArea();
		taConditionText.setPrefRowCount(2);
		taConditionText.setPrefWidth(100);
		taConditionText.setId(Ids.condition_taConditionText);
		taConditionText.setDisable(true);
		taConditionText.textProperty().addListener(ListenerFactory.getConditionValidatorChangeListener(layout));
		layout.add(taConditionText, 1, row, 1, 2);
		
		//Checkbox regex textarea
		CheckBox cbRegexTextArea = new CheckBox("Regex");
		cbRegexTextArea.setId(Ids.condition_cbTextRegex);
		layout.add(cbRegexTextArea, 2, row);
		row++;
		
		//Button entfernen
		Button btnZeileLoeschen = new Button("entfernen");
		btnZeileLoeschen.setPrefWidth(110);
		layout.add(GuiUtil.getWrappedInPane(btnZeileLoeschen), 0, row);
		
		//Checkbox bedingung negiert
		CheckBox cbNegation = new CheckBox("negiert");
		cbNegation.setId(Ids.condition_cbNegation);
		layout.add(cbNegation, 2, row);
		
		Pane wrapper = GuiUtil.getWrappedInPane(layout);
		wrapper.getStyleClass().add(Css.margin);
		
		btnZeileLoeschen.setOnAction(EventHandlerFactory.getRemoveConditionEventHandler(wrapper));
		return wrapper;
	}
}
