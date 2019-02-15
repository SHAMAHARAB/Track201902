package jp.co.softbank.trackproject.repository.mybatis;

import jp.co.softbank.trackproject.repository.RecipeRepository;

import org.apache.ibatis.annotations.Mapper;

/**
 * レシピの登録・照会・更新・削除を行うMybatis用のRepositoryインターフェースです。
 * @author H.Hamahara
 *
 */
@Mapper
public interface RecipeRepositoryMapper extends RecipeRepository {
  
}
