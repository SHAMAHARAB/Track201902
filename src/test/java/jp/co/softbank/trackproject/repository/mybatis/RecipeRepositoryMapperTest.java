package jp.co.softbank.trackproject.repository.mybatis;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import jp.co.softbank.trackproject.model.Recipe;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
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
  
  private int startSeqId;
  
  @Before
  public void beforeRestDb() 
      throws CannotGetJdbcConnectionException, DatabaseUnitException, SQLException {
    targetConnection = new DatabaseConnection(DataSourceUtils.getConnection(dataSourceTest));
    targetDataSet = targetConnection.createDataSet();
    Statement statement = DataSourceUtils.getConnection(dataSourceTest).createStatement();
    ResultSet rs = statement.executeQuery("(select max(id) as id from " + TABLE_NAME + ")");
    rs.next();
    startSeqId = rs.getInt("id");
  }
  
  @After
  public void afterRestSeq() 
      throws CannotGetJdbcConnectionException, DatabaseUnitException, SQLException {
    targetConnection = new DatabaseConnection(DataSourceUtils.getConnection(dataSourceTest));
    targetDataSet = targetConnection.createDataSet();
    Statement statement = DataSourceUtils.getConnection(dataSourceTest).createStatement();
    statement.executeQuery("select setval('" + SEQ_NAME + "', " + startSeqId + ")");
  }

  @Test
  public void test_create() throws SQLException, DatabaseUnitException, MalformedURLException {    
    DatabaseOperation.DELETE_ALL.execute(targetConnection, targetDataSet);
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    target.insert(recipe);
    
    ITable actualTable = targetDataSet.getTable("recipes");
    ITable filteredActualTable = 
        DefaultColumnFilter.excludedColumnsTable(
            actualTable, new String[]{"ID", "CREATED_AT", "UPDATED_AT"});
    
    IDataSet expectedDataSet = new FlatXmlDataSetBuilder()
        .build(RecipeRepositoryMapperTest.class.getResourceAsStream("create-test.xml"));
    ITable expectedTable = expectedDataSet.getTable("recipes");
    ITable filteredExpectedTable = 
        DefaultColumnFilter.excludedColumnsTable(
            expectedTable, new String[]{"ID", "CREATED_AT", "UPDATED_AT"});
    
    Assertion.assertEquals(filteredExpectedTable, filteredActualTable);
  }
  
  @Test
  @DatabaseSetup("get-test.xml")
  public void test_selectById() {    
    // test
    Recipe actual = target.selectById(1);
    
    // verify
    Recipe expected = new Recipe("チキンカレー", "45分", "4人", "玉ねぎ,肉,スパイス", 1000);
    assertThat(actual, is(expected));
  }

}
