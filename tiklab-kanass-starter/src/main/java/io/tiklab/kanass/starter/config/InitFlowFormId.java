package io.tiklab.kanass.starter.config;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.flow.flow.model.Flow;
import io.tiklab.flow.flow.service.FlowService;
import io.tiklab.form.form.model.Form;
import io.tiklab.kanass.workitem.model.WorkType;
import io.tiklab.kanass.workitem.model.WorkTypeDm;
import io.tiklab.kanass.workitem.service.WorkTypeDmService;
import io.tiklab.kanass.workitem.service.WorkTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


//@Component
public class InitFlowFormId implements ApplicationRunner {

    @Autowired
    JpaTemplate jpaTemplate;

    @Autowired
    WorkTypeDmService workTypeDmService;

    @Autowired
    FlowService flowService;

    @Autowired
    WorkTypeService workTypeService;


    private static Logger logger = LoggerFactory.getLogger(InitFlowFormId.class);

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        System.out.println("0");
        updateWorkType();

    }

    public void updateWorkType(){
        List<WorkType> allWorkType = workTypeService.findAllWorkType();
//        for (WorkType workType : allWorkType) {
//            Form form = new Form();
//            form.setId(id);
//            workType.setForm(form);
//            workTypeService.updateWorkType(workType);
//        }
//
//        List<WorkTypeDm> allWorkTypeDm = workTypeDmService.findAllWorkTypeDm();
//        System.out.println("走了");
//        for (WorkTypeDm workTypeDm : allWorkTypeDm) {
//            String id = workTypeDm.getFlow().getId();
//            Flow flow = flowService.findFlow(id);
//            if(!Objects.isNull(flow)){
//                String formId = flow.getForm().getId();
//                Form form = new Form();
//                form.setId(formId);
//                workTypeDm.setForm(form);
//                workTypeDmService.updateWorkTypeDm(workTypeDm);
//            }
//        }
  }
}
