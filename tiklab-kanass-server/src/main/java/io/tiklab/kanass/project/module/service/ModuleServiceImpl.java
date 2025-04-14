package io.tiklab.kanass.project.module.service;

import io.tiklab.kanass.project.module.model.Module;
import io.tiklab.kanass.project.module.model.ModuleQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.kanass.project.module.dao.ModuleDao;
import io.tiklab.kanass.project.module.entity.ModuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

/**
* 模块服务
*/
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuleDao moduleDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createModule(@NotNull @Valid Module module) {
        ModuleEntity moduleEntity = BeanMapper.map(module, ModuleEntity.class);
        String moduleId = moduleDao.createModule(moduleEntity);

        return moduleId;
    }

    @Override
    public void updateModule(@NotNull @Valid Module module) {
        ModuleEntity moduleEntity = BeanMapper.map(module, ModuleEntity.class);
        moduleDao.updateModule(moduleEntity);
    }

    @Override
    public void deleteModule(@NotNull String id) {
        moduleDao.deleteModule(id);
    }

    @Override
    public Module findOne(@NotNull String id) {
        ModuleEntity moduleEntity = moduleDao.findModule(id);

        Module module = BeanMapper.map(moduleEntity, Module.class);
        return module;
    }

    @Override
    public List<Module> findList(List<String> idList) {
        List<ModuleEntity> moduleEntityList = moduleDao.findModuleList(idList);

        List<Module> moduleList = BeanMapper.mapList(moduleEntityList,Module.class);

        return moduleList;
    }

    @Override
    public Module findModule(@NotNull String id) {
        Module module = findOne(id);

        joinTemplate.joinQuery(module);
        return module;
    }

    @Override
    public List<Module> findAllModule() {
        List<ModuleEntity> moduleEntityList = moduleDao.findAllModule();

        List<Module> moduleList = BeanMapper.mapList(moduleEntityList,Module.class);
        for(Module module:moduleList){
            joinTemplate.joinQuery(module);
        }
        return moduleList;
    }

    @Override
    public List<Module> findModuleList(ModuleQuery moduleQuery) {
        List<ModuleEntity> moduleEntityList = moduleDao.findModuleList(moduleQuery);

        List<Module> moduleList = BeanMapper.mapList(moduleEntityList,Module.class);
        for(Module module:moduleList){
            joinTemplate.joinQuery(module);
        }
        return moduleList;
    }


    public List<Module> findSeleteParentModuleList(String id) {
        List<ModuleEntity> moduleEntityList = moduleDao.findAllModule();

        // 获取父级id
        List<ModuleEntity> editModule = moduleEntityList.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
        ModuleEntity moduleEntity = editModule.get(0);
        String parentId = moduleEntity.getParentId();

        // 获取第一级子级id
        List<ModuleEntity> firstChildrenModuleList = moduleEntityList.stream().filter(item -> (item.getParentId()!= null && item.getParentId().equals(id))).collect(Collectors.toList());
        List<String> firstChildrenModuleIds = firstChildrenModuleList.stream().map(item -> item.getId()).collect(Collectors.toList());

        moduleEntityList = moduleEntityList.stream().filter(item -> item.getParentId() == null ||
                !firstChildrenModuleIds.contains(item.getParentId()) &&
                !item.getParentId().equals(id)).collect(Collectors.toList());
        //
        moduleEntityList = moduleEntityList.stream().filter(item -> !item.getId().equals(id) && !item.getId().equals(parentId)
                ).collect(Collectors.toList());

        List<Module> moduleList = BeanMapper.mapList(moduleEntityList,Module.class);
        joinTemplate.joinQuery(moduleList);
        return moduleList;
    }

    @Override
    public List<Module> findModuleListTree(ModuleQuery moduleQuery) {
        List<ModuleEntity> moduleEntityList = moduleDao.findModuleList(moduleQuery);
        List<Module> moduleList = BeanMapper.mapList(moduleEntityList,Module.class);
        //使用流操作将 moduleList 中每个模块的 id 提取为一个字符串列表 moduleIds，用于后续判断某个模块是否为根节点
        List<String> moduleIds = moduleList.stream().map(item -> item.getId()).collect(Collectors.toList());
        //使用流操作从 moduleList 中筛选出根节点模块列表 rootModuleList
        //如果某个模块的 parent 属性为 null，则表示它是根节点
        //或者，如果某个模块的父模块 ID 不在 moduleIds 列表中，也表示它是根节点
        List<Module> rootModuleList = moduleList.stream().filter(item -> (item.getParent() == null || !moduleIds.contains(item.getParent().getId())  )).collect(Collectors.toList());
        moduleList.remove(rootModuleList);
        //调用 setChildrenModuleList 方法，递归地为每个根节点模块设置其子模块列表，从而构建完整的树形结构
        setChildrenModuleList(rootModuleList, moduleList);
        //遍历 moduleList 中的每个模块对象，调用 joinTemplate.joinQuery(module) 方法对其进行额外的关联查询或处理
        for(Module module:moduleList){
            // 调用 joinTemplate.joinQuery(module) 方法对 module 进行关联查询或处理
            joinTemplate.joinQuery(module);
        }
        return rootModuleList;
    }

    public void setChildrenModuleList(List<Module> rootModuleList, List<Module> moduleList) {
        for (Module module : rootModuleList) {
            String id = module.getId();
            //使用流操作从 moduleList 中筛选出所有父模块 ID 等于当前模块 id 的子模块，形成 childrenList
            List<Module> childrenList = moduleList.stream().filter(item -> item.getParent() != null && item.getParent().getId().equals(id)).collect(Collectors.toList());
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

    @Override
    public Pagination<Module> findModulePage(ModuleQuery moduleQuery) {
        Pagination<ModuleEntity> pagination = moduleDao.findModulePage(moduleQuery);

        List<Module> moduleList = BeanMapper.mapList(pagination.getDataList(),Module.class);

        joinTemplate.joinQuery(moduleList);

        return PaginationBuilder.build(pagination,moduleList);
    }
}