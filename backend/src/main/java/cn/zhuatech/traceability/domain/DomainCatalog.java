/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.traceability.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("RELEASE", new WorkflowAction("RELEASE", "提交批次放行", List.of("草稿"), "已放行", "ADMIN"));
        actions.put("SHIP", new WorkflowAction("SHIP", "确认发运", List.of("已放行"), "在途", "OPERATOR"));
        actions.put("CLOSE", new WorkflowAction("CLOSE", "确认交付", List.of("在途"), "已交付", "ADMIN"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技产品全链路追溯系统"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "批次、序列号、物料谱系、生产事件、质检、物流、销售、召回与审计"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "批次/产品"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "召回风险金额"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "追溯数量"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "追溯期限"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("LOT", "批次管理", "统一原料、半成品和成品批次规则"),
            new ModuleDefinition("SERIAL", "序列号管理", "管理单品身份、包装层级和状态"),
            new ModuleDefinition("GENEALOGY", "产品谱系", "关联投入批次、工序、设备和产出"),
            new ModuleDefinition("PRODUCTION_EVENT", "生产事件", "采集投料、加工、返工和报废事件"),
            new ModuleDefinition("INSPECTION", "质量记录", "绑定检验、放行、不合格和处置结果"),
            new ModuleDefinition("LOGISTICS", "物流追踪", "记录库位、装箱、发运和签收节点"),
            new ModuleDefinition("SALES", "销售去向", "连接订单、客户、区域和交付批次"),
            new ModuleDefinition("RECALL", "召回管理", "模拟与执行正向、反向召回"),
            new ModuleDefinition("AUDIT", "追溯审计", "检查链路完整性、时效和数据异常")
        ); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
