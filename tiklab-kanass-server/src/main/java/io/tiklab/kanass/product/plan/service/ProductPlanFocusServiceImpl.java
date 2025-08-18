package io.tiklab.kanass.product.plan.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.product.plan.dao.ProductPlanFocusDao;
import io.tiklab.kanass.product.plan.entity.ProductPlanFocusEntity;
import io.tiklab.kanass.product.plan.model.ProductPlanFocus;
import io.tiklab.kanass.product.plan.model.ProductPlanFocusQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 收藏产品计划的服务
*/
@Service
public class ProductPlanFocusServiceImpl implements ProductPlanFocusService {

    @Autowired
    ProductPlanFocusDao productPlanFocusDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createProductPlanFocus(@NotNull @Valid ProductPlanFocus productPlanFocus) {
        ProductPlanFocusEntity productPlanFocusEntity = BeanMapper.map(productPlanFocus, ProductPlanFocusEntity.class);

        return productPlanFocusDao.createProductPlanFocus(productPlanFocusEntity);
    }

    @Override
    public void updateProductPlanFocus(@NotNull @Valid ProductPlanFocus productPlanFocus) {
        ProductPlanFocusEntity productPlanFocusEntity = BeanMapper.map(productPlanFocus, ProductPlanFocusEntity.class);

        productPlanFocusDao.updateProductPlanFocus(productPlanFocusEntity);
    }

    @Override
    public void deleteProductPlanFocus(@NotNull String id) {
        productPlanFocusDao.deleteProductPlanFocus(id);
    }

    @Override
    public void deleteProductPlanFocusByQuery(ProductPlanFocusQuery productPlanFocusQuery) {
        List<ProductPlanFocusEntity> productPlanFocusList = productPlanFocusDao.findProductPlanFocusList(productPlanFocusQuery);
        String id = productPlanFocusList.get(0).getId();

        productPlanFocusDao.deleteProductPlanFocus(id);
    }

    @Override
    public ProductPlanFocus findOne(String id) {
        ProductPlanFocusEntity productPlanFocusEntity = productPlanFocusDao.findProductPlanFocus(id);

        ProductPlanFocus productPlanFocus = BeanMapper.map(productPlanFocusEntity, ProductPlanFocus.class);
        return productPlanFocus;
    }

    @Override
    public List<ProductPlanFocus> findList(List<String> idList) {
        List<ProductPlanFocusEntity> productPlanFocusEntityList =  productPlanFocusDao.findProductPlanFocusList(idList);

        List<ProductPlanFocus> productPlanFocusList =  BeanMapper.mapList(productPlanFocusEntityList,ProductPlanFocus.class);
        return productPlanFocusList;
    }

    @Override
    public ProductPlanFocus findProductPlanFocus(@NotNull String id) {
        ProductPlanFocus productPlanFocus = findOne(id);

        joinTemplate.joinQuery(productPlanFocus);

        return productPlanFocus;
    }

    @Override
    public List<ProductPlanFocus> findAllProductPlanFocus() {
        List<ProductPlanFocusEntity> productPlanFocusEntityList =  productPlanFocusDao.findAllProductPlanFocus();

        List<ProductPlanFocus> productPlanFocusList =  BeanMapper.mapList(productPlanFocusEntityList,ProductPlanFocus.class);

        joinTemplate.joinQuery(productPlanFocusList);

        return productPlanFocusList;
    }

    @Override
    public List<ProductPlanFocus> findProductPlanFocusList(ProductPlanFocusQuery productPlanFocusQuery) {
        List<ProductPlanFocusEntity> productPlanFocusEntityList = productPlanFocusDao.findProductPlanFocusList(productPlanFocusQuery);

        List<ProductPlanFocus> productPlanFocusList = BeanMapper.mapList(productPlanFocusEntityList,ProductPlanFocus.class);

        joinTemplate.joinQuery(productPlanFocusList);

        return productPlanFocusList;
    }

    @Override
    public Pagination<ProductPlanFocus> findProductPlanFocusPage(ProductPlanFocusQuery productPlanFocusQuery) {
        Pagination<ProductPlanFocusEntity>  pagination = productPlanFocusDao.findProductPlanFocusPage(productPlanFocusQuery);

        List<ProductPlanFocus> productPlanFocusList = BeanMapper.mapList(pagination.getDataList(),ProductPlanFocus.class);

        joinTemplate.joinQuery(productPlanFocusList);

        return PaginationBuilder.build(pagination,productPlanFocusList);
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> findFocusProductPlanIds(){
        String loginId = LoginContext.getLoginId();
        List<String> focusProductPlanIds = productPlanFocusDao.findFocusProductPlanIds(loginId);
        return  focusProductPlanIds;
    }
}