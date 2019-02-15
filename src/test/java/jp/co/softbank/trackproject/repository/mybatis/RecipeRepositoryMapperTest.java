package jp.co.softbank.trackproject.repository.mybatis;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import java.net.MalformedURLException;
import java.sql.SQLException;

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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Test
  public void test() throws SQLException, DatabaseUnitException, MalformedURLException {
    DatabaseConnection connection = 
        new DatabaseConnection(DataSourceUtils.getConnection(dataSourceTest));
    IDataSet databaseDataSet = connection.createDataSet();
    
    DatabaseOperation.DELETE_ALL.execute(connection, databaseDataSet);
    
    Recipe recipe = new Recipe("トマトスープ", "15分", "5人", "玉ねぎ, トマト, スパイス, 水", 450);
    target.insert(recipe);
    
    ITable actualTable = databaseDataSet.getTable("recipes");
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

}
