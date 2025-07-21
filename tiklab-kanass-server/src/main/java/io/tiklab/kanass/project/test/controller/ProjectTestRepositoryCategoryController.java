package io.tiklab.kanass.project.test.controller;

import io.tiklab.core.Result;
import io.tiklab.kanass.project.test.model.Category;
import io.tiklab.kanass.project.test.model.CategoryQuery;
import io.tiklab.kanass.project.test.service.TestCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/projectTestRepositoryCategory")
public class ProjectTestRepositoryCategoryController {

    @Autowired
    private TestCategoryService testCategoryService;

    @PostMapping("/findCategoryListTreeTable")
    public Result<List<Category>> findCategoryListTreeTable(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        return Result.ok(testCategoryService.findCategoryListTreeTable(categoryQuery));
    }
}
