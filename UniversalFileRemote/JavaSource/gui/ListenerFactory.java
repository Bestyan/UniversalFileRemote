package gui;

import data.Ids;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import logic.Validator;

public class ListenerFactory {
	public static ChangeListener<Boolean> getConditionActiveStateChangeListener(Pane layout){
		return new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("D6FFD6"), null, null)));
				} else{
					layout.setBackground(new Background(new BackgroundFill(Paint.valueOf("FFD6D6"), null, null)));
				}
			}
		};
	}
	
	public static ChangeListener<String> getConditionValidatorChangeListener(Pane layout){
		return new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				boolean valid = Validator.validateOperationCondition(layout);
				CheckBox cbActive = (CheckBox) layout.lookup("#" + Ids.condition_cbActive);
				if(!valid){
					cbActive.setSelected(false);
				}
				cbActive.setDisable(!valid);
			}
		};
	}
	
	public static ChangeListener<String> getStartOperationValidatorChangeListener(Pane layout){
		return new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				boolean valid = Validator.validateStartOperation(layout);
				Button btnStart = (Button) layout.lookup("#" + Ids.main_btnStart);
				btnStart.setDisable(!valid);
			}
		};
	}
	
	public static ChangeListener<Boolean> getConcernsAllEnabledChangeListener(Pane layout){
		return new ChangeListener<Boolean>(){
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				((Button) layout.lookup("#" + Ids.main_btnAddCondition)).setDisable(newValue);
				if(newValue){
					Pane conditions = (Pane)layout.lookup("#" + Ids.main_paneCondition);
					conditions.getChildren().removeAll(conditions.getChildren());
				}
			}
		};
	}
}
