package io.tiklab.kanass.product.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.kanass.product.dao.ProductDao;
import io.tiklab.kanass.product.entity.ProductEntity;
import io.tiklab.kanass.product.model.Product;
import io.tiklab.kanass.product.model.ProductQuery;
import io.tiklab.kanass.project.project.model.Project;
import io.tiklab.kanass.project.project.model.ProjectQuery;
import io.tiklab.kanass.project.project.service.ProjectService;
import io.tiklab.kanass.projectset.entity.ProjectSetEntity;
import io.tiklab.privilege.dmRole.service.DmRoleService;
import io.tiklab.privilege.role.model.PatchUser;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService{

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
        projectQuery.setProjectSetId(id);
        List<Project> projectList = projectService.findProjectList(projectQuery);
        if(projectList.size() > 0){
            for (Project project : projectList) {
                project.setProductId("nullstring");
                projectService.updateProject(project);
            }
        }

        productDao.deleteProduct(id);
    }

    /**
     * 根据id查找产品
     *
     * @param id
     * @return
     */
    @Override
    public Product findOne(String id) {
        return null;
    }

    /**
     * 根据多个id,查找产品列表
     *
     * @param idList
     * @return
     */
    @Override
    public List<Product> findList(List<String> idList) {
        return List.of();
    }

    /**
     * 根据id查找产品
     *
     * @param id
     * @return
     */
    @Override
    public Product findProduct(String id) {
        return null;
    }

    /**
     * 查找所有产品
     *
     * @return
     */
    @Override
    public List<Product> findAllProduct() {
        return List.of();
    }

    /**
     * 根据条件查询产品列表
     *
     * @param productQuery
     * @return
     */
    @Override
    public List<Product> findProductList(ProductQuery productQuery) {
        return List.of();
    }

    /**
     * 根据条件按分页查询产品列表
     *
     * @param productQuery
     * @return
     */
    @Override
    public Pagination<Product> findProductPage(ProductQuery productQuery) {
        return null;
    }
}
