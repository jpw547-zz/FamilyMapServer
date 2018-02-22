package tests.model;

import static org.junit.Assert.*;

import model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	private Person p = null;
	
	@Before
	public void setUp() throws Exception { p = new Person(); }

	@After
	public void tearDown() throws Exception { p = null; }

	@Test
	public void testPersonID() { p.setPersonID("e4e4e4e4"); assertEquals("e4e4e4e4", p.getPersonID()); }

	@Test
	public void testFirstName() { p.setFirstName("Homestar"); assertEquals("Homestar", p.getFirstName()); }

	@Test
	public void testLastName() { p.setLastName("Runner"); assertEquals("Runner", p.getLastName()); }

	@Test
	public void testGender() { p.setGender('M'); assertEquals('M', p.getGender()); }

	@Test
	public void testDescendant() { p.setDescendant("Marzipan"); assertEquals("Marzipan", p.getDescendant()); }

	@Test
	public void testFather() { p.setFather("StrongBad"); assertEquals("StrongBad", p.getFather()); }

	@Test
	public void testMother() { p.setMother("da Cheat"); assertEquals("da Cheat", p.getMother()); }

	@Test
	public void testSpouse() { p.setSpouse("Coach Z"); assertEquals("Coach Z", p.getSpouse()); }

}
