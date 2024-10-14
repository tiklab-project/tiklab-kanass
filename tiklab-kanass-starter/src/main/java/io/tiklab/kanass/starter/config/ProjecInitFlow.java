package io.tiklab.kanass.starter.config;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.kanass.support.service.CopyFlowToProjectService;
import io.tiklab.kanass.support.service.InitStateNodeFiledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

//@Component
public class ProjecInitFlow implements ApplicationRunner {

    @Autowired
    CopyFlowToProjectService copyFlowToProjectService;

    @Autowired
    InitStateNodeFiledService initStateNodeFiledService;

    @Autowired
    JpaTemplate jpaTemplate;


    private static Logger logger = LoggerFactory.getLogger(ProjecInitFlow.class);


    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
        copyFlowToProjectService.copyFlow();
    }
}
