package com.wechat.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Element;

import java.io.Writer;
import java.util.HashMap;
import java.util.List;

public class XmlUtil {
    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    public static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * xml解析为map
     *
     * @param root 根节点
     * @param map 返回的map
     */
    public static void parserXml(Element root, HashMap<String, String> map) {
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();
        // 判断有没有子元素列表
        if (elementList.size() == 0) {
            map.put(root.getName(), root.getText());
        } else {
            // 遍历
            for (Element e : elementList) {
                parserXml(e, map);
            }
        }
    }
}