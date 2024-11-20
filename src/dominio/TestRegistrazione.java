package dominio;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import view.Registrazione;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegistrazione {
	
	private Registrazione registrazione;
	
	private String Email2;
	private char[] Password2;
	private String Nome2;
	private String Cognome2;
	private String CodiceFiscale2;
	private String DataNascita2;
	private String Indirizzio2;
	@Before
	public void setUp() {
		Email2 ="Test Email";
		Password2 = "98877456".toCharArray();
		Nome2="TestName";
		Cognome2= "TestCognome";
		CodiceFiscale2="TestCF";
		DataNascita2="TestData";
		Indirizzio2="indirizzio";
	registrazione=new Registrazione(Email2, Password2, Nome2, Cognome2, CodiceFiscale2, DataNascita2, Indirizzio2);
	
	}
@Test(expected = IllegalArgumentException.class)
public void test01NamesCheck() {
	registrazione.nome2Check();
}

@Test(expected = IllegalArgumentException.class)
public void test02EmailCheck() {
	registrazione.email2Check();
}

@Test(expected = IllegalArgumentException.class)
public void test03PasswordCheck() {
	registrazione.Password2Check();
}
@Test(expected = IllegalArgumentException.class)
public void test04CognomeCheck() {
	registrazione.cognome2Check();
}
@Test(expected = IllegalArgumentException.class)
public void test05CodiceFiscaleCheck() {
	registrazione.codicefiscale2Check();
}
@Test(expected = IllegalArgumentException.class)
public void test06DataNascitaCheck() {
	registrazione.datanascita2Check();
}
@Test(expected = IllegalArgumentException.class)
public void test07IndirizzioCheck() {
	registrazione.indirizzio2Check();
}
@Test(expected = IllegalArgumentException.class)
public void test08CheckDuplicate() throws Exception {
	registrazione.checkduplicate();
}


	
}
