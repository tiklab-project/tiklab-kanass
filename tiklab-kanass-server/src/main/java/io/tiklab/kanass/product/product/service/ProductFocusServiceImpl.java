package io.tiklab.kanass.product.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.product.product.dao.ProductFocusDao;
import io.tiklab.kanass.product.product.entity.ProductFocusEntity;
import io.tiklab.kanass.product.product.model.ProductFocus;
import io.tiklab.kanass.product.product.model.ProductFocusQuery;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFocusServiceImpl implements ProductFocusService{
    @Autowired
    JoinTemplate joinTemplate;
    
    @Autowired
    ProductFocusDao productFocusDao;
    @Override
    public String createProductFocus(ProductFocus productFocus) {
        productFocus.setMasterId(LoginContext.getLoginId());
        ProductFocusEntity productFocusEntity = BeanMapper.map(productFocus, ProductFocusEntity.class);

        return productFocusDao.createProductFocus(productFocusEntity);
    }

    @Override
    public void updateProductFocus(ProductFocus productFocus) {
        ProductFocusEntity productFocusEntity = BeanMapper.map(productFocus, ProductFocusEntity.class);

        productFocusDao.updateProductFocus(productFocusEntity);
    }

    @Override
    public void deleteProductFocus(String id) {
        productFocusDao.deleteProductFocus(id);
    }

    @Override
    public ProductFocus findOne(String id) {
        ProductFocusEntity productFocusEntity = productFocusDao.findProductFocus(id);

        ProductFocus productFocus = BeanMapper.map(productFocusEntity, ProductFocus.class);
        return productFocus;
    }

    @Override
    public List<ProductFocus> findList(List<String> idList) {
        List<ProductFocusEntity> productFocusEntityList =  productFocusDao.findProductFocusList(idList);

        List<ProductFocus> productFocusList =  BeanMapper.mapList(productFocusEntityList,ProductFocus.class);
        return productFocusList;
    }

    @Override
    public ProductFocus findProductFocus(String id) {
        ProductFocus productFocus = findOne(id);

        joinTemplate.joinQuery(productFocus);

        return productFocus;
    }

    @Override
    public List<ProductFocus> findAllProductFocus() {
        List<ProductFocusEntity> productFocusEntityList =  productFocusDao.findAllProductFocus();

        List<ProductFocus> productFocusList =  BeanMapper.mapList(productFocusEntityList,ProductFocus.class);

        joinTemplate.joinQuery(productFocusList);

        return productFocusList;
    }

    @Override
    public List<ProductFocus> findProductFocusList(ProductFocusQuery productFocusQuery) {
        List<ProductFocusEntity> productFocusEntityList = productFocusDao.findProductFocusList(productFocusQuery);

        List<ProductFocus> productFocusList = BeanMapper.mapList(productFocusEntityList,ProductFocus.class);

        joinTemplate.joinQuery(productFocusList);

        return productFocusList;
    }

    @Override
    public Pagination<ProductFocus> findProductFocusPage(ProductFocusQuery productFocusQuery) {
        Pagination<ProductFocusEntity>  pagination = productFocusDao.findProductFocusPage(productFocusQuery);

        List<ProductFocus> productFocusList = BeanMapper.mapList(pagination.getDataList(),ProductFocus.class);

        joinTemplate.joinQuery(productFocusList);

        return PaginationBuilder.build(pagination,productFocusList);
    }

    /**
     * 通过条件删除产品关联关系
     * @param productFocusQuery
     */
    @Override
    public void deleteProductFocusByQuery(ProductFocusQuery productFocusQuery) {
        List<ProductFocusEntity> productFocusList = productFocusDao.findProductFocusList(productFocusQuery);
        if(productFocusList.size() > 0){
            for (ProductFocusEntity productFocusEntity : productFocusList) {
                productFocusDao.deleteProductFocus(productFocusEntity.getId());
            }
        }
    }
}
