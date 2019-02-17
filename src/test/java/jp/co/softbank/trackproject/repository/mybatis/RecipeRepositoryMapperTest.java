package jp.co.softbank.trackproject.repository.mybatis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import jp.co.softbank.trackproject.model.Recipe;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@MybatisTest
@ContextConfiguration
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
@RunWith(SpringRunner.class)
@TestPropertySource(
    properties = "spring.test.database.replace: none")
public class RecipeRepositoryMapperTest {
  
  @Autowired
  private RecipeRepositoryMapper target;
  
  @Autowired
  private DataSource dataSourceTest;
  
  private DatabaseConnection targetConnection;
  
  private IDataSet targetDataSet;
  
  private static final String SEQ_NAME = "recipes_id_seq";
  
  private static final String TABLE_NAME = "recipes";
  
  @Before
  public void beforeRestDb() 
      throws CannotGetJdbcConnectionException, DatabaseUnitException, SQLException {
    targetConnection = new DatabaseConnection(DataSourceUtils.getConnection(dataSourceTest));
    targetDataSet = targetConnection.createDataSet();
  }

  @Test
  public void test_insert() throws SQLException, DatabaseUnitException, MalformedURLException {    
    // prepare
    Statement statement = DataSourceUtils.getConnection(dataSourceTest).createStatement();
    ResultSet rs = statement.executeQuery("(select max(id) as id from " + TABLE_NAME + ")");
    rs.next();
    int startSeqId = rs.getInt("id");
    DatabaseOperation.DELETE_ALL.execute(targetConnection, targetDataSet);
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    
    // test
    target.insert(recipe);
    
    // reset seqId
    statement = DataSourceUtils.getConnection(dataSourceTest).createStatement();
    statement.executeQuery("select setval('" + SEQ_NAME + "', " + startSeqId + ")");
    
    // verify
    ITable actualTable = targetDataSet.getTable("recipes");
    ITable filteredActualTable = 
        DefaultColumnFilter.excludedColumnsTable(
            actualTable, new String[]{"ID", "CREATED_AT", "UPDATED_AT"});
    
    IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
        .build(RecipeRepositoryMapperTest.class.getResourceAsStream("recipe-one.xml"));
    ITable expectedTable = expectedDataSet.getTable("recipes");
    ITable filteredExpectedTable = 
        DefaultColumnFilter.excludedColumnsTable(
            expectedTable, new String[]{"ID", "CREATED_AT", "UPDATED_AT"});
    
    Assertion.assertEquals(filteredExpectedTable, filteredActualTable);
  }
  
  @Test
  @DatabaseSetup("recipe-some-data.xml")
  public void test_selectById() {    
    // test
    Recipe actual = target.selectById(1);
    
    // verify
    Recipe expected = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
    assertThat(actual, is(expected));
  }

  @Test
  @DatabaseSetup("recipe-some-data.xml")
  public void test_selectAll() {    
    // test
    List<Recipe> actual = target.selectAll();
    
    // verify
    List<Recipe> expected = Arrays.asList(
        new Recipe(1, "チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000),
        new Recipe(2, "オムライス", "30分", "2人", "玉ねぎ,卵,スパイス,醤油", 700),
        new Recipe(3, "トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450));
    
    assertThat(actual.size(), is(3));
    assertThat(actual, is(expected));
  }
  
  @Test
  @DatabaseSetup("recipe-empty.xml")
  public void test_selectAll_empty() {    
    // test
    List<Recipe> actual = target.selectAll();
    
    // verify
    assertThat(actual.size(), is(0));
  }
  
  @Test
  @DatabaseSetup("recipe-some-data.xml")
  public void test_updateById() throws DataSetException {
    //prepare
    int id = 1;
    Recipe recipe = new Recipe("トマトスープレシピ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    
    // test
    target.updateById(id, recipe);
    
    // verify
    ITable actualTable = targetDataSet.getTable("recipes");
    int actualId = (int) actualTable.getValue(0, "id");
    Recipe actual = new Recipe(
        (String) actualTable.getValue(0, "title"),
        (String) actualTable.getValue(0, "making_time"),
        (String) actualTable.getValue(0, "serves"),
        (String) actualTable.getValue(0, "ingredients"),
        (int) actualTable.getValue(0, "cost"));
    
    assertThat(actualId, is(id));
    assertThat(actual, is(recipe));
  }
  
  @Test
  @DatabaseSetup("recipe-one.xml")
  public void test_delete() throws DataSetException {
    // test
    boolean actual = target.deleteById(3);
    
    // verify
    assertThat(actual, is(true));
    
    ITable actualTable = targetDataSet.getTable("recipes");
    int recipeCount = actualTable.getRowCount();
    assertThat(recipeCount, is(0));
  }
  
  @Test
  @DatabaseSetup("recipe-one.xml")
  public void test_delete_fail() throws DataSetException {
    // test
    boolean actual = target.deleteById(120); // 120は、存在しないID
    
    // verify
    assertThat(actual, is(false));
    
    ITable actualTable = targetDataSet.getTable("recipes");
    int recipeCount = actualTable.getRowCount();
    assertThat(recipeCount, is(1));
  }
}
