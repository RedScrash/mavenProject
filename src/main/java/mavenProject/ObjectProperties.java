package mavenProject;

import mavenProject.EnumClass.Identifier;

public class ObjectProperties{
	private Identifier identifier;
	private String Property;
	//Constructor
	public ObjectProperties(Identifier identifier,String strProperty) {
		setIdentifier(identifier);
		setProperty(strProperty);
	}
	
	//Encapsulamiento de variables
	public void setIdentifier(Identifier identyfier) { this.identifier = identyfier;}
	public Identifier getIdentifier() { return this.identifier;}
	public void setProperty(String strProperty) { this.Property = strProperty;}
	public String getProperty() { return this.Property;}
}


