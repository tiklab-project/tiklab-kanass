package io.thoughtware.kanass.project.module.dao;

import io.thoughtware.kanass.project.module.entity.ModuleEntity;
import io.thoughtware.kanass.project.module.model.ModuleQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 模块数据操作
 */
@Repository
public class ModuleDao{

    private static Logger logger = LoggerFactory.getLogger(ModuleDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建模块
     * @param moduleEntity
     * @return
     */
    public String createModule(ModuleEntity moduleEntity) {
        return jpaTemplate.save(moduleEntity,String.class);
    }

    /**
     * 更新模块
     * @param moduleEntity
     */
    public void updateModule(ModuleEntity moduleEntity){
        jpaTemplate.update(moduleEntity);
    }

    /**
     * 删除模块
     * @param id
     */
    public void deleteModule(String id){
        jpaTemplate.delete(ModuleEntity.class,id);
    }

    /**
     * 查找模块
     * @param id
     * @return
     */
    public ModuleEntity findModule(String id){
        return jpaTemplate.findOne(ModuleEntity.class,id);
    }

    /**
     * 根据模块多个id查找模块列表
     * @param idList
     * @return
     */
    public List<ModuleEntity> findModuleList(List<String> idList) {
        return jpaTemplate.findList(ModuleEntity.class,idList);
    }

    /**
    * 查找所有模块
    * @return
    */
    public List<ModuleEntity> findAllModule() {
        return jpaTemplate.findAll(ModuleEntity.class);
    }

    /**
     * 根据条件查找模块
     * @param moduleQuery
     * @return
     */
    public List<ModuleEntity> findModuleList(ModuleQuery moduleQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ModuleEntity.class)
                .eq("projectId", moduleQuery.getProjectId())
                .like("moduleName", moduleQuery.getModuleName())
                .orders(moduleQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ModuleEntity.class);
    }

    /**
     * 根据条件按分页查找模块
     * @param moduleQuery
     * @return
     */
    public Pagination<ModuleEntity> findModulePage(ModuleQuery moduleQuery){
        QueryCondition queryCondition = QueryBuilders.createQuery(ModuleEntity.class)
                .eq("projectId", moduleQuery.getProjectId())
                .like("moduleName", moduleQuery.getModuleName())
                .orders(moduleQuery.getOrderParams())
                .pagination(moduleQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ModuleEntity.class);
    }
}