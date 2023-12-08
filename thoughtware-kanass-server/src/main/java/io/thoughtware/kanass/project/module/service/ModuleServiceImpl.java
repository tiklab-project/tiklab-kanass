package io.thoughtware.kanass.project.module.service;

import io.thoughtware.kanass.project.module.model.Module;
import io.thoughtware.kanass.project.module.model.ModuleQuery;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.kanass.project.module.dao.ModuleDao;
import io.thoughtware.kanass.project.module.entity.ModuleEntity;
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

    @Override
    public List<Module> findModuleListTree(ModuleQuery moduleQuery) {
        List<ModuleEntity> moduleEntityList = moduleDao.findModuleList(moduleQuery);
        List<Module> moduleList = BeanMapper.mapList(moduleEntityList,Module.class);
        List<Module> rootModuleList = moduleList.stream().filter(item -> item.getParent() == null).collect(Collectors.toList());
        moduleList.remove(rootModuleList);
        setChildrenModuleList(rootModuleList, moduleList);
        for(Module module:moduleList){
            joinTemplate.joinQuery(module);
        }
        return rootModuleList;
    }

    public void setChildrenModuleList(List<Module> rootModuleList, List<Module> moduleList) {
        for (Module module : rootModuleList) {
            String id = module.getId();
            List<Module> childrenList = moduleList.stream().filter(item -> item.getParent() != null && item.getParent().getId().equals(id)).collect(Collectors.toList());
            if(childrenList.size() > 0){
                module.setChildren(childrenList);
            }

            moduleList.remove(childrenList);
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