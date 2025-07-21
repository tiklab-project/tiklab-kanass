package io.tiklab.kanass.project.test.service;

import io.tiklab.kanass.project.test.model.Category;
import io.tiklab.kanass.project.test.model.CategoryQuery;

import java.util.List;

public interface TestCategoryService {

    List<Category> findCategoryListTreeTable(CategoryQuery categoryQuery);
}
