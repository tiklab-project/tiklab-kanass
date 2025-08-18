package io.tiklab.kanass.product.plan.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.kanass.product.plan.dao.ProductPlanStateDao;
import io.tiklab.kanass.product.plan.entity.ProductPlanStateEntity;
import io.tiklab.kanass.product.plan.model.ProductPlanState;
import io.tiklab.kanass.product.plan.model.ProductPlanStateQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 迭代状态接口
*/
@Service
public class ProductPlanStateServiceImpl implements ProductPlanStateService {

    @Autowired
    ProductPlanStateDao productPlanStateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createProductPlanState(@NotNull @Valid ProductPlanState productPlanState) {
        ProductPlanStateEntity productPlanStateEntity = BeanMapper.map(productPlanState, ProductPlanStateEntity.class);

        return productPlanStateDao.createProductPlanState(productPlanStateEntity);
    }

    @Override
    public void updateProductPlanState(@NotNull @Valid ProductPlanState productPlanState) {
        ProductPlanStateEntity productPlanStateEntity = BeanMapper.map(productPlanState, ProductPlanStateEntity.class);

        productPlanStateDao.updateProductPlanState(productPlanStateEntity);
    }

    @Override
    public void deleteProductPlanState(@NotNull String id) {
        productPlanStateDao.deleteProductPlanState(id);
    }

    @Override
    public ProductPlanState findOne(String id) {
        ProductPlanStateEntity productPlanStateEntity = productPlanStateDao.findProductPlanState(id);

        ProductPlanState productPlanState = BeanMapper.map(productPlanStateEntity, ProductPlanState.class);
        return productPlanState;
    }

    @Override
    public List<ProductPlanState> findList(List<String> idList) {
        List<ProductPlanStateEntity> productPlanStateEntityList =  productPlanStateDao.findProductPlanStateList(idList);

        List<ProductPlanState> productPlanStateList =  BeanMapper.mapList(productPlanStateEntityList,ProductPlanState.class);
        return productPlanStateList;
    }

    @Override
    public ProductPlanState findProductPlanState(@NotNull String id) {
        ProductPlanState productPlanState = findOne(id);

        joinTemplate.joinQuery(productPlanState);
        return productPlanState;
    }

    @Override
    public List<ProductPlanState> findAllProductPlanState() {
        List<ProductPlanStateEntity> productPlanStateEntityList =  productPlanStateDao.findAllProductPlanState();

        List<ProductPlanState> productPlanStateList =  BeanMapper.mapList(productPlanStateEntityList,ProductPlanState.class);

        joinTemplate.joinQuery(productPlanStateList);
        return productPlanStateList;
    }

    @Override
    public List<ProductPlanState> findProductPlanStateList(ProductPlanStateQuery productPlanStateQuery) {
        List<ProductPlanStateEntity> productPlanStateEntityList = productPlanStateDao.findProductPlanStateList(productPlanStateQuery);

        List<ProductPlanState> productPlanStateList = BeanMapper.mapList(productPlanStateEntityList,ProductPlanState.class);

        joinTemplate.joinQuery(productPlanStateList);

        return productPlanStateList;
    }

    @Override
    public Pagination<ProductPlanState> findProductPlanStatePage(ProductPlanStateQuery productPlanStateQuery) {

        Pagination<ProductPlanStateEntity>  pagination = productPlanStateDao.findProductPlanStatePage(productPlanStateQuery);

        List<ProductPlanState> productPlanStateList = BeanMapper.mapList(pagination.getDataList(),ProductPlanState.class);

        joinTemplate.joinQuery(productPlanStateList);

        return PaginationBuilder.build(pagination,productPlanStateList);
    }
}