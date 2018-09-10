package tools;

public class EnumClass {
	
	public enum ActionType{
		Click,
		SetText,
		Select,
		Check,
		DoubleClick,
		EqualText,
        ContainText
	}
	public enum Identifier{
		id,
		className,
		xpath,
		name		
	}
	public enum Browser{
		ie,
		firefox,
		chrome,
		edge
	}
}
