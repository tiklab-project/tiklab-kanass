package io.tiklab.kanass.product.service;


import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.product.dao.ProductModuleDao;
import io.tiklab.kanass.product.entity.ProductModuleEntity;
import io.tiklab.kanass.product.model.ProductModule;
import io.tiklab.kanass.product.model.ProductModuleQuery;
import io.tiklab.kanass.workitem.dao.WorkItemDao;
import io.tiklab.kanass.workitem.entity.WorkItemEntity;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductModuleServiceImpl implements ProductModuleService{

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProductModuleDao moduleDao;

    @Autowired
    WorkItemDao workItemDao;

    /**
     * 创建模块
     *
     * @param module
     * @return
     */
    @Override
    public String createModule(ProductModule module) {
        ProductModuleEntity moduleEntity = BeanMapper.map(module, ProductModuleEntity.class);
        String moduleId = moduleDao.createModule(moduleEntity);

        return moduleId;
    }

    /**
     * 更新模块
     *
     * @param module
     */
    @Override
    public void updateModule(ProductModule module) {
        ProductModuleEntity moduleEntity = BeanMapper.map(module, ProductModuleEntity.class);
        moduleDao.updateModule(moduleEntity);
    }

    /**
     * 删除模块
     *
     * @param id
     */
    @Override
    public Boolean deleteModule(String id) {
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setModuleIds(List.of(id));
        List<WorkItemEntity> workItemList = workItemDao.findWorkItemList(workItemQuery);
        if (CollectionUtils.isNotEmpty(workItemList)){
            return false;
        }
        moduleDao.deleteModule(id);
        return true;
    }

    @Override
    public ProductModule findOne(String id) {
        ProductModuleEntity moduleEntity = moduleDao.findModule(id);

        ProductModule module = BeanMapper.map(moduleEntity, ProductModule.class);
        return module;
    }

    /**
     * 根据多个id 查找模块列表
     *
     * @param idList
     * @return
     */
    @Override
    public List<ProductModule> findList(List<String> idList) {
        List<ProductModuleEntity> moduleEntityList = moduleDao.findModuleList(idList);

        List<ProductModule> moduleList = BeanMapper.mapList(moduleEntityList, ProductModule.class);

        return moduleList;
    }

    /**
     * 根据id查找模块
     *
     * @param id
     * @return
     */
    @Override
    public ProductModule findModule(String id) {
        ProductModule module = findOne(id);

        joinTemplate.joinQuery(module, new String[]{"parent", "product"});
        return module;
    }

    /**
     * 查找所有模块
     *
     * @return
     */
    @Override
    public List<ProductModule> findAllModule() {
        List<ProductModuleEntity> moduleEntityList = moduleDao.findAllModule();

        List<ProductModule> moduleList = BeanMapper.mapList(moduleEntityList, ProductModule.class);
        for(ProductModule module:moduleList){
            joinTemplate.joinQuery(module, new String[]{"parent", "product"});
        }
        return moduleList;
    }

    /**
     * 查找模块列表
     *
     * @param moduleQuery
     * @return
     */
    @Override
    public List<ProductModule> findModuleList(ProductModuleQuery moduleQuery) {
        List<ProductModuleEntity> moduleEntityList = moduleDao.findModuleList(moduleQuery);

        List<ProductModule> moduleList = BeanMapper.mapList(moduleEntityList, ProductModule.class);
        for(ProductModule module:moduleList){
            joinTemplate.joinQuery(module, new String[]{"parent", "product"});
        }
        return moduleList;
    }

    @Override
    public List<ProductModule> findSeleteParentModuleList(String id) {
        List<ProductModuleEntity> moduleEntityList = moduleDao.findAllModule();

        // 获取父级id
        List<ProductModuleEntity> editModule = moduleEntityList.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
        ProductModuleEntity moduleEntity = editModule.get(0);
        String parentId = moduleEntity.getParentId();

        // 获取第一级子级id
        List<ProductModuleEntity> firstChildrenModuleList = moduleEntityList.stream().filter(item -> (item.getParentId()!= null && item.getParentId().equals(id))).collect(Collectors.toList());
        List<String> firstChildrenModuleIds = firstChildrenModuleList.stream().map(item -> item.getId()).collect(Collectors.toList());

        moduleEntityList = moduleEntityList.stream().filter(item -> item.getParentId() == null ||
                !firstChildrenModuleIds.contains(item.getParentId()) &&
                        !item.getParentId().equals(id)).collect(Collectors.toList());
        //
        moduleEntityList = moduleEntityList.stream().filter(item -> !item.getId().equals(id) && !item.getId().equals(parentId)
        ).collect(Collectors.toList());

        List<ProductModule> moduleList = BeanMapper.mapList(moduleEntityList, ProductModule.class);
        joinTemplate.joinQuery(moduleList, new String[]{"parent", "product"});
        return moduleList;
    }

    @Override
    public List<ProductModule> findModuleListTree(ProductModuleQuery moduleQuery) {
        List<ProductModuleEntity> moduleEntityList = moduleDao.findModuleList(moduleQuery);
        List<ProductModule> moduleList = BeanMapper.mapList(moduleEntityList, ProductModule.class);
        //使用流操作将 moduleList 中每个模块的 id 提取为一个字符串列表 moduleIds，用于后续判断某个模块是否为根节点
        List<String> moduleIds = moduleList.stream().map(item -> item.getId()).collect(Collectors.toList());
        //使用流操作从 moduleList 中筛选出根节点模块列表 rootModuleList
        //如果某个模块的 parent 属性为 null，则表示它是根节点
        //或者，如果某个模块的父模块 ID 不在 moduleIds 列表中，也表示它是根节点
        List<ProductModule> rootModuleList = moduleList.stream().filter(item -> (item.getParent() == null || !moduleIds.contains(item.getParent().getId())  )).collect(Collectors.toList());
        moduleList.remove(rootModuleList);
        //调用 setChildrenModuleList 方法，递归地为每个根节点模块设置其子模块列表，从而构建完整的树形结构
        setChildrenModuleList(rootModuleList, moduleList);
        //遍历 moduleList 中的每个模块对象，调用 joinTemplate.joinQuery(module) 方法对其进行额外的关联查询或处理
        for(ProductModule module:moduleList){
            // 调用 joinTemplate.joinQuery(module) 方法对 module 进行关联查询或处理
            joinTemplate.joinQuery(module, new String[]{"parent", "product"});
        }
        return rootModuleList;
    }

    public void setChildrenModuleList(List<ProductModule> rootModuleList, List<ProductModule> moduleList) {
        for (ProductModule module : rootModuleList) {
            String id = module.getId();
            //使用流操作从 moduleList 中筛选出所有父模块 ID 等于当前模块 id 的子模块，形成 childrenList
            List<ProductModule> childrenList = moduleList.stream().filter(item -> item.getParent() != null && item.getParent().getId().equals(id)).collect(Collectors.toList());
            if(childrenList.size() > 0){
                module.setChildren(childrenList);
            }

            moduleList.remove(childrenList);
            // 递归调用 setChildrenModuleList 方法，为每个子模块设置其子模块列表
            if(moduleList.size() > 0){
                setChildrenModuleList(childrenList, moduleList);
            }
        }
    }

    /**
     * 根据条件按分页查找模块
     *
     * @param moduleQuery
     * @return
     */
    @Override
    public Pagination<ProductModule> findModulePage(ProductModuleQuery moduleQuery) {
        Pagination<ProductModuleEntity> pagination = moduleDao.findModulePage(moduleQuery);

        List<ProductModule> moduleList = BeanMapper.mapList(pagination.getDataList(), ProductModule.class);

        joinTemplate.joinQuery(moduleList, new String[]{"parent", "product"});

        return PaginationBuilder.build(pagination,moduleList);
    }
}
