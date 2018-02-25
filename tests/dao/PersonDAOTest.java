package tests.dao;

import static org.junit.Assert.*;

import model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.DatabaseException;
import dao.PersonDAO;

public class PersonDAOTest {
	private PersonDAO pd = null;

	@Before
	public void setUp() throws Exception {
		pd = new PersonDAO();
		pd.setConnection();
	}

	@After
	public void tearDown() throws Exception {
		pd.deleteAllPersons();
		pd.closeConnection(false);
		pd = null;
	}

	@Test
	public void testConnection() {
		assertNotNull(pd.getConnection());
	}

	@Test
	public void testAddPerson() {
		Person a = new Person("8912365", "Willy", "Wonka", 'M', "cMonster", "Darth Vader", "Padme", "Mary Poppins");
		try {
			pd.addPerson(a);
			Person b = pd.getPerson(a.getPersonID());
			assertEquals(a.getPersonID(), b.getPersonID());
			assertEquals(a.getFirstName(), b.getFirstName());
			assertEquals(a.getLastName(), b.getLastName());
			assertEquals(a.getGender(), b.getGender());
			assertEquals(a.getDescendant(), b.getDescendant());
			assertEquals(a.getFather(), b.getFather());
			assertEquals(a.getMother(), b.getMother());
			assertEquals(a.getSpouse(), b.getSpouse());
		} catch (DatabaseException e) {
			fail("testAddPerson failed. " + e.getMessage());
		}
	}
	
	@Test
	public void testModifyPerson() {
		Person a = new Person("saag", "Eugene", "Krabs", 'M', "cMonster", "Darth Vader", "Padme", "");
		Person a1 = new Person("saag", "Eugene", "Krabs", 'M', "cMonster", "some crab", "some other crab", "money");
		try {
			pd.addPerson(a);
			pd.modifyPerson(a1);
			Person b = pd.getPerson(a.getPersonID());
			assertEquals(a1.getPersonID(), b.getPersonID());
			assertEquals(a1.getFirstName(), b.getFirstName());
			assertEquals(a1.getLastName(), b.getLastName());
			assertEquals(a1.getGender(), b.getGender());
			assertEquals(a1.getDescendant(), b.getDescendant());
			assertEquals(a1.getFather(), b.getFather());
			assertEquals(a1.getMother(), b.getMother());
			assertEquals(a1.getSpouse(), b.getSpouse());
		} catch (DatabaseException e) {
			fail("testModifyPerson failed. " + e.getMessage());
		}
	}

	@Test(expected=DatabaseException.class)
	public void testDeletePerson() throws DatabaseException{
		Person a = new Person("12177", "Willy", "Wonka", 'M', "cMonster", "Darth Vader", "Padme", "Mary Poppins");
		pd.addPerson(a);
		pd.deletePerson(a);
		assertNotNull(pd.getPerson(a.getPersonID()));
		fail("testDeletePerson failed.");
	}
	
	@Test
	public void testDeleteAll() {
		Person a = new Person("aegaeg", "Rocky", "Balboa", 'M', "cMonster", "A man", "A woman", "Adrian");;
		Person b = new Person("adhtdrh", "Rocky", "Balboa", 'M', "cMonster", "A man", "A woman", "Adrian");
		Person c = new Person("cxbxb", "Rocky", "Balboa", 'M', "cMonster", "A man", "A woman", "Adrian");
		
		try {
			pd.addPerson(a); pd.addPerson(b); pd.addPerson(c);
			pd.deleteAllPersons();
			if(pd.getAllPersons("cMonster").size() > 0) {
				throw new DatabaseException("getAllPersons failed.");
			}
		} catch (DatabaseException e) {
			fail("testDeleteAllPersons failed.");
		}
	}
}
