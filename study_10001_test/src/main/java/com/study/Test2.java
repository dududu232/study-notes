package com.study;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zjj
 * @Date: 2026/03/28/20:09
 * @Description:
 */
public class Test2 {
    /*public static void main(String[] args) {
        String aa = "{\"success\":true,\"data\":{\"items\":[{\"summary\":\"高级管理层履职机制不健全风险\",\"detail\":\"根据检查过程，制度虽明确了信息科技委员会对快速迭代类项目的审议职责，以及重大信息系统项目的定义，但未明确规定重大信息系统项目开发需经董事会批准，也未建立高级管理层（如董事会、信息科技管理委员会）对重大信息系统项目定期报告审核、风险评估汇报听取及风险控制监督的机制。《银行业金融机构信息科技风险管理指引》第三十条明确要求董事会应对重大科技项目进行审批并持续监督，确保符合IT战略与业务目标。当前制度缺失上述关键管控环节，可能导致项目偏离战略方向、风险失控或治理失效。\n【整改建议】\\n1）在《XX银行科技项目管理办法》中补充规定：所有重大信息系统项目立项前须提交董事会审议批准，明确审批流程及相关材料要求；\\n2）建立重大信息系统项目定期报告机制，在制度中规定信息科技管理委员会应每季度听取重大项目进展及风险评估汇报，并保留会议纪要；\\n3）在《XX银行科技项目管理办法》中增加条款，明确信息科技管理委员会对重大项目建设全过程的风险控制监督职责。\"},{\"summary\":\"信息科技部门开发组织架构不明确风险\",\"detail\":\"在提交的检查材料中未发现总行信息科技部的组织架构图，且现有制度文件未明确说明信息科技部门是否设立独立的项目开发组织或相关科室。尽管《XX银行科技项目管理办法》第三章第九条明确了信息科技部承担系统设计、编码等开发职责，但缺乏组织保障层面的支撑，无法确认是否存在专职团队履行该职能。依据《银行业金融机构信息科技风险管理指引》第十九条，应建立职责清晰、结构合理的科技治理架构。组织架构缺失将导致职责落实不到位、管理责任不清，影响项目执行效率与质量管控。\\n【整改建议】\\n1）在《XX银行科技项目管理办法》中补充总行信息科技部的组织架构图，并标注负责项目开发的职能部门；\\n2）在制度中明确定义开发组织的部门名称、岗位设置及其在项目全生命周期中的管理职责；\n3）对于暂未设立独立开发组织的情况，应制定过渡期职责分工方案并纳入制度附件。\"},{\"summary\":\"开发测试岗位分离机制缺失风险\",\"detail\":\"虽然《XX银行信息科技快速迭代类项目管理细则》第二章第四条分别列明了开发人员与测试人员的职责，但未规定两者必须由不同人员担任，亦无禁止兼任的强制性要求，缺乏基本的职责制衡机制。根据《商业银行信息科技风险管理指引》第四十二条，应实行开发与测试岗位分离，防止自我验证、掩盖缺陷等问题。岗位未分离可能引发测试独立性丧失、质量问题漏检、操作风险上升，甚至造成生产事故。\\n【整改建议】\\n1）在《XX银行信息科技快速迭代类项目管理细则》中增加强制性条款，明确规定开发人员与测试人员不得由同一人兼任，实现物理岗位分离；\\n2）在制度中建立开发测试人员名单备案机制，由人力资源部门和信息科技管理部门联合审核并定期检查兼职情况；\\n3）将开发测试分离执行情况纳入内部审计检查范围，并在后评价环节予以评估。\"}]}}";

        JSONObject resp = JSONObject.parseObject(aa);
        JSONObject data = resp.getJSONObject("data");

        JSONArray items = data.getJSONArray("items");

        for (int i = 0; i < items.size(); i++) {
            JSONObject item = items.getJSONObject(i);
            System.out.println(item.getString("detail"));
        }
    }*/

    public static void main(String[] args) throws IOException {
        XWPFDocument doc = new XWPFDocument(new FileInputStream("D:\\tgky\\aiptint\\service\\src\\main\\resources\\templates\\mission-final-check-report-template.docx"));
        String content = "理失效。\n【整改建议】\n1）在《XX银行科技项目管理办";
        XWPFParagraph bodyPara = doc.createParagraph();          // 创建新段落
        bodyPara.setStyle("Normal");                                  // 可选：设置普通样式
        XWPFRun bodyRun = bodyPara.createRun();
        // 按\n分割文本
        String[] lines = content.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            bodyRun.setText(lines[i]);
            // 不是最后一行就添加换行
            if (i != lines.length - 1) {
                bodyRun.addBreak();
            }
        }
        bodyRun.setFontSize(12);                                      // 设置字体大小
        bodyRun.setBold(false);

        doc.write(new FileOutputStream("D:\\ccccccccc.docx"));
    }
}
