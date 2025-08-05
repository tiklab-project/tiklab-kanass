package io.tiklab.kanass.product.dao;

import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductFocusDao {
    private static Logger logger = LoggerFactory.getLogger(ProductFocusDao.class);

    @Autowired
    JpaTemplate jpaTemplate;
}
