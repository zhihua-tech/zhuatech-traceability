/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.traceability.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { double materialCoverage=request.materialLots()==0?100:request.linkedMaterialLots()*100d/request.materialLots();double processCoverage=request.processSteps()==0?100:request.recordedSteps()*100d/request.processSteps();int score=(int)Math.round((materialCoverage+processCoverage+request.serialCoverageRate())/3);List<String> actions=new ArrayList<>();if(materialCoverage<100)actions.add("补齐投入物料批次关系");if(processCoverage<100)actions.add("补录缺失生产事件");if(request.serialCoverageRate()<100)actions.add("补齐产品序列号映射");if(!request.inspectionPassed()){score-=50;actions.add("禁止未检验合格批次放行");}return result(score,actions,"TRACEABLE","INCOMPLETE","BLOCKED",Map.of("materialCoverage",materialCoverage,"processCoverage",processCoverage,"serialCoverage",request.serialCoverageRate())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String batchNo,
        @PositiveOrZero int materialLots,
        @PositiveOrZero int linkedMaterialLots,
        @PositiveOrZero int processSteps,
        @PositiveOrZero int recordedSteps,
        @DecimalMin("0") @DecimalMax("100") double serialCoverageRate,
        boolean inspectionPassed) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
