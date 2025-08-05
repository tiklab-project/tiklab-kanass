package io.tiklab.kanass.product.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductModuleDao {
    private static Logger logger = LoggerFactory.getLogger(ProductModuleDao.class);

    @Autowired
    JpaTemplate jpaTemplate;
}
