package io.tiklab.kanass.product.plan.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.core.BaseModel;
import io.tiklab.kanass.product.product.model.Product;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinField;
import io.tiklab.user.user.model.User;

/**
 * 产品计划模板
 */
@ApiModel
@Join
@Mapper
public class ProductPlan extends BaseModel {

    @ApiProperty(name="id",desc="产品计划ID")
    private String id;

    @ApiProperty(name="newProductPlanId",desc="产品计划ID")
    private String newProductPlanId;


    @ApiProperty(name="productPlanName",desc="产品计划名称",eg="@text32")
    private String productPlanName;

    @ApiProperty(name = "focusIs", desc = "当前用户是否关注")
    private boolean focusIs;

    @ApiProperty(name="desc",desc="产品计划描述",eg="@text32")
    private String desc;

    private int color;

    @ApiProperty(name="master",desc="负责人")
    @Mappings({
            @Mapping(source = "master.id",target = "master")
    })
    @JoinField(key = "id")
    private User master;

    @ApiProperty(name="builder",desc="创建人")
    @Mappings({
            @Mapping(source = "builder.id",target = "builder")
    })
    @JoinField(key = "id")
    private User builder;

    @ApiProperty(name="product",desc="所属项目",eg="@selectOne")
    @Mappings({
            @Mapping(source = "product.id",target = "productId")
    })
    @JoinField(key = "id")
    private Product product;

    @ApiProperty(name="productPlanState",desc="产品计划状态")
    @Mappings({
            @Mapping(source = "productPlanState.id",target = "productPlanStateId")
    })
    @JoinField(key = "id")
    private ProductPlanState productPlanState;

    @ApiProperty(name="startTime",desc="开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String startTime;

    @ApiProperty(name="endTime",desc="结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    @ApiProperty(name="relaStartTime",desc="实际开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String relaStartTime;

    @ApiProperty(name="relaEndTime",desc="实际结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private String relaEndTime;

    @ApiProperty(name="work_number",desc="产品计划的事项数量")
    private Integer workNumber;

    @ApiProperty(name="work_done_number",desc="产品计划的已完成事项数量")
    private Integer workDoneNumber;

    @ApiProperty(name="work_progress_number",desc="产品计划的未完成事项数量")
    private Integer workProgressNumber;

    @ApiProperty(name="quantityNumber",desc="完成数量")
    private Integer quantityNumber;

    @ApiProperty(name="estimateTime",desc="计划工时")
    private Integer estimateTime;

    @ApiProperty(name="surplusTime",desc="剩余工时")
    private Integer surplusTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getProductPlanName() {
        return productPlanName;
    }

    public void setProductPlanName(String productPlanName) {
        this.productPlanName = productPlanName;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

    public ProductPlanState getProductPlanState() {
        return productPlanState;
    }

    public void setProductPlanState(ProductPlanState productPlanState) {
        this.productPlanState = productPlanState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRelaStartTime() {
        return relaStartTime;
    }

    public void setRelaStartTime(String relaStartTime) {
        this.relaStartTime = relaStartTime;
    }

    public String getRelaEndTime() {
        return relaEndTime;
    }

    public void setRelaEndTime(String relaEndTime) {
        this.relaEndTime = relaEndTime;
    }

    public Integer getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(Integer quantityNumber) {
        this.quantityNumber = quantityNumber;
    }


    public Integer getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(Integer workNumber) {
        this.workNumber = workNumber;
    }

    public String getNewProductPlanId() {
        return newProductPlanId;
    }

    public void setNewProductPlanId(String newProductPlanId) {
        this.newProductPlanId = newProductPlanId;
    }

    public Integer getWorkDoneNumber() {
        return workDoneNumber;
    }

    public void setWorkDoneNumber(Integer workDoneNumber) {
        this.workDoneNumber = workDoneNumber;
    }

    public Integer getWorkProgressNumber() {
        return workProgressNumber;
    }

    public void setWorkProgressNumber(Integer workProgressNumber) {
        this.workProgressNumber = workProgressNumber;
    }

    public User getBuilder() {
        return builder;
    }

    public void setBuilder(User builder) {
        this.builder = builder;
    }

    public boolean isFocusIs() {
        return focusIs;
    }

    public void setFocusIs(boolean focusIs) {
        this.focusIs = focusIs;
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Integer getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Integer estimateTime) {
        this.estimateTime = estimateTime;
    }

    public Integer getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(Integer surplusTime) {
        this.surplusTime = surplusTime;
    }
}
