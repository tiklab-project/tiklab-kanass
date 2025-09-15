package io.tiklab.kanass.product.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.common.SendMessageUtil;
import io.tiklab.kanass.home.insight.service.ProjectInsightReportService;
import io.tiklab.kanass.product.product.dao.ProductDao;
import io.tiklab.kanass.product.product.entity.ProductEntity;
import io.tiklab.kanass.product.product.model.Product;
import io.tiklab.kanass.product.product.model.ProductQuery;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.workitem.model.WorkItem;
import io.tiklab.kanass.workitem.model.WorkItemQuery;
import io.tiklab.kanass.workitem.service.WorkItemService;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    public final ExecutorService executorService = Executors.newCachedThreadPool();
    @Autowired
    private ProductDao productDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    UserProcessor userProcessor;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private DmUserService dmUserService;

    @Autowired
    ProjectInsightReportService projectInsightReportService;

    @Autowired
    WorkItemService workItemService;

    @Autowired
    SendMessageUtil sendMessageUtil;

    // 创建产品
    void sendCreateProductMessage(Product product){
        HashMap<String, Object> content = new HashMap<>();
        content.put("productName", product.getName());
        content.put("productId", product.getId());
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("creater", user.getNickname());
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        content.put("link", "/#/product/${productId}");
        content.put("action", "创建项目集");
        content.put("noticeId", "KANASS_MESSAGETYPE_PRODUCT_CREATE");

        sendMessageUtil.sendMessage(content);
    }

    // 更新产品
    void sendUpdateProductMessage(Product product){
        HashMap<String, Object> content = new HashMap<>();
        content.put("productName", product.getName());
        content.put("productId", product.getId());
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("creater", user.getNickname());
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        content.put("link", "/#/product/${productId}");
        content.put("action", "编辑项目集");
        content.put("noticeId", "KANASS_MESSAGETYPE_PRODUCT_UPDATE");

        sendMessageUtil.sendMessage(content);
    }

    // 删除产品
    void sendDeleteProductMessage(Product product){
        HashMap<String, Object> content = new HashMap<>();
        content.put("productName", product.getName());
        content.put("productId", product.getId());
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        content.put("creater", user.getNickname());
        content.put("createUserIcon",user.getNickname().substring( 0, 1).toUpperCase());
        content.put("receiveTime", new SimpleDateFormat("MM-dd").format(new Date()));

        content.put("link", "/#/product");
        content.put("action", "删除项目集");
        content.put("noticeId", "KANASS_MESSAGETYPE_PRODUCT_DELETE");

        sendMessageUtil.sendMessage(content);
    }

    /**
     * 创建产品
     *
     * @param product
     * @return
     */
    @Override
    public String createProduct(Product product) {
        String createUserId = LoginContext.getLoginId();
        User user = userProcessor.findOne(createUserId);
        product.setMaster(user);

        // 默认状态未开始
        product.setStatus("0");

        // 设置项目集头像颜色
        int color = new Random().nextInt(3) + 1;
        System.out.println(color);
        product.setColor(color);
        product.setCreator(createUserId);

        // 设置创建时间
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        product.setCreatTime(format);

        ProductEntity productEntity = BeanMapper.map(product, ProductEntity.class);
        String productId = productDao.createProduct(productEntity);

        String masterId = user.getId();
        initProductDmRole(masterId, productId);

        product.setId(productId);

        executorService.submit(() -> {
            sendCreateProductMessage(product);
        });
        return productId;
    }

    /**
     * 初始化项目集角色
     * @param masterId
     * @param productId
     */
    public void initProductDmRole(String masterId, String productId){
        List<PatchUser> patchUsers = new ArrayList<PatchUser>();
        if(!masterId.equals("111111")){
            // 初始化创建者
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(productId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setUserId(masterId);
            patchUser.setRoleType(2);
            patchUsers.add(patchUser);

            // 初始化"111111"
            PatchUser patchUser1 = new PatchUser();
            DmUser dmUser1 = new DmUser();
            dmUser1.setDomainId(productId);
            User user1 = new User();
            user1.setId("111111");
            dmUser1.setUser(user1);

            patchUser1.setUserId("111111");
            patchUser1.setRoleType(2);
            patchUsers.add(patchUser1);

        }else {
            PatchUser patchUser = new PatchUser();
            DmUser dmUser = new DmUser();
            dmUser.setDomainId(productId);
            User user = new User();
            user.setId(masterId);
            dmUser.setUser(user);
            patchUser.setUserId(masterId);
            patchUser.setRoleType(2);
            patchUsers.add(patchUser);
        }
        dmRoleService.initPatchDmRole(productId,patchUsers);
    }

    /**
     * 更新产品
     *
     * @param product
     */
    @Override
    public void updateProduct(Product product) {
        ProductEntity productEntity = BeanMapper.map(product, ProductEntity.class);

        productDao.updateProduct(productEntity);

        Product newProduct = findProduct(product.getId());

        executorService.submit(() -> {
            sendUpdateProductMessage(newProduct);
        });
    }

    /**
     * 删除产品
     *
     * @param id
     */
    @Override
    public void deleteProduct(String id) {
        // 删除产品与项目的关联
        ProjectQuery projectQuery = new ProjectQuery();
        projectQuery.setProductId(id);
        List<Project> projectList = projectService.findProjectList(projectQuery);
        if(projectList.size() > 0){
            for (Project project : projectList) {
                projectService.deleteProject(project.getId());
            }
        }
        Product newProduct = findProduct(id);

        productDao.deleteProduct(id);

        executorService.submit(() -> {
            sendDeleteProductMessage(newProduct);
        });
    }

    /**
     * 根据id查找产品
     *
     * @param id
     * @return
     */
    @Override
    public Product findOne(String id) {
        ProductEntity productEntity = productDao.findProduct(id);

        Product product = BeanMapper.map(productEntity, Product.class);
        return product;
    }

    /**
     * 根据多个id,查找产品列表
     *
     * @param idList
     * @return
     */
    @Override
    public List<Product> findList(List<String> idList) {
        List<ProductEntity> productEntityList =  productDao.findProductList(idList);

        List<Product> productList =  BeanMapper.mapList(productEntityList,Product.class);
        return productList;
    }

    /**
     * 根据id查找产品
     *
     * @param id
     * @return
     */
    @Override
    public Product findProduct(String id) {
        Product product = findOne(id);

        joinTemplate.joinQuery(product, new String[]{"master"});
        return product;
    }

    /**
     * 查找所有产品
     *
     * @return
     */
    @Override
    public List<Product> findAllProduct() {
        List<ProductEntity> productEntityList =  productDao.findAllProduct();
        List<Product> productList =  BeanMapper.mapList(productEntityList,Product.class);
        joinTemplate.joinQuery(productList, new String[]{"master"});
        if (productList.size() > 0){
            //查询项目集下面相关的项目
            for (Product product:productList){
                Integer projectNum = productDao.findProjectNum(product.getId());
                //添加数量
                product.setProjectNumber(projectNum);
            }
        }
        return productList;
    }

    /**
     * 根据条件查询产品列表
     *
     * @param productQuery
     * @return
     */
    @Override
    public List<Product> findProductList(ProductQuery productQuery) {
        List<ProductEntity> productEntityList = productDao.findProductList(productQuery);

        List<Product> productList = BeanMapper.mapList(productEntityList,Product.class);
        findProjectNum(productList);
        joinTemplate.joinQuery(productList, new String[]{"master"});

        return productList;
    }

    /**
     * 查找对应添加项目的个数
     * @param productList
     */
    public void findProjectNum(List<Product> productList){
        int size = productList.size();
        if(size > 0){
            List<String>  productIds = productList.stream().map(item -> item.getId() ).collect(Collectors.toList());
            String[] list = productIds.toArray(new String[productIds.size()]);
            ProjectQuery projectQuery = new ProjectQuery();
            projectQuery.setProductIds(list);
            List<Project> projectList = projectService.findProjectList(projectQuery);
            for (Product product : productList) {
                List<Project> collect = projectList.stream().filter(item -> item.getProductId().equals(product.getId())).collect(Collectors.toList());
                product.setProjectNumber(collect.size());
            }
        }
    }

    /**
     * 根据条件按分页查询产品列表
     *
     * @param productQuery
     * @return
     */
    @Override
    public Pagination<Product> findProductPage(ProductQuery productQuery) {
        // 查找我所参与的私有项目
        DmUserQuery dmUserQuery = new DmUserQuery();
        String createUserId = LoginContext.getLoginId();
        dmUserQuery.setUserId(createUserId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> privateProductIds = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());

        String[] ids = privateProductIds.toArray(new String[privateProductIds.size()]);
        productQuery.setProductIds(ids);

        Pagination<ProductEntity>  pagination = productDao.findProductPage(productQuery);

        List<Product> productList = BeanMapper.mapList(pagination.getDataList(),Product.class);

        // 计算每个项目集的进度
        for (Product product : productList) {
            String id = product.getId();
            HashMap<String, String> param = new HashMap<>();
            param.put("productId", id);
            Map<String, Integer> workItemCount = projectInsightReportService.statisticsTodoWorkByStatus(param);
            int done = workItemCount.get("end");// 已完成
            int all = workItemCount.get("total");// 所有
            if (all != 0){
                product.setProgress(done * 100f / all);
            }else {
                product.setProgress(0.00f);
            }
        }

        findProjectNum(productList);

        joinTemplate.joinQuery(productList, new String[]{"master"});

        return PaginationBuilder.build(pagination,productList);
    }

    /**
     * 查找最近查看的项目集列表
     * @param productQuery
     * @return
     */
    @Override
    public List<Product> findRecentProductList(ProductQuery productQuery) {
        String userId = LoginContext.getLoginId();
        productQuery.setRecentMasterId(userId);

        List<ProductEntity> productEntityList = productDao.findRecentProductList(productQuery);

        List<Product> productList = BeanMapper.mapList(productEntityList,Product.class);
        findProjectNum(productList);
        joinTemplate.joinQuery(productList, new String[]{"master"});

        return productList;
    }

    @Override
    public List<Product> findProductSortRecentTime(ProductQuery productQuery) {
        // 查找最近项目集合
        List<Product> recentProductList = findRecentProductList(productQuery);
        String productId = productQuery.getProductId();
        if(productId != null){
            recentProductList = recentProductList.stream().filter(item -> !item.getId().equals(productId)).collect(Collectors.toList());
        }

        int size = recentProductList.size();
        if(size < 5){
            List<String> collect = recentProductList.stream().map(item -> item.getId()).collect(Collectors.toList());

            if(productId != null){
                collect.add(productId);
            }
            //如果不够5条，查找我可见的项目
            productQuery.setProductId(null);
            List<Product> joinProductList = findJoinProductList(productQuery);
            // 去除已经被点击过的
            joinProductList = joinProductList.stream().filter(item -> !collect.contains(item.getId())).collect(Collectors.toList());
            int lackSize = 5 - size;
            if(joinProductList.size() > lackSize){
                List<Product> products = joinProductList.subList(0, lackSize);
                recentProductList.addAll(products);
            }else {
                recentProductList.addAll(joinProductList);
            }
        }

        return recentProductList;
    }

    /**
     * 查询产品数量 包括 所有 我收藏的 我创建的
     *
     * @param productQuery
     * @return
     */
    @Override
    public Map<String, Integer> findProductCount(ProductQuery productQuery) {
        // 查找我所参与的私有项目
        DmUserQuery dmUserQuery = new DmUserQuery();
        String createUserId = LoginContext.getLoginId();
        dmUserQuery.setUserId(createUserId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> privateProductIds = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());

        String[] ids = privateProductIds.toArray(new String[privateProductIds.size()]);
        productQuery.setProductIds(ids);
        productQuery.setCreator(createUserId);

        Map<String, Integer> productCount = productDao.findProductCount(productQuery);
        return productCount;
    }

    /**
     * 查看我能看到的所有产品
     */
    @Override
    public List<Product> findJoinProductList(ProductQuery productQuery){
        // 查找我所参与的私有项目
        DmUserQuery dmUserQuery = new DmUserQuery();
        String createUserId = LoginContext.getLoginId();
        dmUserQuery.setUserId(createUserId);
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
        List<String> privateProductIds = dmUserList.stream().map(DmUser::getDomainId).collect(Collectors.toList());

        String[] ids = privateProductIds.toArray(new String[privateProductIds.size()]);
        productQuery.setProductIds(ids);

        List<ProductEntity> joinProductList = productDao.findJoinProductList(productQuery);
        List<Product> productList = BeanMapper.mapList(joinProductList,Product.class);


        // 计算每个项目集的进度
        for (Product product : productList) {
            String id = product.getId();
            HashMap<String, String> param = new HashMap<>();
            param.put("productId", id);
            Map<String, Integer> workItemCount = projectInsightReportService.statisticsTodoWorkByStatus(param);
            int done = workItemCount.get("end");// 已完成
            int all = workItemCount.get("total");// 所有
            if (all != 0){
                product.setProgress(done * 100f / all);
            }else {
                product.setProgress(0.00f);
            }
        }

        findProjectNum(productList);
        joinTemplate.joinQuery(productList, new String[]{"master"});
        return productList;

    }

    /**
     * 查询产品下面的项目列表
     *
     * @param projectQuery
     * @return
     */
    @Override
    public List<Project> findProductDetailList(ProjectQuery projectQuery) {
        List<Project> projectList = projectService.findProjectList(projectQuery);
        if(projectList.size() > 0){
            String projectIds = "(" + projectList.stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
            List<Map<String, Object>> projectWorkItemCount = projectService.findProjectWorkItemStatus(projectIds);
            for (Project project : projectList) {
                String id = project.getId();
                List<Map<String, Object>> allList = projectWorkItemCount.stream().filter(workItem -> workItem.get("project_id").equals(id)).collect(Collectors.toList());
                int size = allList.size();
                project.setWorkItemNumber(size);

                List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").equals(id) && workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
                project.setEndWorkItemNumber(doneList.size());

                DmUserQuery dmUserQuery = new DmUserQuery();
                dmUserQuery.setDomainId(id);
                List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
                project.setMember(dmUserList.size());

                // 统计项目预计工时和剩余工时
                WorkItemQuery workItemQuery = new WorkItemQuery();
                workItemQuery.setProjectId(id);
                List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                int estimateTime = 0;
                int surplusTime = 0;
                for (WorkItem workItem : workItemList) {
                    estimateTime = estimateTime + (workItem.getEstimateTime() == null ? 0 : workItem.getEstimateTime());
                    surplusTime = surplusTime + (workItem.getSurplusTime() == null ? 0 : workItem.getSurplusTime());
                }
                project.setEstimateTime(estimateTime);
                project.setSurplusTime(surplusTime);
            }
        }

        return projectList;
    }

    @Override
    public Pagination<Project> findProductDetailPage(ProjectQuery projectQuery) {
        Pagination<Project> projectPage = projectService.findProjectPage(projectQuery);
        if(!projectPage.getDataList().isEmpty()){
            String projectIds = "(" + projectPage.getDataList().stream().map(item -> "'" + item.getId() + "'").collect(Collectors.joining(", ")) + ")";
            List<Map<String, Object>> projectWorkItemCount = projectService.findProjectWorkItemStatus(projectIds);
            for (Project project : projectPage.getDataList()) {
                String id = project.getId();
                List<Map<String, Object>> allList = projectWorkItemCount.stream().filter(workItem -> workItem.get("project_id").equals(id)).collect(Collectors.toList());
                int size = allList.size();
                project.setWorkItemNumber(size);

                List<Map<String, Object>> doneList = projectWorkItemCount.stream().filter(workItem -> (workItem.get("project_id").equals(id) && workItem.get("work_status_code").equals("DONE"))).collect(Collectors.toList());
                project.setEndWorkItemNumber(doneList.size());

                DmUserQuery dmUserQuery = new DmUserQuery();
                dmUserQuery.setDomainId(id);
                List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
                project.setMember(dmUserList.size());

                // 统计项目预计工时和剩余工时
                WorkItemQuery workItemQuery = new WorkItemQuery();
                workItemQuery.setProjectId(id);
                List<WorkItem> workItemList = workItemService.findWorkItemList(workItemQuery);
                int estimateTime = 0;
                int surplusTime = 0;
                for (WorkItem workItem : workItemList) {
                    estimateTime = estimateTime + (workItem.getEstimateTime() == null ? 0 : workItem.getEstimateTime());
                    surplusTime = surplusTime + (workItem.getSurplusTime() == null ? 0 : workItem.getSurplusTime());
                }
                project.setEstimateTime(estimateTime);
                project.setSurplusTime(surplusTime);
            }
        }

        return projectPage;
    }
}
