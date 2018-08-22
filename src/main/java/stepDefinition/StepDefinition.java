package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import Core.ActionObject;
import cucumber.api.java.en.And;

public class StepDefinition {
	ActionObject actionObject;
	public StepDefinition() {
		actionObject = new ActionObject();
	}
  @Given("^Usuario ingresa al Config$")
  public void IngresarConfig(String strUser) throws Throwable {
	  
  }

  @When("^you are in When annotation$")
  public void when() throws Throwable {
  }

  @Then("^you are in Then annotation$")
  public void then() throws Throwable {
  }

  @And("^you are in And annotation$")
  public void and() throws Throwable {
  }

}
