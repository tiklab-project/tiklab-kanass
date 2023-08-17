package io.tiklab.teamwire.project.module.service;

import io.tiklab.teamwire.project.module.model.Module;
import io.tiklab.teamwire.project.module.model.ModuleQuery;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teamwire.project.module.dao.ModuleDao;
import io.tiklab.teamwire.project.module.entity.ModuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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

        return moduleDao.createModule(moduleEntity);
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
    public Pagination<Module> findModulePage(ModuleQuery moduleQuery) {
        Pagination<ModuleEntity> pagination = moduleDao.findModulePage(moduleQuery);

        List<Module> moduleList = BeanMapper.mapList(pagination.getDataList(),Module.class);

        joinTemplate.joinQuery(moduleList);

        return PaginationBuilder.build(pagination,moduleList);
    }
}