package io.tiklab.kanass.product.plan.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.core.utils.UuidGenerator;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.product.plan.dao.ProductPlanDao;
import io.tiklab.kanass.product.plan.dao.ProductPlanStateDao;
import io.tiklab.kanass.product.plan.entity.ProductPlanEntity;
import io.tiklab.kanass.product.plan.model.ProductPlan;
import io.tiklab.kanass.product.plan.model.ProductPlanQuery;
import io.tiklab.kanass.product.plan.model.ProductPlanState;
import io.tiklab.kanass.product.product.service.ProductService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkProductPlan;
import io.tiklab.kanass.workitem.model.WorkProductPlanQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.kanass.workitem.service.WorkProductPlanService;
import io.tiklab.message.message.model.Message;
import io.tiklab.message.message.model.SendMessageNotice;
import io.tiklab.message.message.service.SendMessageNoticeService;
import io.tiklab.message.setting.model.MessageType;
import io.tiklab.privilege.vRole.model.VRoleDomain;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
* 产品计划服务
*/
@Service
public class ProductPlanServiceImpl implements ProductPlanService {
    private static final Logger log = LoggerFactory.getLogger(ProductPlanServiceImpl.class);
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    ProductPlanDao productPlanDao;

    @Autowired
    ProductPlanStateDao productPlanStateDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ProductService productService;

    @Autowired
    ProductPlanFocusService productPlanFocusService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    WorkProductPlanService workProductPlanService;
    @Autowired
    UserProcessor userProcessor;

    @Value("${base.url:null}")
    String baseUrl;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;



    /**
     * 发送消息
     * @param productPlan
     */
    void sendMessageForCreatProductPlan(ProductPlan productPlan ){
        String productId = productPlan.getProduct().getId();
        HashMap<String, Object> content = new HashMap<>();
        content.put("productPlanName", productPlan.getProductPlanName());
        content.put("productPlanId", productPlan.getId());
        content.put("productId", productPlan.getProduct().getId());

        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_SPRINTCREATE");
        message.setMessageType(messageType);
        message.setData(content);

        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));
        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        String msg = JSONObject.toJSONString(content);

        sendMessageNotice.setBaseUrl(baseUrl);
        sendMessageNotice.setId("KANASS_MESSAGE_SPRINTCREATE");
        sendMessageNotice.setLink("/${productId}/productPlan/${productPlanId}/workitem");
        sendMessageNotice.setAction(productPlan.getProductPlanName());
        sendMessageNotice.setSendId(user.getId());
        sendMessageNotice.setSiteData(msg);
        sendMessageNotice.setQywechatData(msg);
        sendMessageNotice.setEmailData(msg);
        sendMessageNotice.setDomainId(productId);
        VRoleDomain vRoleDomain = new VRoleDomain();
        vRoleDomain.setModelId(productPlan.getId());
        vRoleDomain.setType("productPlan");

        sendMessageNotice.setvRoleDomain(vRoleDomain);
        sendMessageNoticeService.sendDmMessageNotice(sendMessageNotice);
    }

    /**
     * 发送消息
     * @param oldProductPlan
     * @param newProductPlan
     */
    void sendMessageForUpdateProductPlanState(ProductPlan oldProductPlan, ProductPlan newProductPlan ){
        String productId = newProductPlan.getProduct().getId();
        HashMap<String, Object> content = new HashMap<>();
        content.put("productPlanName", newProductPlan.getProductPlanName());
        content.put("productPlanId", newProductPlan.getId());
        content.put("productId", productId);
        content.put("oldValue", oldProductPlan.getProductPlanState().getName());
        content.put("newValue", newProductPlan.getProductPlanState().getName());

        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("createUser", user);
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));


        Message message = new Message();
        MessageType messageType = new MessageType();
        messageType.setId("KANASS_MESSAGETYPE_SPRINTUPDATE");
        message.setMessageType(messageType);
        message.setData(content);

        SendMessageNotice sendMessageNotice = new SendMessageNotice();
        String msg = JSONObject.toJSONString(content);

        sendMessageNotice.setBaseUrl(baseUrl);
        sendMessageNotice.setId("KANASS_MESSAGE_SPRINTUPDATE");
        sendMessageNotice.setLink("/${productId}/productPlan/${productPlanId}/workitem");
        sendMessageNotice.setAction(newProductPlan.getProductPlanName());
        sendMessageNotice.setSendId(user.getId());
        sendMessageNotice.setSiteData(msg);
        sendMessageNotice.setQywechatData(msg);
        sendMessageNotice.setEmailData(msg);
        sendMessageNotice.setDomainId(productId);
        VRoleDomain vRoleDomain = new VRoleDomain();
        vRoleDomain.setModelId(newProductPlan.getId());
        vRoleDomain.setType("productPlan");
        sendMessageNotice.setvRoleDomain(vRoleDomain);
        sendMessageNoticeService.sendDmMessageNotice(sendMessageNotice);
    }

    @Override
    public String createProductPlan(@NotNull @Valid ProductPlan productPlan) {
        //初始化产品计划状态
        ProductPlanState productPlanState = new ProductPlanState();
        productPlanState.setId("000000");
        productPlan.setProductPlanState(productPlanState);

        String createUserId = LoginContext.getLoginId();
        User user = new User();
        user.setId(createUserId);
        productPlan.setBuilder(user);

        int color = new Random().nextInt(3) + 1;
        System.out.println(color);
        productPlan.setColor(color);

        ProductPlanEntity productPlanEntity = BeanMapper.map(productPlan, ProductPlanEntity.class);
        String id = productPlanDao.createProductPlan(productPlanEntity);
//        ProductPlan productPlan1 = findProductPlan(id);
//        sendMessageForCreatProductPlan(productPlan1);

        return id;
    }

    @Override
    public String createJiraProductPlan(@NotNull @Valid ProductPlan productPlan) {
        //初始化产品计划状态
        ProductPlanEntity productPlanEntity = BeanMapper.map(productPlan, ProductPlanEntity.class);
        String id = productPlanDao.createProductPlan(productPlanEntity);
        return id;
    }

    @Override
    public void updateProductPlan(@NotNull @Valid ProductPlan productPlan) {
        String productPlanId = productPlan.getId();
        ProductPlan oldProductPlan = findProductPlan(productPlanId);
        ProductPlanState productPlanState = productPlan.getProductPlanState();
        String newProductPlanId = productPlan.getNewProductPlanId();
        // 如果状态更新为完成
        if(productPlanState != null ){
            if(productPlanState.getId().equals("222222")){
                // 创建新的产品计划与事项的记录
                // 只查询产品计划中未完成的事项
                List<WorkItem> productPlanWorkItemList = workItemService.findProductPlanWorkItemList(productPlanId);
                WorkProductPlanQuery query = new WorkProductPlanQuery();
                query.setProductPlanId(productPlanId);
                List<WorkProductPlan> workProductPlanList = workProductPlanService.findWorkProductPlanList(query);
                Map<String, WorkProductPlan> workProductPlanMap = workProductPlanList.stream().collect(Collectors.toMap(WorkProductPlan::getWorkItemId, Function.identity()));
                if(productPlanWorkItemList.size() > 0){
                    String valueString = "";
                    for (WorkItem workItem : productPlanWorkItemList) {
                        // 更新产品计划之后更新待办
                        if(newProductPlanId != null) {
                            ProductPlan productPlan1 = new ProductPlan();
                            productPlan1.setId(newProductPlanId);
                            workItem.setProductPlan(productPlan1);
                            workItem.setUpdateField("productPlan");
                            workItemService.updateTodoTaskData(workItem);

                            // 关联关系表更新迁移后的id
                            WorkProductPlan workProductPlan = workProductPlanMap.get(workItem.getId());
                            workProductPlan.setTargetProductPlanId(newProductPlanId);
                            workProductPlanService.updateWorkProductPlan(workProductPlan);
                        }else {
                            workItem.setUpdateField("productPlan");
                            workItem.setProductPlan(null);
                            workItemService.updateTodoTaskData(workItem);

                            // 关联关系表更新迁移后的id
                            WorkProductPlan workProductPlan = workProductPlanMap.get(workItem.getId());
                            workProductPlan.setTargetProductPlanId(null);
                            workProductPlanService.updateWorkProductPlan(workProductPlan);
                        }

                        String id = UuidGenerator.getRandomIdByUUID(12);
                        String sql = "('" + id + "', '" + workItem.getId() + "', '" + newProductPlanId + "'),";
                        valueString = valueString.concat(sql);
                    }
                    int length = valueString.length() - 1;
                    String substring = valueString.substring(0, length);
                    if(newProductPlanId != null){
                        // 更新事项与产品计划的关联
                        workProductPlanService.createBatchWorkProductPlan(substring);
                    }
                }

                // 更新事项的产品计划, 没有完成的更新到选择的新的产品计划或者待办列表
                workItemService.updateBatchWorkItemProductPlan(productPlanId, newProductPlanId);

            }

            if(productPlanState.getId().equals("111111")){
                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = formater.format(new Date());
                productPlan.setRelaStartTime(format);
            }

        }

        //设置结束时间
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formater.format(new Date());
        productPlan.setRelaEndTime(format);
        ProductPlanEntity productPlanEntity = BeanMapper.map(productPlan, ProductPlanEntity.class);
        productPlanDao.updateProductPlan(productPlanEntity);
        ProductPlan newProductPlan = findProductPlan(productPlanId);
        if(productPlanState != null){
            executorService.submit(() -> {
                sendMessageForUpdateProductPlanState(oldProductPlan, newProductPlan);
            });
        }
    }

    @Override
    public void deleteProductPlan(@NotNull String id) {
        productPlanDao.deleteProductPlan(id);

        // 删除关注的产品计划数据
        productPlanDao.deleteProductPlanFocus(id);

    }

    @Override
    public ProductPlan findOne(String id) {
        ProductPlanEntity productPlanEntity = productPlanDao.findProductPlan(id);

        ProductPlan productPlan = BeanMapper.map(productPlanEntity, ProductPlan.class);
        return productPlan;
    }

    @Override
    public List<ProductPlan> findList(List<String> idList) {
        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findProductPlanList(idList);

        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);

        return productPlanList;
    }

    @Override
    public ProductPlan findProductPlan(@NotNull String id) {
        ProductPlan productPlan = findOne(id);

        if(!Objects.isNull(productPlan)){
            HashMap<String, Integer> productPlanWorkItemNum = workItemService.findProductPlanWorkItemNum(id);
            productPlan.setWorkNumber(productPlanWorkItemNum.get("all"));
            productPlan.setWorkDoneNumber(productPlanWorkItemNum.get("done"));
            productPlan.setWorkProgressNumber(productPlanWorkItemNum.get("progress"));

            Map<String, Integer> productPlanWorkTime = workItemService.findProductPlanWorkTime(id);
            productPlan.setEstimateTime(productPlanWorkTime.get("estimateTime"));
            productPlan.setSurplusTime(productPlanWorkTime.get("surplusTime"));
        }


        joinTemplate.joinQuery(productPlan, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlan;
    }

    @Override
    public List<ProductPlan> findAllProductPlan() {
        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findAllProductPlan();

        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);

        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlanList;
    }

    @Override
    public List<ProductPlan> findProductPlanList(ProductPlanQuery productPlanQuery) {
        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findProductPlanList(productPlanQuery);
        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);
        if(productPlanList.size() > 0){
            String productPlanIds = "(" + productPlanEntityList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<Map<String, String>> productPlanWorkItemList = workProductPlanService.findProductPlanWorkItemNum(productPlanIds);
            for (ProductPlan productPlan : productPlanList) {
                String id = productPlan.getId();
                List<String> countList = productPlanWorkItemList.stream().filter(map -> map.get("product_plan_id").equals(id)).map(map -> map.get("product_plan_id")).collect(Collectors.toList());
                productPlan.setWorkNumber(countList.size());
            }
        }
        // 查找产品计划的事项数量
        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlanList;
    }

    public List<ProductPlan> findSelectProductPlanList(ProductPlanQuery productPlanQuery) {
        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findSelectProductPlanList(productPlanQuery);
        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);

        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlanList;
    }

    /**
     * 查找产品计划并设置是否被当前用户关注
     * @param productPlanQuery
     * @return
     */
    @Override
    public Pagination<ProductPlan> findProductPlanPage(ProductPlanQuery productPlanQuery) {

        Pagination<ProductPlanEntity> pagination = productPlanDao.findProductPlanPage(productPlanQuery);

        List<ProductPlan> productPlanList = BeanMapper.mapList(pagination.getDataList(), ProductPlan.class);

        if(productPlanList.size() > 0){
            String productPlanIds = "(" + productPlanList.stream().map(item -> "'" + item.getId() + "'").
                    collect(Collectors.joining(", ")) + ")";
            List<String> focusProductPlanIds = productPlanFocusService.findFocusProductPlanIds();
            List<Map<String, String>> productPlanWorkItemList = workProductPlanService.findProductPlanWorkItemNum(productPlanIds);

            for (ProductPlan productPlan : productPlanList) {
                String id = productPlan.getId();
                if(focusProductPlanIds.contains(id)){
                    productPlan.setFocusIs(true);
                }
                List<Map<String, String>> countList = productPlanWorkItemList.stream().filter(map -> map.get("product_plan_id").equals(id)).collect(Collectors.toList());

                if (CollectionUtils.isEmpty(countList)){
                    productPlan.setWorkDoneNumber(0);
                    productPlan.setWorkProgressNumber(0);
                    productPlan.setWorkNumber(0);
                    continue;
                }

                productPlan.setWorkDoneNumber( (int) countList.stream().filter(workItem -> workItem.get("work_status_code").equals("DONE")).count());
                productPlan.setWorkProgressNumber( (int) countList.stream().filter(workItem -> workItem.get("work_status_code").equals("PROGRESS")).count());
                productPlan.setWorkNumber(countList.size());
            }
        }
        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return PaginationBuilder.build(pagination,productPlanList);
    }

    @Override
    public List<ProductPlan> findFocusProductPlanList(ProductPlanQuery productPlanQuery) {

        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findFocusProductPlanList(productPlanQuery);

        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);

        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlanList;
    }

    @Override
    public List<ProductPlan> findProgressProductPlan() {
        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findProgressProductPlan();

        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);

        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlanList;
    }

    /**
     * 根据事项id 查找被关联的产品计划
     * @param workId
     * @return
     */
    @Override
    public List<ProductPlan> findWorkProductPlanList(String workId) {
        List<ProductPlanEntity> productPlanEntityList = productPlanDao.findWorkProductPlanList(workId);
        List<ProductPlan> productPlanList = BeanMapper.mapList(productPlanEntityList, ProductPlan.class);

        joinTemplate.joinQuery(productPlanList, new String[]{"master", "builder", "product", "productPlanState"});
        return productPlanList;
    }

    @Override
    public Map<String, Integer> findProductPlanCount(ProductPlanQuery productPlanQuery) {
        productPlanQuery.setBuilderId(LoginContext.getLoginId());
        Map<String, Integer> productPlanCount = productPlanDao.findProductPlanCount(productPlanQuery);
        return productPlanCount;
    }
}